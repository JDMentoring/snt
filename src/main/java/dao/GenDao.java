package dao;

import java.io.Serializable;
import java.util.List;

public interface GenDao<P extends Identifiable<PK>,PK extends Integer> {
    P create(P object) throws DaoException;
    P read(PK key) throws DaoException;
    void update(P object) throws DaoException;
    void delete(P object) throws DaoException;
    List<P> getAll() throws  DaoException;
}
