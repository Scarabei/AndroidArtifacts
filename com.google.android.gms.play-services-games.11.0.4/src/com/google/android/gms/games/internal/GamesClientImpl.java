package com.google.android.gms.games.internal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesMetadata;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.PlayerStatsBuffer;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.internal.zzbaz;
import com.google.android.gms.internal.zzbbx;
import com.google.android.gms.internal.zzbby;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbdz;
import com.google.android.gms.internal.zzcaj;
import com.google.android.gms.internal.zzcal;
import com.google.android.gms.internal.zzcam;
import com.google.android.gms.internal.zzctu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class GamesClientImpl extends com.google.android.gms.common.internal.zzz {
   private zzcal zzaZp = new zzd(this);
   private final String zzaZq;
   private PlayerEntity zzaZr;
   private GameEntity zzaZs;
   private final zzn zzaZt;
   private boolean zzaZu = false;
   private final Binder zzaZv;
   private final long zzaZw;
   private final Games.GamesOptions zzaZx;
   private boolean zzaZy = false;

   public GamesClientImpl(Context var1, Looper var2, com.google.android.gms.common.internal.zzq var3, Games.GamesOptions var4, ConnectionCallbacks var5, OnConnectionFailedListener var6) {
      super(var1, var2, 1, var3, var5, var6);
      this.zzaZq = var3.zzrq();
      this.zzaZv = new Binder();
      int var8 = var3.zzrm();
      this.zzaZt = new zzq(this, var8);
      this.zzaZw = (long)this.hashCode();
      this.zzaZx = var4;
      if (!this.zzaZx.zzaYC) {
         this.zzs(var3.zzrs());
      }

   }

   public final boolean zzmv() {
      return true;
   }

   public final void onConnectionFailed(ConnectionResult var1) {
      super.onConnectionFailed(var1);
      this.zzaZu = false;
   }

   public final void zzaZ(int var1) {
      this.zzaZt.zzbaL.gravity = var1;
   }

   public final void zzs(View var1) {
      this.zzaZt.zzt(var1);
   }

   protected final Set zzb(Set var1) {
      boolean var2 = false;
      boolean var3 = false;
      Scope var4 = new Scope("https://www.googleapis.com/auth/games");
      Scope var5 = new Scope("https://www.googleapis.com/auth/games.firstparty");
      Iterator var6 = var1.iterator();

      while(var6.hasNext()) {
         Scope var7;
         if ((var7 = (Scope)var6.next()).equals(var4)) {
            var2 = true;
         } else if (var7.equals(var5)) {
            var3 = true;
         }
      }

      if (var3) {
         com.google.android.gms.common.internal.zzbo.zza(!var2, "Cannot have both %s and %s!", new Object[]{"https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty"});
      } else {
         com.google.android.gms.common.internal.zzbo.zza(var2, "Games APIs requires %s to function.", new Object[]{"https://www.googleapis.com/auth/games"});
      }

      return var1;
   }

   public final void zza(com.google.android.gms.common.internal.zzj var1) {
      this.zzaZr = null;
      this.zzaZs = null;
      super.zza(var1);
   }

   public final void disconnect() {
      this.zzaZu = false;
      if (this.isConnected()) {
         try {
            zzj var1;
            (var1 = (zzj)this.zzrf()).zzuP();
            this.zzaZp.flush();
            var1.zzC(this.zzaZw);
         } catch (RemoteException var2) {
            zze.zzy("GamesClientImpl", "Failed to notify client disconnect.");
         }
      }

      super.disconnect();
   }

   protected final String zzdb() {
      return "com.google.android.gms.games.service.START";
   }

   protected final String zzdc() {
      return "com.google.android.gms.games.internal.IGamesService";
   }

   public final Bundle zzoC() {
      try {
         Bundle var1;
         if ((var1 = ((zzj)this.zzrf()).zzoC()) != null) {
            var1.setClassLoader(GamesClientImpl.class.getClassLoader());
         }

         return var1;
      } catch (RemoteException var2) {
         zzd(var2);
         return null;
      }
   }

   protected final Bundle zzmo() {
      String var1 = this.getContext().getResources().getConfiguration().locale.toString();
      Bundle var2;
      (var2 = this.zzaZx.zzui()).putString("com.google.android.gms.games.key.gamePackageName", this.zzaZq);
      var2.putString("com.google.android.gms.games.key.desiredLocale", var1);
      var2.putParcelable("com.google.android.gms.games.key.popupWindowToken", new BinderWrapper(this.zzaZt.zzbaL.zzbaM));
      var2.putInt("com.google.android.gms.games.key.API_VERSION", 6);
      var2.putBundle("com.google.android.gms.games.key.signInOptions", zzctu.zza(this.zzry()));
      return var2;
   }

   public final String zzus() {
      try {
         return ((zzj)this.zzrf()).zzus();
      } catch (RemoteException var1) {
         zzd(var1);
         return null;
      }
   }

   public final void zzb(String var1, zzbaz var2) throws RemoteException {
      com.google.android.gms.common.internal.zzbo.zzh(var1, "Please provide a valid serverClientId");
      ((zzj)this.zzrf()).zza((String)var1, (zzf)(new GamesClientImpl.zzy(var2)));
   }

   public final String zzah(boolean var1) {
      if (this.zzaZr != null) {
         return this.zzaZr.getPlayerId();
      } else {
         try {
            return ((zzj)this.zzrf()).zzuR();
         } catch (RemoteException var2) {
            zzd(var2);
            return null;
         }
      }
   }

   public final Player zzut() {
      this.zzre();
      synchronized(this) {
         if (this.zzaZr == null) {
            try {
               PlayerBuffer var2 = new PlayerBuffer(((zzj)this.zzrf()).zzuS());

               try {
                  if (var2.getCount() > 0) {
                     this.zzaZr = (PlayerEntity)((Player)var2.get(0)).freeze();
                  }
               } finally {
                  var2.release();
               }
            } catch (RemoteException var9) {
               zzd(var9);
            }
         }
      }

      return this.zzaZr;
   }

   public final Game zzuu() {
      this.zzre();
      synchronized(this) {
         if (this.zzaZs == null) {
            try {
               GameBuffer var2 = new GameBuffer(((zzj)this.zzrf()).zzuT());

               try {
                  if (var2.getCount() > 0) {
                     this.zzaZs = (GameEntity)((Game)var2.get(0)).freeze();
                  }
               } finally {
                  var2.release();
               }
            } catch (RemoteException var9) {
               zzd(var9);
            }
         }
      }

      return this.zzaZs;
   }

   public final void zza(zzbaz var1, String var2, boolean var3) throws RemoteException {
      ((zzj)this.zzrf()).zzb(new GamesClientImpl.zzbn(var1), var2, var3);
   }

   public final void zza(zzbaz var1, int var2, boolean var3, boolean var4) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzbn(var1), var2, var3, var4);
   }

   public final void zza(zzbaz var1, String var2, int var3, boolean var4, boolean var5) throws RemoteException {
      byte var7 = -1;
      switch(var2.hashCode()) {
      case 156408498:
         if (var2.equals("played_with")) {
            var7 = 0;
         }
      default:
         switch(var7) {
         case 0:
            ((zzj)this.zzrf()).zza(new GamesClientImpl.zzbn(var1), var2, var3, var4, var5);
            return;
         default:
            IllegalArgumentException var10000 = new IllegalArgumentException;
            String var10003 = String.valueOf(var2);
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "Invalid player collection: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Invalid player collection: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }
      }
   }

   public final void zza(zzbaz var1, boolean var2) throws RemoteException {
      ((zzj)this.zzrf()).zzc(new GamesClientImpl.zzbn(var1), var2);
   }

   public final Intent zzuv() {
      Intent var1;
      try {
         var1 = ((zzj)this.zzrf()).zzuv();
      } catch (RemoteException var2) {
         zzd(var2);
         var1 = null;
      }

      return var1;
   }

   public final Intent zzj(String var1, int var2, int var3) {
      Intent var4;
      try {
         var4 = ((zzj)this.zzrf()).zzk(var1, var2, var3);
      } catch (RemoteException var5) {
         zzd(var5);
         var4 = null;
      }

      return var4;
   }

   public final void zzb(zzbaz var1, boolean var2) throws RemoteException {
      ((zzj)this.zzrf()).zzb(new GamesClientImpl.zzai(var1), var2);
   }

   public final void zzb(zzbaz var1, String var2, boolean var3) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzai(var1), (String)var2, var3);
   }

   public final void zza(zzbaz var1, String var2, String var3, int var4, int var5) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzbl(var1), (String)null, var3, var4, var5);
   }

   public final void zza(zzbaz var1, String var2, int var3, int var4, int var5, boolean var6) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzah(var1), var2, var3, var4, var5, var6);
   }

   public final void zzb(zzbaz var1, String var2, int var3, int var4, int var5, boolean var6) throws RemoteException {
      ((zzj)this.zzrf()).zzb(new GamesClientImpl.zzah(var1), var2, var3, var4, var5, var6);
   }

   public final void zza(zzbaz var1, LeaderboardScoreBuffer var2, int var3, int var4) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzah(var1), var2.zzvn().asBundle(), var3, var4);
   }

   public final void zza(zzbaz var1, String var2, long var3, String var5) throws RemoteException {
      GamesClientImpl.zzcl var6 = var1 == null ? null : new GamesClientImpl.zzcl(var1);
      ((zzj)this.zzrf()).zza(var6, var2, var3, var5);
   }

   public final Intent zzuw() {
      Intent var1;
      try {
         var1 = ((zzj)this.zzrf()).zzuw();
      } catch (RemoteException var2) {
         zzd(var2);
         var1 = null;
      }

      return var1;
   }

   public final void zzc(zzbaz var1, boolean var2) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzf(var1), var2);
   }

   public final void zza(zzbaz var1, String var2) throws RemoteException {
      GamesClientImpl.zze var3 = var1 == null ? null : new GamesClientImpl.zze(var1);
      ((zzj)this.zzrf()).zza(var3, (String)var2, (IBinder)this.zzaZt.zzbaL.zzbaM, (Bundle)this.zzaZt.zzbaL.zzuW());
   }

   public final void zzb(zzbaz var1, String var2) throws RemoteException {
      GamesClientImpl.zze var3 = var1 == null ? null : new GamesClientImpl.zze(var1);
      ((zzj)this.zzrf()).zzb(var3, var2, this.zzaZt.zzbaL.zzbaM, this.zzaZt.zzbaL.zzuW());
   }

   public final void zza(zzbaz var1, String var2, int var3) throws RemoteException {
      GamesClientImpl.zze var4 = var1 == null ? null : new GamesClientImpl.zze(var1);
      ((zzj)this.zzrf()).zza(var4, var2, var3, this.zzaZt.zzbaL.zzbaM, this.zzaZt.zzbaL.zzuW());
   }

   public final void zzb(zzbaz var1, String var2, int var3) throws RemoteException {
      GamesClientImpl.zze var4 = var1 == null ? null : new GamesClientImpl.zze(var1);
      ((zzj)this.zzrf()).zzb(var4, var2, var3, this.zzaZt.zzbaL.zzbaM, this.zzaZt.zzbaL.zzuW());
   }

   public final void zzd(zzbaz var1, boolean var2) throws RemoteException {
      this.zzaZp.flush();
      ((zzj)this.zzrf()).zze(new GamesClientImpl.zzu(var1), var2);
   }

   public final void zza(zzbaz var1, boolean var2, String... var3) throws RemoteException {
      this.zzaZp.flush();
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzu(var1), var2, var3);
   }

   public final void zzn(String var1, int var2) {
      this.zzaZp.zzn(var1, var2);
   }

   public final Intent zzux() {
      Intent var1;
      try {
         var1 = ((zzj)this.zzrf()).zzux();
      } catch (RemoteException var2) {
         zzd(var2);
         var1 = null;
      }

      return var1;
   }

   public final Intent zzuy() {
      Intent var1;
      try {
         var1 = ((zzj)this.zzrf()).zzuy();
      } catch (RemoteException var2) {
         zzd(var2);
         var1 = null;
      }

      return var1;
   }

   public final void zza(zzbdw var1) {
      try {
         GamesClientImpl.zzab var2 = new GamesClientImpl.zzab(var1);
         ((zzj)this.zzrf()).zza((zzf)var2, this.zzaZw);
      } catch (RemoteException var3) {
         zzd(var3);
      }
   }

   public final void zzuz() {
      try {
         ((zzj)this.zzrf()).zzD(this.zzaZw);
      } catch (RemoteException var1) {
         zzd(var1);
      }
   }

   public final void zzb(zzbdw var1) {
      try {
         GamesClientImpl.zzaz var2 = new GamesClientImpl.zzaz(var1);
         ((zzj)this.zzrf()).zzb(var2, this.zzaZw);
      } catch (RemoteException var3) {
         zzd(var3);
      }
   }

   public final void zzuA() {
      try {
         ((zzj)this.zzrf()).zzE(this.zzaZw);
      } catch (RemoteException var1) {
         zzd(var1);
      }
   }

   public final void zzc(zzbdw var1) {
      try {
         GamesClientImpl.zzbs var2 = new GamesClientImpl.zzbs(var1);
         ((zzj)this.zzrf()).zzd(var2, this.zzaZw);
      } catch (RemoteException var3) {
         zzd(var3);
      }
   }

   public final void zzuB() {
      try {
         ((zzj)this.zzrf()).zzG(this.zzaZw);
      } catch (RemoteException var1) {
         zzd(var1);
      }
   }

   public final void zzd(zzbdw var1) {
      try {
         GamesClientImpl.zzbw var2 = new GamesClientImpl.zzbw(var1);
         ((zzj)this.zzrf()).zzc(var2, this.zzaZw);
      } catch (RemoteException var3) {
         zzd(var3);
      }
   }

   public final void zzuC() {
      try {
         ((zzj)this.zzrf()).zzF(this.zzaZw);
      } catch (RemoteException var1) {
         zzd(var1);
      }
   }

   public final Intent zza(PlayerEntity var1) {
      Intent var2;
      try {
         var2 = ((zzj)this.zzrf()).zza(var1);
      } catch (RemoteException var3) {
         zzd(var3);
         var2 = null;
      }

      return var2;
   }

   public final Intent zzuD() {
      Intent var1;
      try {
         var1 = ((zzj)this.zzrf()).zzuD();
      } catch (RemoteException var2) {
         zzd(var2);
         var1 = null;
      }

      return var1;
   }

   public final Intent zza(Room var1, int var2) {
      Intent var3;
      try {
         var3 = ((zzj)this.zzrf()).zza((RoomEntity)var1.freeze(), var2);
      } catch (RemoteException var4) {
         zzd(var4);
         var3 = null;
      }

      return var3;
   }

   public final Intent zzuE() {
      Intent var1;
      try {
         var1 = ((zzj)this.zzrf()).zzuE();
      } catch (RemoteException var2) {
         zzd(var2);
         var1 = null;
      }

      return var1;
   }

   public final void zzf(zzbaz var1) throws RemoteException {
      ((zzj)this.zzrf()).zzb((zzf)(new GamesClientImpl.zzx(var1)));
   }

   public final void zzg(zzbaz var1) throws RemoteException {
      this.zzaZp.flush();
      ((zzj)this.zzrf()).zza((zzf)(new GamesClientImpl.zzcg(var1)));
   }

   public final int zzuF() {
      int var1 = 4368;

      try {
         var1 = ((zzj)this.zzrf()).zzuF();
      } catch (RemoteException var2) {
         zzd(var2);
      }

      return var1;
   }

   public final String zzhl() {
      try {
         return ((zzj)this.zzrf()).zzhl();
      } catch (RemoteException var1) {
         zzd(var1);
         return null;
      }
   }

   public final void zza(zzbaz var1, int var2) throws RemoteException {
      ((zzj)this.zzrf()).zza((zzf)(new GamesClientImpl.zzae(var1)), var2);
   }

   public final void zzo(String var1, int var2) {
      try {
         ((zzj)this.zzrf()).zzo(var1, var2);
      } catch (RemoteException var3) {
         zzd(var3);
      }
   }

   public final void zzp(String var1, int var2) {
      try {
         ((zzj)this.zzrf()).zzp(var1, var2);
      } catch (RemoteException var3) {
         zzd(var3);
      }
   }

   public final Intent zzb(int var1, int var2, boolean var3) {
      Intent var4;
      try {
         var4 = ((zzj)this.zzrf()).zzb(var1, var2, var3);
      } catch (RemoteException var5) {
         zzd(var5);
         var4 = null;
      }

      return var4;
   }

   public final void zza(zzbaz var1, TurnBasedMatchConfig var2) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzco(var1), var2.getVariant(), var2.zzvs(), var2.getInvitedPlayerIds(), var2.getAutoMatchCriteria());
   }

   public final void zzc(zzbaz var1, String var2) throws RemoteException {
      ((zzj)this.zzrf()).zzb(new GamesClientImpl.zzco(var1), (String)var2);
   }

   public final void zzd(zzbaz var1, String var2) throws RemoteException {
      ((zzj)this.zzrf()).zzc(new GamesClientImpl.zzco(var1), var2);
   }

   public final int zzuG() {
      int var1 = -1;

      try {
         var1 = ((zzj)this.zzrf()).zzuG();
      } catch (RemoteException var2) {
         zzd(var2);
      }

      return var1;
   }

   public final void zza(zzbaz var1, String var2, byte[] var3, String var4, ParticipantResult[] var5) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzcs(var1), var2, (byte[])var3, (String)var4, (ParticipantResult[])var5);
   }

   public final void zza(zzbaz var1, String var2, byte[] var3, ParticipantResult[] var4) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzcs(var1), (String)var2, (byte[])var3, (ParticipantResult[])var4);
   }

   public final void zze(zzbaz var1, String var2) throws RemoteException {
      ((zzj)this.zzrf()).zze(new GamesClientImpl.zzcp(var1), var2);
   }

   public final void zza(zzbaz var1, String var2, String var3) throws RemoteException {
      ((zzj)this.zzrf()).zza((zzf)(new GamesClientImpl.zzcp(var1)), (String)var2, (String)var3);
   }

   public final void zzf(zzbaz var1, String var2) throws RemoteException {
      ((zzj)this.zzrf()).zzd(new GamesClientImpl.zzcn(var1), var2);
   }

   public final void zzdj(String var1) {
      try {
         ((zzj)this.zzrf()).zzdm(var1);
      } catch (RemoteException var2) {
         zzd(var2);
      }
   }

   public final void zza(zzbaz var1, int var2, int[] var3) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzct(var1), var2, var3);
   }

   public final void zzg(zzbaz var1, String var2) throws RemoteException {
      ((zzj)this.zzrf()).zzf(new GamesClientImpl.zzcq(var1), var2);
   }

   public final Intent zzc(int var1, int var2, boolean var3) {
      Intent var4;
      try {
         var4 = ((zzj)this.zzrf()).zzc(var1, var2, var3);
      } catch (RemoteException var5) {
         zzd(var5);
         var4 = null;
      }

      return var4;
   }

   public final void zza(zzbdw var1, zzbdw var2, zzbdw var3, RoomConfig var4) {
      try {
         GamesClientImpl.zzcc var5 = new GamesClientImpl.zzcc(var1, var2, var3);
         ((zzj)this.zzrf()).zza(var5, this.zzaZv, var4.getVariant(), var4.getInvitedPlayerIds(), var4.getAutoMatchCriteria(), false, this.zzaZw);
      } catch (RemoteException var6) {
         zzd(var6);
      }
   }

   public final void zzb(zzbdw var1, zzbdw var2, zzbdw var3, RoomConfig var4) {
      try {
         GamesClientImpl.zzcc var5 = new GamesClientImpl.zzcc(var1, var2, var3);
         ((zzj)this.zzrf()).zza(var5, this.zzaZv, var4.getInvitationId(), false, this.zzaZw);
      } catch (RemoteException var6) {
         zzd(var6);
      }
   }

   public final void zza(zzbdw var1, String var2) {
      try {
         ((zzj)this.zzrf()).zza((zzf)(new GamesClientImpl.zzcc(var1)), (String)var2);
      } catch (RemoteException var3) {
         zzd(var3);
      }
   }

   public final int zza(zzbdw var1, byte[] var2, String var3, String var4) {
      try {
         return ((zzj)this.zzrf()).zza(new GamesClientImpl.zzbv(var1), (byte[])var2, (String)var3, (String)var4);
      } catch (RemoteException var5) {
         zzd(var5);
         return -1;
      }
   }

   public final int zza(byte[] var1, String var2, String[] var3) {
      com.google.android.gms.common.internal.zzbo.zzb(var3, "Participant IDs must not be null");

      try {
         return ((zzj)this.zzrf()).zzb(var1, var2, var3);
      } catch (RemoteException var4) {
         zzd(var4);
         return -1;
      }
   }

   public final int zzc(byte[] var1, String var2) {
      try {
         return ((zzj)this.zzrf()).zzb((byte[])var1, var2, (String[])null);
      } catch (RemoteException var3) {
         zzd(var3);
         return -1;
      }
   }

   public final void zzba(int var1) {
      try {
         ((zzj)this.zzrf()).zzba(var1);
      } catch (RemoteException var2) {
         zzd(var2);
      }
   }

   public final Intent zzuH() {
      Intent var1;
      try {
         var1 = ((zzj)this.zzrf()).zzuH();
      } catch (RemoteException var2) {
         zzd(var2);
         var1 = null;
      }

      return var1;
   }

   public final Intent zza(int var1, byte[] var2, int var3, Bitmap var4, String var5) {
      Intent var6;
      try {
         var6 = ((zzj)this.zzrf()).zza(var1, var2, var3, var5);
         com.google.android.gms.common.internal.zzbo.zzb(var4, "Must provide a non null icon");
         var6.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", var4);
      } catch (RemoteException var7) {
         zzd(var7);
         var6 = null;
      }

      return var6;
   }

   public final int zzuI() {
      int var1 = -1;

      try {
         var1 = ((zzj)this.zzrf()).zzuI();
      } catch (RemoteException var2) {
         zzd(var2);
      }

      return var1;
   }

   public final int zzuJ() {
      int var1 = -1;

      try {
         var1 = ((zzj)this.zzrf()).zzuJ();
      } catch (RemoteException var2) {
         zzd(var2);
      }

      return var1;
   }

   public final void zza(zzbaz var1, String[] var2) throws RemoteException {
      ((zzj)this.zzrf()).zza((zzf)(new GamesClientImpl.zzca(var1)), (String[])var2);
   }

   public final void zzb(zzbaz var1, String[] var2) throws RemoteException {
      ((zzj)this.zzrf()).zzb(new GamesClientImpl.zzca(var1), (String[])var2);
   }

   public final void zza(zzbaz var1, int var2, int var3, int var4) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzbz(var1), var2, var3, var4);
   }

   public final void zze(zzbaz var1, boolean var2) throws RemoteException {
      ((zzj)this.zzrf()).zzf(new GamesClientImpl.zzbm(var1), var2);
   }

   public final Intent zzb(int[] var1) {
      Intent var2;
      try {
         var2 = ((zzj)this.zzrf()).zzb(var1);
      } catch (RemoteException var3) {
         zzd(var3);
         var2 = null;
      }

      return var2;
   }

   public final Intent zzdk(String var1) {
      Intent var2;
      try {
         var2 = ((zzj)this.zzrf()).zzdk(var1);
      } catch (RemoteException var3) {
         zzd(var3);
         var2 = null;
      }

      return var2;
   }

   public final void zzh(zzbaz var1, String var2) throws RemoteException {
      this.zzaZp.flush();
      ((zzj)this.zzrf()).zzh(new GamesClientImpl.zzbp(var1), var2);
   }

   public final void zzb(zzbaz var1, String var2, String var3) throws RemoteException {
      this.zzaZp.flush();
      ((zzj)this.zzrf()).zzb((zzf)(new GamesClientImpl.zzbr(var1, var3)), var2, (String)var3);
   }

   public final void zza(zzbaz var1, int[] var2, int var3, boolean var4) throws RemoteException {
      this.zzaZp.flush();
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzbt(var1), var2, var3, var4);
   }

   public final void zzb(zzbaz var1, boolean var2, String[] var3) throws RemoteException {
      this.zzaZp.flush();
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzbt(var1), (String[])var3, var2);
   }

   public final void zzdl(String var1) {
      try {
         ((zzj)this.zzrf()).zza(var1, this.zzaZt.zzbaL.zzbaM, this.zzaZt.zzbaL.zzuW());
      } catch (RemoteException var2) {
         zzd(var2);
      }
   }

   public final int zzuK() {
      int var1 = -1;

      try {
         var1 = ((zzj)this.zzrf()).zzuK();
      } catch (RemoteException var2) {
         zzd(var2);
      }

      return var1;
   }

   public final int zzuL() {
      int var1 = -1;

      try {
         var1 = ((zzj)this.zzrf()).zzuL();
      } catch (RemoteException var2) {
         zzd(var2);
      }

      return var1;
   }

   public final Intent zza(String var1, boolean var2, boolean var3, int var4) {
      Intent var5;
      try {
         var5 = ((zzj)this.zzrf()).zza(var1, var2, var3, var4);
      } catch (RemoteException var6) {
         zzd(var6);
         var5 = null;
      }

      return var5;
   }

   public final void zzf(zzbaz var1, boolean var2) throws RemoteException {
      ((zzj)this.zzrf()).zzd(new GamesClientImpl.zzck(var1), var2);
   }

   public final void zza(zzbaz var1, String var2, boolean var3, int var4) throws RemoteException {
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzcj(var1), var2, var3, var4);
   }

   public final void zza(zzbaz var1, Snapshot var2, SnapshotMetadataChange var3) throws RemoteException {
      SnapshotContents var4;
      com.google.android.gms.common.internal.zzbo.zza(!(var4 = var2.getSnapshotContents()).isClosed(), "Snapshot already closed");
      BitmapTeleporter var5;
      if ((var5 = var3.zzvv()) != null) {
         var5.zzc(this.getContext().getCacheDir());
      }

      com.google.android.gms.drive.zzc var6 = var4.zzsM();
      var4.close();
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzch(var1), (String)var2.getMetadata().getSnapshotId(), (com.google.android.gms.games.snapshot.zze)((com.google.android.gms.games.snapshot.zze)var3), (com.google.android.gms.drive.zzc)var6);
   }

   public final void zza(Snapshot var1) {
      SnapshotContents var2;
      com.google.android.gms.common.internal.zzbo.zza(!(var2 = var1.getSnapshotContents()).isClosed(), "Snapshot already closed");
      com.google.android.gms.drive.zzc var3 = var2.zzsM();
      var2.close();

      try {
         ((zzj)this.zzrf()).zza(var3);
      } catch (RemoteException var4) {
         zzd(var4);
      }
   }

   public final void zzi(zzbaz var1, String var2) throws RemoteException {
      ((zzj)this.zzrf()).zzg(new GamesClientImpl.zzci(var1), var2);
   }

   public final void zza(zzbaz var1, String var2, String var3, SnapshotMetadataChange var4, SnapshotContents var5) throws RemoteException {
      com.google.android.gms.common.internal.zzbo.zza(!var5.isClosed(), "SnapshotContents already closed");
      BitmapTeleporter var6;
      if ((var6 = var4.zzvv()) != null) {
         var6.zzc(this.getContext().getCacheDir());
      }

      com.google.android.gms.drive.zzc var7 = var5.zzsM();
      var5.close();
      ((zzj)this.zzrf()).zza(new GamesClientImpl.zzcj(var1), var2, (String)var3, (com.google.android.gms.games.snapshot.zze)((com.google.android.gms.games.snapshot.zze)var4), (com.google.android.gms.drive.zzc)var7);
   }

   public final void zzh(zzbaz var1) throws RemoteException {
      ((zzj)this.zzrf()).zzc(new GamesClientImpl.zzj(var1));
   }

   public final Intent zzuM() {
      Intent var1;
      try {
         var1 = ((zzj)this.zzrf()).zzuU();
      } catch (RemoteException var2) {
         zzd(var2);
         var1 = null;
      }

      return var1;
   }

   public final void zzi(zzbaz var1) throws RemoteException {
      ((zzj)this.zzrf()).zzd(new GamesClientImpl.zzn(var1));
   }

   public final void zzb(zzbaz var1, int var2) throws RemoteException {
      ((zzj)this.zzrf()).zzb(new GamesClientImpl.zzh(var1), var2);
   }

   public final boolean zzuN() {
      boolean var1 = false;

      try {
         var1 = ((zzj)this.zzrf()).zzuN();
      } catch (RemoteException var2) {
         zzd(var2);
      }

      return var1;
   }

   public final void zze(zzbdw var1) {
      try {
         GamesClientImpl.zzl var2 = new GamesClientImpl.zzl(var1);
         ((zzj)this.zzrf()).zze(var2, this.zzaZw);
      } catch (RemoteException var3) {
         zzd(var3);
      }
   }

   public final void zzuO() {
      try {
         ((zzj)this.zzrf()).zzH(this.zzaZw);
      } catch (RemoteException var1) {
         zzd(var1);
      }
   }

   public final void zza(IBinder var1, Bundle var2) {
      if (this.isConnected()) {
         try {
            ((zzj)this.zzrf()).zza(var1, var2);
            return;
         } catch (RemoteException var3) {
            zzd(var3);
         }
      }

   }

   public final void zzuP() {
      if (this.isConnected()) {
         try {
            ((zzj)this.zzrf()).zzuP();
            return;
         } catch (RemoteException var1) {
            zzd(var1);
         }
      }

   }

   private static void zzd(RemoteException var0) {
      zze.zzc("GamesClientImpl", "service died", var0);
   }

   private static Room zzK(DataHolder var0) {
      com.google.android.gms.games.multiplayer.realtime.zzb var1 = new com.google.android.gms.games.multiplayer.realtime.zzb(var0);
      Room var2 = null;

      try {
         if (var1.getCount() > 0) {
            var2 = (Room)((Room)var1.get(0)).freeze();
         }
      } finally {
         var1.release();
      }

      return var2;
   }

   protected final void zza(int var1, IBinder var2, Bundle var3, int var4) {
      if (var1 == 0 && var3 != null) {
         var3.setClassLoader(GamesClientImpl.class.getClassLoader());
         this.zzaZu = var3.getBoolean("show_welcome_popup");
         this.zzaZy = this.zzaZu;
         this.zzaZr = (PlayerEntity)var3.getParcelable("com.google.android.gms.games.current_player");
         this.zzaZs = (GameEntity)var3.getParcelable("com.google.android.gms.games.current_game");
      }

      super.zza(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   public final void zza(@NonNull IInterface var1) {
      zzj var3 = (zzj)var1;
      super.zza(var3);
      if (this.zzaZu) {
         this.zzaZt.zzuV();
         this.zzaZu = false;
      }

      if (!this.zzaZx.zzaYu && !this.zzaZx.zzaYC) {
         zzj var5 = var3;
         GamesClientImpl var4 = this;

         try {
            GamesClientImpl.zzbo var6 = new GamesClientImpl.zzbo(var4.zzaZt);
            var5.zza((zzh)var6, var4.zzaZw);
            return;
         } catch (RemoteException var7) {
            zzd(var7);
         }
      }

   }

   // $FF: synthetic method
   protected final IInterface zzd(IBinder var1) {
      if (var1 == null) {
         return null;
      } else {
         IInterface var3;
         return (IInterface)((var3 = var1.queryLocalInterface("com.google.android.gms.games.internal.IGamesService")) instanceof zzj ? (zzj)var3 : new zzk(var1));
      }
   }

   // $FF: synthetic method
   static void zza(GamesClientImpl var0, RemoteException var1) {
      zzd(var1);
   }

   static final class CaptureStreamingUrlResultImpl implements Videos.CaptureStreamingUrlResult {
      private final Status mStatus;
      private final String zzD;

      public final Status getStatus() {
         return this.mStatus;
      }

      public final String getUrl() {
         return this.zzD;
      }
   }

   static final class zzo implements Videos.CaptureStateResult {
      private final Status mStatus;
      private final CaptureState zzaZG;

      zzo(Status var1, CaptureState var2) {
         this.mStatus = var1;
         this.zzaZG = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final CaptureState getCaptureState() {
         return this.zzaZG;
      }
   }

   static final class zzk implements Videos.CaptureCapabilitiesResult {
      private final Status mStatus;
      private final VideoCapabilities zzaZE;

      zzk(Status var1, VideoCapabilities var2) {
         this.mStatus = var1;
         this.zzaZE = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final VideoCapabilities getCapabilities() {
         return this.zzaZE;
      }
   }

   static final class zzi implements Videos.CaptureAvailableResult {
      private final Status mStatus;
      private final boolean zzaZD;

      zzi(Status var1, boolean var2) {
         this.mStatus = var1;
         this.zzaZD = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final boolean isAvailable() {
         return this.zzaZD;
      }
   }

   static final class zzs implements Snapshots.DeleteSnapshotResult {
      private final Status mStatus;
      private final String zzaZJ;

      zzs(int var1, String var2) {
         this.mStatus = GamesStatusCodes.zzaY(var1);
         this.zzaZJ = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final String getSnapshotId() {
         return this.zzaZJ;
      }
   }

   static final class zzq extends GamesClientImpl.zzw implements Snapshots.CommitSnapshotResult {
      private final SnapshotMetadata zzaZI;

      zzq(DataHolder var1) {
         super(var1);
         SnapshotMetadataBuffer var2 = new SnapshotMetadataBuffer(var1);

         try {
            if (var2.getCount() > 0) {
               this.zzaZI = new SnapshotMetadataEntity((SnapshotMetadata)var2.get(0));
            } else {
               this.zzaZI = null;
            }
         } finally {
            var2.release();
         }

      }

      public final SnapshotMetadata getSnapshotMetadata() {
         return this.zzaZI;
      }
   }

   static final class zzbc extends GamesClientImpl.zzw implements Snapshots.OpenSnapshotResult {
      private final Snapshot zzbac;
      private final String zzbad;
      private final Snapshot zzbae;
      private final com.google.android.gms.drive.zzc zzbaf;
      private final SnapshotContents zzbag;

      zzbc(DataHolder var1, com.google.android.gms.drive.zzc var2) {
         this(var1, (String)null, var2, (com.google.android.gms.drive.zzc)null, (com.google.android.gms.drive.zzc)null);
      }

      zzbc(DataHolder var1, String var2, com.google.android.gms.drive.zzc var3, com.google.android.gms.drive.zzc var4, com.google.android.gms.drive.zzc var5) {
         super(var1);
         SnapshotMetadataBuffer var6 = new SnapshotMetadataBuffer(var1);

         try {
            if (var6.getCount() == 0) {
               this.zzbac = null;
               this.zzbae = null;
            } else {
               SnapshotMetadataEntity var7;
               if (var6.getCount() == 1) {
                  com.google.android.gms.common.internal.zzc.zzae(var1.getStatusCode() != 4004);
                  var7 = new SnapshotMetadataEntity((SnapshotMetadata)var6.get(0));
                  this.zzbac = new SnapshotEntity(var7, new com.google.android.gms.games.snapshot.zza(var3));
                  this.zzbae = null;
               } else {
                  var7 = new SnapshotMetadataEntity((SnapshotMetadata)var6.get(0));
                  this.zzbac = new SnapshotEntity(var7, new com.google.android.gms.games.snapshot.zza(var3));
                  SnapshotMetadataEntity var8 = new SnapshotMetadataEntity((SnapshotMetadata)var6.get(1));
                  this.zzbae = new SnapshotEntity(var8, new com.google.android.gms.games.snapshot.zza(var4));
               }
            }
         } finally {
            var6.release();
         }

         this.zzbad = var2;
         this.zzbaf = var5;
         this.zzbag = new com.google.android.gms.games.snapshot.zza(var5);
      }

      public final Snapshot getSnapshot() {
         return this.zzbac;
      }

      public final String getConflictId() {
         return this.zzbad;
      }

      public final Snapshot getConflictingSnapshot() {
         return this.zzbae;
      }

      public final SnapshotContents getResolutionSnapshotContents() {
         return this.zzbag;
      }
   }

   static final class zzax extends GamesClientImpl.zzw implements Snapshots.LoadSnapshotsResult {
      zzax(DataHolder var1) {
         super(var1);
      }

      public final SnapshotMetadataBuffer getSnapshots() {
         return new SnapshotMetadataBuffer(this.zzaCX);
      }
   }

   static final class zzas extends GamesClientImpl.zzw implements Stats.LoadPlayerStatsResult {
      private final PlayerStats zzaZU;

      zzas(DataHolder var1) {
         super(var1);
         PlayerStatsBuffer var2 = new PlayerStatsBuffer(var1);

         try {
            if (var2.getCount() > 0) {
               this.zzaZU = new com.google.android.gms.games.stats.zza((PlayerStats)var2.get(0));
            } else {
               this.zzaZU = null;
            }
         } finally {
            var2.release();
         }

      }

      public final PlayerStats getPlayerStats() {
         return this.zzaZU;
      }
   }

   static final class zzp extends GamesClientImpl.zzw implements Quests.ClaimMilestoneResult {
      private final Milestone zzaZH;
      private final Quest zzaZB;

      zzp(DataHolder var1, String var2) {
         super(var1);
         QuestBuffer var3 = new QuestBuffer(var1);

         try {
            if (var3.getCount() <= 0) {
               this.zzaZH = null;
               this.zzaZB = null;
            } else {
               this.zzaZB = new QuestEntity((Quest)var3.get(0));
               List var4 = this.zzaZB.zzvt();
               int var5 = 0;

               for(int var6 = var4.size(); var5 < var6; ++var5) {
                  if (((Milestone)var4.get(var5)).getMilestoneId().equals(var2)) {
                     this.zzaZH = (Milestone)var4.get(var5);
                     return;
                  }
               }

               this.zzaZH = null;
            }
         } finally {
            var3.release();
         }

      }

      public final Milestone getMilestone() {
         return this.zzaZH;
      }

      public final Quest getQuest() {
         return this.zzaZB;
      }
   }

   static final class zzd extends GamesClientImpl.zzw implements Quests.AcceptQuestResult {
      private final Quest zzaZB;

      zzd(DataHolder var1) {
         super(var1);
         QuestBuffer var2 = new QuestBuffer(var1);

         try {
            if (var2.getCount() > 0) {
               this.zzaZB = new QuestEntity((Quest)var2.get(0));
            } else {
               this.zzaZB = null;
            }
         } finally {
            var2.release();
         }

      }

      public final Quest getQuest() {
         return this.zzaZB;
      }
   }

   static final class zzau extends GamesClientImpl.zzw implements Quests.LoadQuestsResult {
      private final DataHolder zzaCX;

      zzau(DataHolder var1) {
         super(var1);
         this.zzaCX = var1;
      }

      public final QuestBuffer getQuests() {
         return new QuestBuffer(this.zzaCX);
      }
   }

   static final class zzav implements Requests.LoadRequestsResult {
      private final Status mStatus;
      private final Bundle zzaZW;

      zzav(Status var1, Bundle var2) {
         this.mStatus = var1;
         this.zzaZW = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final GameRequestBuffer getRequests(int var1) {
         String var10000;
         switch(var1) {
         case 1:
            var10000 = "GIFT";
            break;
         case 2:
            var10000 = "WISH";
            break;
         default:
            zze.zzz("RequestType", (new StringBuilder(33)).append("Unknown request type: ").append(var1).toString());
            var10000 = "UNKNOWN_TYPE";
         }

         String var2 = var10000;
         if (!this.zzaZW.containsKey(var2)) {
            return null;
         } else {
            DataHolder var3 = (DataHolder)this.zzaZW.get(var2);
            return new GameRequestBuffer(var3);
         }
      }

      public final void release() {
         Iterator var1 = this.zzaZW.keySet().iterator();

         while(var1.hasNext()) {
            String var2 = (String)var1.next();
            DataHolder var3;
            if ((var3 = (DataHolder)this.zzaZW.getParcelable(var2)) != null) {
               var3.close();
            }
         }

      }
   }

   static final class zzcw extends GamesClientImpl.zzw implements Requests.UpdateRequestsResult {
      private final zzcam zzbaF;

      zzcw(DataHolder var1) {
         super(var1);
         this.zzbaF = zzcam.zzN(var1);
      }

      public final int getRequestOutcome(String var1) {
         return this.zzbaF.getRequestOutcome(var1);
      }

      public final Set getRequestIds() {
         return this.zzbaF.getRequestIds();
      }
   }

   static final class zzz implements Games.GetServerAuthCodeResult {
      private final Status mStatus;
      private final String zzaZK;

      zzz(Status var1, String var2) {
         this.mStatus = var1;
         this.zzaZK = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final String getCode() {
         return this.zzaZK;
      }
   }

   static final class zzg implements TurnBasedMultiplayer.CancelMatchResult {
      private final Status mStatus;
      private final String zzaZC;

      zzg(Status var1, String var2) {
         this.mStatus = var1;
         this.zzaZC = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final String getMatchId() {
         return this.zzaZC;
      }
   }

   static final class zzaj extends GamesClientImpl.zzcr implements TurnBasedMultiplayer.LeaveMatchResult {
      zzaj(DataHolder var1) {
         super(var1);
      }
   }

   static final class zzcv extends GamesClientImpl.zzcr implements TurnBasedMultiplayer.UpdateMatchResult {
      zzcv(DataHolder var1) {
         super(var1);
      }
   }

   static final class zzaa extends GamesClientImpl.zzcr implements TurnBasedMultiplayer.InitiateMatchResult {
      zzaa(DataHolder var1) {
         super(var1);
      }
   }

   static final class zzap extends GamesClientImpl.zzcr implements TurnBasedMultiplayer.LoadMatchResult {
      zzap(DataHolder var1) {
         super(var1);
      }
   }

   abstract static class zzcr extends GamesClientImpl.zzw {
      private TurnBasedMatch zzbaa;

      zzcr(DataHolder var1) {
         super(var1);
         TurnBasedMatchBuffer var2 = new TurnBasedMatchBuffer(var1);

         try {
            if (var2.getCount() > 0) {
               this.zzbaa = (TurnBasedMatch)((TurnBasedMatch)var2.get(0)).freeze();
            } else {
               this.zzbaa = null;
            }
         } finally {
            var2.release();
         }

      }

      public TurnBasedMatch getMatch() {
         return this.zzbaa;
      }
   }

   static final class zzcm extends GamesClientImpl.zzw implements Leaderboards.SubmitScoreResult {
      private final ScoreSubmissionData zzbay;

      public zzcm(DataHolder var1) {
         super(var1);

         try {
            this.zzbay = new ScoreSubmissionData(var1);
         } finally {
            var1.close();
         }

      }

      public final ScoreSubmissionData getScoreData() {
         return this.zzbay;
      }
   }

   static final class zzat extends GamesClientImpl.zzw implements Players.LoadPlayersResult {
      private final PlayerBuffer zzaZV;

      zzat(DataHolder var1) {
         super(var1);
         this.zzaZV = new PlayerBuffer(var1);
      }

      public final PlayerBuffer getPlayers() {
         return this.zzaZV;
      }
   }

   static final class zzaq implements TurnBasedMultiplayer.LoadMatchesResult {
      private final Status mStatus;
      private final LoadMatchesResponse zzaZS;

      zzaq(Status var1, Bundle var2) {
         this.mStatus = var1;
         this.zzaZS = new LoadMatchesResponse(var2);
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final LoadMatchesResponse getMatches() {
         return this.zzaZS;
      }

      public final void release() {
         this.zzaZS.release();
      }
   }

   static final class zzao extends GamesClientImpl.zzw implements Invitations.LoadInvitationsResult {
      private final InvitationBuffer zzaZR;

      zzao(DataHolder var1) {
         super(var1);
         this.zzaZR = new InvitationBuffer(var1);
      }

      public final InvitationBuffer getInvitations() {
         return this.zzaZR;
      }
   }

   static final class zzar extends GamesClientImpl.zzw implements Leaderboards.LoadPlayerScoreResult {
      private final LeaderboardScoreEntity zzaZT;

      zzar(DataHolder var1) {
         super(var1);
         LeaderboardScoreBuffer var2 = new LeaderboardScoreBuffer(var1);

         try {
            if (var2.getCount() > 0) {
               this.zzaZT = (LeaderboardScoreEntity)((LeaderboardScore)var2.get(0)).freeze();
            } else {
               this.zzaZT = null;
            }
         } finally {
            var2.release();
         }

      }

      public final LeaderboardScore getScore() {
         return this.zzaZT;
      }
   }

   static final class zzaw extends GamesClientImpl.zzw implements Leaderboards.LoadScoresResult {
      private final LeaderboardEntity zzaZX;
      private final LeaderboardScoreBuffer zzaZY;

      zzaw(DataHolder var1, DataHolder var2) {
         super(var2);
         LeaderboardBuffer var3 = new LeaderboardBuffer(var1);

         try {
            if (var3.getCount() > 0) {
               this.zzaZX = (LeaderboardEntity)((Leaderboard)var3.get(0)).freeze();
            } else {
               this.zzaZX = null;
            }
         } finally {
            var3.release();
         }

         this.zzaZY = new LeaderboardScoreBuffer(var2);
      }

      public final Leaderboard getLeaderboard() {
         return this.zzaZX;
      }

      public final LeaderboardScoreBuffer getScores() {
         return this.zzaZY;
      }
   }

   static final class zzag extends GamesClientImpl.zzw implements Leaderboards.LeaderboardMetadataResult {
      private final LeaderboardBuffer zzaZM;

      zzag(DataHolder var1) {
         super(var1);
         this.zzaZM = new LeaderboardBuffer(var1);
      }

      public final LeaderboardBuffer getLeaderboards() {
         return this.zzaZM;
      }
   }

   static final class zzan extends GamesClientImpl.zzw implements GamesMetadata.LoadGamesResult {
      private final GameBuffer zzaZQ;

      zzan(DataHolder var1) {
         super(var1);
         this.zzaZQ = new GameBuffer(var1);
      }

      public final GameBuffer getGames() {
         return this.zzaZQ;
      }
   }

   static final class zzam extends GamesClientImpl.zzw implements Events.LoadEventsResult {
      private final EventBuffer zzaZP;

      zzam(DataHolder var1) {
         super(var1);
         this.zzaZP = new EventBuffer(var1);
      }

      public final EventBuffer getEvents() {
         return this.zzaZP;
      }
   }

   static final class zzcu implements Achievements.UpdateAchievementResult {
      private final Status mStatus;
      private final String zzaZb;

      zzcu(int var1, String var2) {
         this.mStatus = GamesStatusCodes.zzaY(var1);
         this.zzaZb = var2;
      }

      public final Status getStatus() {
         return this.mStatus;
      }

      public final String getAchievementId() {
         return this.zzaZb;
      }
   }

   static final class zzal extends GamesClientImpl.zzw implements Achievements.LoadAchievementsResult {
      private final AchievementBuffer zzaZO;

      zzal(DataHolder var1) {
         super(var1);
         this.zzaZO = new AchievementBuffer(var1);
      }

      public final AchievementBuffer getAchievements() {
         return this.zzaZO;
      }
   }

   abstract static class zzw extends zzbby {
      protected zzw(DataHolder var1) {
         super(var1, GamesStatusCodes.zzaY(var1.getStatusCode()));
      }
   }

   static final class zzbu implements zzbdz {
      private final int zzaxu;
      private final String zzbam;
      private final int zzban;

      zzbu(int var1, int var2, String var3) {
         this.zzaxu = var1;
         this.zzban = var2;
         this.zzbam = var3;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         RealTimeMultiplayer.ReliableMessageSentCallback var3 = (RealTimeMultiplayer.ReliableMessageSentCallback)var1;
         if (var3 != null) {
            var3.onRealTimeMessageSent(this.zzaxu, this.zzban, this.zzbam);
         }

      }
   }

   static final class zzbb implements zzbdz {
      private final RealTimeMessage zzbab;

      zzbb(RealTimeMessage var1) {
         this.zzbab = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         RealTimeMessageReceivedListener var3 = (RealTimeMessageReceivedListener)var1;
         var3.onRealTimeMessageReceived(this.zzbab);
      }
   }

   static final class zzbe implements zzbdz {
      private final String zzbah;

      zzbe(String var1) {
         this.zzbah = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         RoomStatusUpdateListener var3 = (RoomStatusUpdateListener)var1;
         var3.onP2PDisconnected(this.zzbah);
      }
   }

   static final class zzbd implements zzbdz {
      private final String zzbah;

      zzbd(String var1) {
         this.zzbah = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         RoomStatusUpdateListener var3 = (RoomStatusUpdateListener)var1;
         var3.onP2PConnected(this.zzbah);
      }
   }

   static final class zzbh extends GamesClientImpl.zza {
      zzbh(DataHolder var1, String[] var2) {
         super(var1, var2);
      }

      protected final void zza(RoomStatusUpdateListener var1, Room var2, ArrayList var3) {
         var1.onPeersDisconnected(var2, var3);
      }
   }

   static final class zzbf extends GamesClientImpl.zza {
      zzbf(DataHolder var1, String[] var2) {
         super(var1, var2);
      }

      protected final void zza(RoomStatusUpdateListener var1, Room var2, ArrayList var3) {
         var1.onPeersConnected(var2, var3);
      }
   }

   static final class zzbg extends GamesClientImpl.zza {
      zzbg(DataHolder var1, String[] var2) {
         super(var1, var2);
      }

      protected final void zza(RoomStatusUpdateListener var1, Room var2, ArrayList var3) {
         var1.onPeerDeclined(var2, var3);
      }
   }

   static final class zzbk extends GamesClientImpl.zza {
      zzbk(DataHolder var1, String[] var2) {
         super(var1, var2);
      }

      protected final void zza(RoomStatusUpdateListener var1, Room var2, ArrayList var3) {
         var1.onPeerLeft(var2, var3);
      }
   }

   static final class zzbj extends GamesClientImpl.zza {
      zzbj(DataHolder var1, String[] var2) {
         super(var1, var2);
      }

      protected final void zza(RoomStatusUpdateListener var1, Room var2, ArrayList var3) {
         var1.onPeerJoined(var2, var3);
      }
   }

   static final class zzbi extends GamesClientImpl.zza {
      zzbi(DataHolder var1, String[] var2) {
         super(var1, var2);
      }

      protected final void zza(RoomStatusUpdateListener var1, Room var2, ArrayList var3) {
         var1.onPeerInvitedToRoom(var2, var3);
      }
   }

   static final class zzt extends GamesClientImpl.zzc {
      zzt(DataHolder var1) {
         super(var1);
      }

      public final void zza(RoomStatusUpdateListener var1, Room var2) {
         var1.onDisconnectedFromRoom(var2);
      }
   }

   static final class zzr extends GamesClientImpl.zzc {
      zzr(DataHolder var1) {
         super(var1);
      }

      public final void zza(RoomStatusUpdateListener var1, Room var2) {
         var1.onConnectedToRoom(var2);
      }
   }

   static final class zzcb extends GamesClientImpl.zzc {
      zzcb(DataHolder var1) {
         super(var1);
      }

      public final void zza(RoomStatusUpdateListener var1, Room var2) {
         var1.onRoomAutoMatching(var2);
      }
   }

   static final class zzce extends GamesClientImpl.zzc {
      zzce(DataHolder var1) {
         super(var1);
      }

      public final void zza(RoomStatusUpdateListener var1, Room var2) {
         var1.onRoomConnecting(var2);
      }
   }

   static final class zzcd extends GamesClientImpl.zzb {
      zzcd(DataHolder var1) {
         super(var1);
      }

      public final void zza(RoomUpdateListener var1, Room var2, int var3) {
         var1.onRoomConnected(var3, var2);
      }
   }

   static final class zzak implements zzbdz {
      private final int zzaxu;
      private final String zzaZN;

      zzak(int var1, String var2) {
         this.zzaxu = var1;
         this.zzaZN = var2;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         RoomUpdateListener var3 = (RoomUpdateListener)var1;
         var3.onLeftRoom(this.zzaxu, this.zzaZN);
      }
   }

   static final class zzaf extends GamesClientImpl.zzb {
      public zzaf(DataHolder var1) {
         super(var1);
      }

      public final void zza(RoomUpdateListener var1, Room var2, int var3) {
         var1.onJoinedRoom(var3, var2);
      }
   }

   static final class zzcf extends GamesClientImpl.zzb {
      public zzcf(DataHolder var1) {
         super(var1);
      }

      public final void zza(RoomUpdateListener var1, Room var2, int var3) {
         var1.onRoomCreated(var3, var2);
      }
   }

   abstract static class zza extends GamesClientImpl.zzc {
      private final ArrayList zzaZA = new ArrayList();

      zza(DataHolder var1, String[] var2) {
         super(var1);
         int var3 = 0;

         for(int var4 = var2.length; var3 < var4; ++var3) {
            this.zzaZA.add(var2[var3]);
         }

      }

      protected final void zza(RoomStatusUpdateListener var1, Room var2) {
         this.zza(var1, var2, this.zzaZA);
      }

      protected abstract void zza(RoomStatusUpdateListener var1, Room var2, ArrayList var3);
   }

   abstract static class zzc extends zzbbx {
      zzc(DataHolder var1) {
         super(var1);
      }

      protected abstract void zza(RoomStatusUpdateListener var1, Room var2);

      // $FF: synthetic method
      protected final void zza(Object var1, DataHolder var2) {
         this.zza((RoomStatusUpdateListener)var1, GamesClientImpl.zzK(var2));
      }
   }

   abstract static class zzb extends zzbbx {
      zzb(DataHolder var1) {
         super(var1);
      }

      protected abstract void zza(RoomUpdateListener var1, Room var2, int var3);

      // $FF: synthetic method
      protected final void zza(Object var1, DataHolder var2) {
         this.zza((RoomUpdateListener)var1, GamesClientImpl.zzK(var2), var2.getStatusCode());
      }
   }

   static final class zzby implements zzbdz {
      private final String zzQx;

      zzby(String var1) {
         this.zzQx = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         OnRequestReceivedListener var3 = (OnRequestReceivedListener)var1;
         var3.onRequestRemoved(this.zzQx);
      }
   }

   static final class zzbx implements zzbdz {
      private final GameRequest zzbap;

      zzbx(GameRequest var1) {
         this.zzbap = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         OnRequestReceivedListener var3 = (OnRequestReceivedListener)var1;
         var3.onRequestReceived(this.zzbap);
      }
   }

   static final class zzbq implements zzbdz {
      private final Quest zzaZB;

      zzbq(Quest var1) {
         this.zzaZB = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         QuestUpdateListener var3 = (QuestUpdateListener)var1;
         var3.onQuestCompleted(this.zzaZB);
      }
   }

   static final class zzay implements zzbdz {
      private final String zzaZZ;

      zzay(String var1) {
         this.zzaZZ = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         OnTurnBasedMatchUpdateReceivedListener var3 = (OnTurnBasedMatchUpdateReceivedListener)var1;
         var3.onTurnBasedMatchRemoved(this.zzaZZ);
      }
   }

   static final class zzba implements zzbdz {
      private final TurnBasedMatch zzbaa;

      zzba(TurnBasedMatch var1) {
         this.zzbaa = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         OnTurnBasedMatchUpdateReceivedListener var3 = (OnTurnBasedMatchUpdateReceivedListener)var1;
         var3.onTurnBasedMatchReceived(this.zzbaa);
      }
   }

   static final class zzad implements zzbdz {
      private final String zzajX;

      zzad(String var1) {
         this.zzajX = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         OnInvitationReceivedListener var3 = (OnInvitationReceivedListener)var1;
         var3.onInvitationRemoved(this.zzajX);
      }
   }

   static final class zzac implements zzbdz {
      private final Invitation zzaZL;

      zzac(Invitation var1) {
         this.zzaZL = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         OnInvitationReceivedListener var3 = (OnInvitationReceivedListener)var1;
         var3.onInvitationReceived(this.zzaZL);
      }
   }

   static final class zzm implements zzbdz {
      private final int zzaZF;

      zzm(int var1) {
         this.zzaZF = var1;
      }

      public final void zzpT() {
      }

      // $FF: synthetic method
      public final void zzq(Object var1) {
         Videos.CaptureOverlayStateListener var3 = (Videos.CaptureOverlayStateListener)var1;
         var3.onCaptureOverlayStateChanged(this.zzaZF);
      }
   }

   static final class zzn extends zza {
      private final zzbaz zzaIz;

      public zzn(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzd(int var1, Bundle var2) {
         this.zzaIz.setResult(new GamesClientImpl.zzo(new Status(var1), CaptureState.zzs(var2)));
      }
   }

   static final class zzl extends zza {
      private final zzbdw zzaOo;

      zzl(zzbdw var1) {
         this.zzaOo = (zzbdw)com.google.android.gms.common.internal.zzbo.zzb(var1, "Callback must not be null");
      }

      public final void onCaptureOverlayStateChanged(int var1) {
         this.zzaOo.zza(new GamesClientImpl.zzm(var1));
      }
   }

   static final class zzj extends zza {
      private final zzbaz zzaIz;

      zzj(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zza(int var1, VideoCapabilities var2) {
         this.zzaIz.setResult(new GamesClientImpl.zzk(new Status(var1), var2));
      }
   }

   static final class zzh extends zza {
      private final zzbaz zzaIz;

      zzh(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzh(int var1, boolean var2) {
         this.zzaIz.setResult(new GamesClientImpl.zzi(new Status(var1), var2));
      }
   }

   static final class zzci extends zza {
      private final zzbaz zzaIz;

      public zzci(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzj(int var1, String var2) {
         this.zzaIz.setResult(new GamesClientImpl.zzs(var1, var2));
      }
   }

   static final class zzch extends zza {
      private final zzbaz zzbav;

      public zzch(zzbaz var1) {
         this.zzbav = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzE(DataHolder var1) {
         this.zzbav.setResult(new GamesClientImpl.zzq(var1));
      }
   }

   static final class zzcj extends zza {
      private final zzbaz zzbaw;

      public zzcj(zzbaz var1) {
         this.zzbaw = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zza(DataHolder var1, com.google.android.gms.drive.zzc var2) {
         this.zzbaw.setResult(new GamesClientImpl.zzbc(var1, var2));
      }

      public final void zza(DataHolder var1, String var2, com.google.android.gms.drive.zzc var3, com.google.android.gms.drive.zzc var4, com.google.android.gms.drive.zzc var5) {
         this.zzbaw.setResult(new GamesClientImpl.zzbc(var1, var2, var3, var4, var5));
      }
   }

   static final class zzck extends zza {
      private final zzbaz zzbax;

      public zzck(zzbaz var1) {
         this.zzbax = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzD(DataHolder var1) {
         this.zzbax.setResult(new GamesClientImpl.zzax(var1));
      }
   }

   static final class zzbm extends zza {
      private final zzbaz zzaIz;

      public zzbm(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzJ(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzas(var1));
      }
   }

   static final class zzbt extends zza {
      private final zzbaz zzbal;

      public zzbt(zzbaz var1) {
         this.zzbal = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzI(DataHolder var1) {
         this.zzbal.setResult(new GamesClientImpl.zzau(var1));
      }
   }

   static final class zzbr extends zza {
      private final zzbaz zzbaj;
      private final String zzbak;

      public zzbr(zzbaz var1, String var2) {
         this.zzbaj = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
         this.zzbak = (String)com.google.android.gms.common.internal.zzbo.zzb(var2, "MilestoneId must not be null");
      }

      public final void zzF(DataHolder var1) {
         this.zzbaj.setResult(new GamesClientImpl.zzp(var1, this.zzbak));
      }
   }

   static final class zzbp extends zza {
      private final zzbaz zzbai;

      public zzbp(zzbaz var1) {
         this.zzbai = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzG(DataHolder var1) {
         this.zzbai.setResult(new GamesClientImpl.zzd(var1));
      }
   }

   static final class zzbz extends zza {
      private final zzbaz zzbaq;

      public zzbz(zzbaz var1) {
         this.zzbaq = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzc(int var1, Bundle var2) {
         var2.setClassLoader(this.getClass().getClassLoader());
         Status var3 = GamesStatusCodes.zzaY(var1);
         this.zzbaq.setResult(new GamesClientImpl.zzav(var3, var2));
      }
   }

   static final class zzca extends zza {
      private final zzbaz zzbar;

      public zzca(zzbaz var1) {
         this.zzbar = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzC(DataHolder var1) {
         this.zzbar.setResult(new GamesClientImpl.zzcw(var1));
      }
   }

   static final class zzbo extends zzb {
      private final zzn zzaZt;

      public zzbo(zzn var1) {
         this.zzaZt = var1;
      }

      public final zzl zzur() {
         return new zzl(this.zzaZt.zzbaL);
      }
   }

   static final class zzy extends zza {
      private final zzbaz zzaIz;

      public zzy(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzg(int var1, String var2) {
         Status var3 = GamesStatusCodes.zzaY(var1);
         this.zzaIz.setResult(new GamesClientImpl.zzz(var3, var2));
      }
   }

   static final class zzcg extends zza {
      private final zzbaz zzaIz;

      public zzcg(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzuq() {
         Status var1 = GamesStatusCodes.zzaY(0);
         this.zzaIz.setResult(var1);
      }
   }

   static final class zzbv extends zza {
      private zzbdw zzbao;

      public zzbv(zzbdw var1) {
         this.zzbao = var1;
      }

      public final void zzb(int var1, int var2, String var3) {
         if (this.zzbao != null) {
            this.zzbao.zza(new GamesClientImpl.zzbu(var1, var2, var3));
         }

      }
   }

   static final class zzcc extends zza {
      private final zzbdw zzbas;
      private final zzbdw zzbat;
      private final zzbdw zzbau;

      public zzcc(zzbdw var1) {
         this.zzbas = (zzbdw)com.google.android.gms.common.internal.zzbo.zzb(var1, "Callbacks must not be null");
         this.zzbat = null;
         this.zzbau = null;
      }

      public zzcc(zzbdw var1, zzbdw var2, zzbdw var3) {
         this.zzbas = (zzbdw)com.google.android.gms.common.internal.zzbo.zzb(var1, "Callbacks must not be null");
         this.zzbat = var2;
         this.zzbau = var3;
      }

      public final void zzu(DataHolder var1) {
         this.zzbas.zza(new GamesClientImpl.zzcf(var1));
      }

      public final void zzv(DataHolder var1) {
         this.zzbas.zza(new GamesClientImpl.zzaf(var1));
      }

      public final void onLeftRoom(int var1, String var2) {
         this.zzbas.zza(new GamesClientImpl.zzak(var1, var2));
      }

      public final void zzy(DataHolder var1) {
         this.zzbas.zza(new GamesClientImpl.zzcd(var1));
      }

      public final void zzw(DataHolder var1) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzce(var1));
         }

      }

      public final void zzx(DataHolder var1) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzcb(var1));
         }

      }

      public final void zzz(DataHolder var1) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzr(var1));
         }

      }

      public final void zzA(DataHolder var1) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzt(var1));
         }

      }

      public final void zze(DataHolder var1, String[] var2) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzbf(var1, var2));
         }

      }

      public final void zzf(DataHolder var1, String[] var2) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzbh(var1, var2));
         }

      }

      public final void zza(DataHolder var1, String[] var2) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzbi(var1, var2));
         }

      }

      public final void zzb(DataHolder var1, String[] var2) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzbj(var1, var2));
         }

      }

      public final void zzc(DataHolder var1, String[] var2) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzbk(var1, var2));
         }

      }

      public final void zzd(DataHolder var1, String[] var2) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzbg(var1, var2));
         }

      }

      public final void onP2PConnected(String var1) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzbd(var1));
         }

      }

      public final void onP2PDisconnected(String var1) {
         if (this.zzbat != null) {
            this.zzbat.zza(new GamesClientImpl.zzbe(var1));
         }

      }

      public final void onRealTimeMessageReceived(RealTimeMessage var1) {
         if (this.zzbau != null) {
            this.zzbau.zza(new GamesClientImpl.zzbb(var1));
         }

      }
   }

   static final class zzcn extends zza {
      private final zzbaz zzbaz;

      public zzcn(zzbaz var1) {
         this.zzbaz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzi(int var1, String var2) {
         Status var3 = GamesStatusCodes.zzaY(var1);
         this.zzbaz.setResult(new GamesClientImpl.zzg(var3, var2));
      }
   }

   static final class zzcp extends zza {
      private final zzbaz zzbaB;

      public zzcp(zzbaz var1) {
         this.zzbaB = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzs(DataHolder var1) {
         this.zzbaB.setResult(new GamesClientImpl.zzaj(var1));
      }
   }

   static final class zzcs extends zza {
      private final zzbaz zzbaD;

      public zzcs(zzbaz var1) {
         this.zzbaD = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzr(DataHolder var1) {
         this.zzbaD.setResult(new GamesClientImpl.zzcv(var1));
      }
   }

   static final class zzco extends zza {
      private final zzbaz zzbaA;

      public zzco(zzbaz var1) {
         this.zzbaA = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzq(DataHolder var1) {
         this.zzbaA.setResult(new GamesClientImpl.zzaa(var1));
      }
   }

   static final class zzcq extends zza {
      private final zzbaz zzbaC;

      public zzcq(zzbaz var1) {
         this.zzbaC = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzp(DataHolder var1) {
         this.zzbaC.setResult(new GamesClientImpl.zzap(var1));
      }
   }

   static final class zzct extends zza {
      private final zzbaz zzbaE;

      public zzct(zzbaz var1) {
         this.zzbaE = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzb(int var1, Bundle var2) {
         var2.setClassLoader(this.getClass().getClassLoader());
         Status var3 = GamesStatusCodes.zzaY(var1);
         this.zzbaE.setResult(new GamesClientImpl.zzaq(var3, var2));
      }
   }

   static final class zzcl extends zza {
      private final zzbaz zzaIz;

      public zzcl(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzi(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzcm(var1));
      }
   }

   static final class zzbn extends zza {
      private final zzbaz zzaIz;

      zzbn(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzj(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzat(var1));
      }

      public final void zzk(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzat(var1));
      }
   }

   static final class zzbw extends zza {
      private final zzbdw zzaOo;

      zzbw(zzbdw var1) {
         this.zzaOo = var1;
      }

      public final void zzo(DataHolder var1) {
         GameRequestBuffer var2 = new GameRequestBuffer(var1);
         GameRequest var3 = null;

         try {
            if (var2.getCount() > 0) {
               var3 = (GameRequest)((GameRequest)var2.get(0)).freeze();
            }
         } finally {
            var2.release();
         }

         if (var3 != null) {
            this.zzaOo.zza(new GamesClientImpl.zzbx(var3));
         }

      }

      public final void onRequestRemoved(String var1) {
         this.zzaOo.zza(new GamesClientImpl.zzby(var1));
      }
   }

   static final class zzbs extends zza {
      private final zzbdw zzaOo;

      zzbs(zzbdw var1) {
         this.zzaOo = var1;
      }

      public final void zzH(DataHolder var1) {
         Quest var2;
         if ((var2 = zzM(var1)) != null) {
            this.zzaOo.zza(new GamesClientImpl.zzbq(var2));
         }

      }

      private static Quest zzM(DataHolder var0) {
         QuestBuffer var1 = new QuestBuffer(var0);
         Quest var2 = null;

         try {
            if (var1.getCount() > 0) {
               var2 = (Quest)((Quest)var1.get(0)).freeze();
            }
         } finally {
            var1.release();
         }

         return var2;
      }
   }

   static final class zzaz extends zza {
      private final zzbdw zzaOo;

      zzaz(zzbdw var1) {
         this.zzaOo = var1;
      }

      public final void zzt(DataHolder var1) {
         TurnBasedMatchBuffer var2 = new TurnBasedMatchBuffer(var1);
         TurnBasedMatch var3 = null;

         try {
            if (var2.getCount() > 0) {
               var3 = (TurnBasedMatch)((TurnBasedMatch)var2.get(0)).freeze();
            }
         } finally {
            var2.release();
         }

         if (var3 != null) {
            this.zzaOo.zza(new GamesClientImpl.zzba(var3));
         }

      }

      public final void onTurnBasedMatchRemoved(String var1) {
         this.zzaOo.zza(new GamesClientImpl.zzay(var1));
      }
   }

   static final class zzab extends zza {
      private final zzbdw zzaOo;

      zzab(zzbdw var1) {
         this.zzaOo = var1;
      }

      public final void zzn(DataHolder var1) {
         InvitationBuffer var2 = new InvitationBuffer(var1);
         Invitation var3 = null;

         try {
            if (var2.getCount() > 0) {
               var3 = (Invitation)((Invitation)var2.get(0)).freeze();
            }
         } finally {
            var2.release();
         }

         if (var3 != null) {
            this.zzaOo.zza(new GamesClientImpl.zzac(var3));
         }

      }

      public final void onInvitationRemoved(String var1) {
         this.zzaOo.zza(new GamesClientImpl.zzad(var1));
      }
   }

   static final class zzae extends zza {
      private final zzbaz zzaIz;

      zzae(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzm(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzao(var1));
      }
   }

   static final class zzbl extends zza {
      private final zzbaz zzaIz;

      zzbl(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzB(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzar(var1));
      }
   }

   static final class zzah extends zza {
      private final zzbaz zzaIz;

      zzah(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zza(DataHolder var1, DataHolder var2) {
         this.zzaIz.setResult(new GamesClientImpl.zzaw(var1, var2));
      }
   }

   static final class zzai extends zza {
      private final zzbaz zzaIz;

      zzai(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzh(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzag(var1));
      }
   }

   static final class zzu extends zza {
      private final zzbaz zzaIz;

      zzu(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzg(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzam(var1));
      }
   }

   static final class zzx extends zza {
      private final zzbaz zzaIz;

      zzx(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzl(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzan(var1));
      }
   }

   static final class zze extends zza {
      private final zzbaz zzaIz;

      zze(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzh(int var1, String var2) {
         this.zzaIz.setResult(new GamesClientImpl.zzcu(var1, var2));
      }
   }

   static final class zzf extends zza {
      private final zzbaz zzaIz;

      zzf(zzbaz var1) {
         this.zzaIz = (zzbaz)com.google.android.gms.common.internal.zzbo.zzb(var1, "Holder must not be null");
      }

      public final void zzf(DataHolder var1) {
         this.zzaIz.setResult(new GamesClientImpl.zzal(var1));
      }
   }

   class zzv extends zzcaj {
      // $FF: synthetic field
      private GamesClientImpl zzaZz;

      public zzv() {
         this.zzaZz = GamesClientImpl.this;
         super(GamesClientImpl.this.getContext().getMainLooper(), 1000);
      }

      protected final void zzq(String var1, int var2) {
         try {
            if (this.zzaZz.isConnected()) {
               ((zzj)this.zzaZz.zzrf()).zzn(var1, var2);
            } else {
               zze.zzz("GamesClientImpl", (new StringBuilder(89 + String.valueOf(var1).length())).append("Unable to increment event ").append(var1).append(" by ").append(var2).append(" because the games client is no longer connected").toString());
            }
         } catch (RemoteException var4) {
            GamesClientImpl.zza(this.zzaZz, var4);
         }
      }
   }
}
