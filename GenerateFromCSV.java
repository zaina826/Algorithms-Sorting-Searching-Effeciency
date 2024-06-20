import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GenerateFromCSV {
    static Random random = new Random();

    public static int[] getData(String fileName, int length) {

        int[] flowDuration = new int[length];
        Set<Integer> selectedIndicesSet = new HashSet<>();
        int totalEntries = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            while (reader.readLine() != null) {
                totalEntries++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        while (selectedIndicesSet.size() < length) {
            int randomIndex = random.nextInt(totalEntries);
            selectedIndicesSet.add(randomIndex);
        }

        List<Integer> selectedIndicesList = new ArrayList<>(selectedIndicesSet);
        Collections.sort(selectedIndicesList);

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();

            int currentIndex = 0;
            int listIndex = 0;
            String line;
            while ((line = reader.readLine()) != null && listIndex < length) {
                if (currentIndex == selectedIndicesList.get(listIndex)) {
                    String[] values = line.split(",");
                    flowDuration[listIndex] = Integer.parseInt(values[6].trim());
                    listIndex++;
                }
                currentIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return flowDuration;
    }
}
