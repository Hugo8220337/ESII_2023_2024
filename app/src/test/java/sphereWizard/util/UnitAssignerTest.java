package sphereWizard.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sphereWizard.Enums.UnitEnums.*;
import sphereWizard.Exceptions.UnitNotRecognizedException;
import sphereWizard.Util.UnitAssigner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Unit tests for the {@link UnitAssigner} class.
 *
 * This test class is designed to validate the behavior of the {@link UnitAssigner} class methods.
 *
 * @author Group 22
 * @version 1.0
 */
public class UnitAssignerTest {

    // Instance of the UnitAssigner class to be used in tests
    private static UnitAssigner unitAssigner;

    /**
     * Initializes the {@link UnitAssigner} instance before running the tests.
     */
    @BeforeAll
    public static void init() {
        unitAssigner = new UnitAssigner();
    }

    /**
     * Test if {@link UnitAssigner#assignEnum(String)} assigns the correct enum for a valid unit.
     *
     * @throws UnitNotRecognizedException If the unit is not recognized.
     */
    @Test
    public void test001AssignEnumWithValidUnit() throws UnitNotRecognizedException {
        Enum<?> assignEnum = unitAssigner.assignEnum("G");
        assertEquals(Mass.G, assignEnum);
    }

    /**
     * Test if {@link UnitAssigner#assignEnum(String)} throws an exception for an invalid unit.
     */
    @Test
    public void test002AssignEnumWithInvalidUnit() {
        assertThrows(UnitNotRecognizedException.class, () -> unitAssigner.assignEnum("InvalidUnit"));
    }
}
