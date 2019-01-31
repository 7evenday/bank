package ftc.shift.sample.services;

import ftc.shift.sample.models.User;
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

    public void deleteOffer(String id, User user) {
        offerRepository.deleteOffer(id, user);
    }

    public Integer acceptOffer(String id, User userReciever) {
        Offer offer = offerRepository.fetchOffer(id);
        User userGiver = UserService.provideUser(offer.getUserid());
        if (userGiver.getBalance() >= offer.getSum()) {
            userReciever.setBalance(offer.getSum() + userReciever.getBalance());
            userReciever.setDebt(offer.getSum() + userReciever.getDebt());
            userGiver.setBalance(userGiver.getBalance() - offer.getSum());
            offer.setIsAccepted(true);
            return 0;
        }
        else{
            return -1;
        }
    }

    public Offer createOffer (Offer offer) {
        offerRepository.createOffer(offer);
        return offer;
    }

    public List<Offer> provideOffers() {
        return offerRepository.getAllOffers();
    }

}