package controllers;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.EmployeeDetails;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.util.*;

public class AllEmployeeDetailsTest extends BaseTest {

    @Test(groups="smokeTest")
    public void  allEmployeeDetailsTest() {

        Response response = RestAssured.get(baseUrl + allEmplyoeesUrn);
        Assert.assertEquals(response.getStatusCode(), 200);

        JsonPath jsonPath = new JsonPath(response.asString());
        List<String> Ids = jsonPath.get("data.id");
        int count = Ids.size();

         Map<String,EmployeeDetails> listOfEmployee = new LinkedHashMap<String,EmployeeDetails>();
        for (int i = 0; i <= count; i++) {
            EmployeeDetails employeeDetails = new EmployeeDetails();
            employeeDetails.setEmployeeId(jsonPath.get("data.id[" + i + "]"));
            employeeDetails.setEmployeeAge(jsonPath.get("data.employee_age[" + i + "]"));
            employeeDetails.setEmployeeSalary(jsonPath.get("data.employee_salary[" + i + "]"));
            employeeDetails.setEmployeeName(jsonPath.get("data.employee_name[" + i + "]"));
            listOfEmployee.put(employeeDetails.getEmployeeId(),employeeDetails);
        }
        System.out.println(listOfEmployee.containsKey("1"));
          Iterator< Map.Entry<String,EmployeeDetails>> iterator = listOfEmployee.entrySet().iterator();
              while(iterator.hasNext()){
                  Map.Entry<String,EmployeeDetails> object = iterator.next();
                  if(object.getKey().equals("1")){
                      EmployeeDetails details= object.getValue();
                      System.out.println(details.getEmployeeId());
                      System.out.println(details.getEmployeeAge());
                      System.out.println(details.getEmployeeName());
                      System.out.println(details.getEmployeeSalary());
                      break;
                  }

              }
    }
}
