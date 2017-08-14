package com.google.android.gms.vision.barcode;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzf implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new Barcode.ContactInfo[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      Barcode.PersonName var4 = null;
      String var5 = null;
      String var6 = null;
      Barcode.Phone[] var7 = null;
      Barcode.Email[] var8 = null;
      String[] var9 = null;
      Barcode.Address[] var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 2:
            var4 = (Barcode.PersonName)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var11, Barcode.PersonName.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 4:
            var6 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var11);
            break;
         case 5:
            var7 = (Barcode.Phone[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11, Barcode.Phone.CREATOR);
            break;
         case 6:
            var8 = (Barcode.Email[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11, Barcode.Email.CREATOR);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzA(var2, var11);
            break;
         case 8:
            var10 = (Barcode.Address[])com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11, Barcode.Address.CREATOR);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var11);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new Barcode.ContactInfo(var4, var5, var6, var7, var8, var9, var10);
   }
}
