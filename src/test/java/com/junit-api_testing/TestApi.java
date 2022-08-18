package com.the_cat_api;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

import io.restassured.response.Response;

public class TestApi {

    String url = "https://reqres.in/";
    
    @Test
    public void signUp(){

        String apiUrl = "https://api.thecatapi.com/v1/user/passwordlesssignup";
        String payload = "{\"email\": \"pyinbujryxazlhmiuv@nthrl.com\", \"appDescription\": \"Teste de API\", \"details\":{\"user_type\":\"personal\"}}";
        Response response = given().contentType("application/json").body(payload).when().post(apiUrl);
        response.then().statusCode(200);

        System.out.println("Return => " + response.body().asString());
    }

    //To test a request to get a valid list of users
    @Test
    public void getValidUserList(){

        String request = "api/users";
        Response response = given().
        contentType("application/json").
      when().
        get(url+request);
      response.then().
        body("data", Matchers.notNullValue()).body("page", Matchers.is(1)).statusCode(200);
    }

    //To test a request to get a invalid list of user
    @Test
    public void getInvalidUserList(){

        String request = "api/users?page=1000";
        final int PAGE_NUM = 1000;
        Response response = given().
            contentType("application/json").
        when().
            get(url+request);
        response.then().
            body("data", Matchers.notNullValue()).body("page", Matchers.is(PAGE_NUM)).statusCode(200);
        
        System.out.println("URL: "+ url+request);
        System.out.println(response.body().asString());
        
    }
}
