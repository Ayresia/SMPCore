package me.ayresia.smpcore.smpcore;

import me.ayresia.smpcore.smpcore.events.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SmpCore extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}