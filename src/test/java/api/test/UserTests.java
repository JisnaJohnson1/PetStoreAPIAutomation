package api.test;

import api.endpoints.UserEndpoints;
import api.payloads.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User userPayload;
    @BeforeClass
    public void setUpData(){
        faker = new Faker();
        userPayload=new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());


    }

    @Test(priority = 1)
    public void testPostUser(){
        Response res=UserEndpoints.CreateUser(userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);
    }
@Test(priority = 2)
    public  void getUserByName(){
        Response res=UserEndpoints.getUser(this.userPayload.getUsername());
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);

    }

    @Test(priority = 3)
    public  void UpdateUserByName(){

        //Update data using Payload
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());


        Response res=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
        res.then().log().all();
        Assert.assertEquals(res.getStatusCode(),200);

        //Checking Data after update

       Response responseAfterUpdate= UserEndpoints.getUser(this.userPayload.getUsername());
       Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);

    }

    @Test(priority = 4)
    public  void testDeleteUserByName(){
        Response res= UserEndpoints.deleteUser(this.userPayload.getUsername());
        Assert.assertEquals(res.getStatusCode(),200);
    }
}
