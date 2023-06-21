package Items.Objects;

import java.time.Instant;
import java.util.ArrayList;

import Users.Objects.User;

public class Item {
    //#region VARIABLES
    private String id;
    private User owner;
    private String name;
    private String description;
    private String type;
    private Status status;
    private ArrayList<String> images;
    //#endregion

    //#region ENUM
    public enum Status {
        PENDING,
        AUTHORIZED,
        UNAUTHORIZED,
    }
    //#endregion

    //#region CONSTRUCTORS
    public Item(User owner, String name, String description, String type) {
        this.id = "ITM" + Instant.now();
        this.status = Status.PENDING;

        this.owner = owner;
        this.name = name;
        this.description = description;
        this.type = type;

        this.images = new ArrayList<>();
    }
    //#endregion

    //#region GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }
    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<String> getImages() {
        return images;
    }
    public void setImage(String image) {
        this.images.add(image);
    }
    //#endregion

    //#region FUNCTIONS
    public void displayInformation() {
        System.out.println("ID [" + getId() + "]");
        System.out.println("Nome: " + getName());
        System.out.println("Descrição: " + getDescription());
        System.out.println("Dono: " + getOwner().getName());
        System.out.println("Status: " + getStatus());

        System.out.println();
    }
    //#endregion
}
