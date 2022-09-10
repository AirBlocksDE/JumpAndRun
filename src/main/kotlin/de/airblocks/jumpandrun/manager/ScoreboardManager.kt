package de.airblocks.jumpandrun.manager

import de.airblocks.jumpandrun.enums.PlayerState
import de.airblocks.jumpandrun.enums.getState
import de.airblocks.jumpandrun.manager.ScoreboardManager.scoreboards1
import de.airblocks.jumpandrun.scoreboard.AbstractScoreboard
import de.airblocks.jumpandrun.scoreboard.impl.BuildingScoreboard
import de.airblocks.jumpandrun.scoreboard.impl.JumpingScoreboard
import de.airblocks.jumpandrun.scoreboard.impl.LobbyScoreboard
import net.axay.kspigot.chat.KColors
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard

object ScoreboardManager {
    var scoreboards = HashMap<Player, Scoreboard>()
    var scoreboards1 = HashMap<PlayerState, AbstractScoreboard>()

    init {
        scoreboards1[PlayerState.LOBBY] = LobbyScoreboard()
        scoreboards1[PlayerState.JUMPING] = JumpingScoreboard()
        scoreboards1[PlayerState.BUILDING] = BuildingScoreboard()
    }
}

val Player.mainScoreboard: Scoreboard?
    get() = ScoreboardManager.scoreboards[this]

fun Player.refreshScoreboard() {
    scoreboards1[this.getState()]?.init(this.mainScoreboard!!)
    this.scoreboard = this.mainScoreboard!!
}

fun Player.initScoreboard() {
    if (!ScoreboardManager.scoreboards.containsKey(player)) ScoreboardManager.scoreboards[this] = Bukkit.getScoreboardManager().newScoreboard
    val objective = this.mainScoreboard!!.registerNewObjective("mainobjective", "mainobjectiveone")
    objective.displaySlot = DisplaySlot.SIDEBAR
    objective.displayName(Component.text("JumpAndRun").color(KColors.allColors().random()))
    this.refreshScoreboard()
}

val Scoreboard.mainObjective: Objective?
    get() {
        return this.getObjective("mainobjective")
    }