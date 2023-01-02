package me.lucasdang.grasscutter.commands;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import me.lucasdang.grasscutter.packets.PacketWindSeedClientNotify;

import java.io.File;
import java.util.List;

@Command(label = "windy", aliases = "wi", usage = "[luac path | luac file name in plugin's folder]")
public class windyCommand implements CommandHandler {
    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        if (args.size() < 1) {
            this.sendUsageMessage(sender);
            return;
        }

        String luaPath = args.get(0);

        String filepath = String.format("%s/WindSurf/%s.luac", Grasscutter.getConfig().folderStructure.plugins, luaPath);

        File pluginPathFile = new File(filepath);
        File luaFile = new File(luaPath);

        PacketWindSeedClientNotify packet;

        // Check in plugin's data folder first
        if (pluginPathFile.exists() && pluginPathFile.isFile()) {
            // if exist -> send
            packet = new PacketWindSeedClientNotify(pluginPathFile);

        } else if (luaFile.exists() && luaFile.isFile()) {
            packet = new PacketWindSeedClientNotify(luaFile);

        } else {
            CommandHandler.sendMessage(targetPlayer, "Not valid luac");
            return;
        }

        targetPlayer.sendPacket(packet);

        CommandHandler.sendMessage(targetPlayer, "Sent");
    }
}
