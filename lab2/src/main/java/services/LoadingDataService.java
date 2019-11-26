package services;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import exceptions.InvalidFileException;
import models.DataBaseObject;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LoadingDataService {

    private final FileValidationService fileValidationService = new FileValidationService();
    private final XmlMapper xmlMapper = new XmlMapper();

    private static final String XSD_FILE_NAME = "src/main/resources/out.xsd";

    public DataBaseObject loadDateFromXml(File xmlDataFile) throws InvalidFileException {
        File xsdFile = new File(XSD_FILE_NAME);
        fileValidationService.validate(xmlDataFile, xsdFile);
        //logger.info(xmlDataFile.getName() + " validated successful. Used scheme: " + xsdFile.getName());
        DataBaseObject dataBaseObject = null;
        try {
            String xml = fileValidationService.inputStreamToString(new FileInputStream(xmlDataFile));
            dataBaseObject = xmlMapper.readValue(xml, DataBaseObject.class);
        } catch (IOException e) {
            //logger.error(e.getMessage());
            throw new InvalidFileException("Error " + e.getMessage());
        }
        return dataBaseObject;
    }
}
