package de.airblocks.jumpandrun.manager

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.SkullMeta
import de.airblocks.jumpandrun.enums.PlayerState
import de.airblocks.jumpandrun.enums.getState
import java.util.UUID

object InventoryManager {
    fun Player.setItems() {
        if (this.getState() == PlayerState.LOBBY) {
            with(this.inventory) {
                clear()
                setItem(2, itemStack(Material.PLAYER_HEAD) {
                    meta {
                        displayName(Component.text("Dein Profil").color(KColors.DODGERBLUE))
                    }
                    meta<SkullMeta> {
                        owningPlayer = this@setItems
                    }
                })
                setItem(4, itemStack(Material.RECOVERY_COMPASS) {
                    meta {
                        displayName(Component.text("JumpAndRun beitreten").color(KColors.MEDIUMVIOLETRED))
                    }
                })
                setItem(6, itemStack(Material.BLAZE_ROD) {
                    meta {
                        displayName(Component.text("JumpAndRun bauen").color(KColors.GREENYELLOW))
                    }
                })
            }
        } else if (this.getState() == PlayerState.JUMPING) {
            with(this.inventory) {
                clear()
                setItem(4, itemStack(Material.BAMBOO) {
                    meta {
                        displayName(Component.text("Checkpoint").color(KColors.MEDIUMVIOLETRED))
                    }
                })
                setItem(1, itemStack(Material.STRUCTURE_VOID) {
                    meta {
                        displayName(Component.text("JumpAndRun verlassen").color(KColors.DARKOLIVEGREEN))
                    }
                })
                setItem(7, itemStack(Material.BLAZE_ROD) {
                    meta {
                        displayName(Component.text("Map bewerten").color(KColors.DARKSEAGREEN))
                    }
                })
            }
        }
    }
}