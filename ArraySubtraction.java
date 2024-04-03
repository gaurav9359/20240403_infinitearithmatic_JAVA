/**
 * Function to subtract two numbers represented as arrays of digits.
 *
 * @param numberArray1 The first number array.
 * @param numberArray2 The second number array.
 * @return The subtraction result as a String (representing the number).
 * @throws IllegalArgumentException If any input array is invalid (empty, non-array, non-numeric, or negative values).
 */
public static String subtract(int[] numberArray1, int[] numberArray2) {
    // Validate input: numberArray1 and numberArray2 must be arrays
    if (!isArray(numberArray1) || !isArray(numberArray2)) {
        throw new IllegalArgumentException("Invalid input: Both arrays are required.");
    }

    // Validate input: numberArray1 and numberArray2 must contain only numbers
    if (numberArray1.length == 0 || numberArray2.length == 0 ||
            containsNonNumbers(numberArray1) || containsNonNumbers(numberArray2)) {
        throw new IllegalArgumentException("Invalid input: Arrays must contain only numbers.");
    }

    // Validate input: numberArray1 and numberArray2 must contain non-negative numbers
    if (containsNegatives(numberArray1) || containsNegatives(numberArray2)) {
        throw new IllegalArgumentException("Invalid input: Arrays must contain non-negative numbers.");
    }

    // Pad the arrays to make them of equal length
    padArrays(numberArray1, numberArray2);

    // Determine the sign of the result by comparing the arrays digit by digit
    int comparison = compareArrays(numberArray1, numberArray2);
    if (comparison == 0) {
        return "0";
    }

    // If numberArray1 is smaller, swap numberArray1 and numberArray2
    if (comparison < 0) {
        swapArrays(numberArray1, numberArray2);
    }

    // Initialize variables for the subtraction algorithm
    StringBuilder result = new StringBuilder(); // Use StringBuilder for efficient string appending
    int indexA = numberArray1.length - 1;
    int indexB = numberArray2.length - 1;
    int borrow = 0;

    // Iterate through the arrays and subtract corresponding digits
    while (indexA >= 0 || indexB >= 0) {
        // Subtract digits with borrow for the current position
        int resultDigit = subtractDigitsWithBorrow(numberArray1[indexA], numberArray2[indexB], borrow);

        // Append the result digit to the result string
        result.insert(0, resultDigit);

        // Update the borrow for the next subtraction
        borrow = borrowFromPrevious(resultDigit);

        // Move to the next position in both arrays
        indexA--;
        indexB--;
    }

    // Add negative sign if necessary
    if (comparison < 0) {
        result.insert(0, "-"); // Add negative sign to the beginning of the result string
    }

    // Remove leading zeros
    return removeLeadingZeros(result.toString());
}

// Helper methods (can be in a separate class):

private static boolean isArray(int[] arr) {
    return arr != null;
}

private static boolean containsNonNumbers(int[] arr) {
    for (int num : arr) {
        if (Character.isDigit(num + '0')) {
            return false;
        }
    }
    return true;
}

private static boolean containsNegatives(int[] arr) {
    for (int num : arr) {
        if (num < 0) {
            return true;
        }
    }
    return false;
}

private static void padArrays(int[] arr1, int[] arr2) {
    int maxLength = Math.max(arr1.length, arr2.length);
    if (arr1.length < maxLength) {
        arr1 = prependZeros(arr1, maxLength - arr1.length);
    } else if (arr2.length < maxLength) {
        arr2 = prependZeros(arr2, maxLength - arr2.length);
    }
}

private static int[] prependZeros(int[] arr, int numZeros) {
    int[] newArr = new int[arr.length + numZeros];
    System.arraycopy(arr, 0, newArr, numZeros, arr.length);
    return newArr;
}

private static int compareArrays(int[] arr1, int[] arr2) {
    int maxLength = Math.max(arr1.length, arr2.length);
    int i = maxLength - 1;

    // Compare digits from the most significant position (end)
    while (i >= 0) {
        if (arr1[i] > arr2[i]) {
            return 1; // arr1 is greater
        } else if (arr1[i] < arr2[i]) {
            return -1; // arr2 is greater
        }
        i--;
    }

    // If all digits are equal, the arrays are equal
    return 0;
}

private static int subtractDigitsWithBorrow(int digit1, int digit2, int borrow) {
    // ... existing implementation from previous response ...
}

private static int borrowFromPrevious(int resultDigit) {
    return resultDigit == -1 ? 1 : 0; // Borrow 1 if the result is negative
}

private static String removeLeadingZeros(String resultString) {
    int startIndex = 0;
    while (startIndex < resultString.length() && resultString.charAt(startIndex) == '0') {
        startIndex++;
    }
    return resultString.substring(startIndex);
}
