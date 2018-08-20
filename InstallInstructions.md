sudo su
yum install git
mkdir -p /opt/spyc
cd /opt/spyc
git clone https://github.com/SierraPointYC/BarTabsServer.git
yum install mysql
yum install mysql-server
chkconfig mysqld on
# Set mysql password
mysqladmin -u root password "new_password"
mysql -p createSchema.sql

CREATE SCHEMA bartabs;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'Winter@0';
GRANT ALL PRILEGES ON bartabs.* TO 'admin'@'localhost';

# install maven
mvn clean install
#if systemd is available
#cp bartabs.service /etc/systemd/system/bartabs.service
# else
cp bartabsd /etc/init.d
chkconfig --add bartabsd
chkconfig bartabsd on




