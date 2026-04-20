package pl.sirox.nxrtchat.service

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import xyz.xenondevs.invui.gui.Gui
import xyz.xenondevs.invui.inventory.ReferencingInventory
import xyz.xenondevs.invui.inventory.VirtualInventory
import xyz.xenondevs.invui.inventory.event.UpdateReason
import xyz.xenondevs.invui.window.Window

class InventoryService {

    fun openPlayerInventory(sender: Player, target: Player) {

        val inventory = ReferencingInventory.fromStorageContents(sender.inventory).items
        val inventoryHotbar = inventory.copyOfRange(0, 9)
        val inventoryStorage = inventory.copyOfRange(9, 36)

        val hotbar = VirtualInventory(9)
        val storage = VirtualInventory(36)

        inventoryHotbar.forEachIndexed { index, itemStack ->
            hotbar.setItem(UpdateReason.SUPPRESSED, index, itemStack)
        }

        inventoryStorage.forEachIndexed { index, itemStack ->
            storage.setItem(UpdateReason.SUPPRESSED, index, itemStack)
        }

        val lowerGui = Gui.normal()
            .setStructure(
                "I I I I I I I I I",
                "I I I I I I I I I",
                "I I I I I I I I I",
                "H H H H H H H H H"
            )
            .addIngredient('H', hotbar)
            .addIngredient('I', storage)

        val upperGui = Gui.normal()
            .setStructure(
                "# # # # #"
            )

        val window = Window.split()
            .setLowerGui(lowerGui)
            .setUpperGui(upperGui)
            .open(target)
    }

    fun openEnderChestInventory(sender: Player, target: Player) {

        val enderChest = ReferencingInventory.fromStorageContents(sender.enderChest).items
        val enderChestStorage = enderChest.copyOfRange(0, 27)

        val enderChestInventory = VirtualInventory(27)

        enderChestStorage.forEachIndexed { index, itemStack ->
            enderChestInventory.setItem(UpdateReason.SUPPRESSED, index, itemStack)
        }


        val upperGui = Gui.normal()
            .setStructure(
                "I I I I I I I I I",
                "I I I I I I I I I",
                "I I I I I I I I I"
            )
            .addIngredient('I', enderChestInventory)

        val window = Window.single()
            .setGui(upperGui)
            .open(target)
    }

}