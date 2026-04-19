package pl.sirox.nxrtchat.event.events

import com.google.inject.Inject
import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.Material
import org.bukkit.event.EventHandler
import pl.sirox.nxrtchat.bootstrap.EnableBootstrap
import pl.sirox.nxrtchat.configuration.GeneralConfiguration
import pl.sirox.nxrtchat.configuration.MessageConfiguration
import pl.sirox.nxrtchat.event.CustomEvent
import pl.sirox.nxrtchat.regex.Regexes
import pl.sirox.nxrtchat.service.MessageService
import pl.sirox.nxrtchat.service.PermissionService

class ChatEvent @Inject constructor(
    private val plugin: EnableBootstrap,
    private val messageService: MessageService,
    private val messageConfiguration: MessageConfiguration,
    private val generalConfiguration: GeneralConfiguration,
    private val permissionService: PermissionService
) : CustomEvent {

    private val mentionRegex = Regexes.MENTION_REGEX

    @EventHandler
    fun onChat(event: AsyncChatEvent) {
        val player = event.player
        val mini = MiniMessage.miniMessage()

        event.message(
            event.message()
                .replaceText { builder ->
                    builder.match(mentionRegex)

                    builder.replacement { match, _ ->
                        val fullMatch = match.group()
                        val targetName = fullMatch.substring(1)

                        val target = plugin.server.getPlayer(targetName)

                        if (target != null) {
                            messageService.mention(target)

                            mini.deserialize(
                                generalConfiguration.mentionFormat,
                                Placeholder.parsed("player", target.name)
                            )
                        } else {
                            Component.text(fullMatch)
                        }
                    }
                }

                .replaceText { builder ->
                    builder.match(Regex(Regex.escape(generalConfiguration.itemPlaceholder), RegexOption.IGNORE_CASE).toPattern())

                    val item = player.inventory.itemInMainHand

                    builder.replacement { match, _ ->
                        if (generalConfiguration.itemShareFeature) {
                            if (player.inventory.itemInMainHand.type != Material.AIR) {
                                mini.deserialize(
                                    generalConfiguration.itemFormat,
                                    Placeholder.parsed("item", MiniMessage.miniMessage().serialize(item.displayName().hoverEvent(item.asHoverEvent())))
                                )
                            } else {
                                player.sendMessage(MiniMessage.miniMessage().deserialize(messageConfiguration.nothingInHand))
                                Component.text(match.group())
                            }
                        } else {
                            Component.text(match.group())
                        }
                    }
                }
        )

        val format = if (permissionService.isLuckPermsInitialized() && generalConfiguration.format == "ranks") {
            generalConfiguration.ranksFormat[permissionService.getPlayerRank(player)]?.format ?: generalConfiguration.globalFormat
        } else {
            generalConfiguration.globalFormat
        }

        event.renderer { _, _, message, _ ->
            mini.deserialize(
                format,
                Placeholder.component("message", message),
                Placeholder.parsed("player", player.name)
            )
        }
    }

}