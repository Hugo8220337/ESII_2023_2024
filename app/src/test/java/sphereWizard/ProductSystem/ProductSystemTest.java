package sphereWizard.ProductSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sphereWizard.Exceptions.EmptyListException;
import sphereWizard.Exceptions.FlowOutputNotRecognizedException;
import sphereWizard.Exceptions.UnitNotRecognizedException;

/**
 * The {@code ProductSystemTest} class contains JUnit tests for the {@link ProductSystem} class.
 * It covers various methods such as waste calculation, recycled input, mass calculation,
 * necessary energy for primary and secondary materials, getters and setters, and overall system calculations.
 *
 * @author Group 22
 * @version 1.0
 */

public class ProductSystemTest {

    private static ProductSystemData productSystemData;

    @BeforeAll
    public static void init() {

        productSystemData = new ProductSystemData();
        String rightPath = "src/test/java/sphereWizard/ProductSystem/testEXAMPLECOMPLETESUPPLYCHAIN.csv";
        String delimiter = ";";
        try {
            productSystemData.readCsv(rightPath, delimiter);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnitNotRecognizedException e) {
            throw new RuntimeException(e);
        } catch (FlowOutputNotRecognizedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Expected values for the tests
    double expectedMass = 1227.11;
    double expectedRecycledInput = 27.0;
    double expectedWasteEliminated = 1185.11;
    double expectedNecessaryEnergyForPrimaryMaterials = 1.44E7;
    double expectedNecessaryEnergyForSecundaryMaterials = 7200000.0;

    /**
     * Test for verifying waste calculation in the {@link ProductSystem} class.
     * @throws EmptyListException if productSystemData object is empty
     */
    @Test
    public void test009_WasteShouldBeCorrectWithinRange() throws EmptyListException {

        ProductSystem productSystemInstance = new ProductSystem(productSystemData, 5, 10);

        double wasteEliminated = productSystemInstance.calculateWasteEliminated();

        assertEquals(expectedWasteEliminated, wasteEliminated, 0.01);
    }



    /**
     * Test for verifying recycled input calculation in the {@link ProductSystem} class.
     * @throws EmptyListException if productSystemData object is empty
     */
    @Test
    public void test010_RecycledInputShouldBeCorrectWithinRange() throws EmptyListException{

        ProductSystem productSystemInstance = new ProductSystem(productSystemData, 5, 10);

        double recycledInput = productSystemInstance.calculateRecycledInput();

        assertEquals(expectedRecycledInput, recycledInput, 0.01);
    }

    /**
     * Test for verifying mass calculation in the {@link ProductSystem} class.
     * @throws EmptyListException if productSystemData object is empty
     */
    @Test
    public void test011_MassShouldBeCorrectWithinRange() throws EmptyListException{

        ProductSystem productSystemInstance = new ProductSystem(productSystemData, 5, 10);

        double mass = productSystemInstance.calculateMass();

        assertEquals(expectedMass, mass, 0.01);
    }

    /**
     * Test for verifying necessary energy calculation for primary materials in the {@link ProductSystem} class.
     * @throws EmptyListException if productSystemData object is empty
     */
    @Test
    public void test012_NecessaryEnergyForPrimaryMaterialsShouldBeCorrectWithinRange () throws EmptyListException {

        ProductSystem productSystemInstance = new ProductSystem(productSystemData, 5, 10);

        double calculatedNecessaryEnergyForPrimaryMaterials = productSystemInstance.calculateNecessaryEnergyForPrimaryMaterials();

        assertEquals(expectedNecessaryEnergyForPrimaryMaterials, calculatedNecessaryEnergyForPrimaryMaterials, 0.01);
    }

    /**
     * Test for verifying necessary energy calculation for secondary materials in the {@link ProductSystem} class.
     * @throws EmptyListException if productSystemData object is empty
     */
    @Test
    public void test013_NecessaryEnergyForSecondaryyMaterialsShouldBeCorrectWithinRange() throws EmptyListException{

        ProductSystem productSystemInstance = new ProductSystem(productSystemData, 5, 10);

        double calculatedNecessaryEnergyForSecundaryMaterials = productSystemInstance.calculateNecessaryEnergyForSecondaryMaterials();

        assertEquals(expectedNecessaryEnergyForSecundaryMaterials, calculatedNecessaryEnergyForSecundaryMaterials, 0.01);
    }

    /**
     * Test for verifying getters and setters in the {@link ProductSystem} class.
     * @throws EmptyListException if productSystemData object is empty
     */
    @Test
    public void test014_VerifyGettersAndSetters() throws EmptyListException{

        ProductSystem productSystemInstance = new ProductSystem(productSystemData, 5, 10);

        productSystemInstance.setProductLifetime(8);
        productSystemInstance.setProductUtility(15);
        int updatedLifetime = productSystemInstance.getProductLifetime();
        int updatedUtility = productSystemInstance.getProductUtility();

        assertEquals(8, updatedLifetime);
        assertEquals(15, updatedUtility);
    }

    /**
     * Test for verifying modifications in the {@link ProductSystem} class.
     * @throws EmptyListException if productSystemData object is empty
     */
    @Test
    public void test015_VerifyModifications() throws EmptyListException {

        ProductSystem productSystemInstance = new ProductSystem(productSystemData, 5, 10);

        ProductSystemData newproductSystemData = new ProductSystemData();

        productSystemInstance.setProductSystemData(newproductSystemData);

        assertEquals(newproductSystemData, productSystemInstance.getProductSystemData());
    }


}
