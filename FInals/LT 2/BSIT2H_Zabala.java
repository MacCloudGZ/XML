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
            NodeList availableItems = (NodeList) xPath.evaluate("//item[@available='true']/name", doc, XPathConstants.NODESET);
            for (int i = 0; i < availableItems.getLength(); i++) {
                System.out.println(" - " + availableItems.item(i).getTextContent());
            }

            // 2. Retrieve all Pizza items and their prices
            System.out.println("\nPizza Items and Prices:");
            NodeList pizzaItems = (NodeList) xPath.evaluate("//item[category='Pizza']", doc, XPathConstants.NODESET);
            for (int i = 0; i < pizzaItems.getLength(); i++) {
                Element item = (Element) pizzaItems.item(i);
                String name = item.getElementsByTagName("name").item(0).getTextContent();
                String price = item.getElementsByTagName("price").item(0).getTextContent();
                System.out.println(" - " + name + ": $" + price);
            }

            // 3. Get names of all dine-in menu items
            System.out.println("\nDine-in Menu Items:");
            NodeList dineInItems = (NodeList) xPath.evaluate("//menu[@type='dine-in']/item/name", doc, XPathConstants.NODESET);
            for (int i = 0; i < dineInItems.getLength(); i++) {
                System.out.println(" - " + dineInItems.item(i).getTextContent());
            }

            // 4. List ingredients of the Cheeseburger
            System.out.println("\nIngredients of Cheeseburger:");
            NodeList cheeseburgerIngredients = (NodeList) xPath.evaluate("//item[name='Cheeseburger']/ingredients/ingredient", doc, XPathConstants.NODESET);
            for (int i = 0; i < cheeseburgerIngredients.getLength(); i++) {
                System.out.println(" - " + cheeseburgerIngredients.item(i).getTextContent());
            }

            // 5. Find the price and currency of Grilled Cheese
            System.out.println("\nPrice and Currency of Grilled Cheese:");
            String grilledCheesePrice = xPath.evaluate("//item[name='Grilled Cheese']/price", doc);
            String grilledCheeseCurrency = xPath.evaluate("//item[name='Grilled Cheese']/price/@currency", doc);
            System.out.println(" - Price: " + grilledCheesePrice + " " + grilledCheeseCurrency);

        } catch (IOException | ParserConfigurationException | XPathExpressionException | DOMException | SAXException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}