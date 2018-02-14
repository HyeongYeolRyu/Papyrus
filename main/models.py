from django.db import models

# Create your models here.
'''
class tong6(models.Model):
    reg_num=models.BigIntegerField(db_column='등록번호', primary_key=True)
    date=models.DateField(db_column='기록실시일자')
    datetime=models.DateTimeField(db_column='기록실시일시')
    look_code=models.CharField(max_length=20, db_column='임상관찰코드')
    look_codeNM=models.CharField(max_length=50, db_column='임상관찰코드NM')
    source=models.CharField(max_length=50, db_column='측정값_Source')
    numeric=models.FloatField(db_column='측정값_Numeric')
    
    class Meta:
        db_table = 'TB_TONG_6'
        managed=False
'''
class Final2(models.Model):
    reg_num=models.BigIntegerField(db_column='등록번호', primary_key=True)
    hp_day=models.DateField(db_column='입원일자')
    hl=models.CharField(max_length=5, db_column='HL')

    class Meta:
        db_table='final2'
        managed=False

class Hpday(models.Model):
    pi=models.BigIntegerField(db_column='PI', primary_key=True)
    hpday2=models.DateField(db_column='Hpday2')

    class Meta:
        db_table='Hpday'
        managed=False

class Test(models.Model):
    pi=models.BigIntegerField(db_column='등록번호', primary_key=True)
    hpday=models.DateField(db_column='입원일자')
    sex=models.CharField(max_length=1, db_column='성별')
    age=models.IntegerField(db_column='나이')
    height=models.FloatField(db_column='신장')
    weight=models.FloatField(db_column='현재체중')
    bmi_data=models.FloatField(db_column='BMI_data')
    adl=models.CharField(db_column='ADL', max_length=50)
    albumin_data=models.FloatField(db_column='Albumin_data')
    neutrophil=models.FloatField(db_column='Neutrophil')
    crp=models.FloatField(db_column='CRP')
    plt=models.FloatField(db_column='PLT')
    hg=models.FloatField(db_column='HG')
    ht=models.FloatField(db_column='HT')
    tbc=models.FloatField(db_column='TBC')
    dm=models.FloatField(db_column='DM')
    hp=models.FloatField(db_column='HP')
    d1=models.FloatField(db_column='D1')
    d2=models.FloatField(db_column='D2')
    d3=models.FloatField(db_column='D3')
    d4=models.FloatField(db_column='D4')
    d5=models.FloatField(db_column='D5')
    d6=models.FloatField(db_column='D6')
    d7=models.FloatField(db_column='D7')
    d8=models.FloatField(db_column='D8')
    d9=models.FloatField(db_column='D9')
    d10=models.FloatField(db_column='D10')
    y=models.CharField(max_length=1, db_column='y')
    isSmoking=models.CharField(max_length=1, db_column='흡연여부')
    isDrinking=models.CharField(max_length=1, db_column='음주여부')
    exercise_hour=models.CharField(max_length=1, db_column='운동시간')
    exercise_num=models.CharField(max_length=1, db_column='운동횟수')
    pain_score=models.FloatField(db_column='통증점수')
    weight_variant=models.CharField(max_length=1, db_column='체중변화')
    class Meta:
        managed=False
        db_table='TEST'

class Time(models.Model):
    pi=models.BigIntegerField(primary_key=True, db_column='PI')
    hpday=models.DateField(db_column='Hpday')#hp_day
    hptime=models.DateTimeField(db_column='HPtime')
    pain=models.CharField(max_length=200, db_column='Pain')
    class Meta:
        managed=False
        db_table='Time'

