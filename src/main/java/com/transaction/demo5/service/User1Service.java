package com.transaction.demo5.service;

import com.transaction.demo5.model.User1;

public interface User1Service {
    void insertPropagationRequired(User1 user1);

    //void insertPropagationRequiredException(User1 user1);

    void insertPropagationRequiresNew(User1 user1);

    void insertPropagationNested(User1 user1);
}
