import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class sample {
    public static void main(String[] args) {
        try {
            //Step one Declaration
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Step two
            Document doc = builder.newDocument();
            
            Element rootElement = doc.createElement("bookstore");
            doc.appendChild(rootElement);

            //Step 4 books creation
            Element book1 = doc.createElement("Book");
            book1.setAttribute("Category", "Fiction");
            book1.setAttribute("ISBN", "OF-103C");
            rootElement.appendChild(book1);

            createElement(doc,book1,"title","Programing");
            createElement(doc,book1,"author","hackin");
            createElement(doc,book1,"price","29.99");
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("bookstore.xml"));

            transformer.transform(source, result);

            System.out.println("XML File Created Successfully");
        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println(e);
        }
    }

    private static void createElement (Document doc, Element parent, String tagName, String textContent){
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parent.appendChild(element);
    }
}