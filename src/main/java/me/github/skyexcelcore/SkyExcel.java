package me.github.skyexcelcore;


import me.github.skyexcelcore.annotation.Registerclass;
import me.github.skyexcelcore.command.test;
import me.github.skyexcelcore.data.My;
import org.bukkit.plugin.java.JavaPlugin;


public class SkyExcel extends JavaPlugin {

    public static SkyExcel plugin;

    @Override
    public void onEnable() {
        plugin = this;
        super.onEnable();
        new Registerclass(new test().getClass(),this);
    }



    public static SkyExcel getPlugin() {
        return plugin;
    }

}
