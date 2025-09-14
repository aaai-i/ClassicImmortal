import Home from '../views/Home.vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/',  redirect:'/login' },
    {path: '/manager',  name: 'Manager',  component: () => import('../views/Manager.vue'),children:[
         {path: 'home', name: 'Home',meta:{title:'主页'}, component: () => import('../views/Home.vue')},
         {path: 'employee', name: 'Employee',meta:{title:'员工信息页面'}, component: () => import('../views/Employee.vue')},
          {path: 'admin', name: 'Admin',meta:{title:'管理员息页面'}, component: () => import('../views/Admin.vue')},
        {path: 'data', name: 'Data',meta:{title:'数据展示页面'}, component: () => import('../views/Data.vue')},
        {path:'person',name:'Person',meta:{title:'个人信息'},component:()=>import('../views/Person.vue')},
         
    ] },
    {path:'/login',name:'Login',meta:{title:'登录系统'},component:()=>import('../views/Login.vue')},
    {path:'/register',name:'Register',meta:{title:'欢迎注册'},component:()=>import('../views/Register.vue')}
  ]
})

export default router
