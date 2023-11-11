package cn.ppxytest.designmodewang.service;

import cn.ppxytest.designmodewang.dutychain.AbstractBusinessHandler;
import cn.ppxytest.designmodewang.dutychain.CityHandler;
import cn.ppxytest.designmodewang.dutychain.builder.HandlerEnum;
import cn.ppxytest.designmodewang.pojo.BusinessLaunch;
import cn.ppxytest.designmodewang.pojo.UserInfo;
import cn.ppxytest.designmodewang.repo.BussinessLaunchRepository;
import cn.ppxytest.designmodewang.repo.UserRepository;
import cn.ppxytest.designmodewang.ticket.proxy.DirectorProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BussinessLaunchRepository bussinessLaunchRepository;
    @Value("${duty.chain}")
    private String handlerType;
    private String currentHandlerType;
    private AbstractBusinessHandler currentHandler;

    public List<BusinessLaunch> filterBusinessLaunch(String city, String sex, String product) {
        List<BusinessLaunch> launchLst = bussinessLaunchRepository.findAll();
        return buildChain().processHandler(launchLst, city, sex, product);
    }

    @Autowired
    private DirectorProxy directorProxy;

    public Object createTicket(String type, String productId, String content,String title, String bankInfo, String taxId) {
        return directorProxy.buildTicket(type, productId, content, title, bankInfo, taxId);
    }

    private AbstractBusinessHandler buildChain() {
        if (handlerType == null) {
            return null;
        }
        if (currentHandlerType == null) {
            this.currentHandlerType = this.handlerType;
        }
        if (this.handlerType.equals(currentHandlerType) && this.currentHandler != null) {
            return this.currentHandler;
        } else {
            System.out.println("配置有修改或首次初始化");
            synchronized (this) {
                try {
                    CityHandler dummyHeadHandler = new CityHandler();
                    AbstractBusinessHandler preHandler = dummyHeadHandler;
                    List<String> handlerTypeList = Arrays.asList(handlerType.split(","));
                    for (String handlerType : handlerTypeList) {
                        AbstractBusinessHandler handler = (AbstractBusinessHandler) Class.forName(HandlerEnum.valueOf(handlerType).getValue()).newInstance();
                        preHandler.nextHandler = handler;
                        preHandler = handler;
                    }
                    this.currentHandler = dummyHeadHandler.nextHandler;
                    this.currentHandlerType = this.handlerType;
                    return currentHandler;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public String login(String account, String password) {
        UserInfo userInfo = userRepository.findByUserNameAndUserPassword(account, password);
        if (userInfo == null) {
            return "account / password ERROR!";
        }
        return "Login Success";
    }

    public String register(UserInfo userInfo) {
        if (checkUserExists(userInfo.getUserName())) {
            throw new RuntimeException("User already registered.");
        }
        userInfo.setCreateDate(new Date());
        userRepository.save(userInfo);
        return "Register Success!";
    }

    public boolean checkUserExists(String userName) {
        UserInfo user = userRepository.findByUserName(userName);
        if (user == null) {
            return false;
        }
        return true;
    }
}
