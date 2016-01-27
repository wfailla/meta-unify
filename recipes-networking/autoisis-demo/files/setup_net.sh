#!/bin/sh

set -e

NETWORK_STARTED="/tmp/network_started"
last_ether=""
first_ether=""
cd /sys/class/net
INTERFACES=""
for interface in *; do
	if [ x"${interface}" = x"gre0" ]; then
		break;
	fi

	type="$(cat /sys/class/net/${interface}/type)"
	if [ x"${type}" != x"1" ]; then
		continue;
	fi

	if [ -n "$(find /sys/class/net -name ${interface}'.*' -maxdepth 1)" ]; then
		continue; # Ignore devices which have vlans on them - we will add only the vlans
	fi

	last_ether="${interface}"
	if [ -z "$first_ether" ]; then
		first_ether="${interface}"
	fi
	ip link set ${interface} up
	INTERFACES="${INTERFACES}\"${interface}\","
done
# Remove trailing comma
INTERFACES="${INTERFACES%,}"
if [ x"$first_ether" != x"$last_ether" ]; then
	# Remove last ether only from interfaces, if it is not the only one
	INTERFACES="${INTERFACES%,*}"
fi

if [ ! -e "${NETWORK_STARTED}" -a -n "${last_ether}" ]; then
	# Configure hostname to something unique
	# (Without this, the IS-IS graph is somewhat unintelligible)
	new_hostname="$(hostname)-$(cat /sys/class/net/${last_ether}/address | sed -e 's/:/-/g')"
	hostname "${new_hostname}"

	# Configure AutoISIS to run on all ethernets except the last one
	cat <<-EOF > /etc/enit/autoisis/80-demo.config
	{isis, [
	    {startup, [
	        {allowed_interfaces, [$INTERFACES]},
	        {autoconf, true},
	        {autoconf_fingerprint, <<0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31>>}
	    ]},
	    {rib_client, zclient}
	]}.
	EOF

	if [ x"$first_ether" != x"$last_ether" ]; then
		ip addr add 192.168.1.1/24 dev ${last_ether}
		cat <<-EOF > /tmp/mgmt-dhcpd.conf
			start 192.168.1.10
			end 192.168.1.254
			interface ${last_ether}
			max_leases 245
		EOF
		udhcpd /tmp/mgmt-dhcpd.conf
	fi

	touch "${NETWORK_STARTED}"
fi
