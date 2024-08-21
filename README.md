# VoidTeleport

VoidTeleport is a small and lightweight way to customize the void
on your Minecraft server. It provides a neat way to configure void-related
actions, allowing you to add and configure as many worlds as you want.

## Features

1. You can change the y-level which marks the start of the void.
2. You can choose whether to kill the player on contact, or to
   teleport them to a specified location.
3. You can change the teleport location.
4. You can add as many worlds as you would like.

All of this is changeable in the config file.

## How it Works

VoidTeleport uses ProtocolLib to directly look for the player's
movement packet

## Getting Started

1. Download the plugin from the [Releases](https://github.com/xBackpack/VoidTeleport/releases)
page and drop it into your server plugins folder.
2. Start your server.
3. When your server is open, and you have joined, run ```/voidteleport version```.
4. Make sure that it is the correct plugin version for the minecraft version you are using.
5. Then, go into the server files, go into the ```plugins``` directory.
6. Find the ```VoidTeleport``` folder and open it.
7. Go into the ```config.yml``` file and **follow the instructions**.

## Dependencies

1. Requires [ProtocolLib 5.3.0](https://www.spigotmc.org/resources/protocollib.1997/)
   or later (Dev build for 1.21)