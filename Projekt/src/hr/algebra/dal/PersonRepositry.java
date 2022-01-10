/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.Person;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author HT-ICT
 */
public interface PersonRepositry {

    List<Person> selectActors(int idMovie) throws Exception;

    void create(int idMovie, List<Person> actors) throws Exception;

    void create(int idMovie, Person direcotr) throws Exception;

    Optional<Person> selectDirector(int idMovie) throws Exception;

    List<Person> selectDirectors() throws Exception;

    void update(int id, Person person) throws Exception;

    List<Person> selectActors() throws Exception;

    Optional<Person> selecActor(int idActor) throws Exception;

    void delete(int idMovie, int idPerson,int mode) throws Exception;
}
