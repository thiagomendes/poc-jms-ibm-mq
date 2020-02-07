package com.test.pocjmsibmmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class IbmJmsListener {

    private static final Logger LOG = LoggerFactory.getLogger(IbmJmsListener.class);


    @JmsListener(destination = "DEV.QUEUE.1")
    public void onMessage(Message message) throws JMSException {
        LOG.info("Received MQ message. [{}]", message);

    }

}
