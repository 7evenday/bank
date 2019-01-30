package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Offer;

import java.util.*;

public interface OfferRepository {

    Offer fetchOffer(String id);

    Offer updateOffer(Offer offer);

    void deleteOffer(String id);

    Offer createOffer(Offer offer);

    List<Offer> getAllOffers();

}
