package com.google.android.gms.fitness;

import android.content.Intent;
import android.os.Build.VERSION;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbup;
import com.google.android.gms.internal.zzbut;
import com.google.android.gms.internal.zzbux;
import com.google.android.gms.internal.zzbva;
import com.google.android.gms.internal.zzbve;
import com.google.android.gms.internal.zzbvg;
import com.google.android.gms.internal.zzbvk;
import com.google.android.gms.internal.zzbvo;
import com.google.android.gms.internal.zzbxj;
import com.google.android.gms.internal.zzbxk;
import com.google.android.gms.internal.zzbxs;
import com.google.android.gms.internal.zzbxx;
import com.google.android.gms.internal.zzbya;
import com.google.android.gms.internal.zzbyk;
import com.google.android.gms.internal.zzbyl;
import com.google.android.gms.internal.zzbys;
import com.google.android.gms.internal.zzbyz;
import com.google.android.gms.internal.zzbzj;
import java.util.concurrent.TimeUnit;

public class Fitness {
   /** @deprecated */
   @Deprecated
   public static final Void API = null;
   public static final Api SENSORS_API;
   public static final SensorsApi SensorsApi;
   public static final Api RECORDING_API;
   public static final RecordingApi RecordingApi;
   public static final Api SESSIONS_API;
   public static final SessionsApi SessionsApi;
   public static final Api HISTORY_API;
   public static final HistoryApi HistoryApi;
   public static final Api GOALS_API;
   public static final GoalsApi GoalsApi;
   public static final Api CONFIG_API;
   public static final ConfigApi ConfigApi;
   public static final Api BLE_API;
   public static final BleApi BleApi;
   private static Api zzaMc;
   private static zzbxj zzaSY;
   public static final Scope SCOPE_ACTIVITY_READ;
   public static final Scope SCOPE_ACTIVITY_READ_WRITE;
   public static final Scope SCOPE_LOCATION_READ;
   public static final Scope SCOPE_LOCATION_READ_WRITE;
   public static final Scope SCOPE_BODY_READ;
   public static final Scope SCOPE_BODY_READ_WRITE;
   public static final Scope SCOPE_NUTRITION_READ;
   public static final Scope SCOPE_NUTRITION_READ_WRITE;
   public static final String ACTION_TRACK = "vnd.google.fitness.TRACK";
   public static final String ACTION_VIEW = "vnd.google.fitness.VIEW";
   public static final String ACTION_VIEW_GOAL = "vnd.google.fitness.VIEW_GOAL";
   public static final String EXTRA_START_TIME = "vnd.google.fitness.start_time";
   public static final String EXTRA_END_TIME = "vnd.google.fitness.end_time";

   public static long getStartTime(Intent var0, TimeUnit var1) {
      long var2;
      return (var2 = var0.getLongExtra("vnd.google.fitness.start_time", -1L)) == -1L ? -1L : var1.convert(var2, TimeUnit.MILLISECONDS);
   }

   public static long getEndTime(Intent var0, TimeUnit var1) {
      long var2;
      return (var2 = var0.getLongExtra("vnd.google.fitness.end_time", -1L)) == -1L ? -1L : var1.convert(var2, TimeUnit.MILLISECONDS);
   }

   static {
      SENSORS_API = zzbvk.API;
      SensorsApi = new zzbys();
      RECORDING_API = zzbvg.API;
      RecordingApi = new zzbyl();
      SESSIONS_API = zzbvo.API;
      SessionsApi = new zzbyz();
      HISTORY_API = zzbva.API;
      HistoryApi = new zzbya();
      GOALS_API = zzbux.API;
      GoalsApi = new zzbxx();
      CONFIG_API = zzbut.API;
      ConfigApi = new zzbxs();
      BLE_API = zzbup.API;
      BleApi = (BleApi)(VERSION.SDK_INT >= 18 ? new zzbxk() : new zzbzj());
      zzaMc = zzbve.API;
      zzaSY = new zzbyk();
      SCOPE_ACTIVITY_READ = new Scope("https://www.googleapis.com/auth/fitness.activity.read");
      SCOPE_ACTIVITY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.activity.write");
      SCOPE_LOCATION_READ = new Scope("https://www.googleapis.com/auth/fitness.location.read");
      SCOPE_LOCATION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.location.write");
      SCOPE_BODY_READ = new Scope("https://www.googleapis.com/auth/fitness.body.read");
      SCOPE_BODY_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.body.write");
      SCOPE_NUTRITION_READ = new Scope("https://www.googleapis.com/auth/fitness.nutrition.read");
      SCOPE_NUTRITION_READ_WRITE = new Scope("https://www.googleapis.com/auth/fitness.nutrition.write");
   }
}
