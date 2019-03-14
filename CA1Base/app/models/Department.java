package models;

import java.util.*;
import javax.persistence.*;

import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

// Product entity managed by Ebean
@Entity
public class Department extends Model {

   // Fields
   // Annotate id as primary key
   @Id
   private Long id;

   @Constraints.Required
   private String name;

   // Category contains many products
   @OneToMany
   private List<Employee> employees;

   // Default constructor
   public  Department() {
   }
			    
   public  Department(Long id, String name, List<Employee> employees) {
      this.id = id;
      this.name = name;
      this.employees = employees;
   }
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
public static Finder<Long,Department> find = new Finder<Long,Department>(Department.class);

//Find all Products in the database
public static List<Department> findAll() {
   return Department.find.query().where().orderBy("name asc").findList();
}

public static Map<String,String> options() {
    LinkedHashMap<String,String> options = new LinkedHashMap();
 
    // Get all the categories from the database and add them to the options hash map
    for (Department c: Department.findAll()) {
       options.put(c.getId().toString(), c.getName());
    }
    return options;
 }
}