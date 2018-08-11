package io.battlerune.game.world.entity.mob.npc.drop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import io.battlerune.game.world.entity.mob.npc.dropchance.DropChanceData;
import io.battlerune.game.world.entity.mob.npc.dropchance.DropChanceHandler;
import io.battlerune.game.world.entity.mob.player.Player;
import io.battlerune.game.world.items.Item;
import io.battlerune.util.RandomUtils;

/**
 * The class which represents a npc drop table.
 *
 * @author <a href="http://www.rune-server.org/members/stand+up/">Stand Up</a>
 * @since 29-1-2017.
 */
public final class NpcDropTable {

	/** The npc ids that share this drop table. */
	public final int[] npcIds;

	/** Determines if this table has access to the rare drop table. */
	private final boolean rareDropTable;

	/** The cached array of {@link NpcDrop}s. */
	public final NpcDrop[] drops;

	private final NpcDrop[] always;
	private final NpcDrop[] common;
	private final NpcDrop[] uncommon;
	private final NpcDrop[] rare;
	private final NpcDrop[] veryRare;

	/** Constructs a new {@link NpcDropTable}. */
	public NpcDropTable(int[] npcIds, boolean rareDropTable, NpcDrop[] npcDrops, NpcDrop[] always, NpcDrop[] common,
			NpcDrop[] uncommon, NpcDrop[] rare, NpcDrop[] veryRare) {
		this.npcIds = npcIds;
		this.rareDropTable = rareDropTable;
		this.always = always;
		this.common = common;
		this.uncommon = uncommon;
		this.rare = rare;
		this.veryRare = veryRare;
		this.drops = npcDrops;
	}

	public List<NpcDrop> generate(Player player) {
		LinkedList<NpcDrop> items = new LinkedList<>();
		int roll = RandomUtils.inclusive(1300);

		for (DropChanceData data : DropChanceData.values()) {
			for (Item item : player.equipment) {
				if (item != null) {
					if (data.getItemId() == item.getId()) {
						roll = (int) (new DropChanceHandler(player, data).modify() * roll);
					}
				}
			}
		}

		if (veryRare.length > 0 && roll < 2) {
			items.addFirst(RandomUtils.random(veryRare));
		} else if (rare.length > 0 && roll < 45) {
			items.addFirst(RandomUtils.random(rare));
		} else {
			if (common.length > 0 && roll < 850) {
				items.addFirst(RandomUtils.random(common));
			}
			if (uncommon.length > 0 && roll < 350) {
				items.addFirst(RandomUtils.random(uncommon));
			}
		}

		for (NpcDrop drop : always) {
			items.addFirst(drop);
		}
		return items;
	}

}
