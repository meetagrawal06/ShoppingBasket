package org.shopping.basket.type;

import org.shopping.basket.dto.Offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.shopping.basket.type.DiscountType.BUY_ONE_GET_ONE_FREE;
import static org.shopping.basket.type.DiscountType.THREE_FOR_TWO;
import static org.shopping.basket.type.ProductType.LIME;
import static org.shopping.basket.type.ProductType.MELON;

public class OfferMap {
    private static OfferMap INSTANCE = null;
    private List<Offer> offers;

    private OfferMap() {
        offers = new ArrayList<>();
        offers.add(new Offer(MELON, BUY_ONE_GET_ONE_FREE));
        offers.add(new Offer(LIME, THREE_FOR_TWO));
        offers = Collections.unmodifiableList(offers);
    }

    public static OfferMap getInstance() {
        if (OfferMap.INSTANCE == null) {
            INSTANCE = new OfferMap();
        }
        return INSTANCE;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
