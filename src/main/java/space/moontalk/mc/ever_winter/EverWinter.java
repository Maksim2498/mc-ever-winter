package space.moontalk.mc.ever_winter;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.val;

@Getter
public class EverWinter extends JavaPlugin {
    private @Nullable ChunkController chunkController;

    @Override
    public void onEnable() {
        chunkController = new ChunkController(this);
        setupCommand(); 
    }

    private void setupCommand() {
        val command = getCommand("everWinter"); 
        command.setExecutor(new Executor(chunkController));
        command.setTabCompleter(new Completer());
    }

    @Override
    public void onDisable() {
        chunkController.setEnabled(false);
        chunkController = null;
    }
}
