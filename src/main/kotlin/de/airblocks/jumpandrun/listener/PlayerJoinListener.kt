package de.airblocks.jumpandrun.listener

import de.airblocks.jumpandrun.enums.PlayerState
import net.axay.kspigot.event.listen
import org.bukkit.event.player.PlayerJoinEvent
import de.airblocks.jumpandrun.manager.PlayerManager
import de.airblocks.jumpandrun.manager.PlayerManager.update
import org.bukkit.Bukkit
import org.bukkit.Location

object PlayerJoinListener {

    init {
        listen<PlayerJoinEvent> {
            val player = it.player
            it.joinMessage(null)
            PlayerManager.playerStates[player] = PlayerState.LOBBY
            player.update()
            player.teleport(
                Location(
                Bukkit.getWorld("world"),
                1.0,
                Bukkit.getWorld("world")?.getHighestBlockAt(1, 1)?.y!!.toDouble() + 1,
                1.0
                )
            )
        }
    }
}