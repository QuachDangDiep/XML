import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReader {

    public static void main(String[] args) {
        try {
            List<Book> books = new ArrayList<>();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.parse(new File("bookstore.xml"));

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String author = element.getElementsByTagName("author").item(0).getTextContent();
                    int year = Integer.parseInt(element.getElementsByTagName("year").item(0).getTextContent());
                    double price = Double.parseDouble(element.getElementsByTagName("price").item(0).getTextContent());

                    Book book = new Book(title, author, year, price);
                    books.add(book);
                }
            }

            for (Book book : books) {
                System.out.println(book);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
