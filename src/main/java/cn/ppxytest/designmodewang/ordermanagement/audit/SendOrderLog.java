package cn.ppxytest.designmodewang.ordermanagement.audit;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:12
 */
@Component
public class SendOrderLog extends AbstractAuditLogProcessor{
    @Override
    protected OrderAuditLog buildDetail(OrderAuditLog orderAuditLog) {
        HashMap<String, String> stringStringHashMap = new HashMap<>() {
            {
                put("快递公司", "X快递");
                put("快递编号", "100100100");
            }
        };
        orderAuditLog.setDetail(stringStringHashMap);
        return orderAuditLog;
    }
}
