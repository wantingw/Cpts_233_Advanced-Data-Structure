/*
 *  Assignment: Comparing String Duplicate Algorithms
 *
 *  BruteForceDupDetector: An N^2 "Biggest Hammer" approach to duplicate detection
 *   Duplicates Detector / Filter
 * 
 *  Copyright:
 *   For academic use only under the Creative Commons
 *   Attribution-NonCommercial-NoDerivatives 4.0 International License
 *   http://creativecommons.org/licenses/by-nc-nd/4.0
 */

import java.util.ArrayList;
import java.util.HashMap;

class HashMapDupDetector extends StringDupDetectorBase {

    public HashMapDupDetector() {
        // Constructor - should set your algorithms general metadata

        // Give your algorithm a name to identify it
        algorithm_name = "HashMap O(1) Hotrod!";

        // Describe the key feature(s) of your algorithm
        description = "Uses a whatawhat?";

        // Estimate your algorithm's complexity
        complexity = "I dunno!";
    }

    // The one function that does the duplicates detection for your algorithm.
    //  This function is called by the testing code
    //  It returns a list of duplicate strings found - NOTE: you CANNOT have duplicates in the results themselves!
    @Override
    public ArrayList<String> doDupDetection(ArrayList<String> strings) {
        // These two arrays are stored in the results when your algorithm is done
        // NOTE: The duplicates ArrayList *cannot* have duplicates within itself!
        ArrayList<String> duplicates = new ArrayList<>();   // Lists a set of strings that appear more than once

        HashMap<String, String> map = new HashMap<>();
        
        //traverse through the strings list
        for(int i = 0; i<strings.size(); i++) {
            //wanted to make sure items in map that has been existed && duplicate list doesn't have the 
            //repeated item: we add the new duplicated item to the duplicate list
            if(map.containsKey(strings.get(i))&& !duplicates.contains(strings.get(i))) {
                duplicates.add(strings.get(i));
            }
            //transfer items to the map
            map.put(strings.get(i), null);
            
        }

        return duplicates;
    }
}