package cn.ppxytest.designmodewang.dutychain;

import cn.ppxytest.designmodewang.pojo.BusinessLaunch;

import java.util.List;

/**
 * @author: 水自强
 * @create-date: 2023/11/10 21:15
 */
public abstract class AbstractBusinessHandler {
    public AbstractBusinessHandler nextHandler;

    public boolean hasNexHandler() {
        return this.nextHandler != null;
    }

    public abstract List<BusinessLaunch> processHandler(List<BusinessLaunch> launchList, String targetCity, String targetSex, String targetProduct);
}
