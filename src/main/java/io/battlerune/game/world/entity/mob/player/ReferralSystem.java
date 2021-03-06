package io.battlerune.game.world.entity.mob.player;

import java.util.concurrent.TimeUnit;

import io.battlerune.game.world.World;
import io.battlerune.game.world.entity.mob.player.persist.PlayerSerializer;
import io.battlerune.net.packet.out.SendInputMessage;
import io.battlerune.util.Referals;

/**
 * 
 * @author Adam_#6723
 * @author Teek
 *
 */

public class ReferralSystem {

	private static final int TOTAL_POINTS = 1;
	private static final int TOTAL_REFFERALS = 1;

	public static void handleReferral(Player player) {

		player.send(new SendInputMessage("Who refered/Invited you to Runity? Mention them for free Donator Rank!", 20, input -> {
			try {
				linkReferral(player, input);
			} catch (Exception e) {
				player.sendMessage("We were unable to link you with your referral!");
				System.err.println("User not online to link refferal.");
			}
		}));
	}

	private static void linkReferral(Player refer, String referalName) {

		if (!PlayerSerializer.saveExists(referalName) || referalName == null || referalName.isEmpty()) 
			return;
		
		/*if (Referals.hasRefered(refer.registeredMac)) {
			refer.message("<col=FF0019>You were not rewarded since you share the same Mac Address.");
			return;
		}*/
		Player other = World.getPlayerByName(referalName);
		
		
		if (other == null) {
			try {
				other = PlayerSerializer.loadPlayer(referalName);
				other.setRefferalPoints(other.getReferralPoints() + 1);
				PlayerSerializer.saveOffline(other);
			} catch (Exception e) {
				System.out.println("error loading player..");
				return;
			}
		} else {
		    if (other.lastHost.equalsIgnoreCase(refer.lastHost) || refer.lastHost.equalsIgnoreCase(other.lastHost)) {
				other.message("<col=FF0019>You were not rewarded since you share the same IP Address.");
				refer.message("<col=FF0019>You were not rewarded since you share the same IP Address.");
				return;
			}
			if (!other.referaltime.elapsed(1, TimeUnit.MINUTES)) {
				other.message("You are getting spam refered, therefore it didn't count.");
				return;
			}
			other.referaltime.reset();
		    other.totalRefferals += TOTAL_REFFERALS;
			other.refferalpoint += TOTAL_POINTS;
			other.sendMessage("You have been given " + TOTAL_POINTS + " for refering " + refer.getUsername() + ".");
			other.inventory.add(290, 1);
			other.sendMessage("You have also been given an AvO Box for reffering someone!");
		}
		Referals.addToList(refer.registeredMac);
		refer.sendMessage("Thank you for setting a referal!");
		refer.refferalpoint += TOTAL_POINTS;
		refer.inventory.add(290, 1);
		refer.inventory.add(620, 1);
		refer.sendMessage("You have recieved Inferno Box by Joining via an existing member on Runity!");
		refer.sendMessage("You have been given a referal  & Donator Rank, speak to ref to use these points");
        refer.sendMessage("Refer your friends over so you and them can both be given a referal point!");
        refer.sendMessage("Relog to update your rank!");
	}
}
