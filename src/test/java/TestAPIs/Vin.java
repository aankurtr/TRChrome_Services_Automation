

package TestAPIs;
import Base.Base;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import net.minidev.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.apache.commons.httpclient.HttpStatus.SC_NOT_FOUND;
import static org.apache.commons.httpclient.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.hasItem;

public class Vin extends Base {

    public static String VIN;

    @BeforeMethod
    public void setUp(){

        super.setup();
    }


    @Test(priority = 1)

    public void vehicleDescriptionVehicleVin() {
        Response rsp =
                given().
                when().
                        get(RANDOM_VIN).
                then().
                        log().
                        all().
                extract().
                        response();

        Assert.assertEquals(rsp.statusCode(),SC_OK);
        VIN = (rsp.getBody()).asString();
        System.out.println(VIN);
        Assert.assertEquals((rsp.getBody()).asString().length(),17);



    }

    @Test(priority = 2)

    public void vehicleDescriptionVehicleSpecificInformationByVin(){

        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNumber",276700);
        requestParams.put("accountSecret", "85759a777a2a4448");
        requestParams.put("region","us");
        requestParams.put("service","drivin");

        System.out.println("vin:"+  VIN.substring(1));
        given().
                queryParam("vin", VIN).
                body(requestParams.toJSONString()).
                contentType(ContentType.JSON).
        when().
                post(VIN_NUMBER).
        then().
                log().
                all().
                assertThat().
                statusCode(SC_OK);

    }



}
