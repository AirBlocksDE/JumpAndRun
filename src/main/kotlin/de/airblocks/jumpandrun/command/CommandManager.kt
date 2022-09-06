package de.dinomarlir.ffa.command

import de.dinomarlir.ffa.command.impl.InfoCommand

object CommandManager {
    var commands = HashMap<String, AbstractSubCommand>()

    init {
        commands.put("info", InfoCommand())
    }
}