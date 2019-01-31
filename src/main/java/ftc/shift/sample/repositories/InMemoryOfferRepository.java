package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Offer;
import ftc.shift.sample.models.User;
import ftc.shift.sample.services.UserService;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class InMemoryOfferRepository implements OfferRepository {

    private Map<String, Offer> offerCache = new HashMap<>();

    public InMemoryOfferRepository(){
    }

    @Override
    public Offer fetchOffer(String id) {
        return offerCache.get(id);
    }

    @Override
    public Offer createOffer(Offer offer){
        if (UserService.provideUser(offer.getUserid()).getBalance() >= offer.getSum()) {
            offer.setUsername(UserService.provideUser(offer.getUserid()).getName());
            offer.setId(String.valueOf(UUID.randomUUID()));
            offer.setIsAccepted(false);
            offerCache.put(offer.getId(), offer);
            return offer;
        }
        else {
        return null;
        }
    }

    @Override
    public void deleteOffer(String id, User userReciever){
        Offer offer = offerCache.get(id);
        offer.setIsAccepted(true);
        offerCache.put(offer.getId(), offer);
    }

    @Override
    public List<Offer> getAllOffers(){
        List<Offer> offers = new ArrayList<>(offerCache.values());
        int index = 0;
        Offer offer;
        while (index < offers.size()) {
            offer = offers.get(index);
            if (!offer.getIsAccepted()){
                offers.remove(index);
            }
        }
        offers.sort(new Comparator<Offer>(){
            @Override
            public int compare(Offer t1, Offer t2) {
                Integer sum1 = t1.getSum();
                Integer sum2 = t2.getSum();
                if (sum1 > sum2) {
                    return 1;
                }
                else if (sum1 < sum2) {
                    return -1;
                }
                else{
                    return 0;
                }
            }
        });
        return offers;
    }
}
