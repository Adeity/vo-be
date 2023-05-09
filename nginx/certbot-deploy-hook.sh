#!/bin/sh
cp /etc/letsencrypt/live/vyzkumodolnosti.felk.cvut.cz/fullchain.pem /srvsb/ghdata/nginx/conf.d/keys/fullchain.pem
cp /etc/letsencrypt/live/vyzkumodolnosti.felk.cvut.cz/privkey.pem /srvsb/ghdata/nginx/conf.d/keys/privkey.pem
docker-compose restart nginx