package shoecity.shoecity.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;
import shoecity.shoecity.common.CommonContant;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by FENGJINGJU on 2018/7/25.
 *
 * 文件操作工具类
 *
 */
public class FileUtil {
    /**
     * 文件上传，缩放并剪裁 上传的图片至规定大小
     * @param type 根据传入类型，调用不同的缩放方法
     *
     * 返回上传路径
     */
    public static String fileUpLoad(MultipartFile file, String windowsPath, String linuxPath,String type) throws IOException {
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String extendNmae = null;
        if (index != -1) {
            extendNmae = fileName.substring(index);
        }
        fileName = UuidUtil.getUuid() + "_" + String.valueOf(System.currentTimeMillis()) + extendNmae;

        // 获取当前年月，用作文件夹命名
        Calendar cal = Calendar.getInstance();
        String year = String.valueOf(cal.get(Calendar.YEAR));
        String month = String.valueOf(cal.get(Calendar.MONTH) + 1);

        String savePath = null;
        String systemName = System.getProperties().getProperty("os.name");
        if (systemName.toLowerCase().contains("windows")) {// windows操作系统
            savePath = windowsPath + year + "\\" + month + "\\";// 这里结尾不加分隔符也没有关系，但是为了返回路径时区分windows和linux
        } else {// Linux，其他的忽略
            savePath = linuxPath + year + "/" + month + "/";
        }
        File saveFile = new File(savePath, fileName);
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();// 若文件夹不存在，则创建
        }
        try {
            file.transferTo(saveFile);// 上传图片
        } catch (IOException e) {
            System.out.println("路径错误！");
        }

        //缩放并剪裁 上传的图片至规定大小
        if ("exact".equals(type)) {
            FileUtil.exactResizeImage(saveFile, CommonContant.PRODUCT_PUBLISH_IMAGE_WIDTH, CommonContant.PRODUCT_PUBLISH_IMAGE_HIGH);
        }
        if ("width".equals(type)) {
            FileUtil.widthResizeImage(saveFile, CommonContant.PRODUCT_DETAIL_IMAGE_WIDTH);
        }
        return savePath + fileName;
    }

    /**
     * 精确缩放上传的图片至规定大小，长宽必须完全符合，不符合则放大后剪裁
     *
     * @param file 图片文件
     * @param high 规定高度
     * @param width 规定宽度
     *
     * */
    public static void exactResizeImage(File file, Integer width, Integer high) throws IOException {
        //通过文件路径创建ImageIcon
        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
        //通过ImageIcon得到Image对象
        Image image = icon.getImage();
        int imageHigh = image.getHeight(null);
        int imageWidth = image.getWidth(null);
        double highPersent = high * 1.0 / imageHigh;//高度缩放比例【<1缩小，>1放大】
        double widthPersent = width * 1.0 / imageWidth;//宽度缩放比例【<1缩小，>1放大】
        // 如果得到的图片比要求的长宽都大，则缩小，然后剪裁；如果得到的图片比要求的长宽都小，则放大，然后剪裁;
        if ((imageHigh > high && imageWidth > width) || (imageHigh < high && imageWidth < width)) {
            if (highPersent == widthPersent) {
                Thumbnails.of(file).size(width, high).toFile(file);
            } else {
                if (highPersent > widthPersent) {
                    Thumbnails.of(file).scale(highPersent).toFile(file);
                } else {
                    Thumbnails.of(file).scale(widthPersent).toFile(file);
                }
                Thumbnails.of(file).sourceRegion(Positions.CENTER, width, high).scale(1.0).toFile(file);// 剪裁，中心区域
            }
        } else {
            if (imageHigh > high) {// 如果只是高度大，则需要将宽度放大至规定宽度，然后剪裁高度
                Thumbnails.of(file).scale(widthPersent).toFile(file);
            } else if (imageWidth > width) {// 如果只是宽度大，则需要将高度放大至规定高度，然后剪裁宽度
                Thumbnails.of(file).scale(highPersent).toFile(file);
            }
            Thumbnails.of(file).sourceRegion(Positions.CENTER, width, high).scale(1.0).toFile(file);// 剪裁，中心区域
        }
    }

    /**
     * 缩放上传的图片至规定大小，只要宽度达标，不要求高度，无需剪裁
     *
     * @param file 图片文件
     * @param width 规定宽度
     *
     * */
    public static void widthResizeImage(File file, Integer width) throws IOException {
        //通过文件路径创建ImageIcon
//        ImageIcon icon = new ImageIcon(file.getAbsolutePath());
//        //通过ImageIcon得到Image对象
//        Image image = icon.getImage();
//        int imageWidth = image.getWidth(null);
//        double widthPersent = width * 1.0 / imageWidth;//宽度缩放比例【<1缩小，>1放大】
//        // 按宽度比例放大或缩小
//        Thumbnails.of(file).scale(widthPersent).toFile(file);
        Thumbnails.of(file).width(width).toFile(file);
    }

    /**
     * 按要求缩放图片，并拼装图片路径。以分号分割
     *
     * @param type 根据传入类型，调用不同的缩放方法
     *
     * */
    public static String getImageUrl(List<MultipartFile> fileList, String windowsPath, String linuxPath, String staticPath,String type) throws IOException {
        StringBuffer productImageUrlString = new StringBuffer();
        // 批量上传商品缩略图
        for (MultipartFile file : fileList) {
            String fileUrl = FileUtil.fileUpLoad(file, windowsPath, linuxPath, type);

            // fileUrl为绝对路径，由于springBoot前台展示外部图片必须使用静态资源的路径的相对路径，所以这里对路径做适配
            String springStaticLocation = staticPath;
            springStaticLocation = springStaticLocation.replaceAll("file:", "");
            String[] locations = springStaticLocation.split(",");
            for (String s : locations) {
                if (fileUrl.startsWith(s)) {
                    fileUrl = fileUrl.replace(s, "");
                }
            }
            productImageUrlString.append(fileUrl);
            productImageUrlString.append(";");
        }
        return productImageUrlString.toString();
    }
}
