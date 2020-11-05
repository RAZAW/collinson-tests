package tests.apitests;

import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;

public class CityBikesAPITests {
    public static Response response;
    String baseURI =  "http://api.citybik.es";
    public static String jsonAsString = "";

    @Test
    public void testCityCountry() {
        String country = "", latitude = "", longitude = "";
        response = given()
                .log().uri()
                .get(baseURI+ "/v2/networks")
                .then()
                .assertThat().statusCode(200)
                .and()
                .log().status()
                .and()
                .log().body()
                .assertThat().body("networks[0].location", hasKey("city"))
                .assertThat().body("networks[0].location", hasKey("country"))
                .assertThat().body("networks[0].location", hasKey("latitude"))
                .assertThat().body("networks[0].location", hasKey("longitude"))
                .extract().response();


        jsonAsString = response.asString();
        ArrayList<HashMap<String, String>> listOfLocations = response.path("networks.location");
        for (HashMap<String, String> location : listOfLocations) {
            if (location.containsValue("Frankfurt")) {
                country = location.get("country");
                latitude = String.valueOf(location.get("latitude"));
                longitude = String.valueOf(location.get("longitude"));
            }
        }
        System.out.println("For city Frankfurt, the country is " + country+ ". It's latitude is " + latitude  +" and longitude is " + longitude);

    }

}
