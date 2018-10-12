package com.transaction.demo5.notransaction;

import com.transaction.demo5.model.User1;
import com.transaction.demo5.model.User2;
import com.transaction.demo5.service.PropagationService;
import com.transaction.demo5.service.User1Service;
import com.transaction.demo5.service.User2Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext5.xml")
public class OuterMethodNoTransaction {

    @Autowired
    private PropagationService propagationService;

    /**
     * 内部方法开启 propagation_required 事务，外部方法 未开启 事务，外部方法抛出异常
     *
     * 执行结果：张三、李四均插入
     * 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     */
    @Test
    public void testNoTransactionPropagationRequired() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertNoTransactionRequired(user1, user2);
    }

    /**
     * 内部方法开启 propagation_required 事务 ，外部方法 未开启 事务，内部方法抛出异常
     *
     * 执行结果：张三插入、李四未插入
     *
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，插入“张三”方法不受影响。
     */
    @Test
    public void testNoTransactionPropagationRequiredException() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertNoTransactionRequiredException(user1, user2);
    }

    /**
     * PROPAGATION_REQUIRES_NEW	新建事务，如果当前存在事务，把当前事务挂起
     *
     * 内部方法开启 propagation_requires_new 事务 ，外部方法 未开启 事务，外部方法抛出异常
     *
     * 执行结果：张三、李四均插入
     *
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,外围方法抛出异常回滚不会影响内部方法。
     *
     */
    @Test
    public void testNoTransactionPropagationRequiresNew() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertNoTransactionRequiresNew(user1, user2);
    }

    /**
     * PROPAGATION_REQUIRES_NEW	新建事务，如果当前存在事务，把当前事务挂起
     *
     * 内部方法开启 propagation_requires_new 事务 ，外部方法 未开启 事务，内部方法抛出异常
     *
     * 执行结果：张三插入，李四未插入
     *
     * 外围方法没有事务，插入“张三”方法和插入“李四”方法分别开启自己的事务，插入“李四”方法抛出异常回滚，其他事务不受影响。
     *
     */
    @Test
    public void testNoTransactionPropagationRequiresNewException() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertNoTransactionRequiresNewException(user1, user2);
    }

    /**
     * PROPAGATION_NESTED
     *
     * 内部方法开启 PROPAGATION_NESTED 事务 ，外部方法 未开启 事务，外部方法 抛出异常
     *
     * 执行结果：张三、李四均插入
     *
     * 外围方法未开启事务，插入“张三”、“李四”方法在自己的事务中独立运行，外围方法异常不影响内部插入“张三”、“李四”方法独立的事务。
     *
     */
    @Test
    public void testNoTransactionPropagationNested() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertNoTransactionNested(user1, user2);
    }

    /**
     * PROPAGATION_NESTED
     *
     * 内部方法开启 PROPAGATION_NESTED 事务 ，外部方法 未开启 事务，内部方法 抛出异常
     *
     * 执行结果：张三插入，李四未插入
     *
     * 外围方法没有事务，插入“张三”、“李四”方法都在自己的事务中独立运行,所以插入“李四”方法抛出异常只会回滚插入“李四”方法，
     * 插入“张三”方法不受影响。
     *
     */
    @Test
    public void testNoTransactionPropagationNestedException() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertNoTransactionNestedException(user1, user2);
    }


}
