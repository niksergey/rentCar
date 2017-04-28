package main.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
    public static final Logger logger = LogManager.getLogger(CarServiceImpl.class.getName());

    @Override
    public String getDesc() {
        logger.debug("getDesc() is executed!");
        return "Spring MVC Hello World Example";
    }

    @Override
    public String getTitle(String name) {
        logger.debug("getTitle() is executed! $name : {}", name);
        if(StringUtils.isEmpty(name)){
            return "Hello World";
        }else{
            return "Hello " + name;
        }
    }
}