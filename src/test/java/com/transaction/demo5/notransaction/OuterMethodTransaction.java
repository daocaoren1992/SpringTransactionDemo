package com.transaction.demo5.notransaction;

import com.transaction.demo5.model.User1;
import com.transaction.demo5.model.User2;
import com.transaction.demo5.service.PropagationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext5.xml")
/**
 * 外围方法开启事务，这个是使用率比较高的场景
 */
public class OuterMethodTransaction {

    @Autowired
    private PropagationService propagationService;

    /**
     * PROPAGATION_REQUIRED 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
     * 内部方法开启 propagation_required 事务，外部方法开启 propagation_required 事务，外部方法抛出异常
     *
     * 执行结果：张三、李四均未插入
     * 外部方法开启 propagation_required 事务，内部方法也开启 propagation_required 事务，内部方法加入到外部方法的事务当中，外部方法
     * 回滚，内部方法也要回滚
     */
    @Test
    public void testTransactionPropagationRequired() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertTransactionRequired(user1, user2);
    }

    /**
     * 内部方法开启 propagation_required 事务 ，外部方法开启 propagation_required 事务，内部方法抛出异常
     *
     * 执行结果：张三、李四均未插入
     * 外围方法开启事务，内部方法加入外围方法事务，内部方法抛出异常回滚，外围方法感知异常致使整体事务回滚。
     * 即使这个异常在内部方法中被捕获，整体事务还是一样会回滚
     */
    @Test
    public void testTransactionPropagationRequiredException() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertTransactionRequiredException(user1, user2);
    }

    /**
     * PROPAGATION_REQUIRES_NEW	新建事务，如果当前存在事务，把当前事务挂起
     * 内部方法开启 propagation_requires_new 和 propagation_required 事务，外部方法开启 propagation_required 事务，外部方法抛出异常
     *
     * 执行结果：张三未插入，李四、王五插入，
     * 因为外围方法抛出了异常，且外围方法的事务是 propagation_required，
     * 插入“李四”方法、插入“王五”方法分别在独立的新建事务中，外围方法抛出异常只回滚和外围方法同一事务的方法，故插入“张三”的方法回滚。
     *
     */
    @Test
    public void testTransactionPropagationRequiresNewOutRequired() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        User2 user3 = new User2();
        user3.setName("王五");
        propagationService.insertTransactionRequiresNewOutRequired(user1, user2, user3);
    }

    /**
     * PROPAGATION_REQUIRES_NEW	新建事务，如果当前存在事务，把当前事务挂起
     *
     * 内部方法开启 propagation_requires_new 和 propagation_required 事务 ，外部方法开启 propagation_required 事务，内部方法抛出异常
     *
     * 执行结果：张三未插入，李四插入，王五未插入，
     * 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
     * 插入“王五”方法抛出异常，首先插入 “王五”方法的事务被回滚，异常继续抛出被外围方法感知，外围方法事务亦被回滚，故和外围方法相同事务的插入“张三”方法也被回滚。
     */
    @Test
    public void testTransactionPropagationRequiresNewOutRequiredException() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        User2 user3 = new User2();
        user3.setName("王五");
        propagationService.insertTransactionRequiresNewOutRequiredException(user1, user2, user3);
    }

    /**
     * PROPAGATION_REQUIRES_NEW	新建事务，如果当前存在事务，把当前事务挂起
     * 内部方法开启 propagation_requires_new 和 propagation_required 事务 ，外部方法开启 propagation_required 事务，内部方法抛出异常
     * 在外部方法捕获内部方法抛出的异常
     *
     * 执行结果：张三插入，李四插入，王五未插入，
     * 外围方法开启事务，插入“张三”方法和外围方法一个事务，插入“李四”方法、插入“王五”方法分别在独立的新建事务中。
     * 插入“王五”方法抛出异常，首先插入 “王五”方法的事务被回滚，异常被外围方法捕获不被外围方法感知（如果外部方法和内部方法处于同一事务，
     * 那么即使捕获异常，处于同一事务的方法还是会回滚），
     * 所以王五的事务不会被回滚，外围方法也不会回滚，所以和外围方法相同的事务的方法也不会回滚（张三的方法插入成功）
     */
    @Test
    public void testTransactionPropagationRequiresNewOutRequiredExceptionWithTry() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        User2 user3 = new User2();
        user3.setName("王五");
        propagationService.insertTransactionRequiresNewOutRequiredExceptionWithTry(user1, user2, user3);
    }

    /**
     * PROPAGATION_NESTED 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与 PROPAGATION_REQUIRED 类似的操作。
     * 内部方法开启 PROPAGATION_NESTED 事务 ，外部方法开启 propagation_required 事务，外部方法 抛出异常
     *
     * 执行结果：张三、李四均未插入
     *
     * 外围方法开启事务，内部事务为外围事务的子事务，外围方法回滚，内部方法也要回滚。
     *
     */
    @Test
    public void testTransactionPropagationNested() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertTransactionNested(user1, user2);
    }

    /**
     * PROPAGATION_NESTED 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与 PROPAGATION_REQUIRED 类似的操作。
     * 内部方法开启 PROPAGATION_NESTED 事务 ，外部方法开启 propagation_required 事务，内部方法 抛出异常
     *
     * 执行结果：张三、李四均未插入
     *
     * 外围方法开启事务，内部事务为外围事务的子事务，内部方法抛出异常回滚，且外围方法感知异常致使整体事务回滚。
     */
    @Test
    public void testTransactionPropagationNestedException() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertTransactionNestedException(user1, user2);
    }

    /**
     * PROPAGATION_NESTED 事务
     * 内部方法开启 PROPAGATION_NESTED 事务 ，外部方法开启 propagation_required 事务，内部方法抛出异常，但在 外部方法中 被捕获
     *
     * 执行结果：张三插入，李四未插入
     *
     * 外围方法开启事务，内部事务为外围事务的子事务，插入“张三”内部方法抛出异常，可以单独对子事务回滚，此时异常被捕获，
     * 外围方法没有感知，外围方法不会回滚。
     */
    @Test
    public void testTransactionPropagationNestedExceptionWithTry() {
        User1 user1 = new User1();
        user1.setName("张三");
        User2 user2 = new User2();
        user2.setName("李四");
        propagationService.insertTransactionNestedExceptionWithTry(user1, user2);
    }

}
