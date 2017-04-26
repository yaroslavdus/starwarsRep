package swapi.yar.test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import swapi.yar.util.RestUtil;

public class PlanetsTest {
    
    private Response resp = null;
    private List<String> allPlanetsList = new ArrayList<String>();
    private List<String> planetsNameList = null;
    
    @BeforeMethod
    public void setup(){
        RestUtil.initURI("http://swapi.co/api");
        RestUtil.setContentType(ContentType.JSON);  
    }
    
    @Test
    public void statusCodeTest(){
        
        Response resp = when().
                get("/planets");

        System.out.println(resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(), 200);
    }  
    
    @Test
    public void getAllPlanetsTest(){
        
//1st page
        resp = when().get("/planets").then().statusCode(200).extract().response();           
        
        planetsNameList = resp.jsonPath().get("results.name");
        allPlanetsList.addAll(planetsNameList);
        
        System.out.println(allPlanetsList.size() + " : " + planetsNameList);
        
//2nd page
        String page2 = resp.jsonPath().get("next");

        resp = when().get(page2).then().statusCode(200).extract().response();
        
        planetsNameList = resp.jsonPath().get("results.name");
        allPlanetsList.addAll(planetsNameList);
        
        System.out.println(allPlanetsList.size() + " : " + planetsNameList);

//3rd page
        String page3 = resp.jsonPath().get("next");
        
        resp = when().get(page3).then().statusCode(200).extract().response();
        
        planetsNameList = resp.jsonPath().get("results.name");
        allPlanetsList.addAll(planetsNameList);
        
        System.out.println(allPlanetsList.size() + " : " + planetsNameList);
//4th page
        String page4 = resp.jsonPath().get("next");
        
        resp = when().get(page4).then().statusCode(200).extract().response();
        
        planetsNameList = resp.jsonPath().get("results.name");
        allPlanetsList.addAll(planetsNameList);
        
        System.out.println(allPlanetsList.size() + " : " + planetsNameList);
//5th page
        String page5 = resp.jsonPath().get("next");
        
        resp = when().get(page5).then().statusCode(200).extract().response();
        
        planetsNameList = resp.jsonPath().get("results.name");
        allPlanetsList.addAll(planetsNameList);
        
        System.out.println(allPlanetsList.size() + " : " + planetsNameList);
//6th page
        String page6 = resp.jsonPath().get("next");
        
        resp = when().get(page6).then().statusCode(200).extract().response();
        
        planetsNameList = resp.jsonPath().get("results.name");
        allPlanetsList.addAll(planetsNameList);
        
        System.out.println(allPlanetsList.size() + " : " + planetsNameList);
//7th page
        String page7 = resp.jsonPath().get("next");
        
        resp = when().get(page7).then().statusCode(200).extract().response();
        
        planetsNameList = resp.jsonPath().get("results.name");
        allPlanetsList.addAll(planetsNameList);
        
        System.out.println(allPlanetsList.size() + " : " + planetsNameList);
        Assert.assertEquals(allPlanetsList.size(), 61);
        
    }
    
    @Test
    public void verifyOnlyTenPlanetsReturned() {
        
        resp = when().get("/planets").then().statusCode(200).extract().response();           
        
        planetsNameList = resp.jsonPath().get("results.name");
        
        System.out.println(planetsNameList.size());
        Assert.assertEquals(planetsNameList.size(), 10);

    }
    
    @Test
    public void verifyCountOfPlanets(){
        
        resp = when().get("/planets");
        
        int count = resp
              .then()
                  .contentType(ContentType.JSON)
                  .statusCode(200)
              .extract()
                  .path("count");
        
        Assert.assertEquals(count, 61);            
    }
    
    @Test
    public void verifyNextPageURI(){
        
        resp = when().get("/planets");
        
        String actualURI = resp
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(200)
                .extract()
                    .path("next");
        String expectedURI = "http://swapi.co/api/planets/?page=2";
        Assert.assertEquals(actualURI, expectedURI);
    }
    
    @Test
    public void verifyNameOfPlanetById(){
 
        resp = given().log().all()
                .pathParam("id", "1")
            .when()
                .get("/planets/{id}");
        
        String planetName = resp
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(200)
                .extract()
                    .path("name");
        
        Assert.assertEquals(planetName, "Tatooine");
        System.out.println(resp.asString());
        
    }
}
