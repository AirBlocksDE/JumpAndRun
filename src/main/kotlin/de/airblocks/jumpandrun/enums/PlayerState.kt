package de.airblocks.jumpandrun.enums

import de.airblocks.jumpandrun.manager.PlayerManager
import de.airblocks.jumpandrun.manager.PlayerManager.update
import org.bukkit.entity.Player

enum class PlayerState(name: String) {
    LOBBY("Lobby"),
    JUMPING("Jumping"),
    BUILDING("Building");
}
fun Player.getState(): PlayerState? {
    return PlayerManager.playerStates[player]
}
fun Player.setState(state: PlayerState) {
    PlayerManager.playerStates[player!!] = state
    this.update()
}