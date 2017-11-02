package com.qrolling.rfid.services;

import com.qrolling.rfid.entities.Visitor;

import java.util.List;

/**
 * @author Quy Nguyen (nguyenledinhquy@gmail.com) on 10/29/17
 */
public interface VisitorService {
    void add(Visitor visitor);
    List<Visitor> listVisitors();
}
