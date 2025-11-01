<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<div class="fa fa-angle-left" @click="goback()"></div>
			<p>密码修改</p>
		</header>
		<!-- 表单部分 -->
		<ul class="form-box">
			<li>
				<div class="title">
					用户名：
				</div>
				<div class="content">
					{{user.userName}}
				</div>
			</li>
			<li>
				<div class="title">
					原密码：
				</div>
				<div class="content">
					<input v-model="user.oldPassword" type="password" placeholder="密码">
				</div>
			</li>
			<li>
				<div class="title">
					新密码：
				</div>
				<div class="content">
					<input v-model="user.new1Password" type="password" placeholder="确认密码">
				</div>
			</li>
			<li>
				<div class="title">
					确认密码：
				</div>
				<div class="content">
					<input v-model="user.new2Password" type="password" placeholder="确认密码">
				</div>
			</li>
		</ul>
		<div class="button-login">
			<button @click=" updatePwd()">修改</button>
		</div>

		<div class="button-tishi">提示：密码长度至少为6位</div>
		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';

	export default {
		name: 'Register',
		components: {
			Footer
		},
		data() {
			return {
				user: {
					userId: '',
					userName: '',
					password: '',
					oldPassword: '',
					new1Password: '',
					new2Password: '',
				}
			}
		},
		created() {
			this.user = this.$getSessionStorage("user");

		},
		methods: {
			goback() {
				this.$router.go(-1);
			},
			// 获取用户信息
			async updatePwd() {
				let user = this.user;
				// 1. 输入是否为空
				if (user.oldPassword == null ||
					user.new1Password == null ||
					user.new2Password == null) {
					alert("输入不能为空!");
					return;
				} else if (!/^[A-Za-z0-9]{6,}$/.test(user.new1Password)) {
					alert('密码长度至少为6位！');
					return;
				} else if (user.oldPassword != user.password) {
					alert("输入的密码与原密码不符！");
					return;
				}
				// 3. 检查原密码是否一致
				else if (user.oldPassword == user.new1Password) {
					alert("修改密码与原密码相同!");
					return;
				}
				// 4. 检查两次密码输入是否一致

				//console.log('new1Password:  ' + user.new1Password);
				//console.log('new2Password:  ' + user.new2Password);
				else if (user.new1Password != user.new2Password) {
					alert("两次密码输入不一致!");
					return;
				}

				// 5. 提交表单,注册账号
				// 注册成功 则带着注册的账号密码跳转到登陆页面
				this.$axios.post('UserController/modifyPassword', this.$qs.stringify({
					userId: user.userId,
					oldPassword: user.password,
					newPassword: user.new1Password
				})).then(response => {
					if (response.data > 0) {
						alert('密码修改成功！');
						this.$router.push('/');
					} else {
						alert('密码修改失败！');
					}
				}).catch(error => {
					console.error(error);
				});
			}
		}

	}
</script>

<style scoped="scoped">
	/****************** 总容器 ******************/
	.wrapper {
		width: 100%;
		height: 100%;
	}

	/****************** header部分 ******************/
	.wrapper header {
		width: 100%;
		height: 12vw;
		background-color: #0097FF;
		color: #fff;
		font-size: 4.8vw;

		position: fixed;
		left: 0;
		top: 0;
		z-index: 1000;

		display: flex;
		justify-content: center;
		align-items: center;
	}

	.wrapper header .fa-angle-left {
		margin: 0% 80% 0 0;
		position: absolute;
	}

	/****************** 表单部分 ******************/
	.wrapper .form-box {
		width: 100%;
		margin-top: 12vw;
	}

	.wrapper .form-box li {
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
		display: flex;
		align-items: center;
	}

	.wrapper .form-box li .title {
		flex: 0 0 18vw;
		font-size: 3vw;
		font-weight: 700;
		color: #666;
	}

	.wrapper .form-box li .content {
		flex: 1;
	}

	.wrapper .form-box li .content input {
		border: none;
		outline: none;
		width: 100%;
		height: 4vw;
		font-size: 3vw;
	}

	.wrapper .button-login {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
	}

	.wrapper .button-login button {
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;
		color: #fff;
		background-color: #38CA73;
		border-radius: 4px;
		border: none;
		outline: none;
	}

	.wrapper .button-register {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw 3vw 0 3vw;
	}

	.wrapper .button-register button {
		width: 100%;
		height: 10vw;
		font-size: 3.8vw;
		font-weight: 700;
		color: #666;
		background-color: #EEE;
		border-radius: 4px;
		border: none;
		outline: none;
		border: solid 1px #DDD;
	}

	/*************密码提示部分************/
	.wrapper .button-tishi {
		width: 100%;
		height: 5vw;
		font-size: 3.5vw;
		font-weight: 600;
		color: #666;
		background-color: #fff;
		border-radius: 4px;
		border: none;
		outline: none;

	}
</style>