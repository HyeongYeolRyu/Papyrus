"""
Definition of urls for papyrus.
"""

from django.conf.urls import include, url
from main import views
# Uncomment the next two lines to enable the admin:
# from django.contrib import admin
# admin.autodiscover()

urlpatterns = [
    # Examples:
    # url(r'^$', papyrus.views.home, name='home'),
    # url(r'^papyrus/', include('papyrus.papyrus.urls')),

    # Uncomment the admin/doc line below to enable admin documentation:
    # url(r'^admin/doc/', include('django.contrib.admindocs.urls')),

    # Uncomment the next line to enable the admin:
    # url(r'^admin/', include(admin.site.urls)),
    url('main/', include('main.urls')),
]
