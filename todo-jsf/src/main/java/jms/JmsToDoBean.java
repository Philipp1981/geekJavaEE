package jms;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.ToDoDto;
import services.ToDoServiceLocal;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/toDoQueue"),
        @ActivationConfigProperty(propertyValue = "messageSelector", propertyName = "action = 'create'")}
)
public class JmsToDoBean implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(JmsToDoBean.class);

    @EJB
    private ToDoServiceLocal toDoService;

    @Override
    public void onMessage(Message message) {
        logger.info("New JMS message");

        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            try {
                ToDoDto toDo = (ToDoDto) om.getObject();
                toDoService.insert(toDo);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}

