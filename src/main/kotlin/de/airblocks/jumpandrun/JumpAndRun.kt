package de.airblocks.jumpandrun

import de.airblocks.jumpandrun.command.CommandRegistry
import de.airblocks.jumpandrun.listener.FoodLevelChangeListener
import de.airblocks.jumpandrun.listener.PlayerInteractListener
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.main.KSpigot
import de.airblocks.jumpandrun.listener.PlayerJoinListener
import de.airblocks.jumpandrun.listener.PlayerQuitListener
import de.airblocks.jumpandrun.manager.MapManager
import de.airblocks.jumpandrun.utils.VoidGenerator
import net.axay.kspigot.extensions.onlinePlayers
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.generator.ChunkGenerator

class JumpAndRun : KSpigot() {

    override fun load() {
        Bukkit.getConsoleSender().sendMessage(Component.text("Starting ").color(KColors.RED).append(Component.text("${this.javaClass.name}").color(KColors.LIGHTGRAY)))
    }

    override fun startup() {
        PlayerJoinListener
        PlayerQuitListener
        PlayerInteractListener
        FoodLevelChangeListener
        val int = 3 / 9
        MapManager

        getCommand("jumpandrun")?.setExecutor(CommandRegistry())
        getCommand("jumpandrun")?.setTabCompleter(CommandRegistry())
        //WorldCreator("test").generator(VoidGenerator()).createWorld()

    }

    override fun shutdown() {
            for (player in onlinePlayers) {
                player.kick(Component.text("Das Plugin wurde gestoppt!").color(KColors.RED))
        }
        /*for (target in worlds) {
            if (target.name != worlds.get(0).name || target.name != worlds.get(1).name || target.name != worlds.get(2).name) {
                Bukkit.unloadWorld(target, false)
            println(File(target.name).deleteRecursively())
            }
        }

         */
    }

    //code by https://github.com/mooziii
    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator {
        return VoidGenerator()
    }
}