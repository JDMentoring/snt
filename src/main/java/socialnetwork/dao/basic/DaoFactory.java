package socialnetwork.dao.basic;

public interface DaoFactory {
    GenDao getDao(Class beanClass) throws DaoException;
}
