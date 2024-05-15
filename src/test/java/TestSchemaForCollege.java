import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestSchemaForCollege {

    @Test
    public void testSchemaForCollege(){
        baseURI = "http://localhost:8000/";
        get("college").then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
    }

    @Test(dataProvider = "dataProvider")
    public void testPostUsingDataProvider(String productName, Integer quantity, Double price){
        baseURI = "http://localhost:8000/";
        JSONObject jsonObject =  new JSONObject();
        jsonObject.put("name", productName);
        jsonObject.put("price", price);
        jsonObject.put("quantity", quantity);
        System.out.println(jsonObject.toJSONString());

        RequestSpecification request = given();
        request.contentType(ContentType.JSON);
        request.body(jsonObject.toJSONString());

        Response response = request.post("products");
        String responseAsString = response.getBody().asString();
        System.out.println("responseAsString : "  + responseAsString);

//        Assertions
        Assert.assertTrue(responseAsString.contains(productName));
        Assert.assertTrue(responseAsString.contains(quantity.toString()));
        Assert.assertTrue(responseAsString.contains(quantity.toString()));
        Assert.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
    }

    @DataProvider(name = "dataProvider")
    public Object[][] getData(){
       Object productInfo[][] = {
               {"ABC", Integer.valueOf(10), Double.valueOf(22.56)},
               {"CDE", Integer.valueOf(11), Double.valueOf(737.32)},
       };
       return productInfo;
    }
}