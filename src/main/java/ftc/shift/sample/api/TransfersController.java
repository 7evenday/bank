package ftc.shift.sample.api;

import java.text.SimpleDateFormat;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TransfersController {

    private static final String TRANSFER_PATH = "/api/transfers";

    @Autowired
    private TransferService service;

    @GetMapping(TRANSFER_PATH + "/{userid}")
    public ResponseEntity<List<TransferServer>> listTransfers(@PathVariable String userid) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "GET " + TRANSFER_PATH + "/" + userid);
        List<TransferServer> transfers = service.provideTransfers(userid, "all");
        return ResponseEntity.ok(transfers);
    }

    @GetMapping(TRANSFER_PATH + "/{userid}/debts")
    public ResponseEntity<List<TransferServer>> listDebts(@PathVariable String userid) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "GET " + TRANSFER_PATH + "/" + userid);
        List<TransferServer> transfers = service.provideTransfers(userid, "debts");
        return ResponseEntity.ok(transfers);
    }

    @GetMapping(TRANSFER_PATH + "/{userid}/credits")
    public ResponseEntity<List<TransferServer>> listDebit(@PathVariable String userid) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "GET " + TRANSFER_PATH + "/" + userid);
        List<TransferServer> transfers = service.provideTransfers(userid, "credits");
        return ResponseEntity.ok(transfers);
    }

    @GetMapping(TRANSFER_PATH)
    public ResponseEntity<List<TransferServer>> listAllTransfers() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "GET " + TRANSFER_PATH);
        List<TransferServer> transfers = service.provideTransfers(null, "all");
        return ResponseEntity.ok(transfers);
    }
}
