package com.domain;

import com.itextpdf.text.pdf.BaseFont;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.model.SEPX;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Entities;
import org.jsoup.select.Elements;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyles;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用poi+itextpdf进行word转pdf
 * 先将word转成html，再将html转成pdf
 *
 * @author ：hewie
 * @date ：Created in 2020/2/27 22:41
 */
public class trans {

    private static long LEFT_MARGIN = 1800L;
    private static long RIGHT_MARGIN = 1800L;
    private static long TOP_MARGIN = 1440L;
    private static long BOTTOM_MARGIN = 1440L;


    /**
     * 将doc格式文件转成html
     *
     * @param docPath doc文件路径
     * @return html
     */
    public static String doc2Html(String docPath) {
        String content = null;
        ByteArrayOutputStream baos = null;
        try {
            HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(docPath));

            SEPX section = wordDocument.getSectionTable().getSections().get(0);
            section.getSectionProperties().setDyaTop(0);
            section.getSectionProperties().setDxaLeft(0);
            section.getSectionProperties().setDxaRight(0);
            section.getSectionProperties().setDyaBottom(0);

            WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
            wordToHtmlConverter.processDocument(wordDocument);
            Document htmlDocument = wordToHtmlConverter.getDocument();
            DOMSource domSource = new DOMSource(htmlDocument);
            baos = new ByteArrayOutputStream();
            StreamResult streamResult = new StreamResult(baos);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer serializer = tf.newTransformer();
            serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
            serializer.setOutputProperty(OutputKeys.METHOD, "html");
            serializer.transform(domSource, streamResult);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
                    baos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 将docx格式文件转成html
     *
     * @param docxPath docx文件路径
     * @return html
     */
    public static String docx2Html(String docxPath) {
        String content = null;

        FileInputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
// 1> 加载文档到XWPFDocument
            in = new FileInputStream(new File(docxPath));
            XWPFDocument document = new XWPFDocument(in);

            CTSectPr sectPr = document.getDocument().getBody().getSectPr();
            CTPageMar pageMar = sectPr.getPgMar();
            pageMar.setLeft(BigInteger.valueOf(LEFT_MARGIN));
            pageMar.setRight(BigInteger.valueOf(RIGHT_MARGIN));
            pageMar.setTop(BigInteger.valueOf(TOP_MARGIN));
            pageMar.setBottom(BigInteger.valueOf(BOTTOM_MARGIN));


// 2> 解析XHTML配置（这里设置IURIResolver来设置图片存放的目录）
            XHTMLOptions options = XHTMLOptions.create();
// 存放word中图片的目录
//            options.setExtractor(new FileImageExtractor(new File(imageDir)));
//            options.URIResolver(new BasicURIResolver(imageDir));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);
// 3> 将XWPFDocument转换成XHTML
            baos = new ByteArrayOutputStream();
            XHTMLConverter.getInstance().convert(document, baos, options);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (baos != null) {
                    content = new String(baos.toByteArray(), StandardCharsets.UTF_8);
                    baos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return content;
    }

    /**
     * 使用jsoup规范化html
     *
     * @param html html内容
     * @return 规范化后的html
     */
    private static String formatHtml(String html) {
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
// 去除过大的宽度
        String style = doc.attr("style");
        if (StringUtils.isNotEmpty(style) && style.contains("width")) {
            doc.attr("style", "");
        }
        Elements divs = doc.select("div");
        for (Element div : divs) {
            String divStyle = div.attr("style");
            if (StringUtils.isNotEmpty(divStyle) && divStyle.contains("width")) {
                div.attr("style", "");
            }
        }
// jsoup生成闭合标签
        doc.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        doc.outputSettings().escapeMode(Entities.EscapeMode.xhtml);
        return doc.html();
    }

    /**
     * html转成pdf
     *
     * @param html html
     */
//    private static void htmlToPdf(String html, String outputPdfPath) {
//        com.itextpdf.text.Document document = null;
//        try {
//// 纸
//            document = new com.itextpdf.text.Document(PageSize.A4);
//// 笔
//            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(outputPdfPath));
//            document.open();
//
//
//// html转pdf
//            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(html.getBytes()),
//                    StandardCharsets.UTF_8, new FontProvider() {
//                        @Override
//                        public boolean isRegistered(String s) {
//                            return false;
//                        }
//
//                        @Override
//                        public Font getFont(String s, String s1, boolean embedded, float size, int style, BaseColor baseColor) {
//// 配置字体
//                            Font font = null;
//                            try {
//// 方案一：使用本地字体(本地需要有字体)
//                                BaseFont bf = BaseFont.createFont("E:\\simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//// 方案二：使用jar包：iTextAsian，这样只需一个jar包就可以了
////                                BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
//                                font = new Font(bf, size, style, baseColor);
//                                font.setColor(baseColor);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            return font;
//                        }
//                    });
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (document != null) {
//                document.close();
//            }
//        }
//    }
    public static void transform(String html) throws Exception {
        OutputStream os = new FileOutputStream("PDF文件输出全路径.pdf");
        ITextRenderer renderer = new ITextRenderer();

        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("E:\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        renderer.layout();
        renderer.createPDF(os);
        renderer.finishPDF();
        os.close();
    }


    public static void transforms(InputStream inputStream) throws IOException, XmlException {

        Map<Integer, Integer> map = new HashMap<>(10);
        for (int j = 0; j < 10; j++) {
            map.put(j, 0);
        }

        XWPFDocument doc = new XWPFDocument(inputStream);
        // 在没有字体的服务器上发布要用到下面 options,同时在resource目录下加入字体文件， windows 服务器上可不加
        PdfOptions options = PdfOptions.create();
//        options.getConfiguration().configure();
        options.fontEncoding("UTF-8");
        int type = 0;
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            String styleIDStr = paragraph.getStyleID();

            if (StringUtils.isEmpty(styleIDStr)) {
                continue;
            }

            if (styleIDStr.matches("^\\d$")) {

                System.out.println(styleIDStr);
                StringBuilder preStr = new StringBuilder();

                int styleID = Integer.parseInt(styleIDStr);
                if (styleID < type) {
                    for (int i = (styleID + 1); i < 10; i++) {
                        map.put(i, 0);
                    }
                }
                if (styleID <= type) {
                    // map 中 key 对应的值 加一
                    map.put(styleID, (map.get(styleID) + 1));
                }

                for (int n = 1; n <= styleID; n++) {
                    Integer times = map.get(n);


                    if (n == styleID) {
                        preStr.append((times + 1));
                        break;
                    }

                    preStr.append((times + 1)).append(".");
                }


                List<XWPFRun> runs = paragraph.getRuns();

                for (XWPFRun run : runs) {
//                    run.addBreak();
                    String text = run.text();

                    XWPFRun xwpfRun = paragraph.insertNewRun(0);
                    xwpfRun.setText(preStr + "   " + text);
                    paragraph.removeRun(1);
                    break;
                }

                type = styleID;
                paragraph.setStyle("50");
            }

            System.out.println(paragraph.getStyleID());
            System.out.println(paragraph.getStyle());
//            System.out.println("=======================");
        }

        System.out.println("\n\n\n");
        CTStyles style1 = doc.getStyle();
        CTStyle[] styleArray = style1.getStyleArray();
        for (CTStyle ctStyle : styleArray) {
//                if (ctStyle.getName().getVal().contains("heading")) {
//                    System.out.println();
//                    ctStyle.setStyleId("50");
//                }
            System.out.println(ctStyle.getName().getVal());
            System.out.println(ctStyle.getStyleId());
            System.out.println("=======================");
        }

        options.fontProvider((familyName, encoding, size, style, color) -> {
            try {
                com.lowagie.text.pdf.BaseFont bfChinese = com.lowagie.text.pdf.BaseFont.createFont("E:\\simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
                if (size == 0 || size < 1) {
                    size = 14;
                }

                com.lowagie.text.Font fontChinese = new com.lowagie.text.Font(bfChinese, size, style, color);
                if (familyName != null)
                    fontChinese.setFamily(familyName);
                return fontChinese;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        });
        OutputStream out = new FileOutputStream("PDF文件输出全路径.pdf");
        PdfConverter.getInstance().convert(doc, out, options);
        out.close();
    }


    public static void main(String[] args) throws Exception {
        String basePath = "E:\\";
        String docPath = basePath + "会议纪要 - 副本.doc";
//        String docxPath = basePath + "加快了进攻苏联卡_1610672015974.docx";
        String docxPath = basePath + "加快了进攻苏联卡_1610672015974.docx";
//        String docxPath = basePath + "7.17技术方案和评分原版.docx";
        String pdfPath = basePath + "index.pdf";

// 测试doc转pdf
//        String docHtml = doc2Html(docPath);
//        String htmlhead = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
//                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">";
//        docHtml = docHtml.replaceFirst("<html>", htmlhead);
////        String sss = "<style type=\"text/css\">" +
//////                " body{width:794px;height:1123px;border:1px solid #000000;} " +
////                "        @page{size:1440;!important;}\n";
////        docHtml = docHtml.replaceFirst("<style type=\"text/css\">", sss);
////        docHtml = docHtml.replace("仿宋_GB2312", "SimSun");
//
////        Pattern pattern = Pattern.compile("font-family:([\\s\\S]*?);");
//
//        docHtml = docHtml.replaceAll("font-family:([\\s\\S]*?);", "font-family:SimSun;");
////        docHtml = docHtml.replaceAll("width:([\\s\\S]*?);", "width:1in;");
//
//        docHtml = formatHtml(docHtml);
////        System.out.println("================================================");
//        transform(docHtml);
//        writeToOutputStreamAsPDF(new ByteArrayInputStream(docHtml.getBytes()), pdfPath);
//        htmlToPdf(docHtml, pdfPath);
// 测试docx转pdf
//        String docxHtml = docx2Html(docxPath);
//        System.out.println(docxHtml);
//        String htmlhead = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
//                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">";
//        docxHtml = docxHtml.replaceFirst("<html>", htmlhead);
//        String sss = "<style type=\"text/css\">" +
////                " body{width:794px;height:1123px;border:1px solid #000000;} " +
//                "        @page{size:1440;!important;}\n";
//        docxHtml = docxHtml.replaceFirst("<style type=\"text/css\">", sss);
//        docxHtml = docxHtml.replaceAll("font-family:([\\s\\S]*?);", "font-family:SimSun;");
//        docxHtml = formatHtml(docxHtml);
//        System.out.println(docxHtml);
//////        docxHtml = docxHtml.replace("___", "张三");
////        htmlToPdf(docxHtml, pdfPath);
//        transform(docxHtml);


        InputStream in = new FileInputStream(new File(docxPath));
        transforms(in);
//        XWPFDocument document = new XWPFDocument(in);
//        PdfOptions options = PdfOptions.create();
////
//        OutputStream out = new FileOutputStream(new File(pdfPath));
////
//////        ByteArrayOutputStream out = new ByteArrayOutputStream();
////
//        PdfConverter.getInstance().convert(document, out, options);

//        System.out.println(out.toByteArray().toString());
//
//
//        document.close();
//        out.close();


    }
}