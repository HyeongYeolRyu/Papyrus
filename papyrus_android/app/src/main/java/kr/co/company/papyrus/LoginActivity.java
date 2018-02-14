package kr.co.company.papyrus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mikephil.charting.data.Entry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import static kr.co.company.papyrus.MainActivity.patientGender;
import static kr.co.company.papyrus.MainActivity.patientName;
import static kr.co.company.papyrus.MainActivity.weightChange;

public class LoginActivity extends AppCompatActivity {

    String result = "";
    String result2 = "";
    String result3 = "";
    String result4 = "";
    String result5 = "";
    String result6 = "";
    String result7 = "";

    //탐색하고 싶은 URL
    String strUrl = "http://sbigcon05py.azurewebsites.net/main/tong5/";
    String strUrl2 = "http://sbigcon05py.azurewebsites.net/main/tong2/";
    String strUrl3 = "http://sbigcon05py.azurewebsites.net/main/tong8/";
    String strUrl4 = "http://sbigcon05py.azurewebsites.net/main/adl/";
    String strUrl5 = "http://sbigcon05py.azurewebsites.net/main/tong5/weight/";
    String strUrl6 = "http://sbigcon05py.azurewebsites.net/main/cycle/";
    String strUrl7 = "http://sbigcon05py.azurewebsites.net/main/pchi/";
    String pi = "";

    //영양
    static ArrayList<String> nutrition_labels = null;
    static ArrayList<Entry> nutrition_entries = null;
    static double weightMin = 0;
    static double weightMax = 0;

    //통증
    static ArrayList<String> pain_labels = null;
    static ArrayList<Entry> pain_entries = null;
    static ArrayList<String> hospital_date = null;
    static ArrayList<String> pain_labels_only_date = null;
    static int painMin = 0;
    static int painMax = 0;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn_search = (Button) findViewById(R.id.btn_search);

        /* SEARCH 버튼을 눌렀을 경우 */
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                /* 입력값 가져오기 */
                final EditText editName = (EditText) findViewById(R.id.patient_name);
                EditText editNum = (EditText) findViewById(R.id.patient_num);

                String name = editName.getText().toString();
                String num = editNum.getText().toString();

                if (name.equals("")) {
                    patientName = "홍길동";
                }
                else{
                    patientName = name;
                }

