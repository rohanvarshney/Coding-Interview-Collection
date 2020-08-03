import java.util.*;
import java.io.*;

public class InterviewQuestions {


	public static void main(String args[]) {
		//Perform function testing here.
		testEvaluateCrossword();

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
			throw new IllegalArgumentException("duude");
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


	










	
}