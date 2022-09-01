package me.github.skyexcelcore.data;

import me.github.skyexcelcore.SkyExcel;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class NBTMeta {
    private ItemStack itemStack;
    private ItemMeta itemMeta;
    private PersistentDataContainer data;

    private Plugin plugin;

    public NBTMeta(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
        this.data = getItemData();
        this.plugin = SkyExcel.getNewPlugin();
    }



    public String setString(String key, String value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
        itemStack.setItemMeta(itemMeta);
        return value;
    }

    public int setInt(String key, int value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, value);
        itemStack.setItemMeta(itemMeta);
        return value;
    }

    public long setLong(String key, long value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.LONG, value);
        itemStack.setItemMeta(itemMeta);
        return value;
    }

    public float setFloat(String key, float value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.FLOAT, value);
        itemStack.setItemMeta(itemMeta);
        return value;
    }

    public byte setByte(String key, byte value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.BYTE, value);
        itemStack.setItemMeta(itemMeta);
        return value;
    }

    public byte[] setByteArray(String key, byte[] value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.BYTE_ARRAY, value);
        itemStack.setItemMeta(itemMeta);
        return value;
    }

    public int[] setIntArray(String key, int[] value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER_ARRAY, value);
        itemStack.setItemMeta(itemMeta);
        return value;
    }

    public long[] setLongArray(String key, long[] value) {
        data.set(new NamespacedKey(plugin, key), PersistentDataType.LONG_ARRAY, value);
        itemStack.setItemMeta(itemMeta);
        return value;
    }

    public int getInt(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.INTEGER)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! Int 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§c0을 출력합니다...");
            return 0;
        }
    }

    public long getLong(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.LONG)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.LONG);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! Long 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§c0을 출력합니다...");
            return 0;
        }
    }
    public long getByte(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.BYTE)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.BYTE);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! Byte 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§c0을 출력합니다...");
            return 0;
        }
    }
    public float getFloat(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.FLOAT)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.FLOAT);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! Float 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§c0을 출력합니다...");
            return 0;
        }
    }

    public String getString(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.STRING)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! String 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§c공백을 출력합니다...");
            return "";
        }
    }

    public int[] getIntArray(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.INTEGER_ARRAY)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER_ARRAY);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! IntArray 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§cNull을 출력합니다...");
            return null;
        }
    }

    public long[] getLongList(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.LONG_ARRAY)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.LONG_ARRAY);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! Long 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§cNull을 출력합니다...");
            return null;
        }
    }
    public byte[] getByteList(String key) {
        if (data.has(new NamespacedKey(plugin, key), PersistentDataType.BYTE)) {
            return data.get(new NamespacedKey(plugin, key), PersistentDataType.BYTE_ARRAY);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cNBTMeta 오류! Byte 값을 불러오지 못했습니다. 해당 {§f" + key + "§c} 에 데이타가 존재하지 않습니다.  ");
            Bukkit.getConsoleSender().sendMessage("§cNull을 출력합니다...");
            return null;
        }
    }


    public PersistentDataContainer getItemData() {
        return itemMeta.getPersistentDataContainer();
    }
}
