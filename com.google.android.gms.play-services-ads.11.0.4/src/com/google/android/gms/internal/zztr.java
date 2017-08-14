package com.google.android.gms.internal;

import android.os.Parcel;
import android.util.Base64;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@zzzn
final class zztr {
   final zzir zzuT;
   final String zztV;
   final int zzKt;

   static zztr zzab(String var0) throws IOException {
      String[] var1;
      if ((var1 = var0.split("\u0000")).length != 3) {
         throw new IOException("Incorrect field count for QueueSeed.");
      } else {
         Parcel var2 = Parcel.obtain();

         zztr var8;
         try {
            byte[] var3 = Base64.decode(var1[0], 0);
            String var4 = new String(var3, "UTF-8");
            int var5 = Integer.parseInt(var1[1]);
            byte[] var6 = Base64.decode(var1[2], 0);
            var2.unmarshall(var6, 0, var6.length);
            var2.setDataPosition(0);
            zzir var7 = (zzir)zzir.CREATOR.createFromParcel(var2);
            var8 = new zztr(var7, var4, var5);
         } catch (Throwable var12) {
            com.google.android.gms.ads.internal.zzbs.zzbD().zza(var12, "QueueSeed.decode");
            throw new IOException("Malformed QueueSeed encoding.", var12);
         } finally {
            var2.recycle();
         }

         return var8;
      }
   }

   zztr(zztn var1) {
      this(var1.zzeI(), var1.getAdUnitId(), var1.getNetworkType());
   }

   private zztr(zzir var1, String var2, int var3) {
      this.zzuT = var1;
      this.zztV = var2;
      this.zzKt = var3;
   }

   final String zzeW() {
      Parcel var1 = Parcel.obtain();

      try {
         String var2 = Base64.encodeToString(this.zztV.getBytes("UTF-8"), 0);
         String var3 = Integer.toString(this.zzKt);
         this.zzuT.writeToParcel(var1, 0);
         String var4 = Base64.encodeToString(var1.marshall(), 0);
         String var5 = (new StringBuilder(2 + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length())).append(var2).append("\u0000").append(var3).append("\u0000").append(var4).toString();
         return var5;
      } catch (UnsupportedEncodingException var9) {
         zzafr.e("QueueSeed encode failed because UTF-8 is not available.");
      } finally {
         var1.recycle();
      }

      return "";
   }
}
