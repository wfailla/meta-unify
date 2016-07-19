#!/bin/sh

#Author: Tobias Famulla
#Date: 2016-07-19
#Brief: start script the balancer server for the robot demo

#command line: 
#	sudo ./start $1 $2 $3 $4 $5

#dependencies:

#$1 LSI ID								(e.g., 2)
#$2 NF name								(e.g., firewall)
#$3 number_of_ports							(it is supposed to be 2 for this NF)
#$4 and $5 names of port1 and port2 respectively			(e.g., vEth0 vEth1)

server_ip_address="192.168.1.156/24"

if (( $EUID != 0 )) 
then
    echo "[nativeNF_example_start] This script must be executed with ROOT privileges"
    exit 0
fi

if (( $3 != 1))
then
    echo "[balancher_server] This script must have just one interface"
    exit 0
fi

ip a add $server_ip_address dev $4

/usr/bin/balancer_server & echo $! >>/run/balancer_server.pid

