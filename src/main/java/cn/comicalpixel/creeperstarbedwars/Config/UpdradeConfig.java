package cn.comicalpixel.creeperstarbedwars.Config;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConstructor;
import org.bukkit.configuration.file.YamlRepresenter;
import org.bukkit.plugin.Plugin;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.*;
import java.util.logging.Logger;

public class UpdradeConfig extends YamlConfiguration {

    protected final DumperOptions yamlOptions = new DumperOptions();
    protected final Representer yamlRepresenter = new YamlRepresenter();
    protected final Yaml yaml = new Yaml(new YamlConstructor(), this.yamlRepresenter, this.yamlOptions);
    protected File file;
    protected Logger loger;
    protected Plugin plugin;

    public UpdradeConfig(Plugin plugin) {
        this(plugin, "config.yml");
    }

    public UpdradeConfig(Plugin plugin, File file) {
        Validate.notNull(file, "File cannot be null");
        Validate.notNull(plugin, "Plugin cannot be null");
        this.plugin = plugin;
        this.file = file;
        this.loger = plugin.getLogger();
        this.check(file);
        this.init(file);
    }

    public UpdradeConfig(Plugin plugin, String filename) {
        this(plugin, new File(plugin.getDataFolder(), filename));
    }

    private void check(File file) {
        String filename = file.getName();
        InputStream stream = this.plugin.getResource(filename);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                if (stream != null) {
                    this.plugin.saveResource(filename, true);
                }
            }
        } catch (IOException e) {
            this.loger.info("配置文件 " + filename + " 创建失败...");
        }
    }

    private void init(File file) {
        Validate.notNull(file, "File cannot be null");
        try {
            FileInputStream stream = new FileInputStream(file);
            this.init(stream);
        } catch (FileNotFoundException e) {
            this.loger.info("配置文件 " + file.getName() + " 不存在...");
        }
    }

    private void init(InputStream stream) {
        Validate.notNull(stream, "Stream cannot be null");
        try {
            this.load(new InputStreamReader(stream, Charsets.UTF_8));
        } catch (IOException ex) {
            this.loger.info("配置文件 " + this.file.getName() + " 读取错误...");
        } catch (InvalidConfigurationException ex) {
            this.loger.info("配置文件 " + this.file.getName() + " 格式错误...");
        }
    }

    public void setBlockLocation(Location location, String path) {
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        this.set(path, world + "," + x + "," + y + "," + z);
    }

    public void setLocation(Location location, String path) {
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        double yaw = location.getYaw();
        double pitch = location.getPitch();
        this.set(path, world + "," + x + "," + y + "," + z + "," + yaw + "," + pitch);
    }

    public Location getBlockLocation(String path) {
        if (this.getString(path) != null) {
            String locationString = this.getString(path);
            World world = Bukkit.getWorld(locationString.split(",")[0]);
            double x = Double.parseDouble(locationString.split(",")[1]);
            double y = Double.parseDouble(locationString.split(",")[2]);
            double z = Double.parseDouble(locationString.split(",")[3]);
            return new Location(world, x, y, z);
        }
        return null;
    }



    public Location getLocation(String path) {
        if (this.getString(path) != null) {
            String locationString = this.getString(path);
            World world = Bukkit.getWorld(locationString.split(",")[0]);
            double x = Double.parseDouble(locationString.split(",")[1]);
            double y = Double.parseDouble(locationString.split(",")[2]);
            double z = Double.parseDouble(locationString.split(",")[3]);
            double yaw = Double.parseDouble(locationString.split(",")[4]);
            double pitch = Double.parseDouble(locationString.split(",")[5]);
            return new Location(world, x, y, z, (float) yaw, (float) pitch);
        }
        return null;
    }

    public void load(File file) throws IOException, InvalidConfigurationException {
        Validate.notNull(file, "File cannot be null");
        FileInputStream stream = new FileInputStream(file);
        this.load(new InputStreamReader(stream, Charsets.UTF_8));
    }

    public void load(Reader reader) throws IOException, InvalidConfigurationException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader input = reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader)) {
            String line;
            while ((line = input.readLine()) != null) {
                builder.append(line);
                builder.append('\n');
            }
        }
        this.loadFromString(builder.toString());
    }

    public void reload() {
        this.init(this.file);
    }

    public void save() {
        if (this.file == null) {
            this.loger.info("未定义配置文件路径 保存失败!");
        }
        try {
            this.save(this.file);
        } catch (IOException e) {
            this.loger.info("配置文件 " + this.file.getName() + " 保存错误...");
            e.printStackTrace();
        }
    }

    public void save(File file) throws IOException {
        Validate.notNull(file, "File cannot be null");
        Files.createParentDirs(file);
        String data = this.saveToString();
        try (OutputStreamWriter writer = new OutputStreamWriter(java.nio.file.Files.newOutputStream(file.toPath()), Charsets.UTF_8)) {
            writer.write(data);
        }
    }

    public String saveToString() {
        this.yamlOptions.setIndent(this.options().indent());
        this.yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        String header = this.buildHeader();
        String dump = this.yaml.dump(this.getValues(false));
        if (dump.equals("{}\n")) {
            dump = "";
        }
        return header + dump;
    }

}
