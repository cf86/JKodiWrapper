package com.cf.jkodiwrapper

import com.cf.jkodiwrapper.general.attributes.KodiID

class KodiTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val kodi = Kodi("127.0.0.1", 8081)
            val r = kodi.player.getActivePlayers(KodiID(1))
//            kodi.player.setSpeed(KodiID(Random.nextInt()), PlayerID(1), PlayerSpeed.SPEED_0)

            println("bla")
        }
    }
}