                if (num.equals("")) {
                    //환자 번호를 입력하지 않았을 때
                    Toast t = Toast.makeText(getApplicationContext(), "환자 번호를 입력해주세요.", Toast.LENGTH_SHORT);
                    t.show();

                } else {
                    pi = num;

                    /******************************************************************************************/
                    /***********************************         통신        **********************************/
                    /******************************************************************************************/
                /* 통신 */
                    new AsyncTask<Void, Void, Void>() {

                        ProgressDialog asyncDialog = new ProgressDialog(LoginActivity.this);

                        @Override
                        protected void onPreExecute() {
                            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            asyncDialog.setMessage("잠시만 기다려주세요...");
                            asyncDialog.setTitle("로딩중");
                            asyncDialog.show();

                            super.onPreExecute();
                        }

                        @Override
                        protected Void doInBackground(Void... voids) {
                            getJsonText();
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                            asyncDialog.dismiss();
                            Intent intent = new Intent(view.getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }.execute();

                    /******************************************************************************************/
                    /***********************************       통신 끝       **********************************/
                    /******************************************************************************************/
                }
            }
        });
    }

    /**
     * 원격으로부터 JSON형태의 문서를 받아서
     * JSON 객체를 생성한 다음에 객체에서 필요한 데이터 추출
     */
    public String getJsonText() {
        //StringBuffer sb = new StringBuffer();
        //주어진 URL 문서의 내용을 문자열로 얻음

        try {

            /*
            ******************************************
            *           1번 째 데이터
            *           데이터 : 현재체중, 알부민data, bmi data, 체중변화, 날짜, 고위험,저위험, 나이
            ******************************************
            * */
            String jsonPage = getStringFromUrl(strUrl);
            result = jsonPage;
            JSONObject jsonObject = new JSONObject(result);
            String wCh = (String) jsonObject.get("change_weight_in_month");
            if (wCh.equals("<5%감소")) {
                weightChange = 1;
            } else if (wCh.equals("변화없음")) {
                weightChange = 3;
            } else if (wCh.equals("증가")) {
                weightChange = 4;
            } else {
                weightChange = 2;
            }

            MainActivity.bmi = (double) jsonObject.get("bmi_data");
            MainActivity.albumin = (double) jsonObject.get("albumin_data");
            MainActivity.patientAge = (int) jsonObject.get("age");

            Object o = jsonObject.get("high_risk");
            if (o.toString() == "null") {
                MainActivity.isHighDanger = false;
                        } else if (((String) jsonObject.get("high_risk")).equals("V")) {
                MainActivity.isHighDanger = true;
            }

            /*
            ******************************************
            *           2번 째 데이터
            *           데이터 : 통증(Pain), 성별
            ******************************************
            * */
            String jsonPage2 = getStringFromUrl(strUrl2);
            result2 = jsonPage2;
            JSONObject jsonObject2 = new JSONObject(result2);
            String gender = (String) jsonObject2.get("sex");
            patientGender = gender;

            MainActivity.painAvg = (double) jsonObject2.get("pain_avg");

            JSONArray jsonArr = (JSONArray) jsonObject2.get("pain_list");

            pain_labels = new ArrayList<String>();
            pain_entries = new ArrayList<Entry>();
            hospital_date = new ArrayList<String>();
            pain_labels_only_date = new ArrayList<String>();

            int[] painVals = new int[jsonArr.length()];



            for (int i = 0; i < jsonArr.length(); i++) {
                String time = (String) jsonArr.getJSONObject(i).get("daytime");
                pain_labels.add(time);
                int pain = (int) jsonArr.getJSONObject(i).get("pain");
                pain_entries.add(new Entry(i, pain));
                String hospiDate = (String) jsonArr.getJSONObject(i).get("hospitalization_date");
                hospital_date.add(hospiDate);

                painVals[i] = pain;
            }
            Arrays.sort(painVals);
            painMax = painVals[painVals.length - 1];
            painMin = painVals[0];

            /*
            ******************************************
            *           3번 째 데이터
            *           데이터 : 뉴트로필,
            ******************************************
            * */
            String jsonPage3 = getStringFromUrl(strUrl3);
            result3 = jsonPage3;
            JSONObject jsonObject3 = new JSONObject(result3);
            int neu = (int) (1000 * (double) jsonObject3.get("num_output_numeric"));
            MainActivity.neutrophil = neu;

            /*
            ******************************************
            *           4번 째 데이터
            *           데이터 : ADL
            ******************************************
            * */
            String jsonPage4 = getStringFromUrl(strUrl4);
            result4 = jsonPage4;
            JSONObject jsonObject4 = new JSONObject(result4);
            String adlTmp = (String) jsonObject4.get("move");
            if (adlTmp.matches(".*독립.*")) {
                MainActivity.isIndependent = true;
            } else {
                MainActivity.isIndependent = false;
            }


            /*
            ******************************************
            *           5번 째 데이터
            *           데이터 : 체중
            ******************************************
            * */
            String jsonPage5 = getStringFromUrl(strUrl5);
            result5 = jsonPage5;
            JSONArray jsonObject5 = new JSONArray(result5);

            nutrition_labels = new ArrayList<String>();
            nutrition_entries = new ArrayList<Entry>();

            double[] weightRange = new double[jsonObject5.length()];
            for (int i = 0; i < jsonObject5.length(); i++) {
                String str = (String) jsonObject5.getJSONObject(i).get("format_date");
                nutrition_labels.add(str);
                double d = (double) jsonObject5.getJSONObject(i).get("weight");
                nutrition_entries.add(new Entry(i, (float) d));
                weightRange[i] = d;
            }
            Arrays.sort(weightRange);
            weightMax = weightRange[weightRange.length - 1];
            weightMin = weightRange[0];


            /*
            ******************************************
            *           6번 째 데이터
            *           데이터 : 사이클
            ******************************************
            * */
            String jsonPage6 = getStringFromUrl(strUrl6);
            result6 = jsonPage6;
            JSONObject jsonObject6 = new JSONObject(result6);
            int cycle = (int) jsonObject6.get("cycle");
            //int cycle = 1;
            if (cycle > 1) {
                MainActivity.isCycle1 = false;
            } else {
                MainActivity.isCycle1 = true;
            }


            /*
            ******************************************
            *           7번 째 데이터
            *           데이터 : pchi 값
            ******************************************
            * */
            String jsonPage7 = getStringFromUrl(strUrl7);
            result7 = jsonPage7;
            JSONObject jsonObject7 = new JSONObject(result7);
            MainActivity.pchi = (double) jsonObject7.get("pchi");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // getStringFromUrl : 주어진 URL의 문서의 내용을 문자열로 반환
    public String getStringFromUrl(String pUrl) {

        BufferedReader bufreader = null;
        HttpURLConnection urlConnection = null;

        StringBuffer page = new StringBuffer(); //읽어온 데이터를 저장할 StringBuffer객체 생성

        try {

            URL url = new URL(pUrl + pi);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream contentStream = urlConnection.getInputStream();

            bufreader = new BufferedReader(new InputStreamReader(contentStream, "UTF-8"));
            String line = null;

            //버퍼의 웹문서 소스를 줄단위로 읽어(line), Page에 저장함
            while ((line = bufreader.readLine()) != null) {
                page.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //자원해제
            try {
                if (bufreader != null)
                    bufreader.close();
                if (urlConnection != null)
                    urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return page.toString();
    }// getStringFromUrl()-------------------------

} // end Class