package pl.sirox.nxrtchat.service

import com.google.inject.Inject
import dev.triumphteam.gui.builder.item.ItemBuilder
import dev.triumphteam.gui.guis.Gui
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import org.bukkit.block.ShulkerBox
import org.bukkit.entity.Player
import pl.sirox.nxrtchat.configuration.GeneralConfiguration

class InventoryService @Inject constructor(
    private val generalConfiguration: GeneralConfiguration
) {

    fun buildInventory(sender: Player, target: Player, title: String, rows: Int, click: Boolean): Gui {
        val gui = Gui.gui()
            .title(MiniMessage.miniMessage().deserialize(title, Placeholder.unparsed("player", sender.name)))
            .rows(rows)
            .create()

        if (click) {
            gui.setDefaultClickAction {
                it.isCancelled = true
            }
        }

        return gui
    }

    fun openPlayerShareInventory(sender: Player, target: Player) {
        val gui = buildInventory(sender, target, generalConfiguration.invShareTitle, 4, false)

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
        val gui = buildInventory(sender, target, generalConfiguration.ecShareTitle, 4, false)

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