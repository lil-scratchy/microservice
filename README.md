# <img src="https://avatars0.githubusercontent.com/u/53555720?s=200&v=4" width="50"/> Scratchy Microservice :tm:
Microservice for storing sensor data.

## Rest Endpoints
The application exposes several endpoints to create and get data in a MariaDB.

### Data

#### Create
Create a data object.

```
POST {apiUrl}/devices/{deviceId}/data
```

Object example:

```
{
	"name": "co2",
	"value": 382188919.0
}
```

#### Get
Get a collection of data by name.

```
GET {apiUrl}/devices/{deviceId}/data/{dataType}
```

Example:

```
GET {apiUrl}/devices/1/data/co2
```
may return:

```
[
    {
        "id": 29,
        "name": "co2",
        "value": 400,
        "created": "2019-08-01T16:40:19.604+0000"
    },
    {
        "id": 54,
        "name": "co2",
        "value": 402,
        "created": "2019-08-01T16:40:23.681+0000"
    }
]
```
### Device

#### Create
Create new device with location.

```
POST {apiUrl}/devices
```

Object example:

```
{
    "name": "Company X",
    "location": {
        "latitude": 45.4,
        "longitude": 64.7
    }
}
```

#### Get
Get device by id.

```
GET {apiUrl}/devices/{id}
```
may return:

```
{
    "id": 1,
    "name": "Company X",
    "location": {
        "id": 2,
        "latitude": 45.4,
        "longitude": 64.7
    },
    "data": [
        {
        	"id": 29,
        	"name": "co2",
        	"value": 400,
        	"created": "2019-08-01T16:40:19.604+0000"
    	},
    	{
        	"id": 54,
        	"name": "light",
        	"value": 213213.5,
        	"created": "2019-08-01T16:40:23.681+0000"
    	}
    ]
}
```

Get all devices.

```
GET {apiUrl}/devices
```

#### Update
Update an existing device.

```
PUT {apiUrl}/devices/{id}
```

:warning: Send the whole device in the payload.