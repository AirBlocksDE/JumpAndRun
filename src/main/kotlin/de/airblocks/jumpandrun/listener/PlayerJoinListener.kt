package de.airblocks.jumpandrun.listener

import de.airblocks.jumpandrun.enums.PlayerState
import net.axay.kspigot.event.listen
import org.bukkit.event.player.PlayerJoinEvent
import de.airblocks.jumpandrun.manager.InventoryManager.setItems
import de.airblocks.jumpandrun.manager.PlayerManager
import de.airblocks.jumpandrun.manager.PlayerManager.update

object PlayerJoinListener {

    init {
        listen<PlayerJoinEvent> {
            val player = it.player
            it.joinMessage(null)

            PlayerManager.playerStates[player] = PlayerState.LOBBY
            player.update()
        }
    }
}