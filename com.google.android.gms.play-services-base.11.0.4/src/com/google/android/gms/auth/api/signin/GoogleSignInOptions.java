package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends com.google.android.gms.common.internal.safeparcel.zza implements Api.ApiOptions.Optional, ReflectedParcelable {
   public static final Scope zzalV = new Scope("profile");
   public static final Scope zzalW = new Scope("email");
   public static final Scope zzalX = new Scope("openid");
   private static Scope SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
   public static final GoogleSignInOptions DEFAULT_SIGN_IN = (new GoogleSignInOptions.Builder()).requestId().requestProfile().build();
   public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN;
   public static final Creator CREATOR;
   private int versionCode;
   private final ArrayList zzalY;
   private Account zzajb;
   private boolean zzalh;
   private final boolean zzalZ;
   private final boolean zzama;
   private String zzali;
   private String zzamb;
   private ArrayList zzamc;
   private Map zzamd;
   private static Comparator zzalU;

   @Nullable
   public static GoogleSignInOptions zzbQ(@Nullable String var0) throws JSONException {
      if (TextUtils.isEmpty(var0)) {
         return null;
      } else {
         JSONObject var1 = new JSONObject(var0);
         HashSet var2 = new HashSet();
         JSONArray var3;
         int var4 = (var3 = var1.getJSONArray("scopes")).length();

         for(int var5 = 0; var5 < var4; ++var5) {
            var2.add(new Scope(var3.getString(var5)));
         }

         Account var7 = null;
         String var6;
         if (!TextUtils.isEmpty(var6 = var1.optString("accountName", (String)null))) {
            var7 = new Account(var6, "com.google");
         }

         return new GoogleSignInOptions(3, new ArrayList(var2), var7, var1.getBoolean("idTokenRequested"), var1.getBoolean("serverAuthRequested"), var1.getBoolean("forceCodeForRefreshToken"), var1.optString("serverClientId", (String)null), var1.optString("hostedDomain", (String)null), new HashMap());
      }
   }

   GoogleSignInOptions(int var1, ArrayList var2, Account var3, boolean var4, boolean var5, boolean var6, String var7, String var8, ArrayList var9) {
      this(var1, var2, var3, var4, var5, var6, var7, var8, zzw(var9));
   }

   private GoogleSignInOptions(int var1, ArrayList var2, Account var3, boolean var4, boolean var5, boolean var6, String var7, String var8, Map var9) {
      this.versionCode = var1;
      this.zzalY = var2;
      this.zzajb = var3;
      this.zzalh = var4;
      this.zzalZ = var5;
      this.zzama = var6;
      this.zzali = var7;
      this.zzamb = var8;
      this.zzamc = new ArrayList(var9.values());
      this.zzamd = var9;
   }

   public final ArrayList zzmA() {
      return new ArrayList(this.zzalY);
   }

   public Scope[] getScopeArray() {
      return (Scope[])this.zzalY.toArray(new Scope[this.zzalY.size()]);
   }

   public final Account getAccount() {
      return this.zzajb;
   }

   public final boolean isIdTokenRequested() {
      return this.zzalh;
   }

   public final boolean zzmB() {
      return this.zzalZ;
   }

   public final String getServerClientId() {
      return this.zzali;
   }

   private static Map zzw(@Nullable List var0) {
      HashMap var1 = new HashMap();
      if (var0 == null) {
         return var1;
      } else {
         Iterator var2 = var0.iterator();

         while(var2.hasNext()) {
            zzn var3 = (zzn)var2.next();
            var1.put(var3.getType(), var3);
         }

         return var1;
      }
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.zzmA(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzajb, var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.zzalh);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.zzalZ);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.zzama);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.zzali, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzamb, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 9, this.zzamc, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public boolean equals(Object var1) {
      if (var1 == null) {
         return false;
      } else {
         try {
            GoogleSignInOptions var2 = (GoogleSignInOptions)var1;
            if (this.zzamc.size() <= 0 && var2.zzamc.size() <= 0) {
               if (this.zzalY.size() == var2.zzmA().size() && this.zzalY.containsAll(var2.zzmA())) {
                  if (this.zzajb == null) {
                     if (var2.zzajb != null) {
                        return false;
                     }
                  } else if (!this.zzajb.equals(var2.zzajb)) {
                     return false;
                  }

                  if (TextUtils.isEmpty(this.zzali)) {
                     if (!TextUtils.isEmpty(var2.zzali)) {
                        return false;
                     }
                  } else if (!this.zzali.equals(var2.zzali)) {
                     return false;
                  }

                  if (this.zzama == var2.zzama && this.zzalh == var2.zzalh && this.zzalZ == var2.zzalZ) {
                     return true;
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } catch (ClassCastException var3) {
            return false;
         }
      }
   }

   public int hashCode() {
      ArrayList var1 = new ArrayList();
      ArrayList var3;
      int var4 = (var3 = (ArrayList)this.zzalY).size();
      int var5 = 0;

      while(var5 < var4) {
         Object var10000 = var3.get(var5);
         ++var5;
         Scope var2 = (Scope)var10000;
         var1.add(var2.zzpp());
      }

      Collections.sort(var1);
      return (new zzo()).zzo(var1).zzo(this.zzajb).zzo(this.zzali).zzP(this.zzama).zzP(this.zzalh).zzP(this.zzalZ).zzmJ();
   }

   public final String zzmC() {
      return this.zzmz().toString();
   }

   private final JSONObject zzmz() {
      JSONObject var1 = new JSONObject();

      try {
         JSONArray var2 = new JSONArray();
         Collections.sort(this.zzalY, zzalU);
         ArrayList var4;
         int var5 = (var4 = (ArrayList)this.zzalY).size();
         int var6 = 0;

         while(var6 < var5) {
            Object var10000 = var4.get(var6);
            ++var6;
            Scope var3 = (Scope)var10000;
            var2.put(var3.zzpp());
         }

         var1.put("scopes", var2);
         if (this.zzajb != null) {
            var1.put("accountName", this.zzajb.name);
         }

         var1.put("idTokenRequested", this.zzalh);
         var1.put("forceCodeForRefreshToken", this.zzama);
         var1.put("serverAuthRequested", this.zzalZ);
         if (!TextUtils.isEmpty(this.zzali)) {
            var1.put("serverClientId", this.zzali);
         }

         if (!TextUtils.isEmpty(this.zzamb)) {
            var1.put("hostedDomain", this.zzamb);
         }

         return var1;
      } catch (JSONException var7) {
         throw new RuntimeException(var7);
      }
   }

   // $FF: synthetic method
   GoogleSignInOptions(int var1, ArrayList var2, Account var3, boolean var4, boolean var5, boolean var6, String var7, String var8, Map var9, zzc var10) {
      this(3, var2, var3, var4, var5, var6, var7, var8, (Map)var9);
   }

   static {
      DEFAULT_GAMES_SIGN_IN = (new GoogleSignInOptions.Builder()).requestScopes(SCOPE_GAMES).build();
      CREATOR = new zzd();
      zzalU = new zzc();
   }

   public static final class Builder {
      private Set zzame = new HashSet();
      private boolean zzalZ;
      private boolean zzama;
      private boolean zzalh;
      private String zzali;
      private Account zzajb;
      private String zzamb;
      private Map zzamf = new HashMap();

      public Builder() {
      }

      public Builder(@NonNull GoogleSignInOptions var1) {
         zzbo.zzu(var1);
         this.zzame = new HashSet(var1.zzalY);
         this.zzalZ = var1.zzalZ;
         this.zzama = var1.zzama;
         this.zzalh = var1.zzalh;
         this.zzali = var1.zzali;
         this.zzajb = var1.zzajb;
         this.zzamb = var1.zzamb;
         this.zzamf = GoogleSignInOptions.zzw(var1.zzamc);
      }

      public final GoogleSignInOptions.Builder requestId() {
         this.zzame.add(GoogleSignInOptions.zzalX);
         return this;
      }

      public final GoogleSignInOptions.Builder requestEmail() {
         this.zzame.add(GoogleSignInOptions.zzalW);
         return this;
      }

      public final GoogleSignInOptions.Builder requestProfile() {
         this.zzame.add(GoogleSignInOptions.zzalV);
         return this;
      }

      public final GoogleSignInOptions.Builder requestScopes(Scope var1, Scope... var2) {
         this.zzame.add(var1);
         this.zzame.addAll(Arrays.asList(var2));
         return this;
      }

      public final GoogleSignInOptions.Builder requestIdToken(String var1) {
         this.zzalh = true;
         this.zzali = this.zzbR(var1);
         return this;
      }

      public final GoogleSignInOptions.Builder requestServerAuthCode(String var1) {
         return this.requestServerAuthCode(var1, false);
      }

      public final GoogleSignInOptions.Builder requestServerAuthCode(String var1, boolean var2) {
         this.zzalZ = true;
         this.zzali = this.zzbR(var1);
         this.zzama = var2;
         return this;
      }

      public final GoogleSignInOptions.Builder setAccountName(String var1) {
         this.zzajb = new Account(zzbo.zzcF(var1), "com.google");
         return this;
      }

      public final GoogleSignInOptions.Builder setHostedDomain(String var1) {
         this.zzamb = zzbo.zzcF(var1);
         return this;
      }

      public final GoogleSignInOptions.Builder addExtension(GoogleSignInOptionsExtension var1) {
         if (this.zzamf.containsKey(Integer.valueOf(1))) {
            throw new IllegalStateException("Only one extension per type may be added");
         } else {
            this.zzamf.put(Integer.valueOf(1), new zzn(var1));
            return this;
         }
      }

      public final GoogleSignInOptions build() {
         if (this.zzalh && (this.zzajb == null || !this.zzame.isEmpty())) {
            this.requestId();
         }

         return new GoogleSignInOptions(3, new ArrayList(this.zzame), this.zzajb, this.zzalh, this.zzalZ, this.zzama, this.zzali, this.zzamb, this.zzamf, (zzc)null);
      }

      private final String zzbR(String var1) {
         zzbo.zzcF(var1);
         zzbo.zzb(this.zzali == null || this.zzali.equals(var1), "two different server client ids provided");
         return var1;
      }
   }
}
