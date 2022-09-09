package de.airblocks.jumpandrun.manager

import de.airblocks.jumpandrun.data.JarMap
import java.io.File

object MapManager {
    //ONLY FOR TEST PURPOSES!
    var maps: ArrayList<JarMap> = ArrayList<JarMap>()

    init {
        val mapFolder = File("plugins/JumpAndRun/maps")
        mapFolder.mkdirs()

        mapFolder.listFiles().forEach {
            maps.add(JarMap(it.name, true))
        }
    }
}