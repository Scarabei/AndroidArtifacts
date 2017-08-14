package android.support.v4.media;

import android.media.VolumeProvider;
import android.support.annotation.RequiresApi;

@RequiresApi(21)
class VolumeProviderCompatApi21 {
   public static Object createVolumeProvider(int volumeControl, int maxVolume, int currentVolume, final VolumeProviderCompatApi21.Delegate delegate) {
      return new VolumeProvider(volumeControl, maxVolume, currentVolume) {
         public void onSetVolumeTo(int volume) {
            delegate.onSetVolumeTo(volume);
         }

         public void onAdjustVolume(int direction) {
            delegate.onAdjustVolume(direction);
         }
      };
   }

   public static void setCurrentVolume(Object volumeProviderObj, int currentVolume) {
      ((VolumeProvider)volumeProviderObj).setCurrentVolume(currentVolume);
   }

   public interface Delegate {
      void onSetVolumeTo(int var1);

      void onAdjustVolume(int var1);
   }
}
