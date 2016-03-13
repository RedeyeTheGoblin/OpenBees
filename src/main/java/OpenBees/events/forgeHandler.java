package OpenBees.events;

import OpenBees.OpenBees;
import OpenBees.block.blockHive;
import OpenBees.enums.typeEnum;
import OpenBees.genetics.ISpecies;
import OpenBees.genetics.beeProduct;
import OpenBees.item.interfaces.scoopItem;
import OpenBees.utility.logHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;
import java.util.Random;

public class forgeHandler {

    @SubscribeEvent
    public void onBlockBroken(BlockEvent.HarvestDropsEvent event) {
        if (event.block instanceof blockHive) {
            if (event.harvester.getCurrentEquippedItem() == null) {
                return;
            }

            if (!event.harvester.getCurrentEquippedItem().getItem().getClass().isAnnotationPresent(scoopItem.class)) {
                return;
            }

            List<ISpecies> potential = OpenBees.coreBeeHandler.getSpeciesForBiome(event.world.getBiomeGenForCoords(event.x, event.y));

            if (potential.isEmpty()) {
                return;
            }

            Random rand = new Random();
            int index = rand.nextInt(potential.size());

            ItemStack princess = OpenBees.coreBeeHandler.getBeeBySpecies(potential.get(index), typeEnum.Types.PRINCESS);
            ItemStack drone = OpenBees.coreBeeHandler.getBeeBySpecies(potential.get(index), typeEnum.Types.DRONE);

            int drones = rand.nextInt(4);
            if (drones == 0) {
                drones++;
            }

            int princesses = rand.nextInt(event.fortuneLevel + 1);
            if (princesses == 0) {
                princesses++;
            }

            drone.stackSize = drones;
            princess.stackSize = princesses;

            event.drops.add(princess);
            event.drops.add(drone);

            for (beeProduct prod : potential.get(index).getPotentialProducts()) {
                float odds = rand.nextFloat();
                if (odds <= prod.getChance()) {
                    event.drops.add(prod.getStack().copy());
                }
            }
        }
    }


}
