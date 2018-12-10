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
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

public class Models extends Base{

    @BeforeMethod
    public void setup(){
        Base b;
    }



    @Test

    public void modelsRequest() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("year","2000").
                and().
                queryParam("divisionId", 1).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post("/models").
        then().
                log().
                all().
                assertThat().
                body("model.value", hasItems("Integra","NSX","RL","TL")).
                and().
                assertThat().
                statusCode(SC_OK);

    }

    @Test

    public void modelsRequestDivisionIdOtherThan1() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("year","2000").
                and().
                queryParam("divisionId", 2).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/models").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_NOT_FOUND);

    }

    @Test

    public void modelsRequestInvalidDivisionIDFormat() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("year","2000").
                and().
                queryParam("divisionId", "ABCD").
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/models").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_BAD_REQUEST);

    }

    @Test

    public void modelsRequestInvalidYearFormat() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("year","ABCD").
                and().
                queryParam("divisionId", 2).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/models").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_BAD_REQUEST);

    }

    @Test

    public void modelsRequestFutureYear() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("year","2020").
                and().
                queryParam("divisionId", 2).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/models").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_NOT_FOUND);

    }

    @Test

    public void modelsRequestInvalidAccoutNumber() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276766);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("year","2020").
                and().
                queryParam("divisionId", 1).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/models").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_BAD_REQUEST);

    }

    @Test

    public void modelsRequestInvalidAccountSecret() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2aabcd");
        requestParams.put("region","us");
        requestParams.put("service","drivin");


        given().
                queryParam("year","2020").
                and().
                queryParam("divisionId", 1).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/models").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_BAD_REQUEST);

    }

    @Test

    public void modelsRequestInvalidRegionCode() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2aabcd");
        requestParams.put("region","UK");
        requestParams.put("service","drivin");


        given().
                queryParam("year","2020").
                and().
                queryParam("divisionId", 1).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/models").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_BAD_REQUEST);

    }


    @Test

    public void modelsRequestInvalidService() {


        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2aabcd");
        requestParams.put("region","US");
        requestParams.put("service","12344");


        given().
                queryParam("year","2020").
                and().
                queryParam("divisionId", 1).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
                when().
                post("/models").
                then().
                log().
                all().
                assertThat().
                statusCode(SC_BAD_REQUEST);

    }

}
