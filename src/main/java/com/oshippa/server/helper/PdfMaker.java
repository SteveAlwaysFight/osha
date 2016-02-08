/*
 * Copyright (c) 2016. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.oshippa.server.helper;

import com.lowagie.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by steve on 1/25/16.
 */
public class PdfMaker {
    public static void main(String[] args) throws Exception {
        String inputFile = "conf/template/test.html";
        String url = new File(inputFile).toURI().toURL().toString();
        String outputFile = "firstdoc.pdf";
        OutputStream os = new FileOutputStream(outputFile);
        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocument(url);
        renderer.setDocumentFromString(new FileReaderHelper().readFileAsString("index.jsp"));

        // 解决中文支持问题
//        ITextFontResolver fontResolver = renderer.getFontResolver();
//        fontResolver.addFont("C:/Windows/Fonts/arialuni.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        // 解决图片的相对路径问题
//        renderer.getSharedContext().setBaseURL("file:/D:/Work/Demo2do/Yoda/branch/Yoda%20-%20All/conf/template/");

        renderer.layout();
        renderer.createPDF(os);

        os.close();
    }
}
