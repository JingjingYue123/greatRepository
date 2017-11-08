package com.djcps.crm.commons.fastdfs;

import org.apache.commons.io.IOUtils;
import org.csource.fastdfs.StorageClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * FastDFS文件存储上传下载
 *
 * @author 叶千阁
 * @version 1.1
 * @update 璩诗斌
 * @date 2016年8月2日
 */
public class FastDFSUtil extends FastDFSUtilParent {

    /**
     * 上传文件返回数组
     *
     * @param file
     * @return group_name, remote_filename
     * @throws IOException
     * @throws Exception
     */
    private static String[] uploadFile(File file) throws Exception {
        return uploadFile(file, readFileGetLastName(file));
    }

    /**
     * 上传文件和后缀名返回数组
     *
     * @param file
     * @param lastName
     * @return group_name, remote_filename
     * @throws IOException
     * @throws Exception
     */
    private static String[] uploadFile(File file, String lastName) throws Exception {
        return uploadFile(readFileToByteArray(file), lastName);
    }

    private static String[] uploadFile(byte[] bytes, String lastName) throws Exception {
        FastDFSBean fastClient = new FastDFSBean();
        String[] strings = fastClient.getStorageClient().upload_file(bytes, lastName.toLowerCase(), null);
        fastClient.close();
        return strings;
    }


    /**
     * 上传文件返回url
     * l	 *
     *
     * @param file
     * @return url
     * @throws IOException
     * @throws Exception
     */
    public static String uploadFileUrl(File file) throws Exception {
        if (file == null) return null;
        return uploadFileUrl(file, readFileGetLastName(file));
    }


