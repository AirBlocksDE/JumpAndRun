package de.airblocks.jumpandrun.listener

import de.airblocks.jumpandrun.data.JarMap
import net.axay.kspigot.event.listen
import org.bukkit.event.player.PlayerChangedWorldEvent

object PlayerChangedWorldListener {

    init {
        listen<PlayerChangedWorldEvent> {
            val player = it.player
            if (it.from.name != "world" || it.from.name != "world_nether" ||it.from.name != "world_the_end") {
                if (it.from.playerCount == 0) {
                    JarMap(it.from.name, false).unloadWorld(false) //TODO save if it is in buildmode
                }
            }
        }
    }
}