package com.google.android.gms.games.multiplayer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbo;

public final class ParticipantResult extends com.google.android.gms.games.internal.zzc {
   public static final Creator CREATOR = new zzd();
   public static final int MATCH_RESULT_UNINITIALIZED = -1;
   public static final int MATCH_RESULT_WIN = 0;
   public static final int MATCH_RESULT_LOSS = 1;
   public static final int MATCH_RESULT_TIE = 2;
   public static final int MATCH_RESULT_NONE = 3;
   public static final int MATCH_RESULT_DISCONNECT = 4;
   public static final int MATCH_RESULT_DISAGREED = 5;
   public static final int PLACING_UNINITIALIZED = -1;
   private final String zzbah;
   private final int zzbdB;
   private final int zzbdC;

   public ParticipantResult(String var1, int var2, int var3) {
      this.zzbah = (String)zzbo.zzu(var1);
      boolean var10000;
      switch(var2) {
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
         var10000 = true;
         break;
      default:
         var10000 = false;
      }

      zzbo.zzae(var10000);
      this.zzbdB = var2;
      this.zzbdC = var3;
   }

   public final String getParticipantId() {
      return this.zzbah;
   }

   public final int getResult() {
      return this.zzbdB;
   }

   public final int getPlacing() {
      return this.zzbdC;
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getParticipantId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getResult());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getPlacing());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
