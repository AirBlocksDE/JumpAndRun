package de.airblocks.jumpandrun.command

import de.airblocks.jumpandrun.command.impl.InfoCommand
import de.airblocks.jumpandrun.command.impl.MapCommand

object CommandManager {
    var commands = HashMap<String, AbstractSubCommand>()

    init {
        commands.put("info", InfoCommand())
        commands.put("map", MapCommand())
    }
}