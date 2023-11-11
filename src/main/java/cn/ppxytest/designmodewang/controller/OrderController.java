package cn.ppxytest.designmodewang.controller;

import cn.ppxytest.designmodewang.pojo.Order;
import cn.ppxytest.designmodewang.service.OrderServiceInterface;
import cn.ppxytest.designmodewang.service.decorator.OrderServiceDecorator;
import cn.ppxytest.designmodewang.util.AliConstants;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceDecorator orderServiceDecorator;
    @Value("${service.level}")
    private Integer serviceLevel;
    @Autowired
    private OrderServiceInterface orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestParam String productId) {
        return orderService.createOrder(productId);
    }

    @PostMapping("/pay")
    public String payOrder(@RequestParam String orderId, @RequestParam Float price, @RequestParam Integer payType) {
        return orderService.getPayUrl(orderId, price, payType);
    }

    @PostMapping("/send")
    public Order send(@RequestParam String orderId) {
        return orderService.send(orderId);
    }

    @PostMapping("/receive")
    public Order receive(@RequestParam String orderId) {
        return orderService.receive(orderId);
    }

    @RequestMapping("/alipaycallback")
    public String alipayCallback(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        HashMap<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String next = iter.next();
            String[] values = requestParams.get(next);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            valueStr = new String(valueStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            params.put(next, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AliConstants.ALIPAY_PUBLIC_KEY, "UTF-8", AliConstants.SIGN_TYPE);
        if (signVerified) {
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            String trade_no = new String(request.getParameter("trade_no").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            float total_amount = Float.parseFloat(new String(request.getParameter("total_amount")
                    .getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
//            Order order = orderService.pay(out_trade_no);
            orderServiceDecorator.setOrderServiceInterface(orderService);
            Order order = orderServiceDecorator.decoratorPay(out_trade_no, serviceLevel, total_amount);
            return "支付宝成功页面跳转,当前订单为：" + order;
        } else {
            throw new UnsupportedOperationException("callback verify failed!");
        }
    }

    @PostMapping("/friendPay")
    public void friendPay(String sourceCustomer, String orderId, String targetCustomer, String payResult, String role) {
        orderService.friendPay(sourceCustomer, orderId, targetCustomer, payResult, role);
    }

}
