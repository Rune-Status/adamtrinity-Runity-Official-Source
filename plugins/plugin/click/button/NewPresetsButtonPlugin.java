package plugin.click.button;

import io.battlerune.content.dialogue.DialogueFactory;
import io.battlerune.content.preload.PreLoadData;
import io.battlerune.content.preload.PreLoadManager;
import io.battlerune.content.presets.PresetDialogue;
import io.battlerune.content.presets.PresetManager;
import io.battlerune.content.skill.impl.magic.Spellbook;
import io.battlerune.game.plugin.PluginContext;
import io.battlerune.game.world.entity.mob.player.Player;
import io.battlerune.game.world.items.Item;

/**
 * Handles preset interface
 * @author Nerik#8690
 * 
 */

public class NewPresetsButtonPlugin extends PluginContext {


	/** Melee126Equipment **/
	public Item[] Melee126Equipment() {
		return new Item[] { new Item(10828), new Item(1712), new Item(4355), new Item(4587), new Item(1127),
				new Item(8850), new Item(1079), new Item(3105), new Item(2250), new Item(7461) };
	}

	/** Hybrid Inventory! Array **/
	public Item[] Hybrid126Inventory() {
		return new Item[] { new Item(12695), new Item(3024, 2), new Item(9075, 80), new Item(1215), new Item(3024),
				new Item(6685), new Item(560, 40), new Item(391), new Item(391), new Item(391), new Item(557, 200),
				new Item(391, 12), new Item(3144, 4),

		};
	}

	/** Melee126Equipment **/
	public Item[] PureEquipment() {
		return new Item[] { new Item(1169), new Item(4395), new Item(544), new Item(1725), new Item(892, 100),
				new Item(857), new Item(1099), new Item(628), new Item(1065), new Item(2550) };
	}

	/** Hybrid Inventory! Array **/
	public Item[] PureInventory() {
		return new Item[] { new Item(113), new Item(1319), new Item(373, 26),

		};
	}

	/** Hybrid Equipment Array **/
	public Item[] Hybrid126Equipment() {
		return new Item[] { new Item(10828), new Item(2412), new Item(1712), new Item(4675), new Item(4091),
				new Item(3842), new Item(4093), new Item(3105), new Item(2570), new Item(7461) };
	}

	/** Tribrid Inventory! Array **/
	public Item[] TribridInventory() {
		return new Item[] { new Item(1127), new Item(4587), new Item(2503), new Item(9185), new Item(1079),
				new Item(8850), new Item(2497), new Item(10498), new Item(391), new Item(1215), new Item(6685),
				new Item(2440), new Item(391, 2), new Item(6685), new Item(2436), new Item(391, 2), new Item(3024),
				new Item(2444), new Item(391, 2), new Item(3024), new Item(555, 800), new Item(391, 2),
				new Item(560, 600), new Item(565, 300),

		};
	}

	/**
	 * Handles the dialouges for the presets, to it deducts an X amount from the
	 * vault or the inventory!
	 * 
	 * @param player
	 */

	public void sendMelee126Dialogue(Player player) {

		DialogueFactory factory = player.dialogueFactory;
		factory.sendNpcChat(306, "Would you like this preset for 100k? " + player.getName());
		factory.sendOption("Yes", () -> Melee126(player), "Nevermind", factory::clear);
		factory.execute();

	}

	public void send126HybridDialouge(Player player) {

		DialogueFactory factory = player.dialogueFactory;
		factory.sendNpcChat(306, "Would you like this preset for 100k? " + player.getName());
		factory.sendOption("Yes", () -> tribrid126(player), "Nevermind", factory::clear);
		factory.execute();

	}

	public void send126tribridDialouge(Player player) {

		DialogueFactory factory = player.dialogueFactory;
		factory.sendNpcChat(306, "Would you like this preset for 100k? " + player.getName());
		factory.sendOption("Yes", () -> tribrid126(player), "Nevermind", factory::clear);
		factory.execute();

	}

