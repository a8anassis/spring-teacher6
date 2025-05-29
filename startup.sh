echo "Stopping and removing existing container..."
docker stop teachers 2>/dev/null  # 2 is stderr, /dev/null is 'black hole'
if [ $? -ne 0 ]; then echo "Container not running."; fi # $? holds exits status

docker rm teachers 2>/dev/null
if [ $? -ne 0 ]; then echo "Container does not exist."; fi

echo "Building the project..."
./gradlew clean build
if [ $? -ne 0 ]; then
    echo "Build failed."
    exit 1
fi

echo "Building Docker image..."
docker build -t teachers-app .

echo "Running Docker container..."
docker run -d -p 8080:8080 --name teachers teachers-app

echo "Waiting for Tomcat to boot up..."
while true; do
  # $(command substitution) in Bash
  # -s silent mode, hides progress bar and errors
  # -o discards the body of the response
  # -w "%{http_code}" Writes only the HTTP status code to stdout
  #  http://127.0.0.1:8080/manager/html, local Tomcat Manager app (usually password-protected).
  #  health check
    STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://127.0.0.1:8080/manager/html)
    if [ "$STATUS" == "401" ]; then
        break
    fi
    # -n not prints the trailing new line. it is progress indicator
    echo -n "."
    sleep 1
done

echo " Server is online."
