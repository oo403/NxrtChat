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

    @Comment("","Enable item sharing function")
    var itemShareFeature: Boolean = true
    @Comment("","Placeholder for an item")
    var itemPlaceholder: String = "[ITEM]"
    @Comment("","Format to use for item", "Available placeholders: <item>")
    var itemFormat = "<item>"

    @Comment("","Enable inventory sharing function")
    var invShareFeature: Boolean = true
    @Comment("","Title of inventory sharing", "Available placeholders: <player>")
    var invShareTitle: String = "Inventory of <player>"
    @Comment("","Placeholder for an inventory")
    var invPlaceholder: String = "[INV]"
    @Comment("","Format to use for inventory")
    var invFormat: String = "<green>[INV]"
    @Comment("","How many times can player use inventory share placeholder")
    var invUses: Int = 10

    @Comment("","Enable enderchest sharing function")
    var ecShareFeature: Boolean = true
    @Comment("","Title of enderchest sharing", "Available placeholders: <player>")
    var ecShareTitle: String = "Enderchest of <player>"
    @Comment("","Placeholder for an enderchest")
    var ecPlaceholder: String = "[EC]"
    @Comment("","Format to use for enderchest")
    var ecFormat: String = "<green>[EC]"
    @Comment("","How many times can player use enderchest share placeholder")
    var ecUses: Int = 10

}