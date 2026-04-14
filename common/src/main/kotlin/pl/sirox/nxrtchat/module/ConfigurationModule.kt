package pl.sirox.nxrtchat.module

import com.google.inject.AbstractModule
import eu.okaeri.configs.ConfigManager
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer
import pl.sirox.nxrtchat.configuration.GeneralConfiguration
import pl.sirox.nxrtchat.configuration.MessageConfiguration
import java.io.File

class ConfigurationModule(
    private val dataFolder: File
) : AbstractModule() {

    override fun configure() {

        val generalConfigurationFile = File(dataFolder, "settings.yml")

        val generalConfiguration = ConfigManager.create(GeneralConfiguration::class.java) {
            it.configure { opt ->
                opt.configurer(YamlBukkitConfigurer())
                opt.bindFile(generalConfigurationFile)
                opt.removeOrphans(true)
            }
            it.saveDefaults()
            it.load(true)
        }

        val messageConfigurationFile = File(dataFolder, "messages.yml")

        val messageConfiguration = ConfigManager.create(MessageConfiguration::class.java) {
            it.configure { opt ->
                opt.configurer(YamlBukkitConfigurer())
                opt.bindFile(messageConfigurationFile)
                opt.removeOrphans(true)
            }
            it.saveDefaults()
            it.load(true)
        }

        bind(GeneralConfiguration::class.java).toInstance(generalConfiguration)
        bind(MessageConfiguration::class.java).toInstance(messageConfiguration)
    }

}