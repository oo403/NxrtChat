package pl.sirox.nxrtchat.configuration

import eu.okaeri.configs.OkaeriConfig

class MessageConfiguration : OkaeriConfig() {

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