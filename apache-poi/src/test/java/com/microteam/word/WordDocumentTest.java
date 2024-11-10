package com.microteam.word;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;
import java.nio.file.Paths;

class WordDocumentTest {

    private WordDocument wordDocument;

    @BeforeEach
    void setUp() {
        wordDocument = new WordDocument();
    }

    @Test
    void testCreateWordDocument() {
        // This test verifies the document is created and paragraphs are appended correctly
        wordDocument.createWordDocument();

        File outputFile = new File("src/main/resources/word/rest-with-spring.docx");
        assertTrue(outputFile.exists(), "The Word document should be created.");

        // Optionally, check if paragraphs are correctly appended (you can load the document and verify content)
        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(outputFile.toPath()))) {
            assertFalse(doc.getParagraphs().isEmpty(), "Document should contain paragraphs.");
        } catch (Exception e) {
            fail("Failed to read the Word document: " + e.getMessage());
        }
    }

    @Test
    void testAddImageToDocument() {
        String imagePath = "src/main/resources/word/logo-leaf.png";
        String imageDescription = "This is a sample image.";

        // Add image to the document
        wordDocument.addImageToDocument(imagePath, imageDescription);

        // Verify that the image was added successfully by checking the document
        File outputFile = new File("src/main/resources/word/rest-with-spring.docx");
        assertTrue(outputFile.exists(), "Document with image should be saved.");

        // Optionally, you can inspect the document to check the image, or check if image exists at the path.
        assertTrue(Files.exists(Paths.get(imagePath)), "Image file should exist.");
    }

    @Test
    void testSaveDocument() {
        String savePath = "src/main/resources/word/test-document.docx";

        // Save the document
        wordDocument.createWordDocument();
        wordDocument.saveDocument(savePath);

        // Verify the document is saved at the specified path
        File savedFile = new File(savePath);
        assertTrue(savedFile.exists(), "The document should be saved at the specified path.");

        // Clean up by deleting the test file
        savedFile.delete();
    }

    @Test
    void testAppendSingleParagraph() {
        String paragraphText = "This is a single paragraph.";

        // Append a single paragraph
        wordDocument.appendSingleParagraph(paragraphText);

        // Verify the paragraph was added
        File outputFile = new File("src/main/resources/word/rest-with-spring.docx");
        assertTrue(outputFile.exists(), "Document should exist.");

        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(outputFile.toPath()))) {
            assertTrue(doc.getParagraphs().stream().anyMatch(p -> p.getText().equals(paragraphText)),
                    "Document should contain the appended paragraph.");
        } catch (Exception e) {
            fail("Failed to read the Word document: " + e.getMessage());
        }
    }

    @Test
    void testClearDocument() {
        wordDocument.createWordDocument(); // Create a document first
        wordDocument.clearDocument(); // Clear it

        // Verify if the document is cleared
        File outputFile = new File("src/main/resources/word/rest-with-spring.docx");
        assertTrue(outputFile.exists(), "The document should exist after clearing.");

        try (XWPFDocument doc = new XWPFDocument(Files.newInputStream(outputFile.toPath()))) {
            assertTrue(doc.getParagraphs().isEmpty(), "The document should be empty after clearing.");
        } catch (Exception e) {
            fail("Failed to read the Word document: " + e.getMessage());
        }
    }
}
