package emt.proekt.eshop.ordermanagement.application;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import emt.proekt.eshop.ordermanagement.domain.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${stripe_secret_key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public String charge(Order order) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int) order.getTotalPrice().getPrice() * 100);
        chargeParams.put("currency", order.getTotalPrice().getCurrency());
        chargeParams.put("source", order.getTokenId());

        Charge charge = Charge.create(chargeParams);
        return charge.getId();
    }
}
