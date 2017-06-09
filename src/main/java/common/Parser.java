package common;

/**
 * Created by PLJAHAS on 2017-06-07.
 */


import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class Parser {


    public String parseXmlFile(String tagToRetrieve, String filePath) {
        String retrivedElement = null;
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("*");
            for (int item = 0; item < nList.getLength(); item++) {
                Node nNode = nList.item(item);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    try {
                        retrivedElement = eElement.getElementsByTagName(tagToRetrieve).item(0).getTextContent();
                        break;
                    }catch (NullPointerException r){

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrivedElement;
    }
}




