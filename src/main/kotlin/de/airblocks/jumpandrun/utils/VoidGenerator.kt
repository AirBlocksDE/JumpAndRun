package de.airblocks.jumpandrun.utils

//class by https://github.com/mooziii
import org.bukkit.generator.ChunkGenerator
import org.bukkit.generator.WorldInfo
import java.util.*


class VoidGenerator : ChunkGenerator() {

    override fun generateSurface(worldInfo: WorldInfo, random: Random, x: Int, z: Int, chunkData: ChunkData) {}

}