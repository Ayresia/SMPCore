package me.ayresia.smpcore.smpcore.events;

import me.ayresia.smpcore.smpcore.items.Heart;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static me.ayresia.smpcore.smpcore.Utils.getFormattedWeaponString;
import static me.ayresia.smpcore.smpcore.Utils.parseDeathCause;

public class PlayerListener implements Listener {
    Heart heart;
    public PlayerListener(Heart heart) {
        this.heart = heart;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
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

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack currentItemHeld = player.getInventory().getItemInMainHand();

        if (currentItemHeld.getType() == Material.AIR) return;
        if (event.getAction() == Action.LEFT_CLICK_AIR ||
            event.getAction() == Action.LEFT_CLICK_BLOCK ||
            event.getAction() == Action.RIGHT_CLICK_BLOCK) return;

        if (currentItemHeld.getItemMeta().getDisplayName().equals("§c❤ Heart")) {
            double playerHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();

            if (playerHealth >= 40) {
                player.sendMessage("§c❤§f Unable to redeem, you have more than 20 hearts!");
                return;
            }

            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(playerHealth + 2);
            player.setHealthScale(playerHealth + 2);

            player.sendMessage("§c❤§f Heart has now been redeemed!");
            currentItemHeld.setAmount(currentItemHeld.getAmount() - 1);
        }
    }
}
