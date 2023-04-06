./mvnw install -DskipTests

docker build --build-arg JAR_FILE=target/*.jar -t progmatic/kalmar .
docker save -o target/kalmar.zip progmatic/kalmar

# docker run -d -p 8080:8080 --env-file=.env --name kalmar-app progmatic/kalmar
