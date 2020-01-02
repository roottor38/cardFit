# cardFit
EncoreProjecct
===============
## 프로젝트 소개
본인 카드를 검색하여 현재 나의 카드의 혜택을 확인 할 수 있다.
본인이 필요한 해텍을 선택하여 카드를 추천 받을 수 있다.

## 요구사항
 - 카드 검색
 - 혜텍 검색
 - 혜텍을 선택하여 카드 추천 받기
 - 현재 그카드가 어느 나이 대에서 가장 많이 사용하는지 그래프로 표시


## 정책
### crawling 정책
 - 4개의 커뮤니티을 crawling 함
 - 실시간 crawling
 - crawling한 데이터를 flask를 사용하여 logstash로 보냄
 - logstash에서 data를 Elasticsearch로 보냄

### 분석 기간 정책
 - 사용자가 분석 기간을 정할 수 있어야 함
 - 모든 분석 데이터가 사용자가 정한 기간내로 나타나야함

### 사용한 기술
      - Spring boot
      - JS
      - Css
      - Axios
      - ELK stack(Elasticsearch, Logstash, Kibana)
      - Python selenium
      - Beautifulsoup
      - Pandas
      - Deep Learning
      - konlpy
      - nltk
      - Docker compose
      - DNS
      - CentOs 7
      - Jupyter Notebook
      - maven

