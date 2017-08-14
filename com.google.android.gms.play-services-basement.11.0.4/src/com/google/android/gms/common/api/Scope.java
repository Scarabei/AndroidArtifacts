package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

public final class Scope extends zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zze();
   private int zzaku;
   private final String zzaBl;

   Scope(int var1, String var2) {
      zzbo.zzh(var2, "scopeUri must not be null or empty");
      this.zzaku = var1;
      this.zzaBl = var2;
   }

   public Scope(String var1) {
      this(1, var1);
   }

   public final String zzpp() {
      return this.zzaBl;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return !(var1 instanceof Scope) ? false : this.zzaBl.equals(((Scope)var1).zzaBl);
      }
   }

   public final int hashCode() {
      return this.zzaBl.hashCode();
   }

   public final String toString() {
      return this.zzaBl;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 1, this.zzaku);
      zzd.zza(var1, 2, (String)this.zzaBl, false);
      zzd.zzI(var1, var5);
   }
}
