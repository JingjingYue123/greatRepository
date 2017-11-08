package com.djcps.crm.commons.fastdfs;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

/**
 * @author 叶千阁
 * @date 2016年8月2日
 * @update 璩诗斌
 */
public class FastDFSUtilParent {
    private static int length = 1;

    // 通过返回的数组得到url
    static String getUrl(String[] strings) throws IOException {
        return strings[0] + "/" + strings[1];
    }


    // 文件转byte数组
    static byte[] readFileToByteArray(File file) throws IOException {
        return FileUtils.readFileToByteArray(file);
    }

    // 通过文件来获取后缀名
    static String readFileGetLastName(File file) {
        return FilenameUtils.getExtension(file.getName());
    }

    public static String getIpAddress() {
        return "";
    }

    static String getTrackIpAddress() {
        ClassPathResource cpr = new ClassPathResource("config/client.conf");
        File file = null;
        String fileString = null;
        try {
            file = cpr.getFile();
            fileString = FileUtils.readFileToString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert fileString != null;
        return "http://" + fileString.substring(fileString.lastIndexOf("tracker_server=") + "tracker_server=".length(), fileString.indexOf(":22122"));
    }

    static String[] UrlToStringArry(String url) {
        return url.split("/", 2);
    }

    private static String getAbsolutePath() {
        File file = new File("");
        return file.getAbsolutePath();
    }

    // 获取工作目录的路劲
    private static String getPath() {
        String[] strings = getAbsolutePath().split("\\\\");
        StringBuilder path = new StringBuilder(strings[0]);
        length = strings.length - 1;
        for (int i = 1; i < length; i++) {
            path.append("/").append(strings[i]);
        }
        return path.toString();
    }

    // 生成zip文件的路劲
    private static String createZipPath(String zipName) {
        return getPath() + "/" + zipName + ".zip";
    }

    // 生成zip文件
    static File createFileZip(String zipName) {
        String path = createZipPath(zipName);
        return new File(path);
    }

    // 根据文件产生 ZipOutputStream
    static ZipOutputStream createZipOut(File filezip) throws FileNotFoundException {
        FileOutputStream fous = new FileOutputStream(filezip);
        return new ZipOutputStream(fous);
    }

    static String getLastName(String url) {
        return url.substring(url.lastIndexOf(".") + 1);
    }

}
