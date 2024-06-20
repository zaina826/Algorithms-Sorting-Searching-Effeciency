public class search {
    public static int linear(int[] array,int x) {
        int size = array.length;
        for (int i=0; i < size; i++){
            if (array[i]== x){
            return i;}
        }
        return -1;
    };

    public static int binary(int[] array, int x) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == x) {
                return mid;
            } else if (array[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }


}
