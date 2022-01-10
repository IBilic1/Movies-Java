/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import hr.algebra.dal.MovieRepository;
import hr.algebra.dal.PersonRepositry;
import hr.algebra.dal.UserInterface;
import hr.algebra.model.Genre;
import hr.algebra.model.Person;
import hr.algebra.model.Movie;
import hr.algebra.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

public class SQLRepository implements MovieRepository, PersonRepositry, UserInterface {

    private static final String ID_MOVIE = "IDMovie";
    private static final String TITLE = "Title";
    private static final String PUBLISHED_DATE = "PublishedDate";
    private static final String DESCRIPTION = "Description";
    private static final String ORIGINAL_NAME = "OriginalName";
    private static final String DURATION = "Duration";
    private static final String GENRE = "Genre";
    private static final String POSTER = "Poster";
    private static final String START = "Start";
    private static final String ID_PERSON = "IDPerson";
    private static final String FIRST_NAME = "FirstName";
    private static final String LAST_NAME = "LastName";

    private static final String SELECT_MOVIE = "{ CALL selectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL selectMovies }";
    private static final String SELECT_PERSONS_MOVIES = "{ CALL selectPersonMovies (?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL updateMovie (?,?,?,?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL deleteMovie (?) }";
    private static final String CREATE_MOVIE = "{ CALL createMovie (?,?,?,?,?,?,?,?,?) }";

    private static final String SELECT_PERSON = "{ CALL selectPerson (?) }";
    private static final String SELECT_ACTORS = "{ CALL selectActors (?) }";
    private static final String SELECT_ALL_ACTORS = "{ CALL selectAllActors }";
    private static final String SELECT_DIRECTOR = "{ CALL selectDirector (?) }";
    private static final String SELECT_DIRECTORS = "{ CALL selectAllDirecotrs }";
    private static final String UPDATE_PERSON = "{ CALL updatePerson (?,?,?) }";
    private static final String DELETE_PERSON = "{ CALL deletePersonMovie (?,?,?) }";
    private static final String DELETE_ACTORS = "{ CALL deleteActorsMovie (?) }";
    private static final String CREATE_ACTORS = "{ CALL createActor (?,?,?) }";
    private static final String CREATE_DIRECTOR = "{ CALL createDirector (?,?,?) }";

    private static final String DELETE_DATA = "{ CALL deleteData }";
    private static final String ADD_USER = "{? =  CALL addUser (?,?) }";
    private static final String SELECT_USER = "{ ? = CALL selectUser(?,?,?) }";

