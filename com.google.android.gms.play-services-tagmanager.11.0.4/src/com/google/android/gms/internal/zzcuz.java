package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

final class zzcuz extends Thread implements zzcuy {
   private final LinkedBlockingQueue zzbER = new LinkedBlockingQueue();
   private volatile boolean zzOZ = false;
   private volatile boolean mClosed = false;
   private static zzcuz zzbIl;
   private volatile zzcvb zzbIm;
   private final Context mContext;
   private final zze zzvw = zzi.zzrY();

   static zzcuz zzbw(Context var0) {
      if (zzbIl == null) {
         zzbIl = new zzcuz(var0);
      }

      return zzbIl;
   }

   private zzcuz(Context var1) {
      super("GAThread");
      if (var1 != null) {
         this.mContext = var1.getApplicationContext();
      } else {
         this.mContext = var1;
      }

      this.start();
   }

   public final void zzb(String var1, @Nullable String var2, @Nullable String var3, @Nullable Map var4, @Nullable String var5) {
      long var8 = this.zzvw.currentTimeMillis();
      this.zzn(new zzcva(this, this, var8, var1, var2, var3, var4, var5));
   }

   public final void zzn(Runnable var1) {
      this.zzbER.add(var1);
   }

   public final void run() {
      while(true) {
         boolean var10000 = this.mClosed;

         try {
            try {
               Runnable var1 = (Runnable)this.zzbER.take();
               if (!this.zzOZ) {
                  var1.run();
               }
            } catch (InterruptedException var5) {
               zzcvk.zzaS(var5.toString());
            }
         } catch (Throwable var6) {
            ByteArrayOutputStream var3 = new ByteArrayOutputStream();
            PrintStream var4 = new PrintStream(var3);
            var6.printStackTrace(var4);
            var4.flush();
            String var10001 = String.valueOf(new String(var3.toByteArray()));
            String var7;
            if (var10001.length() != 0) {
               var7 = "Error on Google TagManager Thread: ".concat(var10001);
            } else {
               String var10002 = new String;
               var7 = var10002;
               var10002.<init>("Error on Google TagManager Thread: ");
            }

            zzcvk.e(var7);
            zzcvk.e("Google TagManager is shutting down.");
            this.zzOZ = true;
         }
      }
   }

   // $FF: synthetic method
   static zzcvb zza(zzcuz var0) {
      return var0.zzbIm;
   }

   // $FF: synthetic method
   static Context zzb(zzcuz var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static zzcvb zza(zzcuz var0, zzcvb var1) {
      return var0.zzbIm = var1;
   }
}
