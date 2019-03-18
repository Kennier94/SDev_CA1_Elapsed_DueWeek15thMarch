package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;


@Entity
public class Project extends Model {

 // Properties
 @Id
 private Long id;
 @Constraints.Required
 private String name;

 @OneToMany
 private List<Employee> employees;


 // Default Constructor
 public Project() {
 }

 // Constructor to initialise object
 public Project(Long id, String name, List<Employee> employees) {
 this.id = id;
 this.name = name;
 this.employees = employees;
 }
 // Accessor methods
 public Long getId() {
 return id;
 }
 public void setId(Long id) {
 this.id = id;
 }
 public String getName() {
 return name;
 }
 public void setName(String name) {
 this.name = name;
 }

    public List<Employee> getEmployees() {
        return employees;
    }
    
    public void setEmployees (List<Employee> employees) {
        this.employees = employees;
    }
       //Generic query helper for entity Computer with id Long
    public static Finder<Long,Project> find = new Finder<Long,Project>(Project.class);
    
    //Find all Products in the database
    public static List<Project> findAll() {
       return Project.find.query().where().orderBy("name asc").findList();
    }
    
    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap();
     
        // Get all the categories from the database and add them to the options hash map
        for (Project c: Project.findAll()) {
           options.put(c.getId().toString(), c.getName());
        }
        return options;
     }

}
