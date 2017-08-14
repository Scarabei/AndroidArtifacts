package android.support.test.rule;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.annotation.Beta;
import android.support.test.internal.util.Checks;
import android.util.Log;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

@Beta
public class ServiceTestRule implements TestRule {
   private static final String TAG = "ServiceTestRule";
   private static final long DEFAULT_TIMEOUT = 5L;
   private static IBinder mIBinder;
   private Intent mServiceIntent;
   private ServiceConnection mServiceConn;
   private long mTimeout;
   private TimeUnit mTimeUnit;
   boolean mServiceStarted;
   boolean mServiceBound;

   public ServiceTestRule() {
      this(5L, TimeUnit.SECONDS);
   }

   public static ServiceTestRule withTimeout(long timeout, TimeUnit timeUnit) {
      return new ServiceTestRule(timeout, timeUnit);
   }

   private ServiceTestRule(long timeout, TimeUnit timeUnit) {
      this.mServiceStarted = false;
      this.mServiceBound = false;
      this.mTimeout = timeout;
      this.mTimeUnit = timeUnit;
   }

   public void startService(@NonNull Intent intent) throws TimeoutException {
      this.mServiceIntent = (Intent)Checks.checkNotNull(intent, "intent can't be null");
      InstrumentationRegistry.getTargetContext().startService(this.mServiceIntent);
      this.mServiceStarted = true;
      this.mServiceBound = this.bindServiceAndWait(this.mServiceIntent, (ServiceConnection)null, 1);
   }

   public IBinder bindService(@NonNull Intent intent) throws TimeoutException {
      this.mServiceIntent = ((Intent)Checks.checkNotNull(intent, "intent can't be null")).cloneFilter();
      this.mServiceBound = this.bindServiceAndWait(intent, (ServiceConnection)null, 1);
      return mIBinder;
   }

   public IBinder bindService(@NonNull Intent intent, @NonNull ServiceConnection connection, int flags) throws TimeoutException {
      this.mServiceIntent = ((Intent)Checks.checkNotNull(intent, "intent can't be null")).cloneFilter();
      ServiceConnection c = (ServiceConnection)Checks.checkNotNull(connection, "connection can't be null");
      this.mServiceBound = this.bindServiceAndWait(this.mServiceIntent, c, flags);
      return mIBinder;
   }

   boolean bindServiceAndWait(Intent intent, ServiceConnection conn, int flags) throws TimeoutException {
      this.mServiceConn = new ServiceTestRule.ProxyServiceConnection(conn);
      boolean isBound = InstrumentationRegistry.getTargetContext().bindService(intent, this.mServiceConn, flags);
      if (isBound) {
         this.waitOnLatch(ServiceTestRule.ProxyServiceConnection.mConnectedLatch, "connected");
      } else {
         Log.e("ServiceTestRule", "Failed to bind to service");
      }

      return isBound;
   }

   void waitOnLatch(CountDownLatch latch, String actionName) throws TimeoutException {
      try {
         if (!latch.await(this.mTimeout, this.mTimeUnit)) {
            throw new TimeoutException("Waited for " + this.mTimeout + " " + this.mTimeUnit.name() + "," + " but service was never " + actionName);
         }
      } catch (InterruptedException var4) {
         Thread.currentThread().interrupt();
         throw new RuntimeException("Interrupted while waiting for service to be " + actionName, var4);
      }
   }

   void shutdownService() throws TimeoutException {
      if (this.mServiceStarted) {
         InstrumentationRegistry.getTargetContext().stopService(this.mServiceIntent);
         this.mServiceStarted = false;
      }

      if (this.mServiceBound) {
         InstrumentationRegistry.getTargetContext().unbindService(this.mServiceConn);
         mIBinder = null;
         this.mServiceBound = false;
      }

   }

   protected void beforeService() {
   }

   protected void afterService() {
   }

   public Statement apply(Statement base, Description description) {
      return new ServiceTestRule.ServiceStatement(base);
   }

   // $FF: synthetic class
   static class SyntheticClass_1 {
   }

   private class ServiceStatement extends Statement {
      private final Statement mBase;

      public ServiceStatement(Statement base) {
         this.mBase = base;
      }

      public void evaluate() throws Throwable {
         try {
            ServiceTestRule.this.beforeService();
            this.mBase.evaluate();
         } finally {
            ServiceTestRule.this.shutdownService();
            ServiceTestRule.this.afterService();
         }

      }
   }

   static class ProxyServiceConnection implements ServiceConnection {
      private ServiceConnection mCallerConnection;
      public static CountDownLatch mConnectedLatch = new CountDownLatch(1);

      private ProxyServiceConnection(ServiceConnection connection) {
         this.mCallerConnection = connection;
      }

      public void onServiceConnected(ComponentName name, IBinder service) {
         ServiceTestRule.mIBinder = service;
         if (this.mCallerConnection != null) {
            this.mCallerConnection.onServiceConnected(name, service);
         }

         mConnectedLatch.countDown();
      }

      public void onServiceDisconnected(ComponentName name) {
         Log.e("ServiceTestRule", "Connection to the Service has been lost!");
         ServiceTestRule.mIBinder = null;
         if (this.mCallerConnection != null) {
            this.mCallerConnection.onServiceDisconnected(name);
         }

      }

      // $FF: synthetic method
      ProxyServiceConnection(ServiceConnection x0, ServiceTestRule.SyntheticClass_1 x1) {
         this(x0);
      }
   }
}
