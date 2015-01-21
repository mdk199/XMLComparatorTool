package xmlunit_comparator;

import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceConstants;
import org.custommonkey.xmlunit.DifferenceListener;
import org.w3c.dom.Node;

public class IgnoreSequence implements DifferenceListener {
	public int differenceFound(Difference difference) {
		if (difference.getId() == DifferenceConstants.CHILD_NODELIST_SEQUENCE_ID ||
			    difference.getId() == DifferenceConstants.ATTR_SEQUENCE_ID) 
			return DifferenceListener.RETURN_IGNORE_DIFFERENCE_NODES_IDENTICAL; 
		else
			return DifferenceListener.RETURN_ACCEPT_DIFFERENCE;
	}
	
	@Override
	public void skippedComparison(Node arg0, Node arg1) {
		// TODO Auto-generated method stub
		
	}
}
