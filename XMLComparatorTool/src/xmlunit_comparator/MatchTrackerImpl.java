package xmlunit_comparator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.MatchTracker;
import org.custommonkey.xmlunit.NodeDetail;
import org.w3c.dom.Node;

class MatchTrackerImpl implements MatchTracker {
	SingletonWriterForMatchTracker sm = SingletonWriterForMatchTracker.getSingletonFileandWriter();
	File file = sm.getFile();
	FileWriter writer = sm.getFileWriter();
	
    public void matchFound(Difference difference) {
        if (difference != null) {
            NodeDetail controlNode = difference.getControlNodeDetail();
            NodeDetail testNode = difference.getTestNodeDetail();
 
            String controlNodeValue = printNode(controlNode.getNode());
            String testNodeValue = printNode(testNode.getNode());
			
            if (controlNodeValue != null) {
                try {
                	writer.append("**********************\n");
					writer.append("Control Node: " + String.valueOf(controlNodeValue)+"\n");
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if (testNodeValue != null) {
                try {
					writer.append("Test Node: " + String.valueOf(testNodeValue)+"\n");
                	writer.append("**********************\n");
                	writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            }
        }
    }
 
    private static String printNode(Node node) {
        if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
            StringWriter sw = new StringWriter();
            try {
                Transformer t = TransformerFactory.newInstance().newTransformer();
                t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                t.transform(new DOMSource(node), new StreamResult(sw));
            } catch (TransformerException te) {
                System.out.println("nodeToString Transformer Exception");
            }
            return sw.toString();
 
        }
        return null;
    }
}