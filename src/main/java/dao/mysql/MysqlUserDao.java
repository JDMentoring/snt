package dao.mysql;

import dao.AbstractDao;
import dao.DaoException;
import dao.beans.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MysqlUserDao extends AbstractDao<User, Integer> {

    private static Logger log = Logger.getLogger(MysqlUserDao.class);

    private class DaoUser extends User {
        @Override
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM `sn_user`";
    }

    @Override
    public String getWhereQuery() {
        return "SELECT * FROM `sn_user` WHERE `user_id`=";
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO `sn_user`(`login`, `password`, `email`) VALUES (?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE `sn_user` SET `password`=?, `email`=? WHERE `user_id`=?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM `sn_user` WHERE `user_id` = ?;";
    }

    @Override
    public Connection getConnection() {
        try {
            return MySQLDaoFactory.getConnection();
        } catch (DaoException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> parseResultSet(ResultSet resultSet) throws DaoException {
        LinkedList<User> users = new LinkedList<>();
        try {
            while (resultSet.next()) {
                DaoUser user = new DaoUser();
                user.setId(resultSet.getInt(1));
                user.setLogin(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setCreated(resultSet.getDate(5).toLocalDate());
                user.setUpdated(resultSet.getDate(6).toLocalDate());
                users.add(user);
            }
        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public void setInsertPrepareStatement(PreparedStatement prepareStatement, User someObject) throws DaoException {
        try {
            prepareStatement.setString(1, someObject.getLogin());
            prepareStatement.setString(2, someObject.getPassword());
            prepareStatement.setString(3, someObject.getEmail());
        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        }

    }

    @Override
    public void setUpdatePrepareStatement(PreparedStatement prepareStatement, User someObject) throws DaoException {
        try {
            prepareStatement.setString(1, someObject.getPassword());
            prepareStatement.setString(2, someObject.getEmail());
            prepareStatement.setInt(3, someObject.getId());
        } catch (SQLException e) {
            log.error(e);
            throw new DaoException(e);
        }
    }


}
