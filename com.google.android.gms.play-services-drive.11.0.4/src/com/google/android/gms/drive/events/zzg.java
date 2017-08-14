package com.google.android.gms.drive.events;

import android.os.IBinder;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.ArrayList;

public final class zzg implements Creator {
   // $FF: synthetic method
   public final Object[] newArray(int var1) {
      return new CompletionEvent[var1];
   }

   // $FF: synthetic method
   public final Object createFromParcel(Parcel var1) {
      Parcel var2 = var1;
      int var3 = com.google.android.gms.common.internal.safeparcel.zzb.zzd(var1);
      DriveId var4 = null;
      String var5 = null;
      ParcelFileDescriptor var6 = null;
      ParcelFileDescriptor var7 = null;
      MetadataBundle var8 = null;
      ArrayList var9 = null;
      int var10 = 0;
      IBinder var11 = null;

      while(var2.dataPosition() < var3) {
         int var12;
         switch((var12 = var2.readInt()) & 65535) {
         case 2:
            var4 = (DriveId)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, DriveId.CREATOR);
            break;
         case 3:
            var5 = com.google.android.gms.common.internal.safeparcel.zzb.zzq(var2, var12);
            break;
         case 4:
            var6 = (ParcelFileDescriptor)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, ParcelFileDescriptor.CREATOR);
            break;
         case 5:
            var7 = (ParcelFileDescriptor)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, ParcelFileDescriptor.CREATOR);
            break;
         case 6:
            var8 = (MetadataBundle)com.google.android.gms.common.internal.safeparcel.zzb.zza(var2, var12, MetadataBundle.CREATOR);
            break;
         case 7:
            var9 = com.google.android.gms.common.internal.safeparcel.zzb.zzC(var2, var12);
            break;
         case 8:
            var10 = com.google.android.gms.common.internal.safeparcel.zzb.zzg(var2, var12);
            break;
         case 9:
            var11 = com.google.android.gms.common.internal.safeparcel.zzb.zzr(var2, var12);
            break;
         default:
            com.google.android.gms.common.internal.safeparcel.zzb.zzb(var2, var12);
         }
      }

      com.google.android.gms.common.internal.safeparcel.zzb.zzF(var2, var3);
      return new CompletionEvent(var4, var5, var6, var7, var8, var9, var10, var11);
   }
}
