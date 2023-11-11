package cn.ppxytest.designmodewang.ordermanagement.audit;

import org.springframework.stereotype.Component;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:12
 */
@Component
public class ReceiveOrderLog extends AbstractAuditLogProcessor{
    @Override
    protected OrderAuditLog buildDetail(OrderAuditLog orderAuditLog) {
        return orderAuditLog;
    }
}
