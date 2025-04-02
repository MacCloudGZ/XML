import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;


class Book {
    String title;
    String genre;
    public Book(String etitle, String egenre) {
        this.title = etitle;
        this.genre = egenre;
    }
    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
}

public class Zabala_2H {
    public static void main(String[] args) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("books.xml"));
            //Step 2: Read the XML document
            List<Book> books = new ArrayList<>();
            List<String> Genre = new ArrayList<>();//for genre storage
            String title = "", genre ="";
            String elementName = "";
             while(reader.hasNext()) {
                int event = reader.next();
                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        elementName = reader.getLocalName();
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String text = reader.getText();
                        if(!text.isEmpty()){
                            if("title".equals(elementName)){
                                title = text;
                            } else if("genre".equals(elementName)){
                                genre = text;
                                if(!Genre.contains(genre)){
                                    Genre.add(genre);
                                }
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if("book".equals(reader.getLocalName())){
                            books.add(new Book(title, genre));
                        }
                        elementName = "";
                        break;
                }
            }
            reader.close();
            //Step 4: Display the results
            for(String genres : Genre){
                System.out.println(genres);
                for(Book book : books){
                    if(genres.equals(book.getGenre())){
                        System.out.println(" - " +book.getTitle());
                    }
                }
            }
            
        }catch(Exception e){
            System.err.println(e);
        }
    }
}