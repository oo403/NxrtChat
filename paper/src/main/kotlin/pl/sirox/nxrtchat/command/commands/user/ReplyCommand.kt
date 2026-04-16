package pl.sirox.nxrtchat.command.commands.user

import com.google.inject.Inject
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.join.Join
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import pl.sirox.nxrtchat.command.CustomCommand
import pl.sirox.nxrtchat.configuration.MessageConfiguration
import pl.sirox.nxrtchat.service.MessageService
import pl.sirox.nxrtchat.service.PlayerService

@Command(name = "reply", aliases = ["r"])
class ReplyCommand @Inject constructor(
    private val playerService: PlayerService,
    private val messageService: MessageService,
    private val messageConfiguration: MessageConfiguration
) : CustomCommand {

    @Execute
    fun executeReply(@Context sender: Player, @Join("message") message: String) {
        val lastMessageNotFoundMessage = MiniMessage.miniMessage().deserialize(messageConfiguration.lastMessageNotFound)

        val targetUUID = playerService.getLastMsg(sender.uniqueId)

        if (targetUUID == null) {
            sender.sendMessage(lastMessageNotFoundMessage)
        } else {
            val target = Bukkit.getPlayer(targetUUID)

            if (target == null) {
                sender.sendMessage(lastMessageNotFoundMessage)
            } else {
                messageService.sendMsg(sender, target, message)
            }
        }
    }

}
