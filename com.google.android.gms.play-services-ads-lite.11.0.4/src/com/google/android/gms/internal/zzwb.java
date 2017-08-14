package com.google.android.gms.internal;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.zzb;
import java.util.Date;
import java.util.HashSet;

@zzzn
public final class zzwb {
   public static AdSize zzb(zziv var0) {
      AdSize[] var1 = new AdSize[]{AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};

      for(int var2 = 0; var2 < 6; ++var2) {
         if (var1[var2].getWidth() == var0.width && var1[var2].getHeight() == var0.height) {
            return var1[var2];
         }
      }

      return new AdSize(zzb.zza(var0.width, var0.height, var0.zzAs));
   }

   public static int zza(AdRequest.ErrorCode var0) {
      switch(zzwc.zzNn[var0.ordinal()]) {
      case 2:
         return 1;
      case 3:
         return 2;
      case 4:
         return 3;
      default:
         return 0;
      }
   }

   public static MediationAdRequest zzn(zzir var0) {
      HashSet var1 = var0.zzzP != null ? new HashSet(var0.zzzP) : null;
      MediationAdRequest var10000 = new MediationAdRequest;
      Date var10002 = new Date(var0.zzzN);
      AdRequest.Gender var10003;
      switch(var0.zzzO) {
      case 1:
         var10003 = AdRequest.Gender.MALE;
         break;
      case 2:
         var10003 = AdRequest.Gender.FEMALE;
         break;
      default:
         var10003 = AdRequest.Gender.UNKNOWN;
      }

      var10000.<init>(var10002, var10003, var1, var0.zzzQ, var0.zzzV);
      return var10000;
   }
}
