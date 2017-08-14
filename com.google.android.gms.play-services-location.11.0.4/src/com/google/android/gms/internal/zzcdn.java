package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public final class zzcdn extends zza {
   static final List zzbiU = Collections.emptyList();
   private LocationRequest zzaXb;
   private List zzbiV;
   @Nullable
   private String mTag;
   private boolean zzbiW;
   private boolean zzbiX;
   private boolean zzbiY;
   @Nullable
   private String zzanK;
   private boolean zzbiZ = true;
   public static final Creator CREATOR = new zzcdo();

   /** @deprecated */
   @Deprecated
   public static zzcdn zza(LocationRequest var0) {
      return new zzcdn(var0, zzbiU, (String)null, false, false, false, (String)null);
   }

   zzcdn(LocationRequest var1, List var2, @Nullable String var3, boolean var4, boolean var5, boolean var6, String var7) {
      this.zzaXb = var1;
      this.zzbiV = var2;
      this.mTag = var3;
      this.zzbiW = var4;
      this.zzbiX = var5;
      this.zzbiY = var6;
      this.zzanK = var7;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.zzaXb, var2, false);
      zzd.zzc(var1, 5, this.zzbiV, false);
      zzd.zza(var1, 6, this.mTag, false);
      zzd.zza(var1, 7, this.zzbiW);
      zzd.zza(var1, 8, this.zzbiX);
      zzd.zza(var1, 9, this.zzbiY);
      zzd.zza(var1, 10, this.zzanK, false);
      zzd.zzI(var1, var5);
   }

   public final String toString() {
      StringBuilder var1;
      (var1 = new StringBuilder()).append(this.zzaXb.toString());
      if (this.mTag != null) {
         var1.append(" tag=").append(this.mTag);
      }

      if (this.zzanK != null) {
         var1.append(" moduleId=").append(this.zzanK);
      }

      var1.append(" hideAppOps=").append(this.zzbiW);
      var1.append(" clients=").append(this.zzbiV);
      var1.append(" forceCoarseLocation=").append(this.zzbiX);
      if (this.zzbiY) {
         var1.append(" exemptFromBackgroundThrottle");
      }

      return var1.toString();
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof zzcdn)) {
         return false;
      } else {
         zzcdn var2 = (zzcdn)var1;
         return zzbe.equal(this.zzaXb, var2.zzaXb) && zzbe.equal(this.zzbiV, var2.zzbiV) && zzbe.equal(this.mTag, var2.mTag) && this.zzbiW == var2.zzbiW && this.zzbiX == var2.zzbiX && this.zzbiY == var2.zzbiY && zzbe.equal(this.zzanK, var2.zzanK);
      }
   }

   public final int hashCode() {
      return this.zzaXb.hashCode();
   }
}
