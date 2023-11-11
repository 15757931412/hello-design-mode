package cn.ppxytest.designmodewang.ticket.director;

import cn.ppxytest.designmodewang.ticket.builder.CompanyTicketBuilder;
import cn.ppxytest.designmodewang.ticket.builder.PersonalTicketBuilder;
import org.springframework.stereotype.Component;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 13:47
 */
@Component
public class Director extends AbstractDirector {
    @Override
    public Object buildTicket(String type, String product, String content, String title, String bankInfo, String taxId) {
        if (type.equals("person")) {
            PersonalTicketBuilder personalTicketBuilder = new PersonalTicketBuilder();
            personalTicketBuilder.setCommonInfo(title, content, content);
            return personalTicketBuilder.buildTicket();
        } else if (type.equals("company")) {
            CompanyTicketBuilder companyTicketBuilder = new CompanyTicketBuilder();
            companyTicketBuilder.setCommonInfo(title, content, content);
            companyTicketBuilder.setTaxId(taxId);
            companyTicketBuilder.setBankInfo(bankInfo);
            return companyTicketBuilder.buildTicket();
        }
        throw new UnsupportedOperationException("不支持的发票类型! ");
    }
}
