DELETE FROM roles;
DELETE FROM permissions;
DELETE FROM roles_permissions;

INSERT INTO roles (name, description, active, created_at, created_by) VALUES
       ('CUSTOMER', 'Khách hàng', true, NOW(), null),
       ('ADMIN', 'Quản trị viên', true, NOW(), null),
       ('SALES_CLERK', 'Nhân viên bán hàng', true, NOW(), null),
       ('WAREHOUSE_STAFF', 'Nhân viên kho', true, NOW(), null),
       ('MARKETING_STAFF', 'Nhân viên marketing', true, NOW(), null),
       ('HR_MANAGER', 'Nhân viên quản lý nhân sự', true, NOW(), null),
       ('PRODUCT_MANAGER', 'Nhân viên quản lý sản phẩm', true, NOW(), null),
       ('ORDER_MANAGER', 'Nhân viên quản lý đơn hàng', true, NOW(), null),
       ('CUSTOMER_MANAGER', 'Nhân viên quản lý khách hàng', true, NOW(), null);

INSERT INTO permissions (module, name, description, active, created_at, created_by) VALUES
       ('CUSTOMER', 'VIEW', 'Xem thông tin khách hàng', true, NOW(), null),
       ('CUSTOMER', 'ADD', 'Thêm mới khách hàng', true, NOW(), null),
       ('CUSTOMER', 'EDIT', 'Chỉnh sửa thông tin khách hàng', true, NOW(), null),
       ('CUSTOMER', 'DELETE', 'Xóa khách hàng', true, NOW(), null),
       ('CUSTOMER', 'BLOCK', 'Khóa tài khoản khách hàng', true, NOW(), null),
       ('CUSTOMER', 'UNBLOCK', 'Mở khóa tài khoản khách hàng', true, NOW(), null),

       ('EMPLOYEE', 'VIEW', 'Xem thông tin nhân viên', true, NOW(), null),
       ('EMPLOYEE', 'ADD', 'Thêm mới nhân viên', true, NOW(), null),
       ('EMPLOYEE', 'EDIT', 'Chỉnh sửa thông tin nhân viên', true, NOW(), null),
       ('EMPLOYEE', 'DELETE', 'Xóa nhân viên', true, NOW(), null),
       ('EMPLOYEE', 'CHANGE', 'Thay đổi trạng thái công việc nhân viên', true, NOW(), null), -- Đang làm việc, đã nghỉ việc --
       ('EMPLOYEE', 'BLOCK', 'Khóa tài khoản nhân viên', true, NOW(), null),
       ('EMPLOYEE', 'UNBLOCK', 'Mở khóa tài khoản nhân viên', true, NOW(), null),

       ('PRODUCT', 'VIEW', 'Xem thông tin sản phẩm', true, NOW(), null),
       ('PRODUCT', 'ADD', 'Thêm mới sản phẩm', true, NOW(), null),
       ('PRODUCT', 'EDIT', 'Chỉnh sửa thông tin sản phẩm', true, NOW(), null),
       ('PRODUCT', 'DELETE', 'Xóa sản phẩm', true, NOW(), null),
       ('PRODUCT', 'CHANGE', 'Thay đổi trạng thái hiển thị sản phẩm', true, NOW(), null), -- Ẩn, hiển thị, ngừng kinh doanh, liên hệ cửa hàng  --

       ('CATEGORY', 'VIEW', 'Xem thông tin danh mục sản phẩm', true, NOW(), null),
       ('CATEGORY', 'ADD', 'Thêm mới danh mục sản phẩm', true, NOW(), null),
       ('CATEGORY', 'EDIT', 'Chỉnh sửa thông tin danh mục sản phẩm', true, NOW(), null),
       ('CATEGORY', 'DELETE', 'Xóa danh mục sản phẩm', true, NOW(), null),
       ('CATEGORY', 'CHANGE', 'Thay đổi trạng thái hiển thị danh mục sản phẩm', true, NOW(), null), -- Ẩn, hiển thị --
       ('CATEGORY', 'ADD-P', 'Thêm sản phẩm vào danh mục', true, NOW(), null),
       ('CATEGORY', 'REMOVE-P', 'Gỡ sản phẩm vào danh mục', true, NOW(), null),

       ('BRAND', 'VIEW', 'Xem thông tin thương hiệu', true, NOW(), null),
       ('BRAND', 'ADD', 'Thêm mới thương hiệu', true, NOW(), null),
       ('BRAND', 'EDIT', 'Chỉnh sửa thông tin thương hiệu', true, NOW(), null),
       ('BRAND', 'DELETE', 'Xóa thương hiệu', true, NOW(), null),
       ('BRAND', 'CHANGE', 'Thay đổi trạng thái hiển thị thương hiệu', true, NOW(), null),

       ('COMMENT', 'VIEW', 'Xem thông tin bình luận sản phẩm', true, NOW(), null),
       ('COMMENT', 'REPLY', 'Phản hồi thông tin bình luận sản phẩm', true, NOW(), null),
       ('COMMENT', 'DELETE', 'Xóa bình luận sản phẩm', true, NOW(), null),
       ('COMMENT', 'CHANGE', 'Thay đổi trạng thái hiển thị bình luận sản phẩm', true, NOW(), null), -- Ẩn, hiển thị --

       ('REVIEW', 'VIEW', 'Xem thông tin đánh giá sản phẩm', true, NOW(), null),
       ('REVIEW', 'REPLY', 'Phản hồi thông tin đánh giá sản phẩm', true, NOW(), null),
       ('REVIEW', 'DELETE', 'Xóa đánh giá sản phẩm', true, NOW(), null),
       ('REVIEW', 'CHANGE', 'Thay đổi trạng thái hiển thị đánh giá sản phẩm', true, NOW(), null), -- Ẩn, hiển thị --

       ('EVENT', 'VIEW', 'Xem thông tin sự kiện', true, NOW(), null),
       ('EVENT', 'ADD', 'Thêm mới sự kiện', true, NOW(), null),
       ('EVENT', 'EDIT', 'Chỉnh sửa thông tin sự kiện', true, NOW(), null),
       ('EVENT', 'DELETE', 'Xóa sự kiện', true, NOW(), null),
       ('EVENT', 'CHANGE', 'Thay đổi trạng thái hiển thị sự kiện', true, NOW(), null),
       ('EVENT', 'ADD-P', 'Thêm sản phẩm vào sự kiện', true, NOW(), null),
       ('EVENT', 'REMOVE-P', 'Gỡ sản phẩm khỏi sự kiện', true, NOW(), null),

       ('ROLE', 'VIEW', 'Xem thông tin vai trò', true, NOW(), null),
       ('ROLE', 'ADD', 'Thêm mới vai trò', true, NOW(), null),
       ('ROLE', 'EDIT', 'Chỉnh sửa thông tin vai trò', true, NOW(), null),
       ('ROLE', 'DELETE', 'Xóa vai trò', true, NOW(), null),
       ('ROLE', 'ASSIGN', 'Phân vai trò cho nhân viên', true, NOW(), null),
       ('ROLE', 'BLOCK', 'Khóa vai trò', true, NOW(), null),
       ('ROLE', 'UNBLOCK', 'Mở khóa vai trò', true, NOW(), null),

       ('PERMISSION', 'VIEW', 'Xem thông tin quyền hạn', true, NOW(), null),
       ('PERMISSION', 'ADD', 'Thêm mới quyền hạn', true, NOW(), null),
       ('PERMISSION', 'EDIT', 'Chỉnh sửa thông tin quyền hạn', true, NOW(), null),
       ('PERMISSION', 'DELETE', 'Xóa quyền hạn', true, NOW(), null),
       ('PERMISSION', 'BLOCK', 'Khóa quyền hạn', true, NOW(), null),
       ('PERMISSION', 'UNBLOCK', 'Mở khóa quyền hạn', true, NOW(), null),

       ('ORDER', 'VIEW', 'Xem thông tin đơn hàng', true, NOW(), null),
       ('ORDER', 'ADD', 'Thêm mới đơn hàng', true, NOW(), null),
       ('ORDER', 'EDIT', 'Chỉnh sửa thông tin đơn hàng', true, NOW(), null),
       ('ORDER', 'DELETE', 'Xóa đơn hàng', true, NOW(), null),

       ('VOUCHER', 'VIEW', 'Xem thông tin mã giả giá', true, NOW(), null),
       ('VOUCHER', 'ADD', 'Thêm mới mã giảm giá', true, NOW(), null),
       ('VOUCHER', 'EDIT', 'Chỉnh sửa thông tin mã giảm giá', true, NOW(), null),
       ('VOUCHER', 'DELETE', 'Xóa mã giảm giá', true, NOW(), null),
       ('VOUCHER', 'CHANGE', 'Thay đổi trạng thái hiển thị mã giảm giá', true, NOW(), null), -- Ẩn, hiển thị --

       ('CONTACT', 'VIEW', 'Xem thông tin liên hệ khách hàng', true, NOW(), null),
       ('CONTACT', 'REPLY', 'Phản hồi thông tin liên hệ khách hàng', true, NOW(), null);


INSERT INTO roles_permissions (role_id, permission_id)
SELECT r.id as role_id, p.id as permission_id
FROM roles r JOIN permissions p ON (
    (r.name = 'ADMIN' AND p.module IN (
                                       'ROLE',
                                       'PERMISSION',
                                       'CATEGORY',
                                       'CUSTOMER',
                                       'PRODUCT',
                                       'BRAND',
                                       'EMPLOYEE',
                                       'EVENT',
                                       'VOUCHER',
                                       'ORDER',
                                       'REVIEW',
                                       'COMMENT')) OR
    (r.name = 'MARKETING_STAFF' AND p.module IN ('VOUCHER','EVENT')) OR
    (r.name = 'HR_MANAGER' AND p.module = 'EMPLOYEE') OR
    (r.name = 'PRODUCT_MANAGER' AND p.module IN ('PRODUCT', 'CATEGORY', 'COMMENT')) OR
    (r.name = 'ORDER_MANAGER' AND p.module IN ('ORDER', 'REVIEW')) OR
    (r.name = 'CUSTOMER_MANAGER' AND p.module IN ('CUSTOMER','CONTACT'))
)
