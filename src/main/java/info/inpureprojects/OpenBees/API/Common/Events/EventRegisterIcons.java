package info.inpureprojects.OpenBees.API.Common.Events;

import info.inpureprojects.OpenBees.API.Client.Managers.IIconManager;

/**
 * Created by den on 8/6/2014.
 */
public class EventRegisterIcons extends EventOpenBees {

    public IIconManager getIconManager() {
        return this.api.getClientAPI().icons;
    }

}
