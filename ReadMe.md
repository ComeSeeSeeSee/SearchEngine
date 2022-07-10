### How to run
```sql
create database searchdb;  
use searchdb;  
  
  
CREATE TABLE user  
(  
    id INT AUTO_INCREMENT NOT NULL,  
    name VARCHAR(10)        NOT NULL,  
    password VARCHAR(10)        NOT NULL,  
    CONSTRAINT pk_user PRIMARY KEY (id)  
);  
  
  
CREATE TABLE `admin`  
(  
    id INT AUTO_INCREMENT NOT NULL,  
    name VARCHAR(10)        NOT NULL,  
    password VARCHAR(10)        NOT NULL,  
    CONSTRAINT pk_admin PRIMARY KEY (id)  
);  
  
insert into admin(id, name, password) VALUES (1,'admin','123456');  
  
  
CREATE TABLE website  
(  
    wid INT AUTO_INCREMENT NOT NULL,  
    `description` VARCHAR(100)       NULL,  
    url VARCHAR(200)       NULL,  
    create_time VARCHAR(50)        NULL,  
    CONSTRAINT pk_website PRIMARY KEY (wid)  
);
```
2.
![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490748988-a9d01812-5cd1-460e-bcdf-d655a1d529f6.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=341&id=u0eabdf7c&margin=%5Bobject%20Object%5D&name=image.png&originHeight=469&originWidth=1949&originalType=binary&ratio=1&rotation=0&showTitle=false&size=251512&status=done&style=none&taskId=u7c2915d7-8482-4f52-8342-2b646a03b15&title=&width=1417.4545454545455)
3.
![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490758195-4ce694b9-0db7-4006-bbbd-39131bdc74a1.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=377&id=u9ad3baa3&margin=%5Bobject%20Object%5D&name=image.png&originHeight=518&originWidth=1948&originalType=binary&ratio=1&rotation=0&showTitle=false&size=177362&status=done&style=none&taskId=u0d59c201-f6a5-4831-b561-d95f751e035&title=&width=1416.7272727272727)

4. [http://localhost:8080/web](http://localhost:8080/web) is the main page where you can start searching, also login/register or login as admin to edit website info.

![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490782343-02a04415-eb66-4e34-b991-3b312e09fac4.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=814&id=ud377d76c&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1119&originWidth=1931&originalType=binary&ratio=1&rotation=0&showTitle=false&size=63554&status=done&style=none&taskId=ufd04e2b8-8b1b-42e8-b94d-d73bfb2711f&title=&width=1404.3636363636363)




![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490805426-2bf38b61-326d-4caa-91eb-33e911664517.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=1022&id=u8197bb25&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1405&originWidth=1728&originalType=binary&ratio=1&rotation=0&showTitle=false&size=1494226&status=done&style=none&taskId=u41279b34-839b-45b4-adc4-8ad6d45a1b7&title=&width=1256.7272727272727)
Admin:

![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490877365-6af268e6-72a4-47a5-82a1-629c7ad18e83.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=1001&id=u4166de4b&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1377&originWidth=1913&originalType=binary&ratio=1&rotation=0&showTitle=false&size=1739424&status=done&style=none&taskId=uacb79a81-579a-4e23-bcdb-1ac18ad5953&title=&width=1391.2727272727273)


![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490879947-173664da-ebac-495a-9d8d-d5a7c94c35bd.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=1177&id=u0ce68ad6&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1618&originWidth=1920&originalType=binary&ratio=1&rotation=0&showTitle=false&size=268592&status=done&style=none&taskId=u44c42deb-b617-4d16-af4e-fe8e050eec2&title=&width=1396.3636363636363)

![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490892608-5c4a5a70-67cd-4f8d-ac03-30aab68c2f31.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=959&id=u961c145f&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1319&originWidth=1908&originalType=binary&ratio=1&rotation=0&showTitle=false&size=229620&status=done&style=none&taskId=uaa2ce67c-d85b-4b6d-af44-6d0fa49baed&title=&width=1387.6363636363637)


![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490900969-ac88c0ed-1b29-45d1-99f5-0651e7e00a5a.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=697&id=u7cec38de&margin=%5Bobject%20Object%5D&name=image.png&originHeight=958&originWidth=1936&originalType=binary&ratio=1&rotation=0&showTitle=false&size=115601&status=done&style=none&taskId=u50a4c875-1202-4880-b5f2-747a68bb318&title=&width=1408)


Search
![image.png](https://cdn.nlark.com/yuque/0/2022/png/29531365/1657490920829-771c7dc7-6a24-479d-a13c-800c86f53ccd.png#clientId=u1662158d-4898-4&crop=0&crop=0&crop=1&crop=1&from=paste&height=977&id=u19cb86e8&margin=%5Bobject%20Object%5D&name=image.png&originHeight=1343&originWidth=1625&originalType=binary&ratio=1&rotation=0&showTitle=false&size=231461&status=done&style=none&taskId=udaa69001-aa82-47a9-a19a-f713e67b2a1&title=&width=1181.8181818181818)

### Adding more features

- implement elasticsearch 
   - Using web crawler
   - Using index
- Modifying front end display more user friendly
- Adding user menu so users can edit their account and save bookmarks
- More Validation
- Using aws cloud services and  deploy
