package com.google.android.gms.drive;

import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.internal.zzbmh;
import java.util.Arrays;

public class ExecutionOptions {
   public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
   public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
   public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
   private final String zzaMr;
   private final boolean zzaMs;
   private final int zzaMt;

   public ExecutionOptions(String var1, boolean var2, int var3) {
      this.zzaMr = var1;
      this.zzaMs = var2;
      this.zzaMt = var3;
   }

   public final String zzsP() {
      return this.zzaMr;
   }

   public final boolean zzsQ() {
      return this.zzaMs;
   }

   public final int zzsR() {
      return this.zzaMt;
   }

   public final void zze(GoogleApiClient var1) {
      zzbmh var2 = (zzbmh)var1.zza(Drive.zzajR);
      if (this.zzaMs && !var2.zzti()) {
         throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to be notified on completion");
      }
   }

   public boolean equals(Object var1) {
      if (var1 != null && var1.getClass() == this.getClass()) {
         if (var1 == this) {
            return true;
         } else {
            ExecutionOptions var2 = (ExecutionOptions)var1;
            return zzbe.equal(this.zzaMr, var2.zzaMr) && this.zzaMt == var2.zzaMt && this.zzaMs == var2.zzaMs;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzaMr, this.zzaMt, this.zzaMs});
   }

   public static class Builder {
      protected String zzaMr;
      protected boolean zzaMs;
      protected int zzaMt = 0;

      public ExecutionOptions.Builder setTrackingTag(String var1) {
         if (TextUtils.isEmpty(var1) || var1.length() > 65536) {
            throw new IllegalArgumentException(String.format("trackingTag must not be null nor empty, and the length must be <= the maximum length (%s)", 65536));
         } else {
            this.zzaMr = var1;
            return this;
         }
      }

      public ExecutionOptions.Builder setNotifyOnCompletion(boolean var1) {
         this.zzaMs = var1;
         return this;
      }

      public ExecutionOptions.Builder setConflictStrategy(int var1) {
         boolean var10000;
         switch(var1) {
         case 0:
         case 1:
            var10000 = true;
            break;
         default:
            var10000 = false;
         }

         if (!var10000) {
            throw new IllegalArgumentException((new StringBuilder(53)).append("Unrecognized value for conflict strategy: ").append(var1).toString());
         } else {
            this.zzaMt = var1;
            return this;
         }
      }

      protected final void zzsS() {
         if (this.zzaMt == 1 && !this.zzaMs) {
            throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
         }
      }

      public ExecutionOptions build() {
         this.zzsS();
         return new ExecutionOptions(this.zzaMr, this.zzaMs, this.zzaMt);
      }
   }
}
