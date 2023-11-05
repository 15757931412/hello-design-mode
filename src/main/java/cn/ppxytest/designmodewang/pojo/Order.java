package cn.ppxytest.designmodewang.pojo;

import cn.ppxytest.designmodewang.ordermanagement.state.OrderState;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;
    private String productId;
    private OrderState orderState;
    private Float price;
}
