mvn clean package
scp ./target/solar.jar solar:/data/admin/solar.jar
ssh solar 'sudo systemctl stop solar.service &&
sudo mv /data/admin/solar.jar /data/spring/solar.jar &&
sudo chown spring:spring /data/spring/solar.jar &&
sudo systemctl start solar.service'
