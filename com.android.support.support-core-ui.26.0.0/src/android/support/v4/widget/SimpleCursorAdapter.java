package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleCursorAdapter extends ResourceCursorAdapter {
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected int[] mFrom;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected int[] mTo;
   private int mStringConversionColumn = -1;
   private SimpleCursorAdapter.CursorToStringConverter mCursorToStringConverter;
   private SimpleCursorAdapter.ViewBinder mViewBinder;
   String[] mOriginalFrom;

   /** @deprecated */
   @Deprecated
   public SimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to) {
      super(context, layout, c);
      this.mTo = to;
      this.mOriginalFrom = from;
      this.findColumns(c, from);
   }

   public SimpleCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
      super(context, layout, c, flags);
      this.mTo = to;
      this.mOriginalFrom = from;
      this.findColumns(c, from);
   }

   public void bindView(View view, Context context, Cursor cursor) {
      SimpleCursorAdapter.ViewBinder binder = this.mViewBinder;
      int count = this.mTo.length;
      int[] from = this.mFrom;
      int[] to = this.mTo;

      for(int i = 0; i < count; ++i) {
         View v = view.findViewById(to[i]);
         if (v != null) {
            boolean bound = false;
            if (binder != null) {
               bound = binder.setViewValue(v, cursor, from[i]);
            }

            if (!bound) {
               String text = cursor.getString(from[i]);
               if (text == null) {
                  text = "";
               }

               if (v instanceof TextView) {
                  this.setViewText((TextView)v, text);
               } else {
                  if (!(v instanceof ImageView)) {
                     throw new IllegalStateException(v.getClass().getName() + " is not a " + " view that can be bounds by this SimpleCursorAdapter");
                  }

                  this.setViewImage((ImageView)v, text);
               }
            }
         }
      }

   }

   public SimpleCursorAdapter.ViewBinder getViewBinder() {
      return this.mViewBinder;
   }

   public void setViewBinder(SimpleCursorAdapter.ViewBinder viewBinder) {
      this.mViewBinder = viewBinder;
   }

   public void setViewImage(ImageView v, String value) {
      try {
         v.setImageResource(Integer.parseInt(value));
      } catch (NumberFormatException var4) {
         v.setImageURI(Uri.parse(value));
      }

   }

   public void setViewText(TextView v, String text) {
      v.setText(text);
   }

   public int getStringConversionColumn() {
      return this.mStringConversionColumn;
   }

   public void setStringConversionColumn(int stringConversionColumn) {
      this.mStringConversionColumn = stringConversionColumn;
   }

   public SimpleCursorAdapter.CursorToStringConverter getCursorToStringConverter() {
      return this.mCursorToStringConverter;
   }

   public void setCursorToStringConverter(SimpleCursorAdapter.CursorToStringConverter cursorToStringConverter) {
      this.mCursorToStringConverter = cursorToStringConverter;
   }

   public CharSequence convertToString(Cursor cursor) {
      if (this.mCursorToStringConverter != null) {
         return this.mCursorToStringConverter.convertToString(cursor);
      } else {
         return (CharSequence)(this.mStringConversionColumn > -1 ? cursor.getString(this.mStringConversionColumn) : super.convertToString(cursor));
      }
   }

   private void findColumns(Cursor c, String[] from) {
      if (c != null) {
         int count = from.length;
         if (this.mFrom == null || this.mFrom.length != count) {
            this.mFrom = new int[count];
         }

         for(int i = 0; i < count; ++i) {
            this.mFrom[i] = c.getColumnIndexOrThrow(from[i]);
         }
      } else {
         this.mFrom = null;
      }

   }

   public Cursor swapCursor(Cursor c) {
      this.findColumns(c, this.mOriginalFrom);
      return super.swapCursor(c);
   }

   public void changeCursorAndColumns(Cursor c, String[] from, int[] to) {
      this.mOriginalFrom = from;
      this.mTo = to;
      this.findColumns(c, this.mOriginalFrom);
      super.changeCursor(c);
   }

   public interface CursorToStringConverter {
      CharSequence convertToString(Cursor var1);
   }

   public interface ViewBinder {
      boolean setViewValue(View var1, Cursor var2, int var3);
   }
}
