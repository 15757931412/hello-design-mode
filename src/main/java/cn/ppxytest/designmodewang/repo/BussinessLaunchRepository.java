package cn.ppxytest.designmodewang.repo;

import cn.ppxytest.designmodewang.pojo.BusinessLaunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: 水自强
 * @create-date: 2023/11/10 21:08
 */
@Repository
public interface BussinessLaunchRepository extends JpaRepository<BusinessLaunch, Integer> {
}
