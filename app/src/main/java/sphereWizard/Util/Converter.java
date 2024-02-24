package sphereWizard.Util;

import sphereWizard.Enums.UnitEnums.*;
import sphereWizard.Exceptions.ImpossibleConversionException;
import sphereWizard.FlowProcessStructure.Flow;

/**
 * This class acts as a converter for the units and values presents in the file.
 */
public class Converter {

    public Converter() {
        // Empty constructor
    }

    /**
     * This method will apply conversion logic to the value and unit of a flow,
     * in accordance to the type of unit (mass, volume, area, etc)
     * @param flow flow to standardize
     */


    public void convert(Flow flow) {
        Enum<?> unit = flow.getUnit();

        switch (unit.getDeclaringClass().getSimpleName()) {
            case "Mass":
                standardizeMass(flow);
                break;
            case "Energy":
                standardizeEnergy(flow);
                break;
            case "Volume":
                standardizeVolume(flow);
                break;
            case "Distance":
                standardizeDistance(flow);
                break;
            case "Area":
                standardizeArea(flow);
                break;
            default:
                throw new ImpossibleConversionException("There's been an error in conversion");
        }
    }

    /**
     * Standardizes the unit of mass, converting it to KG (the SI unit in
     * mass calculations)
     * @param flow the flow to be standardized
     */
    private void standardizeMass(Flow flow) {
        switch ((Mass) flow.getUnit()) {
            case G:
                flow.setValue(flow.getValue() / 1000);
                break;
            case T:
                flow.setValue(flow.getValue() * 1000);
                break;
            case MG:
                flow.setValue(flow.getValue() / 1000000);
                break;
            case KG:
                flow.setValue(flow.getValue());
            default:
                break;
        }
        flow.setUnit(Mass.KG);
    }

    /**
     * Standardizes the unit of area, converting it to m2 (square meters) (the SI unit in
     * area calculations)
     * @param flow the flow to be standardized
     */
    private void standardizeArea(Flow flow) {
        switch ((Area) flow.getUnit()) {

            case DM2:
                flow.setValue(flow.getValue() / 100);
                break;
            case HM2:
                flow.setValue(flow.getValue() * 10000);
                break;
            case DAM2:
                flow.setValue(flow.getValue() * 100);
                break;
            case CM2:
                flow.setValue(flow.getValue() / 10000);
                break;
            case KM2:
                flow.setValue(flow.getValue() * 1000000);
                break;
            case MM2:
                flow.setValue(flow.getValue() / 1000000);
                break;
            default:
                break;
        }
        flow.setUnit(Area.M2);
    }

    /**
     * Standardizes the unit of volume, converting it to m3 (cubic meters) (the SI unit in
     * volume calculations)
     * @param flow the flow to be standardized
     */
    private void standardizeVolume(Flow flow) {
        switch ((Volume) flow.getUnit()) {
            case L:
                flow.setValue(flow.getValue() / 1000);
                break;
            case CM3:
                flow.setValue(flow.getValue() / 1000000);
                break;
            case DM3:
                flow.setValue(flow.getValue() / 1000);
                break;
            default:
                break;
        }
        flow.setUnit(Volume.M3);
    }

    /**
     * Standardizes the unit of energy, converting it to J (joule) (the SI unit in
     * energy calculations)
     * @param flow the flow to be standardized
     */
    private void standardizeEnergy(Flow flow) {
        switch ((Energy) flow.getUnit()) {
            case MJ:
                flow.setValue(flow.getValue() * 1000000);
                break;
            case KWH:
                flow.setValue(flow.getValue() * (3.6 * 1000000));
                break;
            default:
                break;
        }
        flow.setUnit(Energy.J);
    }

    /**
     * Standardizes the unit of distance, converting it to m (meters) (the SI unit used in
     * length/distance calculations)
     * @param flow the flow to be standardized
     */
    private void standardizeDistance(Flow flow) {
        switch ((Distance) flow.getUnit()) {
            case CM:
                flow.setValue(flow.getValue() / 100);
                break;
            case DM:
                flow.setValue(flow.getValue() / 10);
                break;
            case HM:
                flow.setValue(flow.getValue() * 100);
                break;
            case KM:
                flow.setValue(flow.getValue() * 1000);
                break;
            case MM:
                flow.setValue(flow.getValue() / 1000);
                break;
            case DAM:
                flow.setValue(flow.getValue() * 10);
                break;
            default:
                break;
        }
        flow.setUnit(Distance.M);
    }

}