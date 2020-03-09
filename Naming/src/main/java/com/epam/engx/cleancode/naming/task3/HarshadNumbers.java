package com.epam.engx.cleancode.naming.task3;

public class HarshadNumbers {

	// print some Harshad numbers
	public static void main(String[] args) {
		long rightEndOfRange = 1000; // limit the seq of Harshad numbers
		for (int i = 1; i <= rightEndOfRange; i++) {
			if (i % calculateSumOfDigits(i) == 0) {
				System.out.println(i);
			}
		}
	}

	private static int calculateSumOfDigits(int number) {
		int sumOfDigit = 0;
		while (number != 0) {
            sumOfDigit += number % 10;
            number = number / 10;
        }
		return sumOfDigit;
	}

}
