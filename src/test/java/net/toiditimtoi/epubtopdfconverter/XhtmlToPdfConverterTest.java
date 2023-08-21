package net.toiditimtoi.epubtopdfconverter;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class XhtmlToPdfConverterTest {

    public static final String BASE_URI = "/Users/kevin/workspace/epub-to-pdf-converter/src/test/resources/git-ignore-this/output/OEBPS";
    @Test
    public void testExportToPdf() throws Exception {
        try (OutputStream os = new FileOutputStream("/Users/kevin/workspace/epub-to-pdf-converter/src/test/resources/git-ignore-this/demo.pdf")) {
            try (InputStream xhtmlInputStream = new FileInputStream("/Users/kevin/workspace/epub-to-pdf-converter/src/test/resources/git-ignore-this/output/OEBPS/ch01.xhtml")) {
                ConverterProperties properties = new ConverterProperties();
                properties.setBaseUri(BASE_URI);
                HtmlConverter.convertToPdf(xhtmlInputStream, os, properties);
            }
        }
    }
}

