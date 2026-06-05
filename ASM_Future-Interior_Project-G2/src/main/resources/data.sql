INSERT INTO tai_khoan (ten_dang_nhap, mat_khau, email, trang_thai, vai_tro, ngay_dang_ky)
VALUES
  ('admin', 'admin123', 'admin@example.com', true, true, CURRENT_DATE),
  ('user1', 'password1', 'user1@example.com', true, false, CURRENT_DATE)
ON CONFLICT DO NOTHING;

INSERT INTO nha_san_xuat (id_nha_san_xuat, ten_nha_san_xuat, ngay_tao, mo_ta_nha_san_xuat)
VALUES
  (1, 'Future Interior Co.', CURRENT_DATE, 'Nhà sản xuất nội thất chính')
ON CONFLICT DO NOTHING;

INSERT INTO phan_nhom_loai (id_phan_nhom_loai, ten_phan_loai_nhom, ngay_tao, mo_ta_phan_loai)
VALUES
  (1, 'Ghế sofa', CURRENT_DATE, 'Bộ sưu tập ghế sofa'),
  (2, 'Bàn trà', CURRENT_DATE, 'Bộ sưu tập bàn trà')
ON CONFLICT DO NOTHING;

INSERT INTO san_pham (id_san_pham, ten_san_pham, gia_san_pham, chieu_dai, chieu_rong, chieu_cao, ngay_tao_san_pham, mo_ta_san_pham, so_luong, khoi_luong, trang_thai_ton_kho, id_nha_san_xuat, id_phan_nhom_loai)
VALUES
  ('SP001', 'Sofa X', 12000000, 200.0, 90.0, 85.0, CURRENT_DATE, 'Sofa xã hội hiện đại', 10, 30.5, true, 1, 1),
  ('SP002', 'Bàn trà A', 4500000, 120.0, 60.0, 45.0, CURRENT_DATE, 'Bàn trà sang trọng', 15, 18.0, true, 1, 2)
ON CONFLICT DO NOTHING;
