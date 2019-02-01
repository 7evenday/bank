package ftc.shift.sample.services;

import ftc.shift.sample.*;
import ftc.shift.sample.repositories.TransferRepository;
import ftc.shift.sample.models.TransferClient;
import ftc.shift.sample.models.TransferServer;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private static TransferRepository transferRepository;

    @Autowired
    public TransferService(TransferRepository _transferRepository){
        transferRepository = _transferRepository;
    }

    public TransferServer provideTransfer(String id){
        return transferRepository.fetchTransfer(id);
    }
    public static void createTransfer(TransferClient transferClient){
        String id = UUID.randomUUID().toString();
        transferRepository.createTransfer(new TransferServer(id, transferClient));
    }
    public List<TransferServer> provideTransfers(String userId, String type){
        return transferRepository.getAllTransfers(userId, type);
    }

}
