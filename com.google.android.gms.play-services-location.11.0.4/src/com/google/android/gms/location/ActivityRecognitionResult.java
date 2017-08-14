package com.google.android.gms.location;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ActivityRecognitionResult extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzb();
   private List zzbhu;
   private long zzbhv;
   private long zzbhw;
   private int zzbhx;
   private Bundle extras;

   public ActivityRecognitionResult(List var1, long var2, long var4) {
      this((List)var1, var2, var4, 0, (Bundle)null);
   }

   public ActivityRecognitionResult(DetectedActivity var1, long var2, long var4) {
      this((DetectedActivity)var1, var2, var4, 0, (Bundle)null);
   }

   private ActivityRecognitionResult(DetectedActivity var1, long var2, long var4, int var6, Bundle var7) {
      this((List)Collections.singletonList(var1), var2, var4, 0, (Bundle)null);
   }

   public static boolean hasResult(Intent var0) {
      if (var0 == null) {
         return false;
      } else if (var0 == null ? false : var0.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) {
         return true;
      } else {
         List var1;
         return (var1 = zzj(var0)) != null && !var1.isEmpty();
      }
   }

   public static ActivityRecognitionResult extractResult(Intent var0) {
      ActivityRecognitionResult var10000;
      label26: {
         if (hasResult(var0)) {
            Object var4;
            if ((var4 = var0.getExtras().get("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT")) instanceof byte[]) {
               var10000 = (ActivityRecognitionResult)com.google.android.gms.common.internal.safeparcel.zze.zza((byte[])var4, CREATOR);
               break label26;
            }

            if (var4 instanceof ActivityRecognitionResult) {
               var10000 = (ActivityRecognitionResult)var4;
               break label26;
            }
         }

         var10000 = null;
      }

      ActivityRecognitionResult var1 = var10000;
      if (var10000 != null) {
         return var1;
      } else {
         List var2;
         return (var2 = zzj(var0)) != null && !var2.isEmpty() ? (ActivityRecognitionResult)var2.get(var2.size() - 1) : null;
      }
   }

   @Nullable
   private static List zzj(Intent var0) {
      if (!(var0 == null ? false : var0.hasExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST"))) {
         return null;
      } else {
         Creator var3 = CREATOR;
         String var2 = "com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST";
         ArrayList var4;
         if ((var4 = (ArrayList)var0.getSerializableExtra("com.google.android.location.internal.EXTRA_ACTIVITY_RESULT_LIST")) == null) {
            return null;
         } else {
            ArrayList var5 = new ArrayList(var4.size());
            ArrayList var7;
            int var8 = (var7 = (ArrayList)var4).size();
            int var9 = 0;

            while(var9 < var8) {
               Object var10000 = var7.get(var9);
               ++var9;
               byte[] var6 = (byte[])var10000;
               var5.add(com.google.android.gms.common.internal.safeparcel.zze.zza(var6, var3));
            }

            return var5;
         }
      }
   }

   public DetectedActivity getMostProbableActivity() {
      return (DetectedActivity)this.zzbhu.get(0);
   }

   public int getActivityConfidence(int var1) {
      Iterator var2 = this.zzbhu.iterator();

      DetectedActivity var3;
      do {
         if (!var2.hasNext()) {
            return 0;
         }
      } while((var3 = (DetectedActivity)var2.next()).getType() != var1);

      return var3.getConfidence();
   }

   public List getProbableActivities() {
      return this.zzbhu;
   }

   public long getTime() {
      return this.zzbhv;
   }

   public long getElapsedRealtimeMillis() {
      return this.zzbhw;
   }

   public String toString() {
      String var1 = String.valueOf(this.zzbhu);
      long var2 = this.zzbhv;
      long var4 = this.zzbhw;
      return (new StringBuilder(124 + String.valueOf(var1).length())).append("ActivityRecognitionResult [probableActivities=").append(var1).append(", timeMillis=").append(var2).append(", elapsedRealtimeMillis=").append(var4).append("]").toString();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ActivityRecognitionResult var2 = (ActivityRecognitionResult)var1;
         return this.zzbhv == var2.zzbhv && this.zzbhw == var2.zzbhw && this.zzbhx == var2.zzbhx && zzbe.equal(this.zzbhu, var2.zzbhu) && zzc(this.extras, var2.extras);
      } else {
         return false;
      }
   }

   private static boolean zzc(Bundle var0, Bundle var1) {
      if (var0 == null && var1 == null) {
         return true;
      } else if (var0 == null && var1 != null || var0 != null && var1 == null) {
         return false;
      } else if (var0.size() != var1.size()) {
         return false;
      } else {
         Iterator var2 = var0.keySet().iterator();

         while(var2.hasNext()) {
            String var3 = (String)var2.next();
            if (!var1.containsKey(var3)) {
               return false;
            }

            if (var0.get(var3) == null) {
               if (var1.get(var3) != null) {
                  return false;
               }
            } else if (var0.get(var3) instanceof Bundle) {
               if (!zzc(var0.getBundle(var3), var1.getBundle(var3))) {
                  return false;
               }
            } else if (!var0.get(var3).equals(var1.get(var3))) {
               return false;
            }
         }

         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbhv, this.zzbhw, this.zzbhx, this.zzbhu, this.extras});
   }

   public ActivityRecognitionResult(List var1, long var2, long var4, int var6, Bundle var7) {
      zzbo.zzb(var1 != null && var1.size() > 0, "Must have at least 1 detected activity");
      zzbo.zzb(var2 > 0L && var4 > 0L, "Must set times");
      this.zzbhu = var1;
      this.zzbhv = var2;
      this.zzbhw = var4;
      this.zzbhx = var6;
      this.extras = var7;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbhu, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzbhv);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbhw);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbhx);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.extras, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }
}
