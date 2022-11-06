package space.moontalk.mc.ever_winter;

import java.util.List;

import org.bukkit.Chunk;

import org.jetbrains.annotations.NotNull;

public record ChunkRecord(@NotNull Chunk chunk, @NotNull List<BlockRecord> blockRecords) {

}
