package net.toiditimtoi.epubtopdfconverter;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.CompressionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class XhtmlToPdfConverterTest {

    public static final String BASE_URI = "src/test/resources/git-ignore-this/output/OEBPS";

    public static final String htmlFileSrc = "src/test/resources/git-ignore-this/output/OEBPS/ch01.xhtml";
    public static final String PDF_OUTPUT_FOLDER =  "src/test/resources/git-ignore-this/result";

    private ConverterProperties defaultConverterProperties() {
        ConverterProperties properties = new ConverterProperties();
        properties.setBaseUri(BASE_URI);
        return properties;
    }

    private InputStream createInputStreamFromDefaultSourceFile() throws IOException {
        return new FileInputStream(htmlFileSrc);
    }
    @Test
    public void testExportToPdf() throws Exception {
        var pdfFileSrc = PDF_OUTPUT_FOLDER + "/no-compression.pdf";
        try (OutputStream os = new FileOutputStream(pdfFileSrc)) {
            try (InputStream xhtmlInputStream = new FileInputStream(htmlFileSrc)) {
                HtmlConverter.convertToPdf(xhtmlInputStream, os, defaultConverterProperties());
            }
        }
    }

    @Test
    public void fullCompressionModeWithPdfWriter() throws Exception {
        var pdfOutput = new FileOutputStream(PDF_OUTPUT_FOLDER + "/full-compression.pdf");

        var pdfWriter = new PdfWriter(pdfOutput)
                .setCompressionLevel(CompressionConstants.BEST_COMPRESSION);
        var htmlInputStream = new FileInputStream(htmlFileSrc);
        HtmlConverter.convertToPdf(htmlInputStream, pdfWriter, defaultConverterProperties());
    }

    @Test
    public void playingWithPdfDocument() throws Exception {
        try (var output = new FileOutputStream(PDF_OUTPUT_FOLDER + "/by-pdf-document.pdf")) {
            var pdfWriter = new PdfWriter(output);
            var pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.setTagged();
            try (var htmlInputStream = createInputStreamFromDefaultSourceFile()) {
                HtmlConverter.convertToPdf(htmlInputStream,pdfDocument, defaultConverterProperties());
            }
        }
    }

}

