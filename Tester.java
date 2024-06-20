import java.util.*;

public class Tester {


    static int[] lengths = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};

    static double[][][] averages = new double[3][3][lengths.length];
    static double[][] averagesForSearch = new double[3][lengths.length];

    static double[][] Random = new double[3][lengths.length];
    static double[][] Sorted = new double[3][lengths.length];
    static double[][] ReverseSorted = new double[3][lengths.length];

    public static void getAverages(String fileName) {
        double[] insertionSortRandomTimes = new double[lengths.length];
        double[] insertionSortSortedTimes = new double[lengths.length];
        double[] insertionSortReverseSortedTimes = new double[lengths.length];
        double[] mergeSortRandomTimes = new double[lengths.length];
        double[] mergeSortSortedTimes = new double[lengths.length];
        double[] mergeSortReverseSortedTimes = new double[lengths.length];
        double[] countingSortRandomTimes = new double[lengths.length];
        double[] countingSortSortedTimes = new double[lengths.length];
        double[] countingSortReverseSortedTimes = new double[lengths.length];


        for (int i = 0; i < lengths.length; i++) {
            int length = lengths[i];
            long timeForInsertionRandom = 0, timeForInsertionSorted = 0, timeForInsertionReverse = 0;
            long timeForMergeRandom = 0, timeForMergeSorted = 0, timeForMergeReverse = 0;
            long timeForCountingRandom = 0, timeForCountingSorted = 0, timeForCountingReverse = 0;

            for (int times = 0; times < 10; times++) {
                int[] durationFlowData = GenerateFromCSV.getData(fileName, length);
                int[] copyForCountingSort = Arrays.copyOf(durationFlowData, durationFlowData.length);
                int[] copyForMergeSort = Arrays.copyOf(durationFlowData, durationFlowData.length);

                if (durationFlowData == null) {
                    System.out.println("Error in reading CSV file...");
                    return;
                }

                // Insertion Sort on Random Data
                int[] copyForInsertionSort = Arrays.copyOf(durationFlowData, durationFlowData.length);
                long startTime = System.currentTimeMillis();
                int[] sorted = Sort.inserstionSort(copyForInsertionSort);
                long endTime = System.currentTimeMillis();
                timeForInsertionRandom += (endTime - startTime);

// Insertion Sort on Sorted Data
                copyForInsertionSort = Arrays.copyOf(sorted, sorted.length);
                startTime = System.currentTimeMillis();
                Sort.inserstionSort(copyForInsertionSort);
                endTime = System.currentTimeMillis();
                timeForInsertionSorted += (endTime - startTime);

// Insertion Sort on Reverse Sorted Data
                reverseArray(sorted);
                copyForInsertionSort = Arrays.copyOf(sorted, sorted.length);
                startTime = System.currentTimeMillis();
                Sort.inserstionSort(copyForInsertionSort);
                endTime = System.currentTimeMillis();
                timeForInsertionReverse += (endTime - startTime);

// Merge Sort on Random Data
                startTime = System.currentTimeMillis();
                sorted = Sort.mergeSort(copyForMergeSort);
                endTime = System.currentTimeMillis();
                timeForMergeRandom += (endTime - startTime);

// Merge Sort on Sorted Data
                copyForMergeSort = Arrays.copyOf(sorted, sorted.length);
                startTime = System.currentTimeMillis();
                Sort.mergeSort(copyForMergeSort);
                endTime = System.currentTimeMillis();
                timeForMergeSorted += (endTime - startTime);

// Merge Sort on Reverse Sorted Data
                reverseArray(sorted);
                copyForMergeSort = Arrays.copyOf(sorted, sorted.length);
                startTime = System.currentTimeMillis();
                Sort.mergeSort(copyForMergeSort);
                endTime = System.currentTimeMillis();
                timeForMergeReverse += (endTime - startTime);


                //Counting Sort on Random Data
                startTime = System.currentTimeMillis();
                sorted = Sort.countingSort(copyForCountingSort, findMax(durationFlowData));
                endTime = System.currentTimeMillis();
                timeForCountingRandom += (endTime - startTime);

                // Counting Sort on Sorted Data
                copyForCountingSort = Arrays.copyOf(sorted, sorted.length);
                startTime = System.currentTimeMillis();
                sorted = Sort.countingSort(copyForCountingSort, findMax(durationFlowData));
                endTime = System.currentTimeMillis();
                timeForCountingSorted += (endTime - startTime);

                // Counting Sort on Reverse
                reverseArray(sorted);
                copyForCountingSort = Arrays.copyOf(sorted, sorted.length);
                startTime = System.currentTimeMillis();
                Sort.countingSort(copyForCountingSort, findMax(durationFlowData));
                endTime = System.currentTimeMillis();
                timeForCountingReverse += (endTime - startTime);

            }
            insertionSortRandomTimes[i] = timeForInsertionRandom / 10.0;
            insertionSortSortedTimes[i] = timeForInsertionSorted / 10.0;
            insertionSortReverseSortedTimes[i] = timeForInsertionReverse / 10.0;

            mergeSortRandomTimes[i] = timeForMergeRandom / 10.0;
            mergeSortSortedTimes[i] = timeForMergeSorted / 10.0;
            mergeSortReverseSortedTimes[i] = timeForMergeReverse / 10.0;

            countingSortRandomTimes[i] = timeForCountingRandom / 10.0;
            countingSortSortedTimes[i] = timeForCountingSorted / 10.0;
            countingSortReverseSortedTimes[i] = timeForCountingReverse / 10.0;

            Random[0][i] = insertionSortRandomTimes[i];
            Sorted[0][i] = insertionSortSortedTimes[i];
            ReverseSorted[0][i] = insertionSortReverseSortedTimes[i];

            Random[1][i] = mergeSortRandomTimes[i];
            Sorted[1][i] = mergeSortSortedTimes[i];
            ReverseSorted[1][i] = mergeSortReverseSortedTimes[i];

            Random[2][i] = countingSortRandomTimes[i];
            Sorted[2][i] = countingSortSortedTimes[i];
            ReverseSorted[2][i] = countingSortReverseSortedTimes[i];
        }

        for (int i = 0; i < lengths.length; i++) {
            averages[0][0][i] = insertionSortRandomTimes[i];
            averages[0][1][i] = insertionSortSortedTimes[i];
            averages[0][2][i] = insertionSortReverseSortedTimes[i];

            averages[1][0][i] = mergeSortRandomTimes[i];
            averages[1][1][i] = mergeSortSortedTimes[i];
            averages[1][2][i] = mergeSortReverseSortedTimes[i];

            averages[2][0][i] = countingSortRandomTimes[i];
            averages[2][1][i] = countingSortSortedTimes[i];
            averages[2][2][i] = countingSortReverseSortedTimes[i];
        }
    }

    private static void reverseArray(int[] array) {
        for (int i = 0, j = array.length - 1; i < j; i++, j--) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    public static void getAveragesForSearch(String fileName) {
        double[] linearSearchRandom = new double[lengths.length];
        double[] linearSearchSorted = new double[lengths.length];
        double[] binarySearchSorted = new double[lengths.length];
        for (int i = 0; i < lengths.length; i++) {
            int length = lengths[i];
            long timeForRandomLinear = 0;
            long timeForSortedLinear = 0, timeForSortedBinary = 0;
            for (int times = 0; times < 1000; times++) {
                int[] durationFlowData = GenerateFromCSV.getData(fileName, length);
                if (durationFlowData == null) {
                    System.out.println("Error in reading CSV file...");
                    return;
                }

                Random random = new Random();
                int randomIndex = random.nextInt(durationFlowData.length);
                int randomElement = durationFlowData[randomIndex];

                long startTime = System.nanoTime();
                search.linear(durationFlowData,randomElement);
                long endTime = System.nanoTime();
                timeForRandomLinear+= (endTime-startTime);

                int[]sorted=Sort.mergeSort(durationFlowData);
                startTime = System.nanoTime();
                search.linear(sorted,randomElement);
                endTime = System.nanoTime();
                timeForSortedLinear+= (endTime-startTime);


                startTime = System.nanoTime();
                search.binary(sorted,randomElement);
                endTime = System.nanoTime();

                timeForSortedBinary+= (endTime-startTime);

            }
            linearSearchRandom[i]=timeForRandomLinear/1000.0;
            linearSearchSorted[i]=timeForSortedLinear/1000.0;
            binarySearchSorted[i]=timeForSortedBinary/1000.0;
        }
        for (int i = 0; i < lengths.length; i++) {
            averagesForSearch[0][i] = linearSearchRandom[i];
            averagesForSearch[1][i] = linearSearchSorted[i];
            averagesForSearch[2][i] = binarySearchSorted[i];
        }


    }
    public static void printAllPerformanceData() {
        String[] sortingMethods = {"Insertion Sort", "Merge Sort", "Counting Sort"};
        String[] dataTypes = {"Random Data", "Sorted Data", "Reverse Sorted Data"};
        String[] searchMethods = {"Linear Search on Random Data", "Linear Search on Sorted Data", "Binary Search on Sorted Data"};

        for (int i = 0; i < sortingMethods.length; i++) {
            System.out.println( sortingMethods[i] );
            for (int j = 0; j < dataTypes.length; j++) {
                System.out.println("-- " + dataTypes[j] + " --");
                double[] currentData = j == 0 ? Random[i] : j == 1 ? Sorted[i] : ReverseSorted[i];
                for (int k = 0; k < lengths.length; k++) {
                    System.out.printf("Length %d: %.2f ms\n", lengths[k], currentData[k]);
                }
                System.out.println();
            }
        }

        System.out.println("Search Algorithms Performance");
        for (int i = 0; i < searchMethods.length; i++) {
            System.out.println( searchMethods[i] );
            for (int j = 0; j < lengths.length; j++) {
                System.out.printf("Length %d: %.2f ns\n", lengths[j], averagesForSearch[i][j]);
            }
            System.out.println();
        }
    }


}
