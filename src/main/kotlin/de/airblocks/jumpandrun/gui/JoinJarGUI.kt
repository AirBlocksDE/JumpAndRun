package de.airblocks.jumpandrun.gui

import de.airblocks.jumpandrun.manager.MapManager
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.gui.*
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.kyori.adventure.text.Component
import org.bukkit.Material

object JoinJarGUI {
    val gui = kSpigotGUI(GUIType.FIVE_BY_NINE) {
        defaultPage = 1

        page(1) {
            title = Component.text("JumpAndRun beitreten").color(KColors.MEDIUMVIOLETRED)
            placeholder(Slots.Border, itemStack(Material.BLACK_STAINED_GLASS_PANE) {
                meta {
                    displayName(Component.empty())
                }
            })
            previousPage(Slots.RowOneSlotOne, itemStack(Material.ARROW) {
                meta {
                    displayName(Component.text("Letzte Seite").color(KColors.BLUE))
                }
            }, null) {}
            nextPage(Slots.RowOneSlotNine, itemStack(Material.ARROW) {
                meta {
                    displayName(Component.text("Nächste Seite").color(KColors.BLUE))
                }
            }, null) {}
            val compund = createRectCompound<MapManager.Map>(Slots.RowFourSlotTwo, Slots.RowFourSlotEight, iconGenerator = {
                itemStack(Material.STONE) {
                    meta {
                        name = Component.text(it.name).color(KColors.MEDIUMVIOLETRED)
                        lore(listOf(Component.text(it.folderName).color(KColors.BLUE)))
                    }
                }
            }, onClick = { _, _ ->

            })
            compund.addContent(MapManager.maps)
        }
    }
}