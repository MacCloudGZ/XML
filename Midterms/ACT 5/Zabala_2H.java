import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
public class Zabala_2H {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Step two
            Document doc = builder.newDocument();
            
            Element rootElement = doc.createElement("FilipinoRestaurants");
            doc.appendChild(rootElement);

            Element Resto = doc.createElement("Restuarant");
            rootElement.appendChild(Resto);
            //Details
            Element Details = doc.createElement("Details");
            Resto.appendChild(Details);
            createElement(doc, Details, "Name", "MALAYA");
            Element Op = doc.createElement("OperatingHours");
            Details.appendChild(Op);
            createElement(doc, Op, "Days","Mon,Tue,Wed,Thu,Fri,Sat");
            createElement(doc, Op, "OpenTime", "08:00");
            createElement(doc, Op, "closeTime", "21:30");
            Element Local = doc.createElement("Location");
            Details.appendChild(Local);
            createElement(doc, Local, "Address","Santo Thomas");
            createElement(doc, Local, "Street","Zone 9");
            createElement(doc, Local, "City","Nagaraya");
            createElement(doc, Local, "Region","Mani");
            createElement(doc, Local, "Zipcode","920");
            createElement(doc, Local, "Country","PH");
            Element Contact = doc.createElement("Contact");
            Details.appendChild(Contact);
            createElement(doc, Contact, "Days","Mon,Tue,Wed,Thu,Fri,Sat");
            createElement(doc, Contact, "OpenTime", "08:00");
            createElement(doc, Contact, "closeTime", "21:30");
            //Menu
            Element Menu = doc.createElement("Menu");
            rootElement.appendChild(Menu);
            /*
             * 
             * SPACING PURPOSES
             * 
             */
            Element Dish1 = doc.createElement("Dish");
            Dish1.setAttribute("Type", "Dessert");
            createElement(doc,Dish1, "Name", "Chicharon Malungay");
            createElement(doc,Dish1, "Description", "Malungay na chicharon");
            createElement(doc,Dish1, "Price", "500");
            createElement(doc,Dish1, "Currency", "Peso");
            
            Element Dish2 = doc.createElement("Dish");
            Menu.appendChild(Dish2);
            createElement(doc,Dish2, "Name", "Chiraron na Plato");
            createElement(doc,Dish2, "Description", "pritong plato");
            createElement(doc,Dish2, "Price", "250");
            createElement(doc,Dish2, "Currency", "Peso");
            Element Dish3 = doc.createElement("Dish");
            Menu.appendChild(Dish3);
            createElement(doc,Dish3, "Name", "Kalderong Natutung");
            createElement(doc,Dish3, "Description", "Burned Pan");
            createElement(doc,Dish3, "Price", "75");
            createElement(doc,Dish3, "Currency", "Peso");
            for(int i = 0; i <= 2; i++){

            }
            /*
             * SPACING PURPOSES
             */
            //Save Data here below
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Zabala_2H.xml"));

            transformer.transform(source, result);

            System.out.println("XML File Created Successfully");
        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println("e");
        }
    }
    private static void createElement (Document doc, Element parent, String tagName, String textContent){
        Element element = doc.createElement(tagName);
        element.appendChild(doc.createTextNode(textContent));
        parent.appendChild(element);
    }
}
