<template>
	<div class="wrapper">
		<!-- header部分 -->
		<header>
			<div class="fa fa-angle-left" @click="goback()"></div>

			<p>个人中心</p>
		</header>

		<ul class="userinfoa">
			<li>
				<p><i class="fa fa-expeditedssl"></i>修改</p>
				<i class="fa fa-angle-right"></i>
			</li>
		</ul>
		<ul class="userinfob">
			<li>
				<p><i class="fa fa-expeditedssl"></i>修改密码</p>
				<i class="fa fa-angle-right"></i>
			</li>
			<li>
				<p><i class="fa fa-sign-out"></i>退出登录</p>
				<i class="fa fa-angle-right"></i>
			</li>

		</ul>


		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>

</template>

<script>
	import Footer from '../components/Footer.vue';
	export default {
		name: 'MyInfo',
		data: function() {
			return {

				user: {}
			}
		},

		methods: {
			goback() {
				this.$router.go(-1);
			},
			// 跳转到修改密码页面
			changePwd() {
				this.$router.push('/changePwd');
			},
			// 跳转到头像修改页面
			changeImg: function(userId) {
				this.$router.push({
					path: '/myImg',
					query: {
						userId: userId
					}
				});
			},
			// 获取用户信息
			getUserById: function(userId) {
				let that = this;
				let url = 'UserController/getUserById';
				this.$axios.post(url, this.$qs.stringify({
						userId
					}))
					.then(resp => {
						that.user = resp.data;
					})
					.catch(err => {
						console.log(err);
					})
			},
			// 退出登录 (实际退出操作,依赖响应拦截器)
			logOut(user) {
				this.$removeSessionStorage('user');
				let result = this.$getSessionStorage('user');
				if (result == null) {
					alert('退出成功');
					this.$router.push('/index');
				} else {
					alert('退出失败')
				}
			}
		},
		components: {
			Footer
		},
		created: function() {
			let user = this.$getSessionStorage('user');
			if (user != null) {
				this.getUserById(user.userId);
			}
		}

	}
</script>

<style scoped>
	/********************* 总容器 *************************/
	.wrapper {
		width: 100%;
		height: 100%;
		margin: 0 0 0 0;
		overflow: hidden hidden;
	}

	/******************** header ************************/
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
	/*************** userinfo ***************/
	
	.wrapper .userinfob {
		width: 100%;
		padding-bottom: 14vw;
	}

	.wrapper .userinfob li {
		width: 100%;
		height: 12vw;
		box-sizing: border-box;
		padding: 0 4vw;
		margin-top: 3.6vw;
		background-color: #fff;

		display: flex;
		justify-content: space-between;
		align-items: center;

		user-select: none;
		cursor: pointer;
	}

	.wrapper .userinfob li p {
		font-size: 4.2vw;
		color: #666;
	}

	.wrapper .userinfob li i {
		/*左右两边的图标样式一样*/
		font-size: 5vw;
		color: #0097FF;
		margin-right: 3vw;
	}

</style>