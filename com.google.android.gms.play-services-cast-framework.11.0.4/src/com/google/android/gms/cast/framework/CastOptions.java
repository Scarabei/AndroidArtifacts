package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CastOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzb();
   private final String zzarS;
   private final List zzarT;
   private final boolean zzarU;
   private final LaunchOptions zzapW;
   private final boolean zzarV;
   private final CastMediaOptions zzarW;
   private final boolean zzarX;
   private final double zzarY;

   CastOptions(String var1, List var2, boolean var3, LaunchOptions var4, boolean var5, CastMediaOptions var6, boolean var7, double var8) {
      this.zzarS = TextUtils.isEmpty(var1) ? "" : var1;
      int var10 = var2 == null ? 0 : var2.size();
      this.zzarT = new ArrayList(var10);
      if (var10 > 0) {
         this.zzarT.addAll(var2);
      }

      this.zzarU = var3;
      this.zzapW = var4 == null ? new LaunchOptions() : var4;
      this.zzarV = var5;
      this.zzarW = var6;
      this.zzarX = var7;
      this.zzarY = var8;
   }

   public String getReceiverApplicationId() {
      return this.zzarS;
   }

   public List getSupportedNamespaces() {
      return Collections.unmodifiableList(this.zzarT);
   }

   public boolean getStopReceiverApplicationWhenEndingSession() {
      return this.zzarU;
   }

   public LaunchOptions getLaunchOptions() {
      return this.zzapW;
   }

   public boolean getResumeSavedSession() {
      return this.zzarV;
   }

   public CastMediaOptions getCastMediaOptions() {
      return this.zzarW;
   }

   public boolean getEnableReconnectionService() {
      return this.zzarX;
   }

   public double getVolumeDeltaBeforeIceCreamSandwich() {
      return this.zzarY;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getReceiverApplicationId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 3, this.getSupportedNamespaces(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getStopReceiverApplicationWhenEndingSession());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getLaunchOptions(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getResumeSavedSession());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getCastMediaOptions(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getEnableReconnectionService());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.getVolumeDeltaBeforeIceCreamSandwich());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static final class Builder {
      private String zzarS;
      private List zzarT = new ArrayList();
      private boolean zzarU;
      private LaunchOptions zzapW = new LaunchOptions();
      private boolean zzarV = true;
      private CastMediaOptions zzarW = (new CastMediaOptions.Builder()).build();
      private boolean zzarX = true;
      private double zzarY = 0.05000000074505806D;

      public final CastOptions.Builder setReceiverApplicationId(String var1) {
         this.zzarS = var1;
         return this;
      }

      public final CastOptions.Builder setSupportedNamespaces(List var1) {
         this.zzarT = var1;
         return this;
      }

      public final CastOptions.Builder setStopReceiverApplicationWhenEndingSession(boolean var1) {
         this.zzarU = var1;
         return this;
      }

      public final CastOptions.Builder setLaunchOptions(LaunchOptions var1) {
         this.zzapW = var1;
         return this;
      }

      public final CastOptions.Builder setResumeSavedSession(boolean var1) {
         this.zzarV = var1;
         return this;
      }

      public final CastOptions.Builder setCastMediaOptions(CastMediaOptions var1) {
         this.zzarW = var1;
         return this;
      }

      public final CastOptions.Builder setEnableReconnectionService(boolean var1) {
         this.zzarX = var1;
         return this;
      }

      public final CastOptions.Builder setVolumeDeltaBeforeIceCreamSandwich(double var1) throws IllegalArgumentException {
         if (var1 > 0.0D && var1 <= 0.5D) {
            this.zzarY = var1;
            return this;
         } else {
            throw new IllegalArgumentException("volumeDelta must be greater than 0 and less or equal to 0.5");
         }
      }

      public final CastOptions build() {
         return new CastOptions(this.zzarS, this.zzarT, this.zzarU, this.zzapW, this.zzarV, this.zzarW, this.zzarX, this.zzarY);
      }
   }
}
