package com.ss19.repo.screenroom;

import com.ss19.entity.ScreenRoom;
import com.ss19.entity.Seat;
import com.ss19.repo.theater.TheaterRepo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ScreenRoomRepoImpl implements ScreenRoomRepo{
    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List<ScreenRoom> findAllActive(){
        return sessionFactory.openSession()
                .createQuery("from ScreenRoom sc where sc.status=true", ScreenRoom.class)
                .getResultList();
    }

    @Override
    public ScreenRoom findById(Long id){
        return sessionFactory.openSession()
                .get(ScreenRoom.class, id);
    }

    @Override
    public void save(ScreenRoom screenRoom){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(screenRoom);
            transaction.commit();
        }
    }

    @Override
    public void delete(ScreenRoom screenRoom) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            screenRoom.setStatus(false);
            session.merge(screenRoom);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
