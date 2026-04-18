package pl.sirox.nxrtchat.service

import com.google.inject.Inject
import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.adventure.LiteAdventureExtension
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import dev.rollczi.litecommands.bukkit.LiteBukkitMessages
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import pl.sirox.nxrtchat.bootstrap.EnableBootstrap
import pl.sirox.nxrtchat.command.CustomCommand
import pl.sirox.nxrtchat.command.handler.InvalidUsageHandler
import pl.sirox.nxrtchat.command.handler.MissingPermissionHandler
import pl.sirox.nxrtchat.configuration.MessageConfiguration
import pl.sirox.nxrtchat.logging.LoggerFactory
import pl.sirox.nxrtchat.logging.logger

class CommandService @Inject constructor(
    private val loggerFactory: LoggerFactory,
    private val plugin: EnableBootstrap,
    private val commands: Set<CustomCommand>,
    private val messageConfiguration: MessageConfiguration
) {

    private lateinit var liteCommands : LiteCommands<CommandSender>
    private lateinit var miniMessage: MiniMessage

    private val logger = loggerFactory.logger<CommandService>()

    fun run() {
        try {
            this.miniMessage = MiniMessage.miniMessage()

            this.liteCommands = LiteBukkitFactory.builder("nxrtchat", plugin)
                .commands(
                    commands
                )
                .invalidUsage(InvalidUsageHandler(messageConfiguration))
                .missingPermission(MissingPermissionHandler(messageConfiguration))
                .message(LiteBukkitMessages.PLAYER_NOT_FOUND, MiniMessage.miniMessage().deserialize(messageConfiguration.playerNotFound))
                .extension(LiteAdventureExtension()) {
                    it.miniMessage(true)
                    it.legacyColor(true)
                    it.serializer(this.miniMessage)
                }
                .build()

            logger.info("LiteCommands initialized")
        } catch (e: Exception) {
            logger.error("Failed to initialize LiteCommands", e)
        }
    }

    fun disable() {
        if (this::liteCommands.isInitialized) {
            this.liteCommands.unregister()
        }
    }
}