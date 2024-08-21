package me.xbackpack.voidTeleport

import com.comphenix.protocol.ProtocolLibrary
import me.xbackpack.voidTeleport.commands.VoidTeleportCommand
import me.xbackpack.voidTeleport.listeners.MovementPacketListener
import org.bukkit.Bukkit
import org.bukkit.World
import org.bukkit.plugin.java.JavaPlugin

class VoidTeleport : JavaPlugin() {
    companion object {
        lateinit var instance: VoidTeleport
            private set
        const val VERSION = "0.0.1"
        val worldList = mutableListOf<World>()
    }

    override fun onEnable() {
        instance = this
        saveDefaultConfig()

        if (!checkWorlds()) {
            server.pluginManager.disablePlugin(this)
            return
        }

        val protocolManager = ProtocolLibrary.getProtocolManager()
        MovementPacketListener.registerMovementPacketListener(protocolManager)

        getCommand("voidteleport")!!.setExecutor(VoidTeleportCommand())

        logger.info("Loaded!")
    }

    private fun checkWorlds(): Boolean {
        val worlds =
            config.getConfigurationSection("worlds") ?: run {
                logger.warning("Unable to find 'worlds' section in config.yml, disabling plugin.")
                return false
            }

        worlds.getKeys(false).forEach { worldName ->
            val world =
                Bukkit.getWorld(worldName) ?: run {
                    logger.warning("Unable to find world named '$worldName', ignoring it.")
                    return true
                }

            worldList.add(world)
        }

        return true
    }
}
