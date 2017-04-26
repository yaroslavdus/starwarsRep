package swapi.database;
import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import swapi.entity.Film;
import swapi.entity.Planet;
import swapi.entity.Resident;


public class DBInsert {

    public static void main(String[] args) {
        
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        String url = "jdbc:mysql://localhost:3306/";
        String driver = "com.mysql.jdbc.Driver";
        String dbname = "starwars";
        String username = "root";
        String password = "dusenok7986";
        
        List<Planet> responseAllPlanets = new ArrayList<Planet>();        
        
        //Get List<Planet>
        RestAssured.baseURI = "http://swapi.co/api/planets";
        responseAllPlanets = when()
                    .get()
                .then()
                    .contentType(ContentType.JSON)
                    .statusCode(200)
                .extract()
                    .jsonPath().getList("results", Planet.class);

        // Loop List<Planets> to get residents (links)
        Map<Planet, Map<Resident, List<Film>>> mapAllPlanetsInfo = new LinkedHashMap<Planet, Map<Resident, List<Film>>>();
        for (Planet planet : responseAllPlanets) {
            System.out.println("\n**PlanetName: " + planet.getName());
            
            // Get list of residents(links) for a planet
            List<String> residentsLisnks = planet.getResidents();
            
            // for each resident click on resident link to get Resident object
            Map<Resident, List<Film>> mapFilmsForResident = new LinkedHashMap<Resident, List<Film>>();
            for (String residentLink : residentsLisnks) {
                RestAssured.baseURI = residentLink;
                Resident residentObject = when()
                        .get()
                        .then()
                            .contentType(ContentType.JSON)
                            .statusCode(200)
                        .extract()
                            .jsonPath().getObject("", Resident.class);
                System.out.println("ResidentLink: " + residentLink + " : ResidentName: " + residentObject.getName());
                
                // Get list of films(links) for a resident
                List<String> filmsForResident = residentObject.getFilms();
                
                // click on each film(link) to get Film object
                List<Film> filmsForOneResident = new ArrayList<Film>();
                for (String filmLink : filmsForResident) {
                    RestAssured.baseURI = filmLink;
                    Film filmObject = when()
                            .get()
                            .then()
                                .contentType(ContentType.JSON)
                                .statusCode(200)
                            .extract()
                                .jsonPath().getObject("", Film.class);
                    System.out.println("  filmLink: " + filmLink + " : " + filmObject.getTitle());
                    
                    filmsForOneResident.add(filmObject);
                }   
                mapFilmsForResident.put(residentObject, filmsForOneResident);
            }
            mapAllPlanetsInfo.put(planet, mapFilmsForResident);
        }  
        
        //print Map<Planet, Map<Resident, List<Film>>>
        System.out.println("\n\n*****Print All Planets Info*****");
        for (Planet planet: mapAllPlanetsInfo.keySet()){
            System.out.println("\n*PlanetName: " + planet.getName());
            
            Map<Resident, List<Film>> mapFilmsForALLResidents = mapAllPlanetsInfo.get(planet); 
            
            for (Resident resident : mapFilmsForALLResidents.keySet()) {
                System.out.println(" ResidentName: " + resident.getName());
                
                List<Film> filmsForResident = mapFilmsForALLResidents.get(resident);
                
                for (Film film : filmsForResident) {
                    System.out.println("    FilmName: " + film.getTitle());     
                }   
            }
        }  
        
        try{
            Class.forName(driver).newInstance(); // create object of Driver
            conn = DriverManager.getConnection(url + dbname, username, password);// connection establishment
        
            for(Planet planet1: mapAllPlanetsInfo.keySet()){
                ps = conn.prepareStatement("insert into planets (OriginalId, Name, Diameter) values (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, planet1.getUrl());
                ps.setString(2, planet1.getName());
                ps.setString(3, planet1.getDiameter());
                ps.executeUpdate();

                int planetId = 0;
                rs = ps.getGeneratedKeys();
                if (rs.next())
                    planetId = rs.getInt(1);

                rs.close();
                ps.close();
  
                Map<Resident, List<Film>> mapFilmsForALLResidents1 = mapAllPlanetsInfo.get(planet1); 

                for (Resident resident : mapFilmsForALLResidents1.keySet()) {
                    ps = conn.prepareStatement("insert into residents (ResidentsName, ResidentsHeight, ResidentsMass, PlanetId) values (?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, resident.getName());
                    ps.setInt(2, resident.getHeight());
                    ps.setString(3, resident.getMass());
                    ps.setInt(4, planetId);
                    ps.executeUpdate();
                    
                    int residentId = 0;
                    rs = ps.getGeneratedKeys();
                    if (rs.next())
                        residentId = rs.getInt(1);
                    
                    ps.close();
                    
                    List<Film> filmsForResident = mapFilmsForALLResidents1.get(resident);
                    
                    for(Film film: filmsForResident){
                        ps = conn.prepareStatement("insert into films (FilmName, ResidentId) values (?,?)");
                        ps.setString(1, film.getTitle());
                        ps.setInt(2, residentId);
                        ps.executeUpdate();
                        ps.close();
                  }
                }
            }
            
        }catch(Exception e){
            System.out.println("Error occurs " + e.getMessage());
            
        }
        finally{
            try{
                if(rs != null){
                    rs.close();
                }
                if(ps != null){
                    ps.close();
                }
                if(conn != null){
                    conn.close();
                }
            }catch(Exception e){
                System.out.println("Error occurs" + e);
            }
        }
    }
}
