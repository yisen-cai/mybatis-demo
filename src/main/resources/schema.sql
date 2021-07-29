DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id           BIGINT PRIMARY KEY AUTO_INCREMENT comment '主键',
    avatar       VARCHAR(100) NOT NULL DEFAULT '' comment '用户头像',
    username     VARCHAR(20)  NOT NULL comment '用户名',
    nickname     VARCHAR(20)  NOT NULL comment '用户昵称',
    gender       TINYINT      NOT NULL DEFAULT 2 comment '性别，0:男，1:女，2:未知',
    birthday     DATE         NOT NULL DEFAULT '出生日期',
    user_comment VARCHAR(200) NOT NULL DEFAULT '' comment '备注信息',
    create_time  TIMESTAMP    NOT NULL DEFAULT NOW() comment '创建时间',
    update_time  TIMESTAMP    NOT NULL DEFAULT NOW() comment '上次更新时间',
    deleted      TINYINT      NOT NULL DEFAULT 0 comment '逻辑删除，0:未删除, 1:已删除',
    active       TINYINT      NOT NULL DEFAULT 1 comment '是否启用, 0:未启用, 1:已启用'
);


insert into user (id, avatar, username, nickname, gender, birthday, user_comment, deleted, active, create_time,
                  update_time)
values (1, 'https://staff-test.oss-cn-beijing.aliyuncs.com/avatar/avatar.png', 'Mary1', 'Mary1', 1,
        PARSEDATETIME('01-01-2001', 'dd-MM-YYYY'),
        'any', 0, 1, now(), now()),
       (2, 'https://staff-test.oss-cn-beijing.aliyuncs.com/avatar/avatar.png', 'Mary2', 'Mary2', 1,
        PARSEDATETIME('01-01-2001', 'dd-MM-YYYY'),
        'any', 0, 1, now(), now()),
       (3, 'https://staff-test.oss-cn-beijing.aliyuncs.com/avatar/avatar.png', 'Mary3', 'Mary3', 1,
        PARSEDATETIME('01-01-2001', 'dd-MM-YYYY'),
        'any', 0, 1, now(), now());
