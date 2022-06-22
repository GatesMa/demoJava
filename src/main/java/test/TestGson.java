package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;
import model.Person;

/**
 * TestGson
 *
 * @author by gatesma.
 */
public class TestGson {


    public static void main(String[] args) throws Exception {

        String json = "{\"createdTime\":\"2022-03-23T15:26:41\"}";
        ObjectMapper mapper = new ObjectMapper();

        // 初始化JavaTimeModule
        JavaTimeModule javaTimeModule = new JavaTimeModule();

        //处理LocalDateTime
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));
        //注册时间模块, 支持支持jsr310, 即新的时间类(java.time包下的时间类)
        mapper.registerModule(javaTimeModule);

        Person person = new Person();
        person.setCreatedTime(LocalDateTime.now());

        System.out.println(mapper.writeValueAsString(person));


    }

}
