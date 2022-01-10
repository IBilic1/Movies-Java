/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal;

import hr.algebra.dal.sql.SQLRepository;

/**
 *
 * @author HT-ICT
 *
 */
public class RepositoryFactory {

    private RepositoryFactory() {
    }

    public static <T> T getRepository() throws Exception {
        return (T) new SQLRepository();
    }
}
