package swapi.entity;

import java.util.Date;
import java.util.List;

public class Planet {
    
    private int id;
    private String name;
    private int rotation_period;
    private int orbital_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    private List<String> residents;
    private List<String> films;
    private Date created;
    private Date edited;
    private String url;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getRotation_period() {
        return rotation_period;
    }
    public void setRotation_period(int rotation_period) {
        this.rotation_period = rotation_period;
    }
    public int getOrbital_period() {
        return orbital_period;
    }
    public void setOrbital_period(int orbital_period) {
        this.orbital_period = orbital_period;
    }
    public String getDiameter() {
        return diameter;
    }
    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }
    public String getClimate() {
        return climate;
    }
    public void setClimate(String climate) {
        this.climate = climate;
    }
    public String getGravity() {
        return gravity;
    }
    public void setGravity(String gravity) {
        this.gravity = gravity;
    }
    public String getTerrain() {
        return terrain;
    }
    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }
    public String getSurface_water() {
        return surface_water;
    }
    public void setSurface_water(String surface_water) {
        this.surface_water = surface_water;
    }
    public String getPopulation() {
        return population;
    }
    public void setPopulation(String population) {
        this.population = population;
    }
    public List<String> getResidents() {
        return residents;
    }
    public void setResidents(List<String> residents) {
        this.residents = residents;
    }
    public List<String> getFilms() {
        return films;
    }
    public void setFilms(List<String> films) {
        this.films = films;
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
        return "Planets=[name=" + name + ", diameter=" + diameter + "]";
    }
  
}
