package pl.sirox.nxrtchat.configuration

import eu.okaeri.configs.OkaeriConfig

class MessageConfiguration : OkaeriConfig() {

    var invalidUsage: String = "<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Invalid usage!"
    var missingPermission: String = "<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Missing permission! ( <permissions> )"
    var lastMessageNotFound: String = "<#AAAAAA>[<#F03C3C>✘<#AAAAAA>] Last message not found!"

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