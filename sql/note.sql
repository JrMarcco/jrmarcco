drop table if exists `fn_note`;
create table `fn_note` (
    `id` int(11) unsigned not null auto_increment comment '自增id',
    `user_id` int(11) unsigned not null default 0 comment '用户id',
    `training_topic` varchar(32) not null default 0 comment '训练主题',
    `total_training_volume` int(8) default 0 comment '总训练量',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
    `is_deleted` char(1) not null default '0' comment '删除标记：0-正常；1-已删除',
     primary key (`id`)
) engine=innodb default charset=utf8 comment='健身笔记表';

drop table if exists `fn_note_detail`;
create table `fn_note_detail` (
    `id` int(11) unsigned not null auto_increment comment '自增id',
    `note_id` int(11) unsigned not null default 0 comment '笔记id',
    `training_content` varchar(16) not null default '' comment '训练内容',
    `training_volume` int(8) not null default 0 comment '训练量',
    `training_weight` int(4) not null default 0 comment '训练重量',
    `training_group` int(4) not null default 0 comment '训练组数',
    `group_times` int(4) not null default 0 comment '每组次数',
    `interval` int(4) not null default 0 comment '组间时长',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
     primary key (`id`)
) engine=innodb default charset=utf8 comment='健身笔记详情表';

