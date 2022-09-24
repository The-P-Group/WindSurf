package me.lucasdang.grasscutter.packets;

import com.google.protobuf.ByteString;
import emu.grasscutter.net.packet.BasePacket;
import me.lucasdang.grasscutter.proto.WindSeedClientNotifyOuterClass.WindSeedClientNotify;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class PacketWindSeedClientNotify extends BasePacket {

    public PacketWindSeedClientNotify(File luaFile) {
        super(CustomPacketOpcodes.WindSeedClientNotify, true);

        try {
            byte[] encoded = Files.readAllBytes(luaFile.toPath());

            WindSeedClientNotify.AreaNotify.Builder areaNotify = WindSeedClientNotify.AreaNotify.newBuilder()
                    .setAreaId(1)
                    .setAreaType(1)
                    .setAreaCode(ByteString.copyFrom(encoded));

            WindSeedClientNotify.Builder proto = WindSeedClientNotify.newBuilder().setAreaNotify(areaNotify);

            this.setData(proto.build());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
