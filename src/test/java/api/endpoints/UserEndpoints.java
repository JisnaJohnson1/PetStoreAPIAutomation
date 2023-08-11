package api.endpoints;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
//import static io.restassured.*;


public class UserEndpoints {


     public  static Response CreateUser(User payload){

       Response res= given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);

       return res;

    }

    public  static Response getUser(String userName){

        Response res= given()
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .when()
                .get(Routes.get_url);

        return res;

    }

    public  static Response updateUser(String userName, User payload){

        Response res= given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("username",userName)
                .body(payload)
                .when()
                .put(Routes.update_url);

        return res;

    }

    public  static Response deleteUser(String userName){

        Response res= given()
                .accept(ContentType.JSON)
                .pathParam("username",userName)
                .when()
                .delete(Routes.delete_url);

        return res;

    }
}
