package io.battlerune.game.world.entity.combat.attack.listener.item;

import io.battlerune.game.world.entity.combat.FormulaModifier;
import io.battlerune.game.world.entity.combat.attack.listener.ItemCombatListenerSignature;
import io.battlerune.game.world.entity.combat.attack.listener.NpcCombatListenerSignature;
import io.battlerune.game.world.entity.combat.attack.listener.SimplifiedListener;
import io.battlerune.game.world.entity.mob.Mob;
import io.battlerune.util.RandomUtils;

@NpcCombatListenerSignature(npcs = { 1677 })
@ItemCombatListenerSignature(requireAll = true, items = { 4753, 4755, 4757, 4759 })
public class VeracListener extends SimplifiedListener<Mob> {

	@Override
	public void init(Mob attacker, Mob defender) {
		if (RandomUtils.success(0.50)) {
			defender.attributes.set("VERACS-EFFECT", attacker);
			defender.getCombat().addModifier(VeracModifier.get());
		}
	}

	@Override
	public void finishOutgoing(Mob attacker, Mob defender) {
		defender.attributes.remove("VERACS-EFFECT");
		defender.getCombat().removeModifier(VeracModifier.get());
	}

	private static final class VeracModifier implements FormulaModifier<Mob> {
		private static final VeracModifier INSTANCE = new VeracModifier();

		@Override
		public int modifyDefenceLevel(Mob attacker, Mob defender, int level) {
			return 0;
		}

		@Override
		public int modifyDefensiveBonus(Mob attacker, Mob defender, int bonus) {
			return 0;
		}

		public static VeracModifier get() {
			return INSTANCE;
		}

	}

}
