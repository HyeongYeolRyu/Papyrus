package kr.co.company.papyrus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ValueFragment extends Fragment {

    View view;

    public static ValueFragment newInstance() {
        ValueFragment fragment = new ValueFragment();
        return fragment;
    }

    public ValueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_value, container, false);

        /* 뷰 반환 */
        return view;
    }
}
