package Sweepstakes.Controllers;

import java.util.HashMap;
import java.util.Map;

import Response.Response;
import Sweepstakes.Objects.Sweepstake;
import Users.Objects.User;

public class SweepstakeController {
    //#region VARIABLES
    private Map<String, Sweepstake> sweepstakes;
    //#endregion
    
    //#region CONSTRUCTOR
    public SweepstakeController() {
        this.sweepstakes = new HashMap<>();
    }
    //#endregion

    //#region FUNCTIONS
    public void listSweepstakes() {
        for (Sweepstake sweepstake : sweepstakes.values()) {
            sweepstake.displayInformation();
        }
    }

    public void listSweepstakes(Sweepstake.Status status) {
        for (Sweepstake sweepstake : sweepstakes.values()) {
            if (sweepstake.getStatus().equals(status)) {
                sweepstake.displayInformation();
            }
        }
    }

    public void listSweepstakesPerUser(String cpf) {
        for (Sweepstake sweepstake : sweepstakes.values()) {
            if (sweepstake.getOwner().getId().equals(cpf)) {
                sweepstake.displayInformation();
            }
        }
    }

    public void listSweepstakesPerEntrant(String cpf) {
        for (Sweepstake sweepstake : sweepstakes.values()) {
            if (sweepstake.getEntrants().get(cpf) != null) {
                sweepstake.displayInformation();
            }
        }
    }

    public Sweepstake findSweepstake(String id) {
        return sweepstakes.get(id);
    }

    public Response registerSweepstake(Sweepstake sweepstake) {
        sweepstakes.put(sweepstake.getId(), sweepstake);

        return Response.OK;
    }
    
    public Response removeSweepstake(Sweepstake sweepstake) {
        sweepstakes.remove(sweepstake.getId());

        return Response.OK;
    }

    public Response registerEntrant(Sweepstake sweepstake, User entrant) {
        sweepstake.setEntrants(entrant);

        return Response.OK;
    }
    //#endregion
}
