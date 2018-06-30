package dao;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

public abstract class AbstractDao<P extends Identifiable<PK>, PK extends Integer> implements GenDao<P, PK> {

    private static Logger log = Logger.getLogger(AbstractDao.class);

    public abstract String getSelectQuery();

    public abstract String getInsertQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract String getWhereQuery();

    public abstract Connection getConnection();

    public abstract List<P> parseResultSet(ResultSet resultSet) throws DaoException;

    public abstract void setInsertPrepareStatement(PreparedStatement prepareStatement, P someObject) throws DaoException;

    public abstract void setUpdatePrepareStatement(PreparedStatement prepareStatement, P someObject) throws DaoException;

    @Override
    public P create(P object) throws DaoException {
        P createdObject = null;
        Connection connection = getConnection();
        String insertQuery = getInsertQuery();
        String selectQuery = getWhereQuery()+"last_insert_id()";
        log.trace("\n\n");

        try (PreparedStatement insertPreparedStatement = connection.prepareStatement(insertQuery)) {
            log.trace("Creating new user record...");
            setInsertPrepareStatement(insertPreparedStatement, object);
            int rows = insertPreparedStatement.executeUpdate();
            if (rows != 1) throw new DaoException("Created more than one record " + rows);
            log.trace("New user record was created in DB.");
        } catch (SQLException e) {
            log.error(e);
        }

        try (PreparedStatement selectPreparedStatement = connection.prepareStatement(selectQuery)) {
            log.trace("Getting last created user record...");
            ResultSet resultSet = selectPreparedStatement.executeQuery();
            List<P> objectsList = parseResultSet(resultSet);
            if (objectsList == null || objectsList.size() != 1) {
                log.error("Can not find last created record");
                throw new DaoException("Can not find last created record");
            }
            createdObject = objectsList.iterator().next();
        } catch (SQLException e) {
            log.error(e);
        }
        return createdObject;
    }

    @Override
    public P read(PK key) throws DaoException {
        Connection connection = getConnection();
        List<P> list;
        String selectQuery = getWhereQuery()+"?";
        log.trace("\n\n");
        log.trace("Getting user record from db...");

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DaoException(e);
        }
        if (list == null || list.size() == 0) {
            log.error("Record with PK = " + key + " not found.");
            throw new DaoException("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            log.error("Received more than one record.");
            throw new DaoException("Received more than one record.");
        }
        log.trace("User with id " + key + " was found in DB.");
        return list.iterator().next();
    }

    @Override
    public void update(P object) throws DaoException {
        Connection connection = getConnection();
        log.trace("\n\n");
        log.trace("Connection was established.");
        String updateQuery = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            log.trace("Updating user with id " + object.getId());
            setUpdatePrepareStatement(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                log.error("On updating modified more then 1 record: " + count);
                throw new DaoException("On update modify more then 1 record: " + count);
            }
            log.trace("User record with id " + object.getId() + " was updated");
        } catch (Exception e) {
            log.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(P object) throws DaoException {
        Connection connection = getConnection();
        String deleteQuery = getDeleteQuery();
        log.trace("\n\n");
        log.trace("Start deleting user record from db...");
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setObject(1, object.getId());
            int count = statement.executeUpdate();
            if (count != 1) {
                log.error("On delete modify more then 1 record: " + count);
                throw new DaoException("On delete modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            log.error(e);
            throw new DaoException(e);
        }
        log.trace("User record with id = "+object.getId()+" was deleted from db.");
    }

    @Override
    public List<P> getAll() throws DaoException {
        Connection connection = getConnection();
        List<P> list;
        String sql = getSelectQuery();
        log.trace("\n\n");
        log.trace("Start getting all user records from db...");
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            log.error(e);
            throw new DaoException(e);
        }
        log.trace("All user from db was got.");
        return list;
    }
}
