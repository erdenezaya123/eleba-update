<template>
	<div class="wrapper">

		<!-- header部分 -->
		<header>
			<p>在线支付</p>
		</header>

		<!-- 订单信息部分 -->
		<h3>订单信息：</h3>
		<div class="order-info">
			<p>
				{{ orders.business.businessName }}
				<i class="fa fa-caret-down" @click="detailetShow"></i>
			</p>
			<p>&#165;{{ (orders.orderTotal - totalpoints *0.1).toFixed(1)}}</p>
		</div>

		<!-- 订单明细部分 -->
		<ul class="order-detailet" v-show="isShowDetailet">
			<li v-for="item in orders.list">
				<p>{{ item.food.foodName }} x {{ item.quantity }}</p>
				<p>&#165;{{ (item.food.foodPrice * item.quantity).toFixed(1) }}</p>
			</li>
			<li>
				<p>配送费</p>
				<p>&#165;{{ orders.business.deliveryPrice }}</p>
			</li>
		</ul>

		<!-- 支付方式部分 -->
		<ul class="payment-type">
			<li>
				<img src="../assets/alipay.png">
				<i class="fa fa-check-circle"></i>
			</li>
		</ul>
		<div class="payment-button">
			<button @click="toSuccess()">确认支付</button>
		</div>
		<div class="notpayment-button">
			<button @click="toNotSuccess()">取消支付</button>
		</div>

		<!-- 底部菜单部分 -->
		<Footer></Footer>
	</div>
</template>

<script>
	import Footer from '../components/Footer.vue';

	export default {
		name: 'Payment',
		data() {
			return {
				orderId: this.$route.query.orderId,
				orders: {
					business: {}
				},
				isShowDetailet: false,
				fromWalletId: 0,
				toWalletId: 0,
				balance: '',
				totalpoints: 0
			}
		},
		created() {
			this.user = this.$getSessionStorage('user');


			// this.$axios.post('OrdersController/pay', {
			// 	params: {
			// 		orderId: this.orderId
			// 	}
			// }).then(response => {
			// 	console.log(response.data);  
			// }).catch(error => {
			// 	console.error(error);
			// });

			this.$axios.get('OrdersController/getOrdersById', {
				params: {
					orderId: this.orderId
				}
			}).then(response => {
				//debugger;
				this.orders = response.data //.result;
			}).catch(error => {
				console.error(error);
			});

		},
		mounted() {
			//这里的代码是实现：一旦路由到在线支付组件，就不能回到订单确认组件。
			//先将当前url添加到history对象中
			history.pushState(null, null, document.URL);
			//popstate事件能够监听history对象的变化
			window.onpopstate = () => {
				this.$router.push({
					path: '/index',
					query: {}
				});
			}
		},
		destroyed() {
			window.onpopstate = null;
		},
		methods: {
			toSuccess() {
				// &status=paids
                this.$axios.get('/OrdersController/turnOrderState' + `?orderId=${this.orderId}`)
                .then(response => {
                    if (response.data == 1 || response.data.success) {
                    alert('支付成功！');
                    this.$router.push({ path: '/paysuccess' });
                    } else {
                    alert('支付状态更新失败，请重试。');
                  }//确保在跳转前调用后端API更新订单状态确保在跳转前调用后端API更新订单状态
                })
                .catch(error => {
                    console.error('更新支付状态失败:', error);
                    alert('支付状态更新失败，请重试。');
                });
            },
			toNotSuccess() {
				alert('支付取消！');
				this.$router.push({
					path: '/index',
				});
			},
			detailetShow() {
				this.isShowDetailet = !this.isShowDetailet;
			},
		},
		components: {
			Footer
		}
	}
</script>

<style scoped>
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

	/****************** 订单信息部分 ******************/
	.wrapper h3 {
		margin-top: 12vw;
		box-sizing: border-box;
		padding: 4vw 4vw 0;

		font-size: 4vw;
		font-weight: 300;
		color: #999;
	}

	.wrapper .order-info {
		box-sizing: border-box;
		padding: 4vw;
		font-size: 4vw;
		color: #666;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order-info p:last-child {
		color: orangered;
	}

	/****************** 订单明细部分 ******************/
	.wrapper .order-detailet {
		width: 100%;
	}

	.wrapper .order-detailet li {
		width: 100%;
		box-sizing: border-box;
		padding: 1vw 4vw;

		display: flex;
		justify-content: space-between;
		align-items: center;
	}

	.wrapper .order-detailet li p {
		font-size: 3vw;
		color: #666;
	}

	/****************** 支付方式部分 ******************/
	.wrapper .payment-type {
		width: 100%;
	}

	.wrapper .payment-type li {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw;

		display: flex;
		justify-content: space-between;
		align-items: center;
		color: #666;
	}

	.wrapper .payment-type li img {
		width: 33vw;
		height: 8.9vw;
	}

	.wrapper .payment-type li .fa-check-circle {
		font-size: 5vw;
		color: #38CA73;
	}

	.wrapper .payment-button {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw;
	}

	.wrapper .payment-button button {
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
	.wrapper .notpayment-button {
		width: 100%;
		box-sizing: border-box;
		padding: 4vw;
	}
	.wrapper .notpayment-button button {
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
</style>