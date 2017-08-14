package com.google.android.gms.nearby.messages.audio;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.nearby.messages.Message;
import java.util.Arrays;

public final class AudioBytes {
   public static final int MAX_SIZE = 10;
   private final byte[] zzbyC;

   public AudioBytes(byte[] var1) {
      zzbo.zzu(var1);
      zzbo.zzb(var1.length <= 10, "Given byte array longer than 10 bytes, given by AudioBytes.MAX_SIZE.");
      zzbo.zzb(var1.length > 0, "Given byte array is of zero length.");
      this.zzbyC = var1;
   }

   public static AudioBytes from(Message var0) {
      zzbo.zzu(var0);
      boolean var10000 = var0.zzeD("__audio_bytes");
      String var1 = String.valueOf(var0.getType());
      zzbo.zzb(var10000, (new StringBuilder(56 + String.valueOf(var1).length())).append("Message type '").append(var1).append("' is not Message.MESSAGE_TYPE_AUDIO_BYTES.").toString());
      return new AudioBytes(var0.getContent());
   }

   public final byte[] getBytes() {
      return this.zzbyC;
   }

   public final Message toMessage() {
      return new Message(this.zzbyC, "__reserved_namespace", "__audio_bytes");
   }

   public final String toString() {
      String var1 = String.valueOf(Arrays.toString(this.zzbyC));
      return (new StringBuilder(14 + String.valueOf(var1).length())).append("AudioBytes [").append(var1).append(" ]").toString();
   }
}
