package me.github.skyexcelcore.data;

import me.github.skyexcelcore.SkyExcel;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("unchecked")
public class Config {
    public FileConfiguration config;
    public File file;
    private String name;

    private Plugin plugin;

    private ConfigurationSection section;

    public Config(String name) {
        this.name = name;
        plugin = SkyExcel.getNewPlugin();
    }

    public void setSection(ConfigurationSection section) {
        this.section = section;
    }

    public void reloadConfig() {
        if (this.config == null)
            this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);

        InputStream inputStream = plugin.getResource(name + ".yml");
        if (inputStream != null) {
            YamlConfiguration config = YamlConfiguration.loadConfiguration(new InputStreamReader(inputStream));
            this.config.setDefaults(config);
        }
    }

    public boolean isFileExist() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        return this.file.exists();
    }

    public void deleteFile() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");
        this.file.delete();
    }

    public void renameFile(String name) {
        this.file = new File(plugin.getDataFolder(), this.name + ".yml");
        File file = new File(plugin.getDataFolder(), name + ".yml");

        this.file.renameTo(file);
    }

    public FileConfiguration getConfig() {
        if (this.config == null) reloadConfig();
        return config;
    }

    public boolean saveConfig() {
        if (this.config == null || this.file == null) return false;
        try {
            getConfig().save(this.file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void loadDefaultPluginConfig() {
        File fconfig = new File(plugin.getDataFolder(), this.name + ".yml");
        if (!fconfig.exists()) {
            plugin.saveResource(this.name + ".yml", false);
        }
    }

    public void saveDefualtConfig() {
        if (this.file == null)
            this.file = new File(plugin.getDataFolder(), name + ".yml");
        if (!this.file.exists()) {
            file = new File(plugin.getDataFolder(), name + ".yml");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setString(String path, String msg) {
        if (getConfig().get(path) == null) {
            getConfig().set(path, msg);
            this.saveConfig();
        }
    }

    public void setInteger(String path, int integer) {
        if (getConfig().get(path) == null) {
            getConfig().set(path, integer);

            this.saveConfig();
        }
    }

    public void setBoolean(String path, boolean Boolean) {
        if (getConfig().get(path) == null) {
            getConfig().set(path, Boolean);

            this.saveConfig();
        }
    }

    public void setLong(String path, Long value) {
        if (getConfig().get(path) == null) {
            getConfig().set(path, value);
            this.saveConfig();
        }
    }

    public void setFloat(String path, float value) {
        if (getConfig().get(path) == null) {
            getConfig().set(path, value);
            this.saveConfig();
        }
    }

    public void setDouble(String path, double value) {
        if (getConfig().get(path) == null) {
            getConfig().set(path, value);
            this.saveConfig();
        }
    }

    public void setObject(String path, Object value) {
        if (getConfig().get(path) == null) {
            getConfig().set(path, value);
            this.saveConfig();
        }
    }

    public boolean setLocation(String path, Location value) {

        getConfig().set(path + ".world", value.getWorld().getName());
        getConfig().set(path + ".x", value.getX());
        getConfig().set(path + ".y", value.getY());
        getConfig().set(path + ".z", value.getZ());

        return this.saveConfig();
    }

    public ItemStack setItemStack(String path, ItemStack item) {
        if (item.hasItemMeta()) {
            getConfig().set(path + ".meta.name", item.getItemMeta().getDisplayName());
            getConfig().set(path + ".meta.lore", item.getItemMeta().getLore());
            getConfig().set(path + ".meta.CustomModelData", item.getItemMeta().getCustomModelData());
        }
        getConfig().set(path + ".type", item.getType().name());
        getConfig().set(path + ".amount", item.getAmount());


        saveConfig();
        return item;
    }

    public List<ItemStack> addItemStack(String path, ItemStack stack) {
        List<ItemStack> items = (List<ItemStack>) getConfig().getList(path);
        if (getConfig().get(path) == null) {
            newArrayList(path);
            items.add(stack);

        } else {
            items.add(stack);
        }
        return items;
    }


    public ItemStack getItemStack(String path, boolean single) {
        if (getConfig().get(path) != null) {
            String name = getString(path + ".meta.name");
            String type = getString(path + ".type");
            ItemStack itemstack = new ItemStack(Material.valueOf(type));
            int amount = getInteger(path + ".amount");
            if (getConfig().get(path + ".meta") != null) {
                ItemMeta meta = itemstack.getItemMeta();
                if (name != "") {
                    meta.setDisplayName(name);
                }
                if (getConfig().get(path + ".meta.lore") != null) {
                    List<String> lore = (List<String>) getConfig().getList(path + ".meta.lore");

                    if (lore != null) {
                        if (name != "") {
                            meta.setLore(lore);
                        } else {
                            meta.setDisplayName(name);

                            meta.setLore(lore);
                        }
                    } else {
                        meta.setDisplayName(name);
                    }
                }
                if (getConfig().get(path + ".meta.CustomModelData") != null) {
                    int CustomModel = getInteger(path + ".meta.CustomModelData");
                    meta.setCustomModelData(CustomModel);
                }
                itemstack.setItemMeta(meta);
            }

            if (single) {
                itemstack.setAmount(1);
            } else {
                itemstack.setAmount(amount);
            }
            return itemstack;
        }
        return null;
    }

    public boolean setSectionTime(String path, int... times) {

        if (getConfig().get(path) == null) {
            switch (times.length) {
                case 1:
                    section.set(path + ".day", times[0]);
                    section.set(path + ".hour", 0);
                    section.set(path + ".min", 0);
                    section.set(path + ".hour", 0);

                    break;
                case 2:
                    section.set(path + ".day", times[0]);
                    section.set(path + ".hour", times[1]);
                    section.set(path + ".min", 0);
                    section.set(path + ".second", 0);
                    break;
                case 3:
                    section.set(path + ".day", times[0]);
                    section.set(path + ".hour", times[1]);
                    section.set(path + ".min", times[2]);
                    section.set(path + ".second", 0);
                    break;
                case 4:
                    section.set(path + ".day", times[0]);
                    section.set(path + ".hour", times[1]);
                    section.set(path + ".min", times[2]);
                    section.set(path + ".second", times[3]);
                    break;
            }

            return this.saveConfig();
        }
        return false;
    }


    /***
     *
     * @param path
     * @param times day,hour,minute,second
     */
    public boolean setTime(String path, int... times) {
        if (getConfig().get(path) == null) {
            switch (times.length) {
                case 1:
                    setInteger(path + ".day", times[0]);
                    setInteger(path + ".hour", 0);
                    setInteger(path + ".min", 0);
                    setInteger(path + ".hour", 0);

                    break;
                case 2:
                    setInteger(path + ".day", times[0]);
                    setInteger(path + ".hour", times[1]);
                    setInteger(path + ".min", 0);
                    setInteger(path + ".second", 0);
                    break;
                case 3:
                    setInteger(path + ".day", times[0]);
                    setInteger(path + ".hour", times[1]);
                    setInteger(path + ".min", times[2]);
                    setInteger(path + ".second", 0);
                    break;
                case 4:
                    setInteger(path + ".day", times[0]);
                    setInteger(path + ".hour", times[1]);
                    setInteger(path + ".min", times[2]);
                    setInteger(path + ".second", times[3]);
                    break;
            }

            return this.saveConfig();
        }

        return false;
    }

    public void setInventory(String path, Inventory inv) {
        setInteger(path + ".size", inv.getSize());
        for (int i = 0; i < inv.getSize(); i++) {
            inv.getItem(i);
        }
    }


    public Location getLocation(String path) {
        if (getConfig().get(path) != null) {
            return new Location(Bukkit.getWorld(getString(path + ".world")), getDouble(path + ".x"), getDouble(path + ".y"), getDouble(path + ".z"));
        }
        return null;
    }

    public String getString(String path) {
        return getConfig().getString(path);
    }

    public int getInteger(String path) {
        return getConfig().getInt(path);
    }

    public double getDouble(String path) {
        return getConfig().getDouble(path);
    }

    public boolean getBoolean(String path) {
        return getConfig().getBoolean(path);
    }

    public long getLong(String path) {
        return getConfig().getLong(path);
    }


    public boolean delete() {
        this.file = new File(plugin.getDataFolder(), name + ".yml");

        return this.file.delete();
    }

    public Object get(String path) {
        return this.config.get(path);
    }


    public void removeKey(String key) {
        this.file = new File(plugin.getDataFolder(), name + ".yml");

        Set<String> toRemove = new HashSet<>(); // could also be TreeSet, or LinkedHashSet, or anything else, but of those HashSet performs the best
        toRemove.add(key);

        for (String remove : toRemove) {
            getConfig().set(remove, null);
        }
        saveConfig();
    }


    public void newArrayList(String path) {
        if (getConfig().getList(path) == null) {
            this.getConfig().set(path, new ArrayList<>());
            this.saveConfig();
        } else {
            System.out.print("해당 리스트는 이미 생성 되었습니다.");
        }
    }

    /***
     *@implSpec {@code This method Get A}
     * <pre>{@code
     *
     *   ConfigManager config;
     *   public void CreatePlayer(Player player){
     *   config = new ConfigManager(player.getUniqueId().toString());
     *   config.newArrayList("player", player);
     *   }
     *   public void addPlayer(Player player){
     *   config.addObject("player", player);
     *   }
     *
     * }</pre>
     * @return If the object is contained in List from path
     */
    public boolean AddArrayObject(String path, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);

        if (!list.contains(obj) && list != null) {
            list.add(obj);

            this.saveConfig();
            return true;
        }

        return false;
    }

    public Object getArrayObject(String path, int index) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);

        if (list != null) {
            return list.get(index);
        }
        return null;
    }

    public boolean removeArrayObject(String path, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);
        if (list.contains(obj)) {
            list.remove(obj);
            this.saveConfig();
            return true;
        }
        return false;
    }


    public boolean setArrayObject(String path, int index, Object obj) {
        List<Object> list = (List<Object>) this.getConfig().getList(path);
        if (!list.contains(obj)) {
            list.set(index, obj);
            return true;
        }

        return false;
    }

    /**
     * The name value of this class to be directory of file. so The name should be like "test/" this.
     *
     * @param results
     */
    public void setFileListTabComplete(ArrayList<String> results) {
        this.file = new File(plugin.getDataFolder(), name);
        File[] test = this.file.listFiles();
        for (File file : test) {
            if (file != null) {

                String name = file.getName();
                name = name.replaceAll(".yml", "");
                results.add(name);
            }
        }
    }

    public ConfigurationSection newSection(String path) {
        return getConfig().createSection(path);
    }
}
