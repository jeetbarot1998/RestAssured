import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TestRequest {

    @Test
    public void t1(){
        given()
                .header("till-type", "refund").contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .get("https://reqres.in/api/users?page=2").then()
                .body("data.id[1]",equalTo(8))
                .body("data.last_name", hasItems("Lawson", "Ferguson"))
                .statusCode(200).log().all();
    }

    @Test
    public void PostEx(){
        Map<String , Object> map = new HashMap<>();
        map.put("name", "tablet");
        map.put("quantity", Integer.valueOf(10));
        map.put("price", Double.valueOf(56.22));
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, Object> next = iterator.next();
            System.out.println(next.getKey() + " : " + next.getValue());
        }
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                .when().post("url")
                .then().statusCode(HttpStatus.SC_CREATED).log().all();
    }

    @Test
    public void PutEx(){
        baseURI = "baseUrl";
        Map<String , Object> map = new HashMap<>();
        map.put("name", "tablet");
        map.put("quantity", Integer.valueOf(10));
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                .when().put("uri")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void PatchEx(){
        baseURI = "baseUrl";
        Map<String , Object> map = new HashMap<>();
        map.put("name", "tablet");
        map.put("quantity", Integer.valueOf(10));
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject);
        given().contentType(ContentType.JSON).body(jsonObject.toJSONString())
                .when().patch("uri")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }

    @Test
    public void DeleteEx() {
        baseURI = "baseUrl";
        given().when().delete("uri")
                .then().statusCode(HttpStatus.SC_OK).log().all();
    }




    }
