package pl.sirox.nxrtchat.bootstrap

import com.google.inject.Guice
import com.google.inject.Inject
import com.google.inject.Injector
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import pl.sirox.nxrtchat.logging.LoggerFactory
import pl.sirox.nxrtchat.logging.logger
import pl.sirox.nxrtchat.module.PluginModule
import pl.sirox.nxrtchat.service.CommandService

class EnableBootstrap : JavaPlugin() {

    private lateinit var injector: Injector
    private lateinit var commandService: CommandService
    private lateinit var loggerFactory: LoggerFactory
    private lateinit var logger: Logger

    override fun onEnable() {
        try {
            injector = Guice.createInjector(
                PluginModule(this)
            )

            loggerFactory = injector.getInstance(LoggerFactory::class.java)
            logger = loggerFactory.logger<EnableBootstrap>()

            commandService = injector.getInstance(CommandService::class.java)
            commandService.run()

            logger.info("Plugin enabled!")
        } catch (e: Exception) {
            super.getLogger().severe("Failed to enable plugin: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun onDisable() {
        try {
            commandService.disable()
            logger.info("Plugin disabled!")
        } catch (e: Exception) {
            super.getLogger().severe("Failed to disable plugin: ${e.message}")
            e.printStackTrace()
        }
    }

}