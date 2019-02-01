package ftc.shift.sample.api;

import ftc.shift.sample.models.User;
import ftc.shift.sample.models.Offer;
import ftc.shift.sample.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OffersController {

    private static final String OFFER_PATH = "/api/offers";

    @Autowired
    private OfferService service;

    @GetMapping(OFFER_PATH + "/{id}")
    public ResponseEntity<Offer> readOffer(@PathVariable String id) {
        Offer offer = service.provideOffer(id);

        if (null == offer) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(offer);
        }
    }

    @GetMapping(OFFER_PATH)
    public ResponseEntity<Collection<Offer>> listOffers() {
        List<Offer> offers = service.provideOffers();
        return ResponseEntity.ok(offers);
    }

    @PostMapping(OFFER_PATH)
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer result = service.createOffer(offer);
        if(result == null){
            return ResponseEntity.badRequest().build();
        }
        else {
            return ResponseEntity.ok(result);
        }
    }

    @PatchMapping(OFFER_PATH + "/{id}/accept")
    public ResponseEntity<?> acceptOffer(@PathVariable String id, @RequestBody User user){
        Integer result = service.acceptOffer(id, user);
        if (result == 0){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(OFFER_PATH + "/{id}/delete")
    public ResponseEntity<?> deleteOffer(@PathVariable String id, @RequestParam (required = true) String ownerId) {
        Offer offer = service.provideOffer(id);
        if (offer.getUserid().equals(ownerId)) {
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
