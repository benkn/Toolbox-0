package ben.kn.toolbox.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import ben.kn.toolbox.to.BasePersistedObject;

@Transactional
public abstract class BaseHibernateDAO<T extends BasePersistedObject> {

    protected Logger log = Logger.getLogger(this.getClass());

    private SessionFactory sessionFactory;
    private Class<T> tClass;

    public BaseHibernateDAO(Class<T> tClass, SessionFactory sessionFactory) {
        this.tClass = tClass;
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() throws HibernateException {
        return sessionFactory.getCurrentSession();
    }

    public Integer create(T object) throws HibernateException {
        return (Integer) getSession().save(object);
    }

    public void delete(T object) throws HibernateException {
        getSession().delete(object);
    }

    public void update(T object) throws HibernateException {
        getSession().update(object);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public T selectById(Integer id) throws HibernateException {
        return (T) getSession().get(tClass, id);
    }

}