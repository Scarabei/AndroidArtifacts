package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzaye;
import java.util.Arrays;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public final class MediaTrack extends com.google.android.gms.common.internal.safeparcel.zza implements ReflectedParcelable {
   public static final int TYPE_UNKNOWN = 0;
   public static final int TYPE_TEXT = 1;
   public static final int TYPE_AUDIO = 2;
   public static final int TYPE_VIDEO = 3;
   public static final int SUBTYPE_UNKNOWN = -1;
   public static final int SUBTYPE_NONE = 0;
   public static final int SUBTYPE_SUBTITLES = 1;
   public static final int SUBTYPE_CAPTIONS = 2;
   public static final int SUBTYPE_DESCRIPTIONS = 3;
   public static final int SUBTYPE_CHAPTERS = 4;
   public static final int SUBTYPE_METADATA = 5;
   public static final Creator CREATOR = new zzah();
   private long zzakg;
   private int zzamr;
   private String zzapX;
   private String zzapZ;
   private String mName;
   private String zzaeT;
   private int zzaqN;
   private String zzaoC;
   private JSONObject zzaoD;

   MediaTrack(long var1, int var3, String var4, String var5, String var6, String var7, int var8, String var9) {
      this.zzakg = var1;
      this.zzamr = var3;
      this.zzapX = var4;
      this.zzapZ = var5;
      this.mName = var6;
      this.zzaeT = var7;
      this.zzaqN = var8;
      this.zzaoC = var9;
      if (this.zzaoC != null) {
         try {
            this.zzaoD = new JSONObject(this.zzaoC);
         } catch (JSONException var10) {
            this.zzaoD = null;
            this.zzaoC = null;
         }
      } else {
         this.zzaoD = null;
      }
   }

   MediaTrack(JSONObject var1) throws JSONException {
      this(0L, 0, (String)null, (String)null, (String)null, (String)null, -1, (String)null);
      this.zzakg = var1.getLong("trackId");
      String var4 = var1.getString("type");
      JSONException var10000;
      String var10002;
      String var10003;
      String var10004;
      if ("TEXT".equals(var4)) {
         this.zzamr = 1;
      } else if ("AUDIO".equals(var4)) {
         this.zzamr = 2;
      } else {
         if (!"VIDEO".equals(var4)) {
            var10000 = new JSONException;
            var10003 = String.valueOf(var4);
            if (var10003.length() != 0) {
               var10002 = "invalid type: ".concat(var10003);
            } else {
               var10004 = new String;
               var10002 = var10004;
               var10004.<init>("invalid type: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }

         this.zzamr = 3;
      }

      this.zzapX = var1.optString("trackContentId", (String)null);
      this.zzapZ = var1.optString("trackContentType", (String)null);
      this.mName = var1.optString("name", (String)null);
      this.zzaeT = var1.optString("language", (String)null);
      if (var1.has("subtype")) {
         var4 = var1.getString("subtype");
         if ("SUBTITLES".equals(var4)) {
            this.zzaqN = 1;
         } else if ("CAPTIONS".equals(var4)) {
            this.zzaqN = 2;
         } else if ("DESCRIPTIONS".equals(var4)) {
            this.zzaqN = 3;
         } else if ("CHAPTERS".equals(var4)) {
            this.zzaqN = 4;
         } else {
            if (!"METADATA".equals(var4)) {
               var10000 = new JSONException;
               var10003 = String.valueOf(var4);
               if (var10003.length() != 0) {
                  var10002 = "invalid subtype: ".concat(var10003);
               } else {
                  var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("invalid subtype: ");
               }

               var10000.<init>(var10002);
               throw var10000;
            }

            this.zzaqN = 5;
         }
      } else {
         this.zzaqN = 0;
      }

      this.zzaoD = var1.optJSONObject("customData");
   }

   MediaTrack(long var1, int var3) throws IllegalArgumentException {
      this(0L, 0, (String)null, (String)null, (String)null, (String)null, -1, (String)null);
      this.zzakg = var1;
      if (var3 > 0 && var3 <= 3) {
         this.zzamr = var3;
      } else {
         throw new IllegalArgumentException((new StringBuilder(24)).append("invalid type ").append(var3).toString());
      }
   }

   public final long getId() {
      return this.zzakg;
   }

   public final int getType() {
      return this.zzamr;
   }

   public final String getContentId() {
      return this.zzapX;
   }

   public final void setContentId(String var1) {
      this.zzapX = var1;
   }

   public final String getContentType() {
      return this.zzapZ;
   }

   public final void setContentType(String var1) {
      this.zzapZ = var1;
   }

   public final String getName() {
      return this.mName;
   }

   final void setName(String var1) {
      this.mName = var1;
   }

   public final String getLanguage() {
      return this.zzaeT;
   }

   final void setLanguage(String var1) {
      this.zzaeT = var1;
   }

   public final int getSubtype() {
      return this.zzaqN;
   }

   final void zzW(int var1) throws IllegalArgumentException {
      if (var1 >= 0 && var1 <= 5) {
         if (var1 != 0 && this.zzamr != 1) {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
         } else {
            this.zzaqN = var1;
         }
      } else {
         throw new IllegalArgumentException((new StringBuilder(27)).append("invalid subtype ").append(var1).toString());
      }
   }

   public final JSONObject getCustomData() {
      return this.zzaoD;
   }

   final void setCustomData(JSONObject var1) {
      this.zzaoD = var1;
   }

   public final JSONObject toJson() {
      JSONObject var1 = new JSONObject();

      try {
         var1.put("trackId", this.zzakg);
         switch(this.zzamr) {
         case 1:
            var1.put("type", "TEXT");
            break;
         case 2:
            var1.put("type", "AUDIO");
            break;
         case 3:
            var1.put("type", "VIDEO");
         }

         if (this.zzapX != null) {
            var1.put("trackContentId", this.zzapX);
         }

         if (this.zzapZ != null) {
            var1.put("trackContentType", this.zzapZ);
         }

         if (this.mName != null) {
            var1.put("name", this.mName);
         }

         if (!TextUtils.isEmpty(this.zzaeT)) {
            var1.put("language", this.zzaeT);
         }

         switch(this.zzaqN) {
         case 1:
            var1.put("subtype", "SUBTITLES");
            break;
         case 2:
            var1.put("subtype", "CAPTIONS");
            break;
         case 3:
            var1.put("subtype", "DESCRIPTIONS");
            break;
         case 4:
            var1.put("subtype", "CHAPTERS");
            break;
         case 5:
            var1.put("subtype", "METADATA");
         }

         if (this.zzaoD != null) {
            var1.put("customData", this.zzaoD);
         }
      } catch (JSONException var2) {
         ;
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof MediaTrack)) {
         return false;
      } else {
         MediaTrack var2 = (MediaTrack)var1;
         if (this.zzaoD == null != (var2.zzaoD == null)) {
            return false;
         } else if (this.zzaoD != null && var2.zzaoD != null && !com.google.android.gms.common.util.zzo.zzc(this.zzaoD, var2.zzaoD)) {
            return false;
         } else {
            return this.zzakg == var2.zzakg && this.zzamr == var2.zzamr && zzaye.zza(this.zzapX, var2.zzapX) && zzaye.zza(this.zzapZ, var2.zzapZ) && zzaye.zza(this.mName, var2.mName) && zzaye.zza(this.zzaeT, var2.zzaeT) && this.zzaqN == var2.zzaqN;
         }
      }
   }

   public final int hashCode() {
      return Arrays.hashCode(new Object[]{this.zzakg, this.zzamr, this.zzapX, this.zzapZ, this.mName, this.zzaeT, this.zzaqN, String.valueOf(this.zzaoD)});
   }

   public final void writeToParcel(Parcel var1, int var2) {
      this.zzaoC = this.zzaoD == null ? null : this.zzaoD.toString();
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 2, this.getId());
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 3, this.getType());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 4, this.getContentId(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 5, this.getContentType(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 6, this.getName(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 7, this.getLanguage(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 8, this.getSubtype());
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 9, this.zzaoC, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public static class Builder {
      private final MediaTrack zzaqO;

      public Builder(long var1, int var3) throws IllegalArgumentException {
         this.zzaqO = new MediaTrack(var1, var3);
      }

      public MediaTrack.Builder setContentId(String var1) {
         this.zzaqO.setContentId(var1);
         return this;
      }

      public MediaTrack.Builder setContentType(String var1) {
         this.zzaqO.setContentType(var1);
         return this;
      }

      public MediaTrack.Builder setName(String var1) {
         this.zzaqO.setName(var1);
         return this;
      }

      public MediaTrack.Builder setLanguage(String var1) {
         this.zzaqO.setLanguage(var1);
         return this;
      }

      public MediaTrack.Builder setLanguage(Locale var1) {
         this.zzaqO.setLanguage(zzaye.zzb(var1));
         return this;
      }

      public MediaTrack.Builder setSubtype(int var1) throws IllegalArgumentException {
         this.zzaqO.zzW(var1);
         return this;
      }

      public MediaTrack.Builder setCustomData(JSONObject var1) {
         this.zzaqO.setCustomData(var1);
         return this;
      }

      public MediaTrack build() {
         return this.zzaqO;
      }
   }
}