    /**
     * 上传图片
     *
     * @param bytes    图片的byte数组
     * @param lastName 图片的后缀名
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String uploadFileUrl(byte[] bytes, String lastName) throws Exception {
        String[] strings = uploadFile(bytes, lastName);
        return getUrl(strings);
    }


    /**
     * 上传文件和后缀名返回url
     *
     * @param file
     * @param lastName
     * @return url
     * @throws IOException
     * @throws Exception
     */
    public static String uploadFileUrl(File file, String lastName) throws Exception {
        String[] strings = null;
        try {
            strings = uploadFile(file, lastName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getUrl(strings);
    }

    /**
     * 传入一个url返回一个byte数组
     * 2017-06-24 李阔修改，项目内已经不用HttpClient，需要进行修改
     * @param url
     * @return byte数组
     * @throws IOException
     * @throws Exception
     */
    public static byte[] downloadFile(String url) throws Exception {
       /* if (url == null || url.trim().length() == 0) return null;
        InputStream in = null;
        byte[] bytes = null;
        try {
            url = getTrackIpAddress() + "/" + url;
            // 定义HttpClient
            HttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            in = response.getEntity().getContent();
            bytes = IOUtils.toByteArray(in);
        } finally {
            if (in != null) {
                in.close();
            }

        }
        return bytes;*/
        return new byte[]{};
    }


    /**
     * 传入一个map和一个zipName进行压缩下载
     *
     * @param map     key:文件url&value:文件原名
     * @param zipName 压缩文件名
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static File downloadZip(Map<String, String> map, String zipName) throws Exception {
        File zipfile = createFileZip(zipName);
        try (ZipOutputStream zipOut = createZipOut(zipfile)) {
            Set<String> set = map.keySet();
            for (String fileurl : set) {
                String filename = map.get(fileurl);
                zipEveryFile(fileurl, filename, zipOut);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return zipfile;
    }

    /**
     * @param fileurl  文件url
     * @param filename 原文件名
     * @param zipfile  压缩文件
     * @throws IOException
     * @throws Exception
     */
    private static void zipEveryFile(String fileurl, String filename, File zipfile) throws Exception {
        zipEveryFile(fileurl, filename, createZipOut(zipfile));
    }

    /**
     * @param fileurl
     * @param filename
     * @param zipOut
     * @throws IOException
     * @throws Exception
     */
    private static void zipEveryFile(String fileurl, String filename, ZipOutputStream zipOut) throws Exception {
        byte[] bytes = downloadFile(fileurl);
        ZipEntry entry = new ZipEntry(filename);
        zipOut.putNextEntry(entry);
        IOUtils.write(bytes, zipOut);
    }

    /**
     * 传入组名和文件名返回一个byte数组
     *
     * @param groupName
     * @param remoteFilename
     * @return byte数组
     * @throws IOException
     * @throws Exception
     */
    private static byte[] downloadFile(String groupName, String remoteFilename) throws Exception {
        FastDFSBean fastClient = new FastDFSBean();
        byte[] bytes = fastClient.getStorageClient().download_file(groupName, remoteFilename);
        fastClient.close();
        return bytes;
    }


    public static byte[] downloadFlagFile(String url) throws Exception {
        if (null == url || "".endsWith(url)) return null;
        String[] strings = UrlToStringArry(url);
        if (strings.length == 2) {
            return downloadFile(strings[0], strings[1]);
        }
        return null;
    }

    /**
     * 传入一个url进行删除图片 返回 0 为成功
     *
     * @param url
     * @return int
     * @throws IOException
     * @throws Exception
     */
    public static int deleteFile(String url) throws Exception {
        if (url == null || url.trim().length() == 0) return 0;
        String[] strings = UrlToStringArry(url);
        if (strings.length == 2) {
            return deleteFile(strings[0], strings[1]);
        }
        return 0;
    }

    /**
     * 传入组名和文件名进行删除图片 返回 0 为成功
     *
     * @param group_name
     * @param remote_filename
     * @return int
     * @throws IOException
     * @throws Exception
     */
    private static int deleteFile(String group_name, String remote_filename) throws Exception {
        FastDFSBean fastClient = new FastDFSBean();
        int i = fastClient.getStorageClient().delete_file(group_name, remote_filename);
        fastClient.close();
        return i;
    }


    /**
     * 根据url进行图片复制
     *
     * @param url
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String copyFileByUrl(String url) throws Exception {
        if (url == null || url.trim().length() == 0) return null;
        return copyFileByUrl(url, getLastName(url));
    }

    private static String copyFileByUrl(String url, String lastName) throws Exception {
        String[] strings = copyFileByBytes(downloadFile(url), lastName);
        return getUrl(strings);
    }

    private static String[] copyFileByBytes(byte[] bytes, String lastName) throws Exception {
        return uploadFile(bytes, lastName);
    }

    /**
     * 改变文件内容（覆盖）
     *
     * @param groupName        图片组名称
     * @param appenderFilename 文件名称
     * @param file_buff         添加的内容
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    private static void changeFile(String groupName, String appenderFilename, byte[] file_buff) throws Exception {
        FastDFSBean fastClient = new FastDFSBean();
        StorageClient storageClient = fastClient.getStorageClient();
        storageClient.truncate_file(groupName, appenderFilename);
        storageClient.append_file(groupName, appenderFilename, file_buff);
        fastClient.close();
    }

    /**
     * 改变文件内容（覆盖）
     *
     * @param url       数据库中的fpath
     * @param fileBuff 添加的byte[]数组
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    public static String changeFile(String url, byte[] fileBuff) throws Exception {
        if (url == null || url.trim().length() == 0) {
            url = FastDFSUtil.uploadCouldChangeFileUrl(" ".getBytes(), "txt");
        }
        String[] strings = UrlToStringArry(url);
        changeFile(strings[0], strings[1], fileBuff);
        return url;
    }

    /**
     * 改变文件内容（覆盖）
     *
     * @param url     数据库中的fpath
     * @param content 写入的内容
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    public static String changeFile(String url, String content) throws Exception {
        if (url == null || url.trim().length() == 0) {
            url = FastDFSUtil.uploadCouldChangeFileUrl(" ".getBytes(), "txt");
        }
        changeFile(url, content.getBytes());
        return url;
    }

    /**
     * 上传可修改的文件
     *
     * @param bytes    文件byte数组
     * @param lastName 文件后缀名
     * @return String数组   组名和文件存储路径
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    private static String[] uploadCouldChangeFile(byte[] bytes, String lastName) throws Exception {
        String[] strings = null;
        FastDFSBean fastClient = new FastDFSBean();
        strings = fastClient.getStorageClient().upload_appender_file(bytes, lastName.toLowerCase(), null);
        fastClient.close();
        return strings;
    }

    /**
     * 上传可修改的文件
     *
     * @param file     上传的文件
     * @param lastName 文件的后缀名
     * @return String数组   组名和文件存储路径
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    private static String[] uploadCouldChangeFile(File file, String lastName) throws Exception {
        return uploadCouldChangeFile(readFileToByteArray(file), lastName);
    }


    /**
     * 上传可修改的文件
     *
     * @param file 上传的文件
     * @return String数组   组名和文件存储路径
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    private static String[] uploadCouldChangeFile(File file) throws Exception {
        return uploadCouldChangeFile(file, readFileGetLastName(file));
    }

    /**
     * 上传可修改的文件
     *
     * @param file 上传的文件
     * @return 存入数据的Fpath
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    public static String uploadCouldChangeFileUrl(File file) throws Exception {
        if (file == null) return null;
        return uploadCouldChangeFileUrl(file, readFileGetLastName(file));
    }

    /**
     * 上传可修改的文件
     *
     * @param bytes    文件byte数组
     * @param lastName 文件后缀名
     * @return 存入数据的Fpath
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    public static String uploadCouldChangeFileUrl(byte[] bytes, String lastName) throws Exception {
        String[] strings = uploadCouldChangeFile(bytes, lastName);
        return getUrl(strings);
    }

    /**
     * 上传可修改的文件
     *
     * @param file     上传的文件
     * @param lastName 文件的后缀名
     * @return 存入数据的Fpath
     * @throws IOException
     * @throws Exception
     * @date 2016-09-02
     */
    public static String uploadCouldChangeFileUrl(File file, String lastName) throws Exception {
        String[] strings = null;
        try {
            strings = uploadCouldChangeFile(file, lastName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getUrl(strings);
    }

}
