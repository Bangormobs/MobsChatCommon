package uk.co.mobsoc.chat.common;

import java.util.ArrayList;
/**
 * Collection of helpful static functions
 * @author triggerhapp
 *
 */
public class Util {
	/**
	 *  Protocol version that this common library contains - These numbers should increase depending on how breaking any updates are
	 */
	public static final int MajorVersion = 4;
	public static final int MinorVersion = 0;

	/**
	 * Compare two colours. The programatically nearer the palette colour is to the wanted colour, the lower the int.
	 * The lower the int, the more likely this one will be chosen to dither to
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static int compareColours(Colour c1, Colour c2){
		int dist = Math.abs((c1.getRed() - c2.getRed())^2) + Math.abs((c1.getGreen() - c2.getGreen())^2) + Math.abs((c1.getBlue() - c2.getBlue())^2) ;
		return dist;
	}
	
	/**
	 * Choose the entry from colourList that is nearest (according to compareColours) to colour
	 * @param colourList
	 * @param colour
	 * @return
	 */
	public static Colour getClosestColour(ArrayList<Colour> colourList, Colour colour){
		int dist = Integer.MAX_VALUE;
		Colour c = null;
		for(Colour c2 : colourList){
			int newDist = compareColours(colour,c2);
			if(newDist < dist){
				dist = newDist;
				c = c2;
			}
		}
		return c;
	}
	
	/**
	 * Replace all needles in the haystack
	 * @param haystack
	 * @param needle
	 * @param replace
	 * @return a new String where all needles have been replaced
	 */
    public static String replace(
            final String haystack,
            final String needle,
            final String replace
          )
    {
    	if ( needle.equals("") ) {
    		return haystack;
    	}

    	final StringBuffer result = new StringBuffer();
        //startIdx and idxOld delimit various chunks of aInput; these
    	//chunks always end where aOldPattern begins
    	int startIdx = 0;
    	int idxOld = 0;
    	while ((idxOld = haystack.indexOf(needle, startIdx)) >= 0) {
    		//grab a part of aInput which does not include aOldPattern
    		result.append( haystack.substring(startIdx, idxOld) );
    		//add aNewPattern to take place of aOldPattern
    		result.append( replace );

    		//reset the startIdx to just after the current match, to see
    		//if there are any further matches
    		startIdx = idxOld + needle.length();
    	}
    	//the final chunk will go to the end of aInput
    	result.append( haystack.substring(startIdx) );
    	return result.toString();
    }
}
