package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import java.util.Arrays;

public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player {
   public static final Creator CREATOR = new PlayerEntity.zza();
   private String zzaxn;
   private String zzalP;
   private final Uri zzaXU;
   private final Uri zzaXV;
   private final long zzaYE;
   private final int zzaYF;
   private final long zzaYG;
   private final String zzaYf;
   private final String zzaYg;
   private final String zzaoy;
   private final com.google.android.gms.games.internal.player.zzb zzaYH;
   private final PlayerLevelInfo zzaYI;
   private final boolean zzaYJ;
   private final boolean zzaYK;
   private final String zzaYL;
   private final String mName;
   private final Uri zzaYM;
   private final String zzaYN;
   private final Uri zzaYO;
   private final String zzaYP;
   private final int zzaYQ;
   private final long zzaYR;
   private final boolean zzacy;

   public PlayerEntity(Player var1) {
      this(var1, true);
   }

   private PlayerEntity(Player var1, boolean var2) {
      this.zzaxn = var1.getPlayerId();
      this.zzalP = var1.getDisplayName();
      this.zzaXU = var1.getIconImageUri();
      this.zzaYf = var1.getIconImageUrl();
      this.zzaXV = var1.getHiResImageUri();
      this.zzaYg = var1.getHiResImageUrl();
      this.zzaYE = var1.getRetrievedTimestamp();
      this.zzaYF = var1.zzul();
      this.zzaYG = var1.getLastPlayedWithTimestamp();
      this.zzaoy = var1.getTitle();
      this.zzaYJ = var1.zzum();
      com.google.android.gms.games.internal.player.zza var3 = var1.zzun();
      this.zzaYH = var3 == null ? null : new com.google.android.gms.games.internal.player.zzb(var3);
      this.zzaYI = var1.getLevelInfo();
      this.zzaYK = var1.zzuk();
      this.zzaYL = var1.zzuj();
      this.mName = var1.getName();
      this.zzaYM = var1.getBannerImageLandscapeUri();
      this.zzaYN = var1.getBannerImageLandscapeUrl();
      this.zzaYO = var1.getBannerImagePortraitUri();
      this.zzaYP = var1.getBannerImagePortraitUrl();
      this.zzaYQ = var1.zzuo();
      this.zzaYR = var1.zzup();
      this.zzacy = var1.isMuted();
      com.google.android.gms.common.internal.zzc.zzr(this.zzaxn);
      com.google.android.gms.common.internal.zzc.zzr(this.zzalP);
      com.google.android.gms.common.internal.zzc.zzae(this.zzaYE > 0L);
   }

   PlayerEntity(String var1, String var2, Uri var3, Uri var4, long var5, int var7, long var8, String var10, String var11, String var12, com.google.android.gms.games.internal.player.zzb var13, PlayerLevelInfo var14, boolean var15, boolean var16, String var17, String var18, Uri var19, String var20, Uri var21, String var22, int var23, long var24, boolean var26) {
      this.zzaxn = var1;
      this.zzalP = var2;
      this.zzaXU = var3;
      this.zzaYf = var10;
      this.zzaXV = var4;
      this.zzaYg = var11;
      this.zzaYE = var5;
      this.zzaYF = var7;
      this.zzaYG = var8;
      this.zzaoy = var12;
      this.zzaYJ = var15;
      this.zzaYH = var13;
      this.zzaYI = var14;
      this.zzaYK = var16;
      this.zzaYL = var17;
      this.mName = var18;
      this.zzaYM = var19;
      this.zzaYN = var20;
      this.zzaYO = var21;
      this.zzaYP = var22;
      this.zzaYQ = var23;
      this.zzaYR = var24;
      this.zzacy = var26;
   }

   public final String getPlayerId() {
      return this.zzaxn;
   }

   public final String getDisplayName() {
      return this.zzalP;
   }

   public final void getDisplayName(CharArrayBuffer var1) {
      com.google.android.gms.common.util.zzh.zzb(this.zzalP, var1);
   }

   public final String zzuj() {
      return this.zzaYL;
   }

   public final String getName() {
      return this.mName;
   }

   public final boolean zzuk() {
      return this.zzaYK;
   }

   public final boolean hasIconImage() {
      return this.getIconImageUri() != null;
   }

   public final Uri getIconImageUri() {
      return this.zzaXU;
   }

   public final String getIconImageUrl() {
      return this.zzaYf;
   }

   public final boolean hasHiResImage() {
      return this.getHiResImageUri() != null;
   }

   public final Uri getHiResImageUri() {
      return this.zzaXV;
   }

   public final String getHiResImageUrl() {
      return this.zzaYg;
   }

   public final long getRetrievedTimestamp() {
      return this.zzaYE;
   }

   public final long getLastPlayedWithTimestamp() {
      return this.zzaYG;
   }

   public final int zzul() {
      return this.zzaYF;
   }

   public final boolean zzum() {
      return this.zzaYJ;
   }

   public final String getTitle() {
      return this.zzaoy;
   }

   public final void getTitle(CharArrayBuffer var1) {
      com.google.android.gms.common.util.zzh.zzb(this.zzaoy, var1);
   }

   public final PlayerLevelInfo getLevelInfo() {
      return this.zzaYI;
   }

   public final com.google.android.gms.games.internal.player.zza zzun() {
      return this.zzaYH;
   }

   public final Uri getBannerImageLandscapeUri() {
      return this.zzaYM;
   }

   public final String getBannerImageLandscapeUrl() {
      return this.zzaYN;
   }

   public final Uri getBannerImagePortraitUri() {
      return this.zzaYO;
   }

   public final String getBannerImagePortraitUrl() {
      return this.zzaYP;
   }

   public final int zzuo() {
      return this.zzaYQ;
   }

   public final long zzup() {
      return this.zzaYR;
   }

   public final boolean isMuted() {
      return this.zzacy;
   }

   public final Player freeze() {
      return this;
   }

   public final boolean isDataValid() {
      return true;
   }

   public final int hashCode() {
      return zza(this);
   }

   static int zza(Player var0) {
      return Arrays.hashCode(new Object[]{var0.getPlayerId(), var0.getDisplayName(), var0.zzuk(), var0.getIconImageUri(), var0.getHiResImageUri(), var0.getRetrievedTimestamp(), var0.getTitle(), var0.getLevelInfo(), var0.zzuj(), var0.getName(), var0.getBannerImageLandscapeUri(), var0.getBannerImagePortraitUri(), var0.zzuo(), var0.zzup(), var0.isMuted()});
   }

   public final boolean equals(Object var1) {
      return zza(this, var1);
   }

   static boolean zza(Player var0, Object var1) {
      if (!(var1 instanceof Player)) {
         return false;
      } else if (var0 == var1) {
         return true;
      } else {
         Player var2;
         return zzbe.equal((var2 = (Player)var1).getPlayerId(), var0.getPlayerId()) && zzbe.equal(var2.getDisplayName(), var0.getDisplayName()) && zzbe.equal(var2.zzuk(), var0.zzuk()) && zzbe.equal(var2.getIconImageUri(), var0.getIconImageUri()) && zzbe.equal(var2.getHiResImageUri(), var0.getHiResImageUri()) && zzbe.equal(var2.getRetrievedTimestamp(), var0.getRetrievedTimestamp()) && zzbe.equal(var2.getTitle(), var0.getTitle()) && zzbe.equal(var2.getLevelInfo(), var0.getLevelInfo()) && zzbe.equal(var2.zzuj(), var0.zzuj()) && zzbe.equal(var2.getName(), var0.getName()) && zzbe.equal(var2.getBannerImageLandscapeUri(), var0.getBannerImageLandscapeUri()) && zzbe.equal(var2.getBannerImagePortraitUri(), var0.getBannerImagePortraitUri()) && zzbe.equal(var2.zzuo(), var0.zzuo()) && zzbe.equal(var2.zzup(), var0.zzup()) && zzbe.equal(var2.isMuted(), var0.isMuted());
      }
   }

   public final String toString() {
      return zzb(this);
   }

   static String zzb(Player var0) {
      return zzbe.zzt(var0).zzg("PlayerId", var0.getPlayerId()).zzg("DisplayName", var0.getDisplayName()).zzg("HasDebugAccess", var0.zzuk()).zzg("IconImageUri", var0.getIconImageUri()).zzg("IconImageUrl", var0.getIconImageUrl()).zzg("HiResImageUri", var0.getHiResImageUri()).zzg("HiResImageUrl", var0.getHiResImageUrl()).zzg("RetrievedTimestamp", var0.getRetrievedTimestamp()).zzg("Title", var0.getTitle()).zzg("LevelInfo", var0.getLevelInfo()).zzg("GamerTag", var0.zzuj()).zzg("Name", var0.getName()).zzg("BannerImageLandscapeUri", var0.getBannerImageLandscapeUri()).zzg("BannerImageLandscapeUrl", var0.getBannerImageLandscapeUrl()).zzg("BannerImagePortraitUri", var0.getBannerImagePortraitUri()).zzg("BannerImagePortraitUrl", var0.getBannerImagePortraitUrl()).zzg("GamerFriendStatus", var0.zzuo()).zzg("GamerFriendUpdateTimestamp", var0.zzup()).zzg("IsMuted", var0.isMuted()).toString();
   }

   public final void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 1, this.getPlayerId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getDisplayName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getIconImageUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getHiResImageUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getRetrievedTimestamp());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 6, this.zzaYF);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getLastPlayedWithTimestamp());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.getIconImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.getHiResImageUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 14, this.getTitle(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 15, this.zzaYH, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 16, this.getLevelInfo(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 18, this.zzaYJ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 19, this.zzaYK);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 20, this.zzaYL, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 21, this.mName, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 22, this.getBannerImageLandscapeUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 23, this.getBannerImageLandscapeUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 24, this.getBannerImagePortraitUri(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 25, this.getBannerImagePortraitUrl(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 26, this.zzaYQ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 27, this.zzaYR);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 28, this.zzacy);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   static final class zza extends zzg {
      public final PlayerEntity zzg(Parcel var1) {
         if (!PlayerEntity.zze(PlayerEntity.zzrx()) && !PlayerEntity.zzcA(PlayerEntity.class.getCanonicalName())) {
            String var2 = var1.readString();
            String var3 = var1.readString();
            String var4 = var1.readString();
            String var5 = var1.readString();
            Uri var6 = var4 == null ? null : Uri.parse(var4);
            Uri var7 = var5 == null ? null : Uri.parse(var5);
            long var8 = var1.readLong();
            String var10 = var1.readString();
            String var11 = var1.readString();
            return new PlayerEntity(var2, var3, var6, var7, var8, -1, -1L, (String)null, (String)null, (String)null, (com.google.android.gms.games.internal.player.zzb)null, (PlayerLevelInfo)null, true, false, var10, var11, (Uri)null, (String)null, (Uri)null, (String)null, -1, -1L, false);
         } else {
            return super.zzg(var1);
         }
      }

      // $FF: synthetic method
      public final Object createFromParcel(Parcel var1) {
         return this.zzg(var1);
      }
   }
}
