package ftc.shift.sample.repositories;

import ftc.shift.sample.models.User;
import ftc.shift.sample.models.Offer;

import java.util.*;

public interface OfferRepository {

    Offer fetchOffer(String id);

    void deleteOffer(String id, User user);

    Offer createOffer(Offer offer);

    List<Offer> getAllOffers();

}
