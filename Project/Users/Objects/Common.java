package Users.Objects;

import java.util.HashMap;
import java.util.Map;

import Items.Objects.Item;
import Response.Response;
import Sweepstakes.Objects.Sweepstake;

public class Common extends User {
    private Map<String, Item> items;
    private Map<String, Sweepstake> sweepstakes;
    private Map<String, Sweepstake> sweepstakesJoined;

    public Common(String cpf, String name, String password) {
        super(cpf, name, password, Type.COMMON);

        this.items = new HashMap<>();
        this.sweepstakes = new HashMap<>();
        this.sweepstakesJoined = new HashMap<>();
    }


    public Map<String, Item> getItems() {
        return items;
    }
    public void setItems(Map<String, Item> items) {
        this.items = items;
    }

    public Map<String, Sweepstake> getSweepstakes() {
        return sweepstakes;
    }
    public void setSweepstakes(Map<String, Sweepstake> sweepstakes) {
        this.sweepstakes = sweepstakes;
    }

    public Map<String, Sweepstake> getSweepstakesJoined() {
        return sweepstakesJoined;
    }
    public void setSweepstakesJoined(Map<String, Sweepstake> sweepstakesJoined) {
        this.sweepstakesJoined = sweepstakesJoined;
    }


    public Response registerSweepstake(Sweepstake sweepstakes) {
        this.sweepstakes.put(sweepstakes.getId(), sweepstakes);

        return Response.OK;
    }
    public Sweepstake findSweepstake(String id) {
        return this.sweepstakes.get(id);
    }
    public void listSweepstakes() {
        for (Sweepstake sweepstake : sweepstakes.values()) {
            sweepstake.displayInformation();
        }
    }

    public Response makeSweepstake(String id) {
        return sweepstakes.get(id).make();
    }
    public Response joinSweepstake(Sweepstake sweepstake) {
        sweepstakesJoined.put(sweepstake.getId(), sweepstake);

        return Response.OK;
    }

    public int registerItem(Item item) {
        this.items.put(item.getId(), item);

        return 200;  
    }
    public Item findItem(String id) {
        return this.items.get(id);
    }
    public void listItems() {
        for (Item item : items.values()) {
            item.displayInformation();
        }
    }
    public int removeItem(Item item) {
        items.remove(item.getId());

        return 200;  
    }
}
