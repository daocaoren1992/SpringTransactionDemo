package com.transaction.demo5.service;

import com.transaction.demo5.model.User1;
import com.transaction.demo5.model.User2;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 当前类中的方法相对于 user1Service 和 user2Service 中的方法来说，都是它们 所包含方法的外围方法 ，
 * 因为当前方法中调用了两个或多个 user1Service 和 user2Service 中的方法
 */
public class PropagationService {
    private User1Service user1Service;

    private User2Service user2Service;

    public void setUser1Service(User1Service user1Service) {
        this.user1Service = user1Service;
    }

    public void setUser2Service(User2Service user2Service) {
        this.user2Service = user2Service;
    }

    /**
     * 内部方法开启 propagation_required 事务，外部方法 没有事务，外部方法 抛出异常
     * @param user1
     * @param user2
     */
    public void insertNoTransactionRequired(User1 user1, User2 user2) {
        user1Service.insertPropagationRequired(user1);
        user2Service.insertPropagationRequired(user2);
        throw new RuntimeException();
    }

    /**
     * 内部方法开启 propagation_required 事务， 外部方法 没有事务，内部方法 抛出异常
     * @param user1
     * @param user2
     */
    public void insertNoTransactionRequiredException(User1 user1, User2 user2) {
        user1Service.insertPropagationRequired(user1);
        // 此时就算 在此捕获 内部方法抛出的事务，执行的结果还是一样的
//        try {
//            user2Service.insertPropagationRequiredException(user2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        user2Service.insertPropagationRequiredException(user2);
    }

    /**
     * 内部方法 propagation_requires_new 开启事务，外部方法 没有事务，外部方法 抛出异常
     * @param user1
     * @param user2
     */
    public void insertNoTransactionRequiresNew(User1 user1, User2 user2) {
        user1Service.insertPropagationRequiresNew(user1);
        user2Service.insertPropagationRequiresNew(user2);
        throw new RuntimeException();
    }

    /**
     * 内部方法 propagation_requires_new 开启事务，外部方法 没有事务，内部方法 抛出异常
     * @param user1
     * @param user2
     */
    public void insertNoTransactionRequiresNewException(User1 user1, User2 user2) {
        user1Service.insertPropagationRequiresNew(user1);
        user2Service.insertPropagationRequiresNewException(user2);
    }

    /**
     * 内部方法开启了 propagation_nested 事务，外围方法 没有 开启事务，外部方法 抛出异常
     *
     * @param user1
     * @param user2
     */
    public void insertNoTransactionNested(User1 user1, User2 user2) {
        user1Service.insertPropagationNested(user1);
        user2Service.insertPropagationNested(user2);
        throw new RuntimeException();
    }

    /**
     * 内部方法开启了 propagation_nested 事务，外围方法 没有 开启事务，内部方法 抛出异常
     *
     * @param user1
     * @param user2
     */
    public void insertNoTransactionNestedException(User1 user1, User2 user2) {
        user1Service.insertPropagationNested(user1);
        user2Service.insertPropagationNestedException(user2);
    }

    /*********************** 分界线以上，外部方法 未开启 事务，以下 外部方法 开启事务 ******************/

    /**
     * 内部方法开启 propagation_required 事务，外部方法开启 propagation_required 事务 ，外部方法 抛出异常
     * @param user1
     * @param user2
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTransactionRequired(User1 user1, User2 user2) {
        user1Service.insertPropagationRequired(user1);
        user2Service.insertPropagationRequired(user2);
        throw new RuntimeException();
    }

    /**
     * 内部方法开启 propagation_required 事务，外部方法开启 propagation_required 事务，内部方法 抛出异常
     * @param user1
     * @param user2
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTransactionRequiredException(User1 user1, User2 user2) {
        user1Service.insertPropagationRequired(user1);
        // 即使在这里捕获了内部方法抛出的异常，当前方法插入的数据还是会回滚
//        try {
//            user2Service.insertPropagationRequiredException(user2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        user2Service.insertPropagationRequiredException(user2);
    }

    /**
     * 内部方法开启 propagation_required 和  propagation_requires_new 事务，外部方法开启 propagation_required 事务，外部方法 抛出异常
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTransactionRequiresNewOutRequired(User1 user1, User2 user2, User2 user3) {
        user1Service.insertPropagationRequired(user1);
        user2Service.insertPropagationRequiresNew(user2);
        user2Service.insertPropagationRequiresNew(user3);
        throw new RuntimeException();
    }

    /**
     * 内部方法开启 propagation_required 和  propagation_requires_new 事务，外部方法 开启 propagation_required 事务， 内部方法 抛出异常
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTransactionRequiresNewOutRequiredException(User1 user1, User2 user2, User2 user3) {
        user1Service.insertPropagationRequired(user1);
        /**
         * 下面的这两个方法处于同一个 propagation_requires_new 事务当中
         */
        user2Service.insertPropagationRequiresNew(user2);
        user2Service.insertPropagationRequiresNewException(user3);
    }

    /**
     * 内部方法开启 propagation_required 和  propagation_requires_new 事务，外部方法 开启 propagation_required 事务， 内部方法 抛出异常
     * 在外部方法捕获内部方法抛出的异常
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTransactionRequiresNewOutRequiredExceptionWithTry(User1 user1, User2 user2, User2 user3) {
        user1Service.insertPropagationRequired(user1);
        /**
         * 下面的这两个方法处于同一个 propagation_requires_new 事务当中
         */
        user2Service.insertPropagationRequiresNew(user2);
        try {
            user2Service.insertPropagationRequiresNewException(user3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 内部方法开启了 propagation_nested 事务，外围方法 开启 propagation_required 事务，外部方法 抛出异常
     *
     * @param user1
     * @param user2
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTransactionNested(User1 user1, User2 user2) {
        user1Service.insertPropagationNested(user1);
        user2Service.insertPropagationNested(user2);
        throw new RuntimeException();
    }

    /**
     * 内部方法开启了 propagation_nested 事务，外围方法 开启 propagation_required 事务，内部方法 抛出异常
     *
     * @param user1
     * @param user2
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTransactionNestedException(User1 user1, User2 user2) {
        user1Service.insertPropagationNested(user1);
        user2Service.insertPropagationNestedException(user2);
    }

    /**
     * 内部方法开启了 propagation_nested 事务，外围方法 开启 propagation_required 事务，内部方法 抛出异常
     * 在外部方法中捕获内部方法的异常
     * @param user1
     * @param user2
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTransactionNestedExceptionWithTry(User1 user1, User2 user2) {
        user1Service.insertPropagationNested(user1);
        try {
            user2Service.insertPropagationNestedException(user2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
