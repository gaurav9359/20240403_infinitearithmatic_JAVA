/**
 * Multiplies two numbers represented as arrays of digits.
 *
 * @param numArr1 The first number represented as an array of digits.
 * @param numArr2 The second number represented as an array of digits.
 * @return The result of multiplying the two numbers as a String (representing the number).
 * @throws IllegalArgumentException If either input array is empty or contains invalid elements (non-digits or negative values).
 */
public static String multiplyArrays(int[] numArr1, int[] numArr2) {
    // Check for invalid input arrays (empty, non-digits, or negative values)
    if (numArr1.length == 0 || numArr2.length == 0) {
        throw new IllegalArgumentException("Input arrays cannot be empty.");
    }
    for (int num : numArr1) {
        if (num < 0 || !Character.isDigit(num + '0')) {
            throw new IllegalArgumentException("Invalid input array: non-digit or negative value found.");
        }
    }
    for (int num : numArr2) {
        if (num < 0 || !Character.isDigit(num + '0')) {
            throw new IllegalArgumentException("Invalid input array: non-digit or negative value found.");
        }
    }

    // Reverse both arrays to simplify the multiplication process
    int[] reversedNumArr1 = reverseArray(numArr1);
    int[] reversedNumArr2 = reverseArray(numArr2);

    int maxLength = reversedNumArr1.length + reversedNumArr2.length;
    int[] result = new int[maxLength]; // Initialize result array with zeros

    // Perform multiplication
    for (int i = 0; i < reversedNumArr1.length; i++) {
        for (int j = 0; j < reversedNumArr2.length; j++) {
            int product = reversedNumArr1[i] * reversedNumArr2[j];
            result[i + j] += product; // Add the product to the corresponding position
        }
    }

    // Perform carryover
    for (int i = 0; i < maxLength - 1; i++) {
        int carryOver = result[i] / 10;
        result[i] %= 10;
        result[i + 1] += carryOver;
    }

    // Remove leading zeros and build the final string representation
    StringBuilder sb = new StringBuilder();
    boolean leadingZero = true;
    for (int digit : result) {
        if (digit != 0 || !leadingZero) {
            sb.append(digit);
            leadingZero = false;
        }
    }
    String finalResult = sb.toString();
    return finalResult.isEmpty() ? "0" : finalResult; // Handle empty result (product is 0)
}

// Helper method to reverse an array
private static int[] reverseArray(int[] arr) {
    int[] reversedArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
        reversedArr[arr.length - i - 1] = arr[i];
    }
    return reversedArr;
}

// Example usage (can be in a separate class)
public static void main(String[] args) {
    try {
        System.out.println(multiplyArrays(new int[]{9, 9, 9}, new int[]{1, 1, 1}));
        // ... (other test cases from the original code)
    } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
    }
}
