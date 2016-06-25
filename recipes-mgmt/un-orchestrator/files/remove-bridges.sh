#!/bin/sh -e

BRIDGES=`ovs-vsctl list-br`

for bride in ${BRIDGES[@]}
do
  ovs-vsctl del-br ${bride}
done
