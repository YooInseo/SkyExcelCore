package me.github.skyexcelcore.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class test implements CommandExecutor {


    private long time = 0;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //time add <Day> <Hour> <Minute> <Second>
        //time add day <Day>
        //time add hour <hour>

        Player player = (Player) sender;

        switch (args[0]) {
            case "add":
                if (args.length == 3) {
                    switch (args[1]) {
                        case "day":
                            long day = Long.parseLong(args[2]);
                            time = (long) (day * 3.6e+6);

                            long Hour = (long) (time / 3.6e+6);

                            break;
                    }
                }

                break;
        }


        return false;
    }
}
