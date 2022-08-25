package me.github.skyexcelcore;


import me.github.skyexcelcore.annotation.Registerclass;
import me.github.skyexcelcore.command.abcd;
import me.github.skyexcelcore.command.test;
import org.bukkit.plugin.java.JavaPlugin;


public class SkyExcel extends JavaPlugin {

    public static SkyExcel plugin;

    @Override
    public void onEnable() {
        plugin = this;
        super.onEnable();

        new Registerclass(new test(),this);
        new Registerclass(new abcd(),this);
    }



    public static SkyExcel getPlugin() {
        return plugin;
    }

}
