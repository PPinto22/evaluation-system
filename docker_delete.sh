docker stop evalsys-app1
docker stop evalsys-app2
docker stop evalsys-pg
docker rm evalsys-app1
docker rm evalsys-app2
docker rm evalsys-pg
docker network rm evalsys-bridge
