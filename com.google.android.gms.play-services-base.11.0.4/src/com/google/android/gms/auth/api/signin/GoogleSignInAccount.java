package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final Creator CREATOR = new zzb();
   private static zze zzalN = zzi.zzrY();
   private int versionCode;
   private String zzIi;
   private String zzaln;
   private String zzalO;
   private String zzalP;
   private Uri zzalQ;
   private String zzalR;
   private long zzalS;
   private String zzalT;
   private List zzakz;
   private String zzakX;
   private String zzakY;
   private static Comparator zzalU = new zza();

   @Nullable
   public static GoogleSignInAccount zzbP(@Nullable String var0) throws JSONException {
      if (TextUtils.isEmpty(var0)) {
         return null;
      } else {
         JSONObject var1 = new JSONObject(var0);
         Uri var2 = null;
         String var3;
         if (!TextUtils.isEmpty(var3 = var1.optString("photoUrl", (String)null))) {
            var2 = Uri.parse(var3);
         }

         long var4 = Long.parseLong(var1.getString("expirationTime"));
         HashSet var6 = new HashSet();
         JSONArray var7;
         int var8 = (var7 = var1.getJSONArray("grantedScopes")).length();

         for(int var9 = 0; var9 < var8; ++var9) {
            var6.add(new Scope(var7.getString(var9)));
         }

         String var10000 = var1.optString("id");
         String var10001 = var1.optString("tokenId", (String)null);
         String var10002 = var1.optString("email", (String)null);
         String var10003 = var1.optString("displayName", (String)null);
         String var10004 = var1.optString("givenName", (String)null);
         String var10005 = var1.optString("familyName", (String)null);
         Long var10007 = var4;
         String var18 = var1.getString("obfuscatedIdentifier");
         Long var17 = var10007;
         String var15 = var10005;
         String var14 = var10004;
         String var13 = var10003;
         String var12 = var10002;
         String var11 = var10001;
         String var10 = var10000;
         if (var17 == null) {
            var17 = zzalN.currentTimeMillis() / 1000L;
         }

         GoogleSignInAccount var21 = new GoogleSignInAccount(3, var10, var11, var12, var13, var2, (String)null, var17.longValue(), zzbo.zzcF(var18), new ArrayList((Collection)zzbo.zzu(var6)), var14, var15);
         var11 = var1.optString("serverAuthCode", (String)null);
         GoogleSignInAccount var20 = var21;
         var21.zzalR = var11;
         return var20;
      }
   }

   GoogleSignInAccount(int var1, String var2, String var3, String var4, String var5, Uri var6, String var7, long var8, String var10, List var11, String var12, String var13) {
      this.versionCode = var1;
      this.zzIi = var2;
      this.zzaln = var3;
      this.zzalO = var4;
      this.zzalP = var5;
      this.zzalQ = var6;
      this.zzalR = var7;
      this.zzalS = var8;
      this.zzalT = var10;
      this.zzakz = var11;
      this.zzakX = var12;
      this.zzakY = var13;
   }

   @Nullable
   public String getId() {
      return this.zzIi;
   }

   @Nullable
   public String getIdToken() {
      return this.zzaln;
   }

   @Nullable
   public String getEmail() {
      return this.zzalO;
   }

   @Nullable
   public Account getAccount() {
      return this.zzalO == null ? null : new Account(this.zzalO, "com.google");
   }

   @Nullable
   public String getDisplayName() {
      return this.zzalP;
   }

   @Nullable
   public String getGivenName() {
      return this.zzakX;
   }

   @Nullable
   public String getFamilyName() {
      return this.zzakY;
   }

   @Nullable
   public Uri getPhotoUrl() {
      return this.zzalQ;
   }

   @Nullable
   public String getServerAuthCode() {
      return this.zzalR;
   }

   public final boolean zzmw() {
      return zzalN.currentTimeMillis() / 1000L >= this.zzalS - 300L;
   }

   @NonNull
   public final String zzmx() {
      return this.zzalT;
   }

   @NonNull
   public Set getGrantedScopes() {
      return new HashSet(this.zzakz);
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 1, this.versionCode);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.getIdToken(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getEmail(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getDisplayName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getPhotoUrl(), var2, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getServerAuthCode(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 8, this.zzalS);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzalT, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 10, this.zzakz, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 11, this.getGivenName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 12, this.getFamilyName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public int hashCode() {
      return this.zzmz().toString().hashCode();
   }

   public boolean equals(Object var1) {
      return !(var1 instanceof GoogleSignInAccount) ? false : ((GoogleSignInAccount)var1).zzmz().toString().equals(this.zzmz().toString());
   }

   public final String zzmy() {
      JSONObject var1;
      (var1 = this.zzmz()).remove("serverAuthCode");
      return var1.toString();
   }

   private final JSONObject zzmz() {
      JSONObject var1 = new JSONObject();

      try {
         if (this.getId() != null) {
            var1.put("id", this.getId());
         }

         if (this.getIdToken() != null) {
            var1.put("tokenId", this.getIdToken());
         }

         if (this.getEmail() != null) {
            var1.put("email", this.getEmail());
         }

         if (this.getDisplayName() != null) {
            var1.put("displayName", this.getDisplayName());
         }

         if (this.getGivenName() != null) {
            var1.put("givenName", this.getGivenName());
         }

         if (this.getFamilyName() != null) {
            var1.put("familyName", this.getFamilyName());
         }

         if (this.getPhotoUrl() != null) {
            var1.put("photoUrl", this.getPhotoUrl().toString());
         }

         if (this.getServerAuthCode() != null) {
            var1.put("serverAuthCode", this.getServerAuthCode());
         }

         var1.put("expirationTime", this.zzalS);
         var1.put("obfuscatedIdentifier", this.zzalT);
         JSONArray var2 = new JSONArray();
         Collections.sort(this.zzakz, zzalU);
         Iterator var3 = this.zzakz.iterator();

         while(var3.hasNext()) {
            Scope var4 = (Scope)var3.next();
            var2.put(var4.zzpp());
         }

         var1.put("grantedScopes", var2);
         return var1;
      } catch (JSONException var5) {
         throw new RuntimeException(var5);
      }
   }
}
