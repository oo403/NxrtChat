package pl.sirox.nxrtchat.service

import com.google.inject.Inject
import org.bukkit.event.Listener
import pl.sirox.nxrtchat.bootstrap.EnableBootstrap
import pl.sirox.nxrtchat.event.CustomEvent
import pl.sirox.nxrtchat.logging.LoggerFactory
import pl.sirox.nxrtchat.logging.logger

class EventService @Inject constructor(
    private val plugin: EnableBootstrap,
    private val events: Set<CustomEvent>,
    private val loggerFactory: LoggerFactory
) {

    private val logger = loggerFactory.logger<EventService>()

    fun run() {

        events.forEach { event ->
            try {
                plugin.server.pluginManager.registerEvents(event, plugin)
                logger.info("Registered event: ${event.javaClass.simpleName}")
            } catch (e: Exception) {
                logger.error("Failed to register event: ${event.javaClass.simpleName}", e)
                e.printStackTrace()
            }
        }

    }

}