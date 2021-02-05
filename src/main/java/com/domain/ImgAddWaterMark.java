package com.domain;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImgAddWaterMark {


    private void test(String sourceImgPath, String waterMarkContent, String tarImgPath, String fileExt) {
        Font font = new Font("宋体", Font.BOLD, 24);//水印字体，大小
        Color markContentColor = Color.white;//水印颜色
        Integer degree = -45;//设置水印文字的旋转角度
        float alpha = 1.0f;//设置水印透明度 默认为1.0  值越小颜色越浅
        OutputStream outImgStream = null;
        try {
            File srcImgFile = new File(sourceImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();//得到画笔
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
            g.setColor(markContentColor); //设置水印颜色
            g.setFont(font);              //设置字体
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));//设置水印文字透明度
            if (null != degree) {
                g.rotate(Math.toRadians(degree), bufImg.getWidth(), bufImg.getHeight());//设置水印旋转
            }
            JLabel label = new JLabel(waterMarkContent);
            FontMetrics metrics = label.getFontMetrics(font);
            int width = metrics.stringWidth(label.getText());//文字水印的宽
            int rowsNumber = srcImgHeight/width+srcImgHeight%width;// 图片的高  除以  文字水印的宽  打印的行数(以文字水印的宽为间隔)
            int columnsNumber = srcImgWidth/width+srcImgWidth%width;//图片的宽 除以 文字水印的宽  每行打印的列数(以文字水印的宽为间隔)
            //防止图片太小而文字水印太长，所以至少打印一次
            if(rowsNumber < 1){
                rowsNumber = 1;
            }
            if(columnsNumber < 1){
                columnsNumber = 1;
            }
            for(int j=0;j<rowsNumber;j++){
                for(int i=0;i<columnsNumber;i++){
                    g.drawString(waterMarkContent, i*width + j*width, -i*width + j*width);//画出水印,并设置水印位置
                }
            }
            g.dispose();// 释放资源
            // 输出图片
            outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, fileExt, outImgStream);
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        } finally{
            try {
                if(outImgStream != null){
                    outImgStream.flush();
                    outImgStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getMessage();
            }
        }
    }




    private static byte[] generateText(String pressText, String path, int x, int y,
                                       int fontSize, Color fontColor, int style){
        try {
            BufferedImage bgImage= ImageIO.read(new File(path));
            int wideth = bgImage.getWidth(null);
            int height = bgImage.getHeight(null);
            BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.drawImage(bgImage, 0, 0, wideth, height, null);
            //设置字体大小、颜色等
            g.setColor(fontColor);
            g.setFont(new Font("simsun", style, fontSize));

            g.drawString(pressText, x, y);
            g.dispose();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", out);
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void byte2image(byte[] data, String path) {
        if (data.length < 3 || path.equals("")){
            return;
        }
        try {
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
            System.out.println("生成图片成功：" + path);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    private static void addWaterMarkForImg(InputStream inputStream, String watermark, OutputStream outputStream, String fileExt) {
        Graphics2D graphics2D = null;
        Font font = new Font("simsun", Font.BOLD+Font.ITALIC, 50);//水印字体，大小
        Color markContentColor = Color.GRAY;//水印颜色
        Integer degree = -45;//设置水印文字的旋转角度
        float alpha = 1.0f;//设置水印透明度 默认为1.0  值越小颜色越浅
        try {
            BufferedImage bgImage= ImageIO.read(inputStream);
            int srcImgWidth = bgImage.getWidth(null);
            int srcImgHeight = bgImage.getHeight(null);

            BufferedImage image = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            graphics2D = image.createGraphics();
            graphics2D.drawImage(bgImage, 0, 0, srcImgWidth, srcImgHeight, null);
            graphics2D.setColor(markContentColor);

            graphics2D.setFont(font);
            //设置水印文字透明度
            graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            //设置水印旋转
//            graphics2D.rotate(Math.toRadians(degree), 0, 0);
            AffineTransform affineTransform = new AffineTransform();
            affineTransform.rotate(Math.toRadians(45), 0, 0);
            Font rotatedFont = font.deriveFont(affineTransform);
            graphics2D.setFont(rotatedFont);

            JLabel label = new JLabel(watermark);
            FontMetrics metrics = label.getFontMetrics(font);

            int width = metrics.stringWidth(label.getText());//文字水印的宽
            int rowsNumber = srcImgHeight/width+srcImgHeight%width;// 图片的高  除以  文字水印的宽  打印的行数(以文字水印的宽为间隔)
            int columnsNumber = srcImgWidth/width+srcImgWidth%width;//图片的宽 除以 文字水印的宽  每行打印的列数(以文字水印的宽为间隔)

            // 打印三行两列

            if (rowsNumber < 1 || columnsNumber < 1) {
                int rowWidth =  srcImgHeight / 2;
                int columnWidth = srcImgWidth / 2;
                graphics2D.drawString(watermark, columnWidth, rowWidth);//画出水印,并设置水印位置
            } else {
                int rowWidth =  srcImgHeight / 4;
                int columnWidth = srcImgWidth / 3;
                for(int j=1;j<4;j++){ // hang
                    for(int i=1;i<3;i++){ // lie
                        graphics2D.drawString(watermark, i * columnWidth, j * rowWidth);//画出水印,并设置水印位置
                    }
                }
            }

            ImageIO.write(image, fileExt, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (graphics2D != null) {
                graphics2D.dispose();
            }
        }
    }

    public static void main(String[] args) throws Exception {

        String path = "E:\\vs_img.jpg";
        String path2 = "E:\\vs_img_copy.jpg";

//        byte[] bytes = generateText("斯嘉丽", path, 210, 50, 30, Color.GRAY, Font.BOLD+Font.ITALIC);
//        byte2image(bytes, path2);

        FileInputStream fileInputStream = new FileInputStream(new File(path));
        FileOutputStream fileOutputStream = new FileOutputStream(new File(path2));

        addWaterMarkForImg(fileInputStream, "初审通过", fileOutputStream, "jpg");
    }

}
