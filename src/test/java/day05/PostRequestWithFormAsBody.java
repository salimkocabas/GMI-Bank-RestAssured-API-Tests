package day05;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PostRequestWithFormAsBody {

    //posting in library app
    // body is not json , it's x-www-urlencoded-form-data

    //http://library1.cybertekschool.com/rest/v1/login
    // baseURI  is http://library1.cybertekschool.com
    // basePath is /rest/v1
    // we are working on POST /login

    // Post body , type x-www-urlencoded-form-data
    //email :    librarian69@library
    //password : KNPXrm3S

    // in URLs if you do not see port , because it's omitted because it's so common
    //  http --->> 80
    //  https --->> 443
    //  anything other than above 2 ports need to be explicitly set in the URL
    // for example spartan app use port 8000 -->> yourip:8000

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";


    }

    @DisplayName("POST /login request test")
    @Test
    public void testLoginEndPoint() {

        given()
                .log().all()
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").
                when()
                .post("/login").
                then()
                .log().all()
                .statusCode(200)
                // we can not actually validate the token since it's dynamic
                // what we can validate though -- token field exists and it's not null
                .body("token", is(notNullValue()))

        ;

    }

    // this is for testing
    @DisplayName("Testing out the utility")
    @Test
    public void runningUtilityMethod(){

        System.out.println(  loginAndGetToken("librarian69@library", "KNPXrm3S")  );

    }

    /**
     * A static utility method to get the token by providing username and password
     * as Post request to /Login endpoint and capture the token field from response json
     * @param username
     * @param password
     * @return the token as String from the response json
     */


//This is creating method to enter what ever I want to enter
    public static String loginAndGetToken(String username, String password){
     //  String token="";
        Response response = given()
                .log().all()
                .formParam("email", username)
                .formParam("password", password).
                        when()
                .post("/login");
      //  JsonPath jp = response.jsonPath();
        String token = response.jsonPath().getString("token");
        return token;
    }
    }
