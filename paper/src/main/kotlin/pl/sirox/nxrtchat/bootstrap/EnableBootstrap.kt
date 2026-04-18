package pl.sirox.nxrtchat.bootstrap

import com.google.inject.Guice
import com.google.inject.Injector
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import pl.sirox.nxrtchat.logging.LoggerFactory
import pl.sirox.nxrtchat.logging.logger
import pl.sirox.nxrtchat.module.CommandModule
import pl.sirox.nxrtchat.module.ConfigurationModule
import pl.sirox.nxrtchat.module.EventModule
import pl.sirox.nxrtchat.module.PluginModule
import pl.sirox.nxrtchat.service.CommandService
import pl.sirox.nxrtchat.service.EventService
import pl.sirox.nxrtchat.service.PermissionService

class EnableBootstrap : JavaPlugin() {

    private lateinit var injector: Injector
    private lateinit var commandService: CommandService
    private lateinit var eventService: EventService
    private lateinit var loggerFactory: LoggerFactory
    private lateinit var logger: Logger
    private lateinit var permissionService: PermissionService

    override fun onEnable() {
        try {
            injector = Guice.createInjector(
                PluginModule(this),
                ConfigurationModule(dataFolder),
                CommandModule(),
                EventModule()
            )

            loggerFactory = injector.getInstance(LoggerFactory::class.java)
            logger = loggerFactory.logger<EnableBootstrap>()

            commandService = injector.getInstance(CommandService::class.java)
            commandService.run()

            eventService = injector.getInstance(EventService::class.java)
            eventService.run()

            permissionService = injector.getInstance(PermissionService::class.java)

            if (!permissionService.isLuckPermsInitialized()) {
                logger.warn("LuckPerms is not installed!")
            }

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