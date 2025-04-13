import java.io.File;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

public class learningtask_zabala {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("books.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            NodeList no1 = (NodeList) xPath.evaluate("//book[year = 2008]/title", doc,
                    XPathConstants.NODESET);
            System.out.println("Books Published in 2008:");
            for (int i = 0; i < no1.getLength(); i++) {
                System.out.println(" - " + no1.item(i).getTextContent());
            }
            System.err.println(" ");

            NodeList no2 = (NodeList) xPath.evaluate("//book[genre = 'Programming']/title", doc,
                    XPathConstants.NODESET);
            System.out.println("Book Titles that is under Programming:");
            for (int i = 0; i < no2.getLength(); i++) {
                System.out.println(" - " + no2.item(i).getTextContent());
            }
            System.err.println(" ");
            String no3 = xPath.evaluate("//book[title = 'The Pragmatic Programmer']/price", doc);
            System.err.println("The Price of the Pragmatic Programmer: " + no3);

            System.err.println(" ");
            NodeList no4 = (NodeList) xPath.evaluate("//book[publisher = 'Addison-Wesley']/title", doc, XPathConstants.NODESET);
            System.out.println("Book Titles that is under Addison-Wesley");
            for (int i = 0; i < no4.getLength(); i++) {
                System.out.println(" - " + no4.item(i).getTextContent());
            }
            System.err.println(" ");

            NodeList no5 = (NodeList) xPath.evaluate("//book[genre = 'Software Engineering']/title", doc, XPathConstants.NODESET);
            System.out.println("Book Titles that is under Software Engineering");
            for (int i = 0; i < no5.getLength(); i++) {
                System.out.println(" - " + no5.item(i).getTextContent());
            }

            System.err.println(" ");

            NodeList no6 = (NodeList) xPath.evaluate("//book[year <2000]/title", doc, XPathConstants.NODESET);
            System.out.println("Book Titles that is under 2000");
            for (int i = 0; i < no6.getLength(); i++) {
                System.out.println(" - " + no6.item(i).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}