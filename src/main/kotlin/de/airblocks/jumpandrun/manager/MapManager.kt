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
    val maps = listOf<MapManager.Map>(Map("TestMap"), Map("NiceMap"))

    init {
        File("plugins/JumpAndRun/maps").mkdirs()
    }

    @Serializable
    data class Map(val name: String) {

        fun exists(): Boolean { //tested: works
            val mapConfigFile = File("plugins/JumpAndRun/maps/$name")

            return mapConfigFile.exists()
        }

        fun isLoaded(): Boolean { //tested: works
            if (Bukkit.getWorld(name) != null) return true
            return false
        }

        fun loadWorld() { //tested: works
            val mapFolder = File("plugins/JumpAndRun/maps/$name")
            val mapConfigFile = File("${mapFolder.path}/map.json")

            if (!mapFolder.exists()) {
                Bukkit.getConsoleSender().sendMessage(Component.text("Die Welt $name scheint nicht zu existieren!").color(KColors.RED))
                return
            }

            if (File(name).exists()) {
                Bukkit.getConsoleSender().sendMessage(Component.text("Die Welt $name ist bereits geladen!").color(KColors.RED))
                return
            }
            val direction = File(name)

            mapFolder.copyTo(direction, false)
            WorldCreator(name).generator(VoidGenerator()).createWorld()
        }

        fun unloadWorld(save: Boolean) { //TODO: save
            val mapFolder = File("plugins/JumpAndRun/maps/$name")
            val worldFolder = File(name)

            Bukkit.getWorld(name)?.players?.forEach {
                it.kick(Component.text("Die Welt in der du warst wurde gelöscht! Bitte verbinde dich erneut.").color(KColors.RED))
            }
            if (Bukkit.getWorld(name) != null) {
                Bukkit.unloadWorld(Bukkit.getWorld(name)!!, save)
                worldFolder.deleteRecursively()
            }
        }

        fun deleteWorld() { //tested: works -> todos
            val world = Bukkit.getWorld(name)
            val mapFolder = File("plugins/JumpAndRun/maps/$name")

            unloadWorld(false)
            mapFolder.deleteRecursively()
            //TODO: remove from world (Hash)Map -> coming soon
        }

        fun loadInBuildMode() {
            loadWorld()
            //TODO: MapState implementation
        }


        fun createMap() { //tested: works
            val mapFolder = File("plugins/JumpAndRun/maps/$name")
            val mapConfigFile = File("${mapFolder.path}/map.json")
            val worldFolder = File(name)

            if (mapFolder.exists()) {
                Bukkit.getConsoleSender().sendMessage(Component.text("Die Welt $name existiert bereits!").color(KColors.RED))
                return
            }
            if (worldFolder.exists()) worldFolder.deleteRecursively()

            WorldCreator(name).generator(VoidGenerator()).createWorld()
            Bukkit.unloadWorld(Bukkit.getWorld(name)!!, true)
            worldFolder.copyRecursively(mapFolder)
            worldFolder.deleteRecursively()
        }
    }
}