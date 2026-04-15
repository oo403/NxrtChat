package pl.sirox.nxrtchat.command.commands.user

import com.google.inject.Inject
import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.join.Join
import org.bukkit.entity.Player
import pl.sirox.nxrtchat.command.CustomCommand
import pl.sirox.nxrtchat.service.MessageService

@Command(name = "msg")
class MsgCommand @Inject constructor(
    private val messageService: MessageService
) : CustomCommand {

    @Execute
    fun executeMsg(@Context sender: Player, @Arg target: Player, @Join message: String) {
        messageService.sendMsg(sender, target, message)
    }

}