package com.ss20.repository;

import com.ss20.entity.Seed;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SeedRepositoryImpl implements SeedRepository{
    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean existsBySeedNameIgnoreCase(String seedName){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("select count(s.id) from seed s where lower(s.seedName) = :seedName", Long.class)
                    .setParameter("seedName", seedName.toLowerCase())
                    .getSingleResult() > 0;
        }catch(Exception ex){
            return false;
        }
    }

    @Override
    public List<Seed> findAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from seed", Seed.class).getResultList();
        }catch(Exception ex){
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void save(Seed seed) {
        getCurrentSession().save(seed);
    }

    @Override
    @Transactional
    public void update(Seed seed){
        getCurrentSession().update(seed);
    }

    @Override
    @Transactional
    public void delete(Seed seed){
       getCurrentSession().delete(seed);
    }

    @Override
    public List<Seed> findBySeedName(String seedName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from seed where lower(seedName) like :seedName", Seed.class)
                    .setParameter("seedName", "%" + seedName.toLowerCase() + "%")
                    .getResultList();
        }
    }
}
