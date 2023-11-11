package cn.ppxytest.designmodewang.ticket.director;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 13:46
 */
public abstract class AbstractDirector {
    public abstract Object buildTicket(String type, String product, String content, String title, String bankInfo, String taxId);
}
