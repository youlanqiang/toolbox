package top.youlanqiang.toolbox.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import top.youlanqiang.toolbox.base.ObjectHepler;

public class ZipHepler {

    /**
     * 将目标path文件压缩成zip保存至store目录
     * 
     * @param source 目标文件
     * @param store  保存目录
     * @throws IOException 压缩失败io异常
     */
    public static void zip(Path source, Path store) throws IOException {
        Stream<Path> files = Files.walk(source);
        zip(files, store);
    }

    /**
     * 将files文件集合压缩成zip保存至store目录
     * 
     * @param files 需要压缩的文件集合
     * @param store 保存目录
     * @throws IOException 压缩失败io异常
     */
    public static void zip(Stream<Path> files, Path store) throws IOException {
        if (ObjectHepler.isEmpty(files) || ObjectHepler.isEmpty(store)) {
            throw new NullPointerException("files or store is null.");
        }

        try (var stream = Files.newOutputStream(store, StandardOpenOption.WRITE);
                var zipStream = new ZipOutputStream(stream)) {

            // 保证文件遍历的排序，防止出现先遍历出文件夹下的子文件情况。
            var pathList = files.sorted().collect(Collectors.toList());

            for (Path path : pathList) {
                if (Files.isDirectory(path)) {
                    zipStream.putNextEntry(new ZipEntry(path.toString() + "/"));

                } else {
                    zipStream.putNextEntry(new ZipEntry(path.toString()));
                    Files.copy(path, zipStream);
                }
                zipStream.closeEntry();
            }

        }
    }

    /**
     * 将zip文件解压保存至store目录
     * 
     * @param zip   需要解压zip文件
     * @param store 保存目录
     * @throws IOException 解压失败异常
     */
    public static void unZip(Path zip, Path store) throws IOException {
        if (ObjectHepler.isEmpty(zip) || ObjectHepler.isEmpty(store)) {
            throw new NullPointerException("zip or store is null.");
        }

        // zip解压路径
        String storePath = store.toFile().getAbsolutePath();
        ZipEntry element = null;
        Path path = null;
        try (var zipFile = new ZipFile(zip.toFile())) {
            var entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                element = entries.nextElement();
                path = Path.of(storePath, element.getName());
                if (element.isDirectory()) {
                    if (Files.notExists(path)) {
                        Files.createDirectories(path);
                    }
                } else {
                    if (Files.notExists(path)) {
                        Files.write(path, zipFile.getInputStream(element).readAllBytes(), StandardOpenOption.WRITE);
                    }
                }
            }

        }

    }
}
