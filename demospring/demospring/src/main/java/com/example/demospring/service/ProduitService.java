package com.example.demospring.service;

import com.example.demospring.entity.Produit;
import com.example.demospring.interfaces.IDAO;
import com.example.demospring.tools.ServiceHibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService implements IDAO<Produit> {

   // @Autowired
    private ServiceHibernate serviceHibernate;

    private Session session;

    public ProduitService(ServiceHibernate serviceHibernate){
        this.serviceHibernate = serviceHibernate;
        session = this.serviceHibernate.getSession();
    }

    @Override
    public boolean create(Produit o) {
        return false;
    }

    @Override
    public boolean update(Produit o) {
        return false;
    }

    @Override
    public boolean delete(Produit o) {
        return false;
    }

    @Override
    public Produit findById(int id) {
        return null;
    }

    @Override
    public List<Produit> findAll() {
        return null;
    }
}
