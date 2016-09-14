import com.jcabi.aspects.RetryOnFailure;
import net.jodah.failsafe.Failsafe;
import net.jodah.failsafe.RetryPolicy;
import org.apache.velocity.test.IntrospectorTestCase2;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by svanlaar on 13/09/2016.
 */
public class retry_jcabi {

    @RetryOnFailure(attempts = 5, delay = 1, unit = TimeUnit.SECONDS)
    @Test
    public void retry_test(IntrospectorTestCase2.Bar b) {
        newConnection();
    }

    @Test
    public void failsafe_retry(){
        RetryPolicy retryPolicy = new RetryPolicy()
                .retryOn(NullPointerException.class)
                .withMaxRetries(5)
                .withDelay(1, TimeUnit.SECONDS);

        Failsafe.with(retryPolicy).run(() -> newConnection());
    }

    private void newConnection() {
        throw new NullPointerException();
    }



}
