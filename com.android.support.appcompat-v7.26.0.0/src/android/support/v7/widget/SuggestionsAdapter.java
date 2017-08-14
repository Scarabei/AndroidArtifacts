package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter extends ResourceCursorAdapter implements OnClickListener {
   private static final boolean DBG = false;
   private static final String LOG_TAG = "SuggestionsAdapter";
   private static final int QUERY_LIMIT = 50;
   static final int REFINE_NONE = 0;
   static final int REFINE_BY_ENTRY = 1;
   static final int REFINE_ALL = 2;
   private final SearchManager mSearchManager;
   private final SearchView mSearchView;
   private final SearchableInfo mSearchable;
   private final Context mProviderContext;
   private final WeakHashMap mOutsideDrawablesCache;
   private final int mCommitIconResId;
   private boolean mClosed = false;
   private int mQueryRefinement = 1;
   private ColorStateList mUrlColor;
   static final int INVALID_INDEX = -1;
   private int mText1Col = -1;
   private int mText2Col = -1;
   private int mText2UrlCol = -1;
   private int mIconName1Col = -1;
   private int mIconName2Col = -1;
   private int mFlagsCol = -1;

   public SuggestionsAdapter(Context context, SearchView searchView, SearchableInfo searchable, WeakHashMap outsideDrawablesCache) {
      super(context, searchView.getSuggestionRowLayout(), (Cursor)null, true);
      this.mSearchManager = (SearchManager)this.mContext.getSystemService("search");
      this.mSearchView = searchView;
      this.mSearchable = searchable;
      this.mCommitIconResId = searchView.getSuggestionCommitIconResId();
      this.mProviderContext = context;
      this.mOutsideDrawablesCache = outsideDrawablesCache;
   }

   public void setQueryRefinement(int refineWhat) {
      this.mQueryRefinement = refineWhat;
   }

   public int getQueryRefinement() {
      return this.mQueryRefinement;
   }

   public boolean hasStableIds() {
      return false;
   }

   public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
      String query = constraint == null ? "" : constraint.toString();
      Cursor cursor = null;
      if (this.mSearchView.getVisibility() == 0 && this.mSearchView.getWindowVisibility() == 0) {
         try {
            cursor = this.getSearchManagerSuggestions(this.mSearchable, query, 50);
            if (cursor != null) {
               cursor.getCount();
               return cursor;
            }
         } catch (RuntimeException var5) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", var5);
         }

         return null;
      } else {
         return null;
      }
   }

   public void close() {
      this.changeCursor((Cursor)null);
      this.mClosed = true;
   }

   public void notifyDataSetChanged() {
      super.notifyDataSetChanged();
      this.updateSpinnerState(this.getCursor());
   }

   public void notifyDataSetInvalidated() {
      super.notifyDataSetInvalidated();
      this.updateSpinnerState(this.getCursor());
   }

   private void updateSpinnerState(Cursor cursor) {
      Bundle extras = cursor != null ? cursor.getExtras() : null;
      if (extras == null || !extras.getBoolean("in_progress")) {
         ;
      }
   }

   public void changeCursor(Cursor c) {
      if (this.mClosed) {
         Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
         if (c != null) {
            c.close();
         }

      } else {
         try {
            super.changeCursor(c);
            if (c != null) {
               this.mText1Col = c.getColumnIndex("suggest_text_1");
               this.mText2Col = c.getColumnIndex("suggest_text_2");
               this.mText2UrlCol = c.getColumnIndex("suggest_text_2_url");
               this.mIconName1Col = c.getColumnIndex("suggest_icon_1");
               this.mIconName2Col = c.getColumnIndex("suggest_icon_2");
               this.mFlagsCol = c.getColumnIndex("suggest_flags");
            }
         } catch (Exception var3) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", var3);
         }

      }
   }

   public View newView(Context context, Cursor cursor, ViewGroup parent) {
      View v = super.newView(context, cursor, parent);
      v.setTag(new SuggestionsAdapter.ChildViewCache(v));
      ImageView iconRefine = (ImageView)v.findViewById(id.edit_query);
      iconRefine.setImageResource(this.mCommitIconResId);
      return v;
   }

   public void bindView(View view, Context context, Cursor cursor) {
      SuggestionsAdapter.ChildViewCache views = (SuggestionsAdapter.ChildViewCache)view.getTag();
      int flags = 0;
      if (this.mFlagsCol != -1) {
         flags = cursor.getInt(this.mFlagsCol);
      }

      String text2;
      if (views.mText1 != null) {
         text2 = getStringOrNull(cursor, this.mText1Col);
         this.setViewText(views.mText1, text2);
      }

      if (views.mText2 != null) {
         text2 = getStringOrNull(cursor, this.mText2UrlCol);
         Object text2;
         if (text2 != null) {
            text2 = this.formatUrl(text2);
         } else {
            text2 = getStringOrNull(cursor, this.mText2Col);
         }

         if (TextUtils.isEmpty((CharSequence)text2)) {
            if (views.mText1 != null) {
               views.mText1.setSingleLine(false);
               views.mText1.setMaxLines(2);
            }
         } else if (views.mText1 != null) {
            views.mText1.setSingleLine(true);
            views.mText1.setMaxLines(1);
         }

         this.setViewText(views.mText2, (CharSequence)text2);
      }

      if (views.mIcon1 != null) {
         this.setViewDrawable(views.mIcon1, this.getIcon1(cursor), 4);
      }

      if (views.mIcon2 != null) {
         this.setViewDrawable(views.mIcon2, this.getIcon2(cursor), 8);
      }

      if (this.mQueryRefinement != 2 && (this.mQueryRefinement != 1 || (flags & 1) == 0)) {
         views.mIconRefine.setVisibility(8);
      } else {
         views.mIconRefine.setVisibility(0);
         views.mIconRefine.setTag(views.mText1.getText());
         views.mIconRefine.setOnClickListener(this);
      }

   }

   public void onClick(View v) {
      Object tag = v.getTag();
      if (tag instanceof CharSequence) {
         this.mSearchView.onQueryRefine((CharSequence)tag);
      }

   }

   private CharSequence formatUrl(CharSequence url) {
      if (this.mUrlColor == null) {
         TypedValue colorValue = new TypedValue();
         this.mContext.getTheme().resolveAttribute(attr.textColorSearchUrl, colorValue, true);
         this.mUrlColor = this.mContext.getResources().getColorStateList(colorValue.resourceId);
      }

      SpannableString text = new SpannableString(url);
      text.setSpan(new TextAppearanceSpan((String)null, 0, 0, this.mUrlColor, (ColorStateList)null), 0, url.length(), 33);
      return text;
   }

   private void setViewText(TextView v, CharSequence text) {
      v.setText(text);
      if (TextUtils.isEmpty(text)) {
         v.setVisibility(8);
      } else {
         v.setVisibility(0);
      }

   }

   private Drawable getIcon1(Cursor cursor) {
      if (this.mIconName1Col == -1) {
         return null;
      } else {
         String value = cursor.getString(this.mIconName1Col);
         Drawable drawable = this.getDrawableFromResourceValue(value);
         return drawable != null ? drawable : this.getDefaultIcon1(cursor);
      }
   }

   private Drawable getIcon2(Cursor cursor) {
      if (this.mIconName2Col == -1) {
         return null;
      } else {
         String value = cursor.getString(this.mIconName2Col);
         return this.getDrawableFromResourceValue(value);
      }
   }

   private void setViewDrawable(ImageView v, Drawable drawable, int nullVisibility) {
      v.setImageDrawable(drawable);
      if (drawable == null) {
         v.setVisibility(nullVisibility);
      } else {
         v.setVisibility(0);
         drawable.setVisible(false, false);
         drawable.setVisible(true, false);
      }

   }

   public CharSequence convertToString(Cursor cursor) {
      if (cursor == null) {
         return null;
      } else {
         String query = getColumnString(cursor, "suggest_intent_query");
         if (query != null) {
            return query;
         } else {
            String text1;
            if (this.mSearchable.shouldRewriteQueryFromData()) {
               text1 = getColumnString(cursor, "suggest_intent_data");
               if (text1 != null) {
                  return text1;
               }
            }

            if (this.mSearchable.shouldRewriteQueryFromText()) {
               text1 = getColumnString(cursor, "suggest_text_1");
               if (text1 != null) {
                  return text1;
               }
            }

            return null;
         }
      }
   }

   public View getView(int position, View convertView, ViewGroup parent) {
      try {
         return super.getView(position, convertView, parent);
      } catch (RuntimeException var8) {
         Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", var8);
         View v = this.newView(this.mContext, this.mCursor, parent);
         if (v != null) {
            SuggestionsAdapter.ChildViewCache views = (SuggestionsAdapter.ChildViewCache)v.getTag();
            TextView tv = views.mText1;
            tv.setText(var8.toString());
         }

         return v;
      }
   }

   public View getDropDownView(int position, View convertView, ViewGroup parent) {
      try {
         return super.getDropDownView(position, convertView, parent);
      } catch (RuntimeException var8) {
         Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", var8);
         View v = this.newDropDownView(this.mContext, this.mCursor, parent);
         if (v != null) {
            SuggestionsAdapter.ChildViewCache views = (SuggestionsAdapter.ChildViewCache)v.getTag();
            TextView tv = views.mText1;
            tv.setText(var8.toString());
         }

         return v;
      }
   }

   private Drawable getDrawableFromResourceValue(String drawableId) {
      if (drawableId != null && !drawableId.isEmpty() && !"0".equals(drawableId)) {
         try {
            int resourceId = Integer.parseInt(drawableId);
            String drawableUri = "android.resource://" + this.mProviderContext.getPackageName() + "/" + resourceId;
            Drawable drawable = this.checkIconCache(drawableUri);
            if (drawable != null) {
               return drawable;
            } else {
               drawable = ContextCompat.getDrawable(this.mProviderContext, resourceId);
               this.storeInIconCache(drawableUri, drawable);
               return drawable;
            }
         } catch (NumberFormatException var5) {
            Drawable drawable = this.checkIconCache(drawableId);
            if (drawable != null) {
               return drawable;
            } else {
               Uri uri = Uri.parse(drawableId);
               drawable = this.getDrawable(uri);
               this.storeInIconCache(drawableId, drawable);
               return drawable;
            }
         } catch (NotFoundException var6) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + drawableId);
            return null;
         }
      } else {
         return null;
      }
   }

   private Drawable getDrawable(Uri uri) {
      try {
         String scheme = uri.getScheme();
         if ("android.resource".equals(scheme)) {
            try {
               return this.getDrawableFromResourceUri(uri);
            } catch (NotFoundException var14) {
               throw new FileNotFoundException("Resource does not exist: " + uri);
            }
         } else {
            InputStream stream = this.mProviderContext.getContentResolver().openInputStream(uri);
            if (stream == null) {
               throw new FileNotFoundException("Failed to open " + uri);
            } else {
               Drawable var4;
               try {
                  var4 = Drawable.createFromStream(stream, (String)null);
               } finally {
                  try {
                     stream.close();
                  } catch (IOException var13) {
                     Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, var13);
                  }

               }

               return var4;
            }
         }
      } catch (FileNotFoundException var16) {
         Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + var16.getMessage());
         return null;
      }
   }

   private Drawable checkIconCache(String resourceUri) {
      ConstantState cached = (ConstantState)this.mOutsideDrawablesCache.get(resourceUri);
      return cached == null ? null : cached.newDrawable();
   }

   private void storeInIconCache(String resourceUri, Drawable drawable) {
      if (drawable != null) {
         this.mOutsideDrawablesCache.put(resourceUri, drawable.getConstantState());
      }

   }

   private Drawable getDefaultIcon1(Cursor cursor) {
      Drawable drawable = this.getActivityIconWithCache(this.mSearchable.getSearchActivity());
      return drawable != null ? drawable : this.mContext.getPackageManager().getDefaultActivityIcon();
   }

   private Drawable getActivityIconWithCache(ComponentName component) {
      String componentIconKey = component.flattenToShortString();
      if (this.mOutsideDrawablesCache.containsKey(componentIconKey)) {
         ConstantState cached = (ConstantState)this.mOutsideDrawablesCache.get(componentIconKey);
         return cached == null ? null : cached.newDrawable(this.mProviderContext.getResources());
      } else {
         Drawable drawable = this.getActivityIcon(component);
         ConstantState toCache = drawable == null ? null : drawable.getConstantState();
         this.mOutsideDrawablesCache.put(componentIconKey, toCache);
         return drawable;
      }
   }

   private Drawable getActivityIcon(ComponentName component) {
      PackageManager pm = this.mContext.getPackageManager();

      ActivityInfo activityInfo;
      try {
         activityInfo = pm.getActivityInfo(component, 128);
      } catch (NameNotFoundException var7) {
         Log.w("SuggestionsAdapter", var7.toString());
         return null;
      }

      int iconId = activityInfo.getIconResource();
      if (iconId == 0) {
         return null;
      } else {
         String pkg = component.getPackageName();
         Drawable drawable = pm.getDrawable(pkg, iconId, activityInfo.applicationInfo);
         if (drawable == null) {
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconId + " for " + component.flattenToShortString());
            return null;
         } else {
            return drawable;
         }
      }
   }

   public static String getColumnString(Cursor cursor, String columnName) {
      int col = cursor.getColumnIndex(columnName);
      return getStringOrNull(cursor, col);
   }

   private static String getStringOrNull(Cursor cursor, int col) {
      if (col == -1) {
         return null;
      } else {
         try {
            return cursor.getString(col);
         } catch (Exception var3) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", var3);
            return null;
         }
      }
   }

   Drawable getDrawableFromResourceUri(Uri uri) throws FileNotFoundException {
      String authority = uri.getAuthority();
      if (TextUtils.isEmpty(authority)) {
         throw new FileNotFoundException("No authority: " + uri);
      } else {
         Resources r;
         try {
            r = this.mContext.getPackageManager().getResourcesForApplication(authority);
         } catch (NameNotFoundException var9) {
            throw new FileNotFoundException("No package found for authority: " + uri);
         }

         List path = uri.getPathSegments();
         if (path == null) {
            throw new FileNotFoundException("No path: " + uri);
         } else {
            int len = path.size();
            int id;
            if (len == 1) {
               try {
                  id = Integer.parseInt((String)path.get(0));
               } catch (NumberFormatException var8) {
                  throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
               }
            } else {
               if (len != 2) {
                  throw new FileNotFoundException("More than two path segments: " + uri);
               }

               id = r.getIdentifier((String)path.get(1), (String)path.get(0), authority);
            }

            if (id == 0) {
               throw new FileNotFoundException("No resource found for: " + uri);
            } else {
               return r.getDrawable(id);
            }
         }
      }
   }

   Cursor getSearchManagerSuggestions(SearchableInfo searchable, String query, int limit) {
      if (searchable == null) {
         return null;
      } else {
         String authority = searchable.getSuggestAuthority();
         if (authority == null) {
            return null;
         } else {
            Builder uriBuilder = (new Builder()).scheme("content").authority(authority).query("").fragment("");
            String contentPath = searchable.getSuggestPath();
            if (contentPath != null) {
               uriBuilder.appendEncodedPath(contentPath);
            }

            uriBuilder.appendPath("search_suggest_query");
            String selection = searchable.getSuggestSelection();
            String[] selArgs = null;
            if (selection != null) {
               selArgs = new String[]{query};
            } else {
               uriBuilder.appendPath(query);
            }

            if (limit > 0) {
               uriBuilder.appendQueryParameter("limit", String.valueOf(limit));
            }

            Uri uri = uriBuilder.build();
            return this.mContext.getContentResolver().query(uri, (String[])null, selection, selArgs, (String)null);
         }
      }
   }

   private static final class ChildViewCache {
      public final TextView mText1;
      public final TextView mText2;
      public final ImageView mIcon1;
      public final ImageView mIcon2;
      public final ImageView mIconRefine;

      public ChildViewCache(View v) {
         this.mText1 = (TextView)v.findViewById(16908308);
         this.mText2 = (TextView)v.findViewById(16908309);
         this.mIcon1 = (ImageView)v.findViewById(16908295);
         this.mIcon2 = (ImageView)v.findViewById(16908296);
         this.mIconRefine = (ImageView)v.findViewById(id.edit_query);
      }
   }
}
