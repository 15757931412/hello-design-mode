package cn.ppxytest.designmodewang.ticket.builder;

import cn.ppxytest.designmodewang.ticket.product.CompanyTicket;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 13:44
 */
public class CompanyTicketBuilder extends TicketBuilder<CompanyTicket>{
    private CompanyTicket companyTicket = new CompanyTicket();
    @Override
    public void setCommonInfo(String title, String product, String content) {
        companyTicket.setTitle(title);
        companyTicket.setProduct(product);
        companyTicket.setContent(content);
    }

    @Override
    public void setBankInfo(String bankInfo) {
        companyTicket.setBankInfo(bankInfo);
    }

    @Override
    public void setTaxId(String taxId) {
        companyTicket.setTaxId(taxId);
    }

    @Override
    public CompanyTicket buildTicket() {
        return companyTicket;
    }
}
