package Users.Objects;

public class User {
    //#region VARIABLES
    protected String id;
    protected String name;
    protected String password;
    protected Type type;
    //#endregion

    //#region ENUM
    public enum Type {
        COMMON,
        MANAGER,
    }
    //#endregion

    //#region CONSTRUCTORS
    public User(String cpf, String name, String password, Type type) {
        this.id = cpf;
        this.name = name;
        this.password = password;
        this.type = type;
    }
    //#endregion

    //#region GETTERS & SETTERS
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public int setPassword(String password) {
        if(this.password != password) {
            this.password = password;

            return 200;
        } else {
            return 406;
        }
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    //#endregion
}
