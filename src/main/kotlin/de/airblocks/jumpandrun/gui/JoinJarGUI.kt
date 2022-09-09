package de.airblocks.jumpandrun.gui

import de.airblocks.jumpandrun.data.JarMap
import de.airblocks.jumpandrun.enums.PlayerState
import de.airblocks.jumpandrun.enums.getState
import de.airblocks.jumpandrun.enums.setState
import de.airblocks.jumpandrun.manager.MapManager
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.gui.*
import net.axay.kspigot.items.itemStack
import net.axay.kspigot.items.meta
import net.axay.kspigot.items.name
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

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
            val compund = createRectCompound<JarMap>(Slots.RowFourSlotTwo, Slots.RowFourSlotEight, iconGenerator = {
                val uuids = listOf<UUID>(UUID.fromString("ed21d90f-5bf7-4ae0-a3ea-75c519c89a50"), UUID.fromString("72ccbc0c-a35f-4492-9450-971a26607ee0"), UUID.randomUUID())
                itemStack(Material.PLAYER_HEAD) {
                    meta {
                        name = Component.text(it.name).color(KColors.allColors().random())
                        lore(listOf(Component.text("Linksklick: ").color(KColors.LIGHTGRAY).append(Component.text("JumpAndRun beitreten")), Component.text("Rechtsklick: ").color(KColors.LIGHTGRAY).append(Component.text("JumpAndRun bearbeiten"))))
                    }
                    meta<SkullMeta> {
                        owningPlayer = Bukkit.getOfflinePlayer(uuids.random())
                    }
                }
            }, onClick = { clickEvent, map ->
                if (clickEvent.bukkitEvent.isLeftClick) {
                    map.loadWorld()
                    //TODO: teleport
                } else if (clickEvent.bukkitEvent.isRightClick) { //TODO: better code
                    if (clickEvent.player.hasPermission("jumpandrun.modify")) {
                        map.unloadWorld(false)
                        map.loadWorld() //TODO: in build mode
                        clickEvent.player.teleport(Location(Bukkit.getWorld(map.name), 2.0, 64.0, 2.0))
                        clickEvent.player.setState(PlayerState.BUILDING)
                    } else {
                        clickEvent.player.sendMessage(Component.text("Dafür hast du keine Rechte").color(KColors.RED))
                    }
                }
            })
            compund.addContent(MapManager.maps)
        }
    }
}