package kr.co.company.papyrus;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.LineChart;

import static kr.co.company.papyrus.MainActivity.patientGender;
import static kr.co.company.papyrus.MainActivity.widgetChecked;

public class HomeFragment extends Fragment {

    /* 메인 뷰 */
    static View view;

    /* 프레임 레이아웃 변수들
    FrameLayout fl1;
    FrameLayout fl2;
    FrameLayout fl3;
    FrameLayout fl4;
    */

    /* 프레임 레이아웃에 X 버튼들 */
    Button offBtn1;
    Button offBtn2;
    Button offBtn3;
    Button offBtn4;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        /* 프레임 레이아웃 변수 값 설정 */
        FrameLayout fl1 = (FrameLayout)view.findViewById(R.id.framelayout_1);
        FrameLayout fl2 = (FrameLayout)view.findViewById(R.id.framelayout_2);
        FrameLayout fl3 = (FrameLayout)view.findViewById(R.id.framelayout_3);
        FrameLayout fl4 = (FrameLayout)view.findViewById(R.id.framelayout_4);
        /* 프레임 레이아웃에 클릭 이벤트 부착 */
        setClickListener(fl1, 1);
        setClickListener(fl2, 2);
        setClickListener(fl3, 3);
        setClickListener(fl4, 4);

        /* 프레임 레이아웃의 X 버튼 변수 값 설정 */
        offBtn1 = (Button)view.findViewById(R.id.button_frameOff_1);
        offBtn2 = (Button)view.findViewById(R.id.button_frameOff_2);
        offBtn3 = (Button)view.findViewById(R.id.button_frameOff_3);
        offBtn4 = (Button)view.findViewById(R.id.button_frameOff_4);
        /* 버튼들에 클릭 이벤트 부착 */
        setExitClickListener(offBtn1, 1);
        setExitClickListener(offBtn2, 2);
        setExitClickListener(offBtn3, 3);
        setExitClickListener(offBtn4, 4);

        /* 위젯 체크 설정에 따라 홈 화면에 위젯들을 표시해줌 */
        showWidget();

        /* 뷰 반환 */

        /* 성별에 따라 환자 이미지 다르게 */
        if (patientGender.equals("F") || patientGender.equals("여")) {
            patientGender = "여";
            ImageView iv = (ImageView) view.findViewById(R.id.imageview_person);
            iv.setImageResource(R.drawable.human3);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        else{
            patientGender = "남";
            ImageView iv = (ImageView) view.findViewById(R.id.imageview_person);
            iv.setImageResource(R.drawable.human2);
            iv.setScaleType(ImageView.ScaleType.CENTER);

        }

        return view;
    }

    public void showWidget(){

        /* blankFrame1 에 해당 */
        // 둘 중 하나라도 true 일 경우 blankFrame 은 사라짐
        if (widgetChecked[0] || widgetChecked[1]){

            /* 기존의 blankFrame 은 안 보이게 */
            ImageView imv = (ImageView)view.findViewById(R.id.blankFrame1);
            imv.setVisibility(View.INVISIBLE);
            /* 프레임 레이아웃은 보이게 */
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_1);
            fl.setVisibility(View.VISIBLE);

            /* 위젯1이 체크된 경우 (위젯1이 보임) */
            if (widgetChecked[0] == true){
                /* 위젯1은 보이게 */
                FrameLayout fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget1);
                fl2.setVisibility(View.VISIBLE);
                /* 위젯2은 안 보이게 */
                fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget2);
                fl2.setVisibility(View.INVISIBLE);

