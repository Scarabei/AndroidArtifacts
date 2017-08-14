package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.ads.internal.zzbs;
import com.google.android.gms.internal.zzme;
import com.google.android.gms.internal.zzmo;
import com.google.android.gms.internal.zzzn;
import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzaq {
   private String zzQt;
   private boolean zzQu;
   private int zzQv;
   private int zzQw;

   public zzaq(String var1) {
      JSONObject var2 = null;

      try {
         if (var1 != null) {
            var2 = new JSONObject(var1);
         }
      } catch (JSONException var3) {
         ;
      }

      this.zzQu = zza(var2, "acquire_decoder_before_play", zzmo.zzCB);
      this.zzQt = zzc(var2, "exo_player_version", zzmo.zzCk);
      this.zzQw = zzb(var2, "exo_cache_buffer_size", zzmo.zzCp);
      this.zzQv = zzb(var2, "exo_allocator_segment_size", zzmo.zzCo);
   }

   private static boolean zza(JSONObject var0, String var1, zzme var2) {
      try {
         if (var0 != null) {
            return var0.getBoolean(var1);
         }
      } catch (JSONException var4) {
         ;
      }

      return ((Boolean)zzbs.zzbL().zzd(var2)).booleanValue();
   }

   private static int zzb(JSONObject var0, String var1, zzme var2) {
      try {
         if (var0 != null) {
            return var0.getInt(var1);
         }
      } catch (JSONException var4) {
         ;
      }

      return ((Integer)zzbs.zzbL().zzd(var2)).intValue();
   }

   private static String zzc(JSONObject var0, String var1, zzme var2) {
      try {
         if (var0 != null) {
            return var0.getString(var1);
         }
      } catch (JSONException var4) {
         ;
      }

      return (String)zzbs.zzbL().zzd(var2);
   }
}
