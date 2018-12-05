# Tài liệu hướng dẫn chạy project so sánh hiệu năng giữa Oracle NoSQL và MySQL trên ubuntu

Thành viên trong nhóm:
  - Đoàn Thị Hoài Thu
  - Trần Quang Tuấn
  - Trần Hữu Tuân
  - Nguyễn Đức Thiện

# Mô tả chung về project

  - Project hỗ trợ người dùng thực hiện các truy vấn trên giao diện đối với 2 cơ sở dữ liệu là Oracle NoSQL và MySQL
  - Hiển thị thời gian của từng câu truy vấn, tiện cho người dùng so sánh hiệu suất của 2 loại DBMS
  - Project được demo với ví dụ dữ liệu gồm 2 bảng là students và countries
  - Students gồm các trường ("student_id":int,"fist_name":varchar(20),"last_name":varchar(20),"age":int,"country_id":int)
  - Countries gồm các trường ("country_id":int,"country_code":varchar(2),"country_name":varchar(20))
  - Mỗi DBMS có khoảng 20 triệu bản ghi

### Project sử dụng ngôn ngữ:
  - Java cho backend
  - React cho frontend


### Công nghệ sử dụng

* MySQL Server 8.0.13 đối vơi cơ sở dữ liệu MySQL
* KVStore-ee-18.3 đối với cơ sở dữ liệu Oracle NoSQL

## Hướng dẫn cài đặt chi tiết trên hệ điều hành ubuntu

### Yêu cầu máy tính cần có:
- Java 8
- Gradle 4.0 trở lên (Công cụ để build project)
- MySQL Server 8.0
- Clone project tại link [https://github.com/doanthihoaithu/hqt][PlDb]
#### Sau khi clone project, build project bằng gradle
```sh
$ cd hqt
$ gradle build -x test
```

Run KVStore service để có môi trường chạy Oracle NoSQL, với câu lệnh dưới đây, KVStore service được chạy ở localhost port 5000, chú ý kiểm tra máy tính để ko có service nào khác đang chạy ở port 5000

```sh
$ cd hqt
$ java -jar lib/kvstore.jar kvlite -secure-config disable
```
Ngoài hình thức chạy query trên giao diện web, project đã có sẵn các file thư viện .jar cho phép người dùng thao tác các query bằng cửa sổ dòng lệnh, các file .jar dc để trong thư viện /hqt/lib

```sh
$ cd hqt
$ java -jar lib/kvcli.jar -helper-hosts localhost:5000 -store kvstore -timeout 600000
```

### Thực hiện truy vấn trên giao diện web
Sau khi build project xong, ngay trong thư mục gốc của project sẽ xuất hiện 1 folder có tên là build, folder này có chứa file .jar là file chạy project trên nền tảng web
Để chạy file, nhập câu lệnh sau:
```sh
$ java -jar build/libs/hoaithu-0.0.1-SNAPSHOT.jar
```
Truy cập vào link [http://localhost:5000/swagger-ui.html#/] để thực hiện các truy vấn trên giao diện web.
### END