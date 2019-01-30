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
            offer.setId(String.valueOf(UUID.randomUUID()));
            offerCache.put(offer.getId(), offer);
            return offer;
        }
        else {
        return null;
        }
    }

    @Override
    public void deleteOffer(String id, User userReciever){
        User userGiver = UserService.provideUser(offerCache.get(id).getUserid());
        if (userGiver.getBalance() >= offerCache.get(id).getSum()) {
            userReciever.setBalance(userReciever.getBalance() + offerCache.get(id).getSum());
            userReciever.setDebt((int) (userReciever.getDebt() + 1.1*offerCache.get(id).getSum()));
            userGiver.setBalance(userGiver.getBalance() - offerCache.get(id).getSum());
            offerCache.remove(id);
        }
    }

    @Override
    public List<Offer> getAllOffers(){
        List<Offer> offers = new ArrayList<>(offerCache.values());
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
