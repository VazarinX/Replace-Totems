package DisableTotems;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;
import java.util.Random;

public class EvokerLootModifier extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register event listener
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("EvokerLootModifier has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("EvokerLootModifier has been disabled!");
    }

    @EventHandler
    public void onEvokerDeath(EntityDeathEvent event) {
        // Check if the entity is an Evoker
        if (event.getEntity().getType() == org.bukkit.entity.EntityType.EVOKER) {
            // Remove Totem of Undying from loot
            Iterator<ItemStack> iterator = event.getDrops().iterator();
            while (iterator.hasNext()) {
                ItemStack item = iterator.next();
                if (item.getType() == Material.TOTEM_OF_UNDYING) {
                    iterator.remove();
                }
            }

            // Add 2-4 Gold Ingots to loot
            Random random = new Random();
            int goldAmount = 2 + random.nextInt(3); // 2 to 4 gold ingots
            event.getDrops().add(new ItemStack(Material.GOLD_INGOT, goldAmount));
        }
    }
}