	/**
	 * Handles the equipping of gear, whilst also subtracting the preset cost from
	 * the players inventory!
	 * 
	 * @param player
	 */
	public void Melee126(Player player) {
		if (player.bank.contains(995, 100000)) {
			player.bank.remove(995, 100000);

			if (!player.equipment.isEmpty() || !player.inventory.isEmpty()) {
				player.bank.depositeInventory();
				player.bank.depositeEquipment();
				player.bank.shift();
				player.bank.refresh();

				player.equipment.manualWearAll(Melee126Equipment());
				//player.inventory.addAll(Melee126Inventory());
				player.spellbook = Spellbook.LUNAR;
				player.inventory.refresh();
				player.equipment.refresh();
			}

		} else {
			player.message("You need to have 100k in the bank!");
		}

	}

	public void Hybrid126(Player player) {
		if (player.bank.contains(995, 100000)) {
			player.bank.remove(995, 100000);

			if (!player.equipment.isEmpty() || !player.inventory.isEmpty()) {
				player.bank.depositeInventory();
				player.bank.depositeEquipment();
				player.bank.shift();
				player.bank.refresh();

				player.equipment.manualWearAll(Hybrid126Equipment());
				player.inventory.addAll(Hybrid126Inventory());
				player.inventory.refresh();
				player.equipment.refresh();
				player.spellbook = Spellbook.ANCIENT;

			}

		} else {
			player.message("You need to have 100k in the bank!");
		}

	}

	public void tribrid126(Player player) {
		if (player.bank.contains(995, 100000)) {
			player.bank.remove(995, 100000);

			if (!player.equipment.isEmpty() || !player.inventory.isEmpty()) {
				player.bank.depositeInventory();
				player.bank.depositeEquipment();
				player.bank.shift();
				player.bank.refresh();

				player.equipment.manualWearAll(Hybrid126Equipment());
				player.inventory.addAll(TribridInventory());
				player.inventory.refresh();
				player.equipment.refresh();
				player.spellbook = Spellbook.ANCIENT;

			}

		} else {
			player.message("You need to have 100k in the bank!");
		}

	}

	public void sendPureDialouge(Player player) {

		DialogueFactory factory = player.dialogueFactory;
		factory.sendNpcChat(306, "Would you like this preset for 100k? " + player.getName());
		factory.sendOption("Yes", () -> PureGear(player), "Nevermind", factory::clear);
		factory.execute();

	}

	/**
	 * Handles the equipping of gear, whilst also subtracting the preset cost from
	 * the players inventory!
	 * 
	 * @param player
	 */
	public void PureGear(Player player) {
		if (player.bank.contains(995, 100000)) {
			player.bank.remove(995, 100000);

			if (!player.equipment.isEmpty() || !player.inventory.isEmpty()) {
				player.bank.depositeInventory();
				player.bank.depositeEquipment();
				player.bank.shift();
				player.bank.refresh();

				player.equipment.manualWearAll(PureEquipment());
				player.inventory.addAll(PureInventory());
				player.inventory.refresh();
				player.equipment.refresh();
			}

		} else {
			player.message("You need to have 100k in the bank!");
			return;
		}

	}

	final static int[] BUTTON_IDS = { -22984, -22983, -22982, -22981, -22980, -22979, -22978, -22977 };
	
	@Override
	protected boolean onClick(Player player, int button) {

		for (PreLoadData preload : PreLoadData.values()) {
			if (preload.getButtonId() == button) {
				new PreLoadManager(player, preload).execute();
				return true;
			}
		}

		for(int preset : BUTTON_IDS) {
			if(button == preset) {
				new PresetDialogue(player, button).execute();
				return true;
			}
		}
		
		switch (button) {
		
		/**
		 * Close's preset
		 */
		case -23034:
			player.interfaceManager.close();
			return true;
		}

		return false;
	}
}
