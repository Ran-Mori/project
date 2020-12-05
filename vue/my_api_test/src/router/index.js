import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import PathParaPass from "@/views/PathParaPass";
import ObjectPassByStore from "@/views/ObjectPassByStore";
import ObjectPassByRouter from "@/views/ObjectPassByRouter";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/pathparapass',
    name: "第一条结果",
    component: PathParaPass
  },
  {
    path: '/objectpassbystore',
    name: '通过store传递对象',
    component: ObjectPassByStore
  },
  {
    path: '/objectpassbyrouter',
    name: '通过router传递对象',
    component: ObjectPassByRouter
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
