package android.support.v7.media;

import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MediaRouteDescriptor {
   static final String KEY_ID = "id";
   static final String KEY_GROUP_MEMBER_IDS = "groupMemberIds";
   static final String KEY_NAME = "name";
   static final String KEY_DESCRIPTION = "status";
   static final String KEY_ICON_URI = "iconUri";
   static final String KEY_ENABLED = "enabled";
   static final String KEY_CONNECTING = "connecting";
   static final String KEY_CONNECTION_STATE = "connectionState";
   static final String KEY_CONTROL_FILTERS = "controlFilters";
   static final String KEY_PLAYBACK_TYPE = "playbackType";
   static final String KEY_PLAYBACK_STREAM = "playbackStream";
   static final String KEY_DEVICE_TYPE = "deviceType";
   static final String KEY_VOLUME = "volume";
   static final String KEY_VOLUME_MAX = "volumeMax";
   static final String KEY_VOLUME_HANDLING = "volumeHandling";
   static final String KEY_PRESENTATION_DISPLAY_ID = "presentationDisplayId";
   static final String KEY_EXTRAS = "extras";
   static final String KEY_CAN_DISCONNECT = "canDisconnect";
   static final String KEY_SETTINGS_INTENT = "settingsIntent";
   static final String KEY_MIN_CLIENT_VERSION = "minClientVersion";
   static final String KEY_MAX_CLIENT_VERSION = "maxClientVersion";
   final Bundle mBundle;
   List mControlFilters;

   MediaRouteDescriptor(Bundle bundle, List controlFilters) {
      this.mBundle = bundle;
      this.mControlFilters = controlFilters;
   }

   public String getId() {
      return this.mBundle.getString("id");
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public List getGroupMemberIds() {
      return this.mBundle.getStringArrayList("groupMemberIds");
   }

   public String getName() {
      return this.mBundle.getString("name");
   }

   public String getDescription() {
      return this.mBundle.getString("status");
   }

   public Uri getIconUri() {
      String iconUri = this.mBundle.getString("iconUri");
      return iconUri == null ? null : Uri.parse(iconUri);
   }

   public boolean isEnabled() {
      return this.mBundle.getBoolean("enabled", true);
   }

   /** @deprecated */
   @Deprecated
   public boolean isConnecting() {
      return this.mBundle.getBoolean("connecting", false);
   }

   public int getConnectionState() {
      return this.mBundle.getInt("connectionState", 0);
   }

   public boolean canDisconnectAndKeepPlaying() {
      return this.mBundle.getBoolean("canDisconnect", false);
   }

   public IntentSender getSettingsActivity() {
      return (IntentSender)this.mBundle.getParcelable("settingsIntent");
   }

   public List getControlFilters() {
      this.ensureControlFilters();
      return this.mControlFilters;
   }

   void ensureControlFilters() {
      if (this.mControlFilters == null) {
         this.mControlFilters = this.mBundle.getParcelableArrayList("controlFilters");
         if (this.mControlFilters == null) {
            this.mControlFilters = Collections.emptyList();
         }
      }

   }

   public int getPlaybackType() {
      return this.mBundle.getInt("playbackType", 1);
   }

   public int getPlaybackStream() {
      return this.mBundle.getInt("playbackStream", -1);
   }

   public int getDeviceType() {
      return this.mBundle.getInt("deviceType");
   }

   public int getVolume() {
      return this.mBundle.getInt("volume");
   }

   public int getVolumeMax() {
      return this.mBundle.getInt("volumeMax");
   }

   public int getVolumeHandling() {
      return this.mBundle.getInt("volumeHandling", 0);
   }

   public int getPresentationDisplayId() {
      return this.mBundle.getInt("presentationDisplayId", -1);
   }

   public Bundle getExtras() {
      return this.mBundle.getBundle("extras");
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int getMinClientVersion() {
      return this.mBundle.getInt("minClientVersion", 1);
   }

   @RestrictTo({Scope.LIBRARY_GROUP})
   public int getMaxClientVersion() {
      return this.mBundle.getInt("maxClientVersion", Integer.MAX_VALUE);
   }

   public boolean isValid() {
      this.ensureControlFilters();
      return !TextUtils.isEmpty(this.getId()) && !TextUtils.isEmpty(this.getName()) && !this.mControlFilters.contains((Object)null);
   }

   public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("MediaRouteDescriptor{ ");
      result.append("id=").append(this.getId());
      result.append(", groupMemberIds=").append(this.getGroupMemberIds());
      result.append(", name=").append(this.getName());
      result.append(", description=").append(this.getDescription());
      result.append(", iconUri=").append(this.getIconUri());
      result.append(", isEnabled=").append(this.isEnabled());
      result.append(", isConnecting=").append(this.isConnecting());
      result.append(", connectionState=").append(this.getConnectionState());
      result.append(", controlFilters=").append(Arrays.toString(this.getControlFilters().toArray()));
      result.append(", playbackType=").append(this.getPlaybackType());
      result.append(", playbackStream=").append(this.getPlaybackStream());
      result.append(", deviceType=").append(this.getDeviceType());
      result.append(", volume=").append(this.getVolume());
      result.append(", volumeMax=").append(this.getVolumeMax());
      result.append(", volumeHandling=").append(this.getVolumeHandling());
      result.append(", presentationDisplayId=").append(this.getPresentationDisplayId());
      result.append(", extras=").append(this.getExtras());
      result.append(", isValid=").append(this.isValid());
      result.append(", minClientVersion=").append(this.getMinClientVersion());
      result.append(", maxClientVersion=").append(this.getMaxClientVersion());
      result.append(" }");
      return result.toString();
   }

   public Bundle asBundle() {
      return this.mBundle;
   }

   public static MediaRouteDescriptor fromBundle(Bundle bundle) {
      return bundle != null ? new MediaRouteDescriptor(bundle, (List)null) : null;
   }

   public static final class Builder {
      private final Bundle mBundle;
      private ArrayList mGroupMemberIds;
      private ArrayList mControlFilters;

      public Builder(String id, String name) {
         this.mBundle = new Bundle();
         this.setId(id);
         this.setName(name);
      }

      public Builder(MediaRouteDescriptor descriptor) {
         if (descriptor == null) {
            throw new IllegalArgumentException("descriptor must not be null");
         } else {
            this.mBundle = new Bundle(descriptor.mBundle);
            descriptor.ensureControlFilters();
            if (!descriptor.mControlFilters.isEmpty()) {
               this.mControlFilters = new ArrayList(descriptor.mControlFilters);
            }

         }
      }

      public MediaRouteDescriptor.Builder setId(String id) {
         this.mBundle.putString("id", id);
         return this;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public MediaRouteDescriptor.Builder addGroupMemberId(String groupMemberId) {
         if (TextUtils.isEmpty(groupMemberId)) {
            throw new IllegalArgumentException("groupMemberId must not be empty");
         } else {
            if (this.mGroupMemberIds == null) {
               this.mGroupMemberIds = new ArrayList();
            }

            if (!this.mGroupMemberIds.contains(groupMemberId)) {
               this.mGroupMemberIds.add(groupMemberId);
            }

            return this;
         }
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public MediaRouteDescriptor.Builder addGroupMemberIds(Collection groupMemberIds) {
         if (groupMemberIds == null) {
            throw new IllegalArgumentException("groupMemberIds must not be null");
         } else {
            if (!groupMemberIds.isEmpty()) {
               Iterator var2 = groupMemberIds.iterator();

               while(var2.hasNext()) {
                  String groupMemberId = (String)var2.next();
                  this.addGroupMemberId(groupMemberId);
               }
            }

            return this;
         }
      }

      public MediaRouteDescriptor.Builder setName(String name) {
         this.mBundle.putString("name", name);
         return this;
      }

      public MediaRouteDescriptor.Builder setDescription(String description) {
         this.mBundle.putString("status", description);
         return this;
      }

      public MediaRouteDescriptor.Builder setIconUri(Uri iconUri) {
         if (iconUri == null) {
            throw new IllegalArgumentException("iconUri must not be null");
         } else {
            this.mBundle.putString("iconUri", iconUri.toString());
            return this;
         }
      }

      public MediaRouteDescriptor.Builder setEnabled(boolean enabled) {
         this.mBundle.putBoolean("enabled", enabled);
         return this;
      }

      /** @deprecated */
      @Deprecated
      public MediaRouteDescriptor.Builder setConnecting(boolean connecting) {
         this.mBundle.putBoolean("connecting", connecting);
         return this;
      }

      public MediaRouteDescriptor.Builder setConnectionState(int connectionState) {
         this.mBundle.putInt("connectionState", connectionState);
         return this;
      }

      public MediaRouteDescriptor.Builder setCanDisconnect(boolean canDisconnect) {
         this.mBundle.putBoolean("canDisconnect", canDisconnect);
         return this;
      }

      public MediaRouteDescriptor.Builder setSettingsActivity(IntentSender is) {
         this.mBundle.putParcelable("settingsIntent", is);
         return this;
      }

      public MediaRouteDescriptor.Builder addControlFilter(IntentFilter filter) {
         if (filter == null) {
            throw new IllegalArgumentException("filter must not be null");
         } else {
            if (this.mControlFilters == null) {
               this.mControlFilters = new ArrayList();
            }

            if (!this.mControlFilters.contains(filter)) {
               this.mControlFilters.add(filter);
            }

            return this;
         }
      }

      public MediaRouteDescriptor.Builder addControlFilters(Collection filters) {
         if (filters == null) {
            throw new IllegalArgumentException("filters must not be null");
         } else {
            if (!filters.isEmpty()) {
               Iterator var2 = filters.iterator();

               while(var2.hasNext()) {
                  IntentFilter filter = (IntentFilter)var2.next();
                  this.addControlFilter(filter);
               }
            }

            return this;
         }
      }

      public MediaRouteDescriptor.Builder setPlaybackType(int playbackType) {
         this.mBundle.putInt("playbackType", playbackType);
         return this;
      }

      public MediaRouteDescriptor.Builder setPlaybackStream(int playbackStream) {
         this.mBundle.putInt("playbackStream", playbackStream);
         return this;
      }

      public MediaRouteDescriptor.Builder setDeviceType(int deviceType) {
         this.mBundle.putInt("deviceType", deviceType);
         return this;
      }

      public MediaRouteDescriptor.Builder setVolume(int volume) {
         this.mBundle.putInt("volume", volume);
         return this;
      }

      public MediaRouteDescriptor.Builder setVolumeMax(int volumeMax) {
         this.mBundle.putInt("volumeMax", volumeMax);
         return this;
      }

      public MediaRouteDescriptor.Builder setVolumeHandling(int volumeHandling) {
         this.mBundle.putInt("volumeHandling", volumeHandling);
         return this;
      }

      public MediaRouteDescriptor.Builder setPresentationDisplayId(int presentationDisplayId) {
         this.mBundle.putInt("presentationDisplayId", presentationDisplayId);
         return this;
      }

      public MediaRouteDescriptor.Builder setExtras(Bundle extras) {
         this.mBundle.putBundle("extras", extras);
         return this;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public MediaRouteDescriptor.Builder setMinClientVersion(int minVersion) {
         this.mBundle.putInt("minClientVersion", minVersion);
         return this;
      }

      @RestrictTo({Scope.LIBRARY_GROUP})
      public MediaRouteDescriptor.Builder setMaxClientVersion(int maxVersion) {
         this.mBundle.putInt("maxClientVersion", maxVersion);
         return this;
      }

      public MediaRouteDescriptor build() {
         if (this.mControlFilters != null) {
            this.mBundle.putParcelableArrayList("controlFilters", this.mControlFilters);
         }

         if (this.mGroupMemberIds != null) {
            this.mBundle.putStringArrayList("groupMemberIds", this.mGroupMemberIds);
         }

         return new MediaRouteDescriptor(this.mBundle, this.mControlFilters);
      }
   }
}
