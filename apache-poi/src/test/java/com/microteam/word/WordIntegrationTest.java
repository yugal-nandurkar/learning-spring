package com.microteam.word;

import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordIntegrationTest {
    private static WordDocument wordDocument;

    @BeforeAll
    static void setUp() throws Exception {
        wordDocument = new WordDocument();
        wordDocument.createWordDocument(); // This method generates the document
    }

    @Test
    void testDocumentContentAndFormatting() throws Exception {
        Path wordFilePath = Paths.get("src/main/resources/word/rest-with-spring.docx");
        assertTrue(Files.exists(wordFilePath), "The Word document should exist.");

        try (XWPFDocument document = new XWPFDocument(Files.newInputStream(wordFilePath))) {
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            assertTrue(paragraphs.size() > 0, "The document should contain paragraphs.");

            // Title Paragraph
            XWPFParagraph titleParagraph = paragraphs.get(0);
            XWPFRun titleRun = titleParagraph.getRuns().get(0);
            assertEquals("Build Your REST API with Spring", titleRun.getText(0), "Title text is incorrect.");
            assertEquals("009933", titleRun.getColor(), "Title color is incorrect.");
            assertTrue(titleRun.isBold(), "Title should be bold.");
            assertEquals("Courier", titleRun.getFontFamily(), "Title font is incorrect.");
            assertEquals(20, titleRun.getFontSize(), "Title font size is incorrect.");

            // Subtitle Paragraph
            XWPFParagraph subtitleParagraph = paragraphs.get(1);
            assertEquals("from HTTP fundamentals to API Mastery", subtitleParagraph.getText(), "Subtitle text is incorrect.");

            // Section Title Paragraph
            XWPFParagraph sectionTitleParagraph = paragraphs.get(3);
            assertEquals("What makes a good API?", sectionTitleParagraph.getText(), "Section title text is incorrect.");

            // Content Paragraphs
            XWPFParagraph paragraph1 = paragraphs.get(4);
            assertEquals("This is the content from poi-word-para1.docx", paragraph1.getText(), "Paragraph 1 content is incorrect.");

            XWPFParagraph paragraph2 = paragraphs.get(5);
            assertEquals("This is the content from poi-word-para2.docx", paragraph2.getText(), "Paragraph 2 content is incorrect.");

            XWPFParagraph paragraph3 = paragraphs.get(6);
            assertEquals("This is the content from poi-word-para3.docx", paragraph3.getText(), "Paragraph 3 content is incorrect.");
        }
    }

    @Test
    void testImageAddedToDocument() throws Exception {
        Path wordFilePath = Paths.get("src/main/resources/word/rest-with-spring.docx");
        assertTrue(Files.exists(wordFilePath), "The Word document should exist.");

        try (XWPFDocument document = new XWPFDocument(Files.newInputStream(wordFilePath))) {
            // Verify that the document contains an image by checking for an image paragraph
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            boolean imageFound = false;
            for (XWPFParagraph paragraph : paragraphs) {
                for (XWPFRun run : paragraph.getRuns()) {
                    if (run.getCTR().getDrawingList() != null && !run.getCTR().getDrawingList().isEmpty()) {
                        imageFound = true;
                        break;
                    }
                }
                if (imageFound) break;
            }

            assertTrue(imageFound, "The document should contain an image.");
        }
    }
}
