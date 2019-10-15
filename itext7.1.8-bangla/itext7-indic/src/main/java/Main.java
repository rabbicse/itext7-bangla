import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.IOException;
import java.util.Objects;

public class Main {
    private static final String BANGLA_TEXT = "\u09AA\u09CD\u09B0\u09B8\u09BE\u09B0  \u09AE\u09A6\u09CD\u09AF\u09AA\u09BE\u09A8  \u0986\u09AE\u09BF \u0995\u09CB\u09A8 \u09AA\u09A5\u09C7 \u0995\u09CD\u09B7\u09C0\u09B0\u09C7\u09B0 \u09B2\u0995\u09CD\u09B7\u09CD\u09AE\u09C0 \u09B7\u09A8\u09CD\u09A1 \u09AA\u09C1\u09A4\u09C1\u09B2 \u09B0\u09C1\u09AA\u09CB \u0997\u0999\u09CD\u0997\u09BE \u098B\u09B7\u09BF";
    private static final String BANGLA_TTF_PATH = "fonts/Bangla.ttf";
    private static final String SOLAIMANLIPI_TTF_PATH = "fonts/SolaimanLipi_20-04-07.ttf";
    private static final String KALPURUSH_TTF_PATH = "fonts/kalpurush.ttf";
    private static final String SIYAMRUPALI_TTF_PATH = "fonts/Siyamrupali.ttf";

    public static void main(String[] args) {

        System.out.println("Trying to create PDF...");
        try {
            new Main().createPdf("pdf-with-bangla-font.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("PDF generation Done!");
        }
    }

    private void createPdf(String pdfPath) throws IOException {
        long startTime = System.currentTimeMillis();

        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(pdfPath);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        Document document = new Document(pdf);

        //Add paragraph to the document
        document.add(createHeader("Font: Bangla.ttf"));

        // Add bangla text with Bangla.ttf
        document.add(createBody(BANGLA_TEXT, createItextFont(getResourceFontPath(BANGLA_TTF_PATH))));

        //Add paragraph to the document
        document.add(createHeader("Font: SolaimanLipi_20-04-07.ttf"));

        // Add bangla text with Bangla.ttf
        document.add(createBody(BANGLA_TEXT, createItextFont(getResourceFontPath(SOLAIMANLIPI_TTF_PATH))));


        //Add paragraph to the document
        document.add(createHeader("Font: kalpurush.ttf"));

        // Add bangla text with Bangla.ttf
        document.add(createBody(BANGLA_TEXT, createItextFont(getResourceFontPath(KALPURUSH_TTF_PATH))));


//        //Add paragraph to the document
//        document.add(createHeader("Font: Siyamrupali.ttf"));
//
//        // Add bangla text with Bangla.ttf
//        document.add(createBody(BANGLA_TEXT, createItextFont(getResourceFontPath(SIYAMRUPALI_TTF_PATH), false)));

        //Close document
        document.close();

        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed Time: " + (((double) endTime - (double) startTime) / (double) (1000 * 60)) + " Min");

    }

    private Paragraph createHeader(String text) throws IOException {
        // Create a PdfFont
        PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        return new Paragraph(text).setFont(font);
    }

    private Paragraph createBody(String text, PdfFont font) {

        return new Paragraph(text).setFont(font);
    }

    private String getResourceFontPath(String path) {
        return Objects.requireNonNull(getClass().getClassLoader().getResource(path)).getPath();
    }

    private PdfFont createItextFont(String fontPath) throws IOException {
        return createItextFont(fontPath, true);
    }
    private PdfFont createItextFont(String fontPath, boolean embedded) throws IOException {
        return PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H, embedded, true);
    }
}
