import java.io.File;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

public class XPathExample {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("books.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            // Extract All Book Titles
            NodeList titles = (NodeList) xPath.evaluate("//title", doc, XPathConstants.NODESET);
            System.out.println("Book Titles:");
            for (int i = 0; i < titles.getLength(); i++) {
                System.out.println(" - " + titles.item(i).getTextContent());
            }

            // Get author of a specific book
            String bookId = "2";
            String targetFile = "author";
            String output = xPath.evaluate("//book[@id='" + bookId + "']/" + targetFile + "", doc);
            System.out.println("Author of Book with ID " + bookId + ": " + output);

        } catch (ParserConfigurationException | XPathExpressionException | DOMException e) {
            e.printStackTrace();
        }
    }
}
