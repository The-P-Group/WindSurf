package me.lucasdang.grasscutter.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.game.player.Player;
import me.lucasdang.grasscutter.packets.PacketWindSeedClientNotify;

import java.io.File;
import java.util.List;

@Command(label = "windy", aliases = "wi", usage = "[luac path]")
public class windyCommand implements CommandHandler {
    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        if (args.size() < 1) {
            this.sendUsageMessage(sender);
            return;
        }

        String luaPath = args.get(0);

        File luaFile = new File(luaPath);

        if (!luaFile.exists() || !luaFile.isFile()) {
            CommandHandler.sendMessage(targetPlayer, "Not valid luac");
            return;
        }

        var packet = new PacketWindSeedClientNotify(luaFile);

        System.out.println(packet);

        targetPlayer.sendPacket(packet);
        sender.sendPacket(packet);

        CommandHandler.sendMessage(targetPlayer, "Send success");
    }
}
