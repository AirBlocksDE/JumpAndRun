package de.airblocks.jumpandrun.listener

import de.airblocks.jumpandrun.enums.PlayerState
import de.airblocks.jumpandrun.enums.getState
import de.airblocks.jumpandrun.gui.CreateJarGUI
import net.axay.kspigot.event.listen
import net.axay.kspigot.gui.openGUI
import org.bukkit.Material
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

object PlayerInteractListener {

    init {
        listen<PlayerInteractEvent> {
            val player = it.player

            when(player.getState()) {
                PlayerState.LOBBY -> {
                    if (it.action == Action.RIGHT_CLICK_AIR || it.action == Action.RIGHT_CLICK_BLOCK) {
                        if (it.item?.type == Material.BLAZE_ROD) { //TODO: with object
                            player.openGUI(CreateJarGUI.gui)
                        }
                    }
                }
                else -> {}
            }
        }
    }
}