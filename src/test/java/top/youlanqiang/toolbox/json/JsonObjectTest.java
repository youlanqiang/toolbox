package top.youlanqiang.toolbox.json;

import java.util.Arrays;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("JsonObject测试类")
public class JsonObjectTest {

    @Test
    void testJsonObject() {
        JsonObject json = new JsonObject();
        json.put("name", "tom");
        json.put("age", 11);
        json.put("date", new Date());
        json.put("localDate", LocalDate.now());
        json.put("localDateTime", LocalDateTime.now());
        json.put("list", Arrays.asList(1, 2, 3, 4, 5));
        System.out.println(json.toString());
        // {"name":"tom","age":11,"date":"Fri May 12 11:50:38 GMT+08:00
        // 2023","localDate":"2023-05-12","localDateTime":"2023-05-12T11:50:38.271273700","list":[1,2,3,4,5]}
    }
}
