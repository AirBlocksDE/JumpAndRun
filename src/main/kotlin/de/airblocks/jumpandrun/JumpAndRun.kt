package de.airblocks.jumpandrun

import de.airblocks.jumpandrun.listener.PlayerInteractListener
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.main.KSpigot
import de.airblocks.jumpandrun.listener.PlayerJoinListener
import de.airblocks.jumpandrun.listener.PlayerQuitListener
import de.airblocks.jumpandrun.utils.VoidGenerator
import net.axay.kspigot.extensions.onlinePlayers
import net.axay.kspigot.extensions.worlds
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.WorldCreator
import org.bukkit.generator.ChunkGenerator
import java.io.File

class JumpAndRun : KSpigot() {

    override fun load() {
    }

    override fun startup() {
        PlayerJoinListener
        PlayerQuitListener
        PlayerInteractListener

        //WorldCreator("test").generator(VoidGenerator()).createWorld()

    }

    override fun shutdown() {
            for (player in onlinePlayers) {
                player.kick(Component.text("Das Plugin wurde gestoppt!").color(KColors.RED))
        }
        for (target in worlds) {
            if (target.name != worlds.get(0).name || target.name != worlds.get(1).name || target.name != worlds.get(2).name) {
                Bukkit.unloadWorld(target, false)
            println(File(target.name).deleteRecursively())
            }
        }
    }

    //code by https://github.com/mooziii
    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator {
        return VoidGenerator()
    }
}