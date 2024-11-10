package com.microteam.word;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordDocument {
    private static final Logger logger = Logger.getLogger(WordDocument.class.getName());
    private XWPFDocument document;

    // Constructor initializes the document
    public WordDocument() {
        this.document = new XWPFDocument();
    }

    // Method to append paragraphs from a .docx file
    public void appendParagraphs(String fileName) {
        try (FileInputStream fis = new FileInputStream(Paths.get("src/main/resources/word", fileName).toFile())) {
            XWPFDocument doc = new XWPFDocument(fis);
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                // Append each paragraph to the current document
                this.document.createParagraph().createRun().setText(paragraph.getText());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + fileName, e);
        }
    }

    // Method to create a Word document by appending paragraphs from multiple .docx files
    public void createWordDocument() {
        logger.info("Starting Word document creation process...");
        appendParagraphs("poi-word-para1.docx");
        appendParagraphs("poi-word-para2.docx");
        appendParagraphs("poi-word-para3.docx");
        saveDocument("src/main/resources/word/rest-with-spring.docx");
        logger.info("Word document created successfully!");
    }

    // Method to save the Word document to the specified file path
    public void saveDocument(String filePath) {
        try (FileOutputStream fos = new FileOutputStream(Paths.get(filePath).toFile())) {
            this.document.write(fos);
            logger.info("Document saved at: " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving the document", e);
        }
    }

    // Utility method to create a Word document from text content
    public void createWordDocumentFromText(String text) {
        logger.info("Starting Word document creation from text...");
        this.document.createParagraph().createRun().setText(text);
        saveDocument("src/main/resources/word/rest-with-spring-from-text.docx");
        logger.info("Word document created from text successfully!");
    }

    // Utility method to append a single paragraph to the document
    public void appendSingleParagraph(String paragraphText) {
        this.document.createParagraph().createRun().setText(paragraphText);
    }

    // Utility method to clear the current document content
    public void clearDocument() {
        this.document = new XWPFDocument(); // Reset the document
    }

    // Method to add an image to the document
    public void addImageToDocument(String imagePath, String imageDescription) {
        try {
            // Open the image file to add to the Word document
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                logger.log(Level.SEVERE, "Image file does not exist: " + imagePath);
                return;
            }

            // Create a paragraph for the image
            XWPFParagraph paragraph = this.document.createParagraph();
            XWPFRun run = paragraph.createRun();

            // Add the image to the paragraph using addPicture
            try (FileInputStream fis = new FileInputStream(imageFile)) {
                run.addPicture(fis, XWPFDocument.PICTURE_TYPE_PNG, imageFile.getName(), Units.toEMU(200), Units.toEMU(200)); // Resize image to 200x200 EMU units
            }

            // Optionally, add an image description
            run.addBreak();  // Add a line break after the image
            run.setText(imageDescription);

            logger.info("Image added successfully to the document!");
        } catch (IOException | InvalidFormatException e) {
            logger.log(Level.SEVERE, "Error adding image to document", e);
        }
    }

    // Main method to run the document creation process
    public static void main(String[] args) {
        WordDocument wordDocument = new WordDocument();
        wordDocument.createWordDocument();

        // Add an image to the document
        wordDocument.addImageToDocument("src/main/resources/word/logo-leaf.png", "This is a sample image.");

        System.out.println("Image added successfully to the document!");
    }
}
