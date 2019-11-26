package services;

import exceptions.NotFoundException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class FileValidationService {
    public void validate(File xmlFile, File xsdFile) {
        try {
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlFile));
            //logger.debug("File " + xmlFile + " validated successful. Used scheme - " + xsdFile.getName());
        } catch (IOException e) {
            //logger.error(e);
            System.out.println("Exception: " + e.getMessage());
            //throw new ValidationException("Validation failed " + e.getMessage());
        } catch (org.xml.sax.SAXException e) {
            System.out.println("Exception: " + e.getMessage());
            //logger.debug(xmlFile.getName() + " is NOT valid reason:" + e);
            //throw new ValidationException(xmlFile.getName() + " is NOT valid reason:" + e);
        }
    }

    public File checkFile(String path) throws NotFoundException {
        File file = new File(path);
        if ((file.exists()) && (file.isFile()) &&
                (file.canRead()) && (file.length() > 0))
            return file;
        else{
            throw new NotFoundException("Can't read file: " + path);
        }
    }

    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        }
        return sb.toString();
    }
}
