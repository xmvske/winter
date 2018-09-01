package top.huzhurong.aop.advisor.transaction;

import top.huzhurong.aop.advisor.transaction.definition.DefaultTransactionDefinition;
import top.huzhurong.aop.advisor.transaction.definition.TransactionDefinition;
import top.huzhurong.aop.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author luobo.cs@raycloud.com
 * @since 2018/9/1
 */
public class TransactionAttrSource {

    /**
     * 解析方法上的 @Transactional 注解，获取传播行为，隔离级别，事务超时等信息
     *
     * @param method 执行的方法
     * @return TransactionDefinition 事务信息
     */
    public static TransactionDefinition getDefinition(Method method) {
        Transactional transactional = Objects.requireNonNull(method).getDeclaredAnnotation(Transactional.class);
        TransactionDefinition definition = new DefaultTransactionDefinition();
        ((DefaultTransactionDefinition) definition).setIsolationLevel(transactional.isolation());
        ((DefaultTransactionDefinition) definition).setPropagation(transactional.propagation());
        ((DefaultTransactionDefinition) definition).setReadonly(transactional.readOnly());
        ((DefaultTransactionDefinition) definition).setTimeout(transactional.timeout());
        return definition;
    }

}
