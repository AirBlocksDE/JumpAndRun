package de.airblocks.jumpandrun.enums

import net.axay.kspigot.chat.KColors
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import net.kyori.adventure.text.Component

enum class PressurePlates(name: String, material: Material, onClick: (clickEvent: PlayerInteractEvent) -> Unit = {}) {
    CHECKPOINT("Checkpoint", Material.STONE_PRESSURE_PLATE, onClick = {
        val player = it.player

        player.sendMessage(Component.text("Du hast den Checkpoint erreicht!").color(KColors.GREENYELLOW))
    }),
    GOAL("Ziel", Material.ACACIA_PRESSURE_PLATE, onClick = {
        val player = it.player

        player.sendMessage(net.kyori.adventure.text.Component.text("Du hast das Ziel erreicht!").color(KColors.GREENYELLOW))
    })
}