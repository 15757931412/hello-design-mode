package cn.ppxytest.designmodewang.ticket.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 13:37
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalTicket implements Cloneable {
    private String finalInfo;
    private String title;
    private String product;
    private String content;
    @Override
    public PersonalTicket clone(){
        PersonalTicket personalTicket = null;
        try{
            personalTicket = (PersonalTicket) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return personalTicket;
    }
}
