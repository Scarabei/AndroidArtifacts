package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

final class zzca extends Thread implements zzbz {
   private final LinkedBlockingQueue zzbER = new LinkedBlockingQueue();
   private volatile boolean zzOZ = false;
   private volatile boolean mClosed = false;
   private static zzca zzbES;
   private volatile zzcc zzbET;
   private final Context mContext;

   static zzca zzbs(Context var0) {
      if (zzbES == null) {
         zzbES = new zzca(var0);
      }

      return zzbES;
   }

   private zzca(Context var1) {
      super("GAThread");
      if (var1 != null) {
         this.mContext = var1.getApplicationContext();
      } else {
         this.mContext = var1;
      }

      this.start();
   }

   public final void zzfm(String var1) {
      long var4 = System.currentTimeMillis();
      this.zzn(new zzcb(this, this, var4, var1));
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
               zzdj.zzaS(var5.toString());
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

            zzdj.e(var7);
            zzdj.e("Google TagManager is shutting down.");
            this.zzOZ = true;
         }
      }
   }

   // $FF: synthetic method
   static zzcc zza(zzca var0) {
      return var0.zzbET;
   }

   // $FF: synthetic method
   static Context zzb(zzca var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static zzcc zza(zzca var0, zzcc var1) {
      return var0.zzbET = var1;
   }
}
