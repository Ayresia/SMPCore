package me.ayresia.smpcore.smpcore.events;

import net.kyori.adventure.text.Component;
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
