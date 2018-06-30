package dao.mysql;

import dao.DaoException;
import dao.DaoFactory;
import dao.GenDao;
import dao.beans.User;

import java.util.List;

public class MySQLDAORUnner {
    public static void main(String[] args) {
        try {
            DaoFactory factory = new MySQLDaoFactory();
            GenDao userDao = factory.getDao(User.class);

            User user = new User();
            user.setLogin("nazarmh19");
            user.setPassword("1402");
            user.setEmail("adsfd@gmail.com");
            user = (User) userDao.create(user);

            user = (User) userDao.read(user.getId());
            System.out.println(user);

            user.setEmail("newmail@gmail.com");

            userDao.update(user);

            user = (User) userDao.read(user.getId());
            System.out.println(user);

            //userDao.delete(user);

            List<User> userList = userDao.getAll();
            for(User u: userList){
                System.out.println(u.toString());
            }



        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
