import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

//未登录时，跳登录页
router.beforeEach((to, from, next) => {
    let loginUserJson = window.sessionStorage.getItem('loginUser');
    console.log(to)
    if (to.path == '/login'){
        next();
    }else {
        if (loginUserJson == null){ //未登录
            console.log(loginUserJson)
            next("/login");
        }else {
            next();
        }
    }
})

Vue.config.productionTip = false
Vue.use(ElementUI)
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
