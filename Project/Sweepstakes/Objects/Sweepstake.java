package Sweepstakes.Objects;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Items.Objects.Item;
import Response.Response;
import Rules.Objects.Rule;
import Users.Objects.Common;
import Users.Objects.User;

public class Sweepstake {
    //#region VARIABLES
    private String id;
    private User owner;
    private String overview;
    private String description;
    private Map<String, Rule> rules;

    private Map<String, User> entrants;
    private Map<String, Item> itemsRaffled;
    private Map<User, Item> selected;
    private Status status;
    //#endregion

    //#region ENUM
    public enum Status {
        PENDING,
        IN_PROGRESS,
        FINISHED,
        CANCELLED
    }
    //#endregion

    //#region CONSTRUCTORS
    public Sweepstake(User owner, String overview, String description, Map<String, Rule> rules) {
        this.id = "SWP" + (int) (Math.random() * 10) + Instant.now();

        this.owner = owner;
        this.overview = overview;
        this.description = description;
        this.rules = rules;

        this.entrants = new HashMap<>();
        this.itemsRaffled = new HashMap<>();
        this.selected = new HashMap<>();
        this.status = Status.PENDING;
    }
    //#endregion
    
    //#region GETTERS & SETTERS
    public String getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Rule> getRules() {
        return rules;
    }
    public void setRule(Rule rule) {
        this.rules.put(rule.getId(), rule);
    }
    public void removeRule(String id) {
        this.rules.remove(id);
    }

    public Response setItems(Item item) {
        if(item.getStatus() == Item.Status.AUTHORIZED) {
            itemsRaffled.put(item.getId(), item);

            return Response.OK;
        }
        
        return Response.UNAUTHORIZED;
    }

    public Map<String, User> getEntrants() {
        return entrants;
    }
    public Response setEntrants(User entrant) {
        if(getStatus() == Status.IN_PROGRESS && !getOwner().getId().equals(entrant.getId()) && !entrants.containsKey(entrant.getId())) {
            this.entrants.put(entrant.getId(), entrant);

            return Response.OK;
        }

        return Response.UNAUTHORIZED;
    }
    public Response removeEntrant(User user) {
        this.entrants.remove(user.getId());

        return Response.OK;
    }

    public Map<String, Item> getItemsRaffled() {
        return itemsRaffled;
    }
    public void setItemsRaffled(Item item) {
        this.itemsRaffled.put(item.getId(), item);
    }
    public void removeItemRaffled(String itemId) {
        this.itemsRaffled.remove(itemId);
    }

    public Map<User, Item> getSelected() {
        return selected;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    //#endregion

    //#region FUNCTIONS
    public void displayInformation() {
        System.out.println("ID [" + getId() + "]");
        System.out.println("Resumo: " + getOverview());
        System.out.println("Descrição: " + getDescription());
        System.out.println("Status: " + getStatus());

        System.out.println("--- Items Sorteados ---");
        for (Item item : itemsRaffled.values()) {
            item.displayInformation();
        }

        System.out.println();
    }

    public void displayWinners() {
        System.out.println("ID [" + getId() + "]");
        System.out.println("Resumo: " + getOverview());
        System.out.println("Status: " + getStatus());

        System.out.println("--- Sorteados ---");
        for (Map.Entry<User, Item> entry : selected.entrySet()) {
            User user = entry.getKey();
            Item item = entry.getValue();

            System.out.println(user.getName() + " - " + item.getName());
        }

        System.out.println();
    }

    public Response make() {
        if(getStatus() == Status.IN_PROGRESS && !getItemsRaffled().isEmpty() && !getEntrants().isEmpty()) {
            for(Item item: getItemsRaffled().values()) {
                Common winner = (Common) getRandomUser();
                Common owner = (Common) item.getOwner();

                selected.put(winner, item);

                
                item.setOwner(winner);

                winner.registerItem(item);
                owner.removeItem(item);
            }

            setStatus(Status.FINISHED);

            return Response.OK;
        }

        return Response.NOT_ACCEPTABLE;
    }

    private User getRandomUser() {
        List<String> keys = new ArrayList<>(entrants.keySet());
        Random random = new Random();
        String randomKey = keys.get(random.nextInt(keys.size()));
        return entrants.get(randomKey);
    }
    //#endregion
}
