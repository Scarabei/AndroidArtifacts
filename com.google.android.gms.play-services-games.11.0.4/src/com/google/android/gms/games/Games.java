package com.google.android.gms.games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.games.internal.api.zzab;
import com.google.android.gms.games.internal.api.zzaf;
import com.google.android.gms.games.internal.api.zzav;
import com.google.android.gms.games.internal.api.zzaw;
import com.google.android.gms.games.internal.api.zzax;
import com.google.android.gms.games.internal.api.zzbh;
import com.google.android.gms.games.internal.api.zzbs;
import com.google.android.gms.games.internal.api.zzbt;
import com.google.android.gms.games.internal.api.zzcb;
import com.google.android.gms.games.internal.api.zzcp;
import com.google.android.gms.games.internal.api.zzcq;
import com.google.android.gms.games.internal.api.zzcu;
import com.google.android.gms.games.internal.api.zzdr;
import com.google.android.gms.games.internal.api.zzo;
import com.google.android.gms.games.internal.api.zzp;
import com.google.android.gms.games.internal.api.zzx;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.internal.zzbay;
import com.google.android.gms.internal.zzcah;
import com.google.android.gms.internal.zzcap;
import java.util.ArrayList;

@KeepForSdk
public final class Games {
   public static final String EXTRA_PLAYER_IDS = "players";
   public static final String EXTRA_STATUS = "status";
   static final com.google.android.gms.common.api.Api.zzf zzajR = new com.google.android.gms.common.api.Api.zzf();
   private static final com.google.android.gms.common.api.Api.zza zzajS = new zzb();
   private static final com.google.android.gms.common.api.Api.zza zzaYm = new zzc();
   public static final Scope SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
   private static Scope zzaYn = new Scope("https://www.googleapis.com/auth/games_lite");
   public static final Api API;
   public static final Scope zzaYo;
   private static Api zzaYp;
   public static final GamesMetadata GamesMetadata;
   public static final Achievements Achievements;
   private static zzcah zzaYq;
   public static final Events Events;
   public static final Leaderboards Leaderboards;
   public static final Invitations Invitations;
   public static final TurnBasedMultiplayer TurnBasedMultiplayer;
   public static final RealTimeMultiplayer RealTimeMultiplayer;
   private static Multiplayer zzaYr;
   public static final Players Players;
   public static final Notifications Notifications;
   public static final Quests Quests;
   public static final Requests Requests;
   public static final Snapshots Snapshots;
   public static final Stats Stats;
   public static final Videos Videos;
   private static zzcap zzaYs;

   public static GamesClientImpl zzf(GoogleApiClient var0) {
      return zza(var0, true);
   }

   public static GamesClientImpl zza(GoogleApiClient var0, boolean var1) {
      zzbo.zzb(var0 != null, "GoogleApiClient parameter is required.");
      zzbo.zza(var0.isConnected(), "GoogleApiClient must be connected.");
      return zzb(var0, var1);
   }

