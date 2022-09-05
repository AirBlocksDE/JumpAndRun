package de.airblocks.jumpandrun.enums

import de.airblocks.jumpandrun.manager.PlayerManager
import org.bukkit.entity.Player

enum class PlayerState(name: String) {
    LOBBY("Lobby"),
    JUMPING("Jumping"),
    BUILDING("Building");
}
fun Player.getState(): PlayerState? {
    return PlayerManager.playerStates[player]
}