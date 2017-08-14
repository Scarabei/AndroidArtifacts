package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbo;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class zza implements ServiceConnection {
   private boolean zzazV = false;
   private final BlockingQueue zzazW = new LinkedBlockingQueue();

   public final void onServiceConnected(ComponentName var1, IBinder var2) {
      this.zzazW.add(var2);
   }

   public final void onServiceDisconnected(ComponentName var1) {
   }

   public final IBinder zza(long var1, TimeUnit var3) throws InterruptedException, TimeoutException {
      zzbo.zzcG("BlockingServiceConnection.getServiceWithTimeout() called on main thread");
      if (this.zzazV) {
         throw new IllegalStateException("Cannot call get on this connection more than once");
      } else {
         this.zzazV = true;
         IBinder var4;
         if ((var4 = (IBinder)this.zzazW.poll(10000L, var3)) == null) {
            throw new TimeoutException("Timed out waiting for the service connection");
         } else {
            return var4;
         }
      }
   }

   public final IBinder zzoV() throws InterruptedException {
      zzbo.zzcG("BlockingServiceConnection.getService() called on main thread");
      if (this.zzazV) {
         throw new IllegalStateException("Cannot call get on this connection more than once");
      } else {
         this.zzazV = true;
         return (IBinder)this.zzazW.take();
      }
   }
}
