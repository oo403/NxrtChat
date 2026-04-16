package pl.sirox.nxrtchat.command.handler

import com.google.inject.Inject
import dev.rollczi.litecommands.handler.result.ResultHandlerChain
import dev.rollczi.litecommands.invalidusage.InvalidUsage
import dev.rollczi.litecommands.invalidusage.InvalidUsageHandler
import dev.rollczi.litecommands.invocation.Invocation
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.CommandSender
import pl.sirox.nxrtchat.configuration.MessageConfiguration

class InvalidUsageHandler @Inject constructor(
    private val messageConfiguration: MessageConfiguration
) : InvalidUsageHandler<CommandSender> {

    override fun handle(
        invocation: Invocation<CommandSender?>?,
        result: InvalidUsage<CommandSender?>?,
        chain: ResultHandlerChain<CommandSender?>?
    ) {
        val sender = invocation?.sender()

        val message = messageConfiguration.invalidUsage
        val formattedMessage = MiniMessage.miniMessage().deserialize(message)

        sender?.sendMessage(formattedMessage)
    }

}