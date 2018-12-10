package Base;

import io.restassured.RestAssured;

public class Base {

    public static String RANDOM_VIN="/vehicle/description/randomVin/";
    public static String VIN_NUMBER="/vehicle/description/";

    public Base(){
        RestAssured.baseURI="https://lb.api.chrome.traderev.com/chrome";

    }
}
