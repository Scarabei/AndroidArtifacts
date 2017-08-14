package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzbbz extends GoogleApiClient {
   private final UnsupportedOperationException zzaCY;

   public zzbbz(String var1) {
      this.zzaCY = new UnsupportedOperationException(var1);
   }

   public boolean hasConnectedApi(@NonNull Api var1) {
      throw this.zzaCY;
   }

   @NonNull
   public ConnectionResult getConnectionResult(@NonNull Api var1) {
      throw this.zzaCY;
   }

   public void connect() {
      throw this.zzaCY;
   }

   public ConnectionResult blockingConnect() {
      throw this.zzaCY;
   }

   public ConnectionResult blockingConnect(long var1, @NonNull TimeUnit var3) {
      throw this.zzaCY;
   }

   public void disconnect() {
      throw this.zzaCY;
   }

   public void reconnect() {
      throw this.zzaCY;
   }

   public PendingResult clearDefaultAccountAndReconnect() {
      throw this.zzaCY;
   }

   public void stopAutoManage(@NonNull FragmentActivity var1) {
      throw this.zzaCY;
   }

   public boolean isConnected() {
      throw this.zzaCY;
   }

   public boolean isConnecting() {
      throw this.zzaCY;
   }

   public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks var1) {
      throw this.zzaCY;
   }

   public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks var1) {
      throw this.zzaCY;
   }

   public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks var1) {
      throw this.zzaCY;
   }

   public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener var1) {
      throw this.zzaCY;
   }

   public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener var1) {
      throw this.zzaCY;
   }

   public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener var1) {
      throw this.zzaCY;
   }

   public void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4) {
      throw this.zzaCY;
   }
}
