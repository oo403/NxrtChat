package pl.sirox.nxrtchat.command.commands.admin

import com.google.inject.Inject
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pl.sirox.nxrtchat.command.CustomCommand
import pl.sirox.nxrtchat.service.ConfigurationService

@Command(name = "nxrtchat")
class ReloadCommand @Inject constructor(
    private val configurationService: ConfigurationService
) : CustomCommand {

    @Execute(name = "reload")
    @Permission("nxrtchat.admin.reload")
    fun executeReload(@Context sender: CommandSender) {
        configurationService.reload(sender)
    }

}
