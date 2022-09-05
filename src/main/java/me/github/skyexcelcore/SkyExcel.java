package me.github.skyexcelcore;


import me.github.skyexcelcore.annotation.Adjust;

import me.github.skyexcelcore.annotation.Registerclass;
import me.github.skyexcelcore.command.abcd;
import me.github.skyexcelcore.command.test;
import me.github.skyexcelcore.customer.Address;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.util.Arrays;


public class SkyExcel extends JavaPlugin {

    public static SkyExcel plugin;

    public static Plugin newPlugin;


    @Override
    public void onEnable() {

        super.onEnable();
        plugin = this;
        new Registerclass(new test(), this);
        new Registerclass(new abcd(), this);

        Address address = new Address();
        if (!address.Equal(0, "survivalgame.n-e.kr")) {
            plugin.getServer().getPluginManager().disablePlugin(this);
        }
    }

    public static void setNewPlugin(Plugin newPlugin) {
        SkyExcel.newPlugin = newPlugin;
    }

    public static Plugin getNewPlugin() {
        return newPlugin;
    }

    public static SkyExcel getPlugin() {
        return plugin;
    }



    public static void RegisterEvents(Plugin plugin, Listener[] listeners) {
        PluginManager pm = Bukkit.getPluginManager();
        Arrays.stream(listeners).forEach(listener ->
                pm.registerEvents(listener, plugin));
    }

}
