package com.microteam.methods;

import com.microteam.word.WordDocument;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordMethods {
    private static final Logger LOGGER = Logger.getLogger(WordMethods.class.getName());

    public static void main(String[] args) {
        WordDocument wordDocument = new WordDocument();

        try {
            LOGGER.info("Starting Word document creation process...");

            // Add an image to the Word document first
            String imagePath = "src/main/resources/word/logo-leaf.png"; // Path to the image
            String imageDescription = "This is a sample image.";
            wordDocument.addImageToDocument(imagePath, imageDescription);  // Add image to document

            LOGGER.info("Image added successfully to the document!");
            System.out.println("Image added successfully to the document!");

            // Generate the Word document by appending paragraphs
            wordDocument.createWordDocument();

            LOGGER.info("Word document created successfully!");
            System.out.println("Word document created successfully!");

            // Save the document after the image has been added and paragraphs are appended
            wordDocument.saveDocument("src/main/resources/word/rest-with-image-and-paragraphs.docx");

            LOGGER.info("Document saved successfully!");
            System.out.println("Document saved successfully!");

        } catch (Exception e) {
            // Handle exceptions with detailed error logging
            LOGGER.log(Level.SEVERE, "Error creating the Word document: " + e.getMessage(), e);
            System.err.println("Error creating the Word document. Check logs for details.");
        } finally {
            LOGGER.info("Process completed.");
        }
    }
}
