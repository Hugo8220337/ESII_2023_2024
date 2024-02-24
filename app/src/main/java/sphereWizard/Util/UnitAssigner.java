package sphereWizard.Util;

import sphereWizard.Enums.UnitEnums.*;
import sphereWizard.Exceptions.UnitNotRecognizedException;

public class UnitAssigner {

    public UnitAssigner() {
        // Empty Constructor
    }

    /**
     * checks if it is an area measurement unit
     * @param unit unit to be verified
     * @return true if it is an area measurement, false otherwise
     */
    private boolean isArea(String unit){
        return assignAreaEnum(unit) != null;
    }

    /**
     * checks if it is a mass measurement unit
     * @param unit unit to be verified
     * @return true if it is a mass measurement, false otherwise
     */
    private boolean isMass(String unit){
        return assignMassEnum(unit) != null;
    }

    /**
     * checks if it is a volume measurement unit
     * @param unit unit to be verified
     * @return true if it is a volume measurement, false otherwise
     */
    private boolean isVolume(String unit){
        return assignVolumeEnum(unit) != null;
    }

    /**
     * checks if it is an energy measurement unit
     * @param unit unit to be verified
     * @return true if it is an energy measurement, false otherwise
     */
    private boolean isEnergy(String unit){
        return assignEnergyEnum(unit) != null;
    }

    /**
     * checks if it is a distance measurement unit
     * @param unit unit to be verified
     * @return true if it is a distance measurement, false otherwise
     */
    private boolean isDistance(String unit){
        return assignDistanceEnum(unit) != null;
    }

    /**
     * Assigns an enum of the Area type
     * @param unit the string containing the type of unit
     * @return the enum value corresponding
     */
    private Area assignAreaEnum(String unit) {
        Area[] areaUnits = Area.values();
        for (Area value : areaUnits) {
            if (unit.equalsIgnoreCase(value.toString())) {
                return value;
            }
        }
        return null;
    }

    /**
     * Assigns an enum of the Mass type
     * @param unit the string containing the type of unit
     * @return the enum value corresponding
     */
    private Mass assignMassEnum(String unit) {
        Mass[] massUnits = Mass.values();
        for (Mass value : massUnits) {
            if (unit.equalsIgnoreCase(value.toString())) {
                return value;
            }
        }
        return null;
    }

    /**
     * Assigns an enum of the volume type
     * @param unit the string containing the type of unit
     * @return the enum value corresponding
     */
    private Volume assignVolumeEnum(String unit) {
        Volume[] volumeUnits = Volume.values();
        for (Volume value : volumeUnits) {
            if (unit.equalsIgnoreCase(value.toString())) {
                return value;
            }
        }
        return null;
    }

    /**
     * Assigns an enum of the Energy type
     * @param unit the string containing the type of unit
     * @return the enum value corresponding
     */
    private Energy assignEnergyEnum(String unit) {
        Energy[] energyUnits = Energy.values();
        for (Energy value : energyUnits) {
            if (unit.equalsIgnoreCase(value.toString())) {
                return value;
            }
        }
        return null;
    }

    /**
     * Assigns an enum of the Distance type
     * @param unit the string containing the type of unit
     * @return the enum value corresponding
     */
    private Distance assignDistanceEnum(String unit) {
        Distance[] distanceUnits = Distance.values();
        for (Distance value : distanceUnits) {
            if (unit.equalsIgnoreCase(value.toString())) {
                return value;
            }
        }
        return null;
    }

    /**
     * Assigns an enum corresponding to the data type
     * @param unit string containing the unit of measurement
     * @return the correct enum to assign
     * @throws UnitNotRecognizedException when a unit isn't registered
     */
    public Enum<?> assignEnum(String unit) throws UnitNotRecognizedException{
        Enum<?> assignEnum = null;
        if(isArea(unit)) {
            assignEnum = assignAreaEnum(unit);
        }
        else if(isEnergy(unit)) {
            assignEnum = assignEnergyEnum(unit);
        }
        else if(isVolume(unit)) {
            assignEnum = assignVolumeEnum(unit);
        }
        else if(isMass(unit)) {
            assignEnum = assignMassEnum(unit);
        }
        else if(isDistance(unit)) {
            assignEnum = assignDistanceEnum(unit);
        }
        else {
            throw new UnitNotRecognizedException("value " + unit + "is not registered; please refer to the user manual.");
        }

        return assignEnum;
    }
}