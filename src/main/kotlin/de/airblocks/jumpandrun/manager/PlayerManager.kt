package de.airblocks.jumpandrun.manager

import de.airblocks.jumpandrun.enums.PlayerState
import de.airblocks.jumpandrun.manager.InventoryManager.setItems
import org.bukkit.entity.Player

object PlayerManager {
    var playerStates = HashMap<Player, PlayerState>()

    fun Player.update() {
        this.setItems()
    }
}