package de.airblocks.jumpandrun.scoreboard.impl

import de.airblocks.jumpandrun.manager.mainObjective
import de.airblocks.jumpandrun.scoreboard.AbstractScoreboard
import net.kyori.adventure.text.Component
import org.bukkit.scoreboard.Scoreboard

class BuildingScoreboard : AbstractScoreboard() {
    override fun init(scoreboard: Scoreboard) {
        scoreboard.mainObjective?.getScore("§9JumpAndRun:")!!.score = 1
        scoreboard.mainObjective?.getScore("→ INDEV")!!.score = 0
    }
}