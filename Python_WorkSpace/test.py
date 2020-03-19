import tensorflow as tf

session = tf.Session()
# X轴参数
w = tf.Variable([.3], dtype=tf.float32)
# 偏移量
b = tf.Variable([-.3], dtype=tf.float32)
# x轴
x = tf.placeholder(tf.float32)
# 一元线性模型
linear_model = w * x + b
# 实际值
y = tf.placeholder(tf.float32)
# 观测值和实际值的误差的平方差
squared_deltas = tf.square(linear_model - y)
# 最少二乘法。损失函数
loss = tf.reduce_sum(squared_deltas)
# 优化器.优化函数
optimizer = tf.train.GradientDescentOptimizer(0.01)
train = optimizer.minimize(loss)
# 初始化所有的变量
init = tf.global_variables_initializer()
session.run(init)
# 开始训练。训练的过程就是结合优化函数使损失函数的损失最少
x_train = [1,2,3,4]
y_train = [0, -1,-2,-3]
for i in range(1000):
    session.run(train, {x: x_train, y: y_train})
# 训练的结果
curr_W, curr_b, curr_loss = session.run([w, b, loss], {x: x_train, y: y_train})
print("W: %s b: %s loss: %s"%(curr_W, curr_b, curr_loss))