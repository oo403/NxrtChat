package pl.sirox.nxrtchat.module

import com.google.inject.AbstractModule
import pl.sirox.nxrtchat.bootstrap.EnableBootstrap

class PluginModule(
    private val plugin: EnableBootstrap
) : AbstractModule() {

    override fun configure() {
        bind(EnableBootstrap::class.java).toInstance(plugin)
    }

}