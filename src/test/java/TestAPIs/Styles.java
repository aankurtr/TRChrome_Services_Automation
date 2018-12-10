package TestAPIs;

import Base.Base;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.commons.httpclient.HttpStatus.SC_BAD_REQUEST;
import static org.apache.commons.httpclient.HttpStatus.SC_NOT_FOUND;
import static org.apache.commons.httpclient.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.hasItems;

public class Styles extends Base{

    @BeforeMethod
    public void setup(){
        Base b;
    }



    @Test

    public void stylesRequest() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("modelId",814).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/styles").
                then().
                log().
                all().
                assertThat().
                body("style.id", hasItems(11034,11035,11033,11032)).
                and().
                assertThat().
                statusCode(SC_OK);

    }

    @Test

    public void stylesRequestInvalidModelId() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("modelId",000).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/styles").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_NOT_FOUND);

    }

    @Test

    public void stylesRequestIncorrectModelIdFormat() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("modelId","ABC").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/styles").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_BAD_REQUEST);

    }


}
