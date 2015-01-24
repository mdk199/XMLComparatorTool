package xml_comparator_tool;

public class TestComparator {
	
	public static void main(String [] args){
		String path1 = "src/r1.xml";
		String path2 = "src/e1.xml";
		
		XmlParser parser = new XmlParser(path1, path2);
		XmlComparator comparator = new XmlComparator();
		
		parser.parseXmls();
		comparator.compareXml(XmlParser.d1.getDocumentElement().getChildNodes(), XmlParser.d2.getDocumentElement().getChildNodes());
	}

}
