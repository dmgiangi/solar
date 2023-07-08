# SOLAR

## Prerequisites

### Install needed package
you need to install gpio package 
```shell
sudo apt install pigpio
sudo curl -sL get.docker.com | bash
```

### Export needed environment variable
the Application need to run as root because pigpio need root right in order to interact with pin
add this line at the end of /root/.bashrc
```shell
export REDIS_PASSWORD=eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81
export REDIS_PORT=6379
export REDIS_HOST=redis
```

### place the services on the filesystem
put the jar in home directory.
place the file in the following way

~/ -
  |_ solar.jar
  |_ infrastructure/
    |_ redis-data/
    |_ docker.compose.yml

### Create the systemd service starter
Create a file named /etc/systemd/system/infrastructure.service

```shell
[Unit]
Description=Pull up infrastructure

[Service]
WorkingDirectory=/home/giangi/infrastructure
ExecStart=/usr/bin/docker compose up
User=giangi
Type=simple
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```

and a file named /etc/systemd/system/solar.service
```shell
[Unit]
Description=Manage Java service
After=redis.service
[Service]
WorkingDirectory=/home/giangi
ExecStart=/bin/java -Xms128m -Xmx270m -jar solar.jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005
User=root
Type=simple
Restart=on-failure
RestartSec=10

[Install]
WantedBy=multi-user.target
```

start and enable the services

```shell
sudo systemctl enable infrastructure
sudo systemctl start infrastructure
sudo systemctl enable solar
sudo systemctl start solar
```
# Deploy
