# HƯỚNG DẪN SỬ DỤNG CHƯƠNG TRÌNH

Thực hiện : **Lâm Minh Thiện**
## Mục lục
[1. Tải Source Code ?](#download)

[2.Nhập Database vào MySQL Server thông qua MySQL Workbench ](#import_sql)

[3.Thiết lập đường dẫn để lưu hình ảnh và cấu hình kết nối MySQL Server trên project ](#config)

[4.Build Project](#buildProject)

[5.Chạy trên localhost](#run_on_tomcat)

[6.Hướng dẫn test chức năng của quản trị viên](#admin_guide)


<a name = "download"></a>
#
### 1. Tải Source Code
- Nhấn chuột vào biểu tượng 
- ![image](https://user-images.githubusercontent.com/31031585/150507286-ba76a9a5-37cf-42dd-9682-4fc8f3683944.png)
- Nhấn chuột vào ![image](https://user-images.githubusercontent.com/31031585/150507386-37c1cca1-f340-4551-854c-62edbd904993.png) để tải về máy
- Giải nén vào vị trí nào đó bạn có thể dễ tìm.

#
<a name = "import_sql"></a>
### 2. Nhập Database vào MySQL Server thông qua MySQL Workbench
- Nhấn Rescan Server để tìm MySQL Server đang có 

![image](https://user-images.githubusercontent.com/31031585/150508757-9f7e6f43-6e7e-471d-afcc-eb4058553008.png)
- Nhập mật khẩu để vào giao diện quản lý các Database 

![image](https://user-images.githubusercontent.com/31031585/150508905-3e08e999-6aa7-4b38-b1d8-758f30a97eaf.png)
- Chọn Tab Server -> Data Import 
![image](https://user-images.githubusercontent.com/31031585/150509036-763adcb9-36bc-46f5-81fb-cdc39cb62b49.png)
- Chọn Import from Self-Contained File và chọn tệp tin *"thuong_mai_dien_tu DATA Chuẩn 21-1-2022.sql"*
- Tại *"Default Schema to be Imported To:"*, nhấn chuột vào *"New..."*
- Gõ *"thuong_mai_dien_tu"* và nhấn OK
![image](https://user-images.githubusercontent.com/31031585/150510441-5ac90501-952d-4a18-9296-6c4156d5eb1f.png)

- Nhấn *"Start Import"*
- Tại tab Navigator, nhấn chuột vào biểu tượng ![image](https://user-images.githubusercontent.com/31031585/150509982-34d39d9d-88fc-41e1-9b66-d200aafffc86.png)
- Xuất hiện database *"thuong_mai_dien_tu"* là bạn đã hoàn thành
 ![image](https://user-images.githubusercontent.com/31031585/150510603-2f2aca5b-b59f-4777-b7e4-ba647f1c9995.png)
 
 
 #
<a name = "config"></a>
### 3. Thiết lập đường dẫn để lưu hình ảnh và cấu hình kết nối MySQL Server trên project
- Mở Visual Studio Code, chọn File -> Open Folder để mở thu mục project vừa giải nén.
- Mở file *"ProjectPath.java"* tại đường dẫn *"src\main\java\com\tmdt\utilities\ProjectPath.java*"
- Tạo biến *"static Final String"* và chỉnh sửa tựa như hình dưới đây:
![image](https://user-images.githubusercontent.com/31031585/150512639-30536628-bc48-4af7-b4ce-b3d1f04da423.png)
- Mở file *"SqlMapConfig.xml"* Thay đổi 2 thuộc tính *"value"* tại thẻ *"property*" của *"username"* và *"password"* 
![image](https://user-images.githubusercontent.com/31031585/150513274-44799d15-34e4-45f3-a259-62cde22b0ade.png)


#
<a name = "buildProject"></a>
### 4. Build Project
- Mở *"Terminal"* và gõ lệnh *"mvn clean install"*

![image](https://user-images.githubusercontent.com/31031585/150513815-f482560f-43b6-4051-96b5-83d6a0d5ac4e.png)
- Khi có dòng chữ "*BUILD SUCCESS*" là đã thành công

![image](https://user-images.githubusercontent.com/31031585/150513899-dddf4c9f-e50f-4b9c-99e0-3309f914836e.png)






