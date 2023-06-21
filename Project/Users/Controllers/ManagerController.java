package Users.Controllers;

import Items.Objects.Item;
import Response.Response;
import Sweepstakes.Objects.Sweepstake;
import Users.Objects.Manager;

public class ManagerController {
    //#region VARIABLES
    private Manager user;
    //#endregion

    //#region CONSTRUCTOR
    public ManagerController(Manager user) {
        this.user = user;
    }
    //#endregion

    //#region FUNCTIONS
    public Response validateSweepstake(Sweepstake sweepstake, boolean auth) {
        return user.analizeSweepstake(sweepstake, auth);
    }

    public Response validateItem(Item item, boolean auth) {
        return user.analizeItem(item, auth);
    }
    //#endregion
}
