package me.github.skyexcelcore.annotation;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Registerclass implements CommandExecutor, TabCompleter {

    private Plugin plugin;
    private Object clazz;
    private String label;

    private List<Parameter> tabs = new ArrayList<>();


    public Registerclass(Object clazz, Plugin plugin) {
        this.plugin = plugin;
        this.clazz = clazz;
        registerClass(clazz.getClass());
        plugin.getServer().getPluginCommand(label).setExecutor(this);
        plugin.getServer().getPluginCommand(label).setTabCompleter(this);
    }


    public void registerClass(Class clazz) {
        Adjust adjustclass = (Adjust) clazz.getAnnotation(Adjust.class);

        if (adjustclass != null) {
            this.label = adjustclass.command();
            for (Method method : clazz.getMethods()) {
                Annotation adjustcclazz = method.getAnnotation(Adjust.class);
                if (adjustcclazz instanceof Adjust) {
                    Adjust adjust = (Adjust) adjustcclazz;

                    if (adjust != null) {
                        this.tabs.add(new Parameter(adjust.parameter(), adjust.tab(), adjust.args()));

                    } else {
                        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " Can't find the adjust annotation anywhere");
                    }
                }
            }
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + " Can't find the adjust annotation anywhere in class : " + clazz.getName());
        }
    }

    public void invoke(Class clazz, Player player, String arg, String[] args) {
        for (Method method : clazz.getMethods()) {
            Annotation adjustcclazz = method.getAnnotation(Adjust.class);

            if (adjustcclazz instanceof Adjust) {
                try {
                    if(((Adjust) adjustcclazz).parameter() == args.length - 1){
                        if (args[((Adjust) adjustcclazz).parameter()].equalsIgnoreCase(((Adjust) adjustcclazz).args())) {
                            Class<?> newclazz = Class.forName(clazz.getName());
                            Object object = newclazz.newInstance();
                            method.invoke(object, player, args);
                        }
                    } else{
                        if (args.length > ((Adjust) adjustcclazz).parameter()) {
                            if(args[((Adjust) adjustcclazz).parameter()].equalsIgnoreCase(((Adjust) adjustcclazz).args())){
                                Class<?> newclazz = Class.forName(clazz.getName());
                                Object object = newclazz.newInstance();
                                method.invoke(object, player, args);
                            }
                        }
                    }

                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String
            label, @NotNull String[] args) {

        if (sender instanceof Player) {
            invoke(clazz.getClass(), (Player) sender, args[args.length - 1], args);

        } else {
            sender.sendMessage(ChatColor.RED + "Did you register this command on your Plugin? {console=true} ");
        }
        return false;
    }


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String
            label, @NotNull String[] args) {
        ArrayList<String> tabs = new ArrayList<>();
        if (label.equalsIgnoreCase(label)) {
            if (tabs != null) {
                for (Parameter tabclazz : this.tabs) {
                    if (tabclazz.parameter == args.length - 1) {
                        if (tabclazz.tab) {
                            tabs.add(tabclazz.args);
                        }
                    }
                }
            }
            return tabs;
        }
        return null;
    }

    public class Parameter {
        int parameter;
        boolean tab;
        String args;

        public Parameter(int parameter, boolean tab, String args) {
            this.parameter = parameter;
            this.tab = tab;
            this.args = args;
        }
    }
}
