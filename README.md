Papyrus Web API
==================
* 세브란스 빅데이터 이노베이션 공모전에서 통(Tong)팀이 Papyrus라는 작품과 함께 사용한 web-api입니다.

* 본 프로젝트에서 사용한 database가 보안상 외부 접속이 불가하여 지정된 클라우드 환경 내에서만 사용이 가능합니다.

Prerequisites
---------------


1. python 설치

 본 api에서 python 3.6 버전을 사용했습니다.

  * ubuntu 16.04 LTS에서 설치
  ~~~~
  sudo add-apt-repository ppa:jonathonf/python-3.6
  sudo apt-get update
  sudo apt-get install python3.6
  ~~~~
		
  * window에서 설치
	<http://python.org/> 에서 설치

2. 리포지토리 clone

	~~~~
	git clone https://github.com/taekwan-hwang/papyrus.git
	~~~~
	
3. python 모듈 의존성 설치

	~~~~
	pip install -r requirements.txt
	~~~~

4. api 서버 실행

	~~~~
	python manage.py runserver
	~~~~
	
api 설명
---------------

	URL={your_host}/main/tong2/{환자 번호} : 환자의 마지막 입원에서의 통증과 통증 기록 시간들, 통증의 평균, 성별을 반환합니다
	URL={your_host}/main/tong5/{환자 번호} : 환자의 가장 최근의 영양 상태와 관련된 정보들을 반환합니다.
	URL={your_host}/main/tong5/weight/{환자 번호} : 환자의 몸무게 값과 측정 시간쌍들을 반환합니다
	URL={your_host}/main/tong8/{환자 번호} : 환자의 neutrophil 관련 값을 반환합니다.
	URL={your_host}/main/pchi/{환자 번호} : 환자의 통증 분산 정도를 pchisq검증한 값을 반환합니다.
	URL={your_host}/main/cycle/{환자 번호} : 환자의 입원 횟수를 반환합니다.
	URL={your_host}/main/adl/{환자 번호} : 환자의 운동 가능 정도를 반환합니다.