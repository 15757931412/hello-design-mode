package cn.ppxytest.designmodewang.ticket.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 13:39
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyTicket {
    private String finalInfo;
    private String title;
    private String taxId;
    private String bankInfo;
    private String product;
    private String content;

    @Override
    public CompanyTicket clone() {
        CompanyTicket personalTicket = null;
        try {
            personalTicket = (CompanyTicket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return personalTicket;
    }
}
