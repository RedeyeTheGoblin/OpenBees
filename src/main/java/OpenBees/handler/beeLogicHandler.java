package OpenBees.handler;

import OpenBees.OpenBees;
import OpenBees.block.modifierBlock;
import OpenBees.enums.typeEnum;
import OpenBees.genetics.*;
import OpenBees.item.interfaces.IFrameItem;
import OpenBees.utility.beeHelper;
import OpenBees.utility.logHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;

public class beeLogicHandler implements IBeeLogic
{
    public static boolean recessiveCanTriggerMutations = true;

    @Override
    public ItemStack combine(IBeeKeepingTile tile)
    {
        IBee princess = tile.getQueen();

        IBee drone = tile.getDrone();

        princess.setMate(drone);

        float lifeModifier = 0.0f;
        for (IFrameItem i : tile.getFrames())
        {
            lifeModifier += i.getLifespanModifier();
        }

        for (modifierBlock block : OpenBees.coreBeeHandler.getModifierBlocksNear(tile))
        {
            lifeModifier += block.getLifespanModifier();
        }

        princess.setLifeTicks(Math.round(princess.getLifeTicks() * lifeModifier));

        ItemStack queen = typeEnum.Types.QUEEN.createStack() ;
        queen.setTagCompound(princess.getNBT());

        return queen;

    }

    @Override
    public ItemStack produceOffspring(IBeeKeepingTile tile, List<IFrameItem> allModifiers, boolean princess)
    {
        ItemStack base;
        NBTTagCompound newBee;
        Random rand = new Random();
        IBee queen = tile.getQueen();
        beeMutation selectedMutation = null;

        //Check if there is actually a mate attached to the queen to prevent crashes.
        if (queen.getMate() == null)
        {
            queen.setMate(queen);
        }

        //Get possible mutations
        List<beeMutation> potentialMutations = new ArrayList();
        logHelper.info("queen: " + queen.getDominantGenome().getSpecies().toString() + ", drone: " + queen.getMate().getDominantGenome().getSpecies().toString());
        potentialMutations.addAll(OpenBees.coreBeeHandler.getMutations(queen.getDominantGenome().getSpecies(), queen.getMate().getDominantGenome().getSpecies()));
        if (recessiveCanTriggerMutations)
        {
            potentialMutations.addAll(OpenBees.coreBeeHandler.getMutations(queen.getRecessiveGenome().getSpecies(), queen.getMate().getRecessiveGenome().getSpecies()));
        }

        if (!potentialMutations.isEmpty())
        {
            //If we have more than one possible mutation, randomly pick one to win.
            int selectMutation;
            if (potentialMutations.size() == 1)
            {
                selectMutation = 0;
            } else
            {
                selectMutation = rand.nextInt(potentialMutations.size() -1);
            }

            beeMutation mutation = potentialMutations.get(selectMutation);
            float chance = mutation.getChance();

            //Check if frames can modify the mutation chance.
            for (IFrameItem frame : allModifiers)
            {
                chance = chance + (chance * frame.getMutationModifier());
            }

            //Check to see if we make the mutation chance threshold
            float mutationRoll = rand.nextFloat();
            if (mutationRoll <= chance)
            {
                selectedMutation = mutation;
            }
        }

        Map<Allele.AlleleTypes, punnettSquare> map = punnettSquare.getGeneticPotentials(queen);
        HashMap<Allele.AlleleTypes, Allele> dominantAlleles = new HashMap();
        HashMap<Allele.AlleleTypes, Allele> recessiveAlleles = new HashMap();

        for (Allele.AlleleTypes aTypes : map.keySet())
        {
            int roll = rand.nextInt(map.get(aTypes).getPotential().size() - 1);

            dominantAlleles.put(aTypes, (Allele) map.get(aTypes).getPotential().get(roll)[0]);
            recessiveAlleles.put(aTypes, (Allele) map.get(aTypes).getPotential().get(roll)[1]);
        }

        punnettSquare speciesCheck;
        if (selectedMutation == null)
        {
            speciesCheck = new punnettSquare(new Object[]{queen.getDominantGenome().getSpecies(), queen.getRecessiveGenome().getSpecies()},new Object[]{queen.getMate().getDominantGenome().getSpecies(), queen.getMate().getRecessiveGenome().getSpecies()});
        } else
        {
            speciesCheck = new punnettSquare(new Object[]{queen.getDominantGenome().getSpecies(),selectedMutation.getOutcome()}, new Object[]{queen.getMate().getDominantGenome().getSpecies(),selectedMutation.getOutcome()});
        }

        int rollForSpecies = rand.nextInt(speciesCheck.getPotential().size() - 1);
        ISpecies dominantSpecies = (ISpecies) speciesCheck.getPotential().get(rollForSpecies)[0];
        ISpecies recessiveSpecies = (ISpecies) speciesCheck.getPotential().get(rollForSpecies)[1];
        newBee = OpenBees.coreBeeHelper.generateGenome(dominantSpecies, recessiveSpecies, dominantAlleles, recessiveAlleles, dominantSpecies != recessiveSpecies, beeHelper.generateNewLifeFlag, null);
        if (princess)
        {
            base = typeEnum.Types.PRINCESS.createStack();
        } else
        {
            base = typeEnum.Types.DRONE.createStack();
        }
        base.setTagCompound(newBee);
        return base;
    }

    @Override
    public List<ItemStack> produceItemsOnTick(IBeeKeepingTile tile)
    {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        Random rand = new Random();
        for (beeProduct product : tile.getQueen().getDominantGenome().getSpecies().getPotentialProducts())
        {
            float odds = rand.nextFloat();
            if (odds <= product.getChance())
            {
                stacks.add(product.getStack().copy());
            }
        }
        return stacks;
    }
}
