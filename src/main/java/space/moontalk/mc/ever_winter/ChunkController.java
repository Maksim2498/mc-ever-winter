package space.moontalk.mc.ever_winter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.bukkit.Chunk;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.Getter;
import lombok.val;

public class ChunkController implements Listener {
    @Getter
    private final @NotNull Map<Long, ChunkRecord> records = new HashMap<>();

    @Getter
    private final @NotNull JavaPlugin plugin;

    private boolean enabled = false;

    public ChunkController(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
        register();
    }

    private void register() {
        val server  = plugin.getServer();
        val manager = server.getPluginManager();
        manager.registerEvents(this, plugin);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean val) {
        if (val == enabled)
            return;

        enabled = val;

        if (val) 
            addAllChunks();
        else
            removeAllChunks();
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onChunkLoad(@NotNull ChunkLoadEvent event) {
        if (!enabled)
            return;

        val chunk = event.getChunk();

        addChunk(chunk);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChunkUnload(@NotNull ChunkUnloadEvent event) {
        if (!enabled)
            return;
        
        val chunk = event.getChunk();

        removeChunk(chunk);
    }

    private void addAllChunks() {
        val server    = plugin.getServer();
        val scheduler = server.getScheduler();

        for (val world : server.getWorlds()) {
            val env = world.getEnvironment();

            if (env == Environment.NETHER || env == Environment.CUSTOM)
                continue;

            val chunks = world.getLoadedChunks();
            val status = new Object() { 
                int      i = 0;
                Runnable task;
            };

            status.task = () -> {
                if (status.i == chunks.length)
                    return;

                addChunk(chunks[status.i]);
                status.i++;
                scheduler.scheduleSyncDelayedTask(plugin, status.task, 1);
            };

            scheduler.scheduleSyncDelayedTask(plugin, status.task);
        }
    }

    private void addChunk(@NotNull Chunk chunk) {
        val world        = chunk.getWorld(); 
        val blockRecords = new LinkedList<BlockRecord>(); 

        for (int x = 0; x < 16; x += 4)
            for (int z = 0; z < 16; z += 4)
                for (int y = world.getMinHeight(); y < world.getMaxHeight(); y += 4) {
                    val block    = chunk.getBlock(x, y, z);
                    val biome    = block.getBiome();
                    val newBiome = evalNewBiome(biome);

                    if (newBiome == null)
                        continue;

                    block.setBiome(newBiome);
                    blockRecords.add(new BlockRecord(block, biome));
                }

        records.put(chunk.getChunkKey(), new ChunkRecord(chunk, blockRecords));
    }

    private @Nullable Biome evalNewBiome(@NotNull Biome biome) {
        return switch (biome) {
            case BAMBOO_JUNGLE            -> Biome.SNOWY_TAIGA;
            case BEACH                    -> Biome.SNOWY_BEACH;
            case BIRCH_FOREST             -> Biome.SNOWY_TAIGA;
            case DARK_FOREST              -> Biome.SNOWY_TAIGA;
            case DESERT                   -> Biome.SNOWY_PLAINS;
            case DEEP_COLD_OCEAN          -> Biome.DEEP_FROZEN_OCEAN;
            case DEEP_LUKEWARM_OCEAN      -> Biome.DEEP_FROZEN_OCEAN;
            case DEEP_OCEAN               -> Biome.DEEP_FROZEN_OCEAN;
            case ERODED_BADLANDS          -> Biome.SNOWY_PLAINS;
            case FLOWER_FOREST            -> Biome.SNOWY_TAIGA;
            case FOREST                   -> Biome.SNOWY_TAIGA;
            case JAGGED_PEAKS             -> Biome.FROZEN_PEAKS;
            case JUNGLE                   -> Biome.SNOWY_TAIGA;
            case MANGROVE_SWAMP           -> Biome.SNOWY_TAIGA;
            case COLD_OCEAN               -> Biome.FROZEN_OCEAN;
            case MEADOW                   -> Biome.SNOWY_PLAINS;
            case OCEAN                    -> Biome.FROZEN_OCEAN;
            case OLD_GROWTH_BIRCH_FOREST  -> Biome.SNOWY_TAIGA;
            case OLD_GROWTH_PINE_TAIGA    -> Biome.SNOWY_TAIGA;
            case OLD_GROWTH_SPRUCE_TAIGA  -> Biome.SNOWY_TAIGA;
            case PLAINS                   -> Biome.SNOWY_PLAINS;
            case SAVANNA                  -> Biome.SNOWY_PLAINS;
            case SAVANNA_PLATEAU          -> Biome.SNOWY_PLAINS;
            case SPARSE_JUNGLE            -> Biome.SNOWY_TAIGA;
            case STONY_PEAKS              -> Biome.FROZEN_PEAKS;
            case STONY_SHORE              -> Biome.SNOWY_BEACH;
            case SUNFLOWER_PLAINS         -> Biome.SNOWY_PLAINS;
            case SWAMP                    -> Biome.SNOWY_TAIGA;
            case TAIGA                    -> Biome.SNOWY_TAIGA;
            case WARM_OCEAN               -> Biome.FROZEN_OCEAN;
            case WINDSWEPT_FOREST         -> Biome.SNOWY_TAIGA;
            case WINDSWEPT_GRAVELLY_HILLS -> Biome.SNOWY_PLAINS;
            case WINDSWEPT_HILLS          -> Biome.SNOWY_PLAINS;
            case WINDSWEPT_SAVANNA        -> Biome.SNOWY_PLAINS;
            case WOODED_BADLANDS          -> Biome.SNOWY_PLAINS;
            case RIVER                    -> Biome.FROZEN_RIVER;
            default                       -> null;
        };
    }

    void removeAllChunks() {
        for (val chunkRecord : records.values()) {
            val chunk = chunkRecord.chunk();
            revertChunk(chunk);
        }

        records.clear();
    }

    void removeChunk(@NotNull Chunk chunk) {
        val key         = chunk.getChunkKey();
        val chunkRecord = records.get(key);

        if (chunkRecord == null)
            return;

        revertChunk(chunkRecord);
        records.remove(key);
    }

    void revertChunk(@NotNull Chunk chunk) {
        val key         = chunk.getChunkKey();
        val chunkRecord = records.get(key);

        if (chunkRecord == null)
            return;

        revertChunk(chunkRecord);
    }

    void revertChunk(@NotNull ChunkRecord chunkRecord) {
        for (val blockRecord : chunkRecord.blockRecords()) {
            val block = blockRecord.block();
            val biome = blockRecord.biome();

            block.setBiome(biome);
        }
    }
}
