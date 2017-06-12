package common;

/**
 * Created by PLJAHAS on 2017-06-07.
 */


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class Parser {


    private String parseXmlFile(String tagToRetrieve, String filePath) {
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
                    } catch (NullPointerException r) {

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retrivedElement;
    }


    public String parseOrderXml(String quotationNumber, String tagToRetrive) {
        String filePathBackend1 = "////DE-S-0214369.de.abb.com//Avanade//CQPServices_SANDBOX//App_Data//Serializations//OrderXml";
        String filePathBackend2 = "////DE-S-0214370.de.abb.com//Avanade//CQPServices_SANDBOX//App_Data//Serializations//OrderXml";
        String newFileDirectory = "";
        File[] files = new File(filePathBackend1).listFiles();
        String fileName = "";
        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().contains(quotationNumber) && file.getName().contains("_in_")) {
                    fileName = file.getName();
                    break;
                }
            }
        }
        try {
            newFileDirectory = System.getProperty("user.dir") + "//" + fileName.substring(0, fileName.length() - 3) + "xml";
            FileUtils.copyFile(new File(filePathBackend1 + "//" + fileName), new File(newFileDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fileName == "") {
            files = new File(filePathBackend2).listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().contains(quotationNumber) && file.getName().contains("_in_")) {
                        fileName = file.getName();
                        break;
                    }
                }
            }
            try {
                newFileDirectory = System.getProperty("user.dir") + "//" + fileName.substring(0, fileName.length() - 3) + "xml";
                FileUtils.copyFile(new File(filePathBackend2 + "//" + fileName), new File(newFileDirectory));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String retrivedTag = new Parser().parseXmlFile(tagToRetrive, newFileDirectory);
        try {
            FileUtils.forceDelete(new File(newFileDirectory));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retrivedTag;
    }

}




