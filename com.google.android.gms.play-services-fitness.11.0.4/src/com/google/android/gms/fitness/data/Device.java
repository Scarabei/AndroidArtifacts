package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Build.VERSION;
import android.os.Parcelable.Creator;
import android.provider.Settings.Secure;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbui;
import com.google.android.gms.internal.zzbzn;
import java.util.Arrays;

public final class Device extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int TYPE_UNKNOWN = 0;
   public static final int TYPE_PHONE = 1;
   public static final int TYPE_TABLET = 2;
   public static final int TYPE_WATCH = 3;
   public static final int TYPE_CHEST_STRAP = 4;
   public static final int TYPE_SCALE = 5;
   public static final int TYPE_HEAD_MOUNTED = 6;
   private final int versionCode;
   private final String zzaUf;
   private final String zzaUg;
   private final String version;
   private final String zzaUh;
   private final int type;
   private final int zzaUi;
   public static final Creator CREATOR = new zzo();

   public static Device getLocalDevice(Context var0) {
      int var1 = zzbui.zzaU(var0);
      String var2 = Secure.getString(var0.getContentResolver(), "android_id");
      return new Device(Build.MANUFACTURER, Build.MODEL, VERSION.RELEASE, var2, var1, 2);
   }

   public Device(String var1, String var2, String var3, int var4) {
      this(var1, var2, "", var3, var4, 0);
   }

   private Device(String var1, String var2, String var3, String var4, int var5, int var6) {
      this(1, var1, var2, "", var4, var5, var6);
   }

   Device(int var1, String var2, String var3, String var4, String var5, int var6, int var7) {
      this.versionCode = var1;
      this.zzaUf = (String)zzbo.zzu(var2);
      this.zzaUg = (String)zzbo.zzu(var3);
      this.version = "";
      if (var5 != null) {
         this.zzaUh = var5;
         this.type = var6;
         this.zzaUi = var7;
      } else {
         throw new IllegalStateException("Device UID is null.");
      }
   }

   public final String getManufacturer() {
      return this.zzaUf;
   }

   public final String getModel() {
      return this.zzaUg;
   }

   public final String getUid() {
      return this.zzaUh;
   }

   public final int getType() {
      return this.type;
   }

   final String getStreamIdentifier() {
      return String.format("%s:%s:%s", this.zzaUf, this.zzaUg, this.zzaUh);
   }

   public final String toString() {
      return String.format("Device{%s:%s:%s:%s}", this.getStreamIdentifier(), this.version, this.type, this.zzaUi);
   }

   public final boolean equals(Object var1) {
      if (this != var1) {
         if (var1 instanceof Device) {
            Device var3 = (Device)var1;
            if (zzbe.equal(this.zzaUf, var3.zzaUf) && zzbe.equal(this.zzaUg, var3.zzaUg) && zzbe.equal(this.version, var3.version) && zzbe.equal(this.zzaUh, var3.zzaUh) && this.type == var3.type && this.zzaUi == var3.zzaUi) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaUf, this.zzaUg, this.version, this.zzaUh, this.type});
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getManufacturer(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getModel(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.version, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzaUi == 1 ? this.zzaUh : zzbzn.zzdh(this.zzaUh), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.getType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzaUi);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
