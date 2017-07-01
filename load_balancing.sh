sudo a2enmod proxy
sudo a2enmod proxy_http
sudo a2enmod proxy_balancer
sudo a2enmod lbmethod_byrequests
sudo systemctl restart apache2

ip=172.18.0.1
port1=8081
port2=8082
config="<VirtualHost *:80>
  ProxyPass / balancer://evalsys/ stickysession=JSESSIONID|jsessionid
  ProxyPassReverse / balancer://evalsys/
  ProxyPassReverse / http://$ip:$port1/
  ProxyPassReverse / http://$ip:$port2/
  ProxyPreserveHost On
  ProxyRequests Off

  <Location / >
  	Order deny,allow
  	Allow from All
  </Location>

  <Proxy balancer://evalsys>
  	BalancerMember http://$ip:$port1 retry=60
  	BalancerMember http://$ip:$port2 retry=60
	</Proxy>
</VirtualHost>"

sudo echo "$config" > /etc/apache2/sites-available/000-default.conf
sudo systemctl restart apache2
