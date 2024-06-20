import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.io.IOException;
import java.util.Arrays;

class Main {
    public static void main(String args[]) throws IOException {

        String fileName = args[0];

        System.out.println("Working on Sorting....");
        Tester.getAverages(fileName);

        System.out.println("Working on Searching....");
        Tester.getAveragesForSearch(fileName);

        Tester.printAllPerformanceData();

        showAndSaveChart("Random Performance", Tester.lengths, Tester.Random, false);
        showAndSaveChart("Sorted Performance", Tester.lengths, Tester.Sorted, false);
        showAndSaveChart("Reverse Sorted Performance", Tester.lengths, Tester.ReverseSorted, false);
        showAndSaveChart("Search Algorithms", Tester.lengths, Tester.averagesForSearch, true);

    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis, boolean search) throws IOException {
        // Create Chart
        XYChart chart;
        if (!search) {

            chart = new XYChartBuilder().width(800).height(600).title(title)
                    .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();
        } else {
            chart = new XYChartBuilder().width(800).height(600).title(title)
                    .yAxisTitle("Time in Nanoseconds").xAxisTitle("Input Size").build();
        }
        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        if (!search) {
            chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
            chart.addSeries("Merge Sort", doubleX, yAxis[1]);
            chart.addSeries("Counting Sort", doubleX, yAxis[2]);
        } else {
            chart.addSeries("Random Linear", doubleX, yAxis[0]);
            chart.addSeries("Sorted Linear", doubleX, yAxis[1]);
            chart.addSeries("Binary", doubleX, yAxis[2]);
        }


        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
}
