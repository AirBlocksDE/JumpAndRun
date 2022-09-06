package de.airblocks.jumpandrun.listener

import net.axay.kspigot.event.listen
import org.bukkit.event.entity.FoodLevelChangeEvent

object FoodLevelChangeListener {

    init {
        listen<FoodLevelChangeEvent> {
            it.isCancelled = true
        }
    }
}