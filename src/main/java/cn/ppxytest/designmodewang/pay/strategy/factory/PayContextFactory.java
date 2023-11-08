package cn.ppxytest.designmodewang.pay.strategy.factory;

import cn.ppxytest.designmodewang.pay.strategy.PayStrategyInterface;
import cn.ppxytest.designmodewang.pay.strategy.context.PayContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PayContextFactory extends AbstractPayContextFactory<PayContext> {
    private static final Map<String, PayContext> payContexts = new ConcurrentHashMap<>();
    @Override
    public PayContext getContext(Integer payType) {
        StrategyEnum strategyEnum = payType == 1 ? StrategyEnum.alipay :
                payType == 2 ? StrategyEnum.wechat :
                        null;
        if (strategyEnum == null) {
            throw new UnsupportedOperationException("payType not supported!");
        }

        PayContext context = payContexts.get(strategyEnum.name());
        if (context==null){
            try {
                PayStrategyInterface payStrategy = (PayStrategyInterface)Class.forName(strategyEnum.getValue()).newInstance();
                PayContext payContext = new PayContext(payStrategy);
                payContexts.put(strategyEnum.name(), payContext);
            }catch (Exception e){
                throw new UnsupportedOperationException("Get payStrategy failed!");
            }
        }
        return payContexts.get(strategyEnum.name());
    }
}
