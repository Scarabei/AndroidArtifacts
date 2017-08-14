package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.UserMetadata;
import java.util.Arrays;
import java.util.Collections;

public final class zzu extends zzm {
   public zzu(String var1, int var2) {
      super(var1, Arrays.asList(zzB(var1, "permissionId"), zzB(var1, "displayName"), zzB(var1, "picture"), zzB(var1, "isAuthenticatedUser"), zzB(var1, "emailAddress")), Collections.emptyList(), 6000000);
   }

   protected final boolean zzb(DataHolder var1, int var2, int var3) {
      return var1.zzcv(this.zzcT("permissionId")) && !var1.zzh(this.zzcT("permissionId"), var2, var3);
   }

   private final String zzcT(String var1) {
      return zzB(this.getName(), var1);
   }

   private static String zzB(String var0, String var1) {
      return (new StringBuilder(1 + String.valueOf(var0).length() + String.valueOf(var1).length())).append(var0).append(".").append(var1).toString();
   }

   // $FF: synthetic method
   protected final Object zzc(DataHolder var1, int var2, int var3) {
      String var8;
      if ((var8 = var1.zzd(this.zzcT("permissionId"), var2, var3)) != null) {
         String var9 = var1.zzd(this.zzcT("displayName"), var2, var3);
         String var10 = var1.zzd(this.zzcT("picture"), var2, var3);
         Boolean var11 = var1.zze(this.zzcT("isAuthenticatedUser"), var2, var3);
         String var12 = var1.zzd(this.zzcT("emailAddress"), var2, var3);
         return new UserMetadata(var8, var9, var10, var11.booleanValue(), var12);
      } else {
         return null;
      }
   }
}
