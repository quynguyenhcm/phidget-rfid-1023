package com.qrolling.rfid.services;

import com.qrolling.rfid.dao.VisitorDao;
import com.qrolling.rfid.entities.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
@Service
public class VisitorServiceImpl implements  VisitorService{
    @Autowired
    private VisitorDao visitorDao;

    @Transactional
    @Override
    public void add(Visitor visitor){
        visitorDao.add(visitor);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Visitor> listVisitors(){
        return visitorDao.listVisitors();
    }
}
