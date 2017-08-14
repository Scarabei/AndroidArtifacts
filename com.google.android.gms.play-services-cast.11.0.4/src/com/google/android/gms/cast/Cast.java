package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzaxs;
import com.google.android.gms.internal.zzaxx;
import com.google.android.gms.internal.zzayn;
import java.io.IOException;

public final class Cast {
   public static final int MAX_MESSAGE_LENGTH = 65536;
   public static final int MAX_NAMESPACE_LENGTH = 128;
   public static final int ACTIVE_INPUT_STATE_UNKNOWN = -1;
   public static final int ACTIVE_INPUT_STATE_NO = 0;
   public static final int ACTIVE_INPUT_STATE_YES = 1;
   public static final int STANDBY_STATE_UNKNOWN = -1;
   public static final int STANDBY_STATE_NO = 0;
   public static final int STANDBY_STATE_YES = 1;
   public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
   private static com.google.android.gms.common.api.Api.zza zzajS = new zze();
   public static final Api API;
   public static final Cast.CastApi CastApi;

   static {
      API = new Api("Cast.API", zzajS, zzayn.zzayp);
      CastApi = new Cast.CastApi.zza();
   }

   /** @deprecated */
   @Deprecated
   public interface CastApi {
      void requestStatus(GoogleApiClient var1) throws IOException, IllegalStateException;

      PendingResult sendMessage(GoogleApiClient var1, String var2, String var3);

      PendingResult launchApplication(GoogleApiClient var1, String var2);

      /** @deprecated */
      @Deprecated
      PendingResult launchApplication(GoogleApiClient var1, String var2, boolean var3);

      PendingResult launchApplication(GoogleApiClient var1, String var2, LaunchOptions var3);

      PendingResult joinApplication(GoogleApiClient var1, String var2, String var3);

      PendingResult joinApplication(GoogleApiClient var1, String var2);

      PendingResult joinApplication(GoogleApiClient var1);

      PendingResult leaveApplication(GoogleApiClient var1);

      PendingResult stopApplication(GoogleApiClient var1);

      PendingResult stopApplication(GoogleApiClient var1, String var2);

      void setVolume(GoogleApiClient var1, double var2) throws IOException, IllegalArgumentException, IllegalStateException;

      double getVolume(GoogleApiClient var1) throws IllegalStateException;

      void setMute(GoogleApiClient var1, boolean var2) throws IOException, IllegalStateException;

      boolean isMute(GoogleApiClient var1) throws IllegalStateException;

      int getActiveInputState(GoogleApiClient var1) throws IllegalStateException;

      int getStandbyState(GoogleApiClient var1) throws IllegalStateException;

      ApplicationMetadata getApplicationMetadata(GoogleApiClient var1) throws IllegalStateException;

      String getApplicationStatus(GoogleApiClient var1) throws IllegalStateException;

      void setMessageReceivedCallbacks(GoogleApiClient var1, String var2, Cast.MessageReceivedCallback var3) throws IOException, IllegalStateException;

      void removeMessageReceivedCallbacks(GoogleApiClient var1, String var2) throws IOException, IllegalArgumentException;

      public static final class zza implements Cast.CastApi {
         public final void requestStatus(GoogleApiClient var1) throws IOException, IllegalStateException {
            try {
               ((zzaxx)var1.zza(zzayn.zzayp)).requestStatus();
            } catch (RemoteException var2) {
               throw new IOException("service error");
            }
         }

         public final PendingResult sendMessage(GoogleApiClient var1, String var2, String var3) {
            return var1.zze(new zzf(this, var1, var2, var3));
         }

         public final PendingResult launchApplication(GoogleApiClient var1, String var2) {
            return var1.zze(new zzg(this, var1, var2));
         }

         public final PendingResult launchApplication(GoogleApiClient var1, String var2, LaunchOptions var3) {
            return var1.zze(new zzh(this, var1, var2, var3));
         }

         /** @deprecated */
         @Deprecated
         public final PendingResult launchApplication(GoogleApiClient var1, String var2, boolean var3) {
            LaunchOptions var4 = (new LaunchOptions.Builder()).setRelaunchIfRunning(var3).build();
            return this.launchApplication(var1, var2, var4);
         }

         private final PendingResult zza(GoogleApiClient var1, String var2, String var3, zzz var4) {
            return var1.zze(new zzi(this, var1, var2, var3, (zzz)null));
         }

         public final PendingResult joinApplication(GoogleApiClient var1, String var2, String var3) {
            return this.zza(var1, var2, var3, (zzz)null);
         }

         public final PendingResult joinApplication(GoogleApiClient var1, String var2) {
            return this.zza(var1, var2, (String)null, (zzz)null);
         }

         public final PendingResult joinApplication(GoogleApiClient var1) {
            return this.zza(var1, (String)null, (String)null, (zzz)null);
         }

         public final PendingResult leaveApplication(GoogleApiClient var1) {
            return var1.zze(new zzj(this, var1));
         }

         public final PendingResult stopApplication(GoogleApiClient var1) {
            return var1.zze(new zzk(this, var1));
         }

         public final PendingResult stopApplication(GoogleApiClient var1, String var2) {
            return var1.zze(new zzl(this, var1, var2));
         }

