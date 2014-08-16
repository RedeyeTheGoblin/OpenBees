package info.inpureprojects.OpenBees.API.Common;

import com.google.common.eventbus.EventBus;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeManager;

/**
 * Created by den on 8/6/2014.
 */
public class CommonAPI {
    public final EventBus events = new EventBus();
    //--------------------------------------------
    public final Items items = new Items();
    public final Blocks blocks = new Blocks();
    public final ModSettings settings = new ModSettings();
    //----------------------------------------------------
    public IBeeManager beeManager;
    public ICarpenterManager carpenterRecipes;

}
