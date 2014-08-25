Create Database QLBanHang
Go

Use QLBanHang
Go

Create Table MatHang
(
	TenMatHang nvarchar(70),
	TenHang nvarchar(50),
	LoaiMatHang nvarchar(50)
	
	Primary Key (TenMatHang, TenHang)
)
Go

Create Table HoaDon
(
	TenMatHang nvarchar(70),
	TenHang nvarchar(50),
	LoaiMatHang nvarchar(50),
	NgayXuat char(21)
	
	Primary Key (TenMatHang, TenHang)
)
Go

Create Table PhieuNhap
(
	TenMatHang nvarchar(70),
	TenHang nvarchar(50),
	LoaiMatHang nvarchar(50),
	NgayNhap char(21)
	
	Primary Key (TenMatHang, TenHang)
)
Go

INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'10 câu chuyện văn chương', N'Văn Học', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Ăn cơm mới nói chuyện cũ: Hậu Giang - Ba Thắc', N'Trẻ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Bài học kỳ diệu từ chiếc xe rác', N'Tổng hợp Tp.HCM', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Các cuộc đời ngoại hạng', N'Bạn Trẻ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Camry', N'Toyota', N'Xe hơi')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Cho một ngày mai mơ ước', N'Trình Bầy', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Chuyển đổi lớn - Ráp lại thế giới từ Edison tới Google', N'Trẻ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Chuyện vô lý', N'Văn Nghệ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Đời nghệ sĩ', N'Văn hóa Thông tin', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Du lịch vòng quanh thế giới', N'Khai Trí', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Giai thoại về các tỉ phú Sài Gòn xưa', N'Trẻ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Giang hồ Saigon', N'Trẻ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Gương hy sinh', N'Nguyễn Hiến Lê', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Gương hy sinh', N'Văn hóa Thông tin', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Hạ tầng đô thị Sài Gòn buổi đầu', N'Tổng hợp TP.HCM', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Hồi ký 50 năm mê hát', N'Trẻ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'L''Organisation Des Travaux Administratifs', N'Delmas', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Luyện trí nhớ', N'Khai Trí', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Một góc học tập - Chảy mồ hôi tim óc', N'Đồng Nai', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Một mùa hè vắng bóng chim', N'Văn hóa Thông tin', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Một nghệ thuật sống', N'Thanh Hóa', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Một nghệ thuật sống ', N'Trẻ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Mười điều tâm niệm', N'Tự Do', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Muốn giỏi toán hình học phẳng', N'Nguyễn Hiến Lê', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Mưu kế người xưa', N'Thanh Niên', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Nam Bộ xưa và nay', N'Tp.HCM - Tạp chí Xưa và Nay', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Người Việt biết đùa', N'Văn Nghệ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Những vấn đề của thời đại', N'Mặt Đất', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Óc Sáng Suốt', N'Phạm Văn Tươi', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Othello - An outline - guide to the play', N'Barnes & Noble, Inc', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Phương pháp soạn và viết khảo luận', N'Đại Chúng', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Quẳng gánh lo đi và vui sống', N'Long An', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Quẳng gánh lo đi và vui sống', N'Trẻ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Saigon Tạp Pín Lù', N'Hội nhà văn', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Sống đẹp', N'Văn hóa Thông tin', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Sống Lạc Quan', N'Khai Trí', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Thẳng tiến trên đường đời', N'Khai Trí', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'The advantures of Tom Sawyer', N'Oxford New York - Oxford University Press', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Tội nghiệp Boléro!', N'Văn Nghệ', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Túi khôn của loài người', N'Phạm Văn Tươi', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'Tương lai trong tay ta', N'Văn Hóa', N'Sách báo, tạp chí')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'XPS', N'Dell', N'Laptop')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'K55A', N'Asus', N'Laptop')
INSERT [dbo].[MatHang] ([TenMatHang], [TenHang], [LoaiMatHang]) VALUES (N'N72', N'Nokia', N'Điện thoại, máy tính bảng')