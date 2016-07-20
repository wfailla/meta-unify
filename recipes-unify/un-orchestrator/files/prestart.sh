#!/bin/sh -e

BRIDGES=`ovs-vsctl list-br`
PORT=6632

for bride in ${BRIDGES[@]}
do
  ovs-vsctl del-br ${bride}
done

ovs-appctl -t ovsdb-server ovsdb-server/add-remote ptcp:${PORT}
