package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzayo;

public class CastMediaOptions extends com.google.android.gms.common.internal.safeparcel.zza {
   private static final zzayo zzarK = new zzayo("CastMediaOptions");
   public static final Creator CREATOR = new zza();
   private final String zzatD;
   private final String zzatE;
   private final zzb zzatF;
   private final NotificationOptions zzatG;

   CastMediaOptions(String var1, String var2, IBinder var3, NotificationOptions var4) {
      this.zzatD = var1;
      this.zzatE = var2;
      IInterface var6;
      this.zzatF = (zzb)(var3 == null ? null : ((var6 = var3.queryLocalInterface("com.google.android.gms.cast.framework.media.IImagePicker")) instanceof zzb ? (zzb)var6 : new zzc(var3)));
      this.zzatG = var4;
   }

   public String getMediaIntentReceiverClassName() {
      return this.zzatD;
   }

   public NotificationOptions getNotificationOptions() {
      return this.zzatG;
   }

   public String getExpandedControllerActivityClassName() {
      return this.zzatE;
   }

   public ImagePicker getImagePicker() {
      if (this.zzatF != null) {
         try {
            return (ImagePicker)com.google.android.gms.dynamic.zzn.zzE(this.zzatF.zznT());
         } catch (RemoteException var2) {
            zzarK.zzb(var2, "Unable to call %s on %s.", new Object[]{"getWrappedClientObject", zzb.class.getSimpleName()});
         }
      }

      return null;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getMediaIntentReceiverClassName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getExpandedControllerActivityClassName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzatF == null ? null : this.zzatF.asBinder(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getNotificationOptions(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static final class Builder {
      private String zzatD = MediaIntentReceiver.class.getName();
      private String zzatE;
      private ImagePicker zzatH;
      private NotificationOptions zzatG = (new NotificationOptions.Builder()).build();

      public final CastMediaOptions.Builder setMediaIntentReceiverClassName(String var1) {
         this.zzatD = var1;
         return this;
      }

      public final CastMediaOptions.Builder setExpandedControllerActivityClassName(String var1) {
         this.zzatE = var1;
         return this;
      }

      public final CastMediaOptions.Builder setImagePicker(ImagePicker var1) {
         this.zzatH = var1;
         return this;
      }

      public final CastMediaOptions.Builder setNotificationOptions(NotificationOptions var1) {
         this.zzatG = var1;
         return this;
      }

      public final CastMediaOptions build() {
         IBinder var1 = this.zzatH == null ? null : this.zzatH.zznU().asBinder();
         return new CastMediaOptions(this.zzatD, this.zzatE, var1, this.zzatG);
      }
   }
}
