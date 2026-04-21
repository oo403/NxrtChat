package pl.sirox.nxrtchat.service

import com.google.inject.Inject
import dev.triumphteam.gui.builder.item.ItemBuilder
import dev.triumphteam.gui.guis.Gui
import dev.triumphteam.gui.guis.GuiItem
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.entity.Player
import pl.sirox.nxrtchat.configuration.GeneralConfiguration

class InventoryService @Inject constructor(
    private val generalConfiguration: GeneralConfiguration
) {

    fun openPlayerShareInventory(sender: Player, target: Player) {
        val gui = Gui.gui()
            .title(MiniMessage.miniMessage().deserialize(generalConfiguration.invShareTitle, Placeholder.unparsed("player", sender.name)))
            .rows(4)
            .create()

        gui.setDefaultClickAction {
            it.isCancelled = true
        }

        val items = sender.inventory.contents

        items.forEachIndexed { index, item ->
            if (item != null) {
                val guiItem = ItemBuilder.from(item).asGuiItem()
                gui.setItem(index, guiItem)
            }
        }

        gui.open(target)
    }

    fun openPlayerShareEnderChest(sender: Player, target: Player) {
        val gui = Gui.gui()
            .title(MiniMessage.miniMessage().deserialize(generalConfiguration.ecShareTitle, Placeholder.unparsed("player", sender.name)))
            .rows(4)
            .create()

        gui.setDefaultClickAction {
            it.isCancelled = true
        }

        val items = sender.enderChest.contents

        items.forEachIndexed { index, item ->
            if (item != null) {
                val guiItem = ItemBuilder.from(item).asGuiItem()
                gui.setItem(index, guiItem)
            }
        }

        gui.open(target)
    }

}