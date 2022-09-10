package de.airblocks.jumpandrun.listener

import de.airblocks.jumpandrun.manager.PlayerManager
import de.airblocks.jumpandrun.manager.ScoreboardManager
import de.airblocks.jumpandrun.manager.mainScoreboard
import net.axay.kspigot.event.listen
import org.bukkit.event.player.PlayerQuitEvent

object PlayerQuitListener {

    init {
        listen<PlayerQuitEvent> {
            val player = it.player

            PlayerManager.playerStates.remove(player)

            player.mainScoreboard?.players!!.remove(player)
            ScoreboardManager.scoreboards.remove(player)
        }
    }
}