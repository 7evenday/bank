package ftc.shift.sample.api;

import java.util.*;
import ftc.shift.sample.models.TransferServer;
import ftc.shift.sample.models.User;
import ftc.shift.sample.models.Offer;
import ftc.shift.sample.*;
import ftc.shift.sample.services.OfferService;
import ftc.shift.sample.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferController {

    private static final String OFFER_PATH = "/api/transfers";

    @Autowired
    private TransferService service;

    @GetMapping(OFFER_PATH + "/{userid}")
    public ResponseEntity<List<TransferServer>> listTransfers(@PathVariable String userid) {
        List<TransferServer> transfers = service.provideTransfers(userid);
        return ResponseEntity.ok(transfers);
    }

    @GetMapping(OFFER_PATH)
    public ResponseEntity<List<TransferServer>> listTransfers() {
        List<TransferServer> transfers = service.provideTransfers(null);
        return ResponseEntity.ok(transfers);
    }
}
