CREATE DATABASE seckill;

use seckill;

CREATE TABLE seckill(
  `seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) NOT NULL COMMENT '商品名字',
  `number` int NOT NULL COMMENT '商品库存',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `start_time` timestamp NOT NULL COMMENT '秒杀开启时间',
  `end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY(seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表')
CREATE TABLE seckill(
  `seckill_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
  `name` varchar(120) NOT NULL COMMENT '商品名称',
  `number` int NOT NULL COMMENT '库存数量',
  `start_time` timestamp NOT NULL COMMENT '秒杀开启时间',
  `end_time` timestamp NOT NULL COMMENT '秒杀结束时间',
  `create_time` timestamp NOT NULL /*DEFAULT CURRENT_TIMESTAMP*/ COMMENT '创建时间',
PRIMARY KEY(seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

  'seckill_id' bigint NOT NULL AUTO_INCREMENT comment '商品库存ID',
'name' varchar(120) NOT NULL comment '商品名字',
  'number' int NOT NULL comment '商品库存',
'create_time' TIMESTAMP NOT NULL comment '创建时间',
  'start_time' TIMESTAMP NOT NULL comment '开始时间',
  'end_time' TIMESTAMP NOT NULL comment '结束时间',
  PRIMARY KEY(seckill_id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  KEY idx_create_time(create_time)


ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表')