package OpenBees.init;

import OpenBees.OpenBees;
import OpenBees.block.blockHive;
import OpenBees.block.blockMachine;
import OpenBees.block.tileEntities.tileExtractor;
import OpenBees.utility.blockWrapper;
import OpenBees.block.itemBlockMachine;
import OpenBees.block.tileEntities.tileApiary;
import OpenBees.info.modInfo;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(modInfo.modid)
public class modBlocks {

        public static void init() {
        GameRegistry.registerTileEntity(tileApiary.class, tileApiary.class.getName());
        GameRegistry.registerTileEntity(tileExtractor.class, tileExtractor.class.getName());

        OpenBees.blocks.machine = new blockMachine("openbees.machine");
        GameRegistry.registerBlock(OpenBees.blocks.machine, itemBlockMachine.class, OpenBees.blocks.machine.getUnlocalizedName());
        OpenBees.blocks.apiary = new blockWrapper(OpenBees.blocks.machine, 0);
        OpenBees.blocks.extractor = new blockWrapper(OpenBees.blocks.machine, 1);

        OpenBees.blocks.beehive = new blockWrapper( new blockHive("openbees.hive"), 0);
        GameRegistry.registerBlock(OpenBees.blocks.beehive.getBlock(), OpenBees.blocks.beehive.getBlock().getUnlocalizedName());

    }
}


