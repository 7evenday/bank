package ftc.shift.sample.api;


import ftc.shift.sample.models.Offer;
import ftc.shift.sample.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
        Collection<Offer> offers = service.provideOffers();
        return ResponseEntity.ok(offers);
    }

    @PostMapping(OFFER_PATH)
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Offer result = service.createOffer(offer);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(OFFER_PATH + "/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable String id) {
        service.deleteOffer(id);
        return ResponseEntity.ok().build(); //нет тела, только статус
    }

    @PatchMapping(OFFER_PATH + "/{id}")
    public ResponseEntity<Offer> updateBook(@PathVariable String id, @RequestBody Offer offer) {
        Offer result = service.updateBook(offer);
        return ResponseEntity.ok(result);
    }
}