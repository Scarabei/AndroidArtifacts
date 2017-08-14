package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.R.dimen;
import com.google.android.gms.R.drawable;
import com.google.android.gms.R.string;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NotificationOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final long SKIP_STEP_TEN_SECONDS_IN_MS = 10000L;
   public static final long SKIP_STEP_THIRTY_SECONDS_IN_MS = 30000L;
   private static final List zzatN = Arrays.asList("com.google.android.gms.cast.framework.action.TOGGLE_PLAYBACK", "com.google.android.gms.cast.framework.action.STOP_CASTING");
   private static final int[] zzatO = new int[]{0, 1};
   public static final Creator CREATOR = new zzh();
   private final List zzatP;
   private final int[] zzatQ;
   private final long zzatR;
   private final String zzatS;
   private final int zzatT;
   private final int zzatU;
   private final int zzatV;
   private final int zzatW;
   private final int zzatX;
   private final int zzatY;
   private final int zzatZ;
   private final int zzaua;
   private final int zzaub;
   private final int zzauc;
   private final int zzaud;
   private final int zzaue;
   private final int zzauf;
   private final int zzaug;
   private final int zzauh;
   private final int zzaui;
   private final int zzauj;
   private final int zzauk;
   private final int zzaul;
   private final int zzaum;
   private final int zzaun;
   private final int zzauo;
   private final int zzaup;
   private final int zzauq;
   private final int zzaur;
   private final int zzaus;
   private final int zzaut;

   public NotificationOptions(List var1, int[] var2, long var3, String var5, int var6, int var7, int var8, int var9, int var10, int var11, int var12, int var13, int var14, int var15, int var16, int var17, int var18, int var19, int var20, int var21, int var22, int var23, int var24, int var25, int var26, int var27, int var28, int var29, int var30, int var31, int var32) {
      if (var1 != null) {
         this.zzatP = new ArrayList(var1);
      } else {
         this.zzatP = null;
      }

      if (var2 != null) {
         this.zzatQ = Arrays.copyOf(var2, var2.length);
      } else {
         this.zzatQ = null;
      }

      this.zzatR = var3;
      this.zzatS = var5;
      this.zzatT = var6;
      this.zzatU = var7;
      this.zzatV = var8;
      this.zzatW = var9;
      this.zzatX = var10;
      this.zzatY = var11;
      this.zzatZ = var12;
      this.zzaua = var13;
      this.zzaub = var14;
      this.zzauc = var15;
      this.zzaud = var16;
      this.zzaue = var17;
      this.zzauf = var18;
      this.zzaug = var19;
      this.zzauh = var20;
      this.zzaui = var21;
      this.zzauj = var22;
      this.zzauk = var23;
      this.zzaul = var24;
      this.zzaum = var25;
      this.zzaun = var26;
      this.zzauo = var27;
      this.zzaup = var28;
      this.zzauq = var29;
      this.zzaur = var30;
      this.zzaus = var31;
      this.zzaut = var32;
   }

   public List getActions() {
      return this.zzatP;
   }

   public int[] getCompatActionIndices() {
      return Arrays.copyOf(this.zzatQ, this.zzatQ.length);
   }

   public long getSkipStepMs() {
      return this.zzatR;
   }

   public String getTargetActivityClassName() {
      return this.zzatS;
   }

   public int getSmallIconDrawableResId() {
      return this.zzatT;
   }

   public int getStopLiveStreamDrawableResId() {
      return this.zzatU;
   }

   public int getPauseDrawableResId() {
      return this.zzatV;
   }

   public int getPlayDrawableResId() {
      return this.zzatW;
   }

   public int getSkipNextDrawableResId() {
      return this.zzatX;
   }

   public int getSkipPrevDrawableResId() {
      return this.zzatY;
   }

   public int getForwardDrawableResId() {
      return this.zzatZ;
   }

   public int getForward10DrawableResId() {
      return this.zzaua;
   }

   public int getForward30DrawableResId() {
      return this.zzaub;
   }

   public int getRewindDrawableResId() {
      return this.zzauc;
   }

   public int getRewind10DrawableResId() {
      return this.zzaud;
   }

   public int getRewind30DrawableResId() {
      return this.zzaue;
   }

   public int getDisconnectDrawableResId() {
      return this.zzauf;
   }

   public int getCastingToDeviceStringResId() {
      return this.zzauh;
   }

   public int getStopLiveStreamTitleResId() {
      return this.zzaui;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzb(var1, 2, this.getActions(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getCompatActionIndices(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getSkipStepMs());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getTargetActivityClassName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.getSmallIconDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.getStopLiveStreamDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 8, this.getPauseDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 9, this.getPlayDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.getSkipNextDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 11, this.getSkipPrevDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 12, this.getForwardDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 13, this.getForward10DrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 14, this.getForward30DrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 15, this.getRewindDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 16, this.getRewind10DrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 17, this.getRewind30DrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 18, this.getDisconnectDrawableResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 19, this.zzaug);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 20, this.getCastingToDeviceStringResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 21, this.getStopLiveStreamTitleResId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 22, this.zzauj);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 23, this.zzauk);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 24, this.zzaul);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 25, this.zzaum);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 26, this.zzaun);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 27, this.zzauo);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 28, this.zzaup);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 29, this.zzauq);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 30, this.zzaur);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 31, this.zzaus);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 32, this.zzaut);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static final class Builder {
      private String zzatS;
      private List zzatP;
      private int[] zzatQ;
      private int zzatT;
      private int zzatU;
      private int zzatV;
      private int zzatW;
      private int zzatX;
      private int zzatY;
      private int zzatZ;
      private int zzaua;
      private int zzaub;
      private int zzauc;
      private int zzaud;
      private int zzaue;
      private int zzauf;
      private long zzatR;

      public Builder() {
         this.zzatP = NotificationOptions.zzatN;
         this.zzatQ = NotificationOptions.zzatO;
         this.zzatT = drawable.cast_ic_notification_small_icon;
         this.zzatU = drawable.cast_ic_notification_stop_live_stream;
         this.zzatV = drawable.cast_ic_notification_pause;
         this.zzatW = drawable.cast_ic_notification_play;
         this.zzatX = drawable.cast_ic_notification_skip_next;
         this.zzatY = drawable.cast_ic_notification_skip_prev;
         this.zzatZ = drawable.cast_ic_notification_forward;
         this.zzaua = drawable.cast_ic_notification_forward10;
         this.zzaub = drawable.cast_ic_notification_forward30;
         this.zzauc = drawable.cast_ic_notification_rewind;
         this.zzaud = drawable.cast_ic_notification_rewind10;
         this.zzaue = drawable.cast_ic_notification_rewind30;
         this.zzauf = drawable.cast_ic_notification_disconnect;
         this.zzatR = 10000L;
      }

      public final NotificationOptions.Builder setActions(List var1, int[] var2) {
         if (var1 == null && var2 != null) {
            throw new IllegalArgumentException("When setting actions to null, you must also set compatActionIndices to null.");
         } else if (var1 != null && var2 == null) {
            throw new IllegalArgumentException("When setting compatActionIndices to null, you must also set actions to null.");
         } else {
            if (var1 != null && var2 != null) {
               int var3 = var1.size();
               if (var2.length > var3) {
                  throw new IllegalArgumentException(String.format(Locale.ROOT, "Invalid number of compat actions: %d > %d.", var2.length, var3));
               }

               int[] var4 = var2;
               int var5 = var2.length;
               int var6 = 0;

               while(true) {
                  if (var6 >= var5) {
                     this.zzatP = new ArrayList(var1);
                     this.zzatQ = Arrays.copyOf(var2, var2.length);
                     break;
                  }

                  int var7;
                  if ((var7 = var4[var6]) < 0 || var7 >= var3) {
                     throw new IllegalArgumentException(String.format(Locale.ROOT, "Index %d in compatActionIndices out of range: [0, %d]", var7, var3 - 1));
                  }

                  ++var6;
               }
            } else {
               this.zzatP = NotificationOptions.zzatN;
               this.zzatQ = NotificationOptions.zzatO;
            }

            return this;
         }
      }

      public final NotificationOptions.Builder setSkipStepMs(long var1) {
         zzbo.zzb(var1 > 0L, "skipStepMs must be positive.");
         this.zzatR = var1;
         return this;
      }

      public final NotificationOptions.Builder setTargetActivityClassName(String var1) {
         this.zzatS = var1;
         return this;
      }

      public final NotificationOptions.Builder setSmallIconDrawableResId(int var1) {
         this.zzatT = var1;
         return this;
      }

      public final NotificationOptions.Builder setStopLiveStreamDrawableResId(int var1) {
         this.zzatU = var1;
         return this;
      }

      public final NotificationOptions.Builder setPauseDrawableResId(int var1) {
         this.zzatV = var1;
         return this;
      }

      public final NotificationOptions.Builder setPlayDrawableResId(int var1) {
         this.zzatW = var1;
         return this;
      }

      public final NotificationOptions.Builder setDisconnectDrawableResId(int var1) {
         this.zzauf = var1;
         return this;
      }

      public final NotificationOptions.Builder setSkipNextDrawableResId(int var1) {
         this.zzatX = var1;
         return this;
      }

      public final NotificationOptions.Builder setSkipPrevDrawableResId(int var1) {
         this.zzatY = var1;
         return this;
      }

      public final NotificationOptions.Builder setForwardDrawableResId(int var1) {
         this.zzatZ = var1;
         return this;
      }

      public final NotificationOptions.Builder setForward10DrawableResId(int var1) {
         this.zzaua = var1;
         return this;
      }

      public final NotificationOptions.Builder setForward30DrawableResId(int var1) {
         this.zzaub = var1;
         return this;
      }

      public final NotificationOptions.Builder setRewindDrawableResId(int var1) {
         this.zzauc = var1;
         return this;
      }

      public final NotificationOptions.Builder setRewind10DrawableResId(int var1) {
         this.zzaud = var1;
         return this;
      }

      public final NotificationOptions.Builder setRewind30DrawableResId(int var1) {
         this.zzaue = var1;
         return this;
      }

      public final NotificationOptions build() {
         return new NotificationOptions(this.zzatP, this.zzatQ, this.zzatR, this.zzatS, this.zzatT, this.zzatU, this.zzatV, this.zzatW, this.zzatX, this.zzatY, this.zzatZ, this.zzaua, this.zzaub, this.zzauc, this.zzaud, this.zzaue, this.zzauf, dimen.cast_notification_image_size, string.cast_casting_to_device, string.cast_stop_live_stream, string.cast_pause, string.cast_play, string.cast_skip_next, string.cast_skip_prev, string.cast_forward, string.cast_forward_10, string.cast_forward_30, string.cast_rewind, string.cast_rewind_10, string.cast_rewind_30, string.cast_disconnect);
      }
   }
}
