package controllers;

import play.mvc.*;

import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import java.io.File;

import java.io.IOException;
import java.awt.image.*;
import javax.imageio.*;
import org.imgscalr.*;

import views.html.*;

import play.api.Environment;
import play.data.*;
import play.db.ebean.Transactional;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import models.*;
import models.users.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private FormFactory formFactory;

    private Environment e;

    @Inject
    public HomeController(FormFactory f, Environment env) {
        this.e = env;
        this.formFactory = f;
}
    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result employeesOnRecord(Long cat) {
        List<Employee> employeeList = null;
        List<Department> departmentList = Department.findAll();

        if(cat ==0){
            employeeList = Employee.findAll();
        }else {
            employeeList = Department.find.ref(cat).getEmployees();
        }
        return ok(employeesOnRecord.render(employeeList, departmentList, 
            User.getUserById(session().get("email")),e));

     }

    public Result index() {
        return ok(index.render(User.getUserById(session().get("email"))));
    }

    public Result about() {
        return ok(about.render(User.getUserById(session().get("email"))));
    }

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result addEmployee() {
        Form<Employee> employeeForm = formFactory.form(Employee.class);
        return ok(addEmployee.render(employeeForm, User.getUserById(session().get("email"))));
    }

@Security.Authenticated(Secured.class)
@Transactional
public Result addEmployeeSubmit() {
    // We use the method bindFromRequest() to populate our Form<ItemOnSale> object with the
    // data that the user submitted. Thanks to Play Framework, we do not need to do the messy
    // work of parsing the request and extracting data from it characte by character.
    Form<Employee> newEmployeeForm = formFactory.form(Employee.class).bindFromRequest();
    // We check for errors (based on constraints set in ItemOnSale class)
    if (newEmployeeForm.hasErrors()) {
        // If the form data have errors, we call the method badRequest(), requesting Play 
        // Framework to send an error response to the user and display the additem page again. 
        // As we are passing in newItemForm, the form will be populated with the data that the 
        // user has already entered, saving them from having to enter it all over again.
        return badRequest(addEmployee.render(newEmployeeForm, User.getUserById(session().get("email"))));
    } else {
        // If no errors are found in the form data, we extract the data from the form.
        // Form objects have handy utility methods, such as the get() method we are using 
        // here to extract the data into an ItemOnSale object. This is possible because
        // we defined the form in terms of the model class ItemOnSale.
        Employee newEmployee = newEmployeeForm.get();
        // Now we call the ORM method save() on the model object, to have it saved in the
        // database as a line in the table item_on_sale.
        
        if(newEmployee.getId()==null){
        newEmployee.save();
        }else{
            newEmployee.update();
        }

        MultipartFormData<File> data = request().body().asMultipartFormData();
        FilePart<File> image = data.getFile("upload");
        String saveImageMessage = saveFile(newEmployee.getId(), image);

        // We use the flash scope to specify that we want a success message superimposed on
        // the next displayed page. The flash scope uses cookies, which we can read and set
        // using the flash() function of the Play Framework. The flash scope cookies last
        // for a single request (unlike session cookies, which we will use for log-in in a
        // future lab). So, add a success message to the flash scope.
        flash("success", "Employee " + newEmployee.getName() + " was added/updated" + saveImageMessage);
        // Having specified we want a message at the top, we can redirect to the onsale page,
        // which will have to be modified to read the flash scope and display it.
        return redirect(controllers.routes.HomeController.employeesOnRecord(0));
    }
}

@Security.Authenticated(Secured.class)
@With(AuthAdmin.class)
@Transactional
public Result deleteEmployee(Long id) {

    // The following line of code finds the item object by id, then calls the delete() method
    // on it to have it removed from the database.
    Employee.find.ref(id).delete();

    // Now write to the flash scope, as we did for the successful item creation.
    flash("success", "Employee has been deleted.");
    // And redirect to the onsale page
    return redirect(controllers.routes.HomeController.employeesOnRecord(0));
}

    @Security.Authenticated(Secured.class)
    @With(AuthAdmin.class)
    @Transactional
    public Result updateEmployee(Long id) {
    Employee i;
    Form<Employee> employeeForm;

    try {
        // Find the item by id
        i = Employee.find.byId(id);

        // Populate the form object with data from the item found in the database
        employeeForm = formFactory.form(Employee.class).fill(i);
        } catch (Exception ex) {
            return badRequest("error");
    }

    // Display the "add item" page, to allow the user to update the item
        return ok(addEmployee.render(employeeForm, User.getUserById(session().get("email"))));
    }

    public String saveFile(Long id, FilePart<File> uploaded){
        if(uploaded != null){
            String mimeType = uploaded.getContentType();
            if(mimeType.startsWith("image/")){
                String fileName = uploaded.getFilename();
                String extension = "";
                int i = fileName.lastIndexOf('.');
                if(i >= 0){
                    extension = fileName.substring(i+1);
                }
                File file = uploaded.getFile();
                File dir = new File("public/images/productImages");
                if(!dir.exists()){
                    dir.mkdirs();
                }
                File newFile = new File("public/images/productImages/", id + "." + extension);
                if(file.renameTo(newFile)){
                    try{
                        BufferedImage img = ImageIO.read(newFile);
                        BufferedImage scaledImg = Scalr.resize(img, 90);
                        if(ImageIO.write(scaledImg, extension, new File("public/images/productImages/",
                            id + "thumb.jpg"))){
                                return "/ file uploaded and thumbnail created.";
                            }else {
                                return "/ file uploaded but thumbnail creation failed.";
                            }
                    } catch(IOException e){
                        return "/ file uploaded but thumbnail creation failed.";
                    }
                } else{
                    return "/ file upload failed.";
                }
            }
        }
        return "/ no image file.";
    }

}
