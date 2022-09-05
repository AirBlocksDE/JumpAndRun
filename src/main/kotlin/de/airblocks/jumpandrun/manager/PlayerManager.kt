package de.airblocks.jumpandrun.manager

import de.airblocks.jumpandrun.enums.PlayerState
import org.bukkit.entity.Player

object PlayerManager {
    var playerStates = HashMap<Player, PlayerState>()

    fun Player.update() {
        this.updateInventory()
    }
}