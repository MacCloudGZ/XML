import java.io.File;
import java.io.IOException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class BSIT2H_Zabala {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("restaurant.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            // 1. Get all available menu items (across all menus)
            System.out.println("Available Menu Items:");
            NodeList no1 = (NodeList) xPath.evaluate("//item[@available='true']/name", doc, XPathConstants.NODESET);
            for (int i = 0; i < no1.getLength(); i++) {
                System.out.println(" - " + no1.item(i).getTextContent());
            }

            // 2. Retrieve all Pizza items and their prices
            System.out.println("\nPizza Items and Prices:");
            NodeList no2 = (NodeList) xPath.evaluate("//item[category='Pizza']", doc, XPathConstants.NODESET);
            for (int i = 0; i < no2.getLength(); i++) {
                Element item = (Element) no2.item(i);
                String name = item.getElementsByTagName("name").item(0).getTextContent();
                String price = item.getElementsByTagName("price").item(0).getTextContent();
                System.out.println(" - " + name + ": $" + price);
            }

            // 3. Get names of all dine-in menu items
            System.out.println("\nDine-in Menu Items:");
            NodeList no3 = (NodeList) xPath.evaluate("//menu[@type='dine-in']/item/name", doc, XPathConstants.NODESET);
            for (int i = 0; i < no3.getLength(); i++) {
                System.out.println(" - " + no3.item(i).getTextContent());
            }

            // 4. List ingredients of the Cheeseburger
            System.out.println("\nIngredients of Cheeseburger:");
            NodeList no4 = (NodeList) xPath.evaluate("//item[name='Cheeseburger']/ingredients/ingredient", doc, XPathConstants.NODESET);
            for (int i = 0; i < no4.getLength(); i++) {
                System.out.println(" - " + no4.item(i).getTextContent());
            }

            // 5. Find the price and currency of Grilled Cheese
            System.out.println("\nPrice and Currency of Grilled Cheese:");
            String no5Price = xPath.evaluate("//item[name='Grilled Cheese']/price", doc);
            String no5Currency = xPath.evaluate("//item[name='Grilled Cheese']/price/@currency", doc);
            System.out.println(" - Price: " + no5Price + " " + no5Currency);

        } catch (IOException | ParserConfigurationException | XPathExpressionException | DOMException | SAXException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}