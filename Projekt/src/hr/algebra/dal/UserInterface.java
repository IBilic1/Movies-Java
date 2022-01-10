/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.model.User;
import java.util.Optional;

/**
 *
 * @author HT-ICT
 */
public interface UserInterface{

    Optional<Boolean> selectUser(User user) throws Exception;

    boolean addUser(User user) throws Exception;

    void deleteData() throws Exception;
}
