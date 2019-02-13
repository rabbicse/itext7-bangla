package work.rabbi.pdfgenerator;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        try {
            new App().createPdf("modified_generated.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createPdf(String pdfPath) throws IOException {

        long startTime = System.currentTimeMillis();

        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(pdfPath);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        // Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);

        Paragraph para1 = new Paragraph("Hello World!");
        para1.setFont(font);

        //Add paragraph to the document
        document.add(para1);


        // start of bangla test operation
        // bangla font path
        String banglaFontPath = "/home/mehmet/IdeaProjects/ITextCDemo/fonts/Bangla.ttf";

        // bangla font
        PdfFont banglaFont = PdfFontFactory.createFont(banglaFontPath, PdfEncodings.IDENTITY_H, true, true);

        // start of bangla test operation
        // bangla font path
        String banglaFontPath1 = "/home/mehmet/IdeaProjects/ITextCDemo/fonts/SolaimanLipi_20-04-07.ttf";

        // bangla font
        PdfFont banglaFont1 = PdfFontFactory.createFont(banglaFontPath1, PdfEncodings.IDENTITY_H, true, true);

        // bangla text
//        String text = "\u099A\u09BE\u09AA\u09BE\u0987\u09A8\u09AC\u09BE\u09AC\u0997\u09A8\u09CD\u099C \u09A6\u09A8\u09CD\u09A6\u09CD\u09AC";
        String text = " \u09AA\u09CD\u09B0\u09B8\u09BE\u09B0  \u09AE\u09A6\u09CD\u09AF\u09AA\u09BE\u09A8  \u0986\u09AE\u09BF \u0995\u09CB\u09A8 \u09AA\u09A5\u09C7 \u0995\u09CD\u09B7\u09C0\u09B0\u09C7\u09B0 \u09B2\u0995\u09CD\u09B7\u09CD\u09AE\u09C0 \u09B7\u09A8\u09CD\u09A1 \u09AA\u09C1\u09A4\u09C1\u09B2 \u09B0\u09C1\u09AA\u09CB \u0997\u0999\u09CD\u0997\u09BE \u098B\u09B7\u09BF";

        // add bangla text
        Paragraph banglaPara = new Paragraph(text);

        // set bangla font to paragraph
        banglaPara.setFont(banglaFont);

        // add bangla text
        Paragraph banglaPara1 = new Paragraph(text);

        // set bangla font to paragraph
        banglaPara1.setFont(banglaFont1);

        // add bangla paragraph to document
        document.add(banglaPara);

        // add bangla paragraph to document
        document.add(banglaPara1);
        // end bangla operation


        //Close document
        document.close();

        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (((double) endTime - (double) startTime) / (double) (1000 * 60)) + " Min");

    }
}
