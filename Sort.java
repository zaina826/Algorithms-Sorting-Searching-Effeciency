import java.util.Arrays;

public class Sort {
    public static int[] inserstionSort(int[] array) {
        int arrayLen = array.length;
        for (int j = 1; j < arrayLen; j++) {
            int val= array[j];
            int i = j-1;
            //We add the equal sign in case the 0th element is not in its right place:
            while (i>=0 && array[i]>val){
                array[i+1]=array[i];
                i=i-1;
            }
            array[i+1]= val;
        }
        return array;
    }
    public static int[] mergeSort(int[] array) {
        int arrayLen = array.length;

        if (arrayLen <= 1) {
            return (array);
        }

        int[] left_array = Arrays.copyOfRange(array, 0, arrayLen / 2);
        int[] right_array = Arrays.copyOfRange(array, arrayLen / 2, arrayLen);

        left_array = mergeSort(left_array);
        right_array = mergeSort(right_array);
        int[] result= merge(left_array, right_array);
        return (result);
    }

    public static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, resultIndex = 0;

        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] <= right[rightIndex]) {
                result[resultIndex++] = left[leftIndex++];
            } else {
                result[resultIndex++] = right[rightIndex++];
            }
        }

        while (leftIndex < left.length) {
            result[resultIndex++] = left[leftIndex++];
        }

        while (rightIndex < right.length) {
            result[resultIndex++] = right[rightIndex++];
        }

        return result;
    }

    public static int[] countingSort(int[] array, int max) {

        //This way we'll have an index for each element, and also an index for zero.
        int[] count = new int[max+1];
        int size = array.length;
        int[] result = new int[size];

        //Initializing our count array
        for (int i = 0; i < size; i++) {
            int j = array[i];
            count[j]++;
        }

        //Now we do the accumulative sum for the count array:
        for (int i = 1; i < max+1; i++) {
            count[i]=count[i]+count[i-1];
            };

        //Now we shift our count list to the right by one, we do this by starting from the end:
        //This reverse iteration helps maintain the stability of the sort, meaning that equal elements retain their original order relative to each other.
        for (int i = size-1; i >= 0; i--) {
            int num = array[i];
            //decrement how many we have left:
            count[num]= count[num]-1;
            //Then we just set that place in the result list to have that number
            result[count[num]]=array[i];
        }
        return result;
        };
    }
