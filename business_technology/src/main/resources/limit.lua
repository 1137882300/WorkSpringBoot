-- KEYS和ARGV是两个特殊的变量，用于获取传递给Lua脚本的键和参数。
-- KEYS[1]表示传递给Lua脚本的第一个键
-- ARGV[1]表示传递给Lua脚本的第一个参数
-- local关键字用于声明局部变量
local key = "rate.limit:" .. KEYS[1]
-- 将ARGV[1]的值转换为数字
local limit = tonumber(ARGV[1])
-- 执行Redis的GET命令，从Redis中获取key对应的值
local current = tonumber(redis.call('get', key) or "0")

if current + 1 > limit then
  return 0
else
   -- 没有超阈值，将当前访问数量+1，并设置2秒过期（可根据自己的业务情况调整）
   -- 表示对指定的key进行增加操作
   redis.call("INCRBY", key,"1")
   -- 表示对指定的key设置过期时间
   redis.call("expire", key,"2")
   return current + 1
end