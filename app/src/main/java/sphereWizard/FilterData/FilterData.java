package sphereWizard.FilterData;

import sphereWizard.Exceptions.EmptyListException;
import sphereWizard.FlowProcessStructure.Process;
import sphereWizard.Interfaces.SendFilteredData;
import sphereWizard.Interfaces.sendFileData;
import sphereWizard.ProductSystem.ProductSystemData;

import java.util.ArrayList;
import java.util.List;

public class FilterData implements SendFilteredData {


    sendFileData psd;

    public FilterData(){}
    public FilterData(sendFileData psd){
        this.psd = psd.getProductSystemData();
    }



    /**
     *  creates a new arraylist, iterates through the entire
     *  processes in productsystem and adds the processes with the desired category into the new arraylist.
     *
     * @param category     The category of the desired processes.
     * @return    an arrayList with the new productSystem model that only contains the processes with the define category.
     */

    @Override
    public List<Process> sendByCategory(String category) throws EmptyListException { // envia apenas os processos com categoria Y

        List<Process> newListToSend = new ArrayList<>();

        for(Process process : psd.getProductSystemData().getProcesses()){

            if(process.getCategory().equals(category)){
                newListToSend.add(process);
            }
        }

        if(newListToSend.size() == 0){
            throw new EmptyListException("thine list is empty");
        }
        return newListToSend;

    }


    /**
     *  creates a new arraylist, iterates through the entire
     *  processes in productsystem and adds the processes with the desired name into the new arraylist.
     *
     * @param name     The name of the desired process(es).
     * @return an arrayList with all the processes with the desired name.
     */
    @Override
    public List<Process> sendByName(String name) throws EmptyListException { //envio apenas processo com o  nome X

        List<Process> newListToSend = new ArrayList<>();

        for(Process process : psd.getProductSystemData().getProcesses()){

            if(process.getName().equals(name)){
                newListToSend.add(process);
            }
        }

        if(newListToSend.size() == 0){
            throw new EmptyListException("thine list is empty");
        }

        return newListToSend;
    }

    /**
     * returns the entire productSystem object.
     *
     * @return the product system object.
     */
    @Override
    public ProductSystemData sendAll() throws EmptyListException  { // envio toda a lista de processos

        if(psd.getProductSystemData().getProcesses().isEmpty()){
            throw new EmptyListException("thine list is empty");
        }

        return psd.getProductSystemData();
    }
}
