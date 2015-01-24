package xml_comparator_tool;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlParser {
	static Document d1,d2;
	File f1,f2;
	public XmlParser(String path1, String path2){
		f1 = new File(path1);
		f2 = new File(path2);
	}

	public void parseXmls(){
		DocumentBuilder dBuilder;
		try {
			dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			d1 = dBuilder.parse(f1);
			d2 = dBuilder.parse(f2);
			d1.normalizeDocument();
			d2.normalizeDocument();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}		
	}
}
