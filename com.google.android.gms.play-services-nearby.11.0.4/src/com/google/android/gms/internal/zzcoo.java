package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import java.util.Arrays;

public final class zzcoo extends zza {
   public static final Creator CREATOR = new zzcop();
   private final long id;
   private final int type;
   @Nullable
   private final byte[] zzbws;
   @Nullable
   private final ParcelFileDescriptor zzbxD;
   @Nullable
   private final String zzbxE;
   private final long zzbxF;
   @Nullable
   private final ParcelFileDescriptor zzbxG;

   public zzcoo(long var1, int var3, @Nullable byte[] var4, @Nullable ParcelFileDescriptor var5, @Nullable String var6, long var7, @Nullable ParcelFileDescriptor var9) {
      this.id = var1;
      this.type = var3;
      this.zzbws = var4;
      this.zzbxD = var5;
      this.zzbxE = var6;
      this.zzbxF = var7;
      this.zzbxG = var9;
   }

   public final long getId() {
      return this.id;
   }

   public final int getType() {
      return this.type;
   }

   @Nullable
   public final byte[] getBytes() {
      return this.zzbws;
   }

   @Nullable
   public final ParcelFileDescriptor zzzN() {
      return this.zzbxD;
   }

   @Nullable
   public final String zzzO() {
      return this.zzbxE;
   }

   public final long zzzP() {
      return this.zzbxF;
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.id, this.type, this.zzbws, this.zzbxD, this.zzbxE, this.zzbxF, this.zzbxG});
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 instanceof zzcoo) {
         zzcoo var2 = (zzcoo)var1;
         return zzbe.equal(this.id, var2.id) && zzbe.equal(this.type, var2.type) && zzbe.equal(this.zzbws, var2.zzbws) && zzbe.equal(this.zzbxD, var2.zzbxD) && zzbe.equal(this.zzbxE, var2.zzbxE) && zzbe.equal(this.zzbxF, var2.zzbxF) && zzbe.equal(this.zzbxG, var2.zzbxG);
      } else {
         return false;
      }
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.id);
      zzd.zzc(var1, 2, this.type);
      zzd.zza(var1, 3, this.zzbws, false);
      zzd.zza(var1, 4, this.zzbxD, var2, false);
      zzd.zza(var1, 5, this.zzbxE, false);
      zzd.zza(var1, 6, this.zzbxF);
      zzd.zza(var1, 7, this.zzbxG, var2, false);
      zzd.zzI(var1, var5);
   }
}
