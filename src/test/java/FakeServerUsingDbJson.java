import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class FakeServerUsingDbJson {

    @Test
    public void testGet(){
        baseURI = "http://localhost:8000/";
        given().get("comments").then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testPost(){
        baseURI = "http://localhost:8000/";
        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("body", "Inserted Body 2");
        jsonObject.put("postId", 3);
        given().contentType(ContentType.JSON)
                .body(jsonObject.toJSONString()).post("comments")
                .then().statusCode(HttpStatus.SC_CREATED).log().all();
    }

    @Test
    public void testPut(){
        baseURI = "http://localhost:8000/";
        JSONObject jsonObject =  new JSONObject();
//        jsonObject.put("body", "Put method");
        jsonObject.put("postId", 8);
        given().contentType(ContentType.JSON)
                .body(jsonObject.toJSONString()).put("comments/2")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void testPatch(){
        baseURI = "http://localhost:8000/";
        JSONObject jsonObject =  new JSONObject();
//        jsonObject.put("body", "Inserted Body");
        jsonObject.put("postId", 7);
        given().contentType(ContentType.JSON)
                .body(jsonObject.toJSONString()).patch("comments/3")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void delete(){
        baseURI = "http://localhost:8000/";
        given().contentType(ContentType.JSON).delete("comments/4")
                .then().statusCode(HttpStatus.SC_OK).log().all();

    }


}
