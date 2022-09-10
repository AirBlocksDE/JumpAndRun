package de.airblocks.jumpandrun.scoreboard.impl

import de.airblocks.jumpandrun.manager.mainObjective
import de.airblocks.jumpandrun.scoreboard.AbstractScoreboard
import org.bukkit.scoreboard.Scoreboard

class LobbyScoreboard : AbstractScoreboard() {

    override fun init(scoreboard: Scoreboard) {
        scoreboard.mainObjective?.getScore("Ja, idk")!!.score = 1
    }
}