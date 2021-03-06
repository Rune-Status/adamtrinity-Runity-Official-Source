package io.battlerune.net.packet.out;

import io.battlerune.content.clanchannel.ClanRank;
import io.battlerune.game.world.entity.mob.player.Player;
import io.battlerune.net.packet.OutgoingPacket;
import io.battlerune.net.packet.PacketType;

public class SendClanDetails extends OutgoingPacket {

	private final String name;
	private final String message;
	private final String clan;
	private final ClanRank rank;

	public SendClanDetails(String name, String message, String clanName, ClanRank rank) {
		super(217, PacketType.VAR_SHORT);
		this.name = name;
		this.message = message;
		this.clan = clanName;
		this.rank = rank;
	}

	public SendClanDetails(String message, String clan, ClanRank rank) {
		this("", message, clan, rank);
	}

	public SendClanDetails(String message, String clan) {
		this("", message, clan, ClanRank.MEMBER);
	}

	@Override
	public boolean encode(Player player) {
		builder.writeString(name).writeString(message).writeString(clan).writeShort(rank.rank);
		return true;
	}

}
