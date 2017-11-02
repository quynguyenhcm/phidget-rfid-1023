package com.qrolling.rfid.dao;

import com.qrolling.rfid.entities.Visitor;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
public interface VisitorDao {
    void add(Visitor visitor);
    List<Visitor> listVisitors();
}
