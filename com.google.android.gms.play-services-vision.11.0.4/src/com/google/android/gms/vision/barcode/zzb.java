package com.google.android.gms.vision.barcode;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzb implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Barcode[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      int var4 = 0;
      String var5 = null;
      String var6 = null;
      int var7 = 0;
      Point[] var8 = null;
      Barcode.Email var9 = null;
      Barcode.Phone var10 = null;
      Barcode.Sms var11 = null;
      Barcode.WiFi var12 = null;
      Barcode.UrlBookmark var13 = null;
      Barcode.GeoPoint var14 = null;
      Barcode.CalendarEvent var15 = null;
      Barcode.ContactInfo var16 = null;
      Barcode.DriverLicense var17 = null;

      while(var2.dataPosition() < var3) {
         int var18;
         switch((var18 = var2.readInt()) & 65535) {
         case 2:
            var4 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var18);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var18);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var18);
            break;
         case 5:
            var7 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var18);
            break;
         case 6:
            var8 = (Point[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var18, Point.CREATOR);
            break;
         case 7:
            var9 = (Barcode.Email)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.Email.CREATOR);
            break;
         case 8:
            var10 = (Barcode.Phone)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.Phone.CREATOR);
            break;
         case 9:
            var11 = (Barcode.Sms)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.Sms.CREATOR);
            break;
         case 10:
            var12 = (Barcode.WiFi)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.WiFi.CREATOR);
            break;
         case 11:
            var13 = (Barcode.UrlBookmark)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.UrlBookmark.CREATOR);
            break;
         case 12:
            var14 = (Barcode.GeoPoint)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.GeoPoint.CREATOR);
            break;
         case 13:
            var15 = (Barcode.CalendarEvent)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.CalendarEvent.CREATOR);
            break;
         case 14:
            var16 = (Barcode.ContactInfo)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.ContactInfo.CREATOR);
            break;
         case 15:
            var17 = (Barcode.DriverLicense)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var18, Barcode.DriverLicense.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var18);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Barcode(var4, var5, var6, var7, var8, var9, var10, var11, var12, var13, var14, var15, var16, var17);
   }
}
