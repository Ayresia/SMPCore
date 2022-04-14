package me.ayresia.smpcore.smpcore.items;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Heart {
    ItemStack item;
    List<Component> loreArray = new ArrayList<>();

    public Heart(Plugin plugin) {
        this.item = new ItemStack(Material.NETHER_STAR, 1);
        this.item.setItemMeta(getItemMeta(this.item));

        plugin.getServer().addRecipe(getRecipe());
    }

    public ItemStack getItem() {
        return item;
    }

    public ItemMeta getItemMeta(ItemStack item) {
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text("§c❤ Heart"));
        meta.addEnchant(Enchantment.LUCK, 1, false);

        this.loreArray.add(Component.text("§7Can only be used if you"));
        this.loreArray.add(Component.text("§7have less than 20 hearts"));

        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.lore(this.loreArray);

        return meta;
    }

    public ShapedRecipe getRecipe() {
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("redeemables"), this.item);

        recipe.shape("T T", " O ", "T T");
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('O', Material.OBSIDIAN);

        return recipe;
    }
}
