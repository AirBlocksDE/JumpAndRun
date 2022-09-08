package de.airblocks.jumpandrun.manager

import de.airblocks.jumpandrun.utils.VoidGenerator
import kotlinx.serialization.Serializable
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.extensions.onlinePlayers
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.WorldCreator
import java.io.File

object MapManager {
    //ONLY FOR TEST PURPOSES!
    val maps = listOf<MapManager.Map>(Map("TestMap", "plugins/JumpAndRun/maps/TestMap"), Map("NiceMap", "plugins/JumpAndRun/maps/NiceMap"))
    init {
        File("plugins/JumpAndRun/maps").mkdirs()
    }

    @Serializable
    data class Map(val name: String, val folderName: String) {

        fun exists(): Boolean {
            val mapConfigFile = File("plugins/JumpAndRun/maps/$name/map.json")

            return mapConfigFile.exists()
        }

        fun isLoaded(): Boolean {
            if (Bukkit.getWorld(name) != null) return true
            return false
        }

        fun loadWorld() {
            val mapFolder = File("plugins/JumpAndRun/maps/$name")
            val mapConfigFile = File("${mapFolder.path}/map.json")

            if (!mapConfigFile.exists()) {
                Bukkit.getConsoleSender().sendMessage(Component.text("Die Welt $name scheint nicht zu existieren!").color(KColors.RED))
                return
            }
            if (File(name).exists()) {
                Bukkit.getConsoleSender().sendMessage(Component.text("Die Welt $name ist bereits geladen!").color(KColors.RED))
                return
            }
            val direction = File(name)
            if (!direction.exists()) direction.mkdir()
            mapFolder.copyTo(direction, false)
            WorldCreator(name).generator(VoidGenerator()).createWorld()
        }

        fun unloadWorld(save: Boolean) {
            val mapFolder = File("plugins/JumpAndRun/maps/$name")

            if (Bukkit.getWorld(name) != null) {
                Bukkit.unloadWorld(Bukkit.getWorld(name)!!, save)
                mapFolder.delete()
            }
        }

        fun deleteWorld() {
            for (target in onlinePlayers) {
                if (target.world.name == name) {
                    target.kick(Component.text("Die Welt in der du warst wurde gelöscht! Bitte verbinde dich erneut.").color(KColors.RED))
                }
            }
            if (Bukkit.getWorld(name) != null) Bukkit.unloadWorld(Bukkit.getWorld(name)!!, false)
            val mapFolder = File("plugins/JumpAndRun/maps/$name")
            mapFolder.delete()
            //TODO: remove from world (Hash)Map -> coming soon
        }

        fun loadInBuildMode() {
            loadWorld()
            //TODO: MapState implementation
        }


        fun createMap() {
            val mapFolder = File("plugins/JumpAndRun/maps/$name")
            val mapConfigFile = File("${mapFolder.path}/map.json")

            if (mapFolder.exists()) return
            if (File(name).exists()) return

            mapFolder.mkdirs()
            WorldCreator(name).generator(VoidGenerator()).createWorld()
            Bukkit.getWorld(name)?.save()
            Bukkit.unloadWorld(Bukkit.getWorld(name)!!, true)

            val worldFolder = File(name)
            worldFolder.copyTo(mapFolder)
        }
    }
}