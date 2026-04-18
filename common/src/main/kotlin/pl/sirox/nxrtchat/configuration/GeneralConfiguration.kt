package pl.sirox.nxrtchat.configuration

import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.Comment

class GeneralConfiguration : OkaeriConfig() {

    @Comment("Format to use for messages","Available formats: global, ranks")
    var format: String = "global"

    @Comment("","Format used for all players, regardless of rank")
    var globalFormat: String = "<player> » <message>"

    @Comment("","Format used for players with specific rank")
    var ranksFormat: MutableMap<String, RanksFormat> = mutableMapOf(
        "default" to RanksFormat().apply {
            format = "<player> » <message>"
        }
    )

    class RanksFormat : OkaeriConfig() {
        var format: String = "<player> » <message>"
    }

    @Comment("","Sound to play when someone mentions you")
    var mentionSound: String = "minecraft:entity.player.levelup"

    @Comment("","Format to use for mentions")
    var mentionFormat: String = "<green>@<player>"

}