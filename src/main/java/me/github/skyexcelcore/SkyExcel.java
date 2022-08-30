package me.github.skyexcelcore;


import me.github.skyexcelcore.annotation.Adjust;

import me.github.skyexcelcore.annotation.Registerclass;
import me.github.skyexcelcore.command.abcd;
import me.github.skyexcelcore.command.test;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;


public class SkyExcel extends JavaPlugin {

    public static SkyExcel plugin;

    @Override
    public void onEnable() {
        plugin = this;
        super.onEnable();

        new Registerclass(new test(), this);
        new Registerclass(new abcd(), this);


//        Reflections reflections = new Reflections("me.github.skyexcelcore.annotation");
//
//        for (Class<?> cl : reflections.getTypesAnnotatedWith(Adjust.class)) {
//            Adjust findable = cl.getAnnotation(Adjust.class);
//            System.out.printf("Found class: %s, with meta name: %s%n",
//                    cl.getSimpleName(), findable.command());
//        }

//            Set<Class<?>> annotated =
//                    ref.getTypesAnnotatedWith(Adjust.class);
//



    }


    public static SkyExcel getPlugin() {
        return plugin;
    }

}
