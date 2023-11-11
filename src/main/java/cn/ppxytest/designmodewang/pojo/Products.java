package cn.ppxytest.designmodewang.pojo;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 12:52
 */
@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,name = "product_id")
    private String productId;

    @Column(nullable = false,name = "send_red_bag")
    private Integer sendRedBag;

}
