package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.nearby.messages.internal.zzg;
import com.google.android.gms.nearby.messages.internal.zzl;
import java.util.UUID;

public final class zzcpl extends zza {
   public static final Creator CREATOR = new zzcpm();
   private int zzaku;
   private int zzbyP;
   private byte[] zzbyQ;
   private boolean zzbyR;

   zzcpl(int var1, int var2, byte[] var3, boolean var4) {
      this.zzaku = var1;
      this.zzbyP = var2;
      this.zzbyQ = var3;
      this.zzbyR = var4;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzbyP);
      zzd.zza(var1, 2, this.zzbyQ, false);
      zzd.zza(var1, 3, this.zzbyR);
      zzd.zzc(var1, 1000, this.zzaku);
      zzd.zzI(var1, var5);
   }

   private zzcpl(int var1, byte[] var2) {
      this(1, var1, var2, false);
   }

   public static zzcpl zzT(String var0, @Nullable String var1) {
      String var10000 = String.valueOf(var0);
      String var10001 = String.valueOf(var1 == null ? "" : var1);
      if (var10001.length() != 0) {
         var10000 = var10000.concat(var10001);
      } else {
         String var10002 = new String;
         var10001 = var10000;
         var10000 = var10002;
         var10002.<init>(var10001);
      }

      String var2 = var10000;
      zzg var3 = new zzg(var2);
      return new zzcpl(2, var3.getBytes());
   }

   public static zzcpl zza(UUID var0, @Nullable Short var1, @Nullable Short var2) {
      zzl var3 = new zzl(var0, var1, var2);
      return new zzcpl(3, var3.getBytes());
   }
}
