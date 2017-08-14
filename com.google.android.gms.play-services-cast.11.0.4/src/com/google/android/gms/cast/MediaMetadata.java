package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.internal.zzayv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class MediaMetadata extends com.google.android.gms.common.internal.safeparcel.zza {
   public static final int MEDIA_TYPE_GENERIC = 0;
   public static final int MEDIA_TYPE_MOVIE = 1;
   public static final int MEDIA_TYPE_TV_SHOW = 2;
   public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
   public static final int MEDIA_TYPE_PHOTO = 4;
   public static final int MEDIA_TYPE_USER = 100;
   private static final String[] zzaqh = new String[]{null, "String", "int", "double", "ISO-8601 date String"};
   private static final MediaMetadata.zza zzaqi = (new MediaMetadata.zza()).zzb("com.google.android.gms.cast.metadata.CREATION_DATE", "creationDateTime", 4).zzb("com.google.android.gms.cast.metadata.RELEASE_DATE", "releaseDate", 4).zzb("com.google.android.gms.cast.metadata.BROADCAST_DATE", "originalAirdate", 4).zzb("com.google.android.gms.cast.metadata.TITLE", "title", 1).zzb("com.google.android.gms.cast.metadata.SUBTITLE", "subtitle", 1).zzb("com.google.android.gms.cast.metadata.ARTIST", "artist", 1).zzb("com.google.android.gms.cast.metadata.ALBUM_ARTIST", "albumArtist", 1).zzb("com.google.android.gms.cast.metadata.ALBUM_TITLE", "albumName", 1).zzb("com.google.android.gms.cast.metadata.COMPOSER", "composer", 1).zzb("com.google.android.gms.cast.metadata.DISC_NUMBER", "discNumber", 2).zzb("com.google.android.gms.cast.metadata.TRACK_NUMBER", "trackNumber", 2).zzb("com.google.android.gms.cast.metadata.SEASON_NUMBER", "season", 2).zzb("com.google.android.gms.cast.metadata.EPISODE_NUMBER", "episode", 2).zzb("com.google.android.gms.cast.metadata.SERIES_TITLE", "seriesTitle", 1).zzb("com.google.android.gms.cast.metadata.STUDIO", "studio", 1).zzb("com.google.android.gms.cast.metadata.WIDTH", "width", 2).zzb("com.google.android.gms.cast.metadata.HEIGHT", "height", 2).zzb("com.google.android.gms.cast.metadata.LOCATION_NAME", "location", 1).zzb("com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "latitude", 3).zzb("com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "longitude", 3);
   public static final Creator CREATOR = new zzad();
   private final List zzHC;
   private Bundle zzaqj;
   private int zzaqk;
   public static final String KEY_CREATION_DATE = "com.google.android.gms.cast.metadata.CREATION_DATE";
   public static final String KEY_RELEASE_DATE = "com.google.android.gms.cast.metadata.RELEASE_DATE";
   public static final String KEY_BROADCAST_DATE = "com.google.android.gms.cast.metadata.BROADCAST_DATE";
   public static final String KEY_TITLE = "com.google.android.gms.cast.metadata.TITLE";
   public static final String KEY_SUBTITLE = "com.google.android.gms.cast.metadata.SUBTITLE";
   public static final String KEY_ARTIST = "com.google.android.gms.cast.metadata.ARTIST";
   public static final String KEY_ALBUM_ARTIST = "com.google.android.gms.cast.metadata.ALBUM_ARTIST";
   public static final String KEY_ALBUM_TITLE = "com.google.android.gms.cast.metadata.ALBUM_TITLE";
   public static final String KEY_COMPOSER = "com.google.android.gms.cast.metadata.COMPOSER";
   public static final String KEY_DISC_NUMBER = "com.google.android.gms.cast.metadata.DISC_NUMBER";
   public static final String KEY_TRACK_NUMBER = "com.google.android.gms.cast.metadata.TRACK_NUMBER";
   public static final String KEY_SEASON_NUMBER = "com.google.android.gms.cast.metadata.SEASON_NUMBER";
   public static final String KEY_EPISODE_NUMBER = "com.google.android.gms.cast.metadata.EPISODE_NUMBER";
   public static final String KEY_SERIES_TITLE = "com.google.android.gms.cast.metadata.SERIES_TITLE";
   public static final String KEY_STUDIO = "com.google.android.gms.cast.metadata.STUDIO";
   public static final String KEY_WIDTH = "com.google.android.gms.cast.metadata.WIDTH";
   public static final String KEY_HEIGHT = "com.google.android.gms.cast.metadata.HEIGHT";
   public static final String KEY_LOCATION_NAME = "com.google.android.gms.cast.metadata.LOCATION_NAME";
   public static final String KEY_LOCATION_LATITUDE = "com.google.android.gms.cast.metadata.LOCATION_LATITUDE";
   public static final String KEY_LOCATION_LONGITUDE = "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE";

   MediaMetadata(List var1, Bundle var2, int var3) {
      this.zzHC = var1;
      this.zzaqj = var2;
      this.zzaqk = var3;
   }

   public MediaMetadata() {
      this(0);
   }

   public MediaMetadata(int var1) {
      this(new ArrayList(), new Bundle(), var1);
   }

   public int getMediaType() {
      return this.zzaqk;
   }

   public void clear() {
      this.zzaqj.clear();
      this.zzHC.clear();
   }

   public boolean containsKey(String var1) {
      return this.zzaqj.containsKey(var1);
   }

   public Set keySet() {
      return this.zzaqj.keySet();
   }

   public void putString(String var1, String var2) {
      zzf(var1, 1);
      this.zzaqj.putString(var1, var2);
   }

   public String getString(String var1) {
      zzf(var1, 1);
      return this.zzaqj.getString(var1);
   }

   public void putInt(String var1, int var2) {
      zzf(var1, 2);
      this.zzaqj.putInt(var1, var2);
   }

   public int getInt(String var1) {
      zzf(var1, 2);
      return this.zzaqj.getInt(var1);
   }

   public void putDouble(String var1, double var2) {
      zzf(var1, 3);
      this.zzaqj.putDouble(var1, var2);
   }

   public double getDouble(String var1) {
      zzf(var1, 3);
      return this.zzaqj.getDouble(var1);
   }

   public void putDate(String var1, Calendar var2) {
      zzf(var1, 4);
      this.zzaqj.putString(var1, zzayv.zza(var2));
   }

   public Calendar getDate(String var1) {
      zzf(var1, 4);
      String var2;
      return (var2 = this.zzaqj.getString(var1)) != null ? zzayv.zzco(var2) : null;
   }

   public String getDateAsString(String var1) {
      zzf(var1, 4);
      return this.zzaqj.getString(var1);
   }

   private static void zzf(String var0, int var1) throws IllegalArgumentException {
      if (TextUtils.isEmpty(var0)) {
         throw new IllegalArgumentException("null and empty keys are not allowed");
      } else {
         int var2;
         if ((var2 = zzaqi.zzca(var0)) != var1 && var2 != 0) {
            String var3 = String.valueOf(zzaqh[var1]);
            throw new IllegalArgumentException((new StringBuilder(21 + String.valueOf(var0).length() + String.valueOf(var3).length())).append("Value for ").append(var0).append(" must be a ").append(var3).toString());
         }
      }
   }

   public final JSONObject toJson() {
      JSONObject var1 = new JSONObject();

      try {
         var1.put("metadataType", this.zzaqk);
      } catch (JSONException var2) {
         ;
      }

      zzayv.zza(var1, this.zzHC);
      switch(this.zzaqk) {
      case 0:
         this.zza(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
         break;
      case 1:
         this.zza(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
         break;
      case 2:
         this.zza(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE");
         break;
      case 3:
         this.zza(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE");
         break;
      case 4:
         this.zza(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE");
         break;
      default:
         this.zza(var1);
      }

      return var1;
   }

   public final void zzl(JSONObject var1) {
      this.clear();
      this.zzaqk = 0;

      try {
         this.zzaqk = var1.getInt("metadataType");
      } catch (JSONException var2) {
         ;
      }

      zzayv.zza(this.zzHC, var1);
      switch(this.zzaqk) {
      case 0:
         this.zzb(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
         return;
      case 1:
         this.zzb(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.STUDIO", "com.google.android.gms.cast.metadata.SUBTITLE", "com.google.android.gms.cast.metadata.RELEASE_DATE");
         return;
      case 2:
         this.zzb(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.SERIES_TITLE", "com.google.android.gms.cast.metadata.SEASON_NUMBER", "com.google.android.gms.cast.metadata.EPISODE_NUMBER", "com.google.android.gms.cast.metadata.BROADCAST_DATE");
         return;
      case 3:
         this.zzb(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ALBUM_TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.ALBUM_ARTIST", "com.google.android.gms.cast.metadata.COMPOSER", "com.google.android.gms.cast.metadata.TRACK_NUMBER", "com.google.android.gms.cast.metadata.DISC_NUMBER", "com.google.android.gms.cast.metadata.RELEASE_DATE");
         return;
      case 4:
         this.zzb(var1, "com.google.android.gms.cast.metadata.TITLE", "com.google.android.gms.cast.metadata.ARTIST", "com.google.android.gms.cast.metadata.LOCATION_NAME", "com.google.android.gms.cast.metadata.LOCATION_LATITUDE", "com.google.android.gms.cast.metadata.LOCATION_LONGITUDE", "com.google.android.gms.cast.metadata.WIDTH", "com.google.android.gms.cast.metadata.HEIGHT", "com.google.android.gms.cast.metadata.CREATION_DATE");
         return;
      default:
         this.zzb(var1);
      }
   }

   private final void zza(JSONObject var1, String... var2) {
      try {
         String[] var3 = var2;
         int var4 = var2.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            String var6 = var3[var5];
            if (this.zzaqj.containsKey(var6)) {
               switch(zzaqi.zzca(var6)) {
               case 1:
               case 4:
                  var1.put(zzaqi.zzbY(var6), this.zzaqj.getString(var6));
                  break;
               case 2:
                  var1.put(zzaqi.zzbY(var6), this.zzaqj.getInt(var6));
                  break;
               case 3:
                  var1.put(zzaqi.zzbY(var6), this.zzaqj.getDouble(var6));
               }
            }
         }

         Iterator var8 = this.zzaqj.keySet().iterator();

         while(var8.hasNext()) {
            String var9;
            if (!(var9 = (String)var8.next()).startsWith("com.google.")) {
               Object var10;
               if ((var10 = this.zzaqj.get(var9)) instanceof String) {
                  var1.put(var9, var10);
               } else if (var10 instanceof Integer) {
                  var1.put(var9, var10);
               } else if (var10 instanceof Double) {
                  var1.put(var9, var10);
               }
            }
         }

      } catch (JSONException var7) {
         ;
      }
   }

   private final void zzb(JSONObject var1, String... var2) {
      HashSet var3 = new HashSet(Arrays.asList(var2));

      try {
         Iterator var4 = var1.keys();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            if (!"metadataType".equals(var5)) {
               String var6;
               Object var7;
               if ((var6 = zzaqi.zzbZ(var5)) != null) {
                  if (var3.contains(var6)) {
                     try {
                        if ((var7 = var1.get(var5)) != null) {
                           switch(zzaqi.zzca(var6)) {
                           case 1:
                              if (var7 instanceof String) {
                                 this.zzaqj.putString(var6, (String)var7);
                              }
                              break;
                           case 2:
                              if (var7 instanceof Integer) {
                                 this.zzaqj.putInt(var6, ((Integer)var7).intValue());
                              }
                              break;
                           case 3:
                              if (var7 instanceof Double) {
                                 this.zzaqj.putDouble(var6, ((Double)var7).doubleValue());
                              }
                              break;
                           case 4:
                              if (var7 instanceof String && zzayv.zzco((String)var7) != null) {
                                 this.zzaqj.putString(var6, (String)var7);
                              }
                           }
                        }
                     } catch (JSONException var8) {
                        ;
                     }
                  }
               } else if ((var7 = var1.get(var5)) instanceof String) {
                  this.zzaqj.putString(var5, (String)var7);
               } else if (var7 instanceof Integer) {
                  this.zzaqj.putInt(var5, ((Integer)var7).intValue());
               } else if (var7 instanceof Double) {
                  this.zzaqj.putDouble(var5, ((Double)var7).doubleValue());
               }
            }
         }

      } catch (JSONException var9) {
         ;
      }
   }

   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof MediaMetadata)) {
         return false;
      } else {
         MediaMetadata var2 = (MediaMetadata)var1;
         return this.zzb(this.zzaqj, var2.zzaqj) && this.zzHC.equals(var2.zzHC);
      }
   }

   public int hashCode() {
      int var1 = 17;

      String var3;
      for(Iterator var2 = this.zzaqj.keySet().iterator(); var2.hasNext(); var1 = var1 * 31 + this.zzaqj.get(var3).hashCode()) {
         var3 = (String)var2.next();
      }

      return var1 * 31 + this.zzHC.hashCode();
   }

   public void writeToParcel(Parcel var1, int var2) {
      int var5 = com.google.android.gms.common.internal.safeparcel.zzd.zze(var1);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 2, this.getImages(), false);
      com.google.android.gms.common.internal.safeparcel.zzd.zza(var1, 3, this.zzaqj, false);
      com.google.android.gms.common.internal.safeparcel.zzd.zzc(var1, 4, this.getMediaType());
      com.google.android.gms.common.internal.safeparcel.zzd.zzI(var1, var5);
   }

   public List getImages() {
      return this.zzHC;
   }

   public boolean hasImages() {
      return this.zzHC != null && !this.zzHC.isEmpty();
   }

   public void clearImages() {
      this.zzHC.clear();
   }

   public void addImage(WebImage var1) {
      this.zzHC.add(var1);
   }

   private final boolean zzb(Bundle var1, Bundle var2) {
      if (var1.size() != var2.size()) {
         return false;
      } else {
         Iterator var5 = var1.keySet().iterator();

         Object var4;
         String var6;
         label38:
         do {
            Object var3;
            do {
               if (!var5.hasNext()) {
                  return true;
               }

               var6 = (String)var5.next();
               var3 = var1.get(var6);
               var4 = var2.get(var6);
               if (var3 instanceof Bundle && var4 instanceof Bundle && !this.zzb((Bundle)var3, (Bundle)var4)) {
                  return false;
               }

               if (var3 == null) {
                  continue label38;
               }
            } while(var3.equals(var4));

            return false;
         } while(var4 == null && var2.containsKey(var6));

         return false;
      }
   }

   static class zza {
      private final Map zzaql = new HashMap();
      private final Map zzaqm = new HashMap();
      private final Map zzaqn = new HashMap();

      public final MediaMetadata.zza zzb(String var1, String var2, int var3) {
         this.zzaql.put(var1, var2);
         this.zzaqm.put(var2, var1);
         this.zzaqn.put(var1, var3);
         return this;
      }

      public final String zzbY(String var1) {
         return (String)this.zzaql.get(var1);
      }

      public final String zzbZ(String var1) {
         return (String)this.zzaqm.get(var1);
      }

      public final int zzca(String var1) {
         Integer var2;
         return (var2 = (Integer)this.zzaqn.get(var1)) != null ? var2.intValue() : 0;
      }
   }
}
