package plugin.command.impl.moderator;

import io.battlerune.content.command.Command;
import io.battlerune.game.world.World;
import io.battlerune.game.world.entity.mob.player.Player;
import io.battlerune.game.world.entity.mob.player.PlayerRight;

/**
 * 
 * @author Adam_#6723
 *
 */

public class UnmuteCommand implements Command {

	@Override
	public void execute(Player player, String[] command) {
		     
		     final String name = String.format(command[1]);
             World.search(name.toString()).ifPresent(other -> {
                 other.punishment.unmute();
                 other.dialogueFactory.sendStatement("@or2@You have been unmuted!").execute();
                 player.message("@or2@unmute was complete");
                 
             });
         } 

	

	@Override
	public boolean canUse(Player player) {
		if (PlayerRight.isManagement(player)) {
			return true;
		}
		player.speak("Hey everyone, i just tried doing something silly.");
		return false;
	}

}