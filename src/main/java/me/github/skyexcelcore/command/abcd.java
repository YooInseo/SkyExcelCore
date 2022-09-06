package me.github.skyexcelcore.command;

import me.github.skyexcelcore.SkyExcel;
import me.github.skyexcelcore.annotation.Adjust;
import me.github.skyexcelcore.annotation.Command;
import me.github.skyexcelcore.annotation.Registerclass;
import org.bukkit.entity.Player;

@Adjust(command = "abcd")
public class abcd implements Command {

    @Adjust(args = "test", tab = true) //Hi test
    public void test(Player sender) {
        System.out.println("test");
    }

    @Adjust(args = "int", parameter = 1) //Hi test
    public void inttest(Player sender, int value) {
        sender.sendMessage("Test" + value);
    }

    @Adjust(args = "hi", parameter = 2) //Hi test
    public void asdf(Player sender, int value) {
        sender.sendMessage("Test" + value);
    }

    @Override
    public void register() {
        new Registerclass(this, SkyExcel.newPlugin);
    }
}
