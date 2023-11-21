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
      path: '/plan',
      name: 'plan',
      component: () => import("../views/ThePlanView.vue")
    },
    {
      path: '/plan/list',
      name: 'plan-list',
      component: () => import("@/components/plan/PlanList.vue")
    },
    {
      path: '/category',
      name: 'category',
      component: () => import("../views/TheMainView.vue"),
    },
    {
      path: '/attraction',
      name: 'attraction',
      component: () => import("../views/TheAttractionView.vue"),
    },
    {
      path: "/user",
      name: "user",
      component: () => import("@/views/TheUserView.vue"),
      children: [
        {
          path: "login",
          name: "user-login",
          component: () => import("@/components/users/UserLogin.vue"),
        },
        {
          path: "search/:word",
          name: "search-result",
          component: () => import("@/components/search/SearchList.vue"),
        },
        {
          path: "join",
          name: "user-join",
          component: () => import("@/components/users/UserRegister.vue"),
        },
        {
          path: "mypage",
          name: "user-mypage",
          component: () => import("@/components/users/UserMyPage.vue"),
        },
        {
          path: "/:userId",
          name: "user-yourpage",
          component: () => import("@/components/users/UserYourPage.vue"),
        },
      ],
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
        {
          path: "write/:contentId",
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
    },
    {
      path: "/category/:contentTypeId",
      name: "category-view",
      component: () => import("@/components/category/TheCategoryDetail.vue"),
    },
    {
      path: '/notification',
      name: 'notification',
      component: () => import("@/components/users/UserNotification.vue"),
    },
    
  ]
})

export default router
