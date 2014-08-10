package info.inpureprojects.OpenBees.Common;

import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import info.inpureprojects.OpenBees.API.Common.Bees.Climate.ClimateDefinition;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.*;
import info.inpureprojects.OpenBees.API.Common.Bees.Genetics.Alleles.Generic.AlleleBoolean;
import info.inpureprojects.OpenBees.API.Common.BlockWrapper;
import info.inpureprojects.OpenBees.API.Common.Events.EventRegisterAlleles;
import info.inpureprojects.OpenBees.API.Common.Events.EventRegisterBees;
import info.inpureprojects.OpenBees.API.Common.ItemWrapper;
import info.inpureprojects.OpenBees.API.OpenBeesAPI;
import info.inpureprojects.OpenBees.API.modInfo;
import info.inpureprojects.OpenBees.Client.Gui.GuiHandler;
import info.inpureprojects.OpenBees.Common.Blocks.BlockHive;
import info.inpureprojects.OpenBees.Common.Blocks.BlockMachine;
import info.inpureprojects.OpenBees.Common.Blocks.Tiles.TileApiary;
import info.inpureprojects.OpenBees.Common.Config.ConfigHolder;
import info.inpureprojects.OpenBees.Common.Config.JsonBee;
import info.inpureprojects.OpenBees.Common.Events.EventSetupBlocks;
import info.inpureprojects.OpenBees.Common.Events.EventSetupItems;
import info.inpureprojects.OpenBees.Common.Events.ForgeHandler;
import info.inpureprojects.OpenBees.Common.Genetics.*;
import info.inpureprojects.OpenBees.Common.Items.ItemBee;
import info.inpureprojects.OpenBees.Common.Items.ItemComb;
import info.inpureprojects.OpenBees.Common.Items.ItemScoop;
import info.inpureprojects.OpenBees.Common.Managers.SpeciesImpl;
import info.inpureprojects.OpenBees.OpenBees;
import info.inpureprojects.OpenBees.Proxy.Proxy;
import info.inpureprojects.core.API.IINpureSubmodule;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;
import java.util.HashMap;

/**
 * Created by den on 8/6/2014.
 */
public class ModuleOpenBees implements IINpureSubmodule {

    @SidedProxy(serverSide = modInfo.proxyCommon, clientSide = modInfo.proxyClient)
    public static Proxy proxy;
    public static ConfigHolder config;

    @Override
    public void pre(File configFolder) {
        proxy.setupAPI();
        proxy.setupConfig(configFolder);
        OpenBeesAPI.getAPI().getCommonAPI().events.register(this);
    }

    @Override
    public void init() {
        OpenBeesAPI.getAPI().getCommonAPI().events.post(new EventRegisterAlleles());
    }

    @Override
    public void post() {
        OpenBeesAPI.getAPI().getCommonAPI().events.post(new EventRegisterBees());
        MinecraftForge.EVENT_BUS.register(new ForgeHandler());
    }

    @Subscribe
    public void itemInit(EventSetupItems evt) {
        evt.getApi().getCommonAPI().items.drone = new ItemWrapper(new ItemBee("openbees.bee.drone", 64, "drone"), 0);
        evt.getApi().getCommonAPI().items.princess = new ItemWrapper(new ItemBee("openbees.bee.princess", 1, "princess"), 0);
        evt.getApi().getCommonAPI().items.queen = new ItemWrapper(new ItemBee("openbees.bee.queen", 1, "queen"), 0);
        evt.getApi().getCommonAPI().items.base_comb = new ItemComb("openbees.comb", 64);
        evt.getApi().getCommonAPI().items.honey_comb = new ItemWrapper(evt.getApi().getCommonAPI().items.base_comb, 0);
        evt.getApi().getCommonAPI().items.scoop_wood = new ItemWrapper(new ItemScoop("openbees.scoop.0", Item.ToolMaterial.WOOD, "scoop"), 0);
        evt.getApi().getCommonAPI().items.scoop_iron = new ItemWrapper(new ItemScoop("openbees.scoop.1", Item.ToolMaterial.IRON, "scoop_iron"), 0);
        evt.getApi().getCommonAPI().items.scoop_diamond = new ItemWrapper(new ItemScoop("openbees.scoop.2", Item.ToolMaterial.EMERALD, "scoop_diamond"), 0);
    }

