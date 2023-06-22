package Users.Controllers;

import Items.Objects.Item;
import Response.Response;
import Sweepstakes.Objects.Sweepstake;
import Users.Objects.Common;

public class CommonController {
    //#region VARIABLES
    private Common user;
    //#endregion

    //#region CONSTRUCTOR
    public CommonController(Common user) {
        this.user = user;
    }
    //#endregion

    //#region FUNCTIONS
    public Response joinSweepstake(Sweepstake sweepstake) {
        return user.joinSweepstake(sweepstake);
    }

    public void registerSweepstake(Sweepstake sweepstake) {
        user.registerSweepstake(sweepstake);
    }
    public void listSweepstakes() {
        user.listSweepstakes();
    }
    public Response makeSweepstake(String id) {
        return user.makeSweepstake(id);
    }
    public Response checkForSweepstake() {
        if(this.user.getSweepstakes().isEmpty()) {return Response.NOT_FOUND;}

        return Response.OK;
    }

    public void registerItem(Item item) {
        user.registerItem(item);
    }
    public void listItems() {
        user.listItems();
    }
    public void listItemsAuthorized() {
        user.listItems();
    }
    public void removeItem(Item item) {
        user.removeItem(item);
    }
    public Response checkForItems() {
        if(this.user.getItems().isEmpty()) {return Response.NOT_FOUND;}

        return Response.OK;
    }
    public Response checkForItemsAuthorized() {
        for(Item item: user.getItems().values()) {
            if(item.getStatus().equals(Item.Status.AUTHORIZED)){return Response.OK;}
        }

        return Response.NOT_FOUND;
    }
    //#endregion
}
