import Vue from 'vue'
import App from './App.vue'
import router from './router'

import 'font-awesome/css/font-awesome.min.css'
import axios from 'axios'
import qs from 'qs'
import {
	getCurDate,
	setSessionStorage,
	getSessionStorage,
	removeSessionStorage,
	setLocalStorage,
	getLocalStorage,
	removeLocalStorage
} from './common.js'

//Vue.config.productionTip = false

//设置axios的基础url部分
axios.defaults.baseURL = 'http://localhost:8080/';

//将axios挂载到vue实例上，使用时就可以 this.$axios 这样使用了
app.config.globalPrototype.$axios = axios;

app.config.globalPrototype.$qs = qs;

app.config.globalPrototype.$setSessionStorage = setSessionStorage;
app.config.globalPrototype.$getSessionStorage = getSessionStorage;
app.config.globalPrototype.$removeSessionStorage = removeSessionStorage;
app.config.globalPrototype.$setLocalStorage = setLocalStorage;
app.config.globalPrototype.$getLocalStorage = getLocalStorage;
app.config.globalPrototype.$getCurDate = getCurDate;
app.config.globalPrototype.$removeLocalStorage = removeLocalStorage;

router.beforeEach(function(to,from,next){
	let user = sessionStorage.getItem('user');
	//除了登录、注册、首页、商家列表、商家信息之外，都需要判断是否登录
	if(!(to.path=='/'||to.path=='/index'||to.path=='/businessList'||to.path=='/businessInfo'||to.path=='/login'||to.path=='/register')){
		if(user==null){
			router.push('/login');
			location.reload();
		}
	}
	next();
});

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
