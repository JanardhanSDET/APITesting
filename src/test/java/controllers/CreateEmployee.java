package controllers;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import model.EmployeeDetails;
import org.testng.Assert;
import org.testng.annotations.Test;
import utility.Resource;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateEmployee extends BaseTest {

    @Test(groups = "regressionTest")
    public void createEmployee() throws IOException {

      String response= given().
                              request().with().
                              body(Resource.getCreateEmployeeJson("janardhan1", "10000", "28")).
                       when()
                             .post(baseUrl + createUrn).
                       then()
                            .log().all().assertThat().statusCode(200)
                            .assertThat().body("data.name", equalTo("janardhan1"))
                            .assertThat().body("data.salary",equalTo("10000"))
                            .assertThat().body("data.age",equalTo("28"))
                            .extract().response().getBody().asString();

        JsonPath jsonPath = new JsonPath(response);

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployeeId(jsonPath.get("data.id").toString());
        employeeDetails.setEmployeeAge(jsonPath.get("data.age"));
        employeeDetails.setEmployeeSalary(jsonPath.get("data.salary"));
        employeeDetails.setEmployeeName(jsonPath.get("data.name"));

    }

    @Test(groups = "regressionTest")
    public void createEmployeeAndVerify() throws IOException {

        String response= given().
                                request().with()
                                .body(Resource.getCreateEmployeeJson("janardhan2", "10000", "28")).
                        when()
                             .post(baseUrl + createUrn).
                        then()
                              .log().all().assertThat().statusCode(200)
                              .assertThat().body("data.name", equalTo("janardhan2"))
                              .assertThat().body("data.salary",equalTo("10000"))
                              .assertThat().body("data.age",equalTo("28"))
                              .extract().response().getBody().asString();

        JsonPath jsonPath = new JsonPath(response);

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployeeId(jsonPath.get("data.id").toString());
        employeeDetails.setEmployeeAge(jsonPath.get("data.age"));
        employeeDetails.setEmployeeSalary(jsonPath.get("data.salary"));
        employeeDetails.setEmployeeName(jsonPath.get("data.name"));
       String id=employeeDetails.getEmployeeId();


        RestAssured.when()
                         .get(baseUrl+ getEmployeeDetailsUrn+employeeDetails.getEmployeeId())
                  .then()
                         .assertThat().statusCode(200)
                         .assertThat().body("name",equalTo(employeeDetails.getEmployeeName()))
                         .assertThat().body("salary",equalTo(employeeDetails.getEmployeeSalary()))
                         .assertThat().body("age",equalTo(employeeDetails.getEmployeeAge()))
                         .assertThat().body("id",equalTo(employeeDetails.getEmployeeId()));
    }

    @Test(groups = "regressionTest")
    public void createEmployeeWithOutName() {

         given().
                request().with()
                .body(Resource.getCreateEmployeeJson("", "10000", "28")).
         when()
                .post(baseUrl + createUrn).
         then()
                .log().all().assertThat().statusCode(200)
                .assertThat().body("data.name", equalTo(""))
                .assertThat().body("data.salary", equalTo("10000"))
                .assertThat().body("data.age", equalTo("28"));
    }

    @Test(groups = "regressionTest")
    public void createEmployeeAndVerifyResponseTime() throws IOException {

        long l = given()
                         .request().with()
                         .body(Resource.getCreateEmployeeJson("janardhan", "10000", "28")).
                when()
                        .post(baseUrl + createUrn).
                then()
                        .log().all().assertThat().statusCode(200)
                        .assertThat().body("data.name", equalTo("janardhan"))
                        .assertThat().body("data.salary", equalTo("10000"))
                        .assertThat().body("data.age", equalTo("28"))
                        .extract().timeIn(TimeUnit.MILLISECONDS);

       String  result = "false";
        if (l  <= 1500 ) {
            result = "true";
        }
        Assert.assertEquals(result, true);

    }

    @Test(groups = "regressionTest")
    public void createEmployeeWithOutSalary() {

        given().
                request().with()
                .body(Resource.getCreateEmployeeJson("janardhan", "", "28")).
        when()
                .post(baseUrl + createUrn).
        then()
                .log().all().assertThat().statusCode(200)
                .assertThat().body("data.name", equalTo("janardhan"))
                .assertThat().body("data.salary", equalTo(""))
                .assertThat().body("data.age", equalTo("28"));
    }

    @Test(groups = "regressionTest")
    public void createEmployeeWithOutAllDetails() {

        given().
                request().with()
                .body(Resource.getCreateEmployeeJson("", "", "")).
                when()
                .post(baseUrl + createUrn).
                then()
                .log().all().assertThat().statusCode(200)
                .assertThat().body("data.name", equalTo(""))
                .assertThat().body("data.salary", equalTo(""))
                .assertThat().body("data.age", equalTo(""));
    }

    }
