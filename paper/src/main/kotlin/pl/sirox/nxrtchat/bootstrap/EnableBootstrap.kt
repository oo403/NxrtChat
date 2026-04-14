package pl.sirox.nxrtchat.bootstrap

import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector
import org.bukkit.plugin.java.JavaPlugin
import pl.sirox.nxrtchat.logging.LoggerFactory
import pl.sirox.nxrtchat.logging.logger
import pl.sirox.nxrtchat.module.PluginModule
import pl.sirox.nxrtchat.service.CommandService

class EnableBootstrap @Inject constructor(
    private val commandService: CommandService,
    private val loggerFactory: LoggerFactory
) : JavaPlugin() {

    private lateinit var injector: Injector

    private val logger = loggerFactory.logger<EnableBootstrap>()

    override fun onEnable() {
        try {
            injector = Guice.createInjector(
                PluginModule(this)
            )

            commandService.run()

            logger.info("Plugin enabled!")
        } catch (e: Exception) {
            logger.error("Failed to enable plugin", e)
        }
    }

    override fun onDisable() {
        commandService.disable()

        logger.info("Plugin disabled!")
    }

}