package me.ayresia.smpcore.smpcore;

import me.ayresia.smpcore.smpcore.events.PlayerListener;
import me.ayresia.smpcore.smpcore.items.Heart;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmpCore extends JavaPlugin {
    @Override
    public void onEnable() {
        Heart heart = new Heart(this);
        getServer().getPluginManager().registerEvents(new PlayerListener(heart), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}