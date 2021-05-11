package com.mickey.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.SimpleMessageConverter;


/**
 * @author J·K
 * @description: CustomJackson2JsonMessageConverter
 * @date: 2021/5/11 5:24 下午
 */
public class CustomJackson2JsonMessageConverter extends Jackson2JsonMessageConverter {

    /**
     * The Simple message converter.
     */
    private SimpleMessageConverter simpleMessageConverter;

    /**
     * Instantiates a new Custom jackson 2 json message converter.
     */
    public CustomJackson2JsonMessageConverter() {
        this.simpleMessageConverter = new SimpleMessageConverter();
    }

    /**
     * Message Conversion
     * @param message
     * @return
     * @throws MessageConversionException
     */
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        MessageProperties messageProperties = message.getMessageProperties();
        if (messageProperties != null) {
            String contentType = messageProperties.getContentType();
            if (contentType != null && contentType.contains("json")) {
                return super.fromMessage(message);
            } else if (contentType != null && contentType.startsWith("text")) {
                return simpleMessageConverter.fromMessage(message);
            }
        }
        return super.fromMessage(message);
    }
}
