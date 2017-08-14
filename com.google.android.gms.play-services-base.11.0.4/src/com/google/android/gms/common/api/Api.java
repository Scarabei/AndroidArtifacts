package com.google.android.gms.common.api;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.zzal;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api {
   private final Api.zza zzaAx;
   private final Api.zzh zzaAy;
   private final Api.zzf zzaAz;
   private final Api.zzi zzaAA;
   private final String mName;

   public Api(String var1, Api.zza var2, Api.zzf var3) {
      zzbo.zzb(var2, "Cannot construct an Api with a null ClientBuilder");
      zzbo.zzb(var3, "Cannot construct an Api with a null ClientKey");
      this.mName = var1;
      this.zzaAx = var2;
      this.zzaAy = null;
      this.zzaAz = var3;
      this.zzaAA = null;
   }

   public final Api.zzd zzpb() {
      return this.zzaAx;
   }

   public final Api.zza zzpc() {
      zzbo.zza(this.zzaAx != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
      return this.zzaAx;
   }

   public final Api.zzc zzpd() {
      if (this.zzaAz != null) {
         return this.zzaAz;
      } else {
         throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
      }
   }

   public final String getName() {
      return this.mName;
   }

   public interface zzg extends Api.zzb {
      String zzdb();

      String zzdc();

      IInterface zzd(IBinder var1);
   }

   public interface zze extends Api.zzb {
      void zza(zzj var1);

      void disconnect();

      boolean isConnected();

      boolean isConnecting();

      void zza(zzal var1, Set var2);

      boolean zzmv();

      boolean zzpe();

      boolean zzmG();

      Intent zzmH();

      void dump(String var1, FileDescriptor var2, PrintWriter var3, String[] var4);
   }

   public interface zzb {
   }

   public static final class zzi extends Api.zzc {
   }

   public static final class zzf extends Api.zzc {
   }

   public static class zzc {
   }

   public interface ApiOptions {
      public static final class NoOptions implements Api.ApiOptions.NotRequiredOptions {
      }

      public interface Optional extends Api.ApiOptions.HasOptions, Api.ApiOptions.NotRequiredOptions {
      }

      public interface HasOptions extends Api.ApiOptions {
      }

      public interface NotRequiredOptions extends Api.ApiOptions {
      }
   }

   public abstract static class zzh extends Api.zzd {
   }

   public abstract static class zza extends Api.zzd {
      public abstract Api.zze zza(Context var1, Looper var2, zzq var3, Object var4, GoogleApiClient.ConnectionCallbacks var5, GoogleApiClient.OnConnectionFailedListener var6);
   }

   public abstract static class zzd {
      public int getPriority() {
         return Integer.MAX_VALUE;
      }

      public List zzn(Object var1) {
         return Collections.emptyList();
      }
   }
}