                Widgets.makeWidget1();
            }
            /* 위젯2이 체크된 경우 (위젯2이 보임) */
            else{
                 /* 위젯2은 보이게 */
                FrameLayout fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget2);
                fl2.setVisibility(View.VISIBLE);
                /* 위젯1은 안 보이게 */
                fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget1);
                fl2.setVisibility(View.INVISIBLE);
            }
        }
        //둘 다 false 일 경우 다시 blankFrame 이 보임
        else if ( !(widgetChecked[0] && widgetChecked[1]) ){
            /* 기존의 blankFrame 은 보이게 */
            ImageView imv = (ImageView)view.findViewById(R.id.blankFrame1);
            imv.setVisibility(View.VISIBLE);
            /* 프레임 레이아웃은 안 보이게 */
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_1);
            fl.setVisibility(View.INVISIBLE);
        }


        /* blankFrame2 에 해당 */
        // 둘 중 하나라도 true 일 경우 blankFrame 은 사라짐
        if (widgetChecked[2] || widgetChecked[3]){
            ImageView imv = (ImageView)view.findViewById(R.id.blankFrame2);
            imv.setVisibility(View.INVISIBLE);

             /* 프레임 레이아웃은 보이게 */
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_2);
            fl.setVisibility(View.VISIBLE);

            /* 위젯3이 체크된 경우 (위젯3이 보임) */
            if (widgetChecked[2] == true){
                /* 위젯3은 보이게 */
                FrameLayout fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget3);
                fl2.setVisibility(View.VISIBLE);
                /* 위젯4은 안 보이게 */
                fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget4);
                fl2.setVisibility(View.INVISIBLE);

                Widgets.makeWidget3();
            }
            /* 위젯4가 체크된 경우 (위젯4가 보임) */
            else{
                 /* 위젯4는 보이게 */
                FrameLayout fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget4);
                fl2.setVisibility(View.VISIBLE);
                /* 위젯3은 안 보이게 */
                fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget3);
                fl2.setVisibility(View.INVISIBLE);
            }
        }
        //둘 다 false 일 경우 다시 blankFrame 이 보임
        else if ( !(widgetChecked[2] && widgetChecked[3]) ){
            ImageView imv = (ImageView)view.findViewById(R.id.blankFrame2);
            imv.setVisibility(View.VISIBLE);

            /* 프레임 레이아웃은 안 보이게 */
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_2);
            fl.setVisibility(View.INVISIBLE);
        }


        /* blankFrame3 에 해당 */
        // 둘 중 하나라도 true 일 경우 blankFrame 은 사라짐
        if (widgetChecked[4] || widgetChecked[5]){
            ImageView imv = (ImageView)view.findViewById(R.id.blankFrame3);
            imv.setVisibility(View.INVISIBLE);

             /* 프레임 레이아웃은 보이게 */
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_3);
            fl.setVisibility(View.VISIBLE);

            /* 위젯5가 체크된 경우 (위젯5가 보임) */
            if (widgetChecked[4] == true){
                /* 위젯5는 보이게 */
                FrameLayout fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget5);
                fl2.setVisibility(View.VISIBLE);
                /* 위젯6은 안 보이게 */
                fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget6);
                fl2.setVisibility(View.INVISIBLE);

                Widgets.makeWidget5();
            }
            /* 위젯6이 체크된 경우 (위젯6이 보임) */
            else{
                 /* 위젯6은 보이게 */
                FrameLayout fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget6);
                fl2.setVisibility(View.VISIBLE);
                /* 위젯5는 안 보이게 */
                fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget5);
                fl2.setVisibility(View.INVISIBLE);

                Widgets.makeWidget6();
            }
        }
        //둘 다 false 일 경우 다시 blankFrame 이 보임
        else if ( !(widgetChecked[4] && widgetChecked[5]) ){
            ImageView imv = (ImageView)view.findViewById(R.id.blankFrame3);
            imv.setVisibility(View.VISIBLE);

             /* 프레임 레이아웃은 안 보이게 */
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_3);
            fl.setVisibility(View.INVISIBLE);
        }


        /* blankFrame4 에 해당 */
        // 둘 중 하나라도 true 일 경우 blankFrame 은 사라짐
        if (widgetChecked[6] || widgetChecked[7]){
            ImageView imv = (ImageView)view.findViewById(R.id.blankFrame4);
            imv.setVisibility(View.INVISIBLE);

              /* 프레임 레이아웃은 보이게 */
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_4);
            fl.setVisibility(View.VISIBLE);

            /* 위젯7이 체크된 경우 (위젯7이 보임) */
            if (widgetChecked[6] == true){
                /* 위젯7은 보이게 */
                FrameLayout fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget7);
                fl2.setVisibility(View.VISIBLE);
                /* 위젯8은 안 보이게 */
                fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget8);
                fl2.setVisibility(View.INVISIBLE);

                Widgets.makeWidget7();
            }
            /* 위젯8이 체크된 경우 (위젯8이 보임) */
            else{
                 /* 위젯8은 보이게 */
                FrameLayout fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget8);
                fl2.setVisibility(View.VISIBLE);
                /* 위젯7은 안 보이게 */
                fl2 = (FrameLayout) view.findViewById(R.id.framelayout_widget7);
                fl2.setVisibility(View.INVISIBLE);

                Widgets.makeWidget8();
            }
        }
        //둘 다 false 일 경우 다시 blankFrame 이 보임
        else if ( !(widgetChecked[6] && widgetChecked[7]) ){
            ImageView imv = (ImageView)view.findViewById(R.id.blankFrame4);
            imv.setVisibility(View.VISIBLE);

                /* 프레임 레이아웃은 안 보이게 */
            FrameLayout fl = (FrameLayout) view.findViewById(R.id.framelayout_4);
            fl.setVisibility(View.INVISIBLE);
        }
    }


    /* 프레임 레이아웃을 클릭할 경우 해당 프레임 레이아웃이 가운데로 위치하면서 가로,세로 사이즈가 2배 증가함.
     * 해당 프레임 레이아웃 외, 배경은 어두워짐 */
    public void setClickListener(final FrameLayout fl, int n){

        switch (n){
            /* 1번 프레임 레이아웃 일 경우 */
            case 1 :
                fl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FrameLayout fl1 = (FrameLayout)view.findViewById(R.id.framelayout_1);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fl1.getLayoutParams();
                        params.gravity = Gravity.CENTER;
                        fl.setLayoutParams(params);
                        fl.setScaleX(2.0f);
                        fl.setScaleY(2.0f);

                        /* 프레임 레이아웃을 제일 앞으로 가져옴 */
                        fl.bringToFront();

                        /* EXIT 버튼을 보이게 함 */
                        Button btn = (Button)view.findViewById(R.id.button_frameOff_1);
                        btn.setVisibility(View.VISIBLE);

                        /* 배경을 어둡게 만들고 화면 중앙에 위치한 프레임 레이아웃만 밝게 함 */
                        setBackgroundDarker(1);
                        //setAllFrameDarker();
                        fl.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    }
                });

                break;

            /* 2번 프레임 레이아웃 일 경우 */
            case 2 :
                fl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FrameLayout fl2 = (FrameLayout)view.findViewById(R.id.framelayout_2);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fl2.getLayoutParams();
                        params.gravity = Gravity.CENTER;
                        fl.setLayoutParams(params);
                        fl.setScaleX(2.0f);
                        fl.setScaleY(2.0f);

                        /* 프레임 레이아웃을 제일 앞으로 가져옴 */
                        fl.bringToFront();

                        /* EXIT 버튼을 보이게 함 */
                        Button btn = (Button)view.findViewById(R.id.button_frameOff_2);
                        btn.setVisibility(View.VISIBLE);

                        /* 배경을 어둡게 만들고 화면 중앙에 위치한 프레임 레이아웃만 밝게 함 */
                        setBackgroundDarker(2);
                        //setAllFrameDarker();
                        fl.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    }
                });

                break;

            /* 3번 프레임 레이아웃 일 경우 */
            case 3 :
                fl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FrameLayout fl3 = (FrameLayout)view.findViewById(R.id.framelayout_3);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fl3.getLayoutParams();
                        params.gravity = Gravity.CENTER;
                        fl.setLayoutParams(params);
                        fl.setScaleX(2.0f);
                        fl.setScaleY(2.0f);

                        /* 프레임 레이아웃을 제일 앞으로 가져옴 */
                        fl.bringToFront();

                        /* EXIT 버튼을 보이게 함 */
                        Button btn = (Button)view.findViewById(R.id.button_frameOff_3);
                        btn.setVisibility(View.VISIBLE);

                        /* 배경을 어둡게 만들고 화면 중앙에 위치한 프레임 레이아웃만 밝게 함 */
                        setBackgroundDarker(3);
                        //setAllFrameDarker();
                        fl.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    }
                });

                break;

            /* 4번 프레임 레이아웃 일 경우 */
            case 4 :
                fl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FrameLayout fl4 = (FrameLayout)view.findViewById(R.id.framelayout_4);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fl4.getLayoutParams();
                        params.gravity = Gravity.CENTER;
                        fl.setLayoutParams(params);
                        fl.setScaleX(2.0f);
                        fl.setScaleY(2.0f);

                        /* 프레임 레이아웃을 제일 앞으로 가져옴 */
                        fl.bringToFront();

                        /* EXIT 버튼을 보이게 함 */
                        Button btn = (Button)view.findViewById(R.id.button_frameOff_4);
                        btn.setVisibility(View.VISIBLE);

                        /* 배경을 어둡게 만들고 화면 중앙에 위치한 프레임 레이아웃만 밝게 함 */
                        setBackgroundDarker(4);
                        //setAllFrameDarker();
                        fl.setBackgroundColor(Color.argb(255, 255, 255, 255));
                    }
                });

                break;
        }

        /* 프레임 레이아웃을 제일 앞으로 보냄 */

    }

    public void setBackgroundDarker(int fl){

        FrameLayout wholeFrame = (FrameLayout)view.findViewById(R.id.fl_fragment_home);
        wholeFrame.setBackgroundColor(Color.argb(77, 0, 0, 0));

        if (fl != 3){
             /* 통증 색 어둡게 */
            LineChart lineChart = (LineChart)view.findViewById(R.id.pain_chart);
            lineChart.setBackgroundColor(Color.argb(77, 0, 0, 0));
        }
    }

    public void setBackBackgroundColor(){

        FrameLayout wholeFrame = (FrameLayout)view.findViewById(R.id.fl_fragment_home);
        wholeFrame.setBackgroundColor(Color.argb(0, 0, 0, 0));

        /* 통증 색 다시 돌려줌 */
        LineChart lineChart = (LineChart)view.findViewById(R.id.pain_chart);
        lineChart.setBackgroundColor(Color.argb(0, 0, 0, 0));
    }

    public void setAllFrameDarker(){

          /* X 버튼을 눌러서 해당 프레임 레이아웃을 끄면 다시 본래 색 설정으로 돌아감 */
        FrameLayout flTmp = (FrameLayout)view.findViewById(R.id.framelayout_1);
        flTmp.setBackgroundColor(Color.argb(77, 0, 0, 0));
        flTmp = (FrameLayout)view.findViewById(R.id.framelayout_2);
        flTmp.setBackgroundColor(Color.argb(77, 0, 0, 0));
        flTmp = (FrameLayout)view.findViewById(R.id.framelayout_3);
        flTmp.setBackgroundColor(Color.argb(77, 0, 0, 0));
        flTmp = (FrameLayout)view.findViewById(R.id.framelayout_4);
        flTmp.setBackgroundColor(Color.argb(77, 0, 0, 0));

    }

    public void setBackAllFrameLayout(){

        /* 프레임 레이아웃을 다시 본래 색으로 설정 */
        FrameLayout flTmp = (FrameLayout)view.findViewById(R.id.framelayout_1);
        flTmp.setBackgroundColor(Color.argb(0, 0, 0, 0));
        flTmp = (FrameLayout)view.findViewById(R.id.framelayout_2);
        flTmp.setBackgroundColor(Color.argb(0, 0, 0, 0));
        flTmp = (FrameLayout)view.findViewById(R.id.framelayout_3);
        flTmp.setBackgroundColor(Color.argb(0, 0, 0, 0));
        flTmp = (FrameLayout)view.findViewById(R.id.framelayout_4);
        flTmp.setBackgroundColor(Color.argb(0, 0, 0, 0));

    }

    public void setExitClickListener(Button btn, int n){
        switch (n){
            case 1:
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FrameLayout fl1 = (FrameLayout)view.findViewById(R.id.framelayout_1);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fl1.getLayoutParams();
                        params.gravity = Gravity.LEFT | Gravity.TOP ;
                        fl1.setLayoutParams(params);
                        fl1.setScaleX(1.0f);
                        fl1.setScaleY(1.0f);

                         /* 다시 기존의 가운데 위치한 이미지를 제일 앞으로 가져옴 */
                        FrameLayout fl = (FrameLayout)view.findViewById(R.id.framelayout_img);
                        fl.bringToFront();

                        /* EXIT 버튼을 안 보이게 함 */
                        Button btn = (Button)view.findViewById(R.id.button_frameOff_1);
                        btn.setVisibility(View.INVISIBLE);

                        setBackBackgroundColor();

                        setBackAllFrameLayout();
                    }
                });
                break;

            case 2:
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FrameLayout fl2 = (FrameLayout)view.findViewById(R.id.framelayout_2);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fl2.getLayoutParams();
                        params.gravity = Gravity.LEFT | Gravity.BOTTOM ;
                        fl2.setLayoutParams(params);
                        fl2.setScaleX(1.0f);
                        fl2.setScaleY(1.0f);

                         /* 다시 기존의 가운데 위치한 이미지를 제일 앞으로 가져옴 */
                        FrameLayout fl = (FrameLayout)view.findViewById(R.id.framelayout_img);
                        fl.bringToFront();

                        /* EXIT 버튼을 안 보이게 함 */
                        Button btn = (Button)view.findViewById(R.id.button_frameOff_2);
                        btn.setVisibility(View.INVISIBLE);

                        setBackBackgroundColor();

                        setBackAllFrameLayout();
                    }
                });
                break;

            case 3:
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FrameLayout fl3 = (FrameLayout)view.findViewById(R.id.framelayout_3);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fl3.getLayoutParams();
                        params.gravity = Gravity.RIGHT | Gravity.TOP ;
                        fl3.setLayoutParams(params);
                        fl3.setScaleX(1.0f);
                        fl3.setScaleY(1.0f);

                         /* 다시 기존의 가운데 위치한 이미지를 제일 앞으로 가져옴 */
                        FrameLayout fl = (FrameLayout)view.findViewById(R.id.framelayout_img);
                        fl.bringToFront();

                        /* EXIT 버튼을 안 보이게 함 */
                        Button btn = (Button)view.findViewById(R.id.button_frameOff_3);
                        btn.setVisibility(View.INVISIBLE);

                        setBackBackgroundColor();

                        setBackAllFrameLayout();
                    }
                });
                break;

            case 4:
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FrameLayout fl4 = (FrameLayout)view.findViewById(R.id.framelayout_4);
                        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) fl4.getLayoutParams();
                        params.gravity = Gravity.RIGHT | Gravity.BOTTOM ;
                        fl4.setLayoutParams(params);
                        fl4.setScaleX(1.0f);
                        fl4.setScaleY(1.0f);

                         /* 다시 기존의 가운데 위치한 이미지를 제일 앞으로 가져옴 */
                        FrameLayout fl = (FrameLayout)view.findViewById(R.id.framelayout_img);
                        fl.bringToFront();

                        /* EXIT 버튼을 안 보이게 함 */
                        Button btn = (Button)view.findViewById(R.id.button_frameOff_4);
                        btn.setVisibility(View.INVISIBLE);

                        setBackBackgroundColor();

                        setBackAllFrameLayout();
                    }
                });
                break;
        }
    }
}