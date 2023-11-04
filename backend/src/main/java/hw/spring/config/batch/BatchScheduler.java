package hw.spring.config.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@Profile("dev")
@EnableScheduling
@Slf4j
public class BatchScheduler {

    private final JobLauncher jobLauncher;
    private final Job financeCheckInJob;
    private final Job financeCheckOutJob;

    public BatchScheduler(JobLauncher jobLauncher, @Qualifier("check-in") Job financeCheckInJob, @Qualifier("check-out") Job financeCheckOutJob) {
        this.jobLauncher = jobLauncher;
        this.financeCheckInJob = financeCheckInJob;
        this.financeCheckOutJob = financeCheckOutJob;
    }

    @Scheduled(fixedRate = 10_000)
    public void launchFinanceJob() throws Exception {
        Date date = new Date();

        var checkInJobExecution = jobLauncher.run(financeCheckInJob, new JobParametersBuilder().addDate("launchDate", date).toJobParameters());
        var checkOutJobExecution = jobLauncher.run(financeCheckOutJob, new JobParametersBuilder().addDate("launchDate", date).toJobParameters());

        log.info("check-in job status " + checkInJobExecution.getStatus());
        log.info("check-out job status " + checkOutJobExecution.getStatus());
    }
}
