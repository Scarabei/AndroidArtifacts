package com.google.android.gms.drive.widget;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.internal.zzbng;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DataBufferAdapter extends BaseAdapter {
   private final Context mContext;
   private final int zzaSm;
   private int zzaSn;
   private final int zzaSo;
   private final List zzaSp;
   private final LayoutInflater mInflater;
   private boolean zzaSq;

   public DataBufferAdapter(Context var1, int var2, int var3, List var4) {
      this.zzaSq = true;
      this.mContext = var1;
      this.zzaSm = this.zzaSn = var2;
      this.zzaSo = var3;
      this.zzaSp = var4;
      this.mInflater = (LayoutInflater)var1.getSystemService("layout_inflater");
   }

   public DataBufferAdapter(Context var1, int var2, int var3) {
      this(var1, var2, var3, (List)(new ArrayList()));
   }

   public DataBufferAdapter(Context var1, int var2, List var3) {
      this(var1, var2, 0, (List)var3);
   }

   public DataBufferAdapter(Context var1, int var2) {
      this(var1, var2, 0, (List)(new ArrayList()));
   }

   public DataBufferAdapter(Context var1, int var2, int var3, DataBuffer... var4) {
      this(var1, var2, var3, Arrays.asList(var4));
   }

   public DataBufferAdapter(Context var1, int var2, DataBuffer... var3) {
      this(var1, var2, 0, (List)Arrays.asList(var3));
   }

   public void append(DataBuffer var1) {
      this.zzaSp.add(var1);
      if (this.zzaSq) {
         this.notifyDataSetChanged();
      }

   }

   public void clear() {
      Iterator var1 = this.zzaSp.iterator();

      while(var1.hasNext()) {
         ((DataBuffer)var1.next()).release();
      }

      this.zzaSp.clear();
      if (this.zzaSq) {
         this.notifyDataSetChanged();
      }

   }

   public void notifyDataSetChanged() {
      super.notifyDataSetChanged();
      this.zzaSq = true;
   }

   public void setNotifyOnChange(boolean var1) {
      this.zzaSq = var1;
   }

   public Context getContext() {
      return this.mContext;
   }

   public int getCount() {
      int var1 = 0;

      DataBuffer var3;
      for(Iterator var2 = this.zzaSp.iterator(); var2.hasNext(); var1 += var3.getCount()) {
         var3 = (DataBuffer)var2.next();
      }

      return var1;
   }

   public Object getItem(int var1) throws CursorIndexOutOfBoundsException {
      int var2 = var1;

      int var5;
      for(Iterator var3 = this.zzaSp.iterator(); var3.hasNext(); var2 -= var5) {
         DataBuffer var4;
         if ((var5 = (var4 = (DataBuffer)var3.next()).getCount()) > var2) {
            try {
               return var4.get(var2);
            } catch (CursorIndexOutOfBoundsException var6) {
               throw new CursorIndexOutOfBoundsException(var1, this.getCount());
            }
         }
      }

      throw new CursorIndexOutOfBoundsException(var1, this.getCount());
   }

   public long getItemId(int var1) {
      return (long)var1;
   }

   private final View zza(int var1, View var2, ViewGroup var3, int var4) {
      View var5;
      if (var2 == null) {
         var5 = this.mInflater.inflate(var4, var3, false);
      } else {
         var5 = var2;
      }

      TextView var6;
      try {
         if (this.zzaSo == 0) {
            var6 = (TextView)var5;
         } else {
            var6 = (TextView)var5.findViewById(this.zzaSo);
         }
      } catch (ClassCastException var8) {
         zzbng.zza("DataBufferAdapter", var8, "You must supply a resource ID for a TextView");
         throw new IllegalStateException("DataBufferAdapter requires the resource ID to be a TextView", var8);
      }

      Object var7;
      if ((var7 = this.getItem(var1)) instanceof CharSequence) {
         var6.setText((CharSequence)var7);
      } else {
         var6.setText(var7.toString());
      }

      return var5;
   }

   public View getView(int var1, View var2, ViewGroup var3) {
      return this.zza(var1, var2, var3, this.zzaSm);
   }

   public View getDropDownView(int var1, View var2, ViewGroup var3) {
      return this.zza(var1, var2, var3, this.zzaSn);
   }

   public void setDropDownViewResource(int var1) {
      this.zzaSn = var1;
   }
}
