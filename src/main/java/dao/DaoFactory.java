package dao;

public interface DaoFactory {
    GenDao getDao(Class beanClass) throws DaoException;
}
