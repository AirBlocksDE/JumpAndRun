package de.airblocks.jumpandrun

import de.airblocks.jumpandrun.command.CommandRegistry
import de.airblocks.jumpandrun.listener.*
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.main.KSpigot
import de.airblocks.jumpandrun.manager.MapManager
import de.airblocks.jumpandrun.utils.VoidGenerator
import de.airblocks.jumpandrun.utils.json
import kotlinx.serialization.encodeToString
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
        PlayerChangedWorldListener

        MapManager

        getCommand("jumpandrun")?.setExecutor(CommandRegistry())
        getCommand("jumpandrun")?.tabCompleter = CommandRegistry()
    }

    override fun shutdown() {
            for (player in onlinePlayers) {
                player.kick(Component.text("Das Plugin wurde gestoppt!").color(KColors.RED))
        }
    }

    //code by https://github.com/mooziii
    override fun getDefaultWorldGenerator(worldName: String, id: String?): ChunkGenerator {
        return VoidGenerator()
    }
}