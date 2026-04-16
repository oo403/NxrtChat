package pl.sirox.nxrtchat.service

import com.google.inject.Inject
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import pl.sirox.nxrtchat.configuration.GeneralConfiguration
import pl.sirox.nxrtchat.configuration.MessageConfiguration
import pl.sirox.nxrtchat.logging.LoggerFactory
import pl.sirox.nxrtchat.logging.logger

class ConfigurationService @Inject constructor(
    private val generalConfiguration: GeneralConfiguration,
    private val messageConfiguration: MessageConfiguration,
    private val loggerFactory: LoggerFactory
) {

    private val logger = loggerFactory.logger<ConfigurationService>()

    fun reload(sender: CommandSender) {
        val successReloadMessage = MiniMessage.miniMessage().deserialize(messageConfiguration.configReloadSuccess)
        val failReloadMessage = MiniMessage.miniMessage().deserialize(messageConfiguration.configReloadFailed)

        try {
            generalConfiguration.load(true)
            messageConfiguration.load(true)

            sender.sendMessage(successReloadMessage)
        } catch (e: Exception) {
            logger.error("Failed to reload configuration", e)
            e.printStackTrace()

            sender.sendMessage(failReloadMessage)
        }
    }

}