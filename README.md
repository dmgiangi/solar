# SOLAR

## Prerequisites

### Install needed package
you need to install docker on rpi 
```shell
sudo curl -sL get.docker.com | bash
```

### place the services on the filesystem
Place the file in the following way (we assume that the home directory is /home/giangi).\
Please consider to change the redis password from the docker compose found in thi project in the infrastructure folder
```text
~/ _
    |_ infrastructure/
      |_ redis-data/
      |_ docker.compose.yml
```
### Create the systemd service starter
Create a file named /etc/systemd/system/infrastructure.service

```shell
[Unit]
Description=Pull up infrastructure

[Service]
WorkingDirectory=/home/giangi/infrastructure
ExecStart=/usr/bin/docker compose up
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
```