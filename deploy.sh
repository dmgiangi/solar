ssh solar 'sudo systemctl stop solar.service && 
rm /home/giangi/solar.jar &&
mv /home/giangi/solar_new.jar /home/giangi/solar.jar &&
sudo chown solar_user:solar /home/giangi/solar.jar &&
sudo systemctl start solar.service'
