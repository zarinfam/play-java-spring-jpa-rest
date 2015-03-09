package daos;

import play.Logger;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

/**
 * Created by saeed on 1/March/15 AD.
 */
public class GeneralDaoImpl<T, ID extends Serializable> implements GeneralDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager ;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<T> getAll(Class<T> entityClass) {
        return entityManager.createQuery("from " + entityClass.getName()).getResultList();
    }

    @Override
    public Long count(Class<T> entityClass) {
        return (Long) entityManager.createQuery("select count(*) from " 
                + entityClass.getName()).getSingleResult();
    }

    @Override
    public T find(ID id, Class<T> entityClass) {
        return  (T) entityManager.find(entityClass, id);
    }

    @Override
    public T merge(T entity){
        return entityManager.merge(entity);
    }

    @Override
    public void remove(ID id, Class<T> entityClass){
        entityManager.remove(find(id, entityClass));
    }

}