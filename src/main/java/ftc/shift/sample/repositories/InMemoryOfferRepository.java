package ftc.shift.sample.repositories;

import ftc.shift.sample.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryOfferRepository implements OfferRepository {

    private Map<String, Offer> offerCache = new HashMap<>();

    public InMemoryOfferRepository(){
        offerCache.put("1", new Offer("1", "Petya", 2500));
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
        offer.setId(String.valueOf(System.currentTimeMillis()));
        offerCache.put(offer.getId(), offer);
        return offer;
    }

    @Override
    public void deleteOffer(String id){
        offerCache.remove(id);
    }

    @Override
    public Collection<Offer> getAllOffers(){
        return offerCache.values();
    }
}
