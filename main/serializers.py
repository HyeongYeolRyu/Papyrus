from rest_framework import serializers
from main.models import Test, Time, Tong2, Tong5, Tong8, Move
#from main.models import tong6

class Tong2Serializer(serializers.ModelSerializer):
    class Meta:
        model=Tong2
        fields='__all__'

class Tong5Serializer(serializers.ModelSerializer):
    class Meta:
        model=Tong5
        fields='__all__'

class Tong8Serializer(serializers.ModelSerializer):
    class Meta:
        model=Tong8
        fields='__all__'

class MoveSerializer(serializers.ModelSerializer):
    class Meta:
        model=Move
        fields=('move',)