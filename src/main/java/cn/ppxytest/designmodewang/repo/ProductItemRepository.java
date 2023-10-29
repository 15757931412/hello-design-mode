package cn.ppxytest.designmodewang.repo;

import cn.ppxytest.designmodewang.pojo.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Integer> {
}
