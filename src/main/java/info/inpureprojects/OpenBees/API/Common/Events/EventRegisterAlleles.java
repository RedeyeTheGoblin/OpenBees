package info.inpureprojects.OpenBees.API.Common.Events;

import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.IAlleleManager;

/**
 * Created by den on 8/6/2014.
 */
public class EventRegisterAlleles extends EventOpenBees {

    public EventRegisterAlleles() {
    }

    public IAlleleManager getAlleleManager() {
        return this.api.getCommonAPI().beeManager.getAlleleManager();
    }
}