    @Subscribe
    public void blockInit(EventSetupBlocks evt) {
        GameRegistry.registerTileEntity(TileApiary.class, TileApiary.class.getName());
        evt.getApi().getCommonAPI().blocks.apiary = new BlockWrapper(new BlockMachine("openbees.apiary"), 0);
        NetworkRegistry.INSTANCE.registerGuiHandler(OpenBees.instance, new GuiHandler());
        evt.getApi().getCommonAPI().blocks.beehive = new BlockWrapper(new BlockHive("openbees.hive"), 0);
    }

    @Subscribe
    public void beeInit(EventRegisterBees evt) {
        try {
            proxy.print("Starting bee setup...");
            for (ContentEnums.Bees b : ContentEnums.Bees.values()) {
                HashMap<Allele.AlleleTypes, Allele> map = new HashMap();
                for (Allele.AlleleTypes t : Allele.AlleleTypes.values()) {
                    Allele a = OpenBeesAPI.getAPI().getCommonAPI().beeManager.getAlleleManager().getAlleleByTag(b.getGenome()[t.ordinal()]);
                    map.put(t, a);
                }
                evt.getBeeManager().registerSpecies(new SpeciesImpl(b.toString(), b.getUnloc(), b.getBodyColor(), b.getOutlineColor(), map, b.getProducts()));
                evt.getBeeManager().registerBeeForHive(evt.getBeeManager().getSpeciesByTag(b.toString()), b.getSpawnIn());
                JsonBee j = new JsonBee(b.getUnloc(), b.getBodyColor(), b.getOutlineColor(), b.getGenome(), JsonBee.convert(b.getProducts()), b.getSpawnIn());
                String jb = JsonBee.json.toJson(j);
                proxy.print(jb);
            }
            proxy.print("Bee setup complete!");
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Subscribe
    public void alleleInit(EventRegisterAlleles evt) {
        proxy.print("Starting allele setup...");
        for (AlleleLifespan.Lifespans l : AlleleLifespan.Lifespans.values()) {
            evt.getAlleleManager().registerAllele(new AlleleLifespan(l));
        }
        for (AlleleWorkspeed.Speeds s : AlleleWorkspeed.Speeds.values()) {
            evt.getAlleleManager().registerAllele(new AlleleWorkspeed(s));
        }
        for (AlleleTerritory.TerritoryMods m : AlleleTerritory.TerritoryMods.values()) {
            evt.getAlleleManager().registerAllele(new AlleleTerritory(m));
        }
        for (AllelePollination.PollinationMods p : AllelePollination.PollinationMods.values()) {
            evt.getAlleleManager().registerAllele(new AllelePollination(p));
        }
        for (EnumFlowers f : EnumFlowers.values()) {
            evt.getAlleleManager().registerAllele(new AlleleFlower(modInfo.modid + "|Flower" + f.toString(), f.getBlock(), f.getMeta()));
        }
        evt.getAlleleManager().registerAllele(new AlleleFlowerStone());
        for (BiomeDictionary.Type t : BiomeDictionary.Type.values()) {
            evt.getAlleleManager().registerAllele(new AlleleClimate(modInfo.modid + "|Climate" + t.toString(), new ClimateDefinition(t.toString(), t)));
        }
        for (AlleleFertility.FertilityMods m : AlleleFertility.FertilityMods.values()) {
            evt.getAlleleManager().registerAllele(new AlleleFertility(m));
        }
        evt.getAlleleManager().registerAllele(new AlleleEffect(modInfo.modid + "|EffectNONE") {
            @Override
            public void doEffect(World world, int x, int y, int z, ItemStack bee) {
            }
        });
        evt.getAlleleManager().registerAllele(new AlleleBoolean("openbees|BooleanTRUE", true));
        evt.getAlleleManager().registerAllele(new AlleleBoolean("openbees|BooleanFALSE", false));
        evt.getAlleleManager().registerAllele(new AlleleClimateTemperate("openbees|ClimateTEMPERATE"));
        proxy.print("Allele setup complete!");
    }
}
