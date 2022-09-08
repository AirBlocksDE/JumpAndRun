package de.airblocks.jumpandrun.gui

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.gui.GUIType
import net.axay.kspigot.gui.Slots
import net.axay.kspigot.gui.kSpigotGUI
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.kyori.adventure.text.Component
import org.bukkit.Material

object CreateJarGUI {
    val gui = kSpigotGUI(GUIType.THREE_BY_NINE) {
        page(1) {
            title = Component.text("JumpAndRun GUI").color(KColors.GREENYELLOW)
            placeholder(Slots.Border, itemStack(Material.BLACK_STAINED_GLASS_PANE) {
                meta {
                    displayName(Component.empty())
                }
            })

            button(Slots.RowTwoSlotThree, itemStack(Material.STRUCTURE_VOID) {
                meta {
                    displayName(Component.text("JumpAndRun löschen").color(KColors.RED))
                }
            }, onClick = {
                //TODO: JumpAndRun delete GUI
            })

            button(Slots.RowTwoSlotFive, itemStack(Material.AMETHYST_SHARD) {
                meta {
                    displayName(Component.text("JumpAndRun bearbeiten").color(KColors.CORNFLOWERBLUE))
                }
            }, onClick = {
                //TODO: JumpAndRun modify GUI
            })

            button(Slots.RowTwoSlotFive, itemStack(Material.AMETHYST_SHARD) {
                meta {
                    displayName(Component.text("JumpAndRun erstellen").color(KColors.GREENYELLOW))
                }
            }, onClick = {
                //TODO: JumpAndRun create GUI
            })
            button(Slots.RowTwoSlotSeven, itemStack(Material.RED_CANDLE) {
                meta {
                    displayName(Component.text("JumpAndRun modifizieren").color(KColors.RED))
                }
            }, onClick = {
                //TODO: JumpAndRun modify GUI
            })
        }
    }
}