package controllers;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.EmployeeDetails;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateEmployee extends BaseTest {

    @Test(groups="regressionTest")
    public void createEmployee() {

        Response response = RestAssured.given().body("{\"name\":\"janardhan\",\"salary\":\"1000\",\"age\":\"23\"}")
                .post(baseUrl + createUrn);

        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = new JsonPath(response.getBody().asString());

        Assert.assertEquals(jsonPath.get("data.name"), "janardhan");
        Assert.assertEquals(jsonPath.get("data.salary"), "1000");
        Assert.assertEquals(jsonPath.get("data.age"), "23");

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setEmployeeId(jsonPath.get("data.id").toString());
        employeeDetails.setEmployeeAge(jsonPath.get("data.age"));
        employeeDetails.setEmployeeSalary(jsonPath.get("data.salary"));
        employeeDetails.setEmployeeName(jsonPath.get("data.name"));
    }
}
