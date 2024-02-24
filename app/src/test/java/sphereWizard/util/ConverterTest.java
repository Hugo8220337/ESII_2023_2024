package sphereWizard.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sphereWizard.Enums.UnitEnums.*;
import sphereWizard.FlowProcessStructure.Flow;
import sphereWizard.FlowProcessStructure.InputFlow;
import sphereWizard.Util.Converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link Converter} class.
 *
 * This test class is designed to validate the behavior of the methods in the {@link Converter} class.
 *
 * @author Group 22
 * @version 1.0
 */
public class ConverterTest {

    // Instance of the Converter class to be used in tests
    private static Converter converter;

    /**
     * Initializes the {@link Converter} instance before running the tests.
     */
    @BeforeAll
    public static void init() {
        converter = new Converter();
    }

    /**
     * Test conversion with a mass unit.
     */
    @Test
    public void test001ConvertWithMassUnit() {
        Flow flow = new InputFlow("InputName", "Category", 1000, Mass.G);

        converter.convert(flow);

        assertEquals(1, flow.getValue(), 0.001);
        assertEquals(Mass.KG, flow.getUnit());
    }

    /**
     * Test conversion with an area unit.
     */
    @Test
    public void test002ConvertWithAreaUnit() {

        Flow flow = new InputFlow("InputName", "Category", 1000, Area.DM2);

        converter.convert(flow);

        assertEquals(10, flow.getValue(), 0.001);
        assertEquals(Area.M2, flow.getUnit());
    }

    /**
     * Test conversion with a volume unit.
     */
    @Test
    public void test003ConvertWithVolumeUnit() {

        Flow flow = new InputFlow("InputName", "Category", 1000, Volume.L);

        converter.convert(flow);

        assertEquals(1, flow.getValue(), 0.001);
        assertEquals(Volume.M3, flow.getUnit());
    }

    /**
     * Test conversion with an energy unit.
     */
    @Test
    public void test004ConvertWithEnergyUnit() {

        Flow flow = new InputFlow("InputName", "Category", 1, Energy.MJ);

        converter.convert(flow);

        assertEquals(1000000, flow.getValue(), 0.001);
        assertEquals(Energy.J, flow.getUnit());
    }

    /**
     * Test conversion with a distance unit.
     */
    @Test
    public void test005ConvertWithDistanceUnit() {

        Flow flow = new InputFlow("InputName", "Category", 1000, Distance.CM);

        converter.convert(flow);

        assertEquals(10, flow.getValue(), 0.001);
        assertEquals(Distance.M, flow.getUnit());
    }
}
