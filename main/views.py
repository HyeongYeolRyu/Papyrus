from django.shortcuts import render

# Create your views here.
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from rest_framework.renderers import JSONRenderer
from main.serializers import Tong2Serializer, Tong5Serializer, Tong8Serializer, MoveSerializer
from rest_framework.decorators import api_view
from main.models import Tong2, Tong5, Tong8, Move
from main.statistics.verification import Verification
from main.statistics.cycle_divider import get_cycle_by_person
class Tong2View(APIView):
    def get(self, request, format=None, pi=0):
        objects=Tong2.objects.filter(pi=pi).filter(n_attr_codeNM='통증 강도').order_by('actual_datetime')
        list=[]
        for obj in objects:
            try:
                pain=(int)(obj.attr)
                list.append({'pain':pain,'hospitalization_date':obj.hospitalization_date,'daytime':obj.actual_datetime})
            except ValueError:
                pass
        import statistics
        pain_avg=round(statistics.mean([i['pain'] for i in list]), 1)
        if len(list)>10:
            return Response({'pain_list':list[-10:],'pain_avg':pain_avg, 'sex':objects.last().sex})
        else:
            return Response({'pain_list':list,'pain_avg':pain_avg, 'sex':objects.last().sex})

class Tong5View(APIView):
    def get(self, request, format=None, pi=0):
        tong5obj=Tong5.objects.filter(pi=pi).order_by('format_date').last()
        tong5obj.bmi_data=round(tong5obj.bmi_data, 1)
        serializer=Tong5Serializer(tong5obj)
        return Response(serializer.data)
    @api_view(['GET'])
    def get_weights_by_pi(request, pi=0):
        tong5obj=Tong5.objects.filter(pi=pi).order_by('format_date')
        list=[]
        for obj in tong5obj:
            list.append({'format_date':obj.format_date,'weight':obj.weight})
        return Response(list)
class Tong8View(APIView):
    def get(self, request, format=None, pi=0):
        serializer=Tong8Serializer(Tong8.objects.filter(pi=pi).filter(prescription_code='L100907200').order_by('sampling_time').last())
        
        return Response(serializer.data)

class PchiVerification(APIView):
    def get(self, request, pi):
        return Response({'pchi':Verification.verify_by_pchisq(pi)})

class CycleByPi(APIView):
    def get(self, request, pi):
        return Response({'cycle':get_cycle_by_person(pi)})

class ADL(APIView):
    def get(self, request, pi):
        serializer=MoveSerializer(Move.objects.filter(pi=pi).order_by('format_date').last())
        return Response(serializer.data)

class VerificationAndCycle(APIView):
    def get(self, request, pi, cycle):
        return Response({'pchi':Verification.verify_by_pchisq(pi, int(cycle)), 'cycle':get_cycle_by_person(pi)})