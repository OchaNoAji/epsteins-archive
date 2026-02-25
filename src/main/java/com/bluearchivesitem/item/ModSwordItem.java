package com.bluearchivesitem.item;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class ModSwordItem extends SwordItem {
    public ModSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}