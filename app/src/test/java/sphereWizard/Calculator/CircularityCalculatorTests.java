package sphereWizard.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import sphereWizard.Exceptions.EmptyListException;
import sphereWizard.Exceptions.FlowOutputNotRecognizedException;
import sphereWizard.Exceptions.UnitNotRecognizedException;
import sphereWizard.Interfaces.sendFileData;
import sphereWizard.Interfaces.sendModeledData;
import sphereWizard.ProductSystem.ProductSystem;
import sphereWizard.ProductSystem.ProductSystemData;


/**
 * 
 * The CircularityCalculatorTest class contains unit tests for the
 * CircularityCalculator class.*
 * <p>
 * These tests cover the calculation of Circularity Index (MCIp) and Total
 * Circularity Index (MCItotal)
 * using both fictitious and real data for a product system.
 * </p>
 *
 * @author Group 22
 * @version 1.0
 */
public class CircularityCalculatorTests {

    private static sendModeledData productSystem;
    private static sendFileData productSystemData;

    @BeforeAll
    public static void init() throws EmptyListException {
        productSystemData = new ProductSystemData();

        String rightPath = "src/test/java/sphereWizard/Calculator/testEXAMPLECOMPLETESUPPLYCHAIN.csv";
        String delimiter = ";";

        try {
            productSystemData.getProductSystemData().readCsv(rightPath, delimiter);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnitNotRecognizedException e) {
            e.printStackTrace();
        } catch (FlowOutputNotRecognizedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        productSystem = new ProductSystem(productSystemData, 100, 100);
    }

    /**
     * Test001: Test the calculation of X using the calculateCircularityIndexTotal
     * method.
     * <p>
     * This test verifies that the CircularityCalculator correctly calculates the X
     * value
     * by calling the calculateCircularityIndexTotal method and checks if the result
     * matches the expected value.
     * </p>
     */
    @Test
    public void test016_CalculateXWithValidData() {
        CircularityCalculator calculator = new CircularityCalculator(productSystem);

        calculator.calculateX();

        double x = calculator.getX();
        double expectedResult = 9.0;

        assertEquals(x, expectedResult);
    }

    /**
     * Test002: Test the calculation of the utility factor using the
     * calculateCircularityIndex method.
     * <p>
     * This test verifies that the CircularityCalculator correctly calculates the
     * utility factor (F(X))
     * by calling the calculateCircularityIndex method and checks if the result
     * matches the expected value.
     * </p>
     */
    @Test
    public void test017_test001_CalculateUtilityFactorWithValidData() {
        CircularityCalculator calculator = new CircularityCalculator(productSystem);

        // Calculate X before invoking calculateCircularityIndex
        calculator.calculateX();

        // Fill the utility factor value by invoking calculateCircularityIndex
        calculator.calculateUtilityFactor();
        double utilityFactor = calculator.getUtilityFactor();

        // Calculate the expected result based on the formula
        double expectedResult = 0.9 / calculator.getX();

        assertEquals(expectedResult, utilityFactor);
    }

    @Test
    public void test017_test002_CalculateUtilityFactorWithZeroX() {
        CircularityCalculator calculator = new CircularityCalculator(productSystem);

        // Calculate X before invoking calculateCircularityIndex
        calculator.calculateX();

        // Fill the utility factor value by invoking calculateCircularityIndex
        calculator.calculateUtilityFactor();
        double utilityFactor = calculator.getUtilityFactor();

        // Calculate the expected result based on the formula
        double expectedResult = 0.9 / calculator.getX();

        assertEquals(expectedResult, utilityFactor);
        /* completar teste*/
    }

    /**
     * Test003: Test the calculation of the linear flow index using the
     * calculateCircularityIndex method.
     * <p>
     * This test verifies that the CircularityCalculator correctly calculates the
     * Linear Flow Index (LFI)
     * by calling the calculateCircularityIndex method and checks if the result
     * matches the expected value.
     * </p>
     */
    @Test
    public void test018_CalculateLinearFlowIndexWithValidData() {
        CircularityCalculator calculator = new CircularityCalculator(productSystem);

        // Fill the linear flow index value by invoking calculateCircularityIndex
        calculator.calculateLinearFlowIndex();
        double linearFlowIndex = calculator.getLinearFlowIndex();

        // Set the expected result for comparison
        double expectedResult = 0.9602633576265607;

        // Assert that the calculated linear flow index matches the expected result
        assertEquals(linearFlowIndex, expectedResult);
    }

    /**
     * Test004: Test the calculation of Circularity Index (MCIp) using the
     * calculateCircularityIndex method.
     * <p>
     * This test verifies that the CircularityCalculator correctly calculates the
     * Circularity Index (MCIp)
     * by calling the calculateCircularityIndex method and checks if the result
     * matches the expected value.
     * </p>
     */
    @Test
    public void test019_CalculateCircularityIndexWithValidData() {
        CircularityCalculator calculator = new CircularityCalculator(productSystem);

        // Fill the Circularity Index (MCIp) value by invoking calculateCircularityIndex
        double circularityIndex = calculator.calculateCircularityIndex();

        // Set the expected result for comparison
        double expectedResult = 0.9039736642373439;

        // Assert that the calculated Circularity Index (MCIp) matches the expected
        // result
        assertEquals(circularityIndex, expectedResult);
    }

    /**
     * Test005: Test the calculation of Total Circularity Index (MCItotal) using the
     * calculateCircularityIndexTotal method.
     * <p>
     * This test verifies that the CircularityCalculator correctly calculates the
     * Total Circularity Index (MCItotal)
     * by calling the calculateCircularityIndexTotal method and checks if the result
     * matches the expected value.
     * </p>
     */
//    @Test
//    public void test005CalculateCircularityIndexTotal() {
//        CircularityCalculator calculator = new CircularityCalculator(productSystem);
//
//        // Fill the Total Circularity Index (MCItotal) value by invoking
//        // calculateCircularityIndexTotal
//        double circularityIndexTotal = calculator.calculateCircularityIndexTotal();
//
//        // Set the expected result for comparison
//        double expectedResult = 2400404.0366666666;
//
//        // Assert that the calculated Total Circularity Index (MCItotal) matches the
//        // expected result
//        assertEquals(circularityIndexTotal, expectedResult);
//    }

    /**
     * Test006: Test the proper functioning of the getProductSystem and
     * setProductSystem methods.
     * <p>
     * This test verifies that the getProductSystem and setProductSystem methods of
     * the CircularityCalculator
     * work as expected by creating a new ProductSystem, setting it using
     * setProductSystem, and then retrieving
     * it using getProductSystem. It checks if the retrieved ProductSystem matches
     * the one that was set.
     * </p>
     */
    @Test
    public void test020_VerifyGetAndSet() {
        //ProductSystem productSystemInstance = new ProductSystem(productSystemData);
        CircularityCalculator circularityCalculator = new CircularityCalculator(productSystem);
        ProductSystem newProductSystem = new ProductSystem(productSystemData);

        // Set a new ProductSystem using setProductSystem
        circularityCalculator.setProductSystem(newProductSystem);

        // Retrieve the ProductSystem using getProductSystem
        ProductSystem retrievedProductSystem = circularityCalculator.getProductSystem().getProductSystem();

        // Assert that the retrieved ProductSystem matches the one that was set
        assertEquals(newProductSystem, retrievedProductSystem);
    }
}