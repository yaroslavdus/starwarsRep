package swapi.yar.util;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RestUtil {
    
//    public static String path;
    
    public static void initURI(String baseURI){
        RestAssured.baseURI = baseURI;
    }
    
    public static void setContentType (ContentType type){
        given().contentType(type);
    }
    
}
