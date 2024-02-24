package sphereWizard.Interfaces;

import sphereWizard.Exceptions.EmptyListException;
import sphereWizard.FlowProcessStructure.Process;
import sphereWizard.ProductSystem.ProductSystemData;

import java.util.List;

public interface    SendFilteredData {


    public List<Process> sendByCategory(String category) throws EmptyListException;

    public List<Process> sendByName(String name) throws EmptyListException ;

    public ProductSystemData sendAll() throws EmptyListException ;
}
