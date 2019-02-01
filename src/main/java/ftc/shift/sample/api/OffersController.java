package ftc.shift.sample.api;

import ftc.shift.sample.models.User;
import ftc.shift.sample.models.Offer;
import ftc.shift.sample.services.OfferService;
import ftc.shift.sample.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class OffersController {

    private static final String OFFER_PATH = "/api/offers";

    @Autowired
    private OfferService service;

    @GetMapping(OFFER_PATH + "/{id}")
    public ResponseEntity<Offer> readOffer(@PathVariable String id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "GET "+OFFER_PATH + "/" + id);
        Offer offer = service.provideOffer(id);

        if (null == offer) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(offer);
        }
    }

    @GetMapping(OFFER_PATH)
    public ResponseEntity<Collection<Offer>> listOffers() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "GET" + OFFER_PATH);
        List<Offer> offers = service.provideOffers();
        return ResponseEntity.ok(offers);
    }

    @PostMapping(OFFER_PATH)
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "POST " + OFFER_PATH);
        Offer result = service.createOffer(offer);
        if(result == null){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(result);
        }
    }

    @PatchMapping(OFFER_PATH + "/{id}/accept_optional")
    public ResponseEntity<?> acceptOffer(@PathVariable String id, @RequestBody User user){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "PATCH " + OFFER_PATH + "/" + id + "/accept" + "by" + user.getName());
        if (service.provideOffer(id).getUserid().equals(user.getId())) {
            return ResponseEntity.badRequest().build();
        } else {
            Integer result = service.acceptOffer(id, user);
            if (result == 0) {
                    return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        }
    }

    @PatchMapping(OFFER_PATH + "/{id}/accept")
    public ResponseEntity<?> acceptOfferByID(@PathVariable String id, @RequestBody(required = false) User user, @RequestParam (required = false) String userid){
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        //System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime())) + " : " + "PATCH " + OFFER_PATH + "/" + id + "/accept" + "by" + user.getName());
        if ((user.getId() != userid) & (userid != null) & (user != null)) {
            return ResponseEntity.badRequest().build();
        }
        if (user == null) {
            if (userid != null) {
                return acceptOffer(id, UserService.provideUser(userid));
            }
            else{
                return ResponseEntity.badRequest().build();
            }
        }
        else {
            return acceptOffer(id, user);
        }
    }

    @DeleteMapping(OFFER_PATH + "/{id}/delete")
    public ResponseEntity<?> deleteOffer(@PathVariable String id, @RequestParam (required = true) String userid) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        System.out.println(request.getRemoteAddr() + " :: " + (new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())) + " : " + "DELETE " + OFFER_PATH + "/" + id + "/delete" + "by" + UserService.provideUser(userid).getName());
        Offer offer = service.provideOffer(id);
        if (offer.getUserid().equals(userid)) {
            service.deleteOffer(id);
            return ResponseEntity.ok().build();
        }
        else
        {
            return ResponseEntity.badRequest().build();
        }
         //нет тела, только статус
    }
}
