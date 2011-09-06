package za.dats.bukkit.memorystone;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Event.Type;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.event.screen.ScreenListener;
import org.getspout.spoutapi.inventory.CraftingInventory;

import sun.security.action.GetLongAction;

import za.dats.bukkit.memorystone.economy.EconomyManager;
import za.dats.bukkit.memorystone.ui.SpoutLocationPopupManager;
import za.dats.bukkit.memorystone.util.StructureManager;

public class MemoryStonePlugin extends JavaPlugin {
    private PluginDescriptionFile pdf;
    private PluginManager pm;
    private StructureManager structureManager = new StructureManager(this, "[MemoryStone] ");
    private MemoryStoneManager memoryStoneManager = new MemoryStoneManager(this);
    private CompassManager compassManager = new CompassManager(this);
    private SpoutLocationPopupManager spoutLocationPopupManager = new SpoutLocationPopupManager(this);
    private EconomyManager economyManager = new EconomyManager();
    private static MemoryStonePlugin instance;

    public void onDisable() {
    }

    public void info(String log) {
	getServer().getLogger().info("[MemoryStone] "+log);
    }
    
    public void warn(String log) {
	getServer().getLogger().warning("[MemoryStone] "+log);
    }
    
    
    public void onEnable() {
	instance = this;
	Utility.init(this);
	Config.init(this);
	pm = getServer().getPluginManager();
	pdf = getDescription();

	info(pdf.getName() + " version " + pdf.getVersion() + " is enabled!");
	
	economyManager.loadEconomy();
	
	structureManager.addStructureListener(memoryStoneManager);
	structureManager.registerEvents();
	
	memoryStoneManager.registerEvents();
	compassManager.registerEvents();
	spoutLocationPopupManager.registerEvents();
	
	
    }

    public boolean hasSpout() {
	if (pm.isPluginEnabled("Spout")) {
	    return true;
	}
	return false;
    }

    public StructureManager getStructureManager() {
        return structureManager;
    }

    public MemoryStoneManager getMemoryStoneManager() {
        return memoryStoneManager;
    }
    
    public CompassManager getCompassManager() {
	return compassManager;
    }

    public SpoutLocationPopupManager getSpoutLocationPopupManager() {
        return spoutLocationPopupManager;
    }
    
    public static MemoryStonePlugin getInstance() {
	return instance;
    }
    
    public EconomyManager getEconomyManager() {
	return economyManager;
    }
}
