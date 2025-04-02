package Midterms.Lab;

import java.io.File;
import java.util.Scanner;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Zabala2H {
    public static Scanner sc = new Scanner(System.in);

    public static void clear() {
        if (System.getProperty("os.name").contains("Windows")) {
            // For Windows
            System.out.print("\033c");
        } else {
            // For Linux/macOS
            System.out.print("\033[H\033[2J"); // ANSI escape code for clearing
            System.out.flush();
        }
    }

    public static void Display(int numberDisplay) {
        try {
            File xmlFile = new File("FilipinoRestaurant.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = (Document) builder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            System.out.println("Root element: " + root.getNodeName());

            NodeList main = doc.getElementsByTagName("FilipinoRestaurants");
            for (int i = 0; i < main.getLength(); i++) {
                Node node = main.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    switch (numberDisplay) {
                        case 0:
                            System.out.println("\t\t[ 1 ] Information");
                            System.out.println("\t\t[ 2 ] Menus");
                            System.out.println("\t\t[ 3 ] Exit");
                            break;
                        case 1:
                            System.out.println("\t\tInformation:");
                            NodeList resto = doc.getElementsByTagName("Restuarant");
                            for (int j = 0; j < resto.getLength(); j++) {
                                Node restoNode = resto.item(j);
                                if (restoNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element restoElement = (Element) restoNode;
                                    String restoName = restoElement.getElementsByTagName("Name").item(0)
                                            .getTextContent();
                                    String days = restoElement.getElementsByTagName("Days").item(0).getTextContent();
                                    String openTime = restoElement.getElementsByTagName("OpenTime").item(0)
                                            .getTextContent();
                                    String closeTime = restoElement.getElementsByTagName("closeTime").item(0)
                                            .getTextContent();
                                    String address = restoElement.getElementsByTagName("Address").item(0)
                                            .getTextContent();
                                    String street = restoElement.getElementsByTagName("Street").item(0)
                                            .getTextContent();
                                    String city = restoElement.getElementsByTagName("City").item(0).getTextContent();
                                    String region = restoElement.getElementsByTagName("Region").item(0)
                                            .getTextContent();
                                    String zipcode = restoElement.getElementsByTagName("Zipcode").item(0)
                                            .getTextContent();
                                    String country = restoElement.getElementsByTagName("Country").item(0)
                                            .getTextContent();
                                    System.out.println("\t\t Restaurant: " + restoName);
                                    System.out.println("\t\t\tDays: " + days);
                                    System.out.println("\t\t\tOpen Time: " + openTime);
                                    System.out.println("\t\t\tClose Time: " + closeTime);
                                    System.out.println("\t\t\tAddress: " + address);
                                    System.out.println("\t\t\tStreet: " + street);
                                    System.out.println("\t\t\tCity: " + city);
                                    System.out.println("\t\t\tRegion: " + region);
                                    System.out.println("\t\t\tZipcode: " + zipcode);
                                    System.out.println("\t\t\tCountry: " + country);
                                }
                            }
                            System.out.println("Press Enter To Go Back...");
                            sc.nextLine();
                            break;
                        case 2:
                            NodeList dishes = doc.getElementsByTagName("Dish");
                            System.out.println("\t\tMenu:");
                            for (int j = 0; j < dishes.getLength(); j++) {
                                Node dishNode = dishes.item(j);
                                if (dishNode.getNodeType() == Node.ELEMENT_NODE) {
                                    Element dishElement = (Element) dishNode;
                                    String dishName = dishElement.getElementsByTagName("Name").item(0).getTextContent();
                                    String description = dishElement.getElementsByTagName("Description").item(0)
                                            .getTextContent();
                                    String price = dishElement.getElementsByTagName("Price").item(0).getTextContent();
                                    System.out.println("\t\t Dish: " + dishName);
                                    System.out.println("\t\t\tDescription: " + description);
                                    System.out.println("\t\t\tPrice: " + price);
                                }
                            }
                            System.out.println("Press Enter To Go Back...");
                            sc.nextLine();
                            break;
                        default:
                            System.err.println("No Display");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        boolean isContinue = true;
        int locale = 0;
        while (locale != 3) {
            clear();
            switch (locale) {
                case 0:
                    Display(0);
                    break;
                case 1:
                    Display(1);
                    break;
                case 2:
                    Display(2);
                    break;
                default:
                    Display(909);
                    break;
            }
            if (isContinue) {
                System.out.print("Enter choice: ");
                int laba = sc.nextInt();
                if (laba >= 1 && laba <= 3) {
                    locale = laba; // Update locale based on user input
                } else {
                    locale = 0;
                }
                sc.nextLine();
                isContinue = false;
            } else {
                locale = 0;
                isContinue = true;
            }
        }
    }
}