package swapi.entity;

import java.util.Date;
import java.util.List;

public class Resident {
    
    private String name;
    private int height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private List<String> films;
    private List<String> species;
    private List<String> vehicles;
    private Object[] starships;
    private Date created;
    private Date edited;
    private String url;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String getMass() {
        return mass;
    }
    public void setMass(String mass) {
        this.mass = mass;
    }
    public String getHair_color() {
        return hair_color;
    }
    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }
    public String getSkin_color() {
        return skin_color;
    }
    public void setSkin_color(String skin_color) {
        this.skin_color = skin_color;
    }
    public String getEye_color() {
        return eye_color;
    }
    public void setEye_color(String eye_color) {
        this.eye_color = eye_color;
    }
    public String getBirth_year() {
        return birth_year;
    }
    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getHomeworld() {
        return homeworld;
    }
    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }
    public List<String> getFilms() {
        return films;
    }
    public void setFilms(List<String> films) {
        this.films = films;
    }
    public List<String> getSpecies() {
        return species;
    }
    public void setSpecies(List<String> species) {
        this.species = species;
    }
    public List<String> getVehicles() {
        return vehicles;
    }
    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }
    public Object[] getStarships() {
        return starships;
    }
    public void setStarships(Object[] starships) {
        this.starships = starships;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }
    public Date getEdited() {
        return edited;
    }
    public void setEdited(Date edited) {
        this.edited = edited;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String toString(){
        return "Residents=[name=" + name + ", height=" + height + ", mass=" + mass + "]";
    }

}
