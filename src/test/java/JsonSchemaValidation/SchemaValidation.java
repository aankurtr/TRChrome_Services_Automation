package JsonSchemaValidation;

import Base.Base;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.response.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class SchemaValidation extends Base {



    @Test
    public void validateJsonSchema(){

        given().
                get(RANDOM_VIN).
        then().
                log().
                all().
                assertThat().
                body(matchesJsonSchemaInClasspath(""));

    }


}
