package me.github.skyexcelcore.annotation;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class Registerclass implements CommandExecutor {

    private Plugin plugin;
    private Class clazz;
    public Registerclass(Plugin plugin) {
        this.plugin = plugin;


    }
    public void registerClass(Class clazz){
        this.clazz = clazz;
        findFields(clazz, Adjust.class);
        plugin.getServer().getPluginCommand("").setExecutor(this);
    }

    public static Set<Field> findFields(Class<?> classs, Class<? extends Adjust> ann) {
        Set<Field> set = new HashSet<>();
        Class<?> c = classs;
        while (c != null) {
            for (Method method : classs.getMethods()) {
                if (method.isAnnotationPresent(ann)) {

                }
            }
            c = c.getSuperclass();
        }
        return set;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){

        } else{
            sender.sendMessage(ChatColor.RED + "Did you register this command on your Plugin? {console=true} ");
        }

        return false;
    }
}
