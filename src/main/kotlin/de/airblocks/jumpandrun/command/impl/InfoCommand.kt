package de.dinomarlir.ffa.command.impl

import de.dinomarlir.ffa.command.AbstractSubCommand
import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.net.URL

class InfoCommand : AbstractSubCommand() {
    override val description = "Shows the plugin info"
    override val permission = "none"

    override fun handle(sender: CommandSender, mainCommand: Command, args: Array<out String>): Boolean {
        sender.sendMessage(Component.text("Open source plugin").color(KColors.INDIANRED))
        sender.sendMessage(literalText("Click here to go to the SourceCode on GitHub") {
            color = KColors.LIGHTSLATEGRAY
            italic = true
            clickEvent = ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://GitHub.com/DinoMarlir/JumpAndRun")

        })
        return true
    }
}