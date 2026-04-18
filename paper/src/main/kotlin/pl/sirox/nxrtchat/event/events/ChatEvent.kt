package pl.sirox.nxrtchat.event.events

import com.google.inject.Inject
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.event.EventHandler
import pl.sirox.nxrtchat.bootstrap.EnableBootstrap
import pl.sirox.nxrtchat.configuration.GeneralConfiguration
import pl.sirox.nxrtchat.configuration.MessageConfiguration
import pl.sirox.nxrtchat.event.CustomEvent
import pl.sirox.nxrtchat.regex.Regexes
import pl.sirox.nxrtchat.service.MessageService

class ChatEvent @Inject constructor(
    private val plugin: EnableBootstrap,
    private val messageService: MessageService,
    private val messageConfiguration: MessageConfiguration,
    private val generalConfiguration: GeneralConfiguration
) : CustomEvent {

    private val mentionRegex = Regexes.MENTION_REGEX

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val player = event.player
        val mini = MiniMessage.miniMessage()

        event.message(
            event.message().replaceText { builder ->
                builder.match(mentionRegex)

                builder.replacement { match, _ ->
                    val fullMatch = match.group()
                    val targetName = fullMatch.substring(1)

                    val target = plugin.server.getPlayer(targetName)

                    if (target != null) {
                        messageService.mention(target)

                        mini.deserialize(
                            generalConfiguration.mentionFormat,
                            Placeholder.unparsed("player", target.name)
                        )
                    } else {
                        Component.text(fullMatch)
                    }
                }
            }
        )

        event.renderer { _, _, message, _ ->
            mini.deserialize(
                generalConfiguration.globalFormat,
                Placeholder.component("message", message),
                Placeholder.unparsed("player", player.name)
            )
        }
    }

}