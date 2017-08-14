package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public interface zzbdp {
   zzbay zzd(@NonNull zzbay var1);

   zzbay zze(@NonNull zzbay var1);

   void connect();

   ConnectionResult blockingConnect();

   ConnectionResult blockingConnect(long var1, TimeUnit var3);

   void disconnect();

   @Nullable
   ConnectionResult getConnectionResult(@NonNull Api var1);

   boolean isConnected();

   boolean isConnecting();

   boolean zza(zzbei var1);

   void zzpl();

   void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4);

   void zzpE();
}
