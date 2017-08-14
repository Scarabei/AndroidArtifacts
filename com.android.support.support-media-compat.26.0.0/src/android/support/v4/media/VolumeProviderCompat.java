package android.support.v4.media;

import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class VolumeProviderCompat {
   public static final int VOLUME_CONTROL_FIXED = 0;
   public static final int VOLUME_CONTROL_RELATIVE = 1;
   public static final int VOLUME_CONTROL_ABSOLUTE = 2;
   private final int mControlType;
   private final int mMaxVolume;
   private int mCurrentVolume;
   private VolumeProviderCompat.Callback mCallback;
   private Object mVolumeProviderObj;

   public VolumeProviderCompat(int volumeControl, int maxVolume, int currentVolume) {
      this.mControlType = volumeControl;
      this.mMaxVolume = maxVolume;
      this.mCurrentVolume = currentVolume;
   }

   public final int getCurrentVolume() {
      return this.mCurrentVolume;
   }

   public final int getVolumeControl() {
      return this.mControlType;
   }

   public final int getMaxVolume() {
      return this.mMaxVolume;
   }

   public final void setCurrentVolume(int currentVolume) {
      this.mCurrentVolume = currentVolume;
      Object volumeProviderObj = this.getVolumeProvider();
      if (volumeProviderObj != null && VERSION.SDK_INT >= 21) {
         VolumeProviderCompatApi21.setCurrentVolume(volumeProviderObj, currentVolume);
      }

      if (this.mCallback != null) {
         this.mCallback.onVolumeChanged(this);
      }

   }

   public void onSetVolumeTo(int volume) {
   }

   public void onAdjustVolume(int direction) {
   }

   public void setCallback(VolumeProviderCompat.Callback callback) {
      this.mCallback = callback;
   }

   public Object getVolumeProvider() {
      if (this.mVolumeProviderObj == null && VERSION.SDK_INT >= 21) {
         this.mVolumeProviderObj = VolumeProviderCompatApi21.createVolumeProvider(this.mControlType, this.mMaxVolume, this.mCurrentVolume, new VolumeProviderCompatApi21.Delegate() {
            public void onSetVolumeTo(int volume) {
               VolumeProviderCompat.this.onSetVolumeTo(volume);
            }

            public void onAdjustVolume(int direction) {
               VolumeProviderCompat.this.onAdjustVolume(direction);
            }
         });
      }

      return this.mVolumeProviderObj;
   }

   public abstract static class Callback {
      public abstract void onVolumeChanged(VolumeProviderCompat var1);
   }

   @Retention(RetentionPolicy.SOURCE)
   @RestrictTo({Scope.LIBRARY_GROUP})
   public @interface ControlType {
   }
}
