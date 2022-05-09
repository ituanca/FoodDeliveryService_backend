package com.example.demo.utils;

import com.example.demo.model.Food;
import com.example.demo.model.Menu;
import com.example.demo.model.Restaurant;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lombok.Setter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

@Setter
public class PDFGeneratorMenu {

    private Menu menu;

    public void generate() throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, new FileOutputStream("src\\main\\java\\com\\example\\demo\\pdf\\menu" + menu.getId() +".pdf"));
        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTitle.setSize(20);

        Paragraph paragraph = new Paragraph(menu.getRestaurant().getName() + "\n" + "Menu" + "\n\n", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100f);
        table.setWidths(new int[] { 2, 4, 2, 1 });
        table.setSpacingBefore(5);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(Color.BLACK);
        cell.setPhrase(new Phrase("Item", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Ingredients", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Category", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        for (Food food : menu.getListOfFood()) {
            table.addCell(food.getFood());
            table.addCell(food.getListOfIngredients());
            table.addCell(food.getCategory().getCategory());
            table.addCell(food.getPrice().toString());
        }

        document.add(table);
        document.close();
    }
}
