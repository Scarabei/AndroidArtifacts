package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Session extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final String EXTRA_SESSION = "vnd.google.fitness.session";
   public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
   private final int zzaku;
   private final long zzagZ;
   private final long zzaTo;
   private final String mName;
   private final String zzaVf;
   private final String zzafa;
   private final int zzaTp;
   private final zzb zzaVg;
   private final Long zzaVh;
   public static final Creator CREATOR = new zzad();

   Session(int var1, long var2, long var4, String var6, String var7, String var8, int var9, zzb var10, Long var11) {
      this.zzaku = var1;
      this.zzagZ = var2;
      this.zzaTo = var4;
      this.mName = var6;
      this.zzaVf = var7;
      this.zzafa = var8;
      this.zzaTp = var9;
      this.zzaVg = var10;
      this.zzaVh = var11;
   }

   private Session(long var1, long var3, String var5, String var6, String var7, int var8, zzb var9, Long var10) {
      this(3, var1, var3, var5, var6, var7, var8, (zzb)null, var10);
   }

   private Session(Session.Builder var1) {
      this(var1.zzagZ, var1.zzaTo, var1.mName, var1.zzaVf, var1.zzafa, var1.zzaTp, (zzb)null, var1.zzaVh);
   }

   public static Session extract(Intent var0) {
      return var0 == null ? null : (Session)com.google.android.gms.common.internal.safeparcel.zze.zza(var0, "vnd.google.fitness.session", CREATOR);
   }

   public static String getMimeType(String var0) {
      String var10000 = String.valueOf("vnd.google.fitness.session/");
      String var10001 = String.valueOf(var0);
      return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
   }

   public long getStartTime(TimeUnit var1) {
      return var1.convert(this.zzagZ, TimeUnit.MILLISECONDS);
   }

   public long getEndTime(TimeUnit var1) {
      return var1.convert(this.zzaTo, TimeUnit.MILLISECONDS);
   }

   public long getActiveTime(TimeUnit var1) {
      zzbo.zza(this.zzaVh != null, "Active time is not set");
      return var1.convert(this.zzaVh.longValue(), TimeUnit.MILLISECONDS);
   }

   public boolean hasActiveTime() {
      return this.zzaVh != null;
   }

   public boolean isOngoing() {
      return this.zzaTo == 0L;
   }

   public String getName() {
      return this.mName;
   }

   public String getIdentifier() {
      return this.zzaVf;
   }

   public String getDescription() {
      return this.zzafa;
   }

   public String getActivity() {
      return com.google.android.gms.fitness.zza.getName(this.zzaTp);
   }

   public String getAppPackageName() {
      return this.zzaVg == null ? null : this.zzaVg.getPackageName();
   }

   public boolean equals(Object var1) {
      if (var1 != this) {
         if (var1 instanceof Session) {
            Session var3 = (Session)var1;
            if (this.zzagZ == var3.zzagZ && this.zzaTo == var3.zzaTo && zzbe.equal(this.mName, var3.mName) && zzbe.equal(this.zzaVf, var3.zzaVf) && zzbe.equal(this.zzafa, var3.zzafa) && zzbe.equal(this.zzaVg, var3.zzaVg) && this.zzaTp == var3.zzaTp) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzagZ, this.zzaTo, this.zzaVf});
   }

   public String toString() {
      return zzbe.zzt(this).zzg("startTime", this.zzagZ).zzg("endTime", this.zzaTo).zzg("name", this.mName).zzg("identifier", this.zzaVf).zzg("description", this.zzafa).zzg("activity", this.zzaTp).zzg("application", this.zzaVg).toString();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.zzagZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.zzaTo);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getIdentifier(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getDescription(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 7, this.zzaTp);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzaVg, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaVh, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   // $FF: synthetic method
   Session(Session.Builder var1, zzac var2) {
      this(var1);
   }

   public static class Builder {
      private long zzagZ = 0L;
      private long zzaTo = 0L;
      private String mName = null;
      private String zzaVf;
      private String zzafa;
      private int zzaTp = 4;
      private Long zzaVh;

      public Session.Builder setStartTime(long var1, TimeUnit var3) {
         zzbo.zza(var1 > 0L, "Start time should be positive.");
         this.zzagZ = var3.toMillis(var1);
         return this;
      }

      public Session.Builder setEndTime(long var1, TimeUnit var3) {
         zzbo.zza(var1 >= 0L, "End time should be positive.");
         this.zzaTo = var3.toMillis(var1);
         return this;
      }

      public Session.Builder setName(String var1) {
         zzbo.zzb(var1.length() <= 100, "Session name cannot exceed %d characters", new Object[]{Integer.valueOf(100)});
         this.mName = var1;
         return this;
      }

      public Session.Builder setIdentifier(String var1) {
         zzbo.zzaf(var1 != null && TextUtils.getTrimmedLength(var1) > 0);
         this.zzaVf = var1;
         return this;
      }

      public Session.Builder setDescription(String var1) {
         zzbo.zzb(var1.length() <= 1000, "Session description cannot exceed %d characters", new Object[]{Integer.valueOf(1000)});
         this.zzafa = var1;
         return this;
      }

      public Session.Builder setActivity(String var1) {
         int var3 = com.google.android.gms.fitness.zza.zzcW(var1);
         this.zzaTp = var3;
         return this;
      }

      public Session.Builder setActiveTime(long var1, TimeUnit var3) {
         this.zzaVh = var3.toMillis(var1);
         return this;
      }

      public Session build() {
         zzbo.zza(this.zzagZ > 0L, "Start time should be specified.");
         zzbo.zza(this.zzaTo == 0L || this.zzaTo > this.zzagZ, "End time should be later than start time.");
         if (this.zzaVf == null) {
            String var1 = this.mName == null ? "" : this.mName;
            long var2 = this.zzagZ;
            this.zzaVf = (new StringBuilder(20 + String.valueOf(var1).length())).append(var1).append(var2).toString();
         }

         if (this.zzafa == null) {
            this.zzafa = "";
         }

         return new Session(this, (zzac)null);
      }
   }
}
