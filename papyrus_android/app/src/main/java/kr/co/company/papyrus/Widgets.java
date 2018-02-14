package kr.co.company.papyrus;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import static kr.co.company.papyrus.HomeFragment.view;
import static kr.co.company.papyrus.LoginActivity.nutrition_entries;
import static kr.co.company.papyrus.LoginActivity.nutrition_labels;
import static kr.co.company.papyrus.LoginActivity.pain_entries;
import static kr.co.company.papyrus.LoginActivity.pain_labels;
import static kr.co.company.papyrus.LoginActivity.pain_labels_only_date;
import static kr.co.company.papyrus.LoginActivity.weightMax;
import static kr.co.company.papyrus.LoginActivity.weightMin;
import static kr.co.company.papyrus.MainActivity.albumin;
import static kr.co.company.papyrus.MainActivity.bmi;
import static kr.co.company.papyrus.MainActivity.bmiAlbuminText;
import static kr.co.company.papyrus.MainActivity.isCycle1;
import static kr.co.company.papyrus.MainActivity.isHighDanger;
import static kr.co.company.papyrus.MainActivity.isIndependent;
import static kr.co.company.papyrus.MainActivity.neutrophil;
import static kr.co.company.papyrus.MainActivity.painAvg;
import static kr.co.company.papyrus.MainActivity.patientName;
import static kr.co.company.papyrus.MainActivity.pchi;
import static kr.co.company.papyrus.MainActivity.weightChange;

public class Widgets {

    /* 위젯1 : 감염 */
    public static void makeWidget1() {
        ImageView iv = (ImageView) view.findViewById(R.id.widget1_img);
        TextView tv = (TextView) view.findViewById(R.id.value_neutrophil);
        tv.setText(String.valueOf(neutrophil));
        /* 위험 */
        if (neutrophil < 500) {
            iv.setImageResource(R.drawable.infect_danger);
            tv.setTextColor(Color.rgb(255, 0, 0));

            /* 한 자리 수 일 때 */
            if (neutrophil < 10) {
                tv.setX(tv.getX() + 21);
            }
            /* 두 자리 수 일 때 */
            else if (neutrophil >= 10 && neutrophil < 100) {
                tv.setX(tv.getX() + 15);
            }
            /* 세 자리 수 일 때 */
            else if (neutrophil >= 100) {
                tv.setX(tv.getX() + 8);
            }

        }
        /* 주의 */
        else if (neutrophil >= 500 && neutrophil < 1500) {
            iv.setImageResource(R.drawable.infect_cautious);
            tv.setTextColor(Color.rgb(255, 192, 0));

            /* 세 자리 수 일 때 */
            if (neutrophil >= 100 && neutrophil < 1000) {
                tv.setX(tv.getX() + 8);
            }
        }
        /* 정상 */
        else if (neutrophil >= 1500 && neutrophil < 8000) {
            iv.setImageResource(R.drawable.infect_normal);
            //tv.setVisibility(View.INVISIBLE);
            tv.setTextColor(Color.rgb(0, 176, 80));
        }
        /* 의심 */
        else if (neutrophil >= 8000) {
            iv.setImageResource(R.drawable.infect_suspicious);
            tv.setTextColor(Color.rgb(255, 0, 0));

            /* 다섯 자리 수 일 때 */
            if (neutrophil >= 10000) {
                tv.setTextSize(tv.getTextSize() - 100);
                tv.setX(tv.getX() - 8);
                tv.setY(tv.getY() + 4);
            }
        }
    }

    /* 위젯3 : 감염 관리법 */
    public static void makeWidget3() {
        ImageView iv = (ImageView) view.findViewById(R.id.widget3_img);
        TextView tv = (TextView) view.findViewById(R.id.value_name);
        tv.setText(String.valueOf(patientName));
        tv.setTextColor(Color.rgb(0, 0, 0));

        /* 위험 */
        if (neutrophil < 500) {
            iv.setImageResource(R.drawable.infectcare_danger);
        }
        /* 주의 */
        else if (neutrophil >= 500 && neutrophil < 1500) {
            iv.setImageResource(R.drawable.infectcare_cautious);
            tv.setY(tv.getY() - 2);
            tv.setX(tv.getX() - 1);
        }
        /* 정상 */
        else if (neutrophil >= 1500 && neutrophil < 8000) {
            iv.setImageResource(R.drawable.infectcare_normal);

            TextView tName = (TextView)view.findViewById(R.id.value_name);
            tName.setVisibility(View.INVISIBLE);

        }
        /* 의심 */
        else if (neutrophil >= 8000) {
            iv.setImageResource(R.drawable.infectcare_suspicious);
            tv.setX(tv.getX() - 6);
            tv.setY(tv.getY() - 22);
        }
    }

