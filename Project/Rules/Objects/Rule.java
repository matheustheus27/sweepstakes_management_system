package Rules.Objects;

import java.time.Instant;

public class Rule {
    //#region VARIABLES
    private String id;
    private String name;
    private String description;
    //#endregion
    
    //#region CONSTRUCTOR
    public Rule(String name, String description) {
        this.id = "RL" + Instant.now().getEpochSecond() + (int) (Math.random() * 10);
        this.name = name;
        this.description = description;
    }
    //#endregion

    //#region GETTERS & SETTERS
    public String getId() {
        return id;
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
    //#endregion

    //#region FUNCTIONS
    public void displayInformation() {
        System.out.println("ID [" + getId() + "]");
        System.out.println("Nome: " + getName());
        System.out.println("Descricao: " + getDescription());

        System.out.println();
    }
    //#endregion
}
