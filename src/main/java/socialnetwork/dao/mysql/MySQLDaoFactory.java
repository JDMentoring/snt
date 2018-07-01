package socialnetwork.dao.mysql;

import socialnetwork.beans.User;
import socialnetwork.dao.basic.DaoCreator;
import socialnetwork.dao.basic.DaoException;
import socialnetwork.dao.basic.DaoFactory;
import socialnetwork.dao.basic.GenDao;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MySQLDaoFactory implements DaoFactory {
    private static Logger log = Logger.getLogger(MySQLDaoFactory.class);

    private static String user;
    private static String password;
    private static String url;
    private static String driver;
    private Map<Class, DaoCreator> daosHolder;

    public MySQLDaoFactory() {
        daoGroupSetUp();
    }

    public static Connection getConnection() throws DaoException {
        Connection connection = null;
        loadProperties();
        log.trace("Starting get connection to DB...");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            log.error("Problem with connection to DB");
            throw new DaoException("Problem with connection to DB", e);
        } catch (ClassNotFoundException e) {
            log.error("DRIVER for DB was not found.");
        }

        log.trace("Connection was established.");
        return connection;
    }

    private static void loadProperties() {
        log.trace("Starting loading DB properties...");
        Properties properties = new Properties();
        try {
            properties.load(MySQLDaoFactory.class.getResourceAsStream("/db.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
            log.trace("DB properties was loaded.");
        } catch (IOException e) {
            log.error("Can't load properties for DB from file");
        }
    }


    private void daoGroupSetUp() {
        daosHolder = new HashMap<>();
        daosHolder.put(User.class, new DaoCreator() {
            @Override
            public GenDao create() {
                return new MysqlUserDao();
            }
        });
    }

    @Override
    public GenDao getDao(Class beanClass) throws DaoException {
        DaoCreator creator = daosHolder.get(beanClass);
        if (creator == null) {
            throw new DaoException("Dao implementation for " + beanClass + " not found.");
        }
        return creator.create();
    }
}
