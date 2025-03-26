import java.io.FileInputStream;
import javax.xml.stream.*;
//import java.io.FileReader;

public class StaXReaderExample {
    public static void main(String[] arg) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("example.xml"));
        while(reader.hasNext()) {
            int event = reader.next();
            if(event == XMLStreamConstants.START_ELEMENT) {
                System.out.println("Start Element: " + reader.getLocalName());
            }else if(event == XMLStreamConstants.CHARACTERS) {
                System.out.println("Text: " + reader.getText());
        
            }else if(event == XMLStreamConstants.END_ELEMENT) {
                System.out.println("End Element: " + reader.getLocalName());
            }
        }
        reader.close();
    }
}