sudo su
yum install git
mkdir -p /opt/spyc
cd /opt/spyc
git clone https://github.com/SierraPointYC/BarTabsServer.git
yum install mysql
yum install mysql-server
# Set mysql password
mysql -p createSchema.sql
# install maven
mvn clean install
#if systemd is available
#cp bartabs.service /etc/systemd/system/bartabs.service
# else
cp bartabsd /etc/init.d
