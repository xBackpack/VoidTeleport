package me.xbackpack.voidTeleport.commands

import me.xbackpack.voidTeleport.VoidTeleport
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

class VoidTeleportCommand :
    CommandExecutor,
    TabCompleter {
    override fun onCommand(
        sender: CommandSender,
        cmd: Command,
        label: String,
        args: Array<String>,
    ): Boolean {
        val versionMessage = "Running VoidTeleport ${VoidTeleport.VERSION}"

        if (args.isEmpty()) {
            sender.sendMessage(versionMessage)
            return true
        }

        if (args[0] == "reload") {
            VoidTeleport.instance.reloadConfig()
            sender.sendMessage("Reloaded!")
        } else if (args[0] == "resetconfig") {
            VoidTeleport.instance.saveConfig()
            sender.sendMessage("Reset!")
        } else if (args[0] == "version") {
            sender.sendMessage(versionMessage)
        }

        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        cmd: Command,
        label: String,
        args: Array<String>,
    ): List<String> {
        val collection = arrayListOf<String>()

        if (args.size == 1) {
            collection.addAll(
                arrayOf(
                    "reload",
                    "resetconfig",
                    "version",
                ),
            )
        }

        return collection
    }
}
