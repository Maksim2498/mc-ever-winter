package space.moontalk.mc.ever_winter;

import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import org.jetbrains.annotations.NotNull;

public record BlockRecord(@NotNull Block block, @NotNull Biome biome) {

}
