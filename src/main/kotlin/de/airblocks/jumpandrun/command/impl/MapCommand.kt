package de.airblocks.jumpandrun.command.impl

import de.airblocks.jumpandrun.command.AbstractSubCommand
import de.airblocks.jumpandrun.data.JarMap
import de.airblocks.jumpandrun.manager.MapManager
import net.axay.kspigot.chat.KColors
import net.kyori.adventure.text.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class MapCommand : AbstractSubCommand() {
    override val description = "The Map Command"
    override val permission = "jumpandrun.map"

    override fun handle(sender: CommandSender, mainCommand: Command, args: Array<out String>): Boolean {

        if (args.size == 3) {
            if (args[1].equals("create", true)) {
                val map = JarMap(args[2], false)
                map.createMap()
                sender.sendMessage(Component.text("Die Map '${args[2]}' wurde erstellt!").color(KColors.GREENYELLOW))
            } else if (args[1].equals("delete", true)) {
                MapManager.maps.forEach() {
                    if (it.name.equals(args[2], true)) {
                        it.deleteMap()
                    }
                }
            } else {
                sender.sendMessage(Component.text("Commands: /map create").color(KColors.RED))
            }
        }
        return true
    }
}