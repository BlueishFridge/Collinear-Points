import java.util.Arrays;
import java.util.random.RandomGenerator;

public class MergeSort {


    public static void mergesort(int[] arr) {
        int n = arr.length;
        int mid = n/2;
        int[] left = new int[mid];
        int[] right = new int[n-mid];

        if (n<2) return;

        System.arraycopy(arr, 0, left, 0, mid);
        if (n - mid >= 0) System.arraycopy(arr, mid, right, 0, n - mid);
        mergesort(left);
        mergesort(right);

        merge(arr, left, right);
    }

    private static void merge(int[] arr, int[] left, int[] right) {

        int i = 0,j = 0,k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j] ){
                arr[k++] = left[i++];
            } else if (right[j] <= left[i] ){
                arr[k++] = right[j++];
            }
        }
        while(i< left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }



    public static void main(String[] args) {

    int[] array = new int[10];
    for (int i = 0; i < 10; i++){
        array[i] = RandomGenerator.getDefault().nextInt(1000000);
        }

    System.out.println(Arrays.toString(array));

    mergesort(array);

    System.out.println(Arrays.toString(array));



    }
}