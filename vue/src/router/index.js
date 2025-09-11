import Home from '../views/Home.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/',  redirect:'/login' },
    {path: '/manager',  name: 'Manager',  component: () => import('../views/Manager.vue'),children:[
         {path: 'home', name: 'home',meta:{title:'主页'}, component: () => import('../views/Home.vue'), },
         {path: 'employee', name: 'employee',meta:{title:'员工信息页面'}, component: () => import('../views/Employee.vue'), },
          {path: 'admin', name: 'admin',meta:{title:'管理员息页面'}, component: () => import('../views/Admin.vue'), },
        {path: 'data', name: 'data',meta:{title:'数据展示页面'}, component: () => import('../views/Data.vue'), },
         
    ] },
    {path:'/login',name:'Login',meta:{title:'登录系统'},component:()=>import('../views/Login.vue')}
  ],
})

export default router
