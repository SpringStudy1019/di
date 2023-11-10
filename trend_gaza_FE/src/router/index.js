import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      //component: TheMainView
      component: () => import("../views/TheMainView.vue"),
    },
    {
      path: '/review',
      name: 'review',
      component: () => import("../views/TheReviewView.vue"),
      redirect: { name: "review-list" },
      children: [
        {
          path: "list",
          name: "review-list",
          component: () => import("@/components/review/ReviewList.vue"),
        },
        {
          path: "view/:reviewIdx",
          name: "review-view",
          component: () => import("@/components/review/ReviewDetail.vue"),
        },
      ]
    },
    
  ]
})

export default router
