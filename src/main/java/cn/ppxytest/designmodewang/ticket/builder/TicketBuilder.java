package cn.ppxytest.designmodewang.ticket.builder;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 13:41
 */
public abstract class TicketBuilder<T> {
    public abstract void setCommonInfo(String title, String product, String content);

    public void setTaxId(String taxId) {
        throw new UnsupportedOperationException();
    }

    public void setBankInfo(String bankInfo) {
        throw new UnsupportedOperationException();
    }

    public abstract T buildTicket();
}
