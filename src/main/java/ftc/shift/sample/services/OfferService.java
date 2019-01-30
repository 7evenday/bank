package ftc.shift.sample.services;

import ftc.shift.sample.models.Offer;
import ftc.shift.sample.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OfferService {

    private static OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository _offerRepository) {
        offerRepository = _offerRepository;
    }

    public Offer provideOffer(String id) {
        return offerRepository.fetchOffer(id);
    }

    public void deleteOffer(String id) {
        offerRepository.deleteOffer(id);
    }


    public Offer createOffer (Offer offer) {
        offerRepository.createOffer(offer);
        return offer;
    }

    public List<Offer> provideOffers() {
        return offerRepository.getAllOffers();
    }

}