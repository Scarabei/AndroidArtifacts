package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class zzcav extends zza {
   public static final Creator CREATOR = new zzcaw();
   private int versionCode;
   private zzax zzbft;
   private byte[] zzbfu;

   zzcav(int var1, byte[] var2) {
      this.versionCode = var1;
      this.zzbft = null;
      this.zzbfu = var2;
      this.zzsA();
   }

   public final zzax zzvA() {
      zzcav var1 = this;
      if (this.zzbft == null) {
         try {
            byte[] var3 = var1.zzbfu;
            var1.zzbft = (zzax)adp.zza(new zzax(), var3);
            var1.zzbfu = null;
         } catch (ado var4) {
            throw new IllegalStateException(var4);
         }
      }

      this.zzsA();
      return this.zzbft;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.versionCode);
      zzd.zza(var1, 2, this.zzbfu != null ? this.zzbfu : adp.zzc(this.zzbft), false);
      zzd.zzI(var1, var5);
   }

   private final void zzsA() {
      if (this.zzbft != null || this.zzbfu == null) {
         if (this.zzbft == null || this.zzbfu != null) {
            if (this.zzbft != null && this.zzbfu != null) {
               throw new IllegalStateException("Invalid internal representation - full");
            } else if (this.zzbft == null && this.zzbfu == null) {
               throw new IllegalStateException("Invalid internal representation - empty");
            } else {
               throw new IllegalStateException("Impossible");
            }
         }
      }
   }
}
