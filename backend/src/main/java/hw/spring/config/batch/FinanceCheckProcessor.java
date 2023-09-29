package hw.spring.config.batch;

import hw.spring.model.Finance;
import hw.spring.model.activity.Activity;
import hw.spring.model.repositories.FinanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.function.BinaryOperator;

@Component
@AllArgsConstructor
class FinanceCheckProcessor {
    private static final String REVENUE_NAME = "revenue";
    private final FinanceRepository financeRepository;

    Finance process(Activity activity, BinaryOperator<BigDecimal> operation) {
        var currentRevenue = financeRepository.findFirstByName(REVENUE_NAME).orElse(
                new Finance(1, REVENUE_NAME, BigDecimal.ZERO)
        );
        var newRevenue = currentRevenue.copy();
        newRevenue.setRevenue(operation.apply(newRevenue.getRevenue(), activity.getPrice()));
        return newRevenue;
    }
}
