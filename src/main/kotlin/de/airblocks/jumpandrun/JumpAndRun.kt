package de.airblocks.jumpandrun

import net.axay.kspigot.chat.KColors
import net.axay.kspigot.chat.literalText
import net.axay.kspigot.main.KSpigot
import de.airblocks.jumpandrun.listener.PlayerJoinListener
import de.airblocks.jumpandrun.listener.PlayerQuitListener
import de.airblocks.jumpandrun.utils.scope
import kotlinx.coroutines.launch
import net.axay.kspigot.extensions.onlinePlayers
import net.kyori.adventure.text.Component

class JumpAndRun : KSpigot() {

    override fun load() {
    }

    override fun startup() {
        PlayerJoinListener
        PlayerQuitListener
    }

    override fun shutdown() {
            for (player in onlinePlayers) {
                player.kick(Component.text("Das Plugin wurde gestoppt!").color(KColors.RED))
        }
    }
}