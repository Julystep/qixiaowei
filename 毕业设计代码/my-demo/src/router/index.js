import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import User from '@/components/admin/user/User'
import Index from '@/components/home/Index'
import Welcome from '@/components/home/Welcome'
import Repair from '@/components/user/repair/Repair'
import viewRepair from '@/components/user/repair/viewRepair'
import manageAdmin from '@/components/system/manageAdmin'
import manageBuildings from '@/components/system/manageBuildings'
import BuildingUser from "@/components/admin/building&user/Building&User"
import infomation from '@/components/admin/infomation/infomation'
import manageInfomation from '@/components/admin/infomation/manageInfomation'
import viewInfo from '@/components/user/info/viewInfo'
import manageRepair from '@/components/admin/managerepair/manageRepair'

Vue.use(Router)

export default new Router({
  routes: [{
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
      children: [{
          path: '/welcome',
          component: Welcome
        },
        {
          path: '/user',
          component: User
        },
        {
          path: '/repair',
          component: Repair,
        },
        {
          path: '/viewRepair',
          component: viewRepair,
        },
        {
          path: '/system',
          component: System,
          children: [

          ]
        },
        /**
         * 超级管理员url
         */
        {
          path: '/manageAdmins',
          component: manageAdmin
        },
        {
          path: '/manageBuildings',
          component: manageBuildings
        },
        /*
         次级管理员url
        */
        {
          path: '/buildinganduser',
          component: BuildingUser
        },
        {
          path: '/infomation',
          component: infomation,
        },
        {
          path: "/manageInfomation",
          component: manageInfomation
        },
        {
          path: "/viewInfo",
          component: viewInfo
        },
        {
          path: "/managerepair",
          component: manageRepair
        },
      ]
    },
  ]
})