class Tong2(models.Model):
    pi=models.BigIntegerField(primary_key=True, db_column='등록번호')
    sex=models.CharField(max_length=1, db_column='성별')
    hospitalization_route=models.CharField(max_length=2, db_column='입원경로')
    hospitalization_ward=models.CharField(max_length=6, db_column='입원병동')
    hospitalization_date=models.DateField(db_column='입원일자')
    inpatient_department=models.CharField(max_length=6, db_column='입원진료과')
    inpatient_doctor=models.CharField(max_length=8, db_column='입원주치의')
    discharge_ward=models.CharField(max_length=6, db_column='퇴원병동')
    discharge_date=models.DateField(db_column='퇴원일자')
    discharge_department=models.CharField(max_length=6, db_column='퇴원진료과')
    discharge_doctor=models.CharField(max_length=8, db_column='퇴원주치의')
    discharge_type=models.CharField(max_length=2, db_column='퇴원유형')
    treatment_output=models.CharField(max_length=8,db_column='치료결과')
    days_in_hospital=models.IntegerField(db_column='재원일수')
    age_in_record=models.IntegerField(db_column='기록당시나이(년)')
    actual_date=models.DateField(db_column='실시일자')
    actual_datetime=models.DateTimeField(db_column='실행일시')
    nd_protocol=models.CharField(max_length=10,db_column='간호진단/프로토콜')
    nd_protocolNM=models.CharField(max_length=100,db_column='간호진단/프로토콜NM')
    ni=models.CharField(max_length=10, db_column='간호중재')
    niNM=models.CharField(max_length=100, db_column='간호중재NM')
    na=models.CharField(max_length=10, db_column='간호활동')
    naNM=models.CharField(max_length=100, db_column='간호활동NM')
    n_attr_name=models.CharField(max_length=100, db_column='간호속성명칭')
    n_attr_code=models.CharField(max_length=8, db_column='간호속성코드')
    n_attr_codeNM=models.CharField(max_length=100, db_column='간호속성코드NM')
    attr=models.CharField(max_length=200, db_column='속성')
    n_attr_key=models.IntegerField(db_column='간호속성Key')
    n_attr_key_rank=models.IntegerField(db_column='간호속성KeY의Rank')
    record_order=models.IntegerField(db_column='기록조회순서')
    record_ward=models.CharField(max_length=6, db_column='기록당시병동')
    writing_date=models.DateField(db_column='작성일자')
    writing_section=models.CharField(max_length=6, db_column='작성과')
    writing_sectionNM=models.CharField(max_length=100, db_column='작성과NM')
    writer=models.CharField(max_length=8, db_column='작성자')
    duty=models.IntegerField(db_column='Duty')
    dutyNM=models.CharField(max_length=20, db_column='DutyNM')
    hospitalization_routeNM=models.CharField(max_length=20, db_column='입원경로NM')
    hospitalization_wardNM=models.CharField(max_length=100, db_column='입원병동NM')
    inpatient_departmentNM=models.CharField(max_length=100, db_column='입원진료과NM')
    inpatient_doctorNM=models.CharField(max_length=100, db_column='입원주치의NM')
    discharge_wardNM=models.CharField(max_length=100, db_column='퇴원병동NM')
    discharge_departmentNM=models.CharField(max_length=100,db_column='퇴원진료과NM')
    discharge_doctorNM=models.CharField(max_length=100, db_column='퇴원주치의NM')
    discharge_typeNM=models.CharField(max_length=100, db_column='퇴원유형NM')
    treatment_outputNM=models.CharField(max_length=100,db_column='치료결과NM')
    cause_of_disease=models.CharField(max_length=1, db_column='전체주상병여부')
    term_key=models.CharField(max_length=20, db_column='용어Key')
    term_keyNM=models.CharField(max_length=250, db_column='용어KeyNM')
    d_code=models.CharField(max_length=10, db_column='진단코드(ICD10Cd)')
    class Meta:
        managed=False
        db_table='TB_TONG_2'

class Tong5(models.Model):
    pi=models.BigIntegerField(primary_key=True, db_column='등록번호')
    format_code=models.CharField(max_length=20, db_column='서식코드')
    format_date=models.DateField(db_column='서식작성일자')
    change_food_quantity_in_week=models.CharField(max_length=50, db_column='최근 1주일간 식사량 변화')
    format_form_code=models.CharField(max_length=8, db_column='서식폼코드')
    format_diagnosis_num=models.CharField(max_length=20, db_column='서식지진료번호(Row)')
    age=models.IntegerField(db_column='나이')
    diagnosis_name=models.CharField(max_length=100, db_column='진단명')
    food=models.CharField(max_length=100, db_column='현재 식사처방')
    height=models.FloatField(db_column='신장')
    weight=models.FloatField(db_column='현재체중')
    standard_weight=models.FloatField(db_column='표준체중')
    high_risk=models.CharField(max_length=1, db_column='고위험군')
    low_risk=models.CharField(max_length=1, db_column='저위험군')
    albumin_data=models.FloatField(db_column='Albumin data')
    albumin=models.CharField(max_length=20, db_column='Albumin')
    bmi_data=models.FloatField(db_column='BMI data')
    bmi=models.CharField(max_length=20, db_column='BMI')
    change_weight_in_month=models.CharField(max_length=20, db_column='1개월간 체중 변화')
    class Meta:
        managed=False
        db_table='TB_TONG_5'

class Tong8(models.Model):
    pi=models.BigIntegerField(primary_key=True, db_column='등록번호')
    sampling_date=models.DateField(db_column='검체채취일자')
    sampling_time=models.TimeField(db_column='검체채취시분초')
    output_date=models.DateField(db_column='결과일자')
    output_time=models.TimeField(db_column='결과시간')
    group_prescription_code=models.CharField(max_length=14, db_column='그룹처방코드')
    group_prescription_codeNM=models.CharField(max_length=250, db_column='그룹처방코드NM')
    prescription_code=models.CharField(max_length=14, db_column='처방코드')
    prescription_codeNM=models.CharField(max_length=250, db_column='처방코드NM')
    num_output_source=models.CharField(max_length=20, db_column='수치결과_Source')
    num_output_numeric=models.FloatField(db_column='수치결과_Numeric')
    prescription_num=models.BigIntegerField(db_column='처방일련번호')
    nc=models.CharField(max_length=1, db_column='nc')
    class Meta:
        managed=False
        db_table='TB_TONG_8'

class VarianceInCycle(models.Model):
    pi=models.BigIntegerField(db_column='PI', primary_key=True)
    var_cycle_1=models.FloatField(db_column='var_cycle_1')
    var_cycle_2=models.FloatField(db_column='var_cycle_2')
    var_cycle_3=models.FloatField(db_column='var_cycle_3')
    class Meta:
        managed=False
        db_table='Var_Table'

class Move(models.Model):
    pi=models.BigIntegerField(db_column='등록번호', primary_key=True)
    format_date=models.DateField(db_column='서식작성일자')
    move=models.CharField(max_length=100,db_column='이동 하기')
    class Meta:
        managed=False
        db_table='Move'