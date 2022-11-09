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
        saveDefaultConfig();
        setupChunkController();
        setupCommand(); 
    }

    private void setupChunkController() {
        chunkController = new ChunkController(this);
        val config = getConfig();
        chunkController.setEnabled(config.getBoolean("enabled"));
    }

    private void setupCommand() {
        val command = getCommand("everWinter"); 
        command.setExecutor(new Executor(chunkController));
        command.setTabCompleter(new Completer());
    }

    @Override
    public void onDisable() {
        updateConfig();
        deinitChunkController();
    }

    private void updateConfig() {
        val config = getConfig();
        config.set("enabled", chunkController.isEnabled());
        saveConfig();
    }

    private void deinitChunkController() {
        chunkController.setEnabled(false);
        chunkController = null;
    }
}
