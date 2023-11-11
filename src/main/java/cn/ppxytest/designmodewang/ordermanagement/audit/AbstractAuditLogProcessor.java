package cn.ppxytest.designmodewang.ordermanagement.audit;

import java.util.Date;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:06
 */
public abstract class AbstractAuditLogProcessor {
    private final OrderAuditLog basicAuditLog(String account, String action, String orderId) {
        OrderAuditLog auditLog = new OrderAuditLog();
        auditLog.setAccount(account);
        auditLog.setAction(action);
        auditLog.setOrderId(orderId);
        auditLog.setDate(new Date());
        return auditLog;
    }
    protected abstract OrderAuditLog buildDetail(OrderAuditLog orderAuditLog);
    public final OrderAuditLog createAuditLog(String account, String action, String orderId){
        OrderAuditLog auditLog = basicAuditLog(account, action, orderId);
        return buildDetail(auditLog);
    }
}
