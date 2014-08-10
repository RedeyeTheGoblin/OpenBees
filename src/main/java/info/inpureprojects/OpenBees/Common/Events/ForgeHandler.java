package info.inpureprojects.OpenBees.Common.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.ISpecies;
import info.inpureprojects.OpenBees.API.Common.Bees.IBeeManager;
import info.inpureprojects.OpenBees.API.Common.Tools.ScoopItem;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;
import java.util.Random;

/**
 * Created by den on 8/8/2014.
 */
public class ForgeHandler {

    @SubscribeEvent
    public void onBlockBroken(BlockEvent.HarvestDropsEvent evt) {
        if (OpenBeesAPI.getAPI().getCommonAPI().blocks.beehive.isEqual(evt.block, evt.blockMetadata)) {
            if (evt.harvester.getCurrentEquippedItem() == null) {
                return;
            }
            if (!evt.harvester.getCurrentEquippedItem().getItem().getClass().isAnnotationPresent(ScoopItem.class)) {
                return;
            }
            List<ISpecies> potential = OpenBeesAPI.getAPI().getCommonAPI().beeManager.getSpeciesForBiome(evt.world.getBiomeGenForCoords(evt.x, evt.z));
            if (potential.isEmpty()) {
                return;
            }
            Random r = new Random();
            int index = r.nextInt(potential.size());
            ItemStack princess = OpenBeesAPI.getAPI().getCommonAPI().beeManager.getBeeBySpecies(potential.get(index), IBeeManager.Type.PRINCESS);
            ItemStack drone = OpenBeesAPI.getAPI().getCommonAPI().beeManager.getBeeBySpecies(potential.get(index), IBeeManager.Type.DRONE);
            int drones = r.nextInt(4);
            if (drones == 0) {
                drones++;
            }
            int princesses = r.nextInt(evt.fortuneLevel + 1);
            if (princesses == 0) {
                princesses++;
            }
            drone.stackSize = drones;
            princess.stackSize = princesses;
            evt.drops.add(princess);
            evt.drops.add(drone);
        }
    }
}
