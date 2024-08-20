package com.metaxiii.fr.springbatch.config.batch;

import com.metaxiii.fr.springbatch.config.batch.processor.DuplicatedPersonProcessor;
import com.metaxiii.fr.springbatch.entity.DuplicatedEntity;
import com.metaxiii.fr.springbatch.entity.PersonEntity;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class SimpleReadFromFileToDBBatchConfiguration {

  @Bean
  public Job importUserJob(final JobRepository jobRepository, final Step step) {
    return new JobBuilder("importUserJob", jobRepository).incrementer(new RunIdIncrementer()).start(step).build();
  }

  @Bean
  public DuplicatedPersonProcessor processor() {
    return new DuplicatedPersonProcessor();
  }

  @Bean
  public FlatFileItemReader<PersonEntity> readFromFile() {
    return new FlatFileItemReaderBuilder<PersonEntity>()
      .name("personItemReader")
      .resource(new ClassPathResource("sample-data.csv"))
      .delimited()
      .names("firstName", "lastName")
      .targetType(PersonEntity.class)
      .build();
  }

  @Bean
  public Step step(
    final JobRepository jobRepository,
    final DataSourceTransactionManager transactionManager,
    final FlatFileItemReader<PersonEntity> readFromFile,
    final DuplicatedPersonProcessor processor,
    final JdbcBatchItemWriter<DuplicatedEntity> writer
  ) {
    return new StepBuilder("step", jobRepository)
      .allowStartIfComplete(true)
      .<PersonEntity, DuplicatedEntity>chunk(3, transactionManager)
      .reader(readFromFile)
      .processor(processor)
      .writer(writer)
      .build();
  }

  @Bean
  public PlatformTransactionManager transactionManager(final DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public JdbcBatchItemWriter<DuplicatedEntity> writer(final DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<DuplicatedEntity>()
      .sql(
        "insert into duplicated_entity(id, first_name, last_name, is_active) values(nextval('duplicated_id_seq'), :firstName, :lastName, :active)"
      )
      .dataSource(dataSource)
      .beanMapped()
      .build();
  }
}
