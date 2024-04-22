package com.hcl.bookingservice.exception;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

@Component
public class CustomErrorHandler extends DefaultErrorHandler
{
    private final Logger logger = LoggerFactory.getLogger(CustomErrorHandler.class);

    public CustomErrorHandler()
    {
        super();
    }

    public CustomErrorHandler(DeadLetterPublishingRecoverer deadLetterPublishingRecoverer, FixedBackOff fixedBackOff)
    {
        super(deadLetterPublishingRecoverer, fixedBackOff);
    }

    @Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container)
    {
        handle(thrownException, consumer, record);
        return true;
    }

    @Override
    public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener)
    {
        super.handleOtherException(thrownException, consumer, container, batchListener);
    }

    private void handle(Exception exception, Consumer<?, ?> consumer)
    {
        logger.error("Exception thrown", exception);
        if (exception instanceof RecordDeserializationException ex)
        {
            consumer.seek(ex.topicPartition(), ex.offset() + 1L);
            consumer.commitSync();
        } else
        {
            logger.error("Exception not handled", exception);
        }
    }

    private void handle(Exception exception, Consumer<?, ?> consumer, ConsumerRecord<?, ?> record)
    {
        logger.error("Exception thrown", exception);
        consumer.seek(new TopicPartition(record.topic(), record.partition()),record.offset() + 1L);
        logger.info("Record moved to DLT");
    }
}
