package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class InstrumentInfo extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int CARD_CLASS_UNKNOWN = 0;
   public static final int CARD_CLASS_CREDIT = 1;
   public static final int CARD_CLASS_DEBIT = 2;
   public static final int CARD_CLASS_PREPAID = 3;
   public static final Creator CREATOR = new zzj();
   private String zzbOK;
   private String zzbOL;
   private int zzbOM;

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getInstrumentType(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getInstrumentDetails(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getCardClass());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public InstrumentInfo(String var1, String var2, int var3) {
      this.zzbOK = var1;
      this.zzbOL = var2;
      this.zzbOM = var3;
   }

   private InstrumentInfo() {
   }

   public final String getInstrumentType() {
      return this.zzbOK;
   }

   public final String getInstrumentDetails() {
      return this.zzbOL;
   }

   public final int getCardClass() {
      switch(this.zzbOM) {
      case 1:
      case 2:
      case 3:
         return this.zzbOM;
      default:
         return 0;
      }
   }

   @Retention(RetentionPolicy.SOURCE)
   public @interface CardClass {
   }
}
