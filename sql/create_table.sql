# 数据库初始化

-- 创建库
create database if not exists ximuapi;

-- 切换库
use ximuapi;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    username  varchar(256)                           not null comment '账号',
    password  varchar(512)                           not null comment '密码',
    union_id     varchar(256)                           null comment '微信开放平台id',
    open_id      varchar(256)                           null comment '公众号openId',
    nickname      varchar(256)                           null comment '用户昵称',
    avatar       varchar(1024)                          null comment '用户头像',
    profile      varchar(512)                           null comment '用户简介',
    user_role    varchar(256) default 'user'            not null comment '用户角色：user/admin/disabled',
    create_time  datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_deleted    tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (union_id)
) comment '用户' collate = utf8mb4_unicode_ci;