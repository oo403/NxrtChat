package pl.sirox.nxrtchat.configuration

import eu.okaeri.configs.OkaeriConfig

class GeneralConfiguration : OkaeriConfig() {

    val globalFormat: String = "<player> » <message>"

    var mentionSound: String = "minecraft:entity.player.levelup"

    var mentionFormat: String = "<green>@<player>"

}