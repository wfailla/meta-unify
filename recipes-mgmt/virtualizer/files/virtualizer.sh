#!/bin/sh

# virtualizer depends on a cwd relative to conf-files:
cd /opt/virtualizer

exec python ./virtualizer.py
