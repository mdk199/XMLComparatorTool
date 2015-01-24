package xml_comparator_tool;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlComparator {
	
	private boolean compareNode(Node search_node, Node expected_node){
		if (search_node.getNodeName() == expected_node.getNodeName() 
			&& search_node.getNodeType() == expected_node.getNodeType()
			&& search_node.getTextContent().equals(expected_node.getTextContent()))
			return true;
		return false;
	}
	
	private boolean compareAttributes(NamedNodeMap nnm1, NamedNodeMap nnm2){
		boolean flag = false;
		boolean oflag = true;
		int i,j;
		for(i=0; i < nnm1.getLength(); i++){
			Node search = nnm1.item(i);
			for(j=0; j < nnm2.getLength(); j++){
				Node expected = nnm2.item(j);
				if(compareNode(search, expected)){
					flag = true;
					break;
				}
			}
			if(!flag){
				System.out.println("Attribute Name:"+search.getNodeName()+" Attribute value: "+search.getNodeValue()+"  in file1 not matching with file2");
			}
			oflag = oflag && flag;
		}
		return oflag;
	}
	
	public void compareXml(NodeList n1, NodeList n2){
		boolean flag = false;
		
		int i,j;
		for(i=0; i<n1.getLength(); i++){
			Node search = n1.item(i);
			if(search.getNodeType() == Node.ELEMENT_NODE){
				for(j=0; j<n2.getLength(); j++){
					Node expected = n2.item(j);
					if(expected.getNodeType() == Node.ELEMENT_NODE){
						if(compareNode(search, expected)){
							if (search.hasAttributes() && expected.hasAttributes() 
									&& compareAttributes(search.getAttributes(), expected.getAttributes())){
								compareXml(search.getChildNodes(), expected.getChildNodes());
							}else if(search.hasChildNodes() && expected.hasChildNodes()){
								compareXml(search.getChildNodes(), expected.getChildNodes());
							}
							flag = true;
							break;
						}
					}
				}
				if(!flag){
					System.out.println("Node Name:"+search.getNodeName()+" Node value: "+search.getTextContent()+"  in file1 not matching with file 2");
				}
			}
		}
	}
}
