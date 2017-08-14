package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Arrays;

public class Cap extends com.google.android.gms.common.internal.safeparcel.zza {
   private static final String TAG = Cap.class.getSimpleName();
   public static final Creator CREATOR = new zzb();
   private final int type;
   @Nullable
   private final BitmapDescriptor bitmapDescriptor;
   @Nullable
   private final Float zzbng;

   private Cap(int var1, @Nullable BitmapDescriptor var2, @Nullable Float var3) {
      boolean var4 = var3 != null && var3.floatValue() > 0.0F;
      boolean var10000 = var1 != 3 || var2 != null && var4;
      String var5 = String.valueOf(var2);
      String var6 = String.valueOf(var3);
      zzbo.zzb(var10000, (new StringBuilder(63 + String.valueOf(var5).length() + String.valueOf(var6).length())).append("Invalid Cap: type=").append(var1).append(" bitmapDescriptor=").append(var5).append(" bitmapRefWidth=").append(var6).toString());
      this.type = var1;
      this.bitmapDescriptor = var2;
      this.zzbng = var3;
   }

   Cap(int var1, @Nullable IBinder var2, @Nullable Float var3) {
      BitmapDescriptor var10002;
      if (var2 == null) {
         var10002 = null;
      } else {
         IObjectWrapper var5 = com.google.android.gms.dynamic.IObjectWrapper.zza.zzM(var2);
         var10002 = new BitmapDescriptor(var5);
      }

      this(var1, var10002, var3);
   }

   protected Cap(@NonNull BitmapDescriptor var1, float var2) {
      this(3, (BitmapDescriptor)var1, var2);
   }

   protected Cap(int var1) {
      this(var1, (BitmapDescriptor)null, (Float)null);
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.type);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.bitmapDescriptor == null ? null : this.bitmapDescriptor.zzwe().asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzbng, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.type, this.bitmapDescriptor, this.zzbng});
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Cap)) {
         return false;
      } else {
         Cap var2 = (Cap)var1;
         return this.type == var2.type && zzbe.equal(this.bitmapDescriptor, var2.bitmapDescriptor) && zzbe.equal(this.zzbng, var2.zzbng);
      }
   }

   public String toString() {
      int var1 = this.type;
      return (new StringBuilder(23)).append("[Cap: type=").append(var1).append("]").toString();
   }

   final Cap zzwk() {
      switch(this.type) {
      case 0:
         return new ButtCap();
      case 1:
         return new SquareCap();
      case 2:
         return new RoundCap();
      case 3:
         return new CustomCap(this.bitmapDescriptor, this.zzbng.floatValue());
      default:
         int var1 = this.type;
         Log.w(TAG, (new StringBuilder(29)).append("Unknown Cap type: ").append(var1).toString());
         return this;
      }
   }
}
