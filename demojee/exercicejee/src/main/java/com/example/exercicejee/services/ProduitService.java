package com.example.exercicejee.services;

import com.example.exercicejee.entities.Produit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.List;

public class ProduitService {

    private StandardServiceRegistry registre;

    private SessionFactory sessionFactory;

    private Session session;


    public ProduitService() {
        registre = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(registre).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public boolean create(Produit o){
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }
    public List<Produit> findAll(){
        session.beginTransaction();
        Query<Produit> produitQuery = session.createQuery("from Produit ");
        session.getTransaction().commit();
        return produitQuery.list();
    }

    public Produit findById(int id){
        Produit produit = null;
        session.beginTransaction();
        produit = (Produit) session.get(Produit.class, id);
        session.getTransaction().commit();
        return produit;
    }
}
