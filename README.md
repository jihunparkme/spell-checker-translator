# spell-checker-translator

Spell Checker Translator

- SringBoot 3.0.0
- Java JDK 17
- redis
- thymeleaf

## 화면 구성

- [x] 부산대학교 인공지능연구실 맞춤법/문법 검사 API
- [x] papago 번역 API

- [x] 한글 입력 / 한글 맞춤법 검사 + 영어 번역
- [x] 맞춤법 검사 전/후 비교
  - 처음 페이지 로딩 시에만 레디스 호출하고 이후에는 이어붙이기
- [x] 번역 기록 - 레디스 활용
- [ ] 번역 페이지 별도로 생성

## 로그파일
- 상용에서는 로그파일 생성
- prd 설정파일

## 호스트
- AWS EC2
- cafe24
