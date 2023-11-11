package cn.ppxytest.designmodewang.dutychain;

import cn.ppxytest.designmodewang.pojo.BusinessLaunch;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author: 水自强
 * @create-date: 2023/11/10 21:19
 */
public class ProductHandler extends AbstractBusinessHandler {
    @Override
    public List<BusinessLaunch> processHandler(List<BusinessLaunch> launchList, String targetCity, String targetSex, String targetProduct) {
        if (launchList.isEmpty()) {
            return launchList;
        }
        launchList = launchList.stream().filter(launch -> {
            String product = launch.getTargetProduct();
            if (StringUtils.isEmpty(product)) {
                return true;
            }
            List<String> cityList = Arrays.asList(product.split(","));
            return cityList.contains(targetSex);
        }).toList();

        if (hasNexHandler()) {
            return nextHandler.processHandler(launchList, targetCity, targetSex, targetProduct);
        }
        return launchList;
    }
}
