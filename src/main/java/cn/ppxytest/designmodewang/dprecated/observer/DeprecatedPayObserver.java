package cn.ppxytest.designmodewang.dprecated.observer;

import cn.ppxytest.designmodewang.dprecated.DeprecatedConstants;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DeprecatedPayObserver extends DeprecatedAbstractObserver {
    @PostConstruct
    public void init() {
        DeprecatedConstants.OBSERVER_LIST.add(this);
    }

    @Override
    public void orderStateHandle(String orderId, String orderState) {

    }
}
