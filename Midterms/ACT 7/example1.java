import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

class Book {
    String title;
    String author;
    int year;
    
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Year: " + year;
    }
}

public class example1 {
     public static void main(String[] args) {
         try {
            //Step 1: Create a new XMLInputFactory 
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("example.xml"));
            //Step 2: Read the XML document
            List<Book> books = new ArrayList<>();
            String title = "", author ="";
            int year = 0, BookCount = 0;
            String elementName = "";
            //Step 3: Iterate over the XML document
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
                            } else if("author".equals(elementName)){
                                author = text;
                            } else if("year".equals(elementName)){
                                year = Integer.parseInt(text);
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if("book".equals(reader.getLocalName())){
                            books.add(new Book(title, author, year));
                            BookCount++;
                        }
                        elementName = "";
                        break;
                    default:
                        break;
                }
            }
            reader.close();
            //Step 4: Display the results
            int outputContents = 0;
            System.out.println("Books Published after 2000");
            for(Book book : books){
                if(book.year > 2000){
                    System.out.println(book);
                    outputContents++;
                }
            }
            System.err.println("Total Books: " + BookCount);
            System.err.println("Books Published after 2000: " + outputContents);
         } catch (Exception e) {
            e.printStackTrace();
        }
     }  
}