   public static GamesClientImpl zzb(GoogleApiClient var0, boolean var1) {
      zzbo.zza(var0.zza(API), "GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
      boolean var2 = var0.hasConnectedApi(API);
      if (var1 && !var2) {
         throw new IllegalStateException("GoogleApiClient has an optional Games.API and is not connected to Games. Use GoogleApiClient.hasConnectedApi(Games.API) to guard this call.");
      } else {
         return var2 ? (GamesClientImpl)var0.zza(zzajR) : null;
      }
   }

   public static void setGravityForPopups(GoogleApiClient var0, int var1) {
      GamesClientImpl var2;
      if ((var2 = zza(var0, false)) != null) {
         var2.zzaZ(var1);
      }

   }

   public static void setViewForPopups(GoogleApiClient var0, View var1) {
      zzbo.zzu(var1);
      GamesClientImpl var2;
      if ((var2 = zza(var0, false)) != null) {
         var2.zzs(var1);
      }

   }

   @RequiresPermission("android.permission.GET_ACCOUNTS")
   public static String getCurrentAccountName(GoogleApiClient var0) {
      return zza(var0, true).zzus();
   }

   /** @deprecated */
   @Deprecated
   @KeepForSdk
   public static PendingResult getGamesServerAuthCode(GoogleApiClient var0, String var1) {
      zzbo.zzh(var1, "Please provide a valid serverClientId");
      return var0.zze(new zzd(var0, var1));
   }

   public static String getAppId(GoogleApiClient var0) {
      return zza(var0, true).zzhl();
   }

   public static Intent getSettingsIntent(GoogleApiClient var0) {
      return zza(var0, true).zzuE();
   }

   public static PendingResult signOut(GoogleApiClient var0) {
      return var0.zze(new zze(var0));
   }

   public static int getSdkVariant(GoogleApiClient var0) {
      return zza(var0, true).zzuF();
   }

   static {
      API = new Api("Games.API", zzajS, zzajR);
      zzaYo = new Scope("https://www.googleapis.com/auth/games.firstparty");
      zzaYp = new Api("Games.API_1P", zzaYm, zzajR);
      GamesMetadata = new zzx();
      Achievements = new com.google.android.gms.games.internal.api.zza();
      zzaYq = new zzo();
      Events = new zzp();
      Leaderboards = new zzaf();
      Invitations = new zzab();
      TurnBasedMultiplayer = new zzcu();
      RealTimeMultiplayer = new zzbs();
      zzaYr = new zzav();
      Players = new zzax();
      Notifications = new zzaw();
      Quests = new zzbh();
      Requests = new zzbt();
      Snapshots = new zzcb();
      Stats = new zzcq();
      Videos = new zzdr();
      zzaYs = new zzcp();
   }

   abstract static class zzd extends Games.zza {
      private zzd(GoogleApiClient var1) {
         super(var1);
      }

      // $FF: synthetic method
      public final Result zzb(Status var1) {
         return var1;
      }

      // $FF: synthetic method
      zzd(GoogleApiClient var1, zzb var2) {
         this(var1);
      }
   }

   abstract static class zzc extends Games.zza {
      private zzc(GoogleApiClient var1) {
         super(var1);
      }

      // $FF: synthetic method
      public final Result zzb(Status var1) {
         return new zzf(this, var1);
      }

      // $FF: synthetic method
      zzc(GoogleApiClient var1, zzb var2) {
         this(var1);
      }
   }

   /** @deprecated */
   @Deprecated
   @KeepForSdk
   public interface GetServerAuthCodeResult extends Result {
      @KeepForSdk
      String getCode();
   }

   public abstract static class zza extends zzbay {
      public zza(GoogleApiClient var1) {
         super(Games.zzajR, var1);
      }
   }

   abstract static class zzb extends com.google.android.gms.common.api.Api.zza {
      private zzb() {
      }

      public final int getPriority() {
         return 1;
      }

      // $FF: synthetic method
      public final com.google.android.gms.common.api.Api.zze zza(Context var1, Looper var2, zzq var3, Object var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
         Games.GamesOptions var10 = (Games.GamesOptions)var4;
         if (var10 == null) {
            var10 = (new Games.GamesOptions.Builder((zzb)null)).build();
         }

         return new GamesClientImpl(var1, var2, var3, var10, var5, var6);
      }

      // $FF: synthetic method
      zzb(zzb var1) {
         this();
      }
   }

   public static final class GamesOptions implements GoogleSignInOptionsExtension, Optional {
      public final boolean zzaYu;
      private boolean zzaYv;
      private int zzaYw;
      private boolean zzaYx;
      private int zzaYy;
      private String zzaYz;
      private ArrayList zzaYA;
      private boolean zzaYB;
      public final boolean zzaYC;
      private boolean zzaYD;

      private GamesOptions(boolean var1, boolean var2, int var3, boolean var4, int var5, String var6, ArrayList var7, boolean var8, boolean var9, boolean var10) {
         this.zzaYu = var1;
         this.zzaYv = var2;
         this.zzaYw = var3;
         this.zzaYx = var4;
         this.zzaYy = var5;
         this.zzaYz = var6;
         this.zzaYA = var7;
         this.zzaYB = var8;
         this.zzaYC = var9;
         this.zzaYD = var10;
      }

      public final Bundle toBundle() {
         return this.zzui();
      }

      public final Bundle zzui() {
         Bundle var1;
         (var1 = new Bundle()).putBoolean("com.google.android.gms.games.key.isHeadless", this.zzaYu);
         var1.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.zzaYv);
         var1.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.zzaYw);
         var1.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.zzaYx);
         var1.putInt("com.google.android.gms.games.key.sdkVariant", this.zzaYy);
         var1.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.zzaYz);
         var1.putStringArrayList("com.google.android.gms.games.key.proxyApis", this.zzaYA);
         var1.putBoolean("com.google.android.gms.games.key.requireGooglePlus", this.zzaYB);
         var1.putBoolean("com.google.android.gms.games.key.unauthenticated", this.zzaYC);
         var1.putBoolean("com.google.android.gms.games.key.skipWelcomePopup", this.zzaYD);
         return var1;
      }

      public static Games.GamesOptions.Builder builder() {
         return new Games.GamesOptions.Builder((zzb)null);
      }

      // $FF: synthetic method
      GamesOptions(boolean var1, boolean var2, int var3, boolean var4, int var5, String var6, ArrayList var7, boolean var8, boolean var9, boolean var10, zzb var11) {
         this(false, var2, var3, false, var5, (String)null, var7, false, false, false);
      }

      public static final class Builder {
         private boolean zzaYu;
         private boolean zzaYv;
         private int zzaYw;
         private boolean zzaYx;
         private int zzaYy;
         private String zzaYz;
         private ArrayList zzaYA;
         private boolean zzaYB;
         private boolean zzaYC;
         private boolean zzaYD;

         private Builder() {
            this.zzaYu = false;
            this.zzaYv = true;
            this.zzaYw = 17;
            this.zzaYx = false;
            this.zzaYy = 4368;
            this.zzaYz = null;
            this.zzaYA = new ArrayList();
            this.zzaYB = false;
            this.zzaYC = false;
            this.zzaYD = false;
         }

         public final Games.GamesOptions.Builder setShowConnectingPopup(boolean var1) {
            this.zzaYv = var1;
            this.zzaYw = 17;
            return this;
         }

         public final Games.GamesOptions.Builder setShowConnectingPopup(boolean var1, int var2) {
            this.zzaYv = var1;
            this.zzaYw = var2;
            return this;
         }

         public final Games.GamesOptions.Builder setSdkVariant(int var1) {
            this.zzaYy = var1;
            return this;
         }

         public final Games.GamesOptions build() {
            return new Games.GamesOptions(false, this.zzaYv, this.zzaYw, false, this.zzaYy, (String)null, this.zzaYA, false, false, false, (zzb)null);
         }

         // $FF: synthetic method
         Builder(zzb var1) {
            this();
         }
      }
   }
}
