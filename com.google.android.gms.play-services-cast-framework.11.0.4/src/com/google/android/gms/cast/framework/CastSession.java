package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.Cast.ApplicationConnectionResult;
import com.google.android.gms.cast.Cast.CastApi;
import com.google.android.gms.cast.Cast.Listener;
import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzauj;
import com.google.android.gms.internal.zzaul;
import com.google.android.gms.internal.zzavn;
import com.google.android.gms.internal.zzayo;
import com.google.android.gms.internal.zzayp;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CastSession extends Session {
   private static final zzayo zzarK = new zzayo("CastSession");
   private final Context zzarM;
   private final Set zzarZ = new HashSet();
   private final zzm zzasa;
   private final CastOptions zzarQ;
   private final CastApi zzasb;
   private final zzaul zzasc;
   private final zzavn zzasd;
   private GoogleApiClient zzapu;
   private RemoteMediaClient zzase;
   private CastDevice zzasf;
   private ApplicationConnectionResult zzasg;

   public CastSession(Context var1, String var2, String var3, CastOptions var4, CastApi var5, zzaul var6, zzavn var7) {
      super(var1, var2, var3);
      this.zzarM = var1.getApplicationContext();
      this.zzarQ = var4;
      this.zzasb = var5;
      this.zzasc = var6;
      this.zzasd = var7;
      this.zzasa = zzauj.zza((Context)var1, (CastOptions)var4, (IObjectWrapper)this.zznw(), (zzi)(new CastSession.zzb((zzc)null)));
   }

   protected void start(Bundle var1) {
      this.zzj(var1);
   }

   protected void resume(Bundle var1) {
      this.zzj(var1);
   }

   protected void end(boolean var1) {
      try {
         this.zzasa.zzb(var1, 0);
      } catch (RemoteException var3) {
         zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"disconnectFromDevice", zzm.class.getSimpleName()});
      }

      this.notifySessionEnded(0);
   }

   public RemoteMediaClient getRemoteMediaClient() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzase;
   }

   public CastDevice getCastDevice() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzasf;
   }

   public void requestStatus() throws IOException, IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      if (this.zzapu != null) {
         this.zzasb.requestStatus(this.zzapu);
      }

   }

   public int getActiveInputState() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzapu != null ? this.zzasb.getActiveInputState(this.zzapu) : -1;
   }

   public int getStandbyState() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzapu != null ? this.zzasb.getStandbyState(this.zzapu) : -1;
   }

   public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzapu != null ? this.zzasb.getApplicationMetadata(this.zzapu) : null;
   }

   public String getApplicationStatus() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzapu != null ? this.zzasb.getApplicationStatus(this.zzapu) : null;
   }

   public void setVolume(double var1) throws IOException {
      zzbo.zzcz("Must be called from the main thread.");
      if (this.zzapu != null) {
         this.zzasb.setVolume(this.zzapu, var1);
      }

   }

   public double getVolume() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzapu != null ? this.zzasb.getVolume(this.zzapu) : 0.0D;
   }

   public void setMute(boolean var1) throws IOException, IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      if (this.zzapu != null) {
         this.zzasb.setMute(this.zzapu, var1);
      }

   }

   public boolean isMute() throws IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzapu != null ? this.zzasb.isMute(this.zzapu) : false;
   }

   public ApplicationConnectionResult getApplicationConnectionResult() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzasg;
   }

   public void setMessageReceivedCallbacks(String var1, MessageReceivedCallback var2) throws IOException, IllegalStateException {
      zzbo.zzcz("Must be called from the main thread.");
      if (this.zzapu != null) {
         this.zzasb.setMessageReceivedCallbacks(this.zzapu, var1, var2);
      }

   }

   public void removeMessageReceivedCallbacks(String var1) throws IOException, IllegalArgumentException {
      zzbo.zzcz("Must be called from the main thread.");
      if (this.zzapu != null) {
         this.zzasb.removeMessageReceivedCallbacks(this.zzapu, var1);
      }

   }

   public PendingResult sendMessage(String var1, String var2) {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzapu != null ? this.zzasb.sendMessage(this.zzapu, var1, var2) : null;
   }

   public void addCastListener(Listener var1) {
      zzbo.zzcz("Must be called from the main thread.");
      if (var1 != null) {
         this.zzarZ.add(var1);
      }

   }

   public void removeCastListener(Listener var1) {
      zzbo.zzcz("Must be called from the main thread.");
      if (var1 != null) {
         this.zzarZ.remove(var1);
      }

   }

   private final void zzj(Bundle var1) {
      this.zzasf = CastDevice.getFromBundle(var1);
      if (this.zzasf == null) {
         if (this.isResuming()) {
            this.notifyFailedToResumeSession(8);
         } else {
            this.notifyFailedToStartSession(8);
         }
      } else {
         if (this.zzapu != null) {
            this.zzapu.disconnect();
            this.zzapu = null;
         }

         zzarK.zzb("Acquiring a connection to Google Play Services for %s", new Object[]{this.zzasf});
         CastSession.zzd var2 = new CastSession.zzd((zzc)null);
         Context var10001 = this.zzarM;
         CastDevice var10002 = this.zzasf;
         CastOptions var10003 = this.zzarQ;
         CastSession.zzc var6 = new CastSession.zzc((zzc)null);
         CastOptions var5 = var10003;
         CastDevice var4 = var10002;
         Context var3 = var10001;
         Bundle var9;
         (var9 = new Bundle()).putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", var5 != null && var5.getCastMediaOptions() != null && var5.getCastMediaOptions().getNotificationOptions() != null);
         this.zzapu = (new Builder(var3)).addApi(Cast.API, (new com.google.android.gms.cast.Cast.CastOptions.Builder(var4, var6)).zzi(var9).build()).addConnectionCallbacks(var2).addOnConnectionFailedListener(var2).build();
         this.zzapu.connect();
      }
   }

   private final void zzY(int var1) {
      this.zzasd.zzab(var1);
      if (this.zzapu != null) {
         this.zzapu.disconnect();
         this.zzapu = null;
      }

      this.zzasf = null;
      if (this.zzase != null) {
         try {
            this.zzase.zzc((GoogleApiClient)null);
         } catch (IOException var3) {
            zzarK.zza(var3, "Exception when setting GoogleApiClient.", new Object[0]);
         }

         this.zzase = null;
      }

      this.zzasg = null;
   }

   public long getSessionRemainingTimeMs() {
      zzbo.zzcz("Must be called from the main thread.");
      return this.zzase == null ? 0L : this.zzase.getStreamDuration() - this.zzase.getApproximateStreamPosition();
   }

   class zzb extends zzj {
      // $FF: synthetic field
      private CastSession zzasi;

      private zzb() {
         this.zzasi = CastSession.this;
         super();
      }

      public final void zzt(String var1, String var2) {
         this.zzasi.zzasb.joinApplication(this.zzasi.zzapu, var1, var2).setResultCallback(this.zzasi.new zza("joinApplication"));
      }

      public final void zza(String var1, LaunchOptions var2) {
         this.zzasi.zzasb.launchApplication(this.zzasi.zzapu, var1, var2).setResultCallback(this.zzasi.new zza("launchApplication"));
      }

      public final void zzcc(String var1) {
         this.zzasi.zzasb.stopApplication(this.zzasi.zzapu, var1);
      }

      public final void zzY(int var1) {
         this.zzasi.zzY(var1);
      }

      // $FF: synthetic method
      zzb(zzc var2) {
         this();
      }
   }

   class zza implements ResultCallback {
      private String zzash;
      // $FF: synthetic field
      private CastSession zzasi;

      zza(String var2) {
         this.zzasi = CastSession.this;
         super();
         this.zzash = var2;
      }

      // $FF: synthetic method
      public final void onResult(@NonNull Result var1) {
         ApplicationConnectionResult var3 = (ApplicationConnectionResult)var1;
         CastSession.zza var2 = this;
         this.zzasi.zzasg = var3;

         try {
            if (var3.getStatus().isSuccess()) {
               CastSession.zzarK.zzb("%s() -> success result", new Object[]{var2.zzash});
               var2.zzasi.zzase = new RemoteMediaClient(new zzayp((String)null), var2.zzasi.zzasb);

               try {
                  var2.zzasi.zzase.zzc(var2.zzasi.zzapu);
                  var2.zzasi.zzase.zznX();
                  var2.zzasi.zzase.requestStatus();
                  var2.zzasi.zzasd.zza(var2.zzasi.zzase, var2.zzasi.getCastDevice());
               } catch (IOException var5) {
                  CastSession.zzarK.zza(var5, "Exception when setting GoogleApiClient.", new Object[0]);
                  var2.zzasi.zzase = null;
               }

               var2.zzasi.zzasa.zza(var3.getApplicationMetadata(), var3.getApplicationStatus(), var3.getSessionId(), var3.getWasLaunched());
            } else {
               CastSession.zzarK.zzb("%s() -> failure result", new Object[]{var2.zzash});
               var2.zzasi.zzasa.zzZ(var3.getStatus().getStatusCode());
            }
         } catch (RemoteException var6) {
            CastSession.zzarK.zzb(var6, "Unable to call %s on %s.", new Object[]{"methods", zzm.class.getSimpleName()});
         }
      }
   }

   class zzc extends Listener {
      // $FF: synthetic field
      private CastSession zzasi;

      private zzc() {
         this.zzasi = CastSession.this;
         super();
      }

      public final void onApplicationDisconnected(int var1) {
         this.zzasi.zzY(var1);
         this.zzasi.notifySessionEnded(var1);
         Iterator var2 = (new HashSet(this.zzasi.zzarZ)).iterator();

         while(var2.hasNext()) {
            ((Listener)var2.next()).onApplicationDisconnected(var1);
         }

      }

      public final void onApplicationStatusChanged() {
         Iterator var1 = (new HashSet(this.zzasi.zzarZ)).iterator();

         while(var1.hasNext()) {
            ((Listener)var1.next()).onApplicationStatusChanged();
         }

      }

      public final void onApplicationMetadataChanged(ApplicationMetadata var1) {
         Iterator var2 = (new HashSet(this.zzasi.zzarZ)).iterator();

         while(var2.hasNext()) {
            ((Listener)var2.next()).onApplicationMetadataChanged(var1);
         }

      }

      public final void onActiveInputStateChanged(int var1) {
         Iterator var2 = (new HashSet(this.zzasi.zzarZ)).iterator();

         while(var2.hasNext()) {
            ((Listener)var2.next()).onActiveInputStateChanged(var1);
         }

      }

      public final void onStandbyStateChanged(int var1) {
         Iterator var2 = (new HashSet(this.zzasi.zzarZ)).iterator();

         while(var2.hasNext()) {
            ((Listener)var2.next()).onStandbyStateChanged(var1);
         }

      }

      public final void onVolumeChanged() {
         Iterator var1 = (new HashSet(this.zzasi.zzarZ)).iterator();

         while(var1.hasNext()) {
            ((Listener)var1.next()).onVolumeChanged();
         }

      }

      // $FF: synthetic method
      zzc(zzc var2) {
         this();
      }
   }

   class zzd implements ConnectionCallbacks, OnConnectionFailedListener {
      // $FF: synthetic field
      private CastSession zzasi;

      private zzd() {
         this.zzasi = CastSession.this;
         super();
      }

      public final void onConnected(Bundle var1) {
         try {
            if (this.zzasi.zzase != null) {
               try {
                  this.zzasi.zzase.zznX();
                  this.zzasi.zzase.requestStatus();
               } catch (IOException var3) {
                  CastSession.zzarK.zza(var3, "Exception when setting GoogleApiClient.", new Object[0]);
                  this.zzasi.zzase = null;
               }
            }

            this.zzasi.zzasa.onConnected(var1);
         } catch (RemoteException var4) {
            CastSession.zzarK.zzb(var4, "Unable to call %s on %s.", new Object[]{"onConnected", zzm.class.getSimpleName()});
         }
      }

      public final void onConnectionSuspended(int var1) {
         try {
            this.zzasi.zzasa.onConnectionSuspended(var1);
         } catch (RemoteException var3) {
            CastSession.zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"onConnectionSuspended", zzm.class.getSimpleName()});
         }
      }

      public final void onConnectionFailed(@NonNull ConnectionResult var1) {
         try {
            this.zzasi.zzasa.onConnectionFailed(var1);
         } catch (RemoteException var3) {
            CastSession.zzarK.zzb(var3, "Unable to call %s on %s.", new Object[]{"onConnectionFailed", zzm.class.getSimpleName()});
         }
      }

      // $FF: synthetic method
      zzd(zzc var2) {
         this();
      }
   }
}
