package controllers;

import play.mvc.*;
import play.mvc.Http.*;

public class Secured extends Security.Authenticator {

    // Get the email (username) of the logged-in user or null
    // if no user is logged in
    @Override
    public String getUsername(Context ctx){
        return ctx.session().get("email");
    }


    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(controllers.routes.LoginController.login());
    }

}