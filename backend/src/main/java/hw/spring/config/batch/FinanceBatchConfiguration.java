package hw.spring.config.batch;

import hw.spring.model.Finance;
import hw.spring.model.activity.Activity;
import hw.spring.model.repositories.FinanceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.kafka.KafkaItemReader;
import org.springframework.batch.item.kafka.builder.KafkaItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.UUID;

import static java.time.Duration.ofMillis;
import static java.util.Map.of;
import static org.apache.commons.collections4.MapUtils.toProperties;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class FinanceBatchConfiguration {

    private final FinanceRepository financeRepository;

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${topic.name.check-in}")
    private String checkInTopicName;

    @Value("${topic.name.check-out}")
    private String checkOutTopicName;

    @Value("${topic.groupId}")
    private String checkGroupId;

    @Bean
    public RepositoryItemWriter<Finance> financeWriter() {
        return new RepositoryItemWriterBuilder<Finance>()
                .repository(financeRepository)
                .methodName("save")
                .build();
    }

    @Bean
    @Qualifier("check-in")
    public Job checkInJob(JobRepository jobRepository, @Qualifier("check-in") Step step) {
        return new JobBuilder("checkInJob", jobRepository)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    @Qualifier("check-out")
    public Job checkOutJob(JobRepository jobRepository, @Qualifier("check-out") Step step) {
        return new JobBuilder("checkOutJob", jobRepository)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    @Qualifier("check-in")
    public KafkaItemReader<String, Activity> financeCheckInReader() {
        return commonKafkaItemReaderBuilder()
                .name("check-in-reader")
                .topic(checkInTopicName)
                .build();
    }

    private KafkaItemReaderBuilder<String, Activity> commonKafkaItemReaderBuilder() {
        return new KafkaItemReaderBuilder<String, Activity>()
                .consumerProperties(toProperties(of(
                        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress,
                        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                        ConsumerConfig.GROUP_ID_CONFIG, checkGroupId,
                        ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10000,
                        ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, ("SchedulerCoordinator-" + UUID.randomUUID()),
                        ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3333,
                        ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 7000,
                        ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 2
                )))
                .partitions(0)
                .pollTimeout(ofMillis(600));
    }

    @Bean
    @Qualifier("check-in")
    public Step checkInStep(JobRepository jobRepository, PlatformTransactionManager manager, @Qualifier("check-in") KafkaItemReader<String, Activity> reader,
                            @Qualifier("check-in") ItemProcessor<Activity, Finance> financeCheckInProcessor, RepositoryItemWriter<Finance> writer) {
        return new StepBuilder("checkInStep", jobRepository)
                .<Activity, Finance>chunk(10, manager)
                .reader(reader)
                .processor(financeCheckInProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    @Qualifier("check-out")
    public KafkaItemReader<String, Activity> financeCheckOutReader() {
        return commonKafkaItemReaderBuilder()
                .name("check-out-reader")
                .topic(checkOutTopicName)
                .build();
    }

    @Bean
    @Qualifier("check-out")
    public Step checkOutStep(JobRepository jobRepository, PlatformTransactionManager manager, @Qualifier("check-out") KafkaItemReader<String, Activity> reader,
                             @Qualifier("check-out") ItemProcessor<Activity, Finance> financeCheckOutProcessor, RepositoryItemWriter<Finance> writer) {
        return new StepBuilder("checkOutStep", jobRepository)
                .<Activity, Finance>chunk(10, manager)
                .reader(reader)
                .processor(financeCheckOutProcessor)
                .writer(writer)
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}

