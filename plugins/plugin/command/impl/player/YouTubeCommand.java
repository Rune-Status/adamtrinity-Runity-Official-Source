package plugin.command.impl.player;

import io.battlerune.content.command.Command;
import io.battlerune.game.world.entity.mob.player.Player;
import io.battlerune.net.packet.out.SendURL;

/**
 * @author Adam_#6723
 */

public class YouTubeCommand implements Command {

	@Override
	public void execute(Player player, String command, String[] parts) {
		player.send(new SendURL("https://www.youtube.com/watch?v=RamxlwBPe8s"));
		player.message("Be sure to check the description for the giveaway!");
	}

	@Override
	public boolean canUse(Player player) {
		return true;
	}

}
