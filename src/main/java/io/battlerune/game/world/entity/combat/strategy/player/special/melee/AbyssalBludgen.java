package io.battlerune.game.world.entity.combat.strategy.player.special.melee;

import io.battlerune.game.Animation;
import io.battlerune.game.Graphic;
import io.battlerune.game.UpdatePriority;
import io.battlerune.game.world.entity.combat.hit.Hit;
import io.battlerune.game.world.entity.combat.strategy.player.PlayerMeleeStrategy;
import io.battlerune.game.world.entity.mob.Mob;
import io.battlerune.game.world.entity.mob.player.Player;
import io.battlerune.game.world.entity.skill.Skill;

/**
 * Handles the staff of the dead weapon special attack.
 *
 * @author Daniel
 */
public class AbyssalBludgen extends PlayerMeleeStrategy {

	private static final Animation ANIMATION = new Animation(7010, UpdatePriority.HIGH);
	private static final Graphic GRAPHIC = new Graphic(1284, UpdatePriority.HIGH);

	private static final AbyssalBludgen INSTANCE = new AbyssalBludgen();

	@Override
	public void hitsplat(Player attacker, Mob defender, Hit hit) {
		super.hitsplat(attacker, defender, hit);
		defender.graphic(GRAPHIC);
	}

	@Override
	public int modifyDamage(Player attacker, Mob defender, int damage) {
		int level = attacker.skills.getLevel(Skill.PRAYER);
		int max = attacker.skills.getMaxLevel(Skill.PRAYER);
		return damage + damage * (max - level) / 200;
	}

	@Override
	public Animation getAttackAnimation(Player attacker, Mob defender) {
		return ANIMATION;
	}

	public static AbyssalBludgen get() {
		return INSTANCE;
	}

}