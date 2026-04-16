package pl.sirox.nxrtchat.service

import com.google.inject.Inject
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.entity.Player
import pl.sirox.nxrtchat.configuration.MessageConfiguration
import pl.sirox.nxrtchat.logging.LoggerFactory
import pl.sirox.nxrtchat.logging.logger

class MessageService @Inject constructor(
    private val messageConfiguration: MessageConfiguration,
    private val loggerFactory: LoggerFactory,
    private val playerService: PlayerService
){

    fun sendMsg(sender: Player, target: Player, message: String) {
        val senderName = sender.name
        val targetName = target.name

        val senderMessage = messageConfiguration.msg["sender"]?.format ?: "You -> <target>: <message>"
        val targetMessage = messageConfiguration.msg["target"]?.format ?: "<sender> -> You: <message>"

        val consoleMessage = messageConfiguration.msg["console"]?.format ?: "<sender> -> <target>: <message>"

        val senderFormattedMessage = MiniMessage.miniMessage().deserialize(senderMessage,
            Placeholder.unparsed("sender", senderName),
            Placeholder.unparsed("target", targetName),
            Placeholder.unparsed("message", message))

        val targetFormattedMessage = MiniMessage.miniMessage().deserialize(targetMessage,
            Placeholder.unparsed("sender", senderName),
            Placeholder.unparsed("target", targetName),
            Placeholder.unparsed("message", message))

        val consoleFormattedMessage = consoleMessage
            .replace("<sender>", senderName)
            .replace("<target>", targetName)
            .replace("<message>", message)

        sender.sendMessage(senderFormattedMessage)
        target.sendMessage(targetFormattedMessage)

        val logger = loggerFactory.logger<MessageService>("MSG")
        logger.info(consoleFormattedMessage)

        playerService.setLastMsg(sender.uniqueId, target.uniqueId)
    }

}