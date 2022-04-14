package me.ayresia.smpcore.smpcore.events;

import net.kyori.adventure.text.Component;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

import static me.ayresia.smpcore.smpcore.Utils.getFormattedWeaponString;
import static me.ayresia.smpcore.smpcore.Utils.parseDeathCause;

public class PlayerListener implements Listener {
    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player attacker = victim.getKiller();

        if (attacker != null) {
            String formattedDeathMessage = String.format("☠ §c%s§f was killed by §a%s§f%s",
                    victim.getName(),
                    attacker.getName(),
                    getFormattedWeaponString(attacker)
            );

            if (victim != attacker) {
                double victimHealth = victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double attackerHealth = attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

                if (attackerHealth < 40) {
                    attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(attackerHealth + 2);
                    attacker.setHealthScale(attackerHealth + 2);
                }

                if (victimHealth > 10) {
                    victim.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(victimHealth - 2);
                    victim.setHealthScale(victimHealth - 2);
                }
            }

            event.deathMessage(Component.text(formattedDeathMessage));
            return;
        }

        if (victim.getLastDamageCause() != null) {
            DamageCause cause = victim.getLastDamageCause().getCause();

            String formattedDeathMessage = String.format("☠ §c%s§8 §6%s",
                    victim.getName(),
                    parseDeathCause(cause)
            );

            event.deathMessage(Component.text(formattedDeathMessage));
        }
    }
}
