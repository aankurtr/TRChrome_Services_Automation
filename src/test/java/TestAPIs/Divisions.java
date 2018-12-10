

package TestAPIs;
import Base.Base;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.apache.commons.httpclient.HttpStatus.SC_BAD_REQUEST;
import static org.apache.commons.httpclient.HttpStatus.SC_NOT_FOUND;
import static org.apache.commons.httpclient.HttpStatus.SC_OK;

import net.minidev.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;


public class Divisions extends Base {

    @BeforeMethod
    public void setup(){
        Base b;
    }



    @Test

    public void divisionsRequest() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().port(port).
                queryParam("year","2000").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post("/divisions").
        then().
                log().
                all().
                assertThat().
                body("division.value", hasItem("Acura")).
        and().
                assertThat().
                statusCode(SC_OK);

    }

    @Test

    public void divisionsRequestWithIncorrectAccountNumber(){


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276766);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");



        given().
                queryParam("year","2000").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post("/divisions").
        then().
                log().
                all().
                assertThat().
                header("Connection","keep-alive").
        and().
                assertThat().
                statusCode(SC_OK);

    }

    @Test

    public void divisionRequestWithIncorrectAccountSecret(){
        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret","85759a777a2a4abc");
        requestParams.put("region","us");
        requestParams.put("service","drivin");

        given().
                queryParam("year", "2000").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                log().
                ifValidationFails().
        when().
                post("/divisions").
        then().
                log().
                ifValidationFails().
                assertThat().
                statusCode(SC_BAD_REQUEST);
    }

    @Test

    public void divisionRequestWithIncorrectRegionCode(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("accoutNumber",276700);
        requestParams.put("secretKey","85759a777a2a4448");
        requestParams.put("region","uk");
        requestParams.put("service","drivin");

        given().
                queryParam("year","2000").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post("/division").
        then().
                log().
                everything().
                assertThat().
                statusCode(SC_NOT_FOUND);
    }

    @Test

    public void divisionRequestWithIncorrectService(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("accoutNumber",276700);
        requestParams.put("secretKey","85759a777a2a4448");
        requestParams.put("region","uk");
        requestParams.put("service","xyz123");

        given().
                queryParam("year","2000").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post("/division").
        then().
                log().
                everything().
                assertThat().
                statusCode(SC_NOT_FOUND);
    }

    @Test

    public void divisionRequestWithIncorrectYearFormat(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("accoutNumber",276700);
        requestParams.put("secretKey","85759a777a2a4448");
        requestParams.put("region","uk");
        requestParams.put("service","xyz123");

        given().
                queryParam("year","ABCD").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post("/division").
        then().
                log().
                everything().
                assertThat().
                statusCode(SC_NOT_FOUND);
    }

    @Test

    public void divisionRequestWithInvalidYear(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("accoutNumber",276700);
        requestParams.put("secretKey","85759a777a2a4448");
        requestParams.put("region","uk");
        requestParams.put("service","xyz123");

        given().
                queryParam("year","1940").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post("/division").
        then().
                log().
                everything().
                assertThat().
                statusCode(SC_NOT_FOUND);
    }

    @Test

    public void divisionRequestWithFutureYear(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("accoutNumber",276700);
        requestParams.put("secretKey","85759a777a2a4448");
        requestParams.put("region","uk");
        requestParams.put("service","xyz123");

        given().
                queryParam("year","2020").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post("/division").
        then().
                log().
                everything().
                assertThat().
                statusCode(SC_NOT_FOUND);
    }


}
