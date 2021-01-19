# matrix-demo
user management system, implemented as a REST api

things to do to run:
1. create database ovidiu
2. assign user and password in application.properties

Authentication is done via jwt. An admin user exists by default (see data.sql)
Admin is ( admin@admin.org / admin )

Authentication request is done via:
curl --location --request POST 'http://localhost:8080/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{"username": "admin@admin.org", "password": "admin"}'

returned token can be used to create a new user for example:
curl --location --request POST 'http://localhost:8080/user' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9BRE1JTiIsInN1YiI6ImFkbWluQGFkbWluLm9yZyIsImlhdCI6MTYxMTA3MDYxOSwiZXhwIjoxNjExMTU3MDE5fQ.UXu6d69lTtsfQORak3aijcMB63pNMLMYSOvT9yugFbs' \
--header 'Content-Type: application/json' \
--data-raw '{"first_name": "Your", "last_name": "Name", "password": "password", "email": "you@email.com"}'
