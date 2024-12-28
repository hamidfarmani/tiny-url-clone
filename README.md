1. Use ```cqlsh```
2. First create the keyspace:

```
   CREATE KEYSPACE IF NOT EXISTS store WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' };
```

2. Then create the table:

```
   CREATE TABLE IF NOT EXISTS store.url_entity (
    shortUrl text PRIMARY KEY,
    originalUrl text,
    apiDevKey text,
    userName text,
    expireDate text,
    customAlias text
    );
```
3. Exit the ```cqlsh``` and run the following to get the datacenter name:
```
nodetool status
```
