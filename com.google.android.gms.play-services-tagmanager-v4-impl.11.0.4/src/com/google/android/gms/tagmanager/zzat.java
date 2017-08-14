package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

final class zzat implements DataLayer.zzc {
   private static final String zzbEn = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", "datalayer", "ID", "key", "value", "expires");
   private final Executor zzbEo;
   private final Context mContext;
   private zzax zzbEp;
   private com.google.android.gms.common.util.zze zzvw;
   private int zzbEq;

   public zzat(Context var1) {
      this(var1, com.google.android.gms.common.util.zzi.zzrY(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
   }

   private zzat(Context var1, com.google.android.gms.common.util.zze var2, String var3, int var4, Executor var5) {
      this.mContext = var1;
      this.zzvw = var2;
      this.zzbEq = 2000;
      this.zzbEo = var5;
      this.zzbEp = new zzax(this, this.mContext, var3);
   }

   public final void zza(List var1, long var2) {
      ArrayList var6 = new ArrayList();
      Iterator var7 = var1.iterator();

      while(var7.hasNext()) {
         DataLayer.zza var8 = (DataLayer.zza)var7.next();
         var6.add(new zzay(var8.zzBN, zzF(var8.mValue)));
      }

      this.zzbEo.execute(new zzau(this, var6, var2));
   }

   public final void zza(zzaq var1) {
      this.zzbEo.execute(new zzav(this, var1));
   }

   public final void zzfe(String var1) {
      this.zzbEo.execute(new zzaw(this, var1));
   }

   private final List zzBa() {
      try {
         this.zzah(this.zzvw.currentTimeMillis());
         List var3 = this.zzBb();
         ArrayList var4 = new ArrayList();
         Iterator var5 = var3.iterator();

         while(var5.hasNext()) {
            zzay var6 = (zzay)var5.next();
            var4.add(new DataLayer.zza(var6.zzBN, zzt(var6.zzbEw)));
         }

         ArrayList var1 = var4;
         return var1;
      } finally {
         this.zzBd();
      }
   }

   private static Object zzt(byte[] var0) {
      ByteArrayInputStream var1 = new ByteArrayInputStream(var0);
      ObjectInputStream var2 = null;
      boolean var12 = false;

      Object var3;
      label118: {
         label119: {
            try {
               var12 = true;
               var3 = (var2 = new ObjectInputStream(var1)).readObject();
               var12 = false;
               break label118;
            } catch (IOException var17) {
               var12 = false;
               break label119;
            } catch (ClassNotFoundException var18) {
               var12 = false;
            } finally {
               if (var12) {
                  try {
                     if (var2 != null) {
                        var2.close();
                     }

                     var1.close();
                  } catch (IOException var15) {
                     ;
                  }

               }
            }

            try {
               if (var2 != null) {
                  var2.close();
               }

               var1.close();
            } catch (IOException var14) {
               ;
            }

            return null;
         }

         try {
            if (var2 != null) {
               var2.close();
            }

            var1.close();
         } catch (IOException var13) {
            ;
         }

         return null;
      }

      try {
         var2.close();
         var1.close();
      } catch (IOException var16) {
         ;
      }

      return var3;
   }

   private static byte[] zzF(Object var0) {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      ObjectOutputStream var2 = null;
      boolean var10 = false;

      byte[] var3;
      label90: {
         try {
            var10 = true;
            (var2 = new ObjectOutputStream(var1)).writeObject(var0);
            var3 = var1.toByteArray();
            var10 = false;
            break label90;
         } catch (IOException var14) {
            var10 = false;
         } finally {
            if (var10) {
               try {
                  if (var2 != null) {
                     var2.close();
                  }

                  var1.close();
               } catch (IOException var11) {
                  ;
               }

            }
         }

         try {
            if (var2 != null) {
               var2.close();
            }

            var1.close();
         } catch (IOException var12) {
            ;
         }

         return null;
      }

      try {
         var2.close();
         var1.close();
      } catch (IOException var13) {
         ;
      }

      return var3;
   }

   private final synchronized void zzb(List var1, long var2) {
      try {
         long var4 = this.zzvw.currentTimeMillis();
         this.zzah(var4);
         int var8 = var1.size();
         int var9;
         if ((var9 = this.zzBc() - this.zzbEq + var8) > 0) {
            List var10;
            int var11 = (var10 = this.zzby(var9)).size();
            zzdj.zzaS((new StringBuilder(64)).append("DataLayer store full, deleting ").append(var11).append(" entries to make room.").toString());
            String[] var13 = (String[])var10.toArray(new String[0]);
            SQLiteDatabase var14;
            if (var13 != null && var13.length != 0 && (var14 = this.zzfg("Error opening database for deleteEntries.")) != null) {
               String var15 = String.format("%s in (%s)", "ID", TextUtils.join(",", Collections.nCopies(var13.length, "?")));

               try {
                  var14.delete("datalayer", var15, var13);
               } catch (SQLiteException var18) {
                  String var10001 = String.valueOf(Arrays.toString(var13));
                  String var10000;
                  if (var10001.length() != 0) {
                     var10000 = "Error deleting entries ".concat(var10001);
                  } else {
                     String var10002 = new String;
                     var10000 = var10002;
                     var10002.<init>("Error deleting entries ");
                  }

                  zzdj.zzaT(var10000);
               }
            }
         }

         this.zzc(var1, var4 + var2);
      } finally {
         this.zzBd();
      }

   }

   private final List zzBb() {
      SQLiteDatabase var1 = this.zzfg("Error opening database for loadSerialized.");
      ArrayList var2 = new ArrayList();
      if (var1 == null) {
         return var2;
      } else {
         String[] var3 = new String[]{"key", "value"};
         Cursor var4 = var1.query("datalayer", var3, (String)null, (String[])null, (String)null, (String)null, "ID", (String)null);

         try {
            while(var4.moveToNext()) {
               var2.add(new zzay(var4.getString(0), var4.getBlob(1)));
            }
         } finally {
            var4.close();
         }

         return var2;
      }
   }

   private final void zzc(List var1, long var2) {
      SQLiteDatabase var4;
      if ((var4 = this.zzfg("Error opening database for writeEntryToDatabase.")) != null) {
         Iterator var5 = var1.iterator();

         while(var5.hasNext()) {
            zzay var6 = (zzay)var5.next();
            ContentValues var7;
            (var7 = new ContentValues()).put("expires", var2);
            var7.put("key", var6.zzBN);
            var7.put("value", var6.zzbEw);
            var4.insert("datalayer", (String)null, var7);
         }

      }
   }

   private final void zzff(String var1) {
      SQLiteDatabase var2;
      if ((var2 = this.zzfg("Error opening database for clearKeysWithPrefix.")) != null) {
         try {
            int var3 = var2.delete("datalayer", "key = ? OR key LIKE ?", new String[]{var1, String.valueOf(var1).concat(".%")});
            zzdj.v((new StringBuilder(25)).append("Cleared ").append(var3).append(" items").toString());
            return;
         } catch (SQLiteException var8) {
            String var4 = String.valueOf(var8);
            zzdj.zzaT((new StringBuilder(44 + String.valueOf(var1).length() + String.valueOf(var4).length())).append("Error deleting entries with key prefix: ").append(var1).append(" (").append(var4).append(").").toString());
         } finally {
            this.zzBd();
         }

      }
   }

   private final void zzah(long var1) {
      SQLiteDatabase var3;
      if ((var3 = this.zzfg("Error opening database for deleteOlderThan.")) != null) {
         try {
            int var4 = var3.delete("datalayer", "expires <= ?", new String[]{Long.toString(var1)});
            zzdj.v((new StringBuilder(33)).append("Deleted ").append(var4).append(" expired items").toString());
         } catch (SQLiteException var5) {
            zzdj.zzaT("Error deleting old entries.");
         }
      }
   }

   private final List zzby(int var1) {
      ArrayList var2 = new ArrayList();
      if (var1 <= 0) {
         zzdj.zzaT("Invalid maxEntries specified. Skipping.");
         return var2;
      } else {
         SQLiteDatabase var3;
         if ((var3 = this.zzfg("Error opening database for peekEntryIds.")) == null) {
            return var2;
         } else {
            Cursor var4 = null;

            try {
               if ((var4 = var3.query("datalayer", new String[]{"ID"}, (String)null, (String[])null, (String)null, (String)null, String.format("%s ASC", "ID"), Integer.toString(var1))).moveToFirst()) {
                  do {
                     var2.add(String.valueOf(var4.getLong(0)));
                  } while(var4.moveToNext());
               }
            } catch (SQLiteException var9) {
               String var10001 = String.valueOf(var9.getMessage());
               String var10000;
               if (var10001.length() != 0) {
                  var10000 = "Error in peekEntries fetching entryIds: ".concat(var10001);
               } else {
                  String var10002 = new String;
                  var10000 = var10002;
                  var10002.<init>("Error in peekEntries fetching entryIds: ");
               }

               zzdj.zzaT(var10000);
            } finally {
               if (var4 != null) {
                  var4.close();
               }

            }

            return var2;
         }
      }
   }

   private final int zzBc() {
      int var1 = 0;
      SQLiteDatabase var2;
      if ((var2 = this.zzfg("Error opening database for getNumStoredEntries.")) == null) {
         return 0;
      } else {
         Cursor var3 = null;

         try {
            if ((var3 = var2.rawQuery("SELECT COUNT(*) from datalayer", (String[])null)).moveToFirst()) {
               var1 = (int)var3.getLong(0);
            }
         } catch (SQLiteException var7) {
            zzdj.zzaT("Error getting numStoredEntries");
         } finally {
            if (var3 != null) {
               var3.close();
            }

         }

         return var1;
      }
   }

   private final SQLiteDatabase zzfg(String var1) {
      try {
         SQLiteDatabase var2 = this.zzbEp.getWritableDatabase();
         return var2;
      } catch (SQLiteException var3) {
         zzdj.zzaT(var1);
         return null;
      }
   }

   private final void zzBd() {
      try {
         this.zzbEp.close();
      } catch (SQLiteException var1) {
         ;
      }
   }

   // $FF: synthetic method
   static void zza(zzat var0, List var1, long var2) {
      var0.zzb(var1, var2);
   }

   // $FF: synthetic method
   static List zza(zzat var0) {
      return var0.zzBa();
   }

   // $FF: synthetic method
   static void zza(zzat var0, String var1) {
      var0.zzff(var1);
   }

   // $FF: synthetic method
   static Context zzb(zzat var0) {
      return var0.mContext;
   }

   // $FF: synthetic method
   static String zzBe() {
      return zzbEn;
   }
}
