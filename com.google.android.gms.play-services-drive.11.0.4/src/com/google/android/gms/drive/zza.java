package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.adp;
import com.google.android.gms.internal.zzbqz;

public final class zza extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private long zzaLP;
   private long zzaLQ;
   private long zzaLR;
   private volatile String zzaLS = null;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaLP);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaLQ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaLR);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public zza(long var1, long var3, long var5) {
      zzbo.zzaf(var1 != -1L);
      zzbo.zzaf(var3 != -1L);
      zzbo.zzaf(var5 != -1L);
      this.zzaLP = var1;
      this.zzaLQ = var3;
      this.zzaLR = var5;
   }

   public final int hashCode() {
      String var1 = String.valueOf(String.valueOf(this.zzaLP));
      String var2 = String.valueOf(String.valueOf(this.zzaLQ));
      String var3 = String.valueOf(String.valueOf(this.zzaLR));
      return (new StringBuilder(String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length())).append(var1).append(var2).append(var3).toString().hashCode();
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zza)) {
         return false;
      } else {
         zza var2;
         if ((var2 = (zza)var1).zzaLQ != this.zzaLQ) {
            return false;
         } else if (var2.zzaLR != this.zzaLR) {
            return false;
         } else {
            return var2.zzaLP == this.zzaLP;
         }
      }
   }

   public final String toString() {
      if (this.zzaLS == null) {
         zzbqz var4;
         (var4 = new zzbqz()).versionCode = 1;
         var4.sequenceNumber = this.zzaLP;
         var4.zzaPw = this.zzaLQ;
         var4.zzaPx = this.zzaLR;
         String var2 = Base64.encodeToString(adp.zzc(var4), 10);
         String var10001 = String.valueOf("ChangeSequenceNumber:");
         String var10002 = String.valueOf(var2);
         if (var10002.length() != 0) {
            var10001 = var10001.concat(var10002);
         } else {
            String var10003 = new String;
            var10002 = var10001;
            var10001 = var10003;
            var10003.<init>(var10002);
         }

         this.zzaLS = var10001;
      }

      return this.zzaLS;
   }
}
