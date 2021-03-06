package io.battlerune.game.task.impl;

import io.battlerune.content.RoyaltyProgram;
import io.battlerune.game.task.Task;
import io.battlerune.game.world.World;
import io.battlerune.game.world.entity.mob.player.Player;

/**
 * Handles the royalty randomevent. Event is executed every 30 minutes and
 * awards active players with a royalty point.
 *
 * @author Daniel
 */
public class RoyaltyEvent extends Task {
	private int progress;

	/** Constructs a new <code>RoyaltyEvent</code>. */
	public RoyaltyEvent() {
		super(300);
		this.progress = 0;
	}

	@Override
	public void execute() {
		switch (progress++) {
		case 1:// 5 minutes
			break;
		case 2:// 10 minutes
			World.sendMessage("<icon=19><col=9E4629> RP: </col>Rewards will be given in <col=9E4629>20 </col>minutes.");
			break;
		case 3:// 15 minutes
			World.sendMessage("<icon=19><col=9E4629> RP: </col>Rewards will be given in <col=9E4629>15 </col>minutes.");
			break;
		case 4:// 20 minutes
			World.sendMessage("<icon=19><col=9E4629> RP: </col>Rewards will be given in <col=9E4629>10 </col>minutes.");
			break;
		case 5:// 25 minutes
			World.sendMessage("<icon=19><col=9E4629> RP: </col>Rewards will be given in <col=9E4629>5 </col>minutes.");
			break;
		case 6:// 30 minutes
			for (Player player : World.getPlayers()) {
				if (player.isBot || player.idle)
					return;
				RoyaltyProgram.append(player);
			}
			progress = 0;
			break;
		}

	}
}
