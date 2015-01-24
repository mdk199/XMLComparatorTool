package xmlunit_comparator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.xml.sax.SAXException;

public class TestComparator {

	public static void main(String[] args) throws SAXException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String path1, path2;

		System.out.println("Enter the Reference File path: ");
		path1 = br.readLine();
		
		System.out.println("Enter the Migrated File path: ");
		path2 = br.readLine();
		
		XmlComparator comparator = new XmlComparator(path1, path2);
		comparator.compareXml();
	}

}
