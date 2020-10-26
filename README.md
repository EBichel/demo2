Demo service for managing books
--

Every book is a single instance and therefore several instances of the same book can exist.

Run
--

Starting db:
```
docker run -it --rm \
    -e POSTGRES_DB='book-db' \
    -e POSTGRES_USER='postgres' \
    -e POSTGRES_PASSWORD='password' \
    -p 5432:5432 \
    -v $(pwd)/postgres:/var/lib/postgresql/data \
    postgres:11-alpine
```

Command creates a persistent DB folder in the current working directory of the shell.
