package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzawy extends zzaxt {
   private static String NAMESPACE = zzaye.zzcj("com.google.cast.games");
   private static final zzayo zzapq = new zzayo("GameManagerChannel");
   private final Map zzawR = new ConcurrentHashMap();
   private final List zzawS;
   private final SharedPreferences zzBT;
   private final String zzawT;
   private final Cast.CastApi zzasb;
   private final GoogleApiClient zzXj;
   private zzaxl zzawU;
   private boolean zzawV = false;
   private GameManagerState zzawW;
   private GameManagerState zzawX;
   private String zzawY;
   private JSONObject zzawZ;
   private long zzaxa = 0L;
   private GameManagerClient.Listener zzaxb;
   private final zze zzvw;
   private String zzaxc;

   public zzawy(GoogleApiClient var1, String var2, Cast.CastApi var3) throws IllegalArgumentException, IllegalStateException {
      super(NAMESPACE, "CastGameManagerChannel", (String)null);
      if (TextUtils.isEmpty(var2)) {
         throw new IllegalArgumentException("castSessionId cannot be null.");
      } else if (var1 != null && var1.isConnected() && var1.hasConnectedApi(Cast.API)) {
         this.zzvw = zzi.zzrY();
         this.zzawS = new ArrayList();
         this.zzawT = var2;
         this.zzasb = var3;
         this.zzXj = var1;
         Context var4 = var1.getContext().getApplicationContext();
         String var5 = String.format(Locale.ROOT, "%s.%s", var4.getPackageName(), "game_manager_channel_data");
         this.zzBT = var4.getApplicationContext().getSharedPreferences(var5, 0);
         this.zzawX = null;
         this.zzawW = new zzaxn(0, 0, "", (JSONObject)null, new ArrayList(), "", -1);
      } else {
         throw new IllegalArgumentException("googleApiClient needs to be connected and contain the Cast.API API.");
      }
   }

   public final synchronized PendingResult zza(GameManagerClient var1) throws IllegalArgumentException {
      return this.zzXj.zze(new zzawz(this, var1));
   }

   public final synchronized void dispose() throws IllegalStateException {
      if (!this.zzawV) {
         this.zzawW = null;
         this.zzawX = null;
         this.zzawY = null;
         this.zzawZ = null;
         this.zzawV = true;

         try {
            this.zzasb.removeMessageReceivedCallbacks(this.zzXj, this.getNamespace());
         } catch (IOException var2) {
            zzapq.zzf("Exception while detaching game manager channel.", var2);
         }
      }
   }

   public final synchronized PendingResult zza(String var1, int var2, JSONObject var3) throws IllegalStateException {
      this.zzot();
      return this.zzXj.zze(new zzaxb(this, var2, var1, var3));
   }

   public final synchronized void sendGameMessage(String var1, JSONObject var2) throws IllegalStateException {
      this.zzot();
      long var3 = ++this.zzaxa;
      JSONObject var5;
      if ((var5 = this.zza(var3, var1, 7, var2)) != null) {
         this.zzasb.sendMessage(this.zzXj, this.getNamespace(), var5.toString());
      }
   }

   public final synchronized PendingResult sendGameRequest(String var1, JSONObject var2) throws IllegalStateException {
      this.zzot();
      return this.zzXj.zze(new zzaxc(this, var1, var2));
   }

   public final synchronized GameManagerState getCurrentState() throws IllegalStateException {
      this.zzot();
      return this.zzawW;
   }

   public final synchronized String getLastUsedPlayerId() throws IllegalStateException {
      this.zzot();
      return this.zzaxc;
   }

   private final synchronized String zzcg(String var1) throws IllegalStateException {
      return var1 == null ? null : (String)this.zzawR.get(var1);
   }

   public final synchronized void setListener(GameManagerClient.Listener var1) {
      this.zzaxb = var1;
   }

   public final void zzch(String var1) {
      zzapq.zzb("message received: %s", var1);

      zzaxm var2;
      try {
         var2 = zzaxm.zzo(new JSONObject(var1));
      } catch (JSONException var6) {
         zzapq.zzf("Message is malformed (%s); ignoring: %s", var6.getMessage(), var1);
         return;
      }

      if (var2 == null) {
         zzapq.zzf("Could not parse game manager message from string: %s", var1);
      } else if ((this.isInitialized() || var2.zzawU != null) && !this.isDisposed()) {
         boolean var3;
         if ((var3 = var2.zzaxt == 1) && !TextUtils.isEmpty(var2.zzaxB)) {
            this.zzawR.put(var2.zzaxn, var2.zzaxB);
            this.zzou();
         }

         if (var2.zzaxu == 0) {
            this.zza(var2);
         } else {
            zzapq.zzf("Not updating from game message because the message contains error code: %d", var2.zzaxu);
         }

         int var5 = var2.zzaxu;
         short var10000;
         switch(var2.zzaxu) {
         case 0:
            var10000 = 0;
            break;
         case 1:
            var10000 = 2001;
            break;
         case 2:
            var10000 = 2003;
            break;
         case 3:
            var10000 = 2150;
            break;
         case 4:
            var10000 = 2151;
            break;
         default:
            zzapq.zzf((new StringBuilder(53)).append("Unknown GameManager protocol status code: ").append(var5).toString());
            var10000 = 13;
         }

         short var4 = var10000;
         if (var3) {
            this.zzb(var2.zzaxo, var4, var2);
         }

         if (this.isInitialized() && var4 == 0) {
            if (this.zzaxb != null) {
               if (this.zzawX != null && !this.zzawW.equals(this.zzawX)) {
                  this.zzaxb.onStateChanged(this.zzawW, this.zzawX);
               }

               if (this.zzawZ != null && this.zzawY != null) {
                  this.zzaxb.onGameMessageReceived(this.zzawY, this.zzawZ);
               }
            }

            this.zzawX = null;
            this.zzawY = null;
            this.zzawZ = null;
         }

      }
   }

   public final synchronized boolean isDisposed() {
      return this.zzawV;
   }

   private final synchronized boolean isInitialized() {
      return this.zzawU != null;
   }

   public final void zzc(long var1, int var3) {
      this.zzb(var1, var3, (Object)null);
   }

   protected final boolean zzz(long var1) {
      Iterator var3 = this.zzawS.iterator();

      while(var3.hasNext()) {
         if (((zzayu)var3.next()).zzd(var1, 15)) {
            var3.remove();
         }
      }

      boolean var4 = false;
      Object var5 = zzayu.zzrl;
      synchronized(zzayu.zzrl) {
         Iterator var6 = this.zzawS.iterator();

         while(var6.hasNext()) {
            if (((zzayu)var6.next()).zzoO()) {
               var4 = true;
               break;
            }
         }

         return var4;
      }
   }

   private final synchronized void zzot() throws IllegalStateException {
      if (!this.isInitialized()) {
         throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel before it is initialized.");
      } else if (this.isDisposed()) {
         throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel after it has been disposed.");
      }
   }

   private final void zza(String var1, int var2, JSONObject var3, zzayt var4) {
      long var5 = ++this.zzaxa;
      JSONObject var7;
      if ((var7 = this.zza(var5, var1, var2, var3)) == null) {
         var4.zza(-1L, 2001, (Object)null);
         zzapq.zzf("Not sending request because it was invalid.");
      } else {
         zzayu var8;
         (var8 = new zzayu(this.zzvw, 30000L)).zza(var5, var4);
         this.zzawS.add(var8);
         this.zzZ(true);
         this.zzasb.sendMessage(this.zzXj, this.getNamespace(), var7.toString()).setResultCallback(new zzaxd(this, var5));
      }
   }

   private final JSONObject zza(long var1, String var3, int var4, JSONObject var5) {
      JSONObject var6;
      try {
         (var6 = new JSONObject()).put("requestId", var1);
         var6.put("type", var4);
         var6.put("extraMessageData", var5);
         var6.put("playerId", var3);
         var6.put("playerToken", this.zzcg(var3));
      } catch (JSONException var8) {
         zzapq.zzf("JSONException when trying to create a message: %s", var8.getMessage());
         var6 = null;
      }

      return var6;
   }

   private final synchronized void zza(zzaxm var1) {
      boolean var2 = var1.zzaxt == 1;
      this.zzawX = this.zzawW;
      if (var2 && var1.zzawU != null) {
         this.zzawU = var1.zzawU;
      }

      if (this.isInitialized()) {
         ArrayList var3 = new ArrayList();
         Iterator var4 = var1.zzaxy.iterator();

         while(var4.hasNext()) {
            zzaxp var5;
            String var6 = (var5 = (zzaxp)var4.next()).getPlayerId();
            zzaxo var7 = new zzaxo(var6, var5.getPlayerState(), var5.getPlayerData(), this.zzawR.containsKey(var6));
            var3.add(var7);
         }

         this.zzawW = new zzaxn(var1.zzaxx, var1.zzaxw, var1.zzaxA, var1.zzaxz, var3, this.zzawU.zzox(), this.zzawU.getMaxPlayers());
         PlayerInfo var8;
         if ((var8 = this.zzawW.getPlayer(var1.zzaxn)) != null && var8.isControllable() && var1.zzaxt == 2) {
            this.zzawY = var1.zzaxn;
            this.zzawZ = var1.zzaxp;
         }

      }
   }

   private final void zzb(long var1, int var3, Object var4) {
      Iterator var5 = this.zzawS.iterator();

      while(var5.hasNext()) {
         if (((zzayu)var5.next()).zzc(var1, var3, var4)) {
            var5.remove();
         }
      }

   }

   private final synchronized void zzou() {
      try {
         JSONObject var1;
         (var1 = new JSONObject()).put("castSessionId", this.zzawT);
         var1.put("playerTokenMap", new JSONObject(this.zzawR));
         this.zzBT.edit().putString("save_data", var1.toString()).commit();
      } catch (JSONException var2) {
         zzapq.zzf("Error while saving data: %s", var2.getMessage());
      }
   }

   private final synchronized void zzov() {
      String var1;
      if ((var1 = this.zzBT.getString("save_data", (String)null)) != null) {
         try {
            JSONObject var2;
            String var3 = (var2 = new JSONObject(var1)).getString("castSessionId");
            if (this.zzawT.equals(var3)) {
               JSONObject var4;
               Iterator var5 = (var4 = var2.getJSONObject("playerTokenMap")).keys();

               while(var5.hasNext()) {
                  String var6 = (String)var5.next();
                  this.zzawR.put(var6, var4.getString(var6));
               }

               this.zzaxa = 0L;
            }

         } catch (JSONException var7) {
            zzapq.zzf("Error while loading data: %s", var7.getMessage());
         }
      }
   }

   // $FF: synthetic method
   static GoogleApiClient zza(zzawy var0) {
      return var0.zzXj;
   }

   // $FF: synthetic method
   static Cast.CastApi zzb(zzawy var0) {
      return var0.zzasb;
   }

   // $FF: synthetic method
   static void zzc(zzawy var0) {
      var0.zzov();
   }

   // $FF: synthetic method
   static void zzd(zzawy var0) {
      var0.zzou();
   }

   // $FF: synthetic method
   static void zza(zzawy var0, String var1, int var2, JSONObject var3, zzayt var4) {
      var0.zza(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   static zzayo zzow() {
      return zzapq;
   }

   // $FF: synthetic method
   static zzaxl zza(zzawy var0, zzaxl var1) {
      return var0.zzawU = null;
   }

   // $FF: synthetic method
   static String zza(zzawy var0, String var1) {
      return var0.zzaxc = var1;
   }
}
