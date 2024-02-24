package sphereWizard.Filters;

import sphereWizard.Exceptions.EmptyListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sphereWizard.Exceptions.FlowOutputNotRecognizedException;
import sphereWizard.Exceptions.UnitNotRecognizedException;
import sphereWizard.FilterData.FilterData;
import sphereWizard.Interfaces.SendFilteredData;
import sphereWizard.ProductSystem.ProductSystemData;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FilterDataTest {
    String filepath = "src/test/java/sphereWizard/ProductSystem/test001FilePathImportCorrect.csv";
    ProductSystemData dataReader = new ProductSystemData();

    @BeforeEach
    /**
     * Setup  which needs to be done before each test, so that it can be successfully executed
     */
    public void setup() {
        try {
            dataReader.readCsv(filepath, ";");
        } catch (UnitNotRecognizedException | FlowOutputNotRecognizedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    /**
     * Test 001 tests whether the filter is able to send the processes which adhere to the specified category
     */
    public void test005_test001_SendByCategoryShouldBeCorrect() {
        SendFilteredData filter = new FilterData(dataReader);
        try {
            assertEquals(dataReader.getProcesses().get(0),
                    filter.sendByCategory("Categoria do Processo").get(0));
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void test005_test002_SendByCategoryInvalidCategoryShouldThrowException() {
        // Setup
        SendFilteredData filter = new FilterData(dataReader);

        // Execution and Assertion
        assertThrows(EmptyListException.class, () -> filter.sendByCategory("InvalidCategory"),
                "EmptyListException should be thrown for an invalid category.");
    }
    @Test
    public void test005_test003_SendByNullCategoryShouldThrowException() {
        // Setup
        SendFilteredData filter = new FilterData(dataReader);

        // Execution and Assertion
        assertThrows(EmptyListException.class, () -> filter.sendByCategory(null),
                "EmptyListException should be thrown for an invalid category.");
    }
    @Test
    public void test005_test004_SendByEmptyStringCategoryShouldNotReturnProcesses() {
        // Setup
        SendFilteredData filter = new FilterData(dataReader);

        // Execution and Assertion
        assertThrows(EmptyListException.class, () -> filter.sendByCategory(""),
                "EmptyListException should be thrown for an invalid category.");
    }

    @Test
    /**
     * Test 002 tests whether the filter is able to send the processes which adhere to the specified name
     */
    public void test006_test001_SendByProcessNameShouldBeCorrect() {
        SendFilteredData filter = new FilterData(dataReader);
        try {
            assertEquals(dataReader.getProcesses().get(0),
                    filter.sendByName("Nome do Processo").get(0));
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    /**
     * Test 003 tests whether the filter is able to send processes with a name that does not exist
     */
    public void test006_test002_SendByNonExistentNameShouldThrowException() {
        SendFilteredData filter = new FilterData(dataReader);
        assertThrows(EmptyListException.class, () -> filter.sendByName("NonExistentName"),
                "EmptyListException should be thrown for a non-existent name.");
    }

    @Test
    /**
     * Test 004 tests whether the filter is able to handle a null name
     */
    public void test006_test003_SendByNullNameShouldThrowException() {
        SendFilteredData filter = new FilterData(dataReader);
        assertThrows(EmptyListException.class, () -> filter.sendByName(null),
                "EmptyListException should be thrown for a null name.");
    }

    @Test
    /**
     * Test 005 tests whether the filter is able to handle an empty string as a name
     */
    public void test006_test004_SendByEmptyStringNameShouldNotReturnProcesses() {
        SendFilteredData filter = new FilterData(dataReader);
        assertThrows(EmptyListException.class, () -> filter.sendByName(""),
                "EmptyListException should be thrown for an empty string name.");
    }

    @Test
    /**
     * Test 003 tests whether the filter correctly returns the entirety of the product system,
     * in case no specifications are made.
     */
    public void test007_sendAllShouldSendWholeProductSystem() {
        SendFilteredData filter = new FilterData(dataReader);
        try {
            assertEquals(dataReader, filter.sendAll());
        } catch (EmptyListException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    /**
     * Test 004 tests whether the filter throws the correct and expected exceptions, if
     * no results are returned from the selected filters
     */
    public void test008_FilterShouldNotReturnEmptyResults() {
        SendFilteredData filter = new FilterData(dataReader);
        assertThrows(EmptyListException.class,
                () -> {
                    filter.sendByName("does not exist");
                });

        assertThrows(EmptyListException.class,
                () -> {
                    filter.sendByCategory("does not exist");
                });
    }
}
