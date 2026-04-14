package pl.sirox.nxrtchat.service

import com.google.inject.Inject
import dev.rollczi.litecommands.LiteCommands
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import org.bukkit.command.CommandSender
import pl.sirox.nxrtchat.bootstrap.EnableBootstrap
import pl.sirox.nxrtchat.logging.LoggerFactory
import pl.sirox.nxrtchat.logging.logger

class CommandService @Inject constructor(
    private val loggerFactory: LoggerFactory,
    private val plugin: EnableBootstrap
) {

    private lateinit var liteCommands : LiteCommands<CommandSender>

    private val logger = loggerFactory.logger<CommandService>()

    fun run() {
        try {
            this.liteCommands = LiteBukkitFactory.builder("nxrtchat", plugin)
                .commands()
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