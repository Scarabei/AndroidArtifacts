package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.zzl;
import com.google.android.gms.drive.events.zzn;
import com.google.android.gms.drive.events.zzr;

public final class zzbpi implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new zzbph[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = zzb.zzd(var1);
      int var4 = 0;
      ChangeEvent var5 = null;
      CompletionEvent var6 = null;
      zzl var7 = null;
      com.google.android.gms.drive.events.zzb var8 = null;
      zzr var9 = null;
      zzn var10 = null;

      while(var2.dataPosition() < var3) {
         int var11;
         switch((var11 = var2.readInt()) & 65535) {
         case 2:
            var4 = zzb.zzg(var2, var11);
            break;
         case 3:
            var5 = (ChangeEvent)zzb.zza(var2, var11, ChangeEvent.CREATOR);
            break;
         case 4:
         case 8:
         default:
            zzb.zzb(var2, var11);
            break;
         case 5:
            var6 = (CompletionEvent)zzb.zza(var2, var11, CompletionEvent.CREATOR);
            break;
         case 6:
            var7 = (zzl)zzb.zza(var2, var11, zzl.CREATOR);
            break;
         case 7:
            var8 = (com.google.android.gms.drive.events.zzb)zzb.zza(var2, var11, com.google.android.gms.drive.events.zzb.CREATOR);
            break;
         case 9:
            var9 = (zzr)zzb.zza(var2, var11, zzr.CREATOR);
            break;
         case 10:
            var10 = (zzn)zzb.zza(var2, var11, zzn.CREATOR);
         }
      }

      zzb.zzF(var2, var3);
      return new zzbph(var4, var5, var6, var7, var8, var9, var10);
   }
}
