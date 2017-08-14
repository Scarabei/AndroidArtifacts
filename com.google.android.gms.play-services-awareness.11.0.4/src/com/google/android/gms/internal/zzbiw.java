package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzbiw extends zza {
   public static final Creator CREATOR = new zzbix();
   private String zzaKW;
   private zzbiy zzaKX;
   private long zzaKY;

   zzbiw(String var1, zzbiy var2, long var3) {
      this.zzaKW = var1;
      this.zzaKX = var2;
      this.zzaKY = var3;
   }

   public zzbiw(String var1, long var2, zzbiy var4) {
      this(zzbo.zzcF(var1), (zzbiy)zzbo.zzu(var4), var2);
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 2, this.zzaKW, false);
      zzd.zza(var1, 3, this.zzaKX, var2, false);
      zzd.zza(var1, 4, this.zzaKY);
      zzd.zzI(var1, var5);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaKW, this.zzaKY});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof zzbiw)) {
         return false;
      } else {
         zzbiw var2 = (zzbiw)var1;
         return TextUtils.equals(this.zzaKW, var2.zzaKW) && this.zzaKY == var2.zzaKY;
      }
   }
}
