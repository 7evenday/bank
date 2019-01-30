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
    public Offer updateOffer(Offer offer){
        offerCache.put(offer.getId(), offer);
        return offer;
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
    public void deleteOffer(String id){
        User user = UserService.provideUser(offerCache.get(id).getUserid());
        if (user.getBalance() >= offerCache.get(id).getSum()) {
            user.setBalance(user.getBalance() - offerCache.get(id).getSum());
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
