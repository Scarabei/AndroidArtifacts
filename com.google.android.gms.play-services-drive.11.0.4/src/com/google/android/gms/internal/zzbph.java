package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.drive.events.ChangeEvent;
import com.google.android.gms.drive.events.CompletionEvent;
import com.google.android.gms.drive.events.DriveEvent;
import com.google.android.gms.drive.events.zzb;
import com.google.android.gms.drive.events.zzl;
import com.google.android.gms.drive.events.zzn;
import com.google.android.gms.drive.events.zzr;

public final class zzbph extends zza {
   public static final Creator CREATOR = new zzbpi();
   private int zzaJo;
   private ChangeEvent zzaPb;
   private CompletionEvent zzaPc;
   private zzl zzaPd;
   private zzb zzaPe;
   private zzr zzaPf;
   private zzn zzaPg;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zzc(var1, 2, this.zzaJo);
      zzd.zza(var1, 3, this.zzaPb, var2, false);
      zzd.zza(var1, 5, this.zzaPc, var2, false);
      zzd.zza(var1, 6, this.zzaPd, var2, false);
      zzd.zza(var1, 7, this.zzaPe, var2, false);
      zzd.zza(var1, 9, this.zzaPf, var2, false);
      zzd.zza(var1, 10, this.zzaPg, var2, false);
      zzd.zzI(var1, var5);
   }

   zzbph(int var1, ChangeEvent var2, CompletionEvent var3, zzl var4, zzb var5, zzr var6, zzn var7) {
      this.zzaJo = var1;
      this.zzaPb = var2;
      this.zzaPc = var3;
      this.zzaPd = var4;
      this.zzaPe = var5;
      this.zzaPf = var6;
      this.zzaPg = var7;
   }

   public final DriveEvent zztj() {
      switch(this.zzaJo) {
      case 1:
         return this.zzaPb;
      case 2:
         return this.zzaPc;
      case 3:
         return this.zzaPd;
      case 4:
         return this.zzaPe;
      case 5:
      case 6:
      default:
         int var1 = this.zzaJo;
         throw new IllegalStateException((new StringBuilder(33)).append("Unexpected event type ").append(var1).toString());
      case 7:
         return this.zzaPf;
      case 8:
         return this.zzaPg;
      }
   }
}
