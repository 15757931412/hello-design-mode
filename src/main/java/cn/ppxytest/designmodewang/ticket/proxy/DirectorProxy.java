package cn.ppxytest.designmodewang.ticket.proxy;

import cn.ppxytest.designmodewang.ticket.director.AbstractDirector;
import cn.ppxytest.designmodewang.ticket.director.Director;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 14:26
 */
@Component
public class DirectorProxy extends AbstractDirector {
    @Autowired
    private Director director;

    @Override
    public Object buildTicket(String type, String productId, String content, String title, String bankInfo, String taxId) {
        String product = this.getProduct(productId);
        if (bankInfo != null && !this.validateBankInfo(bankInfo)) {
            return null;
        }
        return director.buildTicket(type, productId, content, title, bankInfo, taxId);
    }

    private String getProduct(String productId) {
        return "通过 productId 获取商品信息";
    }

    private boolean validateBankInfo(String bankInfo) {
        System.out.println(" 银行卡校验逻辑！");
        return true;
    }
}
