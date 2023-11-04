package hw.spring.config.batch;

import hw.spring.model.Finance;
import hw.spring.model.activity.Activity;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("dev")
@RequiredArgsConstructor
@Qualifier("check-in")
public class FinanceCheckInProcessor implements ItemProcessor<Activity, Finance> {

    private final FinanceCheckProcessor financeCheckProcessor;

    @Override
    public Finance process(@NonNull Activity item) throws Exception {
        return financeCheckProcessor.process(item, BigDecimal::add);
    }
}
