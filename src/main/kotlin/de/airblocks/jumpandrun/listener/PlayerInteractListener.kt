package de.airblocks.jumpandrun.listener

import net.axay.kspigot.event.listen
import org.bukkit.event.player.PlayerInteractEvent

object PlayerInteractListener {

    init {
        listen<PlayerInteractEvent> {
            val player = it.player
        }
    }
}