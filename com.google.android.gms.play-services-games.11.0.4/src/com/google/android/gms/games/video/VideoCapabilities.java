package com.google.android.gms.games.video;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.games.internal.zzc;
import java.util.Arrays;

public final class VideoCapabilities extends zzc {
   public static final Creator CREATOR = new zza();
   private final boolean zzbfd;
   private final boolean zzbfe;
   private final boolean zzbff;
   private final boolean[] zzbfg;
   private final boolean[] zzbfh;

   public VideoCapabilities(boolean var1, boolean var2, boolean var3, boolean[] var4, boolean[] var5) {
      this.zzbfd = var1;
      this.zzbfe = var2;
      this.zzbff = var3;
      this.zzbfg = var4;
      this.zzbfh = var5;
   }

   public final boolean isMicSupported() {
      return this.zzbfe;
   }

   public final boolean isCameraSupported() {
      return this.zzbfd;
   }

   public final boolean isWriteStorageSupported() {
      return this.zzbff;
   }

   public final boolean[] getSupportedCaptureModes() {
      return this.zzbfg;
   }

   public final boolean[] getSupportedQualityLevels() {
      return this.zzbfh;
   }

   public final boolean supportsCaptureMode(int var1) {
      zzbo.zzae(VideoConfiguration.isValidCaptureMode(var1, false));
      return this.zzbfg[var1];
   }

   public final boolean supportsQualityLevel(int var1) {
      zzbo.zzae(VideoConfiguration.isValidQualityLevel(var1, false));
      return this.zzbfh[var1];
   }

   public final boolean isFullySupported(int var1, int var2) {
      return this.zzbfd && this.zzbfe && this.zzbff && this.supportsCaptureMode(var1) && this.supportsQualityLevel(var2);
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.getSupportedCaptureModes(), this.getSupportedQualityLevels(), this.isCameraSupported(), this.isMicSupported(), this.isWriteStorageSupported()});
   }

   public final boolean equals(Object var1) {
      if (!(var1 instanceof VideoCapabilities)) {
         return false;
      } else if (this == var1) {
         return true;
      } else {
         VideoCapabilities var2;
         return zzbe.equal((var2 = (VideoCapabilities)var1).getSupportedCaptureModes(), this.getSupportedCaptureModes()) && zzbe.equal(var2.getSupportedQualityLevels(), this.getSupportedQualityLevels()) && zzbe.equal(var2.isCameraSupported(), this.isCameraSupported()) && zzbe.equal(var2.isMicSupported(), this.isMicSupported()) && zzbe.equal(var2.isWriteStorageSupported(), this.isWriteStorageSupported());
      }
   }

   public final String toString() {
      return zzbe.zzt(this).zzg("SupportedCaptureModes", this.getSupportedCaptureModes()).zzg("SupportedQualityLevels", this.getSupportedQualityLevels()).zzg("CameraSupported", this.isCameraSupported()).zzg("MicSupported", this.isMicSupported()).zzg("StorageWriteSupported", this.isWriteStorageSupported()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.isCameraSupported());
      zzd.zza(var1, 2, this.isMicSupported());
      zzd.zza(var1, 3, this.isWriteStorageSupported());
      zzd.zza(var1, 4, this.getSupportedCaptureModes(), false);
      zzd.zza(var1, 5, this.getSupportedQualityLevels(), false);
      zzd.zzI(var1, var5);
   }
}
