package OpenBees.init;

import OpenBees.OpenBees;
import OpenBees.genetics.alleleHandlers.alleleBooleanHandler;
import OpenBees.genetics.alleleHandlers.alleleClimateHandler;
import OpenBees.genetics.alleleHandlers.alleleEffectHandler;
import OpenBees.genetics.alleleHandlers.alleleFlowerHandler;
import OpenBees.genetics.alleleHandlers.climateHandlers.alleleClimateTemperate;
import OpenBees.genetics.alleleHandlers.climateHandlers.climateDefinitions.climateDefinition;
import OpenBees.genetics.alleles.*;
import OpenBees.handler.beeHandler;
import OpenBees.utility.logHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;

public class modAlleles {

    public static void init()
    {

        for (alleleLifespan.Lifespans life : alleleLifespan.Lifespans.values())
        {
            OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleLifespan(life));
        }

        for (alleleWorkspeed.Speeds speed : alleleWorkspeed.Speeds.values())
        {
            OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleWorkspeed(speed));
        }

        for (alleleTerritory.territoryMods territory : alleleTerritory.territoryMods.values())
        {
            OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleTerritory(territory));
        }

        for (allelePollination.PollinationMods pollination : allelePollination.PollinationMods.values())
        {
            OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new allelePollination(pollination));
        }

        for (flowersEnum flower : flowersEnum.values())
        {
            OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleFlowerHandler("Flower." + flower.toString(), flower.getBlock(), flower.getMeta()));
        }

        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleFlowerStone());

        for (BiomeDictionary.Type bType : BiomeDictionary.Type.values())
        {
            OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleClimateHandler("Climate." + bType.toString(), new climateDefinition(bType.toString(), bType)));
        }
        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleClimateTemperate("Climate.TEMPERATE"));

        for (alleleFertility.fertilityMods fertile : alleleFertility.fertilityMods.values())
        {
            OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleFertility(fertile));
        }

        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleEffectHandler("Effect.NONE") {
            @Override
            public void doEffect(World world, int x, int y, int z, ItemStack bee)
            {

            }
        });

        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleBooleanHandler("Nocturnal.FALSE", false));
        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleBooleanHandler("Nocturnal.TRUE", true));

        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleBooleanHandler("Cave.FALSE", false));
        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleBooleanHandler("Cave.TRUE", true));

        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleBooleanHandler("Rain.FALSE", false));
        OpenBees.coreBeeHandler.getAlleleHandler().registerAllele(new alleleBooleanHandler("Rain.TRUE", true));
    }
}
