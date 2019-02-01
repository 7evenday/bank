package ftc.shift.sample.repositories;

import ftc.shift.sample.models.TransferServer;
import java.util.*;

public interface TransferRepository {
    TransferServer fetchTransfer(String id);

    TransferServer createTransfer(TransferServer transferServer);

    List<TransferServer> getAllTransfers(String id);
}
