package de.airblocks.jumpandrun.command

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class CommandRegistry : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if(args!!.isNotEmpty()) {
            if (CommandManager.commands.containsKey(args[0])) {
                CommandManager.commands.keys.forEach {
                    if (it == args[0]) {
                        if (CommandManager.commands.get(it)!!.permission == null) {
                            CommandManager.commands.get(it)?.handle(sender, command, args)
                            return true
                        }
                        if (sender.hasPermission(CommandManager.commands[it]!!.permission!!)) {
                            CommandManager.commands.get(it)?.handle(sender, command, args)
                            return true
                        }
                        sender.sendMessage(Component.text("Dafür hast du keine Rechte!").color(KColors.RED))
                        return true
                    }
                }
            }
            sender.sendMessage(Component.text("Dieser Befehl wurde nicht gefunden!").color(KColors.INDIANRED))
            return true
        }
        sender.sendMessage(literalText {
            text("INFO:") {
                bold = true
                color = KColors.WHITESMOKE
            }
        })
        sender.sendMessage(literalText {
            CommandManager.commands.forEach {
                if (it.value.permission == null) {
                    sender.sendMessage(literalText {
                        text("  " + it.key) { color = KColors.CORNFLOWERBLUE }
                        text(" | ") { color = KColors.DARKGRAY }
                        text(it.value.description) { color = KColors.GREENYELLOW}
                        clickEvent = ClickEvent.clickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/jumpandrun ${it.key}")
                    })
                } else if (sender.hasPermission(it.value.permission.toString())) {
                    sender.sendMessage(literalText {
                        text("  " + it.key) { color = KColors.CORNFLOWERBLUE }
                        text(" | ") { color = KColors.DARKGRAY }
                        text(it.value.description) { color = KColors.GREENYELLOW}
                        clickEvent = ClickEvent.clickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/jumpandrun ${it.key}")
                    })
                } else {
                    sender.sendMessage(literalText {
                        strikethrough = true
                        text("  ") {
                            color = KColors.CORNFLOWERBLUE
                            strikethrough = false
                        }
                        text(it.key) {
                            color = KColors.CORNFLOWERBLUE
                        }
                        text(" | ") {
                            color = KColors.DARKGRAY
                        }
                        text(it.value.description) {
                            color = KColors.GREENYELLOW
                        }
                        hoverEvent = HoverEvent.showText(Component.text("Dafür hast du keine Rechte!").color(KColors.RED))
                    })
                }
            }
            sender.sendMessage(literalText("All messages are tab-completeable!") {
                color = KColors.INDIANRED
                italic = true
                hoverEvent = HoverEvent.hoverEvent(
                    HoverEvent.Action.SHOW_TEXT, Component.text("this is nice!").color(KColors.LIMEGREEN)) })
        })
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String>? {
        return CommandManager.commands.keys.toMutableList()
    }
}