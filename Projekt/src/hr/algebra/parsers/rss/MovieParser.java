/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parsers.rss;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Genre;
import hr.algebra.model.Person;
import hr.algebra.model.Movie;
import hr.algebra.parsers.StringParser;
import hr.algebra.utils.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author HT-ICT
 */
public class MovieParser {

    private static final String RSS_URL = "https://www.blitz-cinestar.hr/rss.aspx?najava=2";
    private static final String EXT = ".jpg";
    private static final String DIR = "assets";

    public static List<Movie> parse() throws IOException, XMLStreamException {
        List<Movie> movies = new ArrayList<>();
        HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(RSS_URL);

        try (InputStream is = con.getInputStream()) {
            XMLEventReader reader = ParserFactory.createStaxParser(is);

            StartElement startElement = null;
            Movie movie = null;
            Optional<TagType> tagType = Optional.empty();
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);
                        if (tagType.isPresent() && tagType.get() == TagType.ITEM) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        String data = characters.getData().trim();

                        if (tagType.isPresent()) {
                            switch (tagType.get()) {
                                case TITLE:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                    break;
                                case PUB_DATE:
                                    if (movie != null && !data.isEmpty()) {
                                        LocalDateTime localDateTime = LocalDateTime.parse(data, DateTimeFormatter.RFC_1123_DATE_TIME);
                                        movie.setPublishedDate(localDateTime);
                                    }
                                    break;
                                case DESCRIPTION:
                                    if (movie != null && !data.isEmpty()) {
                                        String details = StringParser.parsefromHTML(data, ">");
                                        movie.setDescription(details);
                                    }
                                    break;
                                case ORGINALNI_NAZIV:
                                    if (movie != null && !data.isEmpty()) {
                                        movie.setOriginalName(data);
                                    }
                                    break;
                                case DIRECTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        List<String> details = StringParser.parse(data, ",");
                                        List<Person> direcotrs = details.stream().map(s -> StringParser.parseToPerson(s.trim())).collect(Collectors.toList());
                                        movie.setDirector(direcotrs.get(0));
                                    }
                                    break;

                                case ACTORS:
                                    if (movie != null && !data.isEmpty()) {
                                        List<String> details = StringParser.parse(data, ",");
                                        List<Person> actors = details.stream().map(s -> StringParser.parseToPerson(s.trim())).collect(Collectors.toList());
                                        movie.setActors(actors);
                                    }
                                    break;
                                case DURATION:
                                    if (movie != null && !data.isEmpty()) {
                                        int duration = Integer.valueOf(data);
                                        movie.setDuration(duration);
                                    }
                                    break;
                                case GENRE:
                                    if (movie != null) {
                                        List<String> details = StringParser.parse(data, ",");
                                        movie.setGenre(Genre.from(details.get(0)));
                                    }
                                    break;
                                case POSTER:
                                    if (movie != null && !data.isEmpty()) {
                                        handlePicture(movie, data);
                                    }
                                    break;
                                case START:
                                    if (movie != null && !data.isEmpty()) {
                                        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("d.M.yyyy"));
                                        movie.setStart(localDate);
                                    }
                                    break;

                            }
                        }

                }

            }

        }
  
        return movies;
    }

    private static void handlePicture(Movie movie, String url) throws IOException {
        String extension = url.substring(url.lastIndexOf("."));
        if (extension.length() > 4) {
            extension = EXT;
        }

        String pictureName = UUID.randomUUID() + extension;
        String picturePath = DIR + File.separator + pictureName;

        FileUtils.copyFromUrl(url, picturePath);
        movie.setPoster(picturePath);
    }

    private enum TagType {
        ITEM("item"),
        TITLE("title"),
        PUB_DATE("pubDate"),
        DESCRIPTION("description"),
        ORGINALNI_NAZIV("orignaziv"),
        DIRECTORS("redatelj"),
        ACTORS("glumci"),
        DURATION("trajanje"),
        GENRE("zanr"),
        POSTER("plakat"),
        START("datumprikazivanja");

        private String name;

        private TagType(String name) {
            this.name = name;
        }

        public static Optional<TagType> from(String name) {
            for (TagType value : TagType.values()) {
                if (value.name.equals(name)) {
                    return Optional.of(value);
                }
            }
            return Optional.empty();
        }

    }

}
