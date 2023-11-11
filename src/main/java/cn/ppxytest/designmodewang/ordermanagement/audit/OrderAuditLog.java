package cn.ppxytest.designmodewang.ordermanagement.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 15:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderAuditLog {
    private String account;
    private String action;
    private Date date;
    private String orderId;
    private Object detail;
}
