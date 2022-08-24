package me.github.skyexcelcore;


import me.github.skyexcelcore.command.test;
import org.bukkit.plugin.java.JavaPlugin;


public class SkyExcel extends JavaPlugin {

    public static SkyExcel plugin;

    @Override
    public void onEnable() {
        plugin = this;
        super.onEnable();
        getCommand("test").setExecutor(new test());
    }



    public static SkyExcel getPlugin() {
        return plugin;
    }

}
