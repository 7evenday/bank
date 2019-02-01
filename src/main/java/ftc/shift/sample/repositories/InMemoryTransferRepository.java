package ftc.shift.sample.repositories;

import ftc.shift.sample.models.TransferServer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryTransferRepository implements TransferRepository{

    private Map<String, TransferServer> transferServerCache = new HashMap<>();

    @Override
    public TransferServer fetchTransfer(String id){
        return transferServerCache.get(id);
    }

    @Override
    public TransferServer createTransfer(TransferServer transferServer){
        transferServerCache.put(transferServer.getId(), transferServer);
        return transferServer;
    }

    @Override
    public List<TransferServer> getAllTransfers(String id, String type){
        List<TransferServer> transfers = new ArrayList<>(transferServerCache.values());
        TransferServer transfer;
        int index = 0;
        if (id == null) {
            return transfers;
        }else if (type.equals("all")) {
            while (index < transfers.size()) {
                transfer = transfers.get(index);
                if (!(transfer.getUserGiverId().equals(id) | (transfer.getUserRecieverId().equals(id)))) {
                    transfers.remove(index);
                }
                else {
                    index++;
                }
                }
            return transfers;
        }
        else if (type.equals("debts")){
            while (index < transfers.size()) {
                transfer = transfers.get(index);
                if (!(transfer.getUserRecieverId().equals(id))) {
                    transfers.remove(index);
                }
                else {
                    index++;
                }
            }
            return transfers;
        }
        else if (type.equals("credits")){
            while (index < transfers.size()) {
                transfer = transfers.get(index);
                if (!(transfer.getUserGiverId().equals(id))) {
                    transfers.remove(index);
                }
                else{
                    index++;
                }
            }
            return transfers;
        }
        return transfers;
    }
}
