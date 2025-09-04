import Home from '../views/Home.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/',  redirect:'/login' },
    {path: '/manager',  name: 'Manager',  component: () => import('../views/Manager.vue'),children:[
         {path: '/home', name: 'home',meta:{title:'主页'}, component: () => import('../views/Home.vue'), },
    ] },
    {path:'/login',name:'Login',meta:{title:'登录系统'},component:()=>import('../views/Login.vue')}
  ],
})

export default router
