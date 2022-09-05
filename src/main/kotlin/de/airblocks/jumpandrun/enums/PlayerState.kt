package de.airblocks.jumpandrun.enums

import org.bukkit.entity.Player

enum class PlayerState(name: String) {
    LOBBY("Lobby"),
    JUMPING("Jumping"),
    BUILDING("Building");
}
fun Player.getState(): PlayerState {
    return PlayerState.JUMPING
}