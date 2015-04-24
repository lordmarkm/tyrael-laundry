package com.tyrael.laundry.messaging.service;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class MessagingService {

    private static Logger LOG = LoggerFactory.getLogger(MessagingService.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final Serializable payload, String channel) {
        LOG.debug("Sending message. channel={}, payload={}", channel, payload);

        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(payload);
            }
        };

        jmsTemplate.send(channel, messageCreator);
    }

}
