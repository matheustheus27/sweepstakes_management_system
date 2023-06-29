package Users.Objects;

import Items.Objects.Item;
import Response.Response;
import Sweepstakes.Objects.Sweepstake;

public class Manager extends User {
    public Manager(String username, String name, String password) {
        super(username, name, password, Type.MANAGER);
    }

    public Response analizeSweepstake(Sweepstake sweepstake, boolean auth) {

        if(sweepstake.getStatus() == Sweepstake.Status.PENDING) {
            if(auth) {
                sweepstake.setStatus(Sweepstake.Status.IN_PROGRESS);

            } else {
                sweepstake.setStatus(Sweepstake.Status.CANCELLED);
            }

            return Response.OK;
        }

        return Response.UNAUTHORIZED;
    }

    public Response analizeItem(Item item, boolean auth) {
        if(item.getStatus() == Item.Status.PENDING) {
            if(auth) {
                item.setStatus(Item.Status.AUTHORIZED);

            } else {
                item.setStatus(Item.Status.UNAUTHORIZED);
            }

            return Response.OK;
        }

        return Response.UNAUTHORIZED;
    }

    
}
