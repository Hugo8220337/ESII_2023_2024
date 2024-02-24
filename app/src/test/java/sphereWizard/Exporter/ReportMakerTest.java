package sphereWizard.Exporter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sphereWizard.Calculator.CircularityCalculator;
import sphereWizard.Exceptions.EmptyListException;
import sphereWizard.Exceptions.FlowOutputNotRecognizedException;
import sphereWizard.Exceptions.UnitNotRecognizedException;

import sphereWizard.ProductSystem.ProductSystem;
import sphereWizard.ProductSystem.ProductSystemData;

/**
 * The ReportMakerTest class contains unit tests for the ReportMaker class.
 * <p>
 * These tests cover the functionality of exporting information to a CSV file using the ReportMaker class.
 * The class includes a test for the {@code exportInformationToCsv} method, which is responsible for creating
 * a CSV file with information about the product system, processes, and calculator results.
 * </p>
 * <p>
 * The tests involve creating a ProductSystem instance with both fictitious and real data for testing.
 * The exported CSV file is then checked for existence and verified for its content, including headers and
 * data related to processes and calculator results.
 * </p>
 * <p>
 * Additionally, this class initializes the necessary data for testing using the {@code @BeforeAll} method.
 * </p>
 *
 * @author Group 22
 * @version 1.0
 */

public class ReportMakerTest {

    private static ProductSystem productSystem;
    private static ProductSystemData productSystemData;

    private static CircularityCalculator circularityCalculator;

    /**
     * Initializes the necessary data for testing before running any tests.
     */
    @BeforeAll
    public static void init() throws EmptyListException {
        // Initialization code for ProductSystem and ProductSystemData

        productSystemData = new ProductSystemData();

        String rightPath = "src/test/java/sphereWizard/Exporter/testEXAMPLECOMPLETESUPPLYCHAIN.csv";
        String delimiter = ";";

        try {
            productSystemData.readCsv(rightPath, delimiter);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnitNotRecognizedException e) {
            e.printStackTrace();
        } catch (FlowOutputNotRecognizedException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }

        productSystem = new ProductSystem(productSystemData, 100, 100);

        productSystem.calculateAllMethods();

        circularityCalculator = new CircularityCalculator(productSystem);

        circularityCalculator.calculateCircularityIndex();


    }

    /**
     * Tests the functionality of exporting information to a CSV file using the {@code exportInformationToCsv} method.
     */
    @Test
    public void Test021_ValidExport() {

        // Create a ReportMaker object
        ReportMaker reportMaker = new ReportMaker(productSystem,circularityCalculator);

        // Execute the exportInformationToCsv method
            reportMaker.exportInformationToCsv("src/test/java/sphereWizard/Exporter/testFilePath.csv");

        // For example, check if the file was created and contains the expected data

        // Check if the file was created
        File file = new File("src/test/java/sphereWizard/Exporter/testFilePath.csv");
        assertTrue(file.exists());

        // Check the content of the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Check the first line
            String firstLine = reader.readLine();
            assertTrue(firstLine.contains(";;;Processes;;;"));

            // Add more checks as needed, depending on the format of your file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Test002: Tests the functionality of exporting information to a CSV file with an invalid ProductSystem instance.

    @Test
    public void Test001_test002_exportWithInvalidProductSystem() { //TODO : completar
        ReportMaker reportMaker = new ReportMaker(null, circularityCalculator);

        // Execute the exportInformationToCsv method
        NullPointerException exception = assertThrows(NullPointerException.class, () ->
                reportMaker.exportInformationToCsv("src/test/java/sphereWizard/Exporter/testFilePath.csv"));

        assertNull(exception.getMessage()); // Null message is expected for a NullPointerException
    }*/

    /*
     * Test003: Tests the functionality of exporting information to a CSV file with an invalid CircularityCalculator instance.
    @Test
    public void Test001_test003_exportWithInvalidCircularityCalculator() { //TODO: finish this test
        // Create a ReportMaker object
        ReportMaker reportMaker = new ReportMaker(productSystem, null);

        // Execute the exportInformationToCsv method
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                reportMaker.exportInformationToCsv("src/test/java/sphereWizard/Exporter/testFilePath.csv"));

        assertEquals("Invalid CircularityCalculator instance", exception.getMessage());
    }*/

    /*
     * Test004: Tests the functionality of exporting information to a CSV file with an invalid filename.
     * This test expects a FileNotFoundException.
    @Test
    public void Test001_test004_exportWithInvalidFilename() { //TODO: acabar este teste
        // Create a ReportMaker object
        ReportMaker reportMaker = new ReportMaker(productSystem, circularityCalculator);

        // Execute the exportInformationToCsv method with an invalid filename
        FileNotFoundException exception = assertThrows(FileNotFoundException.class, () ->
                reportMaker.exportInformationToCsv("nonexistent/file.csv"));

        assertEquals("File not found: nonexistent/file.csv", exception.getMessage());
    }*/


}
