package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzc;
import java.util.Arrays;

public final class EventEntity extends zzc implements Event {
   public static final Creator CREATOR = new zza();
   private final String zzaZn;
   private final String mName;
   private final String zzafa;
   private final Uri zzaXU;
   private final String zzaYf;
   private final PlayerEntity zzaZi;
   private final long zzaeZ;
   private final String zzaZo;
   private final boolean zzxa;

   public EventEntity(Event var1) {
      this.zzaZn = var1.getEventId();
      this.mName = var1.getName();
      this.zzafa = var1.getDescription();
      this.zzaXU = var1.getIconImageUri();
      this.zzaYf = var1.getIconImageUrl();
      this.zzaZi = (PlayerEntity)var1.getPlayer().freeze();
      this.zzaeZ = var1.getValue();
      this.zzaZo = var1.getFormattedValue();
      this.zzxa = var1.isVisible();
   }

   EventEntity(String var1, String var2, String var3, Uri var4, String var5, Player var6, long var7, String var9, boolean var10) {
      this.zzaZn = var1;
      this.mName = var2;
      this.zzafa = var3;
      this.zzaXU = var4;
      this.zzaYf = var5;
      this.zzaZi = new PlayerEntity(var6);
      this.zzaeZ = var7;
      this.zzaZo = var9;
      this.zzxa = var10;
   }

   public final String getEventId() {
      return this.zzaZn;
   }

   public final String getName() {
      return this.mName;
   }

   public final void getName(CharArrayBuffer var1) {
      zzh.zzb(this.mName, var1);
   }

   public final String getDescription() {
      return this.zzafa;
   }

   public final void getDescription(CharArrayBuffer var1) {
      zzh.zzb(this.zzafa, var1);
   }

   public final Uri getIconImageUri() {
      return this.zzaXU;
   }

   public final String getIconImageUrl() {
      return this.zzaYf;
   }

   public final Player getPlayer() {
      return this.zzaZi;
   }

   public final long getValue() {
      return this.zzaeZ;
   }

   public final String getFormattedValue() {
      return this.zzaZo;
   }

   public final void getFormattedValue(CharArrayBuffer var1) {
      zzh.zzb(this.zzaZo, var1);
   }

   public final boolean isVisible() {
      return this.zzxa;
   }

   public final Event freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Event var0) {
      return Arrays.hashCode(new Object[]{var0.getEventId(), var0.getName(), var0.getDescription(), var0.getIconImageUri(), var0.getIconImageUrl(), var0.getPlayer(), var0.getValue(), var0.getFormattedValue(), var0.isVisible()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(Event var0, Object var1) {
      if (!(var1 instanceof Event)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Event var2;
         return zzbe.equal((var2 = (Event)var1).getEventId(), var0.getEventId()) && zzbe.equal(var2.getName(), var0.getName()) && zzbe.equal(var2.getDescription(), var0.getDescription()) && zzbe.equal(var2.getIconImageUri(), var0.getIconImageUri()) && zzbe.equal(var2.getIconImageUrl(), var0.getIconImageUrl()) && zzbe.equal(var2.getPlayer(), var0.getPlayer()) && zzbe.equal(var2.getValue(), var0.getValue()) && zzbe.equal(var2.getFormattedValue(), var0.getFormattedValue()) && zzbe.equal(var2.isVisible(), var0.isVisible());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Event var0) {
      return zzbe.zzt(var0).zzg("Id", var0.getEventId()).zzg("Name", var0.getName()).zzg("Description", var0.getDescription()).zzg("IconImageUri", var0.getIconImageUri()).zzg("IconImageUrl", var0.getIconImageUrl()).zzg("Player", var0.getPlayer()).zzg("Value", var0.getValue()).zzg("FormattedValue", var0.getFormattedValue()).zzg("isVisible", var0.isVisible()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = zzd.zze(var1);
      zzd.zza(var1, 1, this.getEventId(), false);
      zzd.zza(var1, 2, this.getName(), false);
      zzd.zza(var1, 3, this.getDescription(), false);
      zzd.zza(var1, 4, this.getIconImageUri(), var2, false);
      zzd.zza(var1, 5, this.getIconImageUrl(), false);
      zzd.zza(var1, 6, this.getPlayer(), var2, false);
      zzd.zza(var1, 7, this.getValue());
      zzd.zza(var1, 8, this.getFormattedValue(), false);
      zzd.zza(var1, 9, this.isVisible());
      zzd.zzI(var1, var5);
   }
}
