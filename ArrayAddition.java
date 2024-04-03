/**
 * Class to add two arrays representing numbers.
 */
public class ArrayAddition {

    /**
     * Function to add two arrays representing numbers.
     *
     * @param arr1 The first array.
     * @param arr2 The second array.
     * @return The sum of the two arrays as a string.
     * @throws IllegalArgumentException if both input arrays contain all zeros
     *                                  or if either input array is empty.
     * @throws ArithmeticException if the resulting sum array exceeds the length threshold.
     */
    public static String addition(int[] arr1, int[] arr2) {
        // Check if every number of the arrays is 0
        boolean allZeros1 = allZeros(arr1);
        boolean allZeros2 = allZeros(arr2);

        if (allZeros1 && allZeros2) {
            throw new IllegalArgumentException("Both input arrays contain all zeros.");
        }

        // Validate input arrays
        if (arr1.length == 0 || arr2.length == 0) {
            throw new IllegalArgumentException("Empty arrays are not allowed.");
        }

        // Resultant array to store the sum of digits
        int[] res = new int[Math.max(arr1.length, arr2.length) + 1];
        int idx = res.length - 1; // Index to store the result digit
        int carry = 0; // Carry for addition

        // Iterate through the arrays and add corresponding digits
        for (int i = arr1.length - 1, j = arr2.length - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            if (i >= 0) {
                sum += arr1[i];
            }
            if (j >= 0) {
                sum += arr2[j];
            }
            res[idx--] = sum % 10;
            carry = sum / 10;
        }

        // Check if the length exceeds the threshold
        if (idx < 0) {
            throw new ArithmeticException("Array length overflow: The resulting sum array is too large.");
        }

        // Convert the result array to a string, skipping leading zeros
        StringBuilder sb = new StringBuilder();
        int start = res[0] == 0 ? 1 : 0;
        for (int i = start; i < res.length; i++) {
            sb.append(res[i]);
        }

        return sb.toString();
    }

    /**
     * Helper function to check if all elements of an array are zeros.
     *
     * @param arr The input array.
     * @return true if all elements are zeros, false otherwise.
     */
    private static boolean allZeros(int[] arr) {
        for (int num : arr) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Function to test the 'addition' function with different scenarios.
     */
    public static void main(String[] args) {
        try {
            System.out.println(addition(new int[]{9, 9}, new int[]{0, 1, 1}));
            System.out.println(addition(new int[]{9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9}, new int[]{1}));
            System.out.println(addition(new int[]{0, 0, 0}, new int[]{0, 0, 0}));
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }
    }
}