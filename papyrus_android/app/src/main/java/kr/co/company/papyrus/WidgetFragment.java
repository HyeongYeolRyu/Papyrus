package kr.co.company.papyrus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import static kr.co.company.papyrus.MainActivity.widgetChecked;

public class WidgetFragment extends Fragment {

    View view;

    /* 각 위젯별 체크박스 */
    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;
    CheckBox cb4;
    CheckBox cb5;
    CheckBox cb6;
    CheckBox cb7;
    CheckBox cb8;

    public static WidgetFragment newInstance() {
        WidgetFragment fragment = new WidgetFragment();
        return fragment;
    }

    public WidgetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_widget, container, false);

        /* 위젯 설정 */
        cb1 = (CheckBox) view.findViewById(R.id.checkbox_widget1);
        cb2 = (CheckBox) view.findViewById(R.id.checkbox_widget2);
        cb3 = (CheckBox) view.findViewById(R.id.checkbox_widget3);
        cb4 = (CheckBox) view.findViewById(R.id.checkbox_widget4);
        cb5 = (CheckBox) view.findViewById(R.id.checkbox_widget5);
        cb6 = (CheckBox) view.findViewById(R.id.checkbox_widget6);
        cb7 = (CheckBox) view.findViewById(R.id.checkbox_widget7);
        cb8 = (CheckBox) view.findViewById(R.id.checkbox_widget8);

        /* 위젯이 체크되거나 체크해제될 때마다 호출되어 'widgetChecked[]'의 상태를 변경해줌  */
        setWidgetListener();

        /* 위젯 체크 표시 */
        showWidgetCheck();

        /* 뷰 반환 */
        return view;
    }

    public void showWidgetCheck() {
        if (widgetChecked[0]) cb1.setChecked(true);
        else cb1.setChecked(false);
        if (widgetChecked[1]) cb2.setChecked(true);
        else cb2.setChecked(false);
        if (widgetChecked[2]) cb3.setChecked(true);
        else cb3.setChecked(false);
        if (widgetChecked[3]) cb4.setChecked(true);
        else cb4.setChecked(false);
        if (widgetChecked[4]) cb5.setChecked(true);
        else cb5.setChecked(false);
        if (widgetChecked[5]) cb6.setChecked(true);
        else cb6.setChecked(false);
        if (widgetChecked[6]) cb7.setChecked(true);
        else cb7.setChecked(false);
        if (widgetChecked[7]) cb8.setChecked(true);
        else cb8.setChecked(false);
    }

    public void setWidgetListener(){

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.checkbox_widget1){
                    if (isChecked) widgetChecked[0] = true;
                    else widgetChecked[0] = false;
                }
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.checkbox_widget2){
                    if (isChecked) widgetChecked[1] = true;
                    else widgetChecked[1] = false;
                }
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.checkbox_widget3){
                    if (isChecked) widgetChecked[2] = true;
                    else widgetChecked[2] = false;
                }
            }
        });

        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.checkbox_widget4){
                    if (isChecked) widgetChecked[3] = true;
                    else widgetChecked[3] = false;
                }
            }
        });

        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.checkbox_widget5){
                    if (isChecked) widgetChecked[4] = true;
                    else widgetChecked[4] = false;
                }
            }
        });

        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.checkbox_widget6){
                    if (isChecked) widgetChecked[5] = true;
                    else widgetChecked[5] = false;
                }
            }
        });

        cb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.checkbox_widget7){
                    if (isChecked) widgetChecked[6] = true;
                    else widgetChecked[6] = false;
                }
            }
        });

        cb8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.getId() == R.id.checkbox_widget8){
                    if (isChecked) widgetChecked[7] = true;
                    else widgetChecked[7] = false;
                }
            }
        });
    }

    public void turnOnOffWidget(int widgetNum, boolean isChecked){
        switch (widgetNum){
            case 1 :
                widgetChecked[0] = isChecked;
                break;
            case 2 :
                widgetChecked[1] = isChecked;
                break;
            case 3 :
                widgetChecked[2] = isChecked;
                break;
            case 4 :
                widgetChecked[3] = isChecked;
                break;
            case 5 :
                widgetChecked[4] = isChecked;
                break;
            case 6 :
                widgetChecked[5] = isChecked;
                break;
            case 7 :
                widgetChecked[6] = isChecked;
                break;
            case 8 :
                widgetChecked[7] = isChecked;
                break;
        }
    }
}
