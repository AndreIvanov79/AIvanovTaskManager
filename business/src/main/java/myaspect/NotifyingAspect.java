package myaspect;

import entity.User;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import util.MailSender;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
public class NotifyingAspect {
    private static final Logger LOG = Logger.getLogger(LoggingAspect.class);
    String pattern = "dd-MM-yyyy HH:mm:ss";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    @AfterReturning(pointcut = "execution(* *(..)) && @annotation(annotation.Notifiable)", returning = "user")
    public void mailNotification(JoinPoint joinPoint, User user) throws Throwable {
        String date = simpleDateFormat.format(new Date());

        try {
            String subject = "Creation of User " + user.getUserName();
            String content = "User "+user.getFirstName()+" / "+user.getLastName()+" identified by "+user.getUserName()+
                    " has been created.\n"+"Task "+user.getMyTasks()+" has been assigned to "+user.getUserName();

            MailSender mailSender = new MailSender();
            mailSender.sendMailMessage(subject, content);
        } catch (Throwable t) {
            LOG.error("This message didn`t send.");
            t.printStackTrace();
        }
    }
}
