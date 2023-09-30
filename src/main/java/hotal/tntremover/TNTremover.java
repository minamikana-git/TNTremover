package hotal.tntremover;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TNTremover extends JavaPlugin implements Listener {
    private FileConfiguration config;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        saveDefaultConfig(); // コンフィグファイルが存在しない場合、デフォルトのコンフィグを生成
        config = getConfig();
    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Material placedType = event.getBlockPlaced().getType();
        String worldName = event.getPlayer().getWorld().getName();

        if (config.contains("worlds." + worldName) && config.getBoolean("worlds." + worldName)) {
            if (placedType == Material.TNT_MINECART || placedType == Material.RESPAWN_ANCHOR
                    || placedType == Material.END_CRYSTAL || placedType == Material.TNT) {
                event.setCancelled(true);

                String playerName = event.getPlayer().getName();
                String blockName = placedType.name().toLowerCase().replace("_", "");

                String message = playerName + "が" + blockName + "を設置しようとしました！";
            }
        }
    }
}
