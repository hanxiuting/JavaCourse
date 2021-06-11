建表SQL

```mysql
CREATE TABLE `tb_user_base` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号，唯一',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名称',
  `password` varchar(100) NOT NULL COMMENT '登录密码：加密形式',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` varchar(100) DEFAULT NULL COMMENT '性别',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone_num` decimal(11,0) NOT NULL DEFAULT '0' COMMENT '手机号：加密形式',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_mark` tinyint(1) DEFAULT '1' COMMENT '软删除标识：0-删除 1-有效',
  `image` varchar(500) DEFAULT NULL COMMENT '头像路径',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表'
```

```mysql
CREATE TABLE `tb_user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户id：user_base.id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `province` varchar(100) NOT NULL COMMENT '省名称',
  `city` varchar(100) NOT NULL COMMENT '市区名称',
  `detail_address` varchar(500) NOT NULL COMMENT '详细地址',
  `phone_num` decimal(11,0) NOT NULL DEFAULT '0' COMMENT '收货人手机号',
  `delete_mark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除 1-有效',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户地址表'
```

```mysql
CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '商品类别名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_mark` tinyint(2) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除 1-有效',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类别表 '
```

```mysql
CREATE TABLE `tb_category_spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `category_id` bigint(20) NOT NULL COMMENT '商品类别id：category.id',
  `name` varchar(100) NOT NULL COMMENT '产品名称',
  `title` varchar(30) NOT NULL COMMENT '商品描述',
  `subtitle` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '副标题',
  `brand` varchar(100) DEFAULT NULL COMMENT '品牌',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_mark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除 1-有效',
  `desc` text COMMENT '通用属性',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品通用属性表'
```


```mysql
CREATE TABLE `tb_category_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `spuId` bigint(20) NOT NULL COMMENT '产品id：category_spu.id',
  `category_id` bigint(20) NOT NULL COMMENT '商品类别id：category.id',
  `price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `weight` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '商品重量',
  `place` varchar(100) NOT NULL COMMENT '发货地',
  `item_desc` varchar(1000) DEFAULT NULL COMMENT '商品描述',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_mark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除 1-有效',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情表'
```
```mysql
CREATE TABLE `tb_order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `mount` decimal(8,2) NOT NULL COMMENT '订单金额',
  `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '订单状态：枚举值',
  `user_id` bigint(20) NOT NULL COMMENT '用户id：user_base.id',
  `pay_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付类型：枚举值',
  `pay_no` decimal(22,0) NOT NULL DEFAULT '0' COMMENT '支付流水号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '订单更新时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `delete_mark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除，1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单基础信息表'
```


```mysql
CREATE TABLE `tb_order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `order_id` bigint(20) NOT NULL COMMENT '订单id：order_info.id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id：user_base.id',
  `item_id` bigint(20) NOT NULL COMMENT '商品id：category_item.id',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `price` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_mark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除，1-有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表'
```


```mysql
CREATE TABLE `tb_trade_paylog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `order_id` bigint(20) NOT NULL COMMENT '订单编号：order_info.id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id:user_base.id',
  `pay_no` decimal(22,0) NOT NULL DEFAULT '0' COMMENT '交易流水号',
  `total` decimal(8,3) NOT NULL DEFAULT '0.000' COMMENT '支付金额',
  `pay_type` tinyint(4) NOT NULL COMMENT '支付类型：枚举值',
  `pay_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '支付时间',
  `update_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `delete_mark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除，1-有效',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易记录日志表'
```
