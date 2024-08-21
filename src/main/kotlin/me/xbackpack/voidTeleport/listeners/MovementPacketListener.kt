package me.xbackpack.voidTeleport.listeners

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import me.xbackpack.voidTeleport.VoidTeleport
import org.bukkit.Bukkit
import org.bukkit.Location

object MovementPacketListener {
    fun registerMovementPacketListener(manager: ProtocolManager) {
        manager.addPacketListener(
            object : PacketAdapter(
                VoidTeleport.instance,
                ListenerPriority.NORMAL,
                PacketType.Play.Client.POSITION,
                PacketType.Play.Client.POSITION_LOOK,
            ) {
                override fun onPacketReceiving(event: PacketEvent) {
                    val playerY = event.packet.doubles.read(1)
                    val player = event.player

                    if (!VoidTeleport.worldList.contains(player.world)) return

                    val worlds = VoidTeleport.instance.config.getConfigurationSection("worlds")!!

                    val world = worlds.getConfigurationSection(player.world.name)!!

                    val yLevel = world.getDouble("y-level")

                    if (playerY > yLevel) return

                    val kill = worlds.getBoolean("kill")
                    val teleport = worlds.getBoolean("teleport")

                    if (kill) {
                        player.health = 0.0
                    } else if (teleport) {
                        val teleportLocation =
                            world.getConfigurationSection("teleport-location") ?: run {
                                VoidTeleport.instance.logger.warning("")
                                return
                            }
                        Bukkit.getScheduler().runTask(
                            VoidTeleport.instance,
                            Runnable {
                                player.teleport(
                                    Location(
                                        player.world,
                                        teleportLocation.getDouble("x"),
                                        teleportLocation.getDouble("y"),
                                        teleportLocation.getDouble("z"),
                                        teleportLocation.getDouble("yaw").toFloat(),
                                        teleportLocation.getDouble("pitch").toFloat(),
                                    ),
                                )
                            },
                        )
                    }
                }
            },
        )
    }
}
