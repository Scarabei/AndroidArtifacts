package com.google.android.gms.internal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Build.VERSION;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

final class zzamt extends SQLiteOpenHelper {
   // $FF: synthetic field
   private zzams zzagu;

   zzamt(zzams var1, Context var2, String var3) {
      this.zzagu = var1;
      super(var2, var3, (CursorFactory)null, 1);
   }

   public final SQLiteDatabase getWritableDatabase() {
      if (!zzams.zza(this.zzagu).zzu(3600000L)) {
         throw new SQLiteException("Database open failed");
      } else {
         try {
            return super.getWritableDatabase();
         } catch (SQLiteException var4) {
            zzams.zza(this.zzagu).start();
            this.zzagu.zzbs("Opening the database failed, dropping the table and recreating it");
            String var2 = zzams.zzb(this.zzagu);
            this.zzagu.getContext().getDatabasePath(var2).delete();

            try {
               SQLiteDatabase var1 = super.getWritableDatabase();
               zzams.zza(this.zzagu).clear();
               return var1;
            } catch (SQLiteException var3) {
               this.zzagu.zze("Failed to open freshly created database", var3);
               throw var3;
            }
         }
      }
   }

   private final boolean zza(SQLiteDatabase var1, String var2) {
      Cursor var3 = null;

      try {
         boolean var4 = (var3 = var1.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{var2}, (String)null, (String)null, (String)null)).moveToFirst();
         return var4;
      } catch (SQLiteException var8) {
         this.zzagu.zzc("Error querying for table", var2, var8);
      } finally {
         if (var3 != null) {
            var3.close();
         }

      }

      return false;
   }

   private static Set zzb(SQLiteDatabase var0, String var1) {
      HashSet var2 = new HashSet();
      String var3 = (new StringBuilder(22 + String.valueOf(var1).length())).append("SELECT * FROM ").append(var1).append(" LIMIT 0").toString();
      Cursor var4 = var0.rawQuery(var3, (String[])null);

      try {
         String[] var5 = var4.getColumnNames();

         for(int var6 = 0; var6 < var5.length; ++var6) {
            var2.add(var5[var6]);
         }
      } finally {
         var4.close();
      }

      return var2;
   }

   private static void zza(SQLiteDatabase var0) {
      Set var1 = zzb(var0, "properties");
      String[] var2 = new String[]{"app_uid", "cid", "tid", "params", "adid", "hits_count"};

      for(int var3 = 0; var3 < 6; ++var3) {
         String var4 = var2[var3];
         if (!var1.remove(var4)) {
            SQLiteException var10000 = new SQLiteException;
            String var10003 = String.valueOf(var4);
            String var10002;
            if (var10003.length() != 0) {
               var10002 = "Database properties is missing required column: ".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("Database properties is missing required column: ");
            }

            var10000.<init>(var10002);
            throw var10000;
         }
      }

      if (!var1.isEmpty()) {
         throw new SQLiteException("Database properties table has extra columns");
      }
   }

   public final void onOpen(SQLiteDatabase var1) {
      if (VERSION.SDK_INT < 15) {
         Cursor var2 = var1.rawQuery("PRAGMA journal_mode=memory", (String[])null);

         try {
            var2.moveToFirst();
         } finally {
            var2.close();
         }
      }

      if (!this.zza(var1, "hits2")) {
         var1.execSQL(zzams.zzkV());
      } else {
         Set var5 = zzb(var1, "hits2");
         String[] var6 = new String[]{"hit_id", "hit_string", "hit_time", "hit_url"};

         for(int var8 = 0; var8 < 4; ++var8) {
            String var9 = var6[var8];
            if (!var5.remove(var9)) {
               SQLiteException var10000 = new SQLiteException;
               String var10003 = String.valueOf(var9);
               String var10002;
               if (var10003.length() != 0) {
                  var10002 = "Database hits2 is missing required column: ".concat(var10003);
               } else {
                  String var10004 = new String;
                  var10002 = var10004;
                  var10004.<init>("Database hits2 is missing required column: ");
               }

               var10000.<init>(var10002);
               throw var10000;
            }
         }

         boolean var12 = !var5.remove("hit_app_id");
         if (!var5.isEmpty()) {
            throw new SQLiteException("Database hits2 has extra columns");
         }

         if (var12) {
            String var7 = "ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER";
            var1.execSQL(var7);
         }
      }

      if (!this.zza(var1, "properties")) {
         var1.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
      } else {
         zza(var1);
      }
   }

   public final void onCreate(SQLiteDatabase var1) {
      String var2 = var1.getPath();
      if (zzanr.version() >= 9) {
         File var3;
         (var3 = new File(var2)).setReadable(false, false);
         var3.setWritable(false, false);
         var3.setReadable(true, true);
         var3.setWritable(true, true);
      }

   }

   public final void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
   }
}
