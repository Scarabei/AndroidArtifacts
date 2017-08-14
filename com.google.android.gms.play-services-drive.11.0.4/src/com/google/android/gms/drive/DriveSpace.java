package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import java.util.Set;
import java.util.regex.Pattern;

public class DriveSpace extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzl();
   public static final DriveSpace zzaMl = new DriveSpace("DRIVE");
   public static final DriveSpace zzaMm = new DriveSpace("APP_DATA_FOLDER");
   public static final DriveSpace zzaMn = new DriveSpace("PHOTOS");
   private static Set zzaMo;
   private static String zzaMp;
   private static final Pattern zzaMq;
   private final String mName;

   DriveSpace(String var1) {
      this.mName = (String)zzbo.zzu(var1);
   }

   public boolean equals(Object var1) {
      return var1 != null && var1.getClass() == DriveSpace.class ? this.mName.equals(((DriveSpace)var1).mName) : false;
   }

   public int hashCode() {
      return 1247068382 ^ this.mName.hashCode();
   }

   public String toString() {
      return this.mName;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.mName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static {
      zzaMo = com.google.android.gms.common.util.zzf.zza(zzaMl, zzaMm, zzaMn);
      zzaMp = TextUtils.join(",", zzaMo.toArray());
      zzaMq = Pattern.compile("[A-Z0-9_]*");
   }
}
