/* Student Information
* -------------------
* Student Name: Wu, Yifu
* Student Number: 001450544
* Course Code: CS/SE 2XB3
* Lab Section: 02
*
* I attest that the following code being submitted is my own individual
work.
*/

public class Set {												// Create a class called "Set"

	private final String s[] = new String[10];					// Declaration of String array (only can contain 10 elements)

	public Set(String[] s) {									// Constructor
		for (int i = 0; i < s.length; i++) {					// Loop through all the elements in the array, and put them in the set 
			if (s.length == 1 && s[0].equals("")) {				// If we got an empty input
				this.s[0] = null;								// Then the set contains nothing
			} else {											// Otherwise we put elements into set normally
				this.s[i] = s[i];
			}
		}
	}

	public int getCount() {										// Create getCount method
		int count = 0;
		for (int i = 0; i < s.length; i++) {					// Count the number of non-empty elements
			if (s[i] != null) {
				count++;
			}
		}
		return count;											// Return count
	}

	public String toString() {									// Create toString method
		String array = "{";
		for (int i = 0; i < this.getCount(); i++) {				// Put set elements into string in {...,...} form
			if (i + 1 != this.getCount()) {
				array += this.s[i];
				array += ",";

			} else {
				array += this.s[i];
			}
		}
		array += "}";
		return array;											// Return array
	}

	public Set SetUnion(Set s2) {								// Create SetUnion method
		int index = 0;
		String[] a3 = new String[10];							// Create a array to store resulting elements
		for (int i = 0; i < this.getCount(); i++) {
			if (!Exists(a3, this.s[i])) {						// As long as the element is not included in resulting array
				if (index <= 9) {
					a3[index] = this.s[i];
					index++;
				}
			}
		}
		for (int j = 0; j < s2.getCount(); j++) {				// Check elements of the Set 2 in resulting array
			if (!Exists(a3, s2.s[j])) {							// Check duplicate
				if (index <= 9) {
					a3[index] = s2.s[j];
					index++;
				}
			}
		}
		Set s3 = new Set(a3);
		return s3;
	}

	public static boolean Exists(String[] s, String Element) {	// Create a boolean to check duplicate
		int check = 0;
		boolean exist = true;
		for (int i = 0; i < s.length; i++) {
			if (Element.equals(s[i])) {
				check++;
			}
		}
		if (check == 0) {
			exist = false;
		}
		return exist;											// Return true if the element is existed in the given array
	}

	public Set SetIntersection(Set s2) {						// Create Intersection method 
		String[] a3 = new String[10];							// Create an array to store non-duplicate elements
		int index = 0;
		for (int i = 0; i < this.getCount(); i++) {				// Loop through both arrays
			for (int j = 0; j < s2.getCount(); j++) {
				if (this.s[i].equals(s2.s[j])) {				// If the element of first array appears in 
					if (!Exists(a3, this.s[i])) {				// Check if the element was in the resulting array
						a3[index] = this.s[i];					// If not, we put it into the array
						index++;
					}
				}
			}
		}
		Set s3 = new Set(a3);									// Create a new set for resulting array
		return s3;												// Return the set
	}

	public Set SetDifference(Set s2) {							// Create a SetDifference method
		String[] a3 = new String[10];							// Create resulting array
		int index = 0;
		int count = 0;
		for (int i = 0; i < this.getCount(); i++) {				// Loop through both arrays
			for (int j = 0; j < s2.getCount(); j++) {			// If the element of array one is contained in array two
				if (s2.s[j].equals(this.s[i])) {	
					count++;									// Count + 1
				}
			}
			if (count == 0) {									// If it is not contained, means it is the difference of the arrays
				if (!Exists(a3, this.s[i])) {					// Check the duplicate in resutling array
					a3[index] = this.s[i];
					index++;
				}
			}
			count = 0;
		}
		for (int k = 0; k < s2.getCount(); k++) {				// Do the same thing to array two
			for (int l = 0; l < this.getCount(); l++) {
				if (this.s[l].equals(s2.s[k])) {
					count++;
				}
			}
			if (count == 0) {
				if (!Exists(a3, s2.s[k])) {
					a3[index] = s2.s[k];
					index++;
				}
			}
			count = 0;
		}
		Set s3 = new Set(a3);									// Create a set for resulting array
		return s3;												// Return the set
	}

	public String SetProduct(Set s2) {							// Create SetProduct method
		String[] Array = new String[(this.getCount() * s2.getCount())];
		String result = "";
		int index = 0;
		for (int i = 0; i < this.getCount(); i++) {
			for (int j = 0; j < s2.getCount(); j++) {
				Array[index] = "<" + this.s[i] + "," + s2.s[j] + ">";
				if (i + 1 == this.getCount() && j + 1 == s2.getCount()) {
					result += "<" + this.s[i] + "," + s2.s[j] + ">";
				} else {
					result += "<" + this.s[i] + "," + s2.s[j] + ">,";
				}
				index++;
			}
		}
		return result;											// Return a string include all possible pairs from 2 arrays
	}

	public boolean isSubset(Set s2) {							// Create isSubset method
		int count = 0;
		boolean Subset = true;									// Declare a boolean
		for (int i = 0; i < this.getCount(); i++) {				// Loop through both arrays 
			for (int j = 0; j < s2.getCount(); j++) {
				if (this.s[i].equals(s2.s[j])) {				// If the element of first array appeared in the 2nd array
					count++;									// Count+1
				}
			}
			if (count == 0) {									// As long as count is 0, means one of the element of first set is not included
				Subset = false;									// in the 2nd set, we change the boolean to false
			}
			count = 0;											// Reset the counter
		}
		return Subset;											// Return true/false
	}

	public boolean isEqual(Set s2) {							// Create isEqual method						
		boolean isEqual = false;
		if (s2.isSubset(this) && this.isSubset(s2)) {			// Use Subset to check equal set
			isEqual = true;
		}
		return isEqual;
	}

	public static String valueOf(Object obj) {					// Create a method to compare boolean with string
		return (obj == null) ? "null" : obj.toString();
	}

	public int getCardinality() {								// Create getCardinality method
		int count = 0;
		int unique = 0;
		for (int i = 0; i < this.getCount(); i++) {				// Loop through array
			for (int j = 0; j < this.getCount(); j++) {
				if (this.s[j].equals(this.s[i]) && j != i) {	// If the checked element is contained at other index 
					count++;									// Count++
				}
			}
			if (count == 0) {									// If the count remains 0, means the element is unique
				unique++;										// Counter for unique elements increase 1
			}
			count = 0;
		}
		return unique;											// Return unique counter
	}

}
