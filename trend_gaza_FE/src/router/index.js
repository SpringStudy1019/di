import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: () => import("../views/TheMainView.vue")
    },
    {
      path: '/category',
      name: 'category',
      component: () => import("../views/TheMainView.vue"),
      redirect: { name: "main" },
      children: [
        {
          path: "culture",
          name: "category-culture",
          component: () => import("@/components/category/TheCulture.vue"),
        },
      ]
    },
    {
      path: '/attraction',
      name: 'attraction',
      component: () => import("../views/TheAttractionView.vue"),
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
    {
      path: "/attraction",
      name: "attraction",
      component: () => import("../views/TheAttractionView.vue"),
      children: [
        {
          path: "view/:attractionIdx",
          name: "attraction-view",
          component: () => import("@/components/attraction/AttractionDetail.vue"),
        }
      ]
    }
    
  ]
})

export default router
