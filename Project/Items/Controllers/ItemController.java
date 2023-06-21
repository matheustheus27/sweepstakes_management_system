package Items.Controllers;

import java.util.HashMap;
import java.util.Map;

import Items.Objects.Item;
import Response.Response;

public class ItemController {
    //#region VARIABLES
    private Map<String, Item> items;
    //#endregion
    
    //#region CONSTRUCTOR
    public ItemController() {
        this.items = new HashMap<>();
    }
    //#endregion

    //#region FUNCTIONS
    public void listItems() {
        for (Item item : items.values()) {
            item.displayInformation();
        }
    }

    public void listItems(Item.Status status) {
        for (Item item : items.values()) {
            if (item.getStatus().equals(status)) {
                item.displayInformation();
            }
        }
    }

    public void listItemsPerUser(String cpf) {
        for (Item item : items.values()) {
            if (item.getOwner().getId().equals(cpf)) {
                item.displayInformation();
            }
        }
    }

    public Item findItem(String id) {
        return items.get(id);
    }

    public Response registerItem(Item item) {
        items.put(item.getId(), item);

        return Response.OK;
    }
    
    public Response removeItem(Item item) {
        items.remove(item.getId());

        return Response.OK;
    }
    //#endregion
}
