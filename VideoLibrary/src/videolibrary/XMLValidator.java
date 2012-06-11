/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.io.*;
import javax.swing.text.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author AjkoST
 */
public class XMLValidator {
    
    public static boolean checkXml(String xml) throws SAXException {
        
        try {
            // define the type of schema - we use W3C:
            String schemaLang = "http://www.w3.org/2001/XMLSchema";

            // get validation driver:
            SchemaFactory factory = SchemaFactory.newInstance(schemaLang);


            // create schema by reading it from an XSD file:
            Schema schema = factory.newSchema(new StreamSource(new File("videoXmlSchema.xsd")));

            Validator validator = schema.newValidator();

            // at last perform validation:
            StringReader reader = new StringReader(xml);

            validator.validate(new StreamSource(reader));

            return true;
        } catch (SAXException ex) {

            return false;
        } catch (Exception ex) {

            return false;
        }
        
        
        
        
        
        
        
    }
}
