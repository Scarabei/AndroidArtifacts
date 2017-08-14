package com.google.android.gms.cast;

import android.content.Context;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzayh;
import com.google.android.gms.internal.zzayn;
import com.google.android.gms.internal.zzayw;
import com.google.android.gms.internal.zzbzu;
import com.google.android.gms.internal.zzcaf;

public final class CastRemoteDisplay {
   public static final int CONFIGURATION_INTERACTIVE_REALTIME = 1;
   public static final int CONFIGURATION_INTERACTIVE_NONREALTIME = 2;
   public static final int CONFIGURATION_NONINTERACTIVE = 3;
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zzo();
   public static final Api API;
   public static final CastRemoteDisplayApi CastRemoteDisplayApi;

   public static final boolean isRemoteDisplaySdkSupported(Context var0) {
      zzayh.initialize(var0);
      zzbzu var1 = zzayh.zzayo;
      return ((Boolean)zzcaf.zzuc().zzb(var1)).booleanValue();
   }

   static {
      API = new Api("CastRemoteDisplay.API", zzajS, zzayn.zzayq);
      CastRemoteDisplayApi = new zzayw(API);
   }

   public static final class CastRemoteDisplayOptions implements HasOptions {
      final CastDevice zzaoU;
      final CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzapm;
      final int zzapn;

      private CastRemoteDisplayOptions(CastRemoteDisplay.CastRemoteDisplayOptions.Builder var1) {
         this.zzaoU = var1.zzaoX;
         this.zzapm = var1.zzapo;
         this.zzapn = var1.zzapp;
      }

      // $FF: synthetic method
      CastRemoteDisplayOptions(CastRemoteDisplay.CastRemoteDisplayOptions.Builder var1, zzo var2) {
         this(var1);
      }

      public static final class Builder {
         CastDevice zzaoX;
         CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzapo;
         int zzapp;

         public Builder(CastDevice var1, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks var2) {
            zzbo.zzb(var1, "CastDevice parameter cannot be null");
            this.zzaoX = var1;
            this.zzapo = var2;
            this.zzapp = 2;
         }

         public final CastRemoteDisplay.CastRemoteDisplayOptions.Builder setConfigPreset(@CastRemoteDisplay.Configuration int var1) {
            this.zzapp = var1;
            return this;
         }

         public final CastRemoteDisplay.CastRemoteDisplayOptions build() {
            return new CastRemoteDisplay.CastRemoteDisplayOptions(this, (zzo)null);
         }
      }
   }

   public interface CastRemoteDisplaySessionResult extends Result {
      Display getPresentationDisplay();
   }

   public interface CastRemoteDisplaySessionCallbacks {
      void onRemoteDisplayEnded(Status var1);
   }

   public @interface Configuration {
   }
}
