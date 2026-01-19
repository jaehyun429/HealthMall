# 1. 베이스 이미지 선택 (Java 17 환경)
FROM openjdk:17-jdk

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. 빌드된 Jar 파일 컨테이너로 복사
# 먼저 로컬에서 ./gradlew build 를 실행해서 Jar 파일을 만들어 두어야 합니다.
COPY build/libs/*.jar app.jar

# 4. 컨테이너가 시작될 때 실행할 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]