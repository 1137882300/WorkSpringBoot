新建model：site-qu... 快速创建

自动生成基础代码：idea插件：easycode
实体：
```vm
##引入宏定义
$!{define.vm}
## 自定义
#set($table = $tableInfo.name)
#set($tableName = $tool.append($tableInfo.name, "Model"))
#set($tableInfo.name = $tool.append($tableInfo.name, "Model"))

##使用宏定义设置回调（保存位置与文件后缀）
#save("/model", ".java")

##使用宏定义设置包后缀
#setPackageSuffix("model")

##使用全局变量实现默认包导入
$!{autoImport.vm}
import java.io.Serializable;

##使用宏定义实现类注释信息
#tableComment("实体类")
@TableName("$tool.hump2Underline($!{table})")
public class $!{tableName} implements Serializable {
    private static final long serialVersionUID = 1L;
#foreach($column in $tableInfo.fullColumn)
    #if(${column.comment})/**
     * ${column.comment}
     */#end
    #if(${foreach.first})
    @TableId(type = IdType.AUTO)
    #end
    private $!{tool.getClsNameByFullName($column.type)} $!{column.name};
#end

#foreach($column in $tableInfo.fullColumn)
##使用宏定义实现get,set方法
#getSetMethod($column)
#end
}
```






























































































































































































































































