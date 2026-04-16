package pl.sirox.nxrtchat.command.handler

import com.google.inject.Inject
import dev.rollczi.litecommands.handler.result.ResultHandlerChain
import dev.rollczi.litecommands.invocation.Invocation
import dev.rollczi.litecommands.permission.MissingPermissions
import dev.rollczi.litecommands.permission.MissingPermissionsHandler
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.command.CommandSender
import pl.sirox.nxrtchat.configuration.MessageConfiguration

class MissingPermissionHandler @Inject constructor(
    private val messageConfiguration: MessageConfiguration
) : MissingPermissionsHandler<CommandSender> {

    override fun handle(
        invocation: Invocation<CommandSender?>?,
        missingPermissions: MissingPermissions?,
        chain: ResultHandlerChain<CommandSender?>?
    ) {
        val sender = invocation?.sender()
        val permissions = missingPermissions?.asJoinedText() ?: ""

        val message = messageConfiguration.missingPermission
        val formattedMessage = MiniMessage.miniMessage().deserialize(message,
            Placeholder.unparsed("permissions", permissions))

        sender?.sendMessage(formattedMessage)
    }

}