package space.moontalk.mc.ever_winter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.val;

@Getter
@AllArgsConstructor
public class Executor implements CommandExecutor {
    private final @NotNull ChunkController chunkController;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, 
                             @NotNull String lable, @NotNull String[] args) {
        return switch (args.length) {
            case 0 -> {
                val message = String.format(
                    "§3Ever-winter§f mode is now %s.",
                    chunkController.isEnabled() ? "§aenabled§f"
                                                : "§cdisabled§f"
                );

                sender.sendMessage(message);

                yield true;
            }

            case 1 -> {
                val arg = args[0].toLowerCase();

                yield switch (arg) {
                    case "enable" -> {
                        if (chunkController.isEnabled()) {
                            sender.sendMessage("§cEver-winter mode is already enabled.");
                            yield true;
                        }

                        chunkController.setEnabled(true);
                        sender.sendMessage("§3Ever-winter§f mode is now §aenabled§f.");

                        yield true;
                    }

                    case "disable" -> {
                        if (!chunkController.isEnabled()) {
                            sender.sendMessage("§cEver-winter mode is already disabled.");
                            yield true;
                        }

                        chunkController.setEnabled(false);
                        sender.sendMessage("§3Ever-winter§f mode is now §cdisabled§f.");

                        yield true;
                    }

                    default -> false;
                };
            }

            default -> false;   
        };
    }
}
