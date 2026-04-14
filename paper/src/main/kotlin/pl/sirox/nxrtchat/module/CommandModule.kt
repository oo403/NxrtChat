package pl.sirox.nxrtchat.module

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import org.reflections.Reflections
import pl.sirox.nxrtchat.command.CustomCommand

class CommandModule : AbstractModule() {

    override fun configure() {

        val commands = Reflections("pl.sirox.nxrtchat.command")
        val commandClasses = commands.getSubTypesOf(CustomCommand::class.java)

        val commandBinder = Multibinder.newSetBinder(binder(), CustomCommand::class.java)

        commandClasses.forEach {
            commandBinder.addBinding().to(it)
        }
    }

}