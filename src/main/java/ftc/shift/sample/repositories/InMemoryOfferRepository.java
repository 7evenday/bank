package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryOfferRepository implements OfferRepository {

    private Map<String, Offer> offerCache = new HashMap<>();

    private static Integer numOfOffers = 0;

    public InMemoryOfferRepository(){
        offerCache.put("0", new Offer("0", "0", "Petya", 250));
        offerCache.put("1", new Offer("0", "1", "Классный челик", 2500));
        numOfOffers += 2;
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
        offer.setId(String.valueOf(numOfOffers));
        numOfOffers++;
        offerCache.put(offer.getId(), offer);
        return offer;
    }

    @Override
    public void deleteOffer(String id){
        numOfOffers--;
        //User user = UserService.provideUser(offerCache.get(id).getUserid());
        //user.setBalance(user.getBalance() - offerCache.get(id).getSum);
        offerCache.remove(id);
    }

    @Override
    public Collection<Offer> getAllOffers(){
        return offerCache.values();
    }
}
