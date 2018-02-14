package kr.co.company.papyrus;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /* 프래그먼트 선언 */
    HomeFragment fragHome;
    WidgetFragment fragWidget;
    ValueFragment fragValue;
    MediaFragment fragMedia;
    EditFragment fragEdit;

    /* 위젯 온오프 */                //  1      2      3      4       5      6     7      8
    static boolean[] widgetChecked = {true, false, true, false, true, false, false, true};

    /*
    ** 서버로부터 받는 모든 데이터
    */
    /* 기본 정보 */
    static String patientName = "홍길동";
    static int patientAge = 20;
    static String patientGender = "남";

    /* 활용 데이터 */
    /* 감염/감염관리법 */
    static int neutrophil = 900;

    /* 영양 */
    static boolean isCycle1 = false; // 사이클 1인지 2인지
    static boolean isHighDanger = true; // 고위험인지 저위험인지
    static double bmi = 16.3; // BMI 값
    static double albumin = 3.1; // 소수 첫 째 자리까지
    static int weightChange = 2; // 체중변화 {1: "5%이상 감소", 2:"5% 이하 감소", 3: "변화없음", 4: "증가"}
    static String[] bmiAlbuminText = {"낮음", "정상", "높음"};

    /* 통증 */
    static double pchi = 0;
    static double painAvg = 0;

    /* 운동 */
    static boolean isIndependent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* 기본 세팅 */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /* 기본 세팅 끝 */

        /* 홈 프래그먼트 화면에 표시 */
        fragHome = new HomeFragment().newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_activity_main, fragHome).commit();


        //LinearLayout la = (LinearLayout)findViewById(R.id.layout_right);
        //la.setScaleX();
        /* 그래프 테스트
        // in this example, a LineChart is initialized from xml
        LineChart chart = (LineChart) findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(100, 0.0f));
        entries.add(new Entry(100, 0.0f));
        entries.add(new Entry(100, 0.0f));
        entries.add(new Entry(100, 0.0f));

        LineDataSet dataSet = new LineDataSet(entries, "Label");

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();
        */

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        /* 네비게이션 바에 환자 이름, 성별, 나이 표시 */
        TextView tv = (TextView)findViewById(R.id.nav_textview_name);
        tv.setText(patientName);
        tv = (TextView)findViewById(R.id.nav_textview_gender);
        tv.setText("(" + String.valueOf(patientGender) + ")");
        tv = (TextView)findViewById(R.id.nav_textview_age);
        tv.setText(String.valueOf(patientAge) + " 세");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            fragHome = new HomeFragment().newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_main, fragHome).commit();

        } else if (id == R.id.nav_widget) {

            fragWidget = WidgetFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_main, fragWidget).commit();

        } else if (id == R.id.nav_value) {

            fragValue = ValueFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_main, fragValue).commit();

        } else if (id == R.id.nav_media) {

            fragMedia = MediaFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_main, fragMedia).commit();

        } else if (id == R.id.nav_edit) {

            fragEdit = EditFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_activity_main, fragEdit).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
