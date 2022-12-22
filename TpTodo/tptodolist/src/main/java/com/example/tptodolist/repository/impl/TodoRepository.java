package com.example.tptodolist.repository.impl;

import com.example.tptodolist.entity.Todo;
import com.example.tptodolist.repository.BaseRespository;
import com.example.tptodolist.tool.ServiceHibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// Annotation pour déclarer un spring beans en tant que repository
@Repository
public class TodoRepository implements BaseRespository<Todo> {

    private Session _session;

    private ServiceHibernate _serviceHibernate;

    public TodoRepository(ServiceHibernate serviceHibernate){
        _serviceHibernate = serviceHibernate;
        _session = _serviceHibernate.getSession();
    }

    @Override
    public boolean create(Todo element) {
        _session.beginTransaction();
        _session.persist(element);
        _session.getTransaction().commit();
        return element.getId() > 0;
    }

    @Override
    public boolean update(Todo element) {
        _session.beginTransaction();
        _session.update(element);
        _session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Todo element) {
        _session.beginTransaction();
        _session.delete(element);
        _session.getTransaction().commit();
        return true;
    }

    @Override
    public Todo findById(int id) {
        return (Todo)_session.get(Todo.class,id);
    }

    public List<Todo> findByStatus(boolean status) {
        Query<Todo> todoQuery= _session.createQuery("from Todo where status = :status", Todo.class);
        todoQuery.setParameter("status", status);
        return todoQuery.list();
    }

}
