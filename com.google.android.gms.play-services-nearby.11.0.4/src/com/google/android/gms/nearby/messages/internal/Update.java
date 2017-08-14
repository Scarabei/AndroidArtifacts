package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzcpj;
import com.google.android.gms.nearby.messages.Message;
import java.util.Arrays;

public class Update extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzbg();
   private int zzaku;
   private int zzbzA;
   public final Message zzbzd;
   @Nullable
   public final zze zzbzB;
   @Nullable
   public final zza zzbzC;
   @Nullable
   public final zzcpj zzbzD;

   Update(int var1, int var2, Message var3, @Nullable zze var4, @Nullable zza var5, @Nullable zzcpj var6) {
      this.zzaku = var1;
      this.zzbzA = var2;
      zzbo.zza(!this.zzbt(1) || !this.zzbt(2), "Update cannot represent both FOUND and LOST.");
      this.zzbzd = var3;
      this.zzbzB = var4;
      this.zzbzC = var5;
      this.zzbzD = var6;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbzA);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbzd, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbzB, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzbzC, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzbzD, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean zzbt(int var1) {
      return (this.zzbzA & var1) != 0;
   }

   public String toString() {
      com.google.android.gms.common.util.zza var7 = new com.google.android.gms.common.util.zza();
      if (this.zzbt(1)) {
         var7.add("FOUND");
      }

      if (this.zzbt(2)) {
         var7.add("LOST");
      }

      if (this.zzbt(4)) {
         var7.add("DISTANCE");
      }

      if (this.zzbt(8)) {
         var7.add("BLE_SIGNAL");
      }

      if (this.zzbt(16)) {
         var7.add("DEVICE");
      }

      String var1 = String.valueOf(var7);
      String var2 = String.valueOf(this.zzbzd);
      String var3 = String.valueOf(this.zzbzB);
      String var4 = String.valueOf(this.zzbzC);
      String var5 = String.valueOf(this.zzbzD);
      return (new StringBuilder(56 + String.valueOf(var1).length() + String.valueOf(var2).length() + String.valueOf(var3).length() + String.valueOf(var4).length() + String.valueOf(var5).length())).append("Update{types=").append(var1).append(", message=").append(var2).append(", distance=").append(var3).append(", bleSignal=").append(var4).append(", device=").append(var5).append("}").toString();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Update)) {
         return false;
      } else {
         Update var2 = (Update)var1;
         return this.zzbzA == var2.zzbzA && com.google.android.gms.common.internal.zzbe.equal(this.zzbzd, var2.zzbzd) && com.google.android.gms.common.internal.zzbe.equal(this.zzbzB, var2.zzbzB) && com.google.android.gms.common.internal.zzbe.equal(this.zzbzC, var2.zzbzC) && com.google.android.gms.common.internal.zzbe.equal(this.zzbzD, var2.zzbzD);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbzA, this.zzbzd, this.zzbzB, this.zzbzC, this.zzbzD});
   }
}
