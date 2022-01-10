/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author HT-ICT
 */
public interface MovieRepository {
    List<Movie> select() throws Exception;

    void create(List<Movie> movies) throws Exception;

    void update(int id, Movie movie) throws Exception;

    void delete(int id) throws Exception;

    List<Movie> select(Person person,int mode) throws Exception;

    int create(Movie movie) throws Exception;

    Optional<Movie> select(int id) throws Exception;
}
