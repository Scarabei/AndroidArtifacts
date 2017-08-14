package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

public abstract class CursorAdapter extends BaseAdapter implements Filterable, CursorFilter.CursorFilterClient {
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected boolean mDataValid;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected boolean mAutoRequery;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected Cursor mCursor;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected Context mContext;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected int mRowIDColumn;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected CursorAdapter.ChangeObserver mChangeObserver;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected DataSetObserver mDataSetObserver;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected CursorFilter mCursorFilter;
   @RestrictTo({Scope.LIBRARY_GROUP})
   protected FilterQueryProvider mFilterQueryProvider;
   /** @deprecated */
   @Deprecated
   public static final int FLAG_AUTO_REQUERY = 1;
   public static final int FLAG_REGISTER_CONTENT_OBSERVER = 2;

   /** @deprecated */
   @Deprecated
   public CursorAdapter(Context context, Cursor c) {
      this.init(context, c, 1);
   }

   public CursorAdapter(Context context, Cursor c, boolean autoRequery) {
      this.init(context, c, autoRequery ? 1 : 2);
   }

   public CursorAdapter(Context context, Cursor c, int flags) {
      this.init(context, c, flags);
   }

   /** @deprecated */
   @Deprecated
   protected void init(Context context, Cursor c, boolean autoRequery) {
      this.init(context, c, autoRequery ? 1 : 2);
   }

   void init(Context context, Cursor c, int flags) {
      if ((flags & 1) == 1) {
         flags |= 2;
         this.mAutoRequery = true;
      } else {
         this.mAutoRequery = false;
      }

      boolean cursorPresent = c != null;
      this.mCursor = c;
      this.mDataValid = cursorPresent;
      this.mContext = context;
      this.mRowIDColumn = cursorPresent ? c.getColumnIndexOrThrow("_id") : -1;
      if ((flags & 2) == 2) {
         this.mChangeObserver = new CursorAdapter.ChangeObserver();
         this.mDataSetObserver = new CursorAdapter.MyDataSetObserver();
      } else {
         this.mChangeObserver = null;
         this.mDataSetObserver = null;
      }

      if (cursorPresent) {
         if (this.mChangeObserver != null) {
            c.registerContentObserver(this.mChangeObserver);
         }

         if (this.mDataSetObserver != null) {
            c.registerDataSetObserver(this.mDataSetObserver);
         }
      }

   }

   public Cursor getCursor() {
      return this.mCursor;
   }

   public int getCount() {
      return this.mDataValid && this.mCursor != null ? this.mCursor.getCount() : 0;
   }

   public Object getItem(int position) {
      if (this.mDataValid && this.mCursor != null) {
         this.mCursor.moveToPosition(position);
         return this.mCursor;
      } else {
         return null;
      }
   }

   public long getItemId(int position) {
      if (this.mDataValid && this.mCursor != null) {
         return this.mCursor.moveToPosition(position) ? this.mCursor.getLong(this.mRowIDColumn) : 0L;
      } else {
         return 0L;
      }
   }

   public boolean hasStableIds() {
      return true;
   }

   public View getView(int position, View convertView, ViewGroup parent) {
      if (!this.mDataValid) {
         throw new IllegalStateException("this should only be called when the cursor is valid");
      } else if (!this.mCursor.moveToPosition(position)) {
         throw new IllegalStateException("couldn't move cursor to position " + position);
      } else {
         View v;
         if (convertView == null) {
            v = this.newView(this.mContext, this.mCursor, parent);
         } else {
            v = convertView;
         }

         this.bindView(v, this.mContext, this.mCursor);
         return v;
      }
   }

   public View getDropDownView(int position, View convertView, ViewGroup parent) {
      if (this.mDataValid) {
         this.mCursor.moveToPosition(position);
         View v;
         if (convertView == null) {
            v = this.newDropDownView(this.mContext, this.mCursor, parent);
         } else {
            v = convertView;
         }

         this.bindView(v, this.mContext, this.mCursor);
         return v;
      } else {
         return null;
      }
   }

   public abstract View newView(Context var1, Cursor var2, ViewGroup var3);

   public View newDropDownView(Context context, Cursor cursor, ViewGroup parent) {
      return this.newView(context, cursor, parent);
   }

   public abstract void bindView(View var1, Context var2, Cursor var3);

   public void changeCursor(Cursor cursor) {
      Cursor old = this.swapCursor(cursor);
      if (old != null) {
         old.close();
      }

   }

   public Cursor swapCursor(Cursor newCursor) {
      if (newCursor == this.mCursor) {
         return null;
      } else {
         Cursor oldCursor = this.mCursor;
         if (oldCursor != null) {
            if (this.mChangeObserver != null) {
               oldCursor.unregisterContentObserver(this.mChangeObserver);
            }

            if (this.mDataSetObserver != null) {
               oldCursor.unregisterDataSetObserver(this.mDataSetObserver);
            }
         }

         this.mCursor = newCursor;
         if (newCursor != null) {
            if (this.mChangeObserver != null) {
               newCursor.registerContentObserver(this.mChangeObserver);
            }

            if (this.mDataSetObserver != null) {
               newCursor.registerDataSetObserver(this.mDataSetObserver);
            }

            this.mRowIDColumn = newCursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            this.notifyDataSetChanged();
         } else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
            this.notifyDataSetInvalidated();
         }

         return oldCursor;
      }
   }

   public CharSequence convertToString(Cursor cursor) {
      return cursor == null ? "" : cursor.toString();
   }

   public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
      return this.mFilterQueryProvider != null ? this.mFilterQueryProvider.runQuery(constraint) : this.mCursor;
   }

   public Filter getFilter() {
      if (this.mCursorFilter == null) {
         this.mCursorFilter = new CursorFilter(this);
      }

      return this.mCursorFilter;
   }

   public FilterQueryProvider getFilterQueryProvider() {
      return this.mFilterQueryProvider;
   }

   public void setFilterQueryProvider(FilterQueryProvider filterQueryProvider) {
      this.mFilterQueryProvider = filterQueryProvider;
   }

   protected void onContentChanged() {
      if (this.mAutoRequery && this.mCursor != null && !this.mCursor.isClosed()) {
         this.mDataValid = this.mCursor.requery();
      }

   }

   private class MyDataSetObserver extends DataSetObserver {
      public void onChanged() {
         CursorAdapter.this.mDataValid = true;
         CursorAdapter.this.notifyDataSetChanged();
      }

      public void onInvalidated() {
         CursorAdapter.this.mDataValid = false;
         CursorAdapter.this.notifyDataSetInvalidated();
      }
   }

   private class ChangeObserver extends ContentObserver {
      ChangeObserver() {
         super(new Handler());
      }

      public boolean deliverSelfNotifications() {
         return true;
      }

      public void onChange(boolean selfChange) {
         CursorAdapter.this.onContentChanged();
      }
   }
}
