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
      path: '/attraction',
      name: 'attraction',
      component: () => import("../views/TheAttractionView.vue"),
    },
    {
      path: '/category',
      name: 'category',
      component: () => import("../views/TheMainView.vue"),
      redirect: { name: "main" },
      children: [
        {
          path: "culture",
          name: "review-culture",
          component: () => import("@/components/category/TheCulture.vue"),
        },
      ]
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
          children: [
            {
              path: "",
              name: "comment",
              component: () => import("@/components/comment/CommentList.vue"),
            }
          ]
        },
        {
          path: "write",
          name: "review-write",
          component: () => import("@/components/review/ReviewWrite.vue"),
        },
        {
          path: "modify/:reviewIdx",
          name: "review-modify",
          component: () => import("@/components/review/ReviewModify.vue"),
        }
      ]
    },
    
  ]
})

export default router
