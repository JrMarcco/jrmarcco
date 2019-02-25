drop table if exists `sys_menu`;
create table `sys_menu` (
    `id` int(11) unsigned not null auto_increment comment '菜单id（自增）',
    `parent_id` int(11) unsigned not null default 0 comment '父菜单id',
    `name` varchar(32) not null default '' comment '菜单名称',
    `url` varchar(128) not null default '' comment '菜单url',
    `order_number` varchar(2) not null default '99' comment '排序号',
    primary key (`id`)
) engine=innodb default charset=utf8 comment='系统菜单表';

drop table if exists `sys_role`;
create table `sys_role` (
    `id` int(11) unsigned not null auto_increment comment 'id（自增）',
    `code` varchar(32) not null default '' comment '角色编码',
    `name` varchar(32) not null default '' comment '角色名称',
    `desc` varchar(64) not null default '' comment '角色描述',
    `type` char(1) not null default '0' comment '角色类型：0-普通角色；1-特殊角色（超管）',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`)
) engine=innodb default charset=utf8 comment='系统角色表';

insert into `sys_role`(`id`, `code`, `name`, `desc`, `type`) values (1, 'super_admin', '超级管理员', '超级管理员', '1');
insert into `sys_role`(`id`, `code`, `name`, `desc`, `type`) values (2, 'admin', '管理员', '管理员', '0');
insert into `sys_role`(`id`, `code`, `name`, `desc`, `type`) values (3, 'user', '普通用户', '系统用户', '0');

drop table if exists `sys_user`;
create table `sys_user` (
    `id` int(11) unsigned not null auto_increment comment '用户id（自增）',
    `username` varchar(32) not null default '' comment '用户名',
    `password` varchar(64) not null default '' comment '密码',
    `nickname` varchar(32) not null default '' comment '用户昵称',
    `gender` char(1) not null default '2' comment '性别：0-女；1-男；2-未知',
    `phone_number` varchar(16) not null default '' comment '手机号',
    `avatar` varchar(128) not null default '' comment '头像（路径）',
    `email` varchar(64) not null default '' comment '邮箱地址',
    `role_id` int(11) unsigned not null default 3 comment '角色id（默认普通用户）',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    `is_deleted` char(1) not null default '0' comment '删除标记：0-正常；1-已删除',
    primary key (`id`)
) engine=innodb default charset=utf8 comment='系统用户表';

insert into `sys_user`(`id`, `username`, `password`, `nickname`, `gender`, `role_id`) values (1, 'jrmarcco', '$2a$12$L/ciq4M/oLk4ldoeLq9PQe5JXFdguuyB6WZX8PVi93i10YR.n3Eu.', 'jrmarcco', '1', 1);

drop table if exists `sys_permission`;
create table `sys_permission` (
    `id` int(1) unsigned not null auto_increment comment 'id（自增）',
    `name` varchar(32) not null default '' comment '授权名称',
    `url` varchar(128) not null default '' comment '资源路径',
    `type` char(1) not null default '0' comment '授权类型：0-页面（菜单）授权；1-操作（增删改）授权；2-特殊权限',
    `menu_id` int(11) unsigned not null default 0 comment '绑定菜单id',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    primary key (`id`)
) engine=innodb default charset=utf8 comment='系统授权（权限）表';

insert into `sys_permission`(`id`, `name`, `url`, `type`) values (1, 'ALL', '*', '2');

drop table if exists `sys_role_permission`;
create table `sys_role_permission` (
    `id` varchar(32) not null comment '主键id',
    `role_id` int(11) unsigned not null default 0 comment '角色id',
    `permission_id` int(11) unsigned not null default 0 comment '授权id',
    primary key (`id`)
) engine=innodb default charset=utf8 comment='角色授权关联表';

insert into `sys_role_permission`(`id`, `role_id`, `permission_id`) values (replace(uuid(), '-', ''), 1, 1);

drop table if exists `sys_role_menu`;
create table `sys_role_menu` (
    `id` varchar(32) not null comment '主键id',
    `role_id` int(11) unsigned not null default 0 comment '角色id',
    `menu_id` int(11) unsigned not null default 0 comment '用户id',
    primary key (`id`)
) engine=innodb default charset=utf8 comment='角色菜单关联表';
