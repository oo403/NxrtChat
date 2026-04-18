package pl.sirox.nxrtchat.module

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import org.reflections.Reflections
import pl.sirox.nxrtchat.event.CustomEvent

class EventModule : AbstractModule() {

    override fun configure() {

        val events = Reflections("pl.sirox.nxrtchat.event.events")
        val eventClasses = events.getSubTypesOf(CustomEvent::class.java)

        val eventBinder = Multibinder.newSetBinder(binder(), CustomEvent::class.java)

        eventClasses.forEach {
            eventBinder.addBinding().to(it)
        }

    }

}