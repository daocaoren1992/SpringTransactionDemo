package com.transaction.demo5.service;

import com.transaction.demo5.mapper.User2Dao;
import com.transaction.demo5.model.User1;
import com.transaction.demo5.model.User2;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class User2ServiceImpl implements User2Service {
    private User2Dao user2Dao;

    public void setUser2Dao(User2Dao user2Dao) {
        this.user2Dao = user2Dao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertPropagationRequired(User2 user2) {
        user2Dao.insert(user2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertPropagationRequiredException(User2 user2) {
        user2Dao.insert(user2);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertPropagationRequiresNew(User2 user2) {
        user2Dao.insert(user2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertPropagationRequiresNewException(User2 user2) {
        user2Dao.insert(user2);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void insertPropagationNested(User2 user2) {
        user2Dao.insert(user2);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void insertPropagationNestedException(User2 user2) {
        user2Dao.insert(user2);
        throw new RuntimeException();
    }
}
