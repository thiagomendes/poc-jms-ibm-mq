package com.test.pocjmsibmmq;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

@Configuration
public class IbmMqConfiguration {

    @Bean
    public ConnectionFactory connectionFactory() throws JMSException {
        MQConnectionFactory factory = new com.ibm.mq.jms.MQConnectionFactory();
        factory.setHostName("ibm-mq");
        factory.setPort(1414);
        factory.setQueueManager("QM1");
        factory.setChannel("DEV.ADMIN.SVRCONN");
        factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
        factory.setShareConvAllowed(1);

        UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
        connectionFactoryAdapter.setTargetConnectionFactory(factory);
        connectionFactoryAdapter.setUsername("admin");
        connectionFactoryAdapter.setPassword("passw0rd");

        CachingConnectionFactory result = new CachingConnectionFactory();
        result.setReconnectOnException(true);
        result.setSessionCacheSize(10);
        result.setTargetConnectionFactory(connectionFactoryAdapter);
        result.setCacheConsumers(false);

        return result;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws Exception {
        return new JmsTemplate(connectionFactory());
    }

}
