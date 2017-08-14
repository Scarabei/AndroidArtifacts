package android.support.v4.media;

import android.media.AudioAttributes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiresApi(21)
class AudioAttributesCompatApi21 {
   private static final String TAG = "AudioAttributesCompat";
   private static Method sAudioAttributesToLegacyStreamType;

   public static int toLegacyStreamType(AudioAttributesCompatApi21.Wrapper aaWrap) {
      AudioAttributes aaObject = aaWrap.unwrap();

      try {
         if (sAudioAttributesToLegacyStreamType == null) {
            sAudioAttributesToLegacyStreamType = AudioAttributes.class.getMethod("toLegacyStreamType", AudioAttributes.class);
         }

         Object result = sAudioAttributesToLegacyStreamType.invoke((Object)null, aaObject);
         return ((Integer)result).intValue();
      } catch (InvocationTargetException | IllegalAccessException | ClassCastException | NoSuchMethodException var3) {
         Log.w("AudioAttributesCompat", "getLegacyStreamType() failed on API21+", var3);
         return -1;
      }
   }

   static final class Wrapper {
      private AudioAttributes mWrapped;

      private Wrapper(AudioAttributes obj) {
         this.mWrapped = obj;
      }

      public static AudioAttributesCompatApi21.Wrapper wrap(@NonNull AudioAttributes obj) {
         if (obj == null) {
            throw new IllegalArgumentException("AudioAttributesApi21.Wrapper cannot wrap null");
         } else {
            return new AudioAttributesCompatApi21.Wrapper(obj);
         }
      }

      public AudioAttributes unwrap() {
         return this.mWrapped;
      }
   }
}
