建表SQL

```mysql
CREATE TABLE `user_base` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号，唯一',
  `loginName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名称',
  `password` varchar(100) NOT NULL COMMENT '登录密码：加密形式',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `gender` varchar(100) DEFAULT NULL COMMENT '性别',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phoneNum` decimal(11,0) NOT NULL COMMENT '手机号：加密形式',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleteMark` tinyint(1) DEFAULT '1' COMMENT '软删除标识：0-删除 1-有效',
  `image` varchar(500) DEFAULT NULL COMMENT '头像路径',
  `updateBy` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基础信息表'
```

```mysql
CREATE TABLE `user_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userId` bigint(20) NOT NULL COMMENT '用户id：user_base.id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `province` varchar(100) NOT NULL COMMENT '省名称',
  `city` varchar(100) NOT NULL COMMENT '市区名称',
  `detailAddress` varchar(500) NOT NULL COMMENT '详细地址',
  `phoneNum` decimal(11,0) NOT NULL COMMENT '收货人手机号',
  `deletemark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除 1-有效',
  `updateBy` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `trade_address_user_base_fk` (`userId`),
  CONSTRAINT `trade_address_user_base_fk` FOREIGN KEY (`userId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户地址表'
```

```mysql
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '商品类别名称',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleteMark` tinyint(1) DEFAULT NULL COMMENT '软删除：0-删除 1-有效',
  `creator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
  `updateBy` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类别表
```

```mysql
CREATE TABLE `category_spu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `categoryId` bigint(20) NOT NULL COMMENT '商品类别id：category.id',
  `name` varchar(100) NOT NULL COMMENT '产品名称',
  `title` varchar(30) NOT NULL COMMENT '商品描述',
  `subTitle` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '副标题',
  `brand` varchar(100) DEFAULT NULL COMMENT '品牌',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  `deleteMark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除 1-有效',
  `desc` text COMMENT '通用属性',
  `updateBy` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `category_spu_category_fk` (`categoryId`),
  CONSTRAINT `category_spu_category_fk` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品通用属性表'
```


```mysql
CREATE TABLE `category_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `spuId` bigint(20) NOT NULL COMMENT '产品id：category_spu.id',
  `categoryId` bigint(20) NOT NULL COMMENT '商品类别id：category.id',
  `price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `weight` decimal(6,2) NOT NULL COMMENT '商品重量',
  `place` varchar(100) NOT NULL COMMENT '发货地',
  `desc` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '商品描述',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleteMark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除 1-有效',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updateBy` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  KEY `category_item_category_spu_fk` (`spuId`),
  KEY `category_spu_category_fk` (`categoryId`),
  CONSTRAINT `category_item_category_fk` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`),
  CONSTRAINT `category_item_category_spu_fk` FOREIGN KEY (`spuId`) REFERENCES `category_spu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情表'
```
```mysql
CREATE TABLE `order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `mount` decimal(8,2) NOT NULL COMMENT '订单金额',
  `status` tinyint(1) NOT NULL COMMENT '订单状态：枚举值',
  `userId` bigint(20) NOT NULL COMMENT '用户id：user_base.id',
  `payType` tinyint(1) NOT NULL COMMENT '支付类型：枚举值',
  `payNo` decimal(22,0) NOT NULL COMMENT '支付流水号',
  `createTime` datetime DEFAULT NULL COMMENT '订单创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '订单更新时间',
  `updateBy` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `deleteMark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除，1-有效',
  PRIMARY KEY (`id`),
  KEY `order_info_user_base_fk` (`userId`),
  CONSTRAINT `order_info_user_base_fk` FOREIGN KEY (`userId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单基础信息表'
```


```mysql
CREATE TABLE `order_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `orderId` bigint(20) NOT NULL COMMENT '订单id：order_info.id',
  `userId` bigint(20) NOT NULL COMMENT '用户id：user_base.id',
  `itemId` bigint(20) NOT NULL COMMENT '商品id：category_item.id',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `createTime` datetime DEFAULT NULL COMMENT '订单创建时间',
  `updateBy` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `deleteMark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除，1-有效',
  PRIMARY KEY (`id`),
  KEY `order_detail_order_info_fk` (`orderId`),
  KEY `order_detail_user_base_fk` (`userId`),
  KEY `order_detail_category_item_fk` (`itemId`),
  CONSTRAINT `order_detail_category_item_fk` FOREIGN KEY (`itemId`) REFERENCES `category_item` (`id`),
  CONSTRAINT `order_detail_order_info_fk` FOREIGN KEY (`orderId`) REFERENCES `order_info` (`id`),
  CONSTRAINT `order_detail_user_base_fk` FOREIGN KEY (`userId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表'
```


```mysql
CREATE TABLE `trade_paylog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `orderId` bigint(20) NOT NULL COMMENT '订单编号：order_info.id',
  `userId` bigint(20) NOT NULL COMMENT '用户id:user_base.id',
  `payNo` decimal(22,0) NOT NULL COMMENT '交易流水号',
  `total` decimal(8,3) NOT NULL COMMENT '支付金额',
  `payType` tinyint(4) NOT NULL COMMENT '支付类型：枚举值',
  `payTime` datetime NOT NULL COMMENT '支付时间',
  `updateBy` varchar(50) DEFAULT NULL COMMENT '更新人',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `deleteMark` tinyint(1) NOT NULL DEFAULT '1' COMMENT '软删除：0-删除，1-有效',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `trade_paylog_order_info_fk` (`orderId`),
  KEY `trade_paylog_user_base_fk` (`userId`),
  CONSTRAINT `trade_paylog_order_info_fk` FOREIGN KEY (`orderId`) REFERENCES `order_info` (`id`),
  CONSTRAINT `trade_paylog_user_base_fk` FOREIGN KEY (`userId`) REFERENCES `user_base` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交易记录日志表'
```
