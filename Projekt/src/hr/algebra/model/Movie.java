/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author HT-ICT
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Movie {

    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    public static DateTimeFormatter DATE_FORMATTER_START = DateTimeFormatter.ISO_DATE;

    public Movie(String title, LocalDateTime publishedDate, String description, String originalName, int duration, Genre genre, String poster, LocalDate start) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalName = originalName;
        this.duration = duration;
        this.genre = genre;
        this.poster = poster;
        this.start = start;
    }

    private int id;
    private String title;
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement(name = "publisheddate")
    private LocalDateTime publishedDate;
    private String description;
    private String originalName;
    private Person director;
    @XmlElementWrapper
    @XmlElement(name = "actor")
    private List<Person> actors;
    private int duration;
    private Genre genre;
    private String poster;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "publisheddate")
    private LocalDate start;

    public Movie() {
    }

    public Movie(int id, String title, LocalDateTime publishedDate, String description, String originalName, Person director, List<Person> actors, int duration, Genre genre, String poster, LocalDate start) {
        this.id = id;
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalName = originalName;
        this.director = director;
        this.actors = actors;
        this.duration = duration;
        this.genre = genre;
        this.poster = poster;
        this.start = start;
    }

    public Movie(String title, LocalDateTime publishedDate, String description, String originalName, Person director, List<Person> actors, int duration, Genre genre, String poster, LocalDate start) {
        this.title = title;
        this.publishedDate = publishedDate;
        this.description = description;
        this.originalName = originalName;
        this.director = director;
        this.actors = actors;
        this.duration = duration;
        this.genre = genre;
        this.poster = poster;
        this.start = start;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public void setDirector(Person director) {
        this.director = director;
    }

    public void setActors(List<Person> actors) {
        this.actors = actors;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getOriginalName() {
        return originalName;
    }

    public Person getDirector() {
        return director;
    }

    public List<Person> getActors() {
        return actors;
    }

    public int getDuration() {
        return duration;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getPoster() {
        return poster;
    }

    public LocalDate getStart() {
        return start;
    }

    @Override
    public String toString() {
        return title;
    }

}
