package task17;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class LibraryRunner {
  public static void main(String[] args) {
    boolean validateIsPassed = validateXML("src/com/tms/lesson17/task1/books.xsd",
            "src/com/tms/lesson17/task1/books.xml");

    if (validateIsPassed) {
      System.out.println("Валидация XML документа пройдена успешно =)");
    } else {
      System.out.println("Валидация XML завершилась неудачаей :( ");
    }
  }

  public static boolean validateXML(String xsdPath, String xmlPath) {
    try {
      SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema schema = factory.newSchema(new File(xsdPath));
      Validator validator = schema.newValidator();
      validator.validate(new StreamSource(new File(xmlPath)));

    } catch (IOException | SAXException e) {
      System.out.println("Произошла ошибка валидации!");
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }
}
