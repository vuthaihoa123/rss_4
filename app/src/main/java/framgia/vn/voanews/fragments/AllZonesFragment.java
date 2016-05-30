package framgia.vn.voanews.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import framgia.vn.voanews.R;

/**
 * Created by toannguyen201194 on 23/05/2016.
 */
public class AllZonesFragment extends Fragment {
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_allzones, container, false);
        return view;
    }
}
