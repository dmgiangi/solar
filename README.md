# SOLAR

## Prerequisites

The spring application take some time to start in order to set the state of the pin before it could initilize you can
put the following line in /etc/rc.local **before the line 'exit 0'**

```shell
echo 17 > /sys/class/gpio/export
echo 'out' > /sys/class/gpio/gpio27/direction
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

you need to install some tool on rpi

```shell
sudo apt install pigpio python3-pigpio redis-server openjdk-17-jre
sudo systemctl enable pigpiod
```

### place the services on the filesystem

Create the folder and the user needed for the system

```shell
sudo mkdir /data
sudo chown root:root /data
sudo chmod 755 /data
sudo useradd -r -s /usr/sbin/nologin spring
sudo usermod -a -G gpio spring
sudo mkdir /data/spring
sudo chown spring:spring /data/spring
sudo chmod 755 /data/spring
sudo mkdir /data/admin
sudo chown solar:solar /data/admin
sudo chmod 755 /data/admin
sudo mkdir /data/redis
sudo chown redis:redis /data/redis
sudo chmod 755 /data/redis
sudo systemctl enable redis-server
sudo nano /etc/systemd/system/redis.service
```

and add the row

```text
ReadWritePaths=/data/redis
```

### Configure redis

edit the redis configuration file and set

```shell
sudo nano /etc/redis/redis.conf
```

```text
port 6579
requirepass eYVX7EwVmmxKPCDmwMtyKVge8oLd2t81
dir /data/redis
```

### Create the systemd service starter

Create a file named /etc/systemd/system/solar.service with the command:

```shell
sudo nano /etc/systemd/system/solar.service
```

with the following content:

```shell
[Unit]
Description=Solar services

[Service]
WorkingDirectory=/data/solar
ExecStart=/usr/bin/java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000 -jar /data/spring/solar.jar
User=spring
Type=simple
Restart=on-failure
RestartSec=10
After=network.target
Requires=redis.service

[Install]
WantedBy=multi-user.target
```

**TODO: Try to remove the pytho gpio rest service and introduce diozero**\
create a file named with:

```shell
sudo nano /etc/systemd/system/gpio_rest.service
```

that contain the following code:

```shell
[Unit]
Description=GPIO REST Script Service
After=network.target

[Service]
ExecStart=/usr/bin/python3 /data/admin/listener.py
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

start and enable the services

```shell
sudo systemctl daemon-reload
sudo systemctl enable gpio_rest solar
sudo systemctl start gpio_rest solar
```