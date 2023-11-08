package cn.ppxytest.designmodewang.pay.strategy;

import cn.ppxytest.designmodewang.pojo.Order;
import cn.ppxytest.designmodewang.util.AliConstants;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;

public class AlipayStrategy implements PayStrategyInterface {
    @Override
    public String pay(Order order) {
        DefaultAlipayClient alipayClient = new DefaultAlipayClient(AliConstants.ALIPAY_GATEWAY, AliConstants.APP_ID, AliConstants.APP_PRIVATE_KEY, "JSON", "UTF-8", AliConstants.ALIPAY_PUBLIC_KEY, AliConstants.SIGN_TYPE);
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        payRequest.setReturnUrl(AliConstants.CALLBACK_URL);
        String format = String.format("""
                {
                    "out_trade_no": "{}",
                    "total_amount": "{}",
                    "subject": "伟山育琪",
                    "body": "商品描述",
                    "product_code": "FAST_INSTANT_TRADE_PAY"
                }
                """, order.getOrderId(),order.getPrice());
        payRequest.setBizContent(format);
        try{
            String result = alipayClient.pageExecute(payRequest,"GET").getBody();
            return result;
        }catch (Exception e){
            throw new UnsupportedOperationException("Alipay failed!" + e);
        }
    }
}
