import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  base: import.meta.env.BASE_URL,
  routes: [
    {
        path: '/content',
        name: 'content',
        component: ()=> import('../views/Content.vue')
    },
  ]
})

export default router
