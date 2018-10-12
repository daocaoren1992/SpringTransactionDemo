package com.transaction.demo5.service;

import com.transaction.demo5.mapper.User1Dao;
import com.transaction.demo5.model.User1;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class User1ServiceImpl implements User1Service {
    private User1Dao user1Dao;

    public void setUser1Dao(User1Dao user1Dao) {
        this.user1Dao = user1Dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertPropagationRequired(User1 user1) {
        user1Dao.insert(user1);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertPropagationRequiresNew(User1 user1) {
        user1Dao.insert(user1);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void insertPropagationNested(User1 user1) {
        user1Dao.insert(user1);
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void insertPropagationRequiredException(User1 user1) {
//        user1Dao.insert(user1);
//        throw new RuntimeException();
//    }
}
