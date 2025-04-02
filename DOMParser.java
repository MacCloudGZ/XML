import java.io.File;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class DOMParser {
    public static void main(String[] args) {
        try {
            // step 1
            File xmlFile = new File("bookstore.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = (Document) builder.parse(xmlFile);
            // normalize document
            doc.getDocumentElement().normalize();
            // step 2
            Element root = doc.getDocumentElement();
            System.out.println("Root element: " + root.getNodeName());
            // step 3
            NodeList booklist = doc.getElementsByTagName("book");

            for (int i = 0; i < booklist.getLength(); i++) {
                Node book = booklist.item(i);
                if (book.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) book;
                    String category = bookElement.getAttribute("category");
                    String isbn = bookElement.getAttribute("isbn");
                    String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                    String author = bookElement.getElementsByTagName("author").item(0).getTextContent();
                    String price = bookElement.getElementsByTagName("price").item(0).getTextContent();
                    // print
                    System.out.println();
                    System.out.println("ISBN : " + isbn);
                    System.out.println("Title: " + title);
                    System.out.println("Author: " + author);
                    System.out.println("Category: " + category);
                    System.out.println("Price: " + price);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}