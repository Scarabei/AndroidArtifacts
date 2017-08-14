package com.google.android.gms.awareness.state;

import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.internal.zzasv;
import java.util.List;

public interface BeaconState {
   List getBeaconInfo();

   public abstract static class TypeFilter extends zza {
      public static BeaconState.TypeFilter with(String var0, String var1) {
         return new zzasv(var0, var1);
      }

      public static BeaconState.TypeFilter with(String var0, String var1, byte[] var2) {
         return new zzasv(var0, var1, var2);
      }
   }

   public interface BeaconInfo {
      String getNamespace();

      String getType();

      byte[] getContent();
   }
}
