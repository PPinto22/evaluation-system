cd EvaluationSystem/
mvn package
cp target/evalsys-backend-1.0.jar ../Docker/spring/
cd ../Docker/spring
docker build -t evalsys-app .

cd ../postgres
docker build -t evalsys-pg .
cd ../apache-modproxy
docker build -t evalsys-proxy .
cd ../../

docker network create -d bridge evalsys-bridge

docker create --net evalsys-bridge -p 5432:5432 --name evalsys-pg evalsys-pg
docker create --net evalsys-bridge -p 8081:8080 --name evalsys-app1 evalsys-app
docker create --net evalsys-bridge -p 8082:8080 --name evalsys-app2 evalsys-app
