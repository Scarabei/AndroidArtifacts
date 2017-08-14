package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzz;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public final class zzaxx extends zzz {
   private static final zzayo zzapq = new zzayo("CastClientImpl");
   private ApplicationMetadata zzaxL;
   private final CastDevice zzasf;
   private final Cast.Listener zzaoY;
   private final Map zzaxM;
   private final long zzaxN;
   private final Bundle mExtras;
   private zzaxz zzaxO;
   private String zzaxP;
   private boolean zzaqE;
   private boolean zzaxQ;
   private boolean zzaxR;
   private boolean zzaxS;
   private double zzaqD;
   private int zzaxT;
   private int zzaxU;
   private final AtomicLong zzaxV;
   private String zzaxW;
   private String zzaxX;
   private Bundle zzaxY;
   private final Map zzaxZ;
   private zzbaz zzaya;
   private zzbaz zzayb;
   private static final Object zzayc = new Object();
   private static final Object zzayd = new Object();

   public zzaxx(Context var1, Looper var2, zzq var3, CastDevice var4, long var5, Cast.Listener var7, Bundle var8, ConnectionCallbacks var9, OnConnectionFailedListener var10) {
      super(var1, var2, 10, var3, var9, var10);
      this.zzasf = var4;
      this.zzaoY = var7;
      this.zzaxN = var5;
      this.mExtras = var8;
      this.zzaxM = new HashMap();
      this.zzaxV = new AtomicLong(0L);
      this.zzaxZ = new HashMap();
      this.zzoB();
   }

   private final void zzoB() {
      this.zzaxS = false;
      this.zzaxT = -1;
      this.zzaxU = -1;
      this.zzaxL = null;
      this.zzaxP = null;
      this.zzaqD = 0.0D;
      this.zzaqE = false;
   }

   protected final void zza(int var1, IBinder var2, Bundle var3, int var4) {
      zzapq.zzb("in onPostInitHandler; statusCode=%d", var1);
      if (var1 != 0 && var1 != 1001) {
         this.zzaxS = false;
      } else {
         this.zzaxS = true;
         this.zzaxQ = true;
         this.zzaxR = true;
      }

      int var5 = var1;
      if (var1 == 1001) {
         this.zzaxY = new Bundle();
         this.zzaxY.putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
         var5 = 0;
      }

      super.zza(var5, var2, var3, var4);
   }

   public final void disconnect() {
      zzapq.zzb("disconnect(); ServiceListener=%s, isConnected=%b", this.zzaxO, this.isConnected());
      zzaxz var1 = this.zzaxO;
      this.zzaxO = null;
      if (var1 != null && var1.zzoI() != null) {
         this.zzoD();

         try {
            ((zzayj)super.zzrf()).disconnect();
            return;
         } catch (IllegalStateException | RemoteException var6) {
            zzapq.zzb(var6, "Error while disconnecting the controller interface: %s", var6.getMessage());
         } finally {
            super.disconnect();
         }

      } else {
         zzapq.zzb("already disposed, so short-circuiting");
      }
   }

   public final Bundle zzoC() {
      if (this.zzaxY != null) {
         Bundle var1 = this.zzaxY;
         this.zzaxY = null;
         return var1;
      } else {
         return super.zzoC();
      }
   }

   @NonNull
   protected final String zzdb() {
      return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
   }

   @NonNull
   protected final String zzdc() {
      return "com.google.android.gms.cast.internal.ICastDeviceController";
   }

   protected final Bundle zzmo() {
      Bundle var1 = new Bundle();
      zzapq.zzb("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzaxW, this.zzaxX);
      this.zzasf.putInBundle(var1);
      var1.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzaxN);
      if (this.mExtras != null) {
         var1.putAll(this.mExtras);
      }

      this.zzaxO = new zzaxz(this);
      var1.putParcelable("listener", new BinderWrapper(this.zzaxO.asBinder()));
      if (this.zzaxW != null) {
         var1.putString("last_application_id", this.zzaxW);
         if (this.zzaxX != null) {
            var1.putString("last_session_id", this.zzaxX);
         }
      }

      return var1;
   }

   public final void zza(String var1, String var2, zzbaz var3) throws IllegalArgumentException, IllegalStateException, RemoteException {
      if (TextUtils.isEmpty(var2)) {
         throw new IllegalArgumentException("The message payload cannot be null or empty");
      } else if (var2.length() > 65536) {
         throw new IllegalArgumentException("Message exceeds maximum size");
      } else {
         zzaye.zzci(var1);
         this.zzoE();
         long var4 = this.zzaxV.incrementAndGet();

         try {
            this.zzaxZ.put(var4, var3);
            ((zzayj)super.zzrf()).zzb(var1, var2, var4);
         } catch (Throwable var7) {
            this.zzaxZ.remove(var4);
            throw var7;
         }
      }
   }

   public final void zza(String var1, LaunchOptions var2, zzbaz var3) throws IllegalStateException, RemoteException {
      this.zza(var3);
      ((zzayj)super.zzrf()).zzb(var1, var2);
   }

   public final void zza(String var1, String var2, com.google.android.gms.cast.zzz var3, zzbaz var4) throws IllegalStateException, RemoteException {
      this.zza(var4);
      if (var3 == null) {
         var3 = new com.google.android.gms.cast.zzz();
      }

      ((zzayj)super.zzrf()).zza(var1, var2, var3);
   }

   private final void zza(zzbaz var1) {
      Object var2 = zzayc;
      synchronized(zzayc) {
         if (this.zzaya != null) {
            this.zzaya.setResult(new zzaxy(new Status(2002)));
         }

         this.zzaya = var1;
      }
   }

   public final void zzb(zzbaz var1) throws IllegalStateException, RemoteException {
      this.zzc(var1);
      ((zzayj)super.zzrf()).zzoK();
   }

   public final void zza(String var1, zzbaz var2) throws IllegalStateException, RemoteException {
      this.zzc(var2);
      ((zzayj)super.zzrf()).zzcc(var1);
   }

   private final void zzc(zzbaz var1) {
      Object var2 = zzayd;
      synchronized(zzayd) {
         if (this.zzayb != null) {
            var1.setResult(new Status(2001));
         } else {
            this.zzayb = var1;
         }
      }
   }

   public final void requestStatus() throws IllegalStateException, RemoteException {
      ((zzayj)super.zzrf()).requestStatus();
   }

   public final void setVolume(double var1) throws IllegalArgumentException, IllegalStateException, RemoteException {
      if (!Double.isInfinite(var1) && !Double.isNaN(var1)) {
         ((zzayj)super.zzrf()).zza(var1, this.zzaqD, this.zzaqE);
      } else {
         throw new IllegalArgumentException((new StringBuilder(41)).append("Volume cannot be ").append(var1).toString());
      }
   }

   public final void setMute(boolean var1) throws IllegalStateException, RemoteException {
      ((zzayj)super.zzrf()).zza(var1, this.zzaqD, this.zzaqE);
   }

   public final double getVolume() throws IllegalStateException {
      this.zzoE();
      return this.zzaqD;
   }

   public final boolean isMute() throws IllegalStateException {
      this.zzoE();
      return this.zzaqE;
   }

   public final int getActiveInputState() throws IllegalStateException {
      this.zzoE();
      return this.zzaxT;
   }

   public final int getStandbyState() throws IllegalStateException {
      this.zzoE();
      return this.zzaxU;
   }

   public final void setMessageReceivedCallbacks(String var1, Cast.MessageReceivedCallback var2) throws IllegalArgumentException, IllegalStateException, RemoteException {
      zzaye.zzci(var1);
      this.removeMessageReceivedCallbacks(var1);
      if (var2 != null) {
         Map var3 = this.zzaxM;
         synchronized(this.zzaxM) {
            this.zzaxM.put(var1, var2);
         }

         ((zzayj)super.zzrf()).zzcl(var1);
      }

   }

   public final void removeMessageReceivedCallbacks(String var1) throws IllegalArgumentException, RemoteException {
      if (TextUtils.isEmpty(var1)) {
         throw new IllegalArgumentException("Channel namespace cannot be null or empty");
      } else {
         Map var3 = this.zzaxM;
         Cast.MessageReceivedCallback var2;
         synchronized(this.zzaxM) {
            var2 = (Cast.MessageReceivedCallback)this.zzaxM.remove(var1);
         }

         if (var2 != null) {
            try {
               ((zzayj)super.zzrf()).zzcm(var1);
               return;
            } catch (IllegalStateException var6) {
               zzapq.zzb(var6, "Error unregistering namespace (%s): %s", var1, var6.getMessage());
            }
         }

      }
   }

   public final ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
      this.zzoE();
      return this.zzaxL;
   }

   public final String getApplicationStatus() throws IllegalStateException {
      this.zzoE();
      return this.zzaxP;
   }

   public final void onConnectionFailed(ConnectionResult var1) {
      super.onConnectionFailed(var1);
      this.zzoD();
   }

   private final void zzoD() {
      zzapq.zzb("removing all MessageReceivedCallbacks");
      Map var1 = this.zzaxM;
      synchronized(this.zzaxM) {
         this.zzaxM.clear();
      }
   }

   private final void zza(zzayf var1) {
      ApplicationMetadata var2;
      if (!zzaye.zza(var2 = var1.getApplicationMetadata(), this.zzaxL)) {
         this.zzaxL = var2;
         this.zzaoY.onApplicationMetadataChanged(this.zzaxL);
      }

      boolean var3 = false;
      double var4;
      if (!Double.isNaN(var4 = var1.getVolume()) && Math.abs(var4 - this.zzaqD) > 1.0E-7D) {
         this.zzaqD = var4;
         var3 = true;
      }

      boolean var6;
      if ((var6 = var1.zzoJ()) != this.zzaqE) {
         this.zzaqE = var6;
         var3 = true;
      }

      zzapq.zzb("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", var3, this.zzaxR);
      if (this.zzaoY != null && (var3 || this.zzaxR)) {
         this.zzaoY.onVolumeChanged();
      }

      boolean var7 = false;
      int var8;
      if ((var8 = var1.getActiveInputState()) != this.zzaxT) {
         this.zzaxT = var8;
         var7 = true;
      }

      zzapq.zzb("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", var7, this.zzaxR);
      if (this.zzaoY != null && (var7 || this.zzaxR)) {
         this.zzaoY.onActiveInputStateChanged(this.zzaxT);
      }

      boolean var9 = false;
      int var10;
      if ((var10 = var1.getStandbyState()) != this.zzaxU) {
         this.zzaxU = var10;
         var9 = true;
      }

      zzapq.zzb("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", var9, this.zzaxR);
      if (this.zzaoY != null && (var9 || this.zzaxR)) {
         this.zzaoY.onStandbyStateChanged(this.zzaxU);
      }

      this.zzaxR = false;
   }

   private final void zza(zzaxq var1) {
      boolean var2 = false;
      String var3;
      if (!zzaye.zza(var3 = var1.zzoy(), this.zzaxP)) {
         this.zzaxP = var3;
         var2 = true;
      }

      zzapq.zzb("hasChanged=%b, mFirstApplicationStatusUpdate=%b", var2, this.zzaxQ);
      if (this.zzaoY != null && (var2 || this.zzaxQ)) {
         this.zzaoY.onApplicationStatusChanged();
      }

      this.zzaxQ = false;
   }

   private final void zzoE() throws IllegalStateException {
      if (!this.zzaxS || this.zzaxO == null || this.zzaxO.isDisposed()) {
         throw new IllegalStateException("Not connected to a device");
      }
   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController")) instanceof zzayj ? (zzayj)var3 : new zzayk(var1));
      }
   }

   // $FF: synthetic method
   static void zzb(zzaxx var0) {
      var0.zzoB();
   }

   // $FF: synthetic method
   static zzayo zzoF() {
      return zzapq;
   }

   // $FF: synthetic method
   static ApplicationMetadata zza(zzaxx var0, ApplicationMetadata var1) {
      return var0.zzaxL = var1;
   }

   // $FF: synthetic method
   static String zza(zzaxx var0, String var1) {
      return var0.zzaxW = var1;
   }

   // $FF: synthetic method
   static String zzb(zzaxx var0, String var1) {
      return var0.zzaxX = var1;
   }

   // $FF: synthetic method
   static String zzc(zzaxx var0, String var1) {
      return var0.zzaxP = var1;
   }

   // $FF: synthetic method
   static Object zzoG() {
      return zzayc;
   }

   // $FF: synthetic method
   static zzbaz zzc(zzaxx var0) {
      return var0.zzaya;
   }

   // $FF: synthetic method
   static zzbaz zza(zzaxx var0, zzbaz var1) {
      return var0.zzaya = null;
   }

   // $FF: synthetic method
   static Cast.Listener zzd(zzaxx var0) {
      return var0.zzaoY;
   }

   // $FF: synthetic method
   static void zza(zzaxx var0, zzayf var1) {
      var0.zza(var1);
   }

   // $FF: synthetic method
   static void zza(zzaxx var0, zzaxq var1) {
      var0.zza(var1);
   }

   // $FF: synthetic method
   static Map zze(zzaxx var0) {
      return var0.zzaxM;
   }

   // $FF: synthetic method
   static CastDevice zzf(zzaxx var0) {
      return var0.zzasf;
   }

   // $FF: synthetic method
   static Map zzg(zzaxx var0) {
      return var0.zzaxZ;
   }

   // $FF: synthetic method
   static Object zzoH() {
      return zzayd;
   }

   // $FF: synthetic method
   static zzbaz zzh(zzaxx var0) {
      return var0.zzayb;
   }

   // $FF: synthetic method
   static zzbaz zzb(zzaxx var0, zzbaz var1) {
      return var0.zzayb = null;
   }
}
