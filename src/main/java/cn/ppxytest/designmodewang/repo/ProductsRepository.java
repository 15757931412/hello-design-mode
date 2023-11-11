package cn.ppxytest.designmodewang.repo;

import cn.ppxytest.designmodewang.pojo.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: 水自强
 * @create-date: 2023/11/11 12:55
 */
@Repository
public interface ProductsRepository extends JpaRepository<Products, Integer> {
    public Products findByProductId(String productId);
}
