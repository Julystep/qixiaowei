import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/user/Login'
import Index from '@/components/Index'
import User from '@/components/user/User'
import Buildings from '@/components/Buildings'
import House from '@/components/House'
import Service from '@/components/Service'
import Pay from '@/components/Pay'
import Repair from '@/components/repair/Repair'
import Novelty from '@/components/information/Novelty'
import Lostandfound from '@/components/information/Lostandfound'
import Announcement from '@/components/information/Announcement'
import System from '@/components/system/System'
import Permission from '@/components/system/Permission'
import Device from '@/components/system/Device'
import Welcome from '@/components/Welcome'
 
Vue.use(Router)

export default new Router({
  routes: [
    {
     //自动重定向到登陆页面
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/index',
      component: Index,
      redirect: '/welcome',
      children: [
        {
          path: '/welcome',
          component: Welcome
        },
        {
          path: '/user',
          component: User
        },
        {
          path: '/buildings',
          component: Buildings
        },
        {
          path: '/house',
          component: House
        },
        {
          path: '/service',
          component: Service
        },
        {
          path: '/pay',
          component: Pay
        },
        {
          path: '/repair',
          component: Repair,
        },
        {
          path: '/announcement',
          component: Announcement,
        },
        {
          path: '/lostandfound',
          component: Lostandfound
        },
        {
          path: '/novelty',
          component: Novelty
        },
        {
          path: '/system',
          component: System,
          children: [
            
          ]
        },
        {
           path: '/permission',
           component: Permission
        },
        {
          path: '/device',
          component: Device
        },
      ]
    },
  ]
})
