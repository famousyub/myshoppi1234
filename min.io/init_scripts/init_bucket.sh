#!/bin/sh
/scripts/wait-for-it.sh bucket:9000
/usr/bin/mc alias set myminio http://bucket:9000 admin S1ngu1ar;
/usr/bin/mc mb myminio/perfume-images;
/usr/bin/mc anonymous set public myminio/perfume-images;
/usr/bin/mc cp --recursive /images/ myminio/perfume-images;
exit 0
