package cn.ppxytest.designmodewang.transaction.mediator;

import cn.ppxytest.designmodewang.transaction.colleague.AbstractCustomer;
import cn.ppxytest.designmodewang.transaction.colleague.Buyer;
import cn.ppxytest.designmodewang.transaction.colleague.Payer;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 14:44
 */
@Component
public class Mediator extends AbstractMediator {
    public static Map<String, Map<String, AbstractCustomer>> customerInstances = new ConcurrentHashMap<>();
    private AbstractCustomer buyer;
    private AbstractCustomer payer;

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    @Override
    public void messageTransfer(String orderId, String targetCustomer, AbstractCustomer customer, String payResult) {
        if (customer instanceof Buyer) {
            AbstractCustomer buyer = customerInstances.get(orderId).get("buyer");
            System.out.println("朋友代付：" + buyer.getCustomerName() + "转发 OrderId " + orderId + " 的支付。通知" + targetCustomer + ",支付结果：" + payResult);
        } else if (customer instanceof Payer) {
            AbstractCustomer payer = customerInstances.get(orderId).get("payer");
            System.out.println("代付完成：" + payer.getCustomerName() + "完成 OrderId " + orderId + " 的支付。通知 " + targetCustomer + "，支付结果：" + payResult);
            customerInstances.remove(orderId);
        }
    }
}
