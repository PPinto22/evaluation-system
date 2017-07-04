cd EvaluationSystem/
mvn package
cp target/evalsys-backend-1.0.jar ../Docker/spring/
cd ../Docker/spring
docker build -t evalsys-app .

cd ../postgres
docker build -t evalsys-pg .
cd ../apache-modproxy
docker build -t evalsys-proxy .

cd ../angular
docker build -t angular-image .
cd ../angular-apache-proxy
docker build -t evalsys-angular-proxy .


cd ../../


docker network create -d bridge evalsys-bridge

docker create --net evalsys-bridge -p 5432:5432 --name evalsys-pg evalsys-pg
docker create --net evalsys-bridge -p 8081:8080 --name evalsys-app1 evalsys-app
docker create --net evalsys-bridge -p 8082:8080 --name evalsys-app2 evalsys-app
docker create --net evalsys-bridge -p 80:80 --name evalsys-proxy evalsys-proxy

docker create --net evalsys-bridge -p 4200:4200 --name angular-web angular-image
# docker create --net evalsys-bridge -p 4202:4200 --name angular-web2 angular-image
# docker create --net evalsys-bridge -p 80:80 --name evalsys-angular-proxy evalsys-angular-proxy