package plugin.command.impl.moderator;

import io.battlerune.content.command.Command;
import io.battlerune.game.world.World;
import io.battlerune.game.world.entity.mob.player.Player;
import io.battlerune.game.world.entity.mob.player.PlayerRight;
import io.battlerune.game.world.entity.mob.player.punishments.PunishmentExecuter;
import io.battlerune.net.packet.out.SendMessage;

public class Unbancommand implements Command {

	@Override
	public void execute(Player player, String command, String[] parts) {
		final String other1 = String.format(parts[0].replaceAll("_", " "));
		final String other = command.substring(5, command.length());

		if (!PunishmentExecuter.banned(other)) {
			player.send(new SendMessage("Player " + other + " is not banned!"));
			return;
		}
		PunishmentExecuter.unban(other);
		player.send(new SendMessage("Player " + other + " was successfully unbanned. Command logs written."));
	}

	@Override
	public boolean canUse(Player player) {
		return PlayerRight.isManagement(player);
	}

}
