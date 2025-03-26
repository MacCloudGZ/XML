import java.io.FileInputStream;
import javax.xml.stream.*;
//import java.io.FileReader;

public class StaXReaderExample2 {
    public static void main(String[] arg) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("example.xml"));
        while(reader.hasNext()) {
            int event = reader.next();
            switch (event) {
                case XMLStreamConstants.START_ELEMENT -> System.out.println("Start Element: " + reader.getLocalName());
                case XMLStreamConstants.CHARACTERS -> System.out.println("Text: " + reader.getText());
                case XMLStreamConstants.END_ELEMENT -> System.out.println("End Element: " + reader.getLocalName());
                default -> {
                }
            }
        }
        reader.close();
    }
}