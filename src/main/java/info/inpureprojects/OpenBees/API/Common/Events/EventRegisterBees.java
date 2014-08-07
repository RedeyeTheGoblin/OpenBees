package info.inpureprojects.OpenBees.API.Common.Events;

import info.inpureprojects.OpenBees.API.Common.Bees.IBeeManager;

/**
 * Created by den on 8/6/2014.
 */
public class EventRegisterBees extends EventOpenBees {

    // Fired when it is ok to register your species.

    public IBeeManager getBeeManager() {
        return this.api.getCommonAPI().beeManager;
    }

}
