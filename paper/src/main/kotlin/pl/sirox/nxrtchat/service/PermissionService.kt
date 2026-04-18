package pl.sirox.nxrtchat.service

import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import org.bukkit.entity.Player


class PermissionService {

    fun isLuckPermsInitialized(): Boolean {
        return Bukkit.getPluginManager().getPlugin("LuckPerms") != null
    }

    fun getPlayerRank(player: Player): String {
        if (isLuckPermsInitialized()) {
            val provider = Bukkit.getServicesManager().getRegistration<LuckPerms?>(LuckPerms::class.java)
            if (provider != null) {
                val api = provider.getProvider()

                val user = api.userManager.getUser(player.uniqueId)
                val rank = user?.primaryGroup ?: "default"

                return rank
            }
        }

        return "default"
    }

}