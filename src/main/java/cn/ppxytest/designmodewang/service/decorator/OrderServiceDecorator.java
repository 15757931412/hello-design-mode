package cn.ppxytest.designmodewang.service.decorator;

import cn.ppxytest.designmodewang.pojo.Order;
import cn.ppxytest.designmodewang.pojo.Products;
import cn.ppxytest.designmodewang.repo.ProductsRepository;
import cn.ppxytest.designmodewang.service.OrderServiceInterface;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 12:41
 */
@Service
public class OrderServiceDecorator extends AbstractOrderServiceDecorator {
    @Value("${delay.service.time}")
    private String delayServiceTime;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Order decoratorPay(String orderId, int serviceLevel, float price) {
        Order order = super.pay(orderId);
        try {
            this.updateScoreAndSendRedPaper(order.getProductId(), serviceLevel, price);
        } catch (Exception e) {
            // 重试机制,此处积分更新不能影响支付主流程
        }
        return order;
    }

    @Override
    protected void updateScoreAndSendRedPaper(String productId, int serviceLevel, float price) {
        switch (serviceLevel) {
            case 0 -> {
                int score = Math.round(price) / 100;
                System.out.println("正常处理，为用户更新积分！ score=" + score);
                Products product = productsRepository.findByProductId(productId);
                if (product != null && product.getSendRedBag() == 1) {
                    System.out.println("正常处理，为用户发放红包！productId = " + productId);
                }
            }
            case 1 -> {
                MessageProperties messageProperties = new MessageProperties();
                messageProperties.setExpiration(delayServiceTime);
                Message msg = new Message(productId.getBytes(), messageProperties);
                rabbitTemplate.send("nomalExchange","myRkey",msg);
                System.out.println("延迟处理,时间 = "+delayServiceTime);
            }
            case 2 -> {
                System.out.println("暂停服务!");
            }
            default -> {
                throw new UnsupportedOperationException("不支持服务级别");
            }
        }

    }
}
