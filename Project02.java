public class Project02 {

    public static int globalMax;

    public static void main(String[] args) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Maximum Sum Subarray: " + maxSubArraySum(arr));
    }

    public static int maxSubArraySum(int[] arr) {
        globalMax = Integer.MIN_VALUE;
        maxSubArrayHelper(arr, arr.length - 1);
        return globalMax;
    }

    // Recursive
    private static int maxSubArrayHelper(int[] arr, int i) {
        if (i == 0) {
            globalMax = Math.max(globalMax, arr[0]);
            return arr[0];
        }

        int localMax = Math.max(maxSubArrayHelper(arr, i - 1) + arr[i], arr[i]);

        globalMax = Math.max(globalMax, localMax);

        return localMax;
    }

}


