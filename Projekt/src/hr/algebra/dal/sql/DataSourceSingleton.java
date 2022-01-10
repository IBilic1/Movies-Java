/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dal.sql;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author HT-ICT
 */
public final class DataSourceSingleton {

    private static String SERVER_NAME;
    private static String DATABASE_NAME;
    private static String USER;
    private static String PASSWORD;

    private static DataSource createInstance() {
        
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    private DataSourceSingleton() {
        try {
            loadConfig();
        } catch (IOException ex) {
            Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static DataSource instance;

    static DataSource getInstance() {
        if (instance == null) {
                try {
                    Constructor<DataSourceSingleton> constructor = DataSourceSingleton.class.getDeclaredConstructor();
                    constructor.newInstance();
                } catch (Exception ex) {
                    Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            instance = createInstance();
        }
        return instance;
    }

    private static void loadConfig() throws IOException {
        List<String> details = Files.readAllLines(Paths.get("config.txt"));
        SERVER_NAME = details.get(0);
        DATABASE_NAME = details.get(1);
        USER = details.get(2);
        PASSWORD = details.get(3);
    }

}
