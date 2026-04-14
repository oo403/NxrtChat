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
    private val commandService: CommandService
) : JavaPlugin() {

    private lateinit var injector: Injector

    override fun onEnable() {
        try {
            injector = Guice.createInjector(
                PluginModule(this)
            )

            commandService.run()

            logger.info("Plugin enabled!")
        } catch (e: Exception) {

        }
    }

    override fun onDisable() {
        commandService.disable()

        logger.info("Plugin disabled!")
    }

}