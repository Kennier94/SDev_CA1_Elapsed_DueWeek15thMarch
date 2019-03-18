package models;

import java.util.*;
import javax.persistence.*;
import io.ebean.*;
import play.data.format.*;
import play.data.validation.*;

@Entity
public class Employee extends Model {

    // Properties
    @Id
    private Long id;
    @Constraints.Required
    private String name;
    @Constraints.Required
    private int age;
    @Constraints.Required
    private int yearsWorked;
    // @Constraints.Required
    // private Date startDate;

    @ManyToOne
    private Department department;
    @ManyToOne
    private Project project;

    // Default Constructor
    public Employee() {
    }

    // Constructor to initialise object
    public Employee(Long id, String name, int age, int yearsWorked) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.yearsWorked = yearsWorked;
    }

    public static final Finder<Long, Employee> find = new Finder<>(Employee.class);
			    
public static final List<Employee> findAll() {
   return Employee.find.all();
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getYearsWorked() {
        return yearsWorked;
    }
    public void setYearsWorked(int yearsWorked) {
        this.yearsWorked= yearsWorked;
    }


public Department getDepartment() {
    return department;
}
public void setDepartment(Department department) {
    this.department = department;
}

public Project getProject() {
    return project;
}
public void setProject(Project project) {
    this.project = project;
}


}