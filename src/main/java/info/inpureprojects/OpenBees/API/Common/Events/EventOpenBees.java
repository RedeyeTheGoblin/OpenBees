package info.inpureprojects.OpenBees.API.Common.Events;

import info.inpureprojects.OpenBees.API.IOpenBeesAPI;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;

/**
 * Created by den on 8/6/2014.
 */
public class EventOpenBees {

    protected IOpenBeesAPI api;

    public EventOpenBees() {
        api = OpenBeesAPI.getAPI();
    }

    public IOpenBeesAPI getApi() {
        return api;
    }
}
