package android.support.v7.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.support.v7.mediarouter.R.attr;
import android.support.v7.mediarouter.R.id;
import android.support.v7.mediarouter.R.layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MediaRouteChooserDialog extends AppCompatDialog {
   static final String TAG = "MediaRouteChooserDialog";
   private static final long UPDATE_ROUTES_DELAY_MS = 300L;
   static final int MSG_UPDATE_ROUTES = 1;
   private final MediaRouter mRouter;
   private final MediaRouteChooserDialog.MediaRouterCallback mCallback;
   private TextView mTitleView;
   private MediaRouteSelector mSelector;
   private ArrayList mRoutes;
   private MediaRouteChooserDialog.RouteAdapter mAdapter;
   private ListView mListView;
   private boolean mAttachedToWindow;
   private long mLastUpdateTime;
   private final Handler mHandler;

   public MediaRouteChooserDialog(Context context) {
      this(context, 0);
   }

   public MediaRouteChooserDialog(Context context, int theme) {
      super(MediaRouterThemeHelper.createThemedContext(context, theme), theme);
      this.mSelector = MediaRouteSelector.EMPTY;
      this.mHandler = new Handler() {
         public void handleMessage(Message message) {
            switch(message.what) {
            case 1:
               MediaRouteChooserDialog.this.updateRoutes((List)message.obj);
            default:
            }
         }
      };
      context = this.getContext();
      this.mRouter = MediaRouter.getInstance(context);
      this.mCallback = new MediaRouteChooserDialog.MediaRouterCallback();
   }

   @NonNull
   public MediaRouteSelector getRouteSelector() {
      return this.mSelector;
   }

   public void setRouteSelector(@NonNull MediaRouteSelector selector) {
      if (selector == null) {
         throw new IllegalArgumentException("selector must not be null");
      } else {
         if (!this.mSelector.equals(selector)) {
            this.mSelector = selector;
            if (this.mAttachedToWindow) {
               this.mRouter.removeCallback(this.mCallback);
               this.mRouter.addCallback(selector, this.mCallback, 1);
            }

            this.refreshRoutes();
         }

      }
   }

   public void onFilterRoutes(@NonNull List routes) {
      int i = routes.size();

      while(i-- > 0) {
         if (!this.onFilterRoute((MediaRouter.RouteInfo)routes.get(i))) {
            routes.remove(i);
         }
      }

   }

   public boolean onFilterRoute(@NonNull MediaRouter.RouteInfo route) {
      return !route.isDefaultOrBluetooth() && route.isEnabled() && route.matchesSelector(this.mSelector);
   }

   public void setTitle(CharSequence title) {
      this.mTitleView.setText(title);
   }

   public void setTitle(int titleId) {
      this.mTitleView.setText(titleId);
   }

   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      this.setContentView(layout.mr_chooser_dialog);
      this.mRoutes = new ArrayList();
      this.mAdapter = new MediaRouteChooserDialog.RouteAdapter(this.getContext(), this.mRoutes);
      this.mListView = (ListView)this.findViewById(id.mr_chooser_list);
      this.mListView.setAdapter(this.mAdapter);
      this.mListView.setOnItemClickListener(this.mAdapter);
      this.mListView.setEmptyView(this.findViewById(16908292));
      this.mTitleView = (TextView)this.findViewById(id.mr_chooser_title);
      this.updateLayout();
   }

   void updateLayout() {
      this.getWindow().setLayout(MediaRouteDialogHelper.getDialogWidth(this.getContext()), -2);
   }

   public void onAttachedToWindow() {
      super.onAttachedToWindow();
      this.mAttachedToWindow = true;
      this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
      this.refreshRoutes();
   }

   public void onDetachedFromWindow() {
      this.mAttachedToWindow = false;
      this.mRouter.removeCallback(this.mCallback);
      this.mHandler.removeMessages(1);
      super.onDetachedFromWindow();
   }

   public void refreshRoutes() {
      if (this.mAttachedToWindow) {
         ArrayList routes = new ArrayList(this.mRouter.getRoutes());
         this.onFilterRoutes(routes);
         Collections.sort(routes, MediaRouteChooserDialog.RouteComparator.sInstance);
         if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= 300L) {
            this.updateRoutes(routes);
         } else {
            this.mHandler.removeMessages(1);
            this.mHandler.sendMessageAtTime(this.mHandler.obtainMessage(1, routes), this.mLastUpdateTime + 300L);
         }
      }

   }

   void updateRoutes(List routes) {
      this.mLastUpdateTime = SystemClock.uptimeMillis();
      this.mRoutes.clear();
      this.mRoutes.addAll(routes);
      this.mAdapter.notifyDataSetChanged();
   }

   static final class RouteComparator implements Comparator {
      public static final MediaRouteChooserDialog.RouteComparator sInstance = new MediaRouteChooserDialog.RouteComparator();

      public int compare(MediaRouter.RouteInfo lhs, MediaRouter.RouteInfo rhs) {
         return lhs.getName().compareToIgnoreCase(rhs.getName());
      }
   }

   private final class MediaRouterCallback extends MediaRouter.Callback {
      public void onRouteAdded(MediaRouter router, MediaRouter.RouteInfo info) {
         MediaRouteChooserDialog.this.refreshRoutes();
      }

      public void onRouteRemoved(MediaRouter router, MediaRouter.RouteInfo info) {
         MediaRouteChooserDialog.this.refreshRoutes();
      }

      public void onRouteChanged(MediaRouter router, MediaRouter.RouteInfo info) {
         MediaRouteChooserDialog.this.refreshRoutes();
      }

      public void onRouteSelected(MediaRouter router, MediaRouter.RouteInfo route) {
         MediaRouteChooserDialog.this.dismiss();
      }
   }

   private final class RouteAdapter extends ArrayAdapter implements OnItemClickListener {
      private final LayoutInflater mInflater;
      private final Drawable mDefaultIcon;
      private final Drawable mTvIcon;
      private final Drawable mSpeakerIcon;
      private final Drawable mSpeakerGroupIcon;

      public RouteAdapter(Context context, List routes) {
         super(context, 0, routes);
         this.mInflater = LayoutInflater.from(context);
         TypedArray styledAttributes = this.getContext().obtainStyledAttributes(new int[]{attr.mediaRouteDefaultIconDrawable, attr.mediaRouteTvIconDrawable, attr.mediaRouteSpeakerIconDrawable, attr.mediaRouteSpeakerGroupIconDrawable});
         this.mDefaultIcon = styledAttributes.getDrawable(0);
         this.mTvIcon = styledAttributes.getDrawable(1);
         this.mSpeakerIcon = styledAttributes.getDrawable(2);
         this.mSpeakerGroupIcon = styledAttributes.getDrawable(3);
         styledAttributes.recycle();
      }

      public boolean areAllItemsEnabled() {
         return false;
      }

      public boolean isEnabled(int position) {
         return ((MediaRouter.RouteInfo)this.getItem(position)).isEnabled();
      }

      public View getView(int position, View convertView, ViewGroup parent) {
         View view = convertView;
         if (convertView == null) {
            view = this.mInflater.inflate(layout.mr_chooser_list_item, parent, false);
         }

         MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)this.getItem(position);
         TextView text1 = (TextView)view.findViewById(id.mr_chooser_route_name);
         TextView text2 = (TextView)view.findViewById(id.mr_chooser_route_desc);
         text1.setText(route.getName());
         String description = route.getDescription();
         boolean isConnectedOrConnecting = route.getConnectionState() == 2 || route.getConnectionState() == 1;
         if (isConnectedOrConnecting && !TextUtils.isEmpty(description)) {
            text1.setGravity(80);
            text2.setVisibility(0);
            text2.setText(description);
         } else {
            text1.setGravity(16);
            text2.setVisibility(8);
            text2.setText("");
         }

         view.setEnabled(route.isEnabled());
         ImageView iconView = (ImageView)view.findViewById(id.mr_chooser_route_icon);
         if (iconView != null) {
            iconView.setImageDrawable(this.getIconDrawable(route));
         }

         return view;
      }

      public void onItemClick(AdapterView parent, View view, int position, long id) {
         MediaRouter.RouteInfo route = (MediaRouter.RouteInfo)this.getItem(position);
         if (route.isEnabled()) {
            route.select();
            MediaRouteChooserDialog.this.dismiss();
         }

      }

      private Drawable getIconDrawable(MediaRouter.RouteInfo route) {
         Uri iconUri = route.getIconUri();
         if (iconUri != null) {
            try {
               InputStream is = this.getContext().getContentResolver().openInputStream(iconUri);
               Drawable drawable = Drawable.createFromStream(is, (String)null);
               if (drawable != null) {
                  return drawable;
               }
            } catch (IOException var5) {
               Log.w("MediaRouteChooserDialog", "Failed to load " + iconUri, var5);
            }
         }

         return this.getDefaultIconDrawable(route);
      }

      private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo route) {
         switch(route.getDeviceType()) {
         case 1:
            return this.mTvIcon;
         case 2:
            return this.mSpeakerIcon;
         default:
            return route instanceof MediaRouter.RouteGroup ? this.mSpeakerGroupIcon : this.mDefaultIcon;
         }
      }
   }
}
