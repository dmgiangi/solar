# SOLAR

## Prerequisites
The spring application take some time to start in order to set the state of the pin before it could initilize you can put the following line in /etc/rc.local before the line 'exit 0'
```shell
echo 17 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio17/direction
echo 1 > /sys/class/gpio/gpio17/active_low
echo 0 >/sys/class/gpio/gpio17/value

echo 27 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio27/direction
echo 1 > /sys/class/gpio/gpio27/active_low
echo 0 >/sys/class/gpio/gpio27/value

echo 22 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio22/direction
echo 1 > /sys/class/gpio/gpio22/active_low
echo 0 >/sys/class/gpio/gpio22/value

echo 23 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio23/direction
echo 1 > /sys/class/gpio/gpio23/active_low
echo 0 >/sys/class/gpio/gpio23/value

echo 24 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio24/direction
echo 1 > /sys/class/gpio/gpio24/active_low
echo 0 >/sys/class/gpio/gpio24/value

echo 25 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio25/direction
echo 1 > /sys/class/gpio/gpio25/active_low
echo 0 >/sys/class/gpio/gpio25/value

echo 5 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio5/direction
echo 1 > /sys/class/gpio/gpio5/active_low
echo 0 >/sys/class/gpio/gpio5/value

echo 6 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio6/direction
echo 1 > /sys/class/gpio/gpio6/active_low
echo 0 >/sys/class/gpio/gpio6/value

echo 2 > /sys/class/gpio/export
echo 'in' > /sys/class/gpio/gpio2/direction
```
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