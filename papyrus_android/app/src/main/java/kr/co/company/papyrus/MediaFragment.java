package kr.co.company.papyrus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MediaFragment extends Fragment {

    View view;

    public static MediaFragment newInstance() {
        MediaFragment fragment = new MediaFragment();
        return fragment;
    }

    public MediaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_media, container, false);

        /* 뷰 반환 */
        return view;
    }
}
