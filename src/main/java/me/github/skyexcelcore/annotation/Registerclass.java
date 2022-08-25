package me.github.skyexcelcore.annotation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Registerclass implements CommandExecutor {

    private Plugin plugin;
    private Class clazz;
    private String label;
    private String[] args;

    public Registerclass(Class clazz,Plugin plugin) {
        this.plugin = plugin;
        this.clazz = clazz;
        registerClass(clazz);
        plugin.getServer().getPluginCommand(label).setExecutor(this);
    }


    public void registerClass(Class clazz){
        for(Field field : clazz.getDeclaredFields()){

            for(Adjust adjust: (Adjust[]) field.getAnnotations()){
                if(adjust != null){
                    this.label = adjust.command();
                    this.args = adjust.args();
                } else{
                    Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " you didn't write annotation in class " + clazz.getName());
                }
            }
        }
    }

    public boolean console(Class clazz){
        for(Field field : clazz.getDeclaredFields()){

            for(Adjust adjust: (Adjust[]) field.getAnnotations()){
                return adjust.console();
            }
        }
        return false;
    }

    public Method getMethod(Class clazz){
        for(Field field : clazz.getDeclaredFields()){
            for(Method adjust: field.getClass().getMethods()){
                return adjust;
            }
        }
        return null;
    }
    public int integer(Class clazz, int i){
        for(Field field : clazz.getDeclaredFields()){

            for(Adjust adjust: (Adjust[]) field.getAnnotations()){
                for(String args : adjust.args()){
                    int index = args.indexOf(adjust.args().length);

                    if(args.equalsIgnoreCase("int") || args.equalsIgnoreCase("integer")){

                        return i;
                    }
                }
            }
        }
        return -1;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {


        if(sender instanceof Player){
            if(args.length == this.args.length){
                integer(clazz,Integer.parseInt(args[0]));
                if(args[args.length - 1].equalsIgnoreCase(this.args[this.args.length - 1])){
                    getMethod(clazz);
                }
            }
        } else{
            if(console(clazz)){
                sender.sendMessage("This command send from the console!");
            }
            sender.sendMessage(ChatColor.RED + "Did you register this command on your Plugin? {console=true} ");
        }
        return false;
    }
}
