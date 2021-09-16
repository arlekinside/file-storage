# File Storage API

---
<h3>Start-up</h3>

- Configure ElasticSearch cluster on any desirable platform
- Add application.properties file to ``src/main/resources``.<br>
  It should look like this
````
server.port=PORT # Optional. Default is 8080
spring.data.elasticsearch.client.reactive.endpoints=YOUR_DB_ENDPOINT
spring.data.elasticsearch.client.reactive.username=YOUR_USERNAME
spring.data.elasticsearch.client.reactive.password=YOUR_PASSWORD
````
- Declare bean of type ``com.github.arlkinside.filestorage.services.FileNameAnalyzer``<br>
that should map method arg's input string (filename) to filetype.<br><br>
The other option is to declare bean of type ``com.github.arlekinside.filestorage.services.implementations.FileNameAnalyzerImpl``<br>
with injected Map, where _key_ is _extension_ and _value_ is _filetype_ 
- Launch the application with one of the supported scripts<br>
`mvn spring-boot:run`
  
---
<h3>API endpoints</h3>

- **POST /file<br>**
  Saves a file to the database<br>
  
_Request body:_
```
{
    "name": "file_name.ext"
    "size" : 121231
}
  ```
_Response body:_
```
{
    "ID": "unique file ID"
}
```
_If one of the fields is absent:_
````
{
    "success": false,
    "error": "error description"
}

````
<br>

- **DELETE /file/{ID}**<br>
  Deletes a file by its ID from the database
  

  _Response body:_
```
{
    "success": true
}
  ```
_Or 404 and body:_
````
{
    "success": false,
    "error": "File not found"
}

````
- **POST /file/{ID}/tags**<br>
Adds unlimited number of tags to chosen file
  
_Request body:_
````
[
    "tag1", "tag2", "tag3"
]
````
_Returns:_
````
{
    "success": true
}
````
<br>

- **DELETE /file/{ID}/tags**<br>
Removes provided tags from file

_Request body_
````
[
    "tag1", "tag3"
]
````
_Response body_
````
{
    "success": true
}
````
_On error:_
````
{
"success": false,
"error": "tag not found on file"
}
````
<br>

- **GET /file?tags=tag1,tag2,tag3&page=2&size=3**<br>
  - tags - [optional] list of tags to filter by. Only files containing ALL of supplied tags should return. If tags parameter is omitted - don't apply tags filtering i.e. return all files.
  - page - [optional] the 0-based parameter for paging. If not provided use 0 (the first page)
  - size - [optional] the page size parameter. If not passed use default value 10.
    
_Response body_
````
{
   "total": 25,
   "page": [
       {
          "id": "ID1",
          "name": "presentation.pdf",
          "size": 123123,
          "tags": ["work"]
       },
       {
          "id": "ID2",
          "name": "file.mp3",
          "size": 123123,
          "tags": ["audio", "jazz"]
       },
       {
          "id": "ID3",
          "name": "film.mp4",
          "size": 123123,
          "tags": ["video"]
       }
   ]
}
````