    /* 영양 */
    public static void makeWidget5() {
        ImageView iv = (ImageView) view.findViewById(R.id.widget5_img);

        /* BMI 값 변화 */
        TextView tv = (TextView) view.findViewById(R.id.value_bmi);
        tv.setText(String.valueOf(bmi));
        if (18.5 <= bmi && 23.5 > bmi)
            tv.setTextColor(Color.rgb(85, 196, 143));

        /* Albumin 값 변화 */
        tv = (TextView) view.findViewById(R.id.value_albumin);
        tv.setText(String.valueOf(albumin));
        if (3.5 < albumin)
            tv.setTextColor(Color.rgb(85, 196, 143));

        tv = (TextView) view.findViewById(R.id.value_bmi_text);
        if (18.5 > bmi)
            tv.setText(bmiAlbuminText[0]);
        else if (18.5 <= bmi && 23.5 > bmi)
            tv.setText(bmiAlbuminText[1]);
        else
            tv.setText(bmiAlbuminText[2]);

        tv = (TextView) view.findViewById(R.id.value_albumin_text);
        if (3.5 > albumin)
            tv.setText(bmiAlbuminText[0]);
        else
            tv.setText(bmiAlbuminText[1]);

        //사이클1 인지
        if (isCycle1) {
            LineChart lct = (LineChart) view.findViewById(R.id.nutrition_chart);
            lct.setVisibility(View.INVISIBLE);

            //고위험
            if (isHighDanger) {
                //5% 이상 감소
                if (weightChange == 1) {
                    iv.setImageResource(R.drawable.nutrition_cycle1_high_5proup);
                }
                //5% 이하 감소
                else if (weightChange == 2) {
                    iv.setImageResource(R.drawable.nutrition_cycle1_high_5prodown);
                }
                //변화없음
                else if (weightChange == 3) {

                    //high bmi
                    if (bmi >= 18.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_high_nochange_high_bmi);
                    }

                    //low bmi
                    else if (bmi < 18.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_high_nochange_low_bmi);
                    }
                }
                //증가
                else if (weightChange == 4) {

                    if (albumin < 3.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_high_up_low_albumin);
                    } else {
                        iv.setImageResource(R.drawable.nutrition_cycle1_high_up_normal_albumin);
                    }
                }

            }

            //저위험
            else {
                //5% 이상 감소
                if (weightChange == 1) {
                    iv.setImageResource(R.drawable.nutrition_cycle1_low_5proup);
                }
                //5% 이하 감소
                else if (weightChange == 2) {
                    iv.setImageResource(R.drawable.nutrition_cycle1_low_5prodown);
                }
                //변화없음
                else if (weightChange == 3) {

                    if (bmi >= 18.5 && bmi < 23.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_low_nochange_normal_bmi);
                    } else if (bmi < 18.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_low_nochange_low_bmi);
                    } else if (bmi >= 23.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_low_nochange_high_bmi);
                    }
                }
                //증가
                else if (weightChange == 4) {

                    //알부민 비정상 bmi 는 상관없음
                    if (albumin < 3.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_low_up_low_albumin);
                    }

                    //둘 다 정상
                    else if ((bmi >= 18.5 && bmi < 23.5) && (albumin >= 3.5)) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_low_up_both);
                    }

                    //bmi 가 표준 이하
                    else if ((bmi < 18.5) && (albumin >= 3.5)) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_low_up_low_bmi);
                    }

                    //bmi 가 표준 이상
                    else if ((bmi > 23.5) && (albumin >= 3.5)) {
                        iv.setImageResource(R.drawable.nutrition_cycle1_low_up_high_bmi);
                    }
                }
            }

        }

        //사이클 2인지
        else {
            LineChart lc = (LineChart) view.findViewById(R.id.nutrition_chart);
            lc.setVisibility(View.VISIBLE);

            //고위험
            if (isHighDanger) {
                //5% 이상 감소
                if (weightChange == 1) {
                    iv.setImageResource(R.drawable.nutrition_cycle2_high_5proup);
                }
                //5% 이하 감소
                else if (weightChange == 2) {
                    iv.setImageResource(R.drawable.nutrition_cycle2_high_5prodown);
                }
                //변화없음
                else if (weightChange == 3) {
                    if (bmi < 18.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_high_nochange_low_bmi);
                    } else if (bmi >= 18.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_high_nochange_high_bmi);
                    }
                }
                //증가
                else if (weightChange == 4) {
                    if (albumin < 3.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_high_up_low_albumin);
                    } else if ((albumin >= 3.5) && (bmi < 18.5)) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_high_up_low_bmi);
                    } else if ((albumin >= 3.5) && (bmi >= 18.5)) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_high_up_high_bmi);
                    }
                }
            }

            //저위험
            else {
                //5% 이상 감소
                if (weightChange == 1) {
                    iv.setImageResource(R.drawable.nutrition_cycle2_low_5proup);
                }
                //5% 이하 감소
                else if (weightChange == 2) {
                    iv.setImageResource(R.drawable.nutrition_cycle2_low_5prodown);
                }
                //변화없음
                else if (weightChange == 3) {
                    if (bmi < 18.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_low_nochange_low_bmi);
                    } else if (bmi >= 18.5 && bmi < 23.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_low_nochange_normal_bmi);
                    } else if (bmi >= 23.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_low_nochange_high_bmi);
                    }
                }
                //증가
                else if (weightChange == 4) {
                    if (albumin < 3.5) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_low_up_low_albumin);
                    } else if ((albumin >= 3.5) && (bmi > 23.5)) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_low_up_high_bmi);
                    } else if ((albumin >= 3.5) && (bmi < 18.5)) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_low_up_low_bmi);
                    }
                    //둘 다 정상
                    else if ((albumin >= 3.5) && (bmi < 23.5 && bmi > 18.5)) {
                        iv.setImageResource(R.drawable.nutrition_cycle2_low_up_both);
                    }
                }
            }

            LineChart lineChart = (LineChart) view.findViewById(R.id.nutrition_chart);

            /* 오른쪽 y축 없애기 */
            YAxis rightAxis = lineChart.getAxisRight();
            rightAxis.setEnabled(false);

            /* 왼쪽 Y값 구간 최대한 넓게 지정 */
            YAxis leftAxis = lineChart.getAxisLeft();
            leftAxis.setAxisMinimum((float) weightMin - 1);
            leftAxis.setAxisMaximum((float) weightMax + 1);
            leftAxis.setTextSize(8f);

            /* 날짜 x축 */
            XAxis xaxis = lineChart.getXAxis();
            String[] dates = nutrition_labels.toArray(new String[nutrition_labels.size()]);
            for (int i = 1; i < dates.length - 1; i++) {
                dates[i] = "";
            }
            final String[] quarters = dates;
            IAxisValueFormatter formatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return quarters[(int) value];
                }
            };
            xaxis.setValueFormatter(formatter);
            xaxis.setTextSize(6f);
            xaxis.setDrawGridLines(false);
            xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);

            LineDataSet dataset = new LineDataSet(nutrition_entries, "체중");
            dataset.setCircleSize(3f);
            //dataset.setValueTextSize(8f);
            dataset.setDrawValues(false);
            dataset.setColor(Color.rgb(73, 50, 214));

            lineChart.getDescription().setEnabled(false);

            LineData lineData = new LineData(dataset);
            lineChart.setData(lineData);
            lineChart.animateY(500);
            lineChart.invalidate();
        }
    }

    /* 통증 */
    public static void makeWidget6() {
        ImageView iv = (ImageView) view.findViewById(R.id.widget6_img);

        // 사이클 1 일 때
        if (isCycle1) {
            LineChart lineChart = (LineChart) view.findViewById(R.id.pain_chart);
            lineChart.setVisibility(View.INVISIBLE);

            //TextView patientN = (TextView) view.findViewById(R.id.pain_patient_name);
            //patientN.setVisibility(View.INVISIBLE);
            TextView painval = (TextView) view.findViewById(R.id.pain_painval_avg);
            painval.setText(painAvg + "점");

            // 환자의 통증 평균이 5보다 클 경우 : 관리가 잘 되고 있지 않음
            if (painAvg >= 5) {
                iv.setImageResource(R.drawable.pain_cycle1_notcare);
            }
            // 환자의 통증 평균이 5보다 작을 경우 : 관리가 잘 되고 있음
            else {
                iv.setImageResource(R.drawable.pain_cycle1_wellcare);
            }
        }
        // 사이클 2 이상일 때
        else {
            //TextView patientN = (TextView) view.findViewById(R.id.pain_patient_name);
            //patientN.setText(patientName);
            TextView painval = (TextView) view.findViewById(R.id.pain_painval_avg);
            painval.setVisibility(View.INVISIBLE);

            /* 통증 관리가 잘 안 되고 있음 */
            if (pchi < 0.05) {
                iv.setImageResource(R.drawable.pain_cycle2_notcare);
            }
            /* 통증 관리가 잘 되고 있음 */
            else {
                iv.setImageResource(R.drawable.pain_cycle2_wellcare);
            }

            /* 통증 그래프 그리기 */
            LineChart lineChart = (LineChart) view.findViewById(R.id.pain_chart);

            /* 오른쪽 y축 없애기 */
            YAxis rightAxis = lineChart.getAxisRight();
            rightAxis.setEnabled(false);

            /* 왼쪽 Y값 구간 최대한 넓게 지정 */
            YAxis leftAxis = lineChart.getAxisLeft();
            leftAxis.setAxisMinimum(0);
            leftAxis.setAxisMaximum(10);
            leftAxis.setTextSize(9f);
            leftAxis.setDrawGridLines(false);

            lineChart.getAxisRight().setDrawGridLines(false);


            /* 사이클 레이블에 들어갈 데이터 중복 제거 */

            // HashSet 데이터 형태로 생성되면서 중복 제거됨
            //HashSet hs = new HashSet(hospital_date);
            // ArrayList 형태로 다시 생성
            //hospital_date = new ArrayList<String>(hs);

             //pain_labels_only_date 에는 날짜만 담김
            for (int i = 0; i < pain_labels.size(); i++) {
                String[] s = pain_labels.get(i).split("T");
                pain_labels_only_date.add(s[0]);
            }


            /* 날짜 x축 */
            XAxis xaxis = lineChart.getXAxis();
            String[] dates = pain_labels_only_date.toArray(new String[pain_labels_only_date.size()]);
            //String[] hospiDate = hospital_date.toArray(new String[hospital_date.size()]);

            //String tmp = dates[dates.length-1];

            for (int i = 0; i < dates.length; i++) {
                dates[i] = "";
            }

            //dates[0] = pain_labels_only_date.get(0);
            //dates[dates.length-1] = pain_labels_only_date.get(pain_labels_only_date.size()-1);

            TextView t = (TextView)view.findViewById(R.id.pain_date_start);
            t.setText(pain_labels_only_date.get(0));
            t= (TextView)view.findViewById(R.id.pain_date_end);
            t.setText(pain_labels_only_date.get(pain_labels_only_date.size()-1));

            //for (int i = 0 ; i < dates.length; i++)
            //   Log.e("valueTest", dates[i]);
            /*
            Date day1 = null;
            Date day2 = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 0, j = 0; i < hospiDate.length; i++) {

                try {
                    day1 = format.parse(hospiDate[i]);
                    day2 = format.parse(pain_labels_only_date.get(j));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int compare = day1.compareTo(day2);

                if (compare == 0){

                }
            */

            //dates[dates.length-10] = tmp;
            final String[] quarters = dates;
            IAxisValueFormatter formatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return quarters[(int) value];
                }
            };

            xaxis.setValueFormatter(formatter);
            xaxis.setTextSize(6f);
            xaxis.setDrawGridLines(true);
            xaxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            LineDataSet dataset = new LineDataSet(pain_entries, "통증");
            dataset.setCircleSize(3f);
            dataset.setValueTextSize(7f);
            dataset.setDrawValues(true);
            dataset.setColor(Color.rgb(73, 50, 214));

            final String[] quarters2 = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            IValueFormatter formatter1 = new IValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return quarters2[(int) value];
                }
            };

            dataset.setValueFormatter(formatter1);


            lineChart.getDescription().setEnabled(false);

            LineData lineData = new LineData(dataset);
            lineChart.setData(lineData);
            lineChart.animateY(500);
            lineChart.invalidate();
        }


    }

    /* 운동 */
    public static void makeWidget7() {
        ImageView iv = (ImageView) view.findViewById(R.id.widget7_img);
        if (isIndependent) {
            iv.setImageResource(R.drawable.exercise_independent);
        } else {
            iv.setImageResource(R.drawable.exercise_dependent);
        }
    }

    public static void makeWidget8() {

        ImageView iv = (ImageView)view.findViewById(R.id.widget8_img);

        //고위험일 때
        if (isHighDanger){
            iv.setImageResource(R.drawable.nutrition_management_high);
        }
        //저위험일 때
        else {

            //5% 이상 감소
            if (weightChange == 1){
                iv.setImageResource(R.drawable.nutrition_management_low_up);
            }

            //5% 이하 감소
            else if (weightChange == 2){
                iv.setImageResource(R.drawable.nutrition_management_low_up);
            }

            //변화없음
            else if (weightChange == 3){

                if (bmi < 18.5){
                    iv.setImageResource(R.drawable.nutrition_management_low_up);
                } else if (bmi >= 18.5 && bmi < 23.5) {
                    iv.setImageResource(R.drawable.nutrition_management_low_normal_bmi);
                } else if (bmi > 23.5) {
                    iv.setImageResource(R.drawable.nutrition_management_low_high_bmi);
                }
            }

            //증가
            else if (weightChange == 4){

                if (bmi < 18.5){
                    iv.setImageResource(R.drawable.nutrition_management_low_up);
                } else if (bmi >= 18.5 && bmi < 23.5) {
                    iv.setImageResource(R.drawable.nutrition_management_low_normal_bmi);
                } else if (bmi > 23.5) {
                    iv.setImageResource(R.drawable.nutrition_management_low_high_bmi);
                }
            }

        }
    }
}
