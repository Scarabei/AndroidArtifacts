package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzcpl;
import com.google.android.gms.nearby.messages.internal.zzad;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MessageFilter extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final Creator CREATOR = new zzc();
   public static final MessageFilter INCLUDE_ALL_MY_TYPES = (new MessageFilter.Builder()).includeAllMyTypes().build();
   private int zzaku;
   private final List zzbxX;
   private final List zzbxY;
   private final boolean zzbxZ;
   private final List zzbya;
   private final int zzbyb;

   MessageFilter(int var1, List var2, List var3, boolean var4, List var5, int var6) {
      this.zzaku = var1;
      this.zzbxX = Collections.unmodifiableList((List)zzbo.zzu(var2));
      this.zzbxZ = var4;
      if (var3 == null) {
         var3 = Collections.emptyList();
      }

      this.zzbxY = Collections.unmodifiableList(var3);
      if (var5 == null) {
         var5 = Collections.emptyList();
      }

      this.zzbya = Collections.unmodifiableList(var5);
      this.zzbyb = var6;
   }

   private MessageFilter(List var1, List var2, boolean var3, List var4, int var5) {
      this(2, var1, var2, var3, var4, var5);
   }

   public final List zzzQ() {
      return this.zzbxX;
   }

   public final boolean zzzR() {
      return this.zzbxZ;
   }

   final List zzzS() {
      return this.zzbxY;
   }

   public final List zzzT() {
      return this.zzbya;
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.zzbxX, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzbxY, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzbxZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.zzbya, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 5, this.zzbyb);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1000, this.zzaku);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public String toString() {
      boolean var1 = this.zzbxZ;
      String var2 = String.valueOf(this.zzbxX);
      return (new StringBuilder(53 + String.valueOf(var2).length())).append("MessageFilter{includeAllMyTypes=").append(var1).append(", messageTypes=").append(var2).append("}").toString();
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof MessageFilter)) {
         return false;
      } else {
         MessageFilter var2 = (MessageFilter)var1;
         return this.zzbxZ == var2.zzbxZ && zzbe.equal(this.zzbxX, var2.zzbxX) && zzbe.equal(this.zzbxY, var2.zzbxY) && zzbe.equal(this.zzbya, var2.zzbya);
      }
   }

   public int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzbxX, this.zzbxY, this.zzbxZ, this.zzbya});
   }

   // $FF: synthetic method
   MessageFilter(List var1, List var2, boolean var3, List var4, int var5, zzb var6) {
      this(var1, var2, var3, var4, var5);
   }

   public static final class Builder {
      private final Set zzbyc = new HashSet();
      private final List zzbxY = new ArrayList();
      private final Set zzbyd = new HashSet();
      private boolean zzbxZ;
      private int zzbyb = 0;

      public final MessageFilter.Builder includeAllMyTypes() {
         this.zzbxZ = true;
         return this;
      }

      public final MessageFilter.Builder includeNamespacedType(String var1, String var2) {
         zzbo.zzb(var1 != null && !var1.isEmpty() && !var1.contains("*"), "namespace(%s) cannot be null, empty or contain (*).", new Object[]{var1});
         zzbo.zzb(var2 != null && !var2.contains("*"), "type(%s) cannot be null or contain (*).", new Object[]{var2});
         return this.zzS(var1, var2);
      }

      public final MessageFilter.Builder includeFilter(MessageFilter var1) {
         this.zzbyc.addAll(var1.zzzQ());
         this.zzbxY.addAll(var1.zzzS());
         this.zzbyd.addAll(var1.zzzT());
         this.zzbxZ |= var1.zzzR();
         return this;
      }

      public final MessageFilter.Builder includeEddystoneUids(String var1, @Nullable String var2) {
         this.zzS("__reserved_namespace", "__eddystone_uid");
         this.zzbxY.add(zzcpl.zzT(var1, var2));
         return this;
      }

      public final MessageFilter.Builder includeIBeaconIds(UUID var1, @Nullable Short var2, @Nullable Short var3) {
         this.zzS("__reserved_namespace", "__i_beacon_id");
         this.zzbxY.add(zzcpl.zza(var1, var2, var3));
         return this;
      }

      public final MessageFilter.Builder includeAudioBytes(int var1) {
         zzbo.zzb(this.zzbyb == 0, "includeAudioBytes() can only be called once per MessageFilter instance.");
         zzbo.zzb(var1 > 0, (new StringBuilder(44)).append("Invalid value for numAudioBytes: ").append(var1).toString());
         zzbo.zzb(var1 <= 10, "numAudioBytes is capped by AudioBytes.MAX_SIZE = 10");
         this.zzS("__reserved_namespace", "__audio_bytes");
         this.zzbyb = var1;
         return this;
      }

      private final MessageFilter.Builder zzS(String var1, String var2) {
         this.zzbyc.add(new zzad(var1, var2));
         return this;
      }

      public final MessageFilter build() {
         zzbo.zza(this.zzbxZ || !this.zzbyc.isEmpty(), "At least one of the include methods must be called.");
         return new MessageFilter(new ArrayList(this.zzbyc), this.zzbxY, this.zzbxZ, new ArrayList(this.zzbyd), this.zzbyb, (zzb)null);
      }
   }
}