    @Override
    public List<Movie> select() throws Exception {
        List<Movie> movies = new ArrayList<>();
        int idMovie = 0;
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                idMovie = rs.getInt(ID_MOVIE);
                movies.add(new Movie(
                        idMovie,
                        rs.getString(TITLE),
                        LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                        rs.getString(DESCRIPTION),
                        rs.getString(ORIGINAL_NAME),
                        selectDirector(idMovie).isPresent() ? selectDirector(idMovie).get() : new Person(),
                        selectActors(idMovie),
                        rs.getInt(DURATION),
                        Genre.from(rs.getString(GENRE)),
                        rs.getString(POSTER),
                        LocalDate.parse(rs.getString(START), Movie.DATE_FORMATTER_START)));
            }
        }
        return movies;
    }

    @Override
    public Optional<Person> selecActor(int idActor) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PERSON)) {
            stmt.setInt(1, idActor);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> selectUser(User user) throws Exception {
        Boolean exists = null;
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_USER)) {

            stmt.registerOutParameter(1, java.sql.Types.BIT);

            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getPassword());
            stmt.registerOutParameter(4, Types.BIT);
            stmt.execute();
            exists = stmt.getBoolean(1);
            return exists == false ? Optional.empty() : Optional.of(stmt.getBoolean(4));
        }

    }

    @Override
    public boolean addUser(User user) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(ADD_USER)) {

            stmt.setString(2, user.getUserName());
            stmt.setString(3, user.getPassword());
            stmt.registerOutParameter(1, java.sql.Types.BIT);
            stmt.execute();
            return stmt.getBoolean(1);

        }

    }

    @Override
    public void create(int idMovie, List<Person> actors) throws Exception {
        if (actors != null) {
            DataSource dataSource = DataSourceSingleton.getInstance();
            try (Connection con = dataSource.getConnection();
                    CallableStatement stmt = con.prepareCall(CREATE_ACTORS)) {

                for (Person actor : actors) {
                    stmt.setInt(1, idMovie);
                    stmt.setString(2, actor.getFirstName());
                    stmt.setString(3, actor.getPasswod());
                    stmt.executeUpdate();
                }

            }
        }
    }

    @Override
    public List<Person> selectActors(int idMovie) throws Exception {
        List<Person> personos = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS)) {
            stmt.setInt(1, idMovie);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    personos.add(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)));
                }
            }
        }
        return personos;
    }

    @Override
    public void deleteData() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_DATA)) {
            stmt.executeUpdate();
        }
    }

    @Override
    public void create(int idMovie, Person direcotr) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR)) {

            stmt.setInt(1, idMovie);
            stmt.setString(2, direcotr.getFirstName());
            stmt.setString(3, direcotr.getPasswod());
            stmt.executeUpdate();

        }

    }

    @Override
    public Optional<Person> selectDirector(int idMovie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR)) {
            stmt.setInt(1, idMovie);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)));
                }

            }
            return Optional.empty();
        }

    }

    @Override
    public List<Person> selectActors() throws Exception {
        List<Person> personos = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ALL_ACTORS)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    personos.add(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)));
                }
            }
        }
        return personos;
    }

    @Override
    public void update(int id, Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_PERSON)) {

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getPasswod());
            stmt.setInt(3, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int idMovie, int idPerson, int mode) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_PERSON)) {
            stmt.setInt(1, idMovie);
            stmt.setInt(2, idPerson);
            stmt.setInt(3, mode);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Person> selectDirectors() throws Exception {
        List<Person> personos = new ArrayList<>();
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    personos.add(new Person(
                            rs.getInt(ID_PERSON),
                            rs.getString(FIRST_NAME),
                            rs.getString(LAST_NAME)));
                }
            }
        }
        return personos;
    }

    private void deleteMovieActor(int id) throws SQLException {
        //update u tablici MovieAcotr
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_ACTORS)) {
            stmt.setInt(1, id);

            stmt.executeUpdate();

        }

    }

    @Override
    public void create(List<Movie> movies) throws Exception {
        for (Movie movie : movies) {
            create(movie);
        }
    }

    @Override
    public int create(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_MOVIE)) {
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(3, movie.getDescription());
            stmt.setString(4, movie.getOriginalName());
            stmt.setInt(5, movie.getDuration());
            stmt.setString(6, movie.getGenre().getName());
            stmt.setString(7, movie.getPoster());
            stmt.setString(8, movie.getStart().format(Movie.DATE_FORMATTER_START));
            stmt.registerOutParameter(9, Types.INTEGER);
            stmt.executeUpdate();
            if (stmt.getInt(9) != 0) {
                create(stmt.getInt(9), movie.getActors());
                create(stmt.getInt(9), movie.getDirector());
            }
            return stmt.getInt(9);

        }
    }

    @Override
    public void update(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(UPDATE_MOVIE)) {
//pde treba napravit update direktora
            stmt.setString(1, movie.getTitle());
            stmt.setString(2, movie.getPublishedDate().format(Movie.DATE_FORMATTER));
            stmt.setString(3, movie.getDescription());
            stmt.setString(4, movie.getOriginalName());
            stmt.setInt(5, movie.getDuration());
            stmt.setString(6, movie.getGenre().getName());
            stmt.setString(7, movie.getPoster());
            stmt.setString(8, movie.getStart().format(Movie.DATE_FORMATTER_START));
            stmt.setInt(9, movie.getDirector().getId());
            System.out.println(id);
            stmt.setInt(10, id);

            stmt.executeUpdate();
            deleteMovieActor(id); // BRISANJE SVIH GLUMACA
            create(id, movie.getActors()); // DODAVANJA GLUMACA NANOCO
        }
    }

    @Override
    public void delete(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(DELETE_MOVIE)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Movie> select(Person person, int mode) throws Exception {
        List<Movie> movies = new ArrayList<>();
        int idMovie = 0;
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_PERSONS_MOVIES);) {
            stmt.setInt(1, person.getId());
            stmt.setInt(2, mode);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    idMovie = rs.getInt(ID_MOVIE);
                    movies.add(new Movie(
                            idMovie,
                            rs.getString(TITLE),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_NAME),
                            selectDirector(idMovie).isPresent() ? selectDirector(idMovie).get() : new Person(),
                            selectActors(idMovie),
                            rs.getInt(DURATION),
                            Genre.from(rs.getString(GENRE)),
                            rs.getString(POSTER),
                            LocalDate.parse(rs.getString(START), Movie.DATE_FORMATTER_START)));
                }
            }
        }
        return movies;
    }

    @Override
    public Optional<Movie> select(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_MOVIE)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Movie(
                            id,
                            rs.getString(TITLE),
                            LocalDateTime.parse(rs.getString(PUBLISHED_DATE), Movie.DATE_FORMATTER),
                            rs.getString(DESCRIPTION),
                            rs.getString(ORIGINAL_NAME),
                            selectDirector(id).isPresent() ? selectDirector(id).get() : new Person(),
                            selectActors(id),
                            rs.getInt(DURATION),
                            Genre.from(rs.getString(GENRE)),
                            rs.getString(POSTER),
                            LocalDate.parse(rs.getString(START), Movie.DATE_FORMATTER_START)));
                }
            }
        }
        return Optional.empty();
    }

}
