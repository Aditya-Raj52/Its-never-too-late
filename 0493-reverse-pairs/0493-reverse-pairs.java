class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) return 0;
        int[] temp = new int[nums.length];
        return mergeSortCount(nums, temp, 0, nums.length - 1);
    }

    private static int mergeSortCount(int[] arr, int[] temp, int left, int right) {
        if (left >= right) return 0;

        int mid = left + (right - left) / 2;
        int count = mergeSortCount(arr, temp, left, mid) + 
                    mergeSortCount(arr, temp, mid + 1, right);

        // Step 1: Count reverse pairs between left half and right half
        int j = mid + 1;
        for (int i = left; i <= mid; i++) {
            while (j <= right && (long) arr[i] > 2L * arr[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }

        // Step 2: Standard merge of two sorted halves
        merge(arr, temp, left, mid, right);

        return count;
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (i = left; i <= right; i++) {
            arr[i] = temp[i];
        }
    }
}