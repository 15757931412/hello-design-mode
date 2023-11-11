package cn.ppxytest.designmodewang.ticket.builder;

import cn.ppxytest.designmodewang.ticket.product.PersonalTicket;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 13:43
 */
public class PersonalTicketBuilder extends TicketBuilder<PersonalTicket> {

    PersonalTicket personalTicket = new PersonalTicket();

    @Override
    public void setCommonInfo(String title, String product, String content) {
        personalTicket.setTitle(title);
        personalTicket.setProduct(product);
        personalTicket.setContent(content);
    }

    @Override
    public PersonalTicket buildTicket() {
        return personalTicket;
    }
}
