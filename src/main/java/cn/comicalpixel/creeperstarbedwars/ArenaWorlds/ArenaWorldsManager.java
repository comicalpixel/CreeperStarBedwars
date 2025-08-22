package cn.comicalpixel.creeperstarbedwars.ArenaWorlds;

import cn.comicalpixel.creeperstarbedwars.CreeperStarBedwars;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class ArenaWorldsManager {

    private final File arenaWorldsDir;
    private final File serverRootDir;

    public ArenaWorldsManager() {
        // 获取插件数据目录下的 arena_worlds 目录
        this.arenaWorldsDir = new File(CreeperStarBedwars.getPlugin().getDataFolder(), "arena_worlds");
        // 获取服务器核心根目录
        this.serverRootDir = CreeperStarBedwars.getPlugin().getDataFolder().getParentFile().getParentFile();
        // 创建 arena_worlds 目录
        createArenaWorldsDir();
    }

    /**
     * 创建 arena_worlds 目录
     */
    private void createArenaWorldsDir() {
        if (!arenaWorldsDir.exists()) {
            if (arenaWorldsDir.mkdirs()) {
                CreeperStarBedwars.getPlugin().getLogger().info("[1-1]成功创建 arena_worlds 目录");
            } else {
                CreeperStarBedwars.getPlugin().getLogger().warning("[1-0]创建 arena_worlds 目录失败");
            }
        }
    }

    /**
     * 插件加载时调用，替换服务器核心根目录中的文件目录
     */
    public void replaceWorldsOnLoad() {
        if (!arenaWorldsDir.exists() || !arenaWorldsDir.isDirectory()) {
            return;
        }

        File[] arenaWorldFiles = arenaWorldsDir.listFiles();
        if (arenaWorldFiles == null) {
            return;
        }

        for (File arenaWorldFile : arenaWorldFiles) {
            if (arenaWorldFile.isDirectory()) {
                File serverWorldDir = new File(serverRootDir, arenaWorldFile.getName());
                try {
                    // 删除服务器核心根目录中对应的目录
                    deleteDirectory(serverWorldDir.toPath());
                    // 复制 arena_worlds 中的目录到服务器核心根目录
                    copyDirectory(arenaWorldFile.toPath(), serverWorldDir.toPath());
                    CreeperStarBedwars.getPlugin().getLogger().info("[2-0]成功替换世界目录: " + arenaWorldFile.getName());
                } catch (IOException e) {
                    CreeperStarBedwars.getPlugin().getLogger().warning("[2-1]替换世界目录 " + arenaWorldFile.getName() + " 时出错: " + e.getMessage());
                }
            }
        }
    }

    /**
     * 递归删除目录
     *
     * @param path 要删除的目录路径
     * @throws IOException 删除过程中可能抛出的异常
     */
    private void deleteDirectory(Path path) throws IOException {
        if (Files.exists(path)) {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

    /**
     * 递归复制目录
     *
     * @param source 源目录路径
     * @param target 目标目录路径
     * @throws IOException 复制过程中可能抛出的异常
     */
    private void copyDirectory(Path source, Path target) throws IOException {
        Files.walkFileTree(source, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path newDir = target.resolve(source.relativize(dir));
                Files.createDirectories(newDir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Path newFile = target.resolve(source.relativize(file));
                Files.copy(file, newFile, StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });
    }
    
}
