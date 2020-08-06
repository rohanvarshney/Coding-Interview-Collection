import java.util.*;
import java.io.*;

public class InterviewQuestions {


	public static void main(String args[]) {
		//Perform function testing here.
		testGenerate3DigitPermutations();

	}

	public static class Range {
		int start;
		int end;
		public Range(int start, int end) {
			this.start = start;
			this.end = end;
		}
		public String toString() {
			return "Range: [" + start + ", " + end + "]";
		}
	}

	public static class Coordinate {
		int x;
		int y;
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return "Coordinate: [" + x + ", " + y + "]";
		}
	}


	/*
	Implement a method to perform basic string compression using the counts
	of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the
	"compressed" string would not become smaller than the original string, your method should return
	the original string. You can assume the string has only uppercase and lowercase letters (a - z).
	*/
	public static String compressString(String str) {
		String answer = "";

		boolean only1s = true;
		char c = str.charAt(0);
		int count = 1;
		for(int x = 1; x < str.length(); x++) {
			char letterHere = str.charAt(x);
			if (letterHere == c) {
				count++;
			} else {
				answer += c + "" + count;
				if (count > 1)
					only1s = false;
				c = letterHere;
				count = 1;
			}

		}
		if (count > 1)
			only1s = false;
		
		if (only1s)
			return str;

		answer += c + "" + count;
		return answer;


	}
	public static void testCompressString() {
		System.out.println(compressString("aabcccccaaa"));
		System.out.println(compressString("abcdefghijkkkkkkklmnooooopqrrrr"));
		System.out.println(compressString("aaaaaaaa"));
		System.out.println(compressString("abcdefghijkl"));
		System.out.println(compressString("zefghij"));
		System.out.println(compressString("aab"));
	}


	/*
	Take an input and output the number of consecutive numbers in a row, 
	or what you would say if you were to read it out loud 
	ex input: 111222289  (three ones four twos one eight one nine ) = 31421819  
	*/
	public static String readNumber(String num) {
		String answer = "";

		char c = num.charAt(0);
		int count = 1;
		for (int x = 1; x < num.length(); x++) {
			char here = num.charAt(x);
			if (here == c) {
				count++;
			}
			else {
				answer = answer + count + c;
				c = here;
				count = 1;
			}
		}

		answer = answer + count + c;
		return answer;
	}
	public static void testReadNumber() {
		System.out.println(readNumber("233313411134323"));
		System.out.println(readNumber("111222289"));
		System.out.println(readNumber("1234567890000"));
	}


	/*
	A generic Binary search algorithm.
	*/
	public static int binarySearch(int[] nums, int target) {
		int start = 0;
		int end = nums.length;
		while (start < end) {
			int mid = (start+end)/2;
			if (nums[mid] > target) {
				end = mid - 1;
			} else if (nums[mid] < target) {
				start = mid + 1;
			} else {
				return nums[mid];
			}
		}
		return -1;
	}
	public static void testBinarySearch() {
		System.out.println(binarySearch(new int[]{1,2,3,4,5,6,7,8,9}, 2));
		System.out.println(binarySearch(new int[]{1,3,5,7,9,10,13,15,19,99}, 99));
		System.out.println(binarySearch(new int[]{1,2,3,4,5,6,7,8,9}, 10));
		System.out.println(binarySearch(new int[]{1,2,3,4,5,6,7,8,9}, 9));
	}


	/*
	Given an array of integers that has a peak integer 
	(left neighbor < peak > right neighbor), find the peak integer. 
	5,5,5,5,5,6,6,6,7,7,8,9,9,***50****,20,20,20,10,0.

	This is essentially a modified binary search where the desired result is the maximum integer.
	*/
	public static int findPeak(int[] nums) {
		int start = 0;
		int end = nums.length;
		while (start <= end) {
			int mid = (start+end)/2;

			if (mid == nums.length - 1 && nums[mid] > nums[mid-1]) //end of list
				return nums[mid];
			if (mid == 0 && nums[mid] > nums[mid+1]) //beginning of list
				return nums[mid];
			if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) //bigger than element before and after
				return nums[mid];

			if (nums[mid+1] > nums[mid]) {
				start = mid;
			} else if (nums[mid+1] < nums[mid]) {
				end = mid;
			} else if (start == end) { //completed search
				return nums[mid];
			}
		}
		return -1;
	}
	public static void testFindPeak() {
		System.out.println(findPeak(new int[]{1,2,3,4,5,6,7,8,9}));
		System.out.println(findPeak(new int[]{1,3,5,7,9,10,13,15,19,99}));
		System.out.println(findPeak(new int[]{1,2,3,4,5,6,7,8,9,8,7,6,5,4,3,2}));
		System.out.println(findPeak(new int[]{1,2,3,4,7,6,5,4,3,2}));
		System.out.println(findPeak(new int[]{1,2,3,4,3,2}));
		System.out.println(findPeak(new int[]{0,2,1,0}));
		System.out.println(findPeak(new int[]{1,2,3,4,5,6,7,8,9, 481, 98, 97}));
		
	}



	/*
	Fibonacci algorithm performed with recursion.
	*/
	public static int fibonacci(int n) {
		if (n < 1)
			throw new IllegalArgumentException("Incorrect input");
		return fibonacciRecursion(n);
	}

	public static int fibonacciRecursion(int n) {
		if (n == 1 || n == 2)
			return 1;
		return fibonacciRecursion(n-1) + fibonacciRecursion(n-2);
	}
	public static void testFibonacci() {
		System.out.println(fibonacci(0));
		System.out.println(fibonacci(-1));
		System.out.println(fibonacci(1));
		System.out.println(fibonacci(2));
		System.out.println(fibonacci(3));
		System.out.println(fibonacci(4));
		System.out.println(fibonacci(5));
		System.out.println(fibonacci(6));
		System.out.println(fibonacci(7));
		
	}


	/*
	Print 3^n hash symbols with recursion.
	*/
	public static void printHashSymbols(int n) {
		printHashSymbolsHelper(n);
		System.out.println("\n\n");
	}

	public static void printHashSymbolsHelper(int n) {
		if (n < 0)
			return;
		printHashSymbolsHelper(n-1);
		printHashSymbolsHelper(n-1);
		printHashSymbolsHelper(n-1);
		if (n == 0) {
			System.out.print("#");
		}
	}
	public static void testPrintHashSymbols() {
		printHashSymbols(1);
		printHashSymbols(2);
		printHashSymbols(3);
		printHashSymbols(4);
	}


	/*
	Sum of first n evens using recursion, starting from 2.
	*/
	public static int sumOfEvens(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Incorrect input.");
		}
		return sumOfEvensRecursion(n);
	}
	public static int sumOfEvensRecursion(int n) {
		if (n == 0)
			return 0;

		return (n*2) + sumOfEvensRecursion(n-1);
	}
	public static void testSumOfEvens() {		
		System.out.println(sumOfEvens(1));
		System.out.println(sumOfEvens(2));
		System.out.println(sumOfEvens(3));
		System.out.println(sumOfEvens(4));
		System.out.println(sumOfEvens(5));
		System.out.println(sumOfEvens(6));
		System.out.println(sumOfEvens(7));
		System.out.println(sumOfEvens(8));
		System.out.println(sumOfEvens(10));
	}




	/*
	Given an array of integers where every integer occurs three 
	times except for one integer, which only occurs once, find and 
	return the non-duplicated integer.

	For example, given [6, 1, 3, 3, 3, 6, 6], return 1. 
	Given [13, 19, 13, 13], return 19.
	*/
	public static int findUniqueOccurrenceOfThree(int[] arr) {
		HashSet<Integer> visitedOnlyOnce = new HashSet<>();
		HashSet<Integer> visited = new HashSet<>();

		for (int element: arr) {
			if (visited.contains(element)) {
				if (visitedOnlyOnce.contains(element)) {
					visitedOnlyOnce.remove(element);
				} 
			} else {
				visited.add(element);
				visitedOnlyOnce.add(element);
			}
		}

		return visitedOnlyOnce.iterator().next(); //Returns first and only element
	}
	public static void testFindUniqueOccurrenceOfThree() {
		System.out.println(findUniqueOccurrenceOfThree(new int[]{0,0,0,0,1,1,2,3,3,4,4,4,5,5,5,5,5,6,6,6,6,7,7,7,8,8,9,9,20,20}));
		System.out.println(findUniqueOccurrenceOfThree(new int[]{-10,-10,-1,-1,-1,-1,-1,-1,-1,0}));
		System.out.println(findUniqueOccurrenceOfThree(new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0}));
		System.out.println(findUniqueOccurrenceOfThree(new int[]{999,999,10,10,10,10,10,9,9,8,8,-9978, 7, 7, 7, 7, 7}));
		System.out.println(findUniqueOccurrenceOfThree(new int[]{999}));
	}

	/*
	Given a list of sorted numbers (can be both negative or positive), return the list of numbers squared in sorted order.
	def square_numbers(nums):
	print(square_numbers([-5, -3, -1, 0, 1, 4, 5]))
	[0, 1, 1, 9, 16, 25, 25]
	Solve this problem in O(n) time.

	We know the original list is sorted, so the maximums occur on the ends, which would end up being on the back of the list.
	*/
	public static List<Integer> sortSquaredNumbers(int[] arr) {
		LinkedList<Integer> answer = new LinkedList<Integer>();
		int start = 0;
		int end = arr.length - 1;

		double squaredStart = 0;
		double squaredEnd = 0;
		while (start < end) {
			squaredStart = Math.pow(arr[start], 2);
			squaredEnd = Math.pow(arr[end], 2);
			if (squaredStart > squaredEnd) {
				answer.addFirst((int)squaredStart);
				start++;
			} else {
				answer.addFirst((int)squaredEnd);
				end--;
			}
		}
		answer.addFirst((int)Math.pow(arr[start], 2));
		return answer;
	}
	public static void testSortSquaredNumbers() {
		System.out.println(sortSquaredNumbers(new int[]{-99}));
		System.out.println(sortSquaredNumbers(new int[]{-10,0,10}));
		System.out.println(sortSquaredNumbers(new int[]{-5, -3, -1, 0, 1, 4, 5}));
		System.out.println(sortSquaredNumbers(new int[]{-5, -4,-3,-2, -1, 0, 1, 4, 5}));
		System.out.println(sortSquaredNumbers(new int[]{-5, -3, -1, 0, 1, 4,4,4,4,4,4, 5}));
		System.out.println(sortSquaredNumbers(new int[]{-99,-20,-10,-5,-5,-5, -3, -1, 1, 1,1,2,4, 5}));
	}


	/*
	Given a singly linked list and an integer k, remove the 
	kth last element from the list. k is guaranteed to be smaller 
	than the length of the list. The list is very long, so making 
	more than one pass is prohibitively expensive. 
	Do this in constant space and in one pass.

	Answer: Two pointer. First pointer at element zero, have the
	second pointer be k elements in front. Move both simltaneously
	forward until the second pointer hits null. Remove the element
	the first pointer is looking at.
	*/




	/*
	Given a list of words, and an arbitrary alphabetical order, verify that the words are in order of the alphabetical order.

	Example:
	Input:
	words = ["abcd", "efgh"], order="zyxwvutsrqponmlkjihgfedcba"

	Output: False
	Explanation: 'e' comes before 'a' so 'efgh' should come before 'abcd'

	Example 2:
	Input:
	words = ["zyx", "zyxw", "zyxwy"],
	order="zyxwvutsrqponmlkjihgfedcba"

	Output: True
	*/
	public static boolean evaluateLexographicalOrder(String[] words, String alphabet) {
		HashMap<Character, Integer> alphabetValue = new HashMap<>();
		for (int x = 0; x < alphabet.length(); x++) {
			char c = alphabet.charAt(x);
			alphabetValue.put(c, x);
		}
		//System.out.println(alphabetValue);

		for (String word : words) {
			boolean val = evaluateLexographicalOrderOfWord(word, alphabetValue);
			if (!val) return false;
		}
		return true;
	}
	public static boolean evaluateLexographicalOrderOfWord(String word, HashMap<Character, Integer> alphabet) {
		int prevVal = alphabet.get(word.charAt(0));
		for (int x = 1; x < word.length(); x++) {
			char c = word.charAt(x);
			int value = alphabet.get(c);
			if (value < prevVal) {
				return false;
			}
			prevVal = value;
		}
		return true;
	}
	public static void testEvaluateWordOrder() {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String reversedAlphabet = new StringBuilder(alphabet).reverse().toString();
		String[] words = {"abc", "def", "xyz"};
		String[] words2 = {"zy", "zy", "cba"};
		String[] words3 = {"zyxwvu", "zy", "fedcba"};
		System.out.println(evaluateLexographicalOrder(words, alphabet));
		System.out.println(evaluateLexographicalOrder(words, reversedAlphabet));
		System.out.println(evaluateLexographicalOrder(words2, reversedAlphabet));
		System.out.println(evaluateLexographicalOrder(words3, reversedAlphabet));
	}



	/*
	You have a function rand7() that generates a random integer from 1 to 7. 
	Use it to write a function rand5() that generates a random integer from 1 to 5.
	*/
	public static int rand7() {
		return (int) Math.random() * 6 + 1;
	}
	public static int rand5() {

		int sum; 
		do 
		{
			sum = 5 * rand5() + rand5(); 
		} while (sum > 20); 
		return sum % 7; 
	}


	/*
	This problem was asked by Coursera. Given a 2D board of characters and a word, find if the word exists in the grid.
	The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are 
	those horizontally or vertically neighboring. The same letter cell may not be used more than once.
	For example, given the following board:
	[
	  ['A','B','C','E'],
	  ['S','F','C','S'],
	  ['A','D','E','E']
	]
	exists(board, "ABCCED") returns true, exists(board, "SEE") returns true, exists(board, "ABCB") returns false.
	*/
	public static boolean evaluateCrossword(String word, char[][] board) {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				boolean result = evaluateCrosswordSpot(x, y, word, 0, 'N', board) 
					|| evaluateCrosswordSpot(x, y, word, 0, 'S', board) 
					|| evaluateCrosswordSpot(x, y, word, 0, 'E', board) 
					|| evaluateCrosswordSpot(x, y, word, 0, 'W', board);
				if (result == true) return true;
			}
		}
		return false;
	}
	public static boolean evaluateCrosswordSpot(int x, int y, String word, int len, char direction, char[][] board) {
		if (len == word.length()) return true;
		if (x < 0 || y < 1 || x >= board.length || y >= board[0].length) return false;

		boolean works = false;
		if (board[x][y] == word.charAt(len)) {
			len += 1;
		}
		if (direction == 'N') {
			works = evaluateCrosswordSpot(x-1, y, word, len, direction, board);
		}
		if (direction == 'S') {
			works = evaluateCrosswordSpot(x+1, y, word, len, direction, board);
		}
		if (direction == 'E') {
			works = evaluateCrosswordSpot(x, y+1, word, len, direction, board);
		}
		if (direction == 'W') {
			works = evaluateCrosswordSpot(x, y-1, word, len, direction, board);
		}
		return works;

	}

	public static void testEvaluateCrossword() {
		char[][] board = {
			{'A','B','C','E'},
			{'S','F','C','S'},
			{'A','D','E','E'}
						};
		System.out.println(evaluateCrossword("DEE", board));
		System.out.println(evaluateCrossword("ESE", board));
		System.out.println(evaluateCrossword("ECA", board));
		System.out.println(evaluateCrossword("ADEEA", board));

	}



	/*
	This problem was asked by Palantir.
	In academia, the h-index is a metric used to calculate the impact of a researcher's papers. 
	It is calculated as follows:
	A researcher has index h if at least h of her N papers have h citations each. 
	If there are multiple h satisfying this formula, the maximum is chosen.
	For example, suppose N = 5, and the respective citations of each paper are [4, 3, 0, 1, 5]. 
	Then the h-index would be 3, since the researcher has 3 papers with at least 3 citations.
	Given a list of paper citations of a researcher, calculate their h-index.
	*/
	public static int hIndex(int[] citations) {
		//Count buckets for occurrences of each paper.
		int[] summedBuckets = new int[citations.length + 1];
		for (int citation: citations) {
			if (citation >= citations.length) {
				summedBuckets[citations.length]++;
			} else {
				summedBuckets[citation] += 1;
			}
		}

		//Go backwards, sum papers up as you go.
		int sum = 0;
		for (int x = citations.length; x > 0; x--) {
			sum += summedBuckets[x];
			if (sum >= x) {
				return x;
			}
		}
		return 0;
	}

	public static void testHIndex() {
		System.out.println(hIndex(new int[]{8,6,3,2,1,5}));
		System.out.println(hIndex(new int[]{4, 3, 0, 1,5}));
	}





	/*
	Hi, here's your problem today. This problem was recently asked by Apple:

	In many spreadsheet applications, the columns are marked with letters. 
	From the 1st to the 26th column the letters are A to Z. Then starting from the 27th column it uses AA, AB, ..., ZZ, AAA, etc.

	Given a number n, find the n-th column name.

	def column_name(n):
	  # Fill this in.

	print column_name(26)
	print column_name(27)
	print column_name(28)
	# Z
	# AA
	# AB
	*/
	public static String nthColumnName(int n) {
		String s = "";

		while (n > 26) {
			int dividend = n / 26;
			char c = (char)('A' + (dividend - 1));
			s = s + c;
			n = n - (dividend * 26);
		}
		s = s + (char)('A' + (n - 1));
		return s;
	}
	public static void testnthColumnName() {
		System.out.println(nthColumnName(3));
		System.out.println(nthColumnName(25));
		System.out.println(nthColumnName(26));
		System.out.println(nthColumnName(27));
		System.out.println(nthColumnName(28));
		System.out.println(nthColumnName(100));
		System.out.println(nthColumnName(105));
		System.out.println(nthColumnName(106));
		System.out.println(nthColumnName(107));
	}


	/*
	Given a number of integers, combine them so it would create the largest number.

	Example:
	Input:  [17, 7, 2, 45, 72]
	Output:  77245217
	def largestNum(nums):
	  # Fill this in.

	print largestNum([17, 7, 2, 45, 72])
	# 77245217
	*/
	public static String largestNumOfNums(List<String> nums) {
		StringBuilder sb = new StringBuilder();
		Collections.sort(nums, new Comparator<String>(){ 
	  		@Override
	        public int compare(String X, String Y) { 
	        	String XY=X + Y; 
	        	String YX=Y + X; 
	        	return XY.compareTo(YX) > 0 ? -1:1; 
	    	} 
	    });  
	    Iterator it = nums.iterator();
	    while(it.hasNext()) {
	        sb.append(it.next()); 
	    }
	    return sb.toString();
	}
	public static void testLargestNumOfNums() {
		List<String> l = Arrays.asList("1", "2", "31", "33");
		System.out.println(largestNumOfNums(l));
	}




	/*
	Hi, here's your problem today. This problem was recently asked by Microsoft:

	Given an array of heights, determine whether the array forms a "mountain" pattern. A mountain pattern goes up and then down.

	Like
	  /\
	 /  \
	/    \
	class Solution(object):
	  def validMountainArray(self, arr):
	    # Fill this in.

	print(Solution().validMountainArray([1, 2, 3, 2, 1]))
	# True

	print(Solution().validMountainArray([1, 2, 3]))
	# False
	*/
	public static boolean isMountainArray(int[] nums) {
		boolean hasReachedAPeak = false;
		int peak = Integer.MIN_VALUE;
		int previous = nums[0];
		for (int x = 1; x < nums.length; x++) {
			if (nums[x] >= previous) {
				if (hasReachedAPeak) {
					return false;
				}
				previous = nums[x];
				peak = nums[x];
			} else if (nums[x] < previous) {
				hasReachedAPeak = true;
				previous = nums[x];
			}
		}
		return true;

	}
	public static void testIsMountainArray() {
		System.out.println(isMountainArray(new int[]{1,2,3,4,3,2,1}));
		System.out.println(isMountainArray(new int[]{1,2,3,4,3,2,4}));

		System.out.println(isMountainArray(new int[]{1,2,3,10,3,2,1}));
		System.out.println(isMountainArray(new int[]{1,2,3,2,3,2,1}));

		System.out.println(isMountainArray(new int[]{1,2,3,9,9,9,9,100,1}));
		System.out.println(isMountainArray(new int[]{1,100,3,9,9,9,9,100,1}));

		System.out.println(isMountainArray(new int[]{1,100,99,98,91,3,2,1}));
		System.out.println(isMountainArray(new int[]{1,100,99,98,91,100,2,1}));
	}




	/*
	Hi, here's your problem today. 
	This problem was recently asked by Microsoft:

	An IP Address is in the format of A.B.C.D, where A, B, C, D are all integers between 0 to 255.

	Given a string of numbers, return the possible IP addresses you can make with that string by splitting into 4 parts of A, B, C, D.

	Keep in mind that integers can't start with a 0! (Except for 0)

	Example:
	Input: 1592551013
	Output: ['159.255.101.3', '159.255.10.13']
	def ip_addresses(s, ip_parts=[]):
	  # Fill this in.

	print ip_addresses('1592551013')
	# ['159.255.101.3', '159.255.10.13']
	*/
	public static HashSet<String> makeIPAddresses(String num) {
		HashSet<String> possible = new HashSet<String>();
		String base = num + "";
		for (int x = 0; x < base.length(); x++) {
			for (int y = 0; y < base.length()+1; y++) {
				for (int z = 0; z < base.length() + 2; z++) {
					String ip = base;
					ip = ip.substring(0, x) + "." + ip.substring(x);
					ip = ip.substring(0, y) + "." + ip.substring(y);
					ip = ip.substring(0, z) + "." + ip.substring(z);
					boolean valid = evaluateIPAddress(ip);
					if (valid) possible.add(ip);
				}
			}
		}
		return possible;
	}
	public static boolean evaluateIPAddress(String ip) {
		String[] parts = ip.split("\\.");

		if (parts.length != 4) {
			return false;
		}
		for (String part : parts) {
			if (!isNumeric(part)) return false;
			if (part == "") return false;
			float num = Float.parseFloat(part);
			if (num > 255 || num < 0) return false;
			if (part.startsWith("0") && !part.equals("0")) return false;
		}
		return true;
	}
	public static boolean isNumeric(final String str) {
		if (str == null || str.length() == 0) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
	public static void testMakeIPAddresses() {
		System.out.println(makeIPAddresses("25525511135"));
		System.out.println(makeIPAddresses("1592551013"));
		System.out.println(makeIPAddresses("10000083782"));
		System.out.println(makeIPAddresses("9999999"));
		System.out.println(makeIPAddresses("10010010010"));
	}



	/*
	Hi, here's your problem today. This problem was recently asked by Twitter:

	Given a sorted list with duplicates, and a target number n, 
	find the range in which the number exists 
	(represented as a tuple (low, high), both inclusive. 
	If the number does not exist in the list, return (-1, -1)).

	Here's some examples and some starter code.

	def find_num(nums, target):
	  # Fill this in.

	print(find_num([1, 1, 3, 5, 7], 1))
	# (0, 1)

	print(find_num([1, 2, 3, 4], 5))
	# (-1, -1)
	*/
	public static Range findRangeInDuplicates(int[] nums, int target) {
		int start = 0;
		int end = nums.length;
		int index = 0;
		while (start < end) {
			int mid = (start+end)/2;
			if (nums[mid] > target) {
				end = mid - 1;
			} else if (nums[mid] < target) {
				start = mid + 1;
			} else {
				index = mid;
				break;
			}
		}
		Range ret = new Range(-1, -1);
		if (start == end && nums[start] != target) {
			return ret;
		}


		int left = findLeftBound(nums, index, target);
		int right = findRightBound(nums, index, target);
		ret = new Range(left, right);
		if (nums[index-1] != target && nums[index+1] != target) ret = new Range(index, index+1);
		return ret;
	}
	public static int findLeftBound(int[] nums, int l, int target) {
		int start = 0;
		int end = l;
		int index = 0;
		while (start < end) {
			int mid = (start+end)/2;
			if (nums[mid] < target) {
				start = mid + 1;
			} else if (nums[mid] == target) {
				end = mid - 1;
			}
		}
		return nums[start] == target ? start : end;
	}
	public static int findRightBound(int[] nums, int r, int target) {
		int start = r;
		int end = nums.length;
		int index = 0;
		while (start < end) {
			int mid = (start+end)/2;
			if (nums[mid] > target) {
				end = mid - 1;
			} else if (nums[mid] == target) {
				start = mid + 1;
			}
		}
		return nums[start] == target ? start : end;
	}
	public static void testFindRangeInDuplicates() {
		System.out.println(findRangeInDuplicates(new int[]{1, 3, 5, 7, 9, 10, 10, 10, 12}, 10));
		System.out.println(findRangeInDuplicates(new int[]{1, 1, 1, 1, 1, 1, 10, 20, 30, 40}, 1));
		System.out.println(findRangeInDuplicates(new int[]{1, 1, 1, 1, 1, 1, 10, 10, 10, 10, 20, 30, 30, 40}, 30));
		System.out.println(findRangeInDuplicates(new int[]{1, 1, 10, 20, 30, 40}, 20));
	}









	/*
	Hi, here's your problem today. This problem was recently asked by Amazon:

	Given a 2d n x m matrix where each cell has a certain amount of change on the floor, your goal is to start from the 
	top left corner mat[0][0] and end in the bottom right corner mat[n - 1][m - 1] with the most amount of change. 
	You can only move either right or down.

	Here's some starter code:

	def max_change(mat):
	  # Fill this in.

	mat = [
	    [0, 3, 0, 2],
	    [1, 2, 3, 3],
	    [6, 0, 3, 2]
	]

	print(max_change(mat))
	# 13
	*/
	public static int maxChange(int[][] floor) {
		return maxChangeHelper(floor, 0, 0, 0);
	}
	public static int maxChangeHelper(int[][] floor, int x, int y, int sum) {
		if (x == floor.length - 1 && y == floor[0].length - 1) {
			return sum + floor[x][y];
		}
		if (x >= floor.length || y >= floor[0].length) {
			return 0;
		}

		sum += floor[x][y];
		return Math.max(maxChangeHelper(floor, x + 1, y, sum), maxChangeHelper(floor, x, y+1, sum));
	}
	public static void testMaxChange() {
		int[][] board = {
			{0, 3, 0, 2},
	    	{1, 2, 3, 3},
	    	{6, 0, 3, 2}
		};
		System.out.println(maxChange(board));
	}






	/*
	Good morning! Here's your coding interview problem for today.

	This problem was asked by IBM.

	Given a string with repeated characters, rearrange the string so that no two adjacent characters are the same. 
	If this is not possible, return None.

	For example, given "aaabbc", you could return "ababac". Given "aaab", return None.
	*/
	public static String nonAdjacentString(String original) {
		int iter = 0;
		int ahead = 1;
		String s = original + "";

		while (iter < s.length() && ahead < s.length()) {
			if (s.charAt(iter) == s.charAt(ahead)) {
				int finder = ahead;
				while (s.charAt(ahead) == s.charAt(finder)) {
					if (finder == original.length() -1 ) return "None";
					finder++;
				}
				s = swapChar(s, finder, ahead);
			}
			iter++;
			ahead++;
		}
		return s;
	}
	public static String swapChar(String str, int i, int j) 
    { 
        char ch[] = str.toCharArray(); 
        char temp = ch[i]; 
        ch[i] = ch[j]; 
        ch[j] = temp; 

        String s = new String(ch);
        return s;
    } 
    public static void testNonAdjacentString() {
    	System.out.println(nonAdjacentString("aaabbc"));
    	System.out.println(nonAdjacentString("aaab"));
    	System.out.println(nonAdjacentString("aaaaabcdefg"));
    }






    /*
    Hi, here's your problem today. This problem was recently asked by Facebook:
	You are given an array of integers. Return the smallest positive integer that is not present in the array. The array may contain duplicate entries.
	For example, the input [3, 4, -1, 1] should return 2 because it is the smallest positive integer that doesn't exist in the array.
	Your solution should run in linear time and use constant space.
	Here's your starting point:
	def first_missing_positive(nums):
	  # Fill this in.
	print first_missing_positive([3, 4, -1, 1])
	# 2
    */
    //This solution does not use constant space, but uses O(n) space.
    public static int firstMissingPositive(int[] nums) {
    	HashSet<Integer> set = new HashSet<>();
    	for (int num: nums) {
    		set.add(num);
    	}
    	for (int x = 1; x <= set.size() + 1; x++) {
    		if (!set.contains(x)) return x;
    	}
    	return 0;


    }
    public static void testFirstMissingPositive() {
    	System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
    	System.out.println(firstMissingPositive(new int[]{3, 4, 2, 1}));
    	System.out.println(firstMissingPositive(new int[]{0, 1, 2, 3, 6, 7, 8, 9, -100}));
    }

    /*
    Hi, here's your problem today. This problem was recently asked by LinkedIn:
	Given a non-empty array where each element represents a digit of a non-negative integer, add one to the integer. 
	The most significant digit is at the front of the array and each element in the array contains only one digit. 
	Furthermore, the integer does not have leading zeros, except in the case of the number '0'. 

	Example:
	Input: [2,3,4]
	Output: [2,3,5]
	class Solution():
	  def plusOne(self, digits):
	    # Fill this in.

	num = [2, 9, 9]
	print(Solution().plusOne(num))
	# [3, 0, 0]
    */
    public static int[] arrayPlusOne(int[] num) {
    	int carry = 1;
    	for (int x = num.length - 1; x >= 0; x--) {
    		int sum = num[x] + carry;
    		if (sum > 9) {
    			carry = sum/10;
    			sum = sum%10;
    		} else {
    			carry = 0;
    		}
    		num[x] = sum;
    	}
    	if (carry != 0) {
    		int[] newNum = new int[num.length + 1];
    		newNum[0] = carry;
    		for (int x = 0; x < num.length; x++) {
    			newNum[x+1] = num[x];
    		}
    		num = newNum;
    	}
    	return num;
    }
    public static void testArrayPlusOne() {
    	System.out.println(Arrays.toString(arrayPlusOne(new int[]{1, 2})));
    	System.out.println(Arrays.toString(arrayPlusOne(new int[]{1, 9})));
    	System.out.println(Arrays.toString(arrayPlusOne(new int[]{8, 9})));
    	System.out.println(Arrays.toString(arrayPlusOne(new int[]{1, 9, 9})));
    	System.out.println(Arrays.toString(arrayPlusOne(new int[]{2, 0, 1})));
    	System.out.println(Arrays.toString(arrayPlusOne(new int[]{9})));
    	System.out.println(Arrays.toString(arrayPlusOne(new int[]{9, 9})));
    	System.out.println(Arrays.toString(arrayPlusOne(new int[]{9, 9 ,9})));
    }




    /*
    Determine whether there exists a one-to-one character mapping from one string s1 to another s2.
	For example, given s1 = abc and s2 = bcd, return true since we can map a to b, b to c, and c to d.
	Given s1 = foo and s2 = bar, return false since the o cannot map to two characters.
    */
    public static boolean characterMappingExists(String a, String b) {
    	if (b.length() != a.length()) { //make String a shorter
    		return false;
    	}
    	HashMap<Character, Character> dict = new HashMap<>();
    	for (int x = 0; x < a.length(); x++) {
    		char aa = a.charAt(x);
    		char bb = b.charAt(x);
    		if (dict.containsKey(aa)) {
    			if (dict.get(aa) != bb) {
    				return false;
    			}
    		} else {
    			dict.put(aa, bb);
    		}
    	}
    	return true;
    }
    public static void testCharacterMappingExists() {
    	System.out.println(characterMappingExists("abc", "bcd"));
    	System.out.println(characterMappingExists("foo", "bar"));
    	System.out.println(characterMappingExists("for", "bar"));
    	System.out.println(characterMappingExists("aaaaaauy", "bbbbbbuy"));
    }


    /*
	Good morning! Here's your coding interview problem for today.
	This problem was asked by Google.
	You are in an infinite 2D grid where you can move in any of the 8 directions:
	 (x,y) to
	    (x+1, y),
	    (x - 1, y),
	    (x, y+1),
	    (x, y-1),
	    (x-1, y-1),
	    (x+1,y+1),
	    (x-1,y+1),
	    (x+1,y-1)
	You are given a sequence of points and the order in which you need to cover the points. 
	Give the minimum number of steps in which you can achieve it. 
	You start from the first point.
	Example:
	Input: [(0, 0), (1, 1), (1, 2)]
	Output: 2
	It takes 1 step to move from (0, 0) to (1, 1). It takes one more step to move from (1, 1) to (1, 2).
	*/
	public static int stepsToMove(List<Coordinate> points) {
		int totalDistance = 0;
		for (int x = 1; x < points.size(); x++) {
			Coordinate a = points.get(x);
			Coordinate b = points.get(x-1);
			int steps = numberOfSteps(a, b);
			totalDistance += steps;
		}
		return totalDistance;

	}
	public static int numberOfSteps(Coordinate a, Coordinate b) {
		int xDiff = Math.abs(a.x - b.x);
		int yDiff = Math.abs(a.y - b.y);
		int diagonalSteps = Math.min(xDiff, yDiff);
		int xSteps = xDiff - diagonalSteps;
		int ySteps = yDiff - diagonalSteps;
		return diagonalSteps + xSteps + ySteps;
	}
	public static void testStepsToMove() {
		List<Coordinate> list = new ArrayList<>();
		list.add(new Coordinate(0, 0));
		list.add(new Coordinate(1, 1));
		list.add(new Coordinate(1, 2));
		System.out.println(stepsToMove(list));

		list.add(new Coordinate(0, 0));
		System.out.println(stepsToMove(list));
		list.add(new Coordinate(1, 1));
		list.add(new Coordinate(7, 10));
		System.out.println(stepsToMove(list));
	}



	/*
	Hi, here's your problem today. This problem was recently asked by Twitter:

	Given a string with only ( and ), find the minimum number of characters to add or subtract to fix the string such that the brackets are balanced.

	Example:
	Input: '(()()'
	Output: 1
	Explanation:

	The fixed string could either be ()() by deleting the first bracket, 
	or (()()) by adding a bracket. 
	These are not the only ways of fixing the string, 
	there are many other ways by adding it in different positions!


	Here's some code to start with:

	def fix_brackets(s):
	  # Fill this in.

	print fix_brackets('(()()')
	# 1
	*/
	public static int disparateBracketCount(String b) {
		int count = 0;
		Stack<Character> stack = new Stack<>();
		for (int x = 0; x < b.length(); x++) {
			char c = b.charAt(x);
			if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				if (stack.empty()) {
					count++;
				} else if (stack.peek() == '(') {
					stack.pop();
				} else {
					count++;
				}
			}
		}
		while (!stack.empty()) {
			stack.pop();
			count++;
		}
		return count;
	}
	public static void testDisparateBracketCount() {
		System.out.println(disparateBracketCount("(()()"));
		System.out.println(disparateBracketCount("((()()"));
		System.out.println(disparateBracketCount("(()())"));
		System.out.println(disparateBracketCount("("));
		System.out.println(disparateBracketCount(")"));
		System.out.println(disparateBracketCount("()())())"));
	}





	/*
	You like skiing a lot. In order to get maximum speed and fun, you like to go downhill every time. 
	You have a 2D grid representing a Lime ski resort. 
	Every number in the grid represents the elevation at that coordinate. 
	The following rules apply:

	You can only move horizontally or vertically in the grid (no diagonal) 
	You cannot go beyond the boundaries. 
	You can only move to a cell with lower elevation.***
	You can start at any point in the grid
	You get 1 point of fun when skiing from one cell to another cell in the grid. 
	Getting a lift from the cable car at any time will reset the fun. 

	Find the maximum possible fun you can get at this resort
	Example:

	Input: Ski Resot

	[[1,  2,  3, 4,  5],
	[16, 17, 18, 19, 6],
	[15, 24, 25, 20, 7],
	[14, 23, 22, 21, 8],
	[13, 12, 11, 10, 9]]

	rec(i, j) = 1 + max[rec(i-1,j), rec(i+1,j), rec(i, j+1), rec(i, j-1)]

	maximum possible fun at this resort: 24
	*/
	public static int maxFunSkiing(int[][] resort) {
		int max = 0;
		int[][] funPossible = new int[resort.length][resort[0].length];

		for (int x = 0; x < funPossible.length; x++) {
			for (int y = 0; y < funPossible[0].length; y++) {
				funPossible[x][y] = Integer.MIN_VALUE;
			}
		}

		for (int x = 0; x < resort.length; x++) {
			for (int y = 0; y < resort[0].length; y++) {
				int maxFunPossible = maxFunAtPoint(resort, x, y, funPossible) - 1;
				if (maxFunPossible > max) max = maxFunPossible;
			}
		}
		return max;

	}
	public static int maxFunAtPoint(int[][] resort, int x, int y, int[][] record) {
		if (x < 0 || y < 0 || x >= resort.length || y >= resort[0].length) {
			return 0;
		}
		if (record[x][y] != Integer.MIN_VALUE) return record[x][y];

		int north = 0, south = 0, east = 0, west = 0;

		if (x - 1 >= 0 && resort[x-1][y] < resort[x][y]) {
			//System.out.println("North");
			north = maxFunAtPoint(resort, x-1, y, record);
		}

		if (x + 1 < resort.length && resort[x+1][y] < resort[x][y]) {
			//System.out.println("South");
			south = maxFunAtPoint(resort, x+1, y, record);
		}

		if (y +  1 < resort[0].length && resort[x][y+1] < resort[x][y]) {
			//System.out.println("East");
			east = maxFunAtPoint(resort, x, y+1, record);
		}

		if (y - 1 >= 0 && resort[x][y-1] < resort[x][y]) {
			//System.out.println("West");
			west = maxFunAtPoint(resort, x, y-1, record);
		}

		//System.out.println("N:"+north+" E:"+east+" W:"+west+" S:"+south);
		int maxFunPossible = 1 + Math.max(Math.max(north, south), Math.max(east, west));
		record[x][y] = maxFunPossible;
		return maxFunPossible;
	}
	public static void testMaxFunSkiing() {
		int[][] skiResort = new int[][] {
			{1,  2,  3, 4,  5},
			{16, 17, 18, 19, 6},
			{15, 24, 25, 20, 7},
			{14, 23, 22, 21, 8},
			{13, 12, 11, 10, 9}
		};
		System.out.println(maxFunSkiing(skiResort));
	}



	public static void wordCounterLyft() {
		List<String> lines = new ArrayList<String>();
		Scanner in = new Scanner(System.in);
		while (in.hasNextLine()) {
			String command = in.nextLine();
		    if(command.equals("")) {
		        break;
		    } else {
		    	lines.add(command);
		    }
		}
		System.out.println("\n");
		wordCountLines(lines);
	}
	public static void wordCountLines(List<String> lines) {
		TreeMap<String, Integer> count = new TreeMap<>();
		for (String line: lines) {
			String[] words = line.split(" ");
			for (int x = 0; x < words.length; x++) {
				String word = words[x];
				if (word == "" || word == " ") continue;

				if (count.containsKey(word)) {
					count.put(word, count.get(word) + 1);
				} else {
					count.put(word, 1);
				}
			}
		}
		printTreeMap(count);
	}
	public static void printTreeMap(TreeMap<String, Integer> tm) {
		for (String key: tm.keySet()) {
			System.out.println(key + " " + tm.get(key));
		}
	}


	/*
	Hi, here's your problem today. This problem was recently asked by Google:

	There are n people lined up, and each have a height represented as an integer. 
	A murder has happened right in front of them, 
	and only people who are taller than everyone in front of them are able to see what has happened. 
	How many witnesses are there?

	Example:
	Input: [3, 6, 3, 4, 1]  
	Output: 3
	Explanation: Only [6, 4, 1] were able to see in front of them.
	 #
	 #
	 # #
	####
	####
	#####
	36341                                 x (murder scene)
	Here's your starting point:

	def witnesses(heights):
	  # Fill this in.

	print witnesses([3, 6, 3, 4, 1])
	# 3
	*/
	public static int numberOfWitnesses(int[] heights) {
		int count = 0;
		int maxHeight = Integer.MIN_VALUE;
		for (int x = heights.length-1; x >= 0; x--) {
			if (heights[x] > maxHeight) {
				count++;
				maxHeight = heights[x];
			}
		}
		return count;
	}
	public static void testNumberOfWitnesses() {
		System.out.println(numberOfWitnesses(new int[]{3, 6, 3, 4, 1}));
	}




	/*
	Hi, here's your problem today. This problem was recently asked by Facebook:

	Given a sequence of numbers, find the longest sequence that contains only 2 unique numbers.

	Example:
	Input: [1, 3, 5, 3, 1, 3, 1, 5]
	Output: 4
	The longest sequence that contains just 2 unique numbers is [3, 1, 3, 1]

	Here's the solution signature:

	def findSequence(seq):
	  # Fill this in.

	print findSequence([1, 3, 5, 3, 1, 3, 1, 5])
	# 4
	*/
	public static int longestSequenceOf2UniqueNums(int[] nums) {
		int maxLength = 0;
		HashMap<Integer, Integer> count = new HashMap<>();
		count.put(nums[0], 1);
		int back = 0, front = 1;
		while (front < nums.length) {
			int number = nums[front];
			//put in hashmap
			if (count.containsKey(number)) {
				count.put(number, count.get(number) + 1);
			} else {
				count.put(number, 1);
			}
			//evaluate: if hashmap has more than 2 keys, remove from back until 2 keys
			//if has 2 keys, return length
			if (count.keySet().size() > 2) {

				while (count.keySet().size() != 2) {
					//remove from back in hashmap
					int backNumber = nums[back];
					if (count.containsKey(backNumber)) {
						if (count.get(backNumber) == 1) {
							count.remove(backNumber);
						} else {
							count.put(backNumber, count.get(backNumber) - 1);
						}
					}
					back++;
				}

			} else if (count.keySet().size() == 2) {
				int length = front - back;
				if (length > maxLength) maxLength = length;
			}
			front++;
		}
		return maxLength + 1;

	}
	public static void testLongestSequenceOf2UniqueNums() {
		System.out.println(longestSequenceOf2UniqueNums(new int[]{1, 3, 5, 3, 1, 3, 1, 5}));
		System.out.println(longestSequenceOf2UniqueNums(new int[]{1, 1, 1, 1, 1, 3, 1, 5}));
		System.out.println(longestSequenceOf2UniqueNums(new int[]{1, 2, 3, 4, 5, 6, 7, 7}));
		System.out.println(longestSequenceOf2UniqueNums(new int[]{1, 1, 3, 4, 5, 6, 7, 7}));
		System.out.println(longestSequenceOf2UniqueNums(new int[]{1, 2, 3, 4, 5, 6, 7, 7, 1, 2, 3, 4, 5, 6, 7, 7}));	
	}


	/*
	Hi, here's your problem today. This problem was recently asked by Amazon:

	You are given a string s, and an integer k. Return the length of the longest substring in s that contains at most k distinct characters.

	For instance, given the string:
	aabcdefff and k = 3, then the longest substring with 3 distinct characters would be defff. The answer should be 5.

	Here's a starting point:

	def longest_substring_with_k_distinct_characters(s, k):
	  # Fill this in.

	print longest_substring_with_k_distinct_characters('aabcdefff', 3)
	# 5 (because 'defff' has length 5 with 3 characters)

	Good morning! Here's your coding interview problem for today.
	This problem was asked by Amazon.
	Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.
	For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
	*/
	//If we want to do with Strings, just change HashMap to <Character, Integer> and
	//iterate though a char[] array, converted from string. Logic holds same.
	public static int longestSequenceOfKUniqueNums(int[] nums, int k) {
		int maxLength = 0;
		HashMap<Integer, Integer> count = new HashMap<>();
		count.put(nums[0], 1);
		int back = 0, front = 1;
		while (front < nums.length) {
			int number = nums[front];
			//put in hashmap
			if (count.containsKey(number)) {
				count.put(number, count.get(number) + 1);
			} else {
				count.put(number, 1);
			}
			//evaluate: if hashmap has more than 2 keys, remove from back until 2 keys
			//if has 2 keys, return length
			if (count.keySet().size() > k) {

				while (count.keySet().size() != k) {
					//remove from back in hashmap
					int backNumber = nums[back];
					if (count.containsKey(backNumber)) {
						if (count.get(backNumber) == 1) {
							count.remove(backNumber);
						} else {
							count.put(backNumber, count.get(backNumber) - 1);
						}
					}
					back++;
				}

			} else if (count.keySet().size() <= k) {
				int length = front - back;
				if (length > maxLength) maxLength = length;
			} 
			front++;
		}
		return maxLength + 1;

	}
	public static void testLongestSequenceOfKUniqueNums() {
		System.out.println(longestSequenceOfKUniqueNums(new int[]{1, 3, 5, 3, 1, 3, 1, 5}, 3));
		System.out.println(longestSequenceOfKUniqueNums(new int[]{1, 3, 5}, 7));
		System.out.println(longestSequenceOfKUniqueNums(new int[]{1, 1, 1, 1, 1, 3, 1, 5}, 4));
		System.out.println(longestSequenceOfKUniqueNums(new int[]{1, 2, 3, 4, 5, 6, 7, 7}, 3));
		System.out.println(longestSequenceOfKUniqueNums(new int[]{1, 1, 3, 4, 5, 6, 7, 7}, 2));
		System.out.println(longestSequenceOfKUniqueNums(new int[]{1, 2, 3, 4, 5, 6, 7, 7, 1, 2, 3, 4, 5, 6, 7, 7}, 2));	
	}


	/*
	This problem was asked by Amazon.
	Given a matrix of 1s and 0s, return the number of "islands" in the matrix. 
	A 1 represents land and 0 represents water, 
	so an island is a group of 1s that are neighboring whose perimeter is surrounded by water.
	For example, this matrix has 4 islands.
	1 0 0 0 0
	0 0 1 1 0
	0 1 1 0 0
	0 0 0 0 0
	1 1 0 0 1
	1 1 0 0 1

	*/
	public static int numberOfIslands(int[][] matrix) {
		int count = 0;
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[0].length; y++) {
				if (matrix[x][y] == 1) {
					fillIsland(matrix, x, y);
					count++;
				}
			}
		}
		return count;
	}
	public static void fillIsland(int[][] matrix, int x, int y) {
		if (x < 0 || y < 0 || x == matrix.length || y == matrix[0].length) return;
		if (matrix[x][y] == 0) return;
		if (matrix[x][y] == 1) {
			matrix[x][y] = 0;
		}

		fillIsland(matrix, x-1, y);
		fillIsland(matrix, x+1, y);
		fillIsland(matrix, x, y-1);
		fillIsland(matrix, x, y+1);
	}
	public static void testNumberOfIslands() {
		int[][] ocean = new int[][]{
			{1,0,0,0,0},
			{0,0,1,1,0},
			{0,1,1,0,0},
			{0,0,0,0,0},
			{1,1,0,0,1},
			{1,1,0,0,1},
		};
		System.out.println(numberOfIslands(ocean));
		int[][] ocean2 = new int[][]{
			{1,1,0,0,0},
			{0,1,1,1,0},
			{0,1,1,0,0},
			{0,0,0,0,0},
			{1,1,0,0,1},
			{1,1,0,0,1},
		};
		System.out.println(numberOfIslands(ocean2));
	}

	/*
	This problem was asked by Google.
	Given a word W and a string S, find all starting indices in S which are anagrams of W.
	For example, given that W is "ab", and S is "abxaba", return 0, 3, and 4.
	*/
	public static List<Integer> indicesOfAnagram(String w, String s) {
		List<Integer> answer = new ArrayList<>();
		int substringLength = w.length();
		HashMap<Character, Integer> countOfW = new HashMap<>();
		for (int x = 0; x < w.length(); x++) {
			if (countOfW.containsKey(w.charAt(x))) {
				countOfW.put(w.charAt(x), countOfW.get(w.charAt(x) + 1));
			} else {
				countOfW.put(w.charAt(x), 1);
			}
		}

		HashMap<Character, Integer> count = new HashMap<>();

		for (int x = 0; x < s.length(); x++) {
			char add = s.charAt(x);
			if (count.containsKey(add)) {
				count.put(add, count.get(add) + 1);
			} else {
				count.put(add, 1);
			}

			if (x > substringLength-1) {
				char remove = s.charAt(x - substringLength);
				if (count.containsKey(remove)) {
					count.put(remove, count.get(remove) - 1);
					if (count.get(remove) == 0) {
						count.remove(remove);
					}
				} else {
					count.put(remove, 1);
				}
			}

			if (count.equals(countOfW)) {
				answer.add(x - 1);
			}
		}

		return answer;
	}
	public static void testIndicesOfAnagram() {
		System.out.println(indicesOfAnagram("ab", "abxaba"));
		System.out.println(indicesOfAnagram("abc", "cbaxxxxbac"));
		System.out.println(indicesOfAnagram("abc", "cbacxxxxcbac"));
		System.out.println(indicesOfAnagram("abc", "abcabcabc"));
	}



	/*
	This problem was asked by Lyft.
	Given a list of integers and a number K, return which contiguous elements of the list sum to K.
	For example, if the list is [1, 2, 3, 4, 5] and K is 9, then it should return [2, 3, 4].

	Given a list of integers S and a target number k, write a function that returns a subset of S that adds up to k. 
	If such a subset cannot be made, then return null.
	*/
	//This solution works for only positive numbers.
	public static LinkedList<Integer> contiguousPositiveSum(int k, int[] nums) {
		LinkedList<Integer> answer = new LinkedList<Integer>();
		int currSum = 0;

		int index = 0;
		while (index < nums.length) {
			System.out.println("Index: " + index + ". Sum: " + currSum);
			if (currSum < k) {
				int add = nums[index];
				answer.addLast(add);
				currSum += add;
				index++;
			} else if (currSum > k) {
				int rem = answer.removeFirst();
				currSum -= rem;
			}
			if (currSum == k) {
				return answer;
			}
		}

		return null;
	}
	public static void testContiguousSum() {
		System.out.println(contiguousPositiveSum(9, new int[]{1, 2, 3, 4, 5}));
		System.out.println(contiguousPositiveSum(10, new int[]{1, 8, 3, 1, 5, 1}));
	}



	/*This problem was asked by Microsoft.
	Given a number in the form of a list of digits, return all possible permutations.
	For example, given [1,2,3], return [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]].
	*/
	//Only works for 3 digit numbers.
	public static HashSet<List<Integer>> generate3DigitPermutations(List<Integer> list) {
		HashSet<List<Integer>> answer = new HashSet<>();
		for (int x = 0; x < list.size(); x++) {
			for (int y = 0; y < list.size(); y++) {
				for (int z = 0; z < list.size(); z++) {
					if (x == y || y == z || x == z) continue;
					List<Integer> permutation = new ArrayList<Integer>();
					permutation.addAll(Arrays.asList(list.get(x), list.get(y), list.get(z)));
					answer.add(permutation);
				}
			}
		}
		return answer;
	}
	public static void testGenerate3DigitPermutations() {
		List<Integer> test = new ArrayList<>(Arrays.asList(1, 2, 3));
		System.out.println(generate3DigitPermutations(test));

		List<Integer> test2 = new ArrayList<>(Arrays.asList(8, 7, 6));
		System.out.println(generate3DigitPermutations(test2));
	}


	/*
	This problem was asked by Microsoft.
	Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
	For example, given [100, 4, 200, 1, 3, 2], the longest consecutive element sequence is [1, 2, 3, 4]. Return its length: 4.
	Your algorithm should run in O(n) complexity.
	*/





	/*
	This problem was asked by Square.
	Given a string and a set of characters, return the shortest substring containing all the characters in the set.
	For example, given the string "figehaeci" and the set of characters {a, e, i}, you should return "aeci".
	*/
	//Incomplete
	public static String substringWithSetCharacter(HashSet<Character> chars, String word) {
		HashMap<Character, Integer> count = new HashMap<>();
		int countSoFar = 0;
		String answer = "";
		String answerSoFar = "";

		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (count.containsKey(c)) {
				count.put(c, count.get(c) + 1);
			} else {
				count.put(c, 1);
			}
			countSoFar++;

	 		if (countSoFar >= chars.size()) {
	 			boolean hasAllChars = true;
	 			for (Character c: chars) {
	 				if (!count.contansKey(c)) {
	 					hasAllChars = false;
	 					break;
	 				}
	 			}
	 			if (hasAllChars) {
	 				answerSoFar += c;
	 				if (answerSoFar.length() < answer) {
	 					answer = answerSoFar;
	 				}
	 			}
	 		}
	 	}
	}

	/*
	This problem was asked by Google.
	Given a string, return the first recurring character in it, or null if there is no recurring character.
	For example, given the string "acbbac", return "b". Given the string "abcdef", return null.
	*/
	public static char firstRecurringCharacter(String s) {
		HashSet<Charcter> hs = new HashSet<>();
		for (char c: s.toCharArray()) {
			if (hs.containKey(c)) return c;
			else hs.add(c);
		}
		return '';
	}
	//Untested

	/*
	This problem was asked by Amazon.
	Given a string, find the longest palindromic contiguous substring. If there are more than one with the maximum length, return any one.
	For example, the longest palindromic substring of "aabcdcb" is "bcdcb". The longest palindromic substring of "bananas" is "anana".
	*/

	/*
	Asked by Bloomberg.
	Given a string with no unbalanced brackets, find the substring(s) within the most deeply nested bracket
	"ab(c(d)e)"      ->  "d"
	"((a)b(cd)ef)"   ->  "a", "cd"
	"()()" -> "", 
	"abcde" -> "abcde"
	"abbbbbbbbb(c(d)(e)(f(g)))" -> "g"
	"()" -> ""
	"" -> ""
	"(abc)" -> "abc"
	"abb(c(d)(e)(f(g)))" -> "g"
	    0  1 2  2  2 3
	*/
	public static List<String> deeplyNestedBracketString(String s) {
		return null;
	}













	
}