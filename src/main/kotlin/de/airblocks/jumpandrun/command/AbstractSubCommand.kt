package de.dinomarlir.ffa.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender

abstract class AbstractSubCommand {
    abstract val description: String
    abstract val permission: String
    abstract fun handle(sender: CommandSender, mainCommand: Command, args: Array<out String>): Boolean
}