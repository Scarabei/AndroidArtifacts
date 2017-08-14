package com.google.android.gms.location.places.internal;

import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ado;
import com.google.android.gms.internal.il;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class zzav extends com.google.android.gms.common.data.zzc {
   public zzav(DataHolder var1, int var2) {
      super(var1, var2);
   }

   private final byte[] zzb(String var1, byte[] var2) {
      return this.zzcv(var1) && !this.zzcx(var1) ? this.getByteArray(var1) : null;
   }

   protected final float zza(String var1, float var2) {
      return this.zzcv(var1) && !this.zzcx(var1) ? this.getFloat(var1) : var2;
   }

   protected final List zza(String var1, List var2) {
      byte[] var3;
      if ((var3 = this.zzb(var1, (byte[])null)) == null) {
         return var2;
      } else {
         try {
            il var4;
            if ((var4 = il.zzz(var3)).zzbUT == null) {
               return var2;
            } else {
               ArrayList var5 = new ArrayList(var4.zzbUT.length);

               for(int var6 = 0; var6 < var4.zzbUT.length; ++var6) {
                  var5.add(var4.zzbUT[var6]);
               }

               return var5;
            }
         } catch (ado var7) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
               Log.e("SafeDataBufferRef", "Cannot parse byte[]", var7);
            }

            return var2;
         }
      }
   }

   protected final int zzu(String var1, int var2) {
      return this.zzcv(var1) && !this.zzcx(var1) ? this.getInteger(var1) : var2;
   }

   protected final List zza(String var1, Creator var2, List var3) {
      byte[] var4;
      if ((var4 = this.zzb(var1, (byte[])null)) == null) {
         return var3;
      } else {
         try {
            il var5;
            if ((var5 = il.zzz(var4)).zzbUU == null) {
               return var3;
            } else {
               ArrayList var6 = new ArrayList(var5.zzbUU.length);
               byte[][] var7 = var5.zzbUU;
               int var8 = var5.zzbUU.length;

               for(int var9 = 0; var9 < var8; ++var9) {
                  byte[] var10 = var7[var9];
                  var6.add(com.google.android.gms.common.internal.safeparcel.zze.zza(var10, var2));
               }

               return var6;
            }
         } catch (ado var11) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
               Log.e("SafeDataBufferRef", "Cannot parse byte[]", var11);
            }

            return var3;
         }
      }
   }

   protected final SafeParcelable zza(String var1, Creator var2) {
      byte[] var3;
      return (var3 = this.zzb(var1, (byte[])null)) == null ? null : com.google.android.gms.common.internal.safeparcel.zze.zza(var3, var2);
   }

   protected final List zzb(String var1, List var2) {
      byte[] var3;
      if ((var3 = this.zzb(var1, (byte[])null)) == null) {
         return var2;
      } else {
         try {
            il var4;
            return (var4 = il.zzz(var3)).zzbUS == null ? var2 : Arrays.asList(var4.zzbUS);
         } catch (ado var5) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
               Log.e("SafeDataBufferRef", "Cannot parse byte[]", var5);
            }

            return var2;
         }
      }
   }

   protected final String zzD(String var1, String var2) {
      return this.zzcv(var1) && !this.zzcx(var1) ? this.getString(var1) : var2;
   }
}
