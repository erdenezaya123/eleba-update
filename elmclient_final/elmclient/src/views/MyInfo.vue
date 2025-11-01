<template>
	<div class="wrapper">
        <heade>
			<p>我的</p>
		</heade>
		
		<header>
			<img :src="user.userImg">
		</header>
		<!-- 个人信息部分 -->
		<ul class="userinfo">
			<li @click="changeImg()">
				<p>更换头像</p>			
				<i class="fa fa-angle-right"></i>
			</li>
			<li @click="changePwd">
				<p>修改密码</p>
				<i class="fa fa-angle-right"></i>
			</li>
			<li @click="logOut()">
				<p>退出登录</p>
				<i class="fa fa-angle-right"></i>
			</li>
		
		</ul>
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue'
	export default {
		name: 'MyInfo',
		data: function() {
			return {
				user:{}
			}
		},
		methods: {
			// 跳转到修改密码页面
			changePwd() {
				this.$router.push('/changePwd');
			},
			// 跳转到头像修改页面
			changeImg: function() {
				this.$router.push({
					path: '/myImg',
					query: {
						userId: this.user.userId
					}
				});
			},
			toMyCredit: function() {
				this.$router.push('/credit');
			},
			toMyWallet: function() {
				this.$router.push('/myWallet');
			},
			// 获取用户信息
			getUserById: function(userId) {
				let that = this;
				let url = 'UserController/getUserById';
				url += `?userId=${encodeURIComponent(userId)}`
				this.$axios.get(url)
					.then(resp => {
						if (typeof resp.data !== 'object') return
						that.user = resp.data;
					})
					.catch(err => {
						console.log(err);
					})
			},
			// 退出登录 (实际退出操作,依赖响应拦截器)
			logOut(user) {
				this.$removeSessionStorage('user');
				let result= this.$getSessionStorage('user');
				if(result==null) {
					alert('退出成功');
                    this.$router.push('/index');  				
				}
				else{ alert('退出失败')}
			}
		},
		components: {
			Footer: Footer
		},
		mounted: function() {
			let user = this.$getSessionStorage('user');
			if (user != null) {
				this.user = user
				this.getUserById(user.userId);
			}
		}
	}
</script>

<style scoped="scoped">
	/*************** 总容器 ***************/
	.wrapper {
		width: 100%;
		height: 100%;
		background-color: #F5F5F5;
	}

	.wrapper heade{
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

	/*************** header ***************/
	.wrapper header {
		width: 100%;
		height: 30vw;
		padding-top: 12vw;
		background-color: #0097FF;
		display: flex;
		align-items: center;
		color: #fff;
		font-size: 4.8vw;
	}

	.wrapper header img {
		width: 16vw;
		height: 16vw;
		border-radius: 8vw;
		margin-left: 5vw;
	}

	.wrapper header p {
		margin-left: 5vw;
		color: #fff;
		font-size: 5.6vw;
		font-weight: 700;
	}

	/*************** userinfo ***************/
	
	.wrapper .userinfo {
		width: 100%;
		padding-top: 2vw;
		padding-bottom: 14vw;
	}

	.wrapper .userinfo li {
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

	.wrapper .userinfo li p {
		font-size: 4.2vw;
		color: #666;
	}

	.wrapper .userinfo li i {
		/*左右两边的图标样式一样*/
		font-size: 5vw;
		color: #0097FF;
		margin-right: 3vw;
	}
</style>