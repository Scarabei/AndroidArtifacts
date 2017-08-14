package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.nearby.connection.AdvertisingOptions;
import com.google.android.gms.nearby.connection.AppMetadata;
import com.google.android.gms.nearby.connection.ConnectionLifecycleCallback;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.DiscoveryOptions;
import com.google.android.gms.nearby.connection.EndpointDiscoveryCallback;
import com.google.android.gms.nearby.connection.Payload;
import com.google.android.gms.nearby.connection.PayloadCallback;
import java.util.List;

public final class zzclm implements Connections {
   public static final zzf zzajR = new zzf();
   public static final zza zzajS = new zzcln();

   public final PendingResult startAdvertising(GoogleApiClient var1, String var2, String var3, ConnectionLifecycleCallback var4, AdvertisingOptions var5) {
      zzbdw var6 = var1.zzp(var4);
      return var1.zze(new zzcly(this, var1, var2, var3, var6, var5));
   }

   public final void stopAdvertising(GoogleApiClient var1) {
      var1.zze(new zzclz(this, var1));
   }

   public final PendingResult startDiscovery(GoogleApiClient var1, String var2, EndpointDiscoveryCallback var3, DiscoveryOptions var4) {
      zzbdw var5 = var1.zzp(var3);
      return var1.zze(new zzcma(this, var1, var2, var5, var4));
   }

   public final void stopDiscovery(GoogleApiClient var1) {
      var1.zze(new zzcmb(this, var1));
   }

   public final PendingResult requestConnection(GoogleApiClient var1, @Nullable String var2, String var3, ConnectionLifecycleCallback var4) {
      zzbdw var5 = var1.zzp(var4);
      return var1.zze(new zzcmc(this, var1, var2, var3, var5));
   }

   public final PendingResult acceptConnection(GoogleApiClient var1, String var2, PayloadCallback var3) {
      zzbdw var4 = var1.zzp(var3);
      return var1.zze(new zzcmd(this, var1, var2, var4));
   }

   public final PendingResult rejectConnection(GoogleApiClient var1, String var2) {
      return var1.zze(new zzcme(this, var1, var2));
   }

   public final PendingResult sendPayload(GoogleApiClient var1, String var2, Payload var3) {
      return var1.zze(new zzcmf(this, var1, var2, var3));
   }

   public final PendingResult sendPayload(GoogleApiClient var1, List var2, Payload var3) {
      return var1.zze(new zzclo(this, var1, var2, var3));
   }

   public final void disconnectFromEndpoint(GoogleApiClient var1, String var2) {
      var1.zze(new zzclp(this, var1, var2));
   }

   public final void stopAllEndpoints(GoogleApiClient var1) {
      var1.zze(new zzclq(this, var1));
   }

   /** @deprecated */
   @Deprecated
   public final PendingResult startAdvertising(GoogleApiClient var1, String var2, AppMetadata var3, long var4, Connections.ConnectionRequestListener var6) {
      zzbdw var7 = var1.zzp(var6);
      return var1.zze(new zzclr(this, var1, var2, var4, var7));
   }

   /** @deprecated */
   @Deprecated
   public final PendingResult startDiscovery(GoogleApiClient var1, String var2, long var3, Connections.EndpointDiscoveryListener var5) {
      zzbdw var6 = var1.zzp(var5);
      return var1.zze(new zzcls(this, var1, var2, var3, var6));
   }

   /** @deprecated */
   @Deprecated
   public final void stopDiscovery(GoogleApiClient var1, String var2) {
      this.stopDiscovery(var1);
   }

   /** @deprecated */
   @Deprecated
   public final PendingResult sendConnectionRequest(GoogleApiClient var1, String var2, String var3, byte[] var4, Connections.ConnectionResponseCallback var5, Connections.MessageListener var6) {
      zzbdw var7 = var1.zzp(var5);
      zzbdw var8 = var1.zzp(var6);
      return var1.zze(new zzclt(this, var1, var2, var3, var4, var7, var8));
   }

   /** @deprecated */
   @Deprecated
   public final PendingResult acceptConnectionRequest(GoogleApiClient var1, String var2, byte[] var3, Connections.MessageListener var4) {
      zzbdw var5 = var1.zzp(var4);
      return var1.zze(new zzclu(this, var1, var2, var3, var5));
   }

   /** @deprecated */
   @Deprecated
   public final PendingResult rejectConnectionRequest(GoogleApiClient var1, String var2) {
      return var1.zze(new zzclv(this, var1, var2));
   }

   /** @deprecated */
   @Deprecated
   public final void sendReliableMessage(GoogleApiClient var1, String var2, byte[] var3) {
      var1.zze(new zzclw(this, var1, var2, var3));
   }

   /** @deprecated */
   @Deprecated
   public final void sendReliableMessage(GoogleApiClient var1, List var2, byte[] var3) {
      var1.zze(new zzclx(this, var1, var2, var3));
   }

   /** @deprecated */
   @Deprecated
   public final void sendUnreliableMessage(GoogleApiClient var1, String var2, byte[] var3) {
      this.sendPayload(var1, var2, Payload.fromBytes(var3));
   }

   /** @deprecated */
   @Deprecated
   public final void sendUnreliableMessage(GoogleApiClient var1, List var2, byte[] var3) {
      this.sendPayload(var1, var2, Payload.fromBytes(var3));
   }
}
