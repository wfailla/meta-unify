#!/bin/sh

# virtualizer depends on a cwd relative to conf-files:
cd /opt/virtualizer

exec gunicorn -b 0.0.0.0:8001 virtualizer:api
