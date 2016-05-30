package framgia.vn.voanews.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by nguyen van toan on 5/26/2016.
 */
public final class CheckConnectionUtil {
    public static boolean isInternetOn(Context mContext) {
        int mobi = ConnectivityManager.TYPE_MOBILE;
        int wifi = ConnectivityManager.TYPE_WIFI;
        ConnectivityManager connec = (ConnectivityManager)
                mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connec.getNetworkInfo(mobi).getState() == NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(mobi).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(wifi).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(wifi).getState() == android.net.NetworkInfo.State.CONNECTED) {
            return true;

        } else if (
                connec.getNetworkInfo(mobi).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(wifi).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }
}