from django.conf.urls import url
from main import views

urlpatterns = [
    url('tong2/(?P<pi>[0-9]+)', views.Tong2View.as_view()),
    url('tong5/(?P<pi>[0-9]+)', views.Tong5View.as_view()),
    url('tong5/weight/(?P<pi>[0-9]+)', views.Tong5View.get_weights_by_pi),
    url('tong8/(?P<pi>[0-9]+)', views.Tong8View.as_view()),
    url('pchi/(?P<pi>[0-9]+)', views.PchiVerification.as_view()),
    url('cycle/(?P<pi>[0-9]+)', views.CycleByPi.as_view()),
    url('adl/(?P<pi>[0-9]+)', views.ADL.as_view()),
    url('info/(?P<pi>[0-9]+)/(?P<cycle>[1-3])', views.VerificationAndCycle.as_view())
]
