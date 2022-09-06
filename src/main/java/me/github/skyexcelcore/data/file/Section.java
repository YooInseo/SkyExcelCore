package me.github.skyexcelcore.data.file;

import me.github.skyexcelcore.data.time.Time;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

public class Section {
    private ConfigurationSection section;
    private Config config;

    public Section(Config config) {
        this.config = config;
    }

    public ConfigurationSection addArray(String path, String newpath) {

        this.section = config.getConfig().getConfigurationSection(path + "." + config.getConfig().getConfigurationSection(path).getKeys(false).size());

        if (config.getConfig().getConfigurationSection(path) != null) {
            if (section != null) {
                return section;
            }
        } else {
            return config.getConfig().createSection(section.getCurrentPath() + "." + newpath);
        }
        return null;
    }



    public void setLocation(String path, Location location){
        this.section = config.getConfig().getConfigurationSection(path);
        section.set(path + ".world",location.getWorld().getName());
        section.set(path + ".y",location.getY());
        section.set(path + ".x",location.getX());
        section.set(path + ".z",location.getZ());

    }
    public void setTime(String path, Time time){
        this.section = config.getConfig().getConfigurationSection(path);

        section.set(path,time.SECOND_IN_MILLIS());
    }

    public Time getTime(String path){
        this.section = config.getConfig().getConfigurationSection(path);
        return new Time(section.getLong(path));
    }

    public Location getLocation(String path){
        this.section = config.getConfig().getConfigurationSection(path);
        return new Location(
                Bukkit.getWorld(section.getString(path + ".world")),
                section.getDouble(path + ".x"),
                section.getDouble(path + ".y"),
                section.getDouble(path + ".z"));
    }


    public ConfigurationSection getSection() {
        return section;
    }
}
