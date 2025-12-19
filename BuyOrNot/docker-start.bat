@echo off
echo Checking Docker containers...

docker ps | findstr database >nul 2>&1
set database_running=%errorlevel%

docker ps | findstr redis >nul 2>&1
set redis_running=%errorlevel%

if NOT %database_running% == 0 goto start_containers
if NOT %redis_running% == 0 goto start_containers
echo All containers are already running.
goto :end

:start_containers
echo Starting Docker containers...
docker-compose up -d
ping -n 11 127.0.0.1 >nul
echo Containers started successfully.

:end