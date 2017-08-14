package com.google.android.gms.cast.framework.media;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import com.google.android.gms.R.id;
import com.google.android.gms.R.layout;
import com.google.android.gms.R.string;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.MediaTrack.Builder;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class TracksChooserDialogFragment extends DialogFragment {
   private List zzauH;
   private List zzauI;
   private long[] zzaqu;
   private Dialog mDialog;

   public static TracksChooserDialogFragment newInstance(MediaInfo var0, long[] var1) {
      if (var0 == null) {
         return null;
      } else {
         List var2;
         if ((var2 = var0.getMediaTracks()) == null) {
            return null;
         } else {
            ArrayList var3 = zza(var2, 2);
            ArrayList var4 = zza(var2, 1);
            if (var3.size() <= 1 && var4.isEmpty()) {
               return null;
            } else {
               TracksChooserDialogFragment var5 = new TracksChooserDialogFragment();
               Bundle var6;
               (var6 = new Bundle()).putParcelableArrayList("extra_tracks_type_audio", var3);
               var6.putParcelableArrayList("extra_tracks_type_text", var4);
               var6.putLongArray("extra_active_track_ids", var1);
               var5.setArguments(var6);
               return var5;
            }
         }
      }
   }

   public void onCreate(Bundle var1) {
      super.onCreate(var1);
      this.setRetainInstance(true);
      this.zzauH = this.getArguments().getParcelableArrayList("extra_tracks_type_text");
      if (!this.zzauH.isEmpty()) {
         this.zzauH.add(0, (new Builder(-1L, 1)).setName(this.getActivity().getString(string.cast_tracks_chooser_dialog_none)).setSubtype(2).setContentId("").build());
      }

      this.zzauI = this.getArguments().getParcelableArrayList("extra_tracks_type_audio");
      this.zzaqu = this.getArguments().getLongArray("extra_active_track_ids");
   }

   @NonNull
   public Dialog onCreateDialog(Bundle var1) {
      int var2 = zza(this.zzauH, this.zzaqu, 0);
      int var3 = zza(this.zzauI, this.zzaqu, -1);
      zzal var4 = new zzal(this.getActivity(), this.zzauH, var2);
      zzal var5 = new zzal(this.getActivity(), this.zzauI, var3);
      android.app.AlertDialog.Builder var6 = new android.app.AlertDialog.Builder(this.getActivity());
      View var7 = this.getActivity().getLayoutInflater().inflate(layout.cast_tracks_chooser_dialog_layout, (ViewGroup)null);
      ListView var12 = (ListView)var7.findViewById(id.text_list_view);
      ListView var13 = (ListView)var7.findViewById(id.audio_list_view);
      TabHost var14;
      (var14 = (TabHost)var7.findViewById(id.tab_host)).setup();
      TabSpec var15;
      if (var4.getCount() == 0) {
         var12.setVisibility(4);
      } else {
         var12.setAdapter(var4);
         (var15 = var14.newTabSpec("textTab")).setContent(id.text_list_view);
         var15.setIndicator(this.getActivity().getString(string.cast_tracks_chooser_dialog_subtitles));
         var14.addTab(var15);
      }

      if (var5.getCount() <= 1) {
         var13.setVisibility(4);
      } else {
         var13.setAdapter(var5);
         (var15 = var14.newTabSpec("audioTab")).setContent(id.audio_list_view);
         var15.setIndicator(this.getActivity().getString(string.cast_tracks_chooser_dialog_audio));
         var14.addTab(var15);
      }

      var6.setView(var7).setPositiveButton(this.getActivity().getString(string.cast_tracks_chooser_dialog_ok), new zzak(this, var4, var5)).setNegativeButton(string.cast_tracks_chooser_dialog_cancel, new zzaj(this));
      if (this.mDialog != null) {
         this.mDialog.cancel();
         this.mDialog = null;
      }

      this.mDialog = var6.create();
      return this.mDialog;
   }

   private final void zza(zzal var1, zzal var2) {
      CastSession var3;
      if ((var3 = CastContext.getSharedInstance(this.getContext()).getSessionManager().getCurrentCastSession()) != null && var3.isConnected()) {
         RemoteMediaClient var4;
         if ((var4 = var3.getRemoteMediaClient()) != null && var4.hasMediaSession()) {
            ArrayList var5 = new ArrayList();
            MediaTrack var6;
            if ((var6 = var1.zzod()).getId() != -1L) {
               var5.add(var6.getId());
            }

            MediaTrack var7;
            if ((var7 = var2.zzod()) != null) {
               var5.add(var7.getId());
            }

            long[] var8;
            if ((var8 = var4.getMediaStatus().getActiveTrackIds()) != null && var8.length > 0) {
               HashSet var9 = new HashSet();
               Iterator var10 = this.zzauI.iterator();

               MediaTrack var11;
               while(var10.hasNext()) {
                  var11 = (MediaTrack)var10.next();
                  var9.add(var11.getId());
               }

               var10 = this.zzauH.iterator();

               while(var10.hasNext()) {
                  var11 = (MediaTrack)var10.next();
                  var9.add(var11.getId());
               }

               long[] var16 = var8;
               int var18 = var8.length;

               for(int var12 = 0; var12 < var18; ++var12) {
                  long var13 = var16[var12];
                  if (!var9.contains(var13)) {
                     var5.add(var13);
                  }
               }
            }

            long[] var15 = new long[var5.size()];

            for(int var17 = 0; var17 < var5.size(); ++var17) {
               var15[var17] = ((Long)var5.get(var17)).longValue();
            }

            Arrays.sort(var15);
            var4.setActiveMediaTracks(var15);
            if (this.mDialog != null) {
               this.mDialog.cancel();
               this.mDialog = null;
            }

         }
      }
   }

   public void onDestroyView() {
      if (this.getDialog() != null && this.getRetainInstance()) {
         this.getDialog().setDismissMessage((Message)null);
      }

      super.onDestroyView();
   }

   @NonNull
   private static ArrayList zza(List var0, int var1) {
      ArrayList var2 = new ArrayList();
      if (var0 != null) {
         Iterator var3 = var0.iterator();

         while(var3.hasNext()) {
            MediaTrack var4;
            if ((var4 = (MediaTrack)var3.next()).getType() == var1) {
               var2.add(var4);
            }
         }
      }

      return var2;
   }

   private static int zza(List var0, long[] var1, int var2) {
      if (var1 != null && var0 != null) {
         for(int var3 = 0; var3 < var0.size(); ++var3) {
            long[] var4 = var1;
            int var5 = var1.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               if (var4[var6] == ((MediaTrack)var0.get(var3)).getId()) {
                  return var3;
               }
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   // $FF: synthetic method
   static Dialog zza(TracksChooserDialogFragment var0) {
      return var0.mDialog;
   }

   // $FF: synthetic method
   static Dialog zza(TracksChooserDialogFragment var0, Dialog var1) {
      return var0.mDialog = null;
   }

   // $FF: synthetic method
   static void zza(TracksChooserDialogFragment var0, zzal var1, zzal var2) {
      var0.zza(var1, var2);
   }
}
