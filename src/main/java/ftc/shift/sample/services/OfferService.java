package ftc.shift.sample.services;

import ftc.shift.sample.models.TransferClient;
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

    public void deleteOffer(String id) {
        //UserService.provideUser(offerRepository.fetchOffer(id))
        offerRepository.deleteOffer(id);
    }

    public Integer acceptOffer(String id, User userRecieve) {
        Offer offer = offerRepository.fetchOffer(id);
        if (!offer.getIsAccepted()) {
            User userReciever = UserService.provideUser(userRecieve.getId());
            User userGiver = UserService.provideUser(offer.getUserid());
            userReciever.setBalance(offer.getSum() + userReciever.getBalance());
            userReciever.setDebt(offer.getSum() + userReciever.getDebt());
            //userGiver.setBalance(userGiver.getBalance() - offer.getSum());
            offer.setIsAccepted(true);
            TransferService.createTransfer(new TransferClient(userGiver.getId(), userReciever.getId(), offer.getSum()));
            return 0;
        }
        else{
            return -1;
        }
    }

    public Offer createOffer (Offer offer) {
        User userGiver = UserService.provideUser(offer.getUserid());
        if (userGiver == null){
            return null;
        }
        else {
            if((userGiver.getBalance() >= offer.getSum()) & (offer.getSum() >= 0)){
                offer.setUsername(userGiver.getName());
                offer.setIsAccepted(false);
                UserService.provideUser(offer.getUserid()).setBalance(UserService.provideUser(offer.getUserid()).getBalance() - offer.getSum());
                offerRepository.createOffer(offer);
                return offer;
            }
        }
        return null;
    }

    public List<Offer> provideOffers() {
        return offerRepository.getAllOffers();
    }

}