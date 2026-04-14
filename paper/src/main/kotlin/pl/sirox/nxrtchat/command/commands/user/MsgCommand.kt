package pl.sirox.nxrtchat.command.commands.user

import dev.rollczi.litecommands.annotations.argument.Arg
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.join.Join
import org.bukkit.entity.Player
import pl.sirox.nxrtchat.command.CustomCommand

@Command(name = "msg")
class MsgCommand : CustomCommand {

    @Execute
    fun executeMsg(@Context sender: Player, @Arg target: Player, @Join message: String) {
        sender.sendMessage("Message sent to ${target.name}: $message")
        target.sendMessage("Message from ${sender.name}: $message")
    }

}