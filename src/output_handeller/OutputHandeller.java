package output_handeller;

import data_structures.ServiceResult;

public interface OutputHandeller {

    public void displayResult(ServiceResult sequence, String alogrithmName);

    public void displayUnSupportedAlgorithm(String algorithmName);
}
