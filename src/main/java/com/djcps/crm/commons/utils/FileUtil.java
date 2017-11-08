package com.djcps.crm.commons.utils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;

/**
 * 文件转换处理类
 * Created by TruthBean on 2017-04-24 16:50.
 */
public class FileUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static byte[] convert(MultipartFile file) throws IOException {
        validateFile(file);
        return file.getBytes();
    }

    private static void validateFile(MultipartFile file) throws IOException {
        String contentType = file.getContentType();
        if (!contentType.equals(MediaType.IMAGE_JPEG.toString()) && !contentType.equals(MediaType.IMAGE_PNG.toString()))
            throw new IOException("Invalid media type");
    }

    private static BufferedImage convertToImage(MultipartFile file) throws IOException {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        return ImageIO.read(in);
    }

    private static byte[] convertToByteArr(BufferedImage image, String contentType) throws IOException {
        byte[] imageInByte;

        String typeName = "jpg";
        if (contentType.equals(MediaType.IMAGE_PNG.toString()))
            typeName = "png";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, typeName, baos);
        baos.flush();
        imageInByte = baos.toByteArray();
        baos.close();

        return imageInByte;
    }

    private static byte[] convertToByteArr(MultipartFile file, String ext) throws IOException {
        byte[] imageInByte;
        InputStream in = new ByteArrayInputStream(file.getBytes());
        BufferedImage image = ImageIO.read(in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, ext, baos);
        baos.flush();
        imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    public static byte[] compressPic(MultipartFile file, int width, int height, float per, String ext) throws IOException {
        InputStream in = new ByteArrayInputStream(file.getBytes());
        BufferedImage img = ImageIO.read(in);

        int oldWidth = img.getWidth(null); //得到源图宽
        int oldHeight = img.getHeight(null);
        int newWidht = 0;
        int newHeight = 0; //得到源图长

        double w2 = oldWidth / width;
        double h2 = oldHeight / height;

        //图片跟据长宽留白，成一个正方形图。
        BufferedImage oldpic;
        if (oldWidth > oldHeight) {
            oldpic = new BufferedImage(oldWidth, oldWidth, BufferedImage.TYPE_INT_RGB);
        } else {
            if (oldWidth < oldHeight) {
                oldpic = new BufferedImage(oldHeight, oldHeight, BufferedImage.TYPE_INT_RGB);
            } else {
                oldpic = new BufferedImage(oldWidth, oldHeight, BufferedImage.TYPE_INT_RGB);
            }
        }
        Graphics2D g = oldpic.createGraphics();
        g.setColor(Color.white);
        if (oldWidth > oldHeight) {
            g.fillRect(0, 0, oldWidth, oldWidth);
            g.drawImage(img, 0, (oldWidth - oldHeight) / 2, oldWidth, oldHeight, Color.white, null);
        } else {
            if (oldWidth < oldHeight) {
                g.fillRect(0, 0, oldHeight, oldHeight);
                g.drawImage(img, (oldHeight - oldWidth) / 2, 0, oldWidth, oldHeight, Color.white, null);
            } else {
                //g.fillRect(0,0,oldHeight,oldHeight);
                g.drawImage(img.getScaledInstance(oldWidth, oldHeight, Image.SCALE_SMOOTH), 0, 0, null);
            }
        }
        g.dispose();
        img = oldpic;
        //图片调整为方形结束
        if (oldWidth > width)
            newWidht = (int) Math.round(oldWidth / w2);
        else
            newWidht = oldWidth;
        if (oldHeight > height)
            newHeight = (int) Math.round(oldHeight / h2);//计算新图长宽
        else
            newHeight = oldHeight;
        BufferedImage tag = new BufferedImage(newWidht, newHeight, BufferedImage.TYPE_INT_RGB);
        //tag.getGraphics().drawImage(src,0,0,new_w,new_h,null); //绘制缩小后的图
        tag.getGraphics().drawImage(img.getScaledInstance(newWidht, newHeight, Image.SCALE_SMOOTH), 0, 0, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(tag, ext, baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return imageInByte;
    }

    public static String saveFile(MultipartFile filedata, String saveFilePath, String newFileName) {
        // 保存相对路径到数据库 文件写入服务器
        if (filedata != null && !filedata.isEmpty()) {
            // 获取文件的文件名
            //String fileName = filedata.getOriginalFilename();
            // 获取文件的扩展名
            //String extensionName = FilenameUtils.getExtension(fileName);
            //String newFileName = StringUtils.makeRandomString() + "." + extensionName;
            try {
                saveFile(newFileName, filedata, saveFilePath);
                return newFileName;
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        throw new RuntimeException("文件上传异常");
    }

    /**
     * 存储图片到本地
     *
     * @param newFileName 新文件名
     * @param filedata    需要存储的文件
     */
    private static void saveFile(String newFileName, MultipartFile filedata, String saveFilePath) {
        try {
            // 根据配置文件获取服务器图片存放路径
        /* 构建文件目录 */
            File fileDir = new File(saveFilePath);
            if (!fileDir.exists()) {
                if (!fileDir.mkdirs()) {
                    throw new RuntimeException("创建文件夹失败");
                }
            }
            FileOutputStream out = new FileOutputStream(saveFilePath + "/" + newFileName);
            // 写入文件
            out.write(filedata.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean delFile(String fileName) {
        Path path = Paths.get(fileName);
        try {
            Files.delete(path);
            return true;
        } catch (NoSuchFileException x) {
            logger.error("{}: no such" + " file or directory%n", path);
            x.printStackTrace();
        } catch (DirectoryNotEmptyException x) {
            logger.error("{} not empty", path);
            x.printStackTrace();
        } catch (IOException x) {
            // File permission problems are caught here.
            logger.error("{} has less permission or is using", path);
            x.printStackTrace();
        }
        return false;
    }

    public static MultipartBody.Part fileToMultipartBodyPart(String fileParams, MultipartFile file) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse(file.getContentType()), file.getBytes());
        builder.addFormDataPart(fileParams, file.getName(), requestBody);
        builder.setType(MultipartBody.FORM);
        MultipartBody.Part part = MultipartBody.Part.createFormData(file.getName(), file.getOriginalFilename(), requestBody);
        return part;
    }

}