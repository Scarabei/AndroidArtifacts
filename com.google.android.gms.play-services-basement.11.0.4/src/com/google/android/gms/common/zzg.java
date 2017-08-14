package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzar;
import com.google.android.gms.common.internal.zzas;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

abstract class zzg extends zzas {
   private int zzaAg;

   protected zzg(byte[] var1) {
      if (var1.length != 25) {
         int var2 = var1.length;
         String var3 = String.valueOf(com.google.android.gms.common.util.zzl.zza(var1, 0, var1.length, false));
         Log.wtf("GoogleCertificates", (new StringBuilder(51 + String.valueOf(var3).length())).append("Cert hash data has incorrect length (").append(var2).append("):\n").append(var3).toString(), new Exception());
         boolean var10000 = (var1 = Arrays.copyOfRange(var1, 0, 25)).length == 25;
         var2 = var1.length;
         zzbo.zzb(var10000, (new StringBuilder(55)).append("cert hash data has incorrect length. length=").append(var2).toString());
      }

      this.zzaAg = Arrays.hashCode(var1);
   }

   public int hashCode() {
      return this.zzaAg;
   }

   public boolean equals(Object var1) {
      if (var1 != null && var1 instanceof zzar) {
         try {
            zzar var2;
            if ((var2 = (zzar)var1).zzoZ() != this.hashCode()) {
               return false;
            } else {
               IObjectWrapper var3;
               if ((var3 = var2.zzoY()) == null) {
                  return false;
               } else {
                  byte[] var4 = (byte[])com.google.android.gms.dynamic.zzn.zzE(var3);
                  return Arrays.equals(this.getBytes(), var4);
               }
            }
         } catch (RemoteException var5) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", var5);
            return false;
         }
      } else {
         return false;
      }
   }

   abstract byte[] getBytes();

   public final IObjectWrapper zzoY() {
      return com.google.android.gms.dynamic.zzn.zzw(this.getBytes());
   }

   public final int zzoZ() {
      return this.hashCode();
   }

   protected static byte[] zzcs(String var0) {
      try {
         return var0.getBytes("ISO-8859-1");
      } catch (UnsupportedEncodingException var2) {
         throw new AssertionError(var2);
      }
   }
}