         public final void setVolume(GoogleApiClient var1, double var2) throws IOException, IllegalArgumentException, IllegalStateException {
            try {
               ((zzaxx)var1.zza(zzayn.zzayp)).setVolume(var2);
            } catch (RemoteException var4) {
               throw new IOException("service error");
            }
         }

         public final double getVolume(GoogleApiClient var1) throws IllegalStateException {
            return ((zzaxx)var1.zza(zzayn.zzayp)).getVolume();
         }

         public final void setMute(GoogleApiClient var1, boolean var2) throws IOException, IllegalStateException {
            try {
               ((zzaxx)var1.zza(zzayn.zzayp)).setMute(var2);
            } catch (RemoteException var3) {
               throw new IOException("service error");
            }
         }

         public final boolean isMute(GoogleApiClient var1) throws IllegalStateException {
            return ((zzaxx)var1.zza(zzayn.zzayp)).isMute();
         }

         public final int getActiveInputState(GoogleApiClient var1) throws IllegalStateException {
            return ((zzaxx)var1.zza(zzayn.zzayp)).getActiveInputState();
         }

         public final int getStandbyState(GoogleApiClient var1) throws IllegalStateException {
            return ((zzaxx)var1.zza(zzayn.zzayp)).getStandbyState();
         }

         public final ApplicationMetadata getApplicationMetadata(GoogleApiClient var1) throws IllegalStateException {
            return ((zzaxx)var1.zza(zzayn.zzayp)).getApplicationMetadata();
         }

         public final String getApplicationStatus(GoogleApiClient var1) throws IllegalStateException {
            return ((zzaxx)var1.zza(zzayn.zzayp)).getApplicationStatus();
         }

         public final void setMessageReceivedCallbacks(GoogleApiClient var1, String var2, Cast.MessageReceivedCallback var3) throws IOException, IllegalStateException {
            try {
               ((zzaxx)var1.zza(zzayn.zzayp)).setMessageReceivedCallbacks(var2, var3);
            } catch (RemoteException var4) {
               throw new IOException("service error");
            }
         }

         public final void removeMessageReceivedCallbacks(GoogleApiClient var1, String var2) throws IOException, IllegalArgumentException {
            try {
               ((zzaxx)var1.zza(zzayn.zzayp)).removeMessageReceivedCallbacks(var2);
            } catch (RemoteException var3) {
               throw new IOException("service error");
            }
         }
      }
   }

   abstract static class zza extends zzaxs {
      public zza(GoogleApiClient var1) {
         super(var1);
      }

      public void zza(zzaxx var1) throws RemoteException {
      }

      // $FF: synthetic method
      public final Result zzb(Status var1) {
         return new zzm(this, var1);
      }
   }

   public interface ApplicationConnectionResult extends Result {
      ApplicationMetadata getApplicationMetadata();

      String getApplicationStatus();

      String getSessionId();

      boolean getWasLaunched();
   }

   public static final class CastOptions implements HasOptions {
      final CastDevice zzaoU;
      final Cast.Listener zzaoV;
      final Bundle extras;
      private final int zzaoW;

      private CastOptions(Cast.CastOptions.Builder var1) {
         this.zzaoU = var1.zzaoX;
         this.zzaoV = var1.zzaoY;
         this.zzaoW = var1.zzaoZ;
         this.extras = var1.mExtras;
      }

      /** @deprecated */
      @Deprecated
      public static Cast.CastOptions.Builder builder(CastDevice var0, Cast.Listener var1) {
         return new Cast.CastOptions.Builder(var0, var1);
      }

      // $FF: synthetic method
      CastOptions(Cast.CastOptions.Builder var1, zze var2) {
         this(var1);
      }

      // $FF: synthetic method
      static int zza(Cast.CastOptions var0) {
         return var0.zzaoW;
      }

      public static final class Builder {
         CastDevice zzaoX;
         Cast.Listener zzaoY;
         private int zzaoZ;
         private Bundle mExtras;

         public Builder(CastDevice var1, Cast.Listener var2) {
            zzbo.zzb(var1, "CastDevice parameter cannot be null");
            zzbo.zzb(var2, "CastListener parameter cannot be null");
            this.zzaoX = var1;
            this.zzaoY = var2;
            this.zzaoZ = 0;
         }

         public final Cast.CastOptions.Builder setVerboseLoggingEnabled(boolean var1) {
            if (var1) {
               this.zzaoZ |= 1;
            } else {
               this.zzaoZ &= -2;
            }

            return this;
         }

         public final Cast.CastOptions.Builder zzi(Bundle var1) {
            this.mExtras = var1;
            return this;
         }

         public final Cast.CastOptions build() {
            return new Cast.CastOptions(this, (zze)null);
         }
      }
   }

   public interface MessageReceivedCallback {
      void onMessageReceived(CastDevice var1, String var2, String var3);
   }

   public static class Listener {
      public void onApplicationStatusChanged() {
      }

      public void onApplicationMetadataChanged(ApplicationMetadata var1) {
      }

      public void onApplicationDisconnected(int var1) {
      }

      public void onActiveInputStateChanged(int var1) {
      }

      public void onStandbyStateChanged(int var1) {
      }

      public void onVolumeChanged() {
      }
   }
}
