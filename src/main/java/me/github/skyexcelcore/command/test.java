package me.github.skyexcelcore.command;

import me.github.skyexcelcore.annotation.Adjust;
import org.bukkit.entity.Player;

@Adjust(command = "Hi")
public class test {

    @Adjust(args = "first", tab = true) //Hi test
    public void test(Player sender, String[] args) {
        sender.sendMessage("test");
    }

    @Adjust(args = "multifirst", tab = true) //Hi test
    public void atest(Player sender, String[] args) {
        System.out.println("test" + args[1]);
    }


    @Adjust(args = "other", tab = true) //Hi test
    public void inttest(Player sender, String[] args) {

    }

    @Adjust(args = "hi", parameter = 2, tab = true) //Hi test
    public void asdf(Player sender, String[] args) {
        sender.sendMessage("Test");
    }
}
