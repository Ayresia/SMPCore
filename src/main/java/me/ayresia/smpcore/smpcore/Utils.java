package me.ayresia.smpcore.smpcore;

import org.apache.commons.lang.WordUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class Utils {
    public static String parseDeathCause(DamageCause cause) {
        return switch(cause) {
            case FALLING_BLOCK -> "got squashed by a falling block";
            case FIRE, LAVA, HOT_FLOOR, FIRE_TICK -> "got burnt";
            case ENTITY_EXPLOSION, BLOCK_EXPLOSION -> "exploded";
            case LIGHTNING -> "got struck by lightning";
            case FALL -> "fell from a high place";
            case THORNS -> "was killed by thorns";
            case VOID -> "fell out of the world";
            case SUFFOCATION -> "has suffocated";
            case STARVATION -> "died by hunger";
            case MAGIC -> "killed by magic";
            case DROWNING -> "drowned";
            case POISON, WITHER -> "was poisoned";
            case SUICIDE -> "suicided";
            case FREEZE -> "froze to death";
            case CONTACT -> "was pricked to death";
            case CRAMMING -> "was crammed";
            case DRAGON_BREATH -> "was roasted in dragon breath";
            case FLY_INTO_WALL -> "experienced kinetic energy";
            default -> "has died";
        };
    }

    public static String getFormattedWeaponString(Player attacker) {
        ItemStack weapon = attacker.getInventory().getItemInMainHand();
        String weaponString;

        if (weapon.getItemMeta() == null) {
            weaponString = "Fist";
        } else {
            weaponString =  weapon.getItemMeta().hasDisplayName() ?
                    weapon.getItemMeta().getDisplayName()
                    : WordUtils.capitalize(weapon.getType()
                    .name()
                    .replace('_', ' ')
                    .toLowerCase()
            );
        }

        return String.format(" using §6%s", weaponString);
    }
}
