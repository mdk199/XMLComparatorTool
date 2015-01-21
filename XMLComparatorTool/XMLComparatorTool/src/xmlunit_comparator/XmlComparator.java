package xmlunit_comparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.ElementNameAndAttributeQualifier;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.xml.sax.SAXException;

public class XmlComparator {
	FileReader f1, f2;
	public XmlComparator(String path1, String path2) throws FileNotFoundException {
        f1 = new FileReader(path1);
        f2 = new FileReader(path2);
	}
	
	public void compareXml() throws SAXException, IOException{
    	XMLUnit.setIgnoreComments(true);
    	XMLUnit.setIgnoreWhitespace(true);
    	XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
    	
        Diff diff = new Diff(f1, f2);
        DetailedDiff detDiff = new DetailedDiff(diff);
        detDiff.overrideElementQualifier(new ElementNameAndAttributeQualifier());
        detDiff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
        detDiff.overrideDifferenceListener(new IgnoreSequence());
        detDiff.overrideMatchTracker(new MatchTrackerImpl());
        
        List<?> differences = detDiff.getAllDifferences();

        System.out.println("Similar? " + diff.similar());
        writeDiffToFile(differences);

	}

	private void writeDiffToFile(List<?> differences) throws IOException{
		File file = new File("src/Differences.txt");
		file.createNewFile();
		FileWriter writer = new FileWriter(file);
		for (Object object : differences) {
		    Difference difference = (Difference)object;
		  	writer.write(String.valueOf(difference));
		  	writer.write("\n");
		    writer.write("\n");
		}
		writer.flush();
		writer.close();
	}
}
