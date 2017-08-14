package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.drive.DriveId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ParentDriveIdSet extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzn();
   final List zzaPO;

   ParentDriveIdSet(List var1) {
      this.zzaPO = var1;
   }

   public ParentDriveIdSet() {
      this(new ArrayList());
   }

   public final Set zzB(long var1) {
      HashSet var3 = new HashSet();
      Iterator var4 = this.zzaPO.iterator();

      while(var4.hasNext()) {
         zzq var5 = (zzq)var4.next();
         var3.add(new DriveId(var5.zzaMh, var5.zzaMi, var1, var5.zzaMj));
      }

      return var3;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzaPO, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
