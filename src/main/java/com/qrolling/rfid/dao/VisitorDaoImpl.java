package com.qrolling.rfid.dao;

import com.qrolling.rfid.entities.Visitor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Repository
public class VisitorDaoImpl implements VisitorDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Visitor visitor){
        em.persist(visitor);
    }

    @Override
    public List<Visitor> listVisitors(){
        CriteriaQuery<Visitor> criteriaQuery = em.getCriteriaBuilder().createQuery(Visitor.class);
        @SuppressWarnings("unused")
        Root<Visitor> root = criteriaQuery.from(Visitor.class);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
