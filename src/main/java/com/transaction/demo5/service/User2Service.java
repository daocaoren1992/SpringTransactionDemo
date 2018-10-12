package com.transaction.demo5.service;

import com.transaction.demo5.model.User2;

public interface User2Service {
    void insertPropagationRequired(User2 user2);

    void insertPropagationRequiredException(User2 user2);

    void insertPropagationRequiresNew(User2 user2);

    void insertPropagationRequiresNewException(User2 user2);

    void insertPropagationNested(User2 user2);

    void insertPropagationNestedException(User2 user2);
}
