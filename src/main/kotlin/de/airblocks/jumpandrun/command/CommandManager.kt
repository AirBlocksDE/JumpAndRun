package de.airblocks.jumpandrun.command

import de.airblocks.jumpandrun.command.impl.InfoCommand

object CommandManager {
    var commands = HashMap<String, AbstractSubCommand>()

    init {
        commands.put("info", InfoCommand())
    }
}