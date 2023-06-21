package Users.Controllers;

import java.util.HashMap;
import java.util.Map;

import Response.Response;
import Users.Objects.Common;
import Users.Objects.Manager;
import Users.Objects.User;
import Users.Objects.User.Type;

public class UserController {
    //#region VARIABLES
    private Map<String, User> users;
    private User sessionUser;
    //#endregion
    
    //#region CONSTRUCTOR
    public UserController() {
        this.users = new HashMap<>();
    }
    //#endregion

    //#region GETTERS & SETTERS
    public User getSessionUser() {
        return sessionUser; 
    }
    //#endregion

    //#region LOGIN/SINGNUP
    public Response logIn(String cpf, String password) {
        User user = this.findUser(cpf);
        if(user != null) {
            if(user.getPassword().equals(password)) {
                sessionUser = user;
                if(user.getType().equals(Type.MANAGER)) return Response.MANAGER_ACCESS;

                return Response.COMMON_ACCESS;
            } else {
                return Response.UNAUTHORIZED;
            }
        } else {
            return Response.NOT_FOUND;
        }    
    }

    public Response singnUp(String cpf, String name, String password, String hashValidation) {
        if(this.findUser(cpf) == null) {
            if(hashValidation.equals("ADMIN")) {
                this.users.put(cpf, new Manager(cpf, name, password));
            } else {
                this.users.put(cpf, new Common(cpf, name, password));
            }

            return Response.CREATED;
        } else {
            return Response.NOT_ACCEPTABLE;
        }
    }

    private User findUser(String cpf) {
        return this.users.get(cpf);
    }
    //#endregion
}
