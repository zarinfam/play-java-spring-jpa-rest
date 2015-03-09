package daos;

import java.io.Serializable;
import java.util.List;

/**
 * Created by saeed on 1/March/15 AD.
 */
public interface GenericDao<T, ID extends Serializable>{
    void persist(T entity);
    List<T> getAll(Class<T> entityClass);
    Long count(Class<T> entityClass);
    T find(ID id, Class<T> entityClass);
    T merge(T entity);
    void remove(ID id, Class<T> entityClass);
}
