package android.support.test.internal.runner;

import java.util.Collection;
import org.junit.runner.Request;

public class TestRequest {
   private final Collection mFailures;
   private final Request mRequest;

   public TestRequest(Collection requestBuildFailures, Request request) {
      this.mRequest = request;
      this.mFailures = requestBuildFailures;
   }

   public Collection getFailures() {
      return this.mFailures;
   }

   public Request getRequest() {
      return this.mRequest;
   }
}
