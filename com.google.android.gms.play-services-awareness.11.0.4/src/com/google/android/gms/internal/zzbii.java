package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzbii extends zza {
   private static zzer zzaKK = new zzbij();
   private static int[] zzaKL = new int[]{0, 1};
   public static final Creator CREATOR = new zzbik();
   private acl zzaKM = null;
   private byte[] zzaKN;

   public zzbii(byte[] var1) {
      this.zzaKN = (byte[])zzbo.zzu(var1);
      this.zzsA();
   }

   private final void zzsz() {
      if (this.zzaKM == null) {
         try {
            byte[] var2 = this.zzaKN;
            this.zzaKM = (acl)adp.zza(new acl(), var2);
            this.zzaKN = null;
         } catch (ado var3) {
            Log.e("ContextData", "Could not deserialize context bytes.", var3);
            throw new IllegalStateException(var3);
         }
      }

      this.zzsA();
   }

   private final void zzsA() {
      if (this.zzaKM != null || this.zzaKN == null) {
         if (this.zzaKM == null || this.zzaKN != null) {
            if (this.zzaKM != null && this.zzaKN != null) {
               throw new IllegalStateException("Invalid internal representation - full");
            } else if (this.zzaKM == null && this.zzaKN == null) {
               throw new IllegalStateException("Invalid internal representation - empty");
            } else {
               throw new IllegalStateException("Impossible");
            }
         }
      }
   }

   private final String getId() {
      this.zzsz();
      return this.zzaKM.zzcqD;
   }

   public final int hashCode() {
      this.zzsz();
      return Arrays.hashCode(new Object[]{this.getId(), this.zzaKM.zzcqE.version});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzbii)) {
         return false;
      } else {
         zzbii var2 = (zzbii)var1;
         this.zzsz();
         var2.zzsz();
         return this.getId().equals(var2.getId()) && this.zzaKM.zzcqE.version == var2.zzaKM.zzcqE.version;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaKN != null ? this.zzaKN : adp.zzc(this.zzaKM), false);
      zzd.zzI(var1, var5);
   }

   public final String toString() {
      this.zzsz();
      String var10000 = String.valueOf(this.zzaKM.toString());
      String var10001 = String.valueOf(zzaKK.zza(this));
      return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
   }
}
