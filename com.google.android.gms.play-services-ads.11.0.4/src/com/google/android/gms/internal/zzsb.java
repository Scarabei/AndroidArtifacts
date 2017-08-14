package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Releasable;
import java.lang.ref.WeakReference;
import java.util.Map;

@zzzn
public abstract class zzsb implements Releasable {
   protected Context mContext;
   private String zzJP;
   private WeakReference zzJQ;

   public zzsb(zzaka var1) {
      this.mContext = var1.getContext();
      this.zzJP = com.google.android.gms.ads.internal.zzbs.zzbz().zzs(this.mContext, var1.zziz().zzaP);
      this.zzJQ = new WeakReference(var1);
   }

   public abstract boolean zzU(String var1);

   public abstract void abort();

   public void release() {
   }

   protected final void zza(String var1, String var2, int var3) {
      zzaiy.zzaaH.post(new zzsd(this, var1, var2, var3));
   }

   public final void zza(String var1, String var2, String var3, @Nullable String var4) {
      zzaiy.zzaaH.post(new zzse(this, var1, var2, var3, var4));
   }

   private static String zzV(String var0) {
      String var1 = "internal";
      byte var3 = -1;
      switch(var0.hashCode()) {
      case -1396664534:
         if (var0.equals("badUrl")) {
            var3 = 6;
         }
         break;
      case -1347010958:
         if (var0.equals("inProgress")) {
            var3 = 2;
         }
         break;
      case -918817863:
         if (var0.equals("downloadTimeout")) {
            var3 = 7;
         }
         break;
      case -659376217:
         if (var0.equals("contentLengthMissing")) {
            var3 = 3;
         }
         break;
      case -642208130:
         if (var0.equals("playerFailed")) {
            var3 = 1;
         }
         break;
      case -354048396:
         if (var0.equals("sizeExceeded")) {
            var3 = 8;
         }
         break;
      case -32082395:
         if (var0.equals("externalAbort")) {
            var3 = 9;
         }
         break;
      case 96784904:
         if (var0.equals("error")) {
            var3 = 0;
         }
         break;
      case 580119100:
         if (var0.equals("expireFailed")) {
            var3 = 5;
         }
         break;
      case 725497484:
         if (var0.equals("noCacheDir")) {
            var3 = 4;
         }
      }

      switch(var3) {
      case 0:
      case 1:
      case 2:
      case 3:
         var1 = "internal";
         break;
      case 4:
      case 5:
         var1 = "io";
         break;
      case 6:
      case 7:
         var1 = "network";
         break;
      case 8:
      case 9:
         var1 = "policy";
      }

      return var1;
   }

   private final void zza(String var1, Map var2) {
      zzaka var3;
      if ((var3 = (zzaka)this.zzJQ.get()) != null) {
         var3.zza(var1, var2);
      }

   }

   // $FF: synthetic method
   static void zza(zzsb var0, String var1, Map var2) {
      var0.zza(var1, var2);
   }

   // $FF: synthetic method
   static String zza(zzsb var0, String var1) {
      return zzV(var1);
   }
}
