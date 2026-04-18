package pl.sirox.nxrtchat.configuration

import eu.okaeri.configs.OkaeriConfig

class GeneralConfiguration : OkaeriConfig() {

    var format: String = "global"

    var globalFormat: String = "<player> » <message>"

    var ranksFormat: MutableMap<String, RanksFormat> = mutableMapOf(
        "default" to RanksFormat().apply {
            format = "<player> » <message>"
        }
    )

    class RanksFormat : OkaeriConfig() {
        var format: String = "<player> » <message>"
    }

    var mentionSound: String = "minecraft:entity.player.levelup"

    var mentionFormat: String = "<green>@<player>"

}