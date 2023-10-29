package cn.ppxytest.designmodewang.bridge.function.abst;

import cn.ppxytest.designmodewang.bridge.function.RegisterLoginFuncInterface;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterLoginComponentFactory {
    public static final Map<String, AbstractReisterLoginComponent> componentMap = new ConcurrentHashMap<>();

    public static Map<String, RegisterLoginFuncInterface> funcMap = new ConcurrentHashMap<>();

    public static AbstractReisterLoginComponent getComponent(String type){
        AbstractReisterLoginComponent component = componentMap.get(type);
        if (component == null) {
            synchronized (componentMap){
                component = componentMap.get(type);
                if (component == null){
                    component = new RegisterLoginComponent(funcMap.get(type));
                    componentMap.put(type, component);
                }
            }
        }
        return component;
    }

}
