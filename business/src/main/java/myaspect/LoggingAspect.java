package myaspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
public class LoggingAspect {
    private static final Logger LOG = Logger.getLogger(LoggingAspect.class);
    String pattern = "dd-MM-yyyy HH:mm:ss.SSS";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


    @Pointcut("execution(* *(..))&& @annotation(annotation.Loggable)")
    public void logMethod() {
    }

    @Before(value = "logMethod()")
    public void logBefore(JoinPoint joinPoint){
        String date = simpleDateFormat.format(new Date());
        LOG.info("Start execution of method [ "+joinPoint.getSignature().getName()+" ] at: "+date);
    }

    @After(value = "logMethod()")
    public void logAfter(JoinPoint joinPoint){
        String date = simpleDateFormat.format(new Date());
        LOG.info("Finish execution of method [ "+joinPoint.getSignature().getName()+" ] at: "+date);
    }
}
