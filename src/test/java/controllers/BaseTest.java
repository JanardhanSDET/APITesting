package controllers;

import org.testng.annotations.BeforeMethod;
import setUp.SetUpTestData;
import utility.Environment;

public class BaseTest {
    public String baseUrl;
    public String createUrn;
    public String allEmplyoeesUrn;
    public String getEmployeeDetailsUrn;

    @BeforeMethod(groups={"smokeTest","regressionTest"})
    public void setUp(){

        SetUpTestData setUpTestData = new SetUpTestData(Environment.QA);
           baseUrl= setUpTestData.getBaseUrl();
           createUrn=setUpTestData.getCreateUrn();
           allEmplyoeesUrn= setUpTestData.getAllEmplyoeesUrn();
        getEmployeeDetailsUrn =setUpTestData.getUrnForGetEmployeeDetails();
    }




}
