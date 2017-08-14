package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.nearby.messages.Message;
import java.util.Arrays;

public final class zzaf extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzag();
   private int zzaku;
   private Message zzbzd;

   zzaf(int var1, Message var2) {
      this.zzaku = var1;
      this.zzbzd = (Message)zzbo.zzu(var2);
   }

   public static final zzaf zza(Message var0) {
      return new zzaf(1, var0);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzbzd, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzaf)) {
         return false;
      } else {
         zzaf var2 = (zzaf)var1;
         return com.google.android.gms.common.internal.zzbe.equal(this.zzbzd, var2.zzbzd);
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbzd});
   }

   public final String toString() {
      String var1 = String.valueOf(this.zzbzd.toString());
      return (new StringBuilder(24 + String.valueOf(var1).length())).append("MessageWrapper{message=").append(var1).append("}").toString();
   }
}
