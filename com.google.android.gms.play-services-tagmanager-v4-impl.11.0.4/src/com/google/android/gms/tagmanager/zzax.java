package com.google.android.gms.tagmanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Build.VERSION;
import java.util.HashSet;

final class zzax extends SQLiteOpenHelper {
   // $FF: synthetic field
   private zzat zzbEt;

   zzax(zzat var1, Context var2, String var3) {
      this.zzbEt = var1;
      super(var2, var3, (CursorFactory)null, 1);
   }

   private static boolean zza(String var0, SQLiteDatabase var1) {
      Cursor var2 = null;

      try {
         boolean var3 = (var2 = var1.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{var0}, (String)null, (String)null, (String)null)).moveToFirst();
         return var3;
      } catch (SQLiteException var7) {
         String var10001 = String.valueOf(var0);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Error querying for table ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Error querying for table ");
         }

         zzdj.zzaT(var10000);
      } finally {
         if (var2 != null) {
            var2.close();
         }

      }

      return false;
   }

   public final SQLiteDatabase getWritableDatabase() {
      SQLiteDatabase var1 = null;

      try {
         var1 = super.getWritableDatabase();
      } catch (SQLiteException var2) {
         zzat.zzb(this.zzbEt).getDatabasePath("google_tagmanager.db").delete();
      }

      if (var1 == null) {
         var1 = super.getWritableDatabase();
      }

      return var1;
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

      if (!zza("datalayer", var1)) {
         var1.execSQL(zzat.zzBe());
      } else {
         Cursor var4 = var1.rawQuery("SELECT * FROM datalayer WHERE 0", (String[])null);
         HashSet var5 = new HashSet();

         try {
            String[] var6 = var4.getColumnNames();

            for(int var7 = 0; var7 < var6.length; ++var7) {
               var5.add(var6[var7]);
            }
         } finally {
            var4.close();
         }

         if (var5.remove("key") && var5.remove("value") && var5.remove("ID") && var5.remove("expires")) {
            if (!var5.isEmpty()) {
               throw new SQLiteException("Database has extra columns");
            }
         } else {
            throw new SQLiteException("Database column missing");
         }
      }
   }

   public final void onCreate(SQLiteDatabase var1) {
      zzbs.zzfk(var1.getPath());
   }

   public final void onUpgrade(SQLiteDatabase var1, int var2, int var3) {
   }

   public final void onDowngrade(SQLiteDatabase var1, int var2, int var3) {
   }
}
