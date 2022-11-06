package space.moontalk.mc.ever_winter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.val;

public class Completer implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command,
                                                @NotNull String lable, @NotNull String[] args) {
        if (args.length != 1)
            return Collections.emptyList();

        val arg      = args[0];
        val variants = new LinkedList<String>();

        variants.add("enable");
        variants.add("disable");
        variants.removeIf(v -> !v.toLowerCase().startsWith(arg.toLowerCase()));

        return variants;
    }
}
