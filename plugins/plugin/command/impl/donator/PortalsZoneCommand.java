package plugin.command.impl.donator;

import io.battlerune.Config;
import io.battlerune.content.command.Command;
import io.battlerune.content.skill.impl.magic.teleport.Teleportation;
import io.battlerune.game.world.entity.mob.player.Player;
import io.battlerune.game.world.entity.mob.player.PlayerRight;
import io.battlerune.net.packet.out.SendMessage;

/**
 * 
 * @author Adam_#6723
 *
 */
public class PortalsZoneCommand implements Command {

	@Override
	public void execute(Player player, String[] parts) {
		// TODO Auto-generated method stub
		Teleportation.teleport(player, Config.PORTAL_ZONE);
		player.send(new SendMessage("You have teleported to the Portal Zone!"));
	}

	@Override
	public boolean canUse(Player player) {
		if (PlayerRight.isDonator(player) || PlayerRight.isSuper(player) || PlayerRight.isExtreme(player)
				|| PlayerRight.isElite(player) || PlayerRight.isKing(player) || PlayerRight.isSupreme(player)) {
			return true;
		} else {
			player.speak("I just tried to do something silly!");
			return false;
		}
	}

}
