Demo service for managing subscriptions
--

I slightly over-thought some parts in the beginning. Thinking of pause and cancel as a possibility to create
a partially "used" subscription and having a dynamic invoice object per period. This was way out of scope.

I tried to decouple subscription from product as much as possible. However, from a Service API point of view
it does feel a bit weird. JPA can be used to connect the entities via relationships. Both entities are
not big enough or clearly separated that it would make sense in this case. I left it like that providing an
_alternative_ solution.

I made the calculation of tax and gross price dynamic to accommodate I18N and possible VAT changes. The intention
was to make a change just in a single product and let it appear on all entities involved without duplicating data. A
next step could involve a periodic invoice as a "paper trail" or audit log.

Caveats
--
Most tests are just happy path.
Some data types are probably too big
Lack of validations
DB is throwing errors (probably a configuration issue on application side)

Prerequisites before running
--

Starting db:
```
docker run -it --rm \
    -e POSTGRES_DB='demo-db' \
    -e POSTGRES_USER='postgres' \
    -e POSTGRES_PASSWORD='password' \
    -p 5432:5432 \
    -v $(pwd)/postgres:/var/lib/postgresql/data \
    postgres:11-alpine
```

Command creates a persistent DB folder in the current working directory of the shell.

Some product data is added on start.

App is running on `http://localhost:8080` per default

REST API Documentation
--

Monetary values are in cents.
Percentages are multiplied by 100.

### Product endpoints

#### `GET /products`

Retrieves a list of all available products

Response example:
```
[
    {
        "id": "15992ed6-d4be-4ebc-b235-18b3cf1ccf6a",
        "name": "cheap subscription",
        "duration": 13,
        "netPrice": 9998,
        "grossPrice": 11598,
        "tax": 1599,
        "taxRate": 1599
    },
    {
        "id": "b41061aa-4c5c-4d8e-a54f-c4a6ef072181",
        "name": "expensive subscription",
        "duration": 29,
        "netPrice": 19998,
        "grossPrice": 23198,
        "tax": 3199,
        "taxRate": 1599
    }
]
```

#### `GET /products/{productId}`

Retrieves a single product by ID (UUID)

Response example:
```
{
    "id": "15993ed6-d4be-4ebc-b235-18b3cf1ccf6a",
    "name": "cheap subscription",
    "duration": 14,
    "netPrice": 9999,
    "grossPrice": 11599,
    "tax": 1600,
    "taxRate": 1600
}
```


### Subscription Endpoints

#### `GET /subscriptions/{subscriptionId}`

Retrieves a single subscription by ID

Result Example:
```
{
    "id": "aa897c50-04ee-4c26-82ce-41eb0c3308c6",
    "productId": "15993ed6-d4be-4ebc-b235-18b3cf1ccf6a",
    "startDate": "2020-10-15",
    "endDate": "2020-10-29",
    "active": true,
    "paused": false,
    "grossPrice": 11599,
    "netPrice": 9999,
    "tax": 1600,
    "taxRate": 1600
}
```

#### `POST /subscriptions`

Creates a new subscription with the provided product ID

Possible input:
```
{
    "productId": "15993ed6-d4be-4ebc-b235-18b3cf1ccf6a"
}
```

Result Example:
```
{
    "id": "48a83274-44eb-48f8-a59b-ffa1b9e54556",
    "productId": "15993ed6-d4be-4ebc-b235-18b3cf1ccf6a",
    "startDate": "2020-10-29",
    "endDate": "2020-11-12",
    "active": true,
    "paused": false,
    "grossPrice": 11599,
    "netPrice": 9999,
    "tax": 1600,
    "taxRate": 1600
}

```

#### `PATCH /subscriptions/{subscriptionId}`

Updates a single subscription by ID

Supports disabling and pausing subscription by setting the respective boolean for `active` and `paused` respectively.

Possible input:
```
{
    "active": false,
    "paused": true
}
```

Result Example:
```
{
    "id": "44778387-f537-4a10-88b3-3e9993698c3b",
    "productId": "15993ed6-d4be-4ebc-b235-18b3cf1ccf6a",
    "startDate": "2020-10-15",
    "endDate": "2020-10-29",
    "active": false,
    "paused": true,
    "grossPrice": 11599,
    "netPrice": 9999,
    "tax": 1600,
    "taxRate": 1600
}
```
