package de.airblocks.jumpandrun.data

import de.airblocks.jumpandrun.manager.MapManager
import de.airblocks.jumpandrun.utils.VoidGenerator
import io.papermc.paper.annotation.DoNotUse
import kotlinx.serialization.Serializable
import net.axay.kspigot.chat.KColors
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Warning
import org.bukkit.WorldCreator
import java.io.File

@Serializable
data class JarMap(val name: String, val useable: Boolean) {

    fun exists(): Boolean { //tested: works
        val mapFolder = File("plugins/JumpAndRun/maps/$name")

        return mapFolder.exists()
    }

    fun isLoaded(): Boolean { //tested: works
        if (Bukkit.getWorld(name) != null) return true
        return false
    }

    fun loadWorld() { //tested: works
        val mapFolder = File("plugins/JumpAndRun/maps/$name")
        val mapConfigFile = File("${mapFolder.path}/map.json")

        if (!mapFolder.exists()) {
            Bukkit.getConsoleSender().sendMessage(
                Component.text("Die Welt $name scheint nicht zu existieren!").color(
                    KColors.RED))
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

    fun deleteMap() { //tested: works -> todos
        val world = Bukkit.getWorld(name)
        val mapFolder = File("plugins/JumpAndRun/maps/$name")

        unloadWorld(false)
        mapFolder.deleteRecursively()
        //TODO: remove from world (Hash)Map -> coming soon
        MapManager.maps.remove(this)
    }

    @DoNotUse //because it won't work
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
        mapConfigFile.createNewFile()
        worldFolder.deleteRecursively()
        MapManager.maps.add(this)
    }
}