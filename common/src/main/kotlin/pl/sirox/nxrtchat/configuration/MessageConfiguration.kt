package pl.sirox.nxrtchat.configuration

import eu.okaeri.configs.OkaeriConfig
import eu.okaeri.configs.annotation.Comment

class MessageConfiguration : OkaeriConfig() {

    @Comment("Invalid usage message")
    var invalidUsage: String = "<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Invalid usage!"
    @Comment("","Missing permission message", "Available placeholders: <permissions>")
    var missingPermission: String = "<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Missing permission! ( <permissions> )"
    @Comment("","Player not found message")
    var playerNotFound: String = "<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Player not found!"
    @Comment("","Last message not found message")
    var lastMessageNotFound: String = "<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Last message not found!"
    @Comment("","Config reload success message")
    var configReloadSuccess: String = "<#AAAAAA>[<#5DF083>✔<#AAAAAA>] Config reloaded successfully!"
    @Comment("","Config reload failed message")
    var configReloadFailed: String = "<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Config reload failed!"

    @Comment("","Msg format")
    var msg: MutableMap<String, Msg> = mutableMapOf(
        "sender" to Msg().apply {
            format = "You » <target>: <message>"
        },

        "target" to Msg().apply {
            format = "<sender> » You: <message>"
        },

        "console" to Msg().apply {
            format = "<sender> -> <target>: <message>"
        }
    )

    class Msg : OkaeriConfig() {
        var format: String = ""
    }

}