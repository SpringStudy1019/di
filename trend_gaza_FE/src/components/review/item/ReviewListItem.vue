<script setup>
defineProps({ review: Object });
</script>

<template>
    <tr class="text-center">
        <th scope="row">{{ review.reviewIdx }}</th>
        <td class="text-start">
        <router-link
            :to="{ name: 'review-view', params: { reviewIdx: review.reviewIdx } }"
            class="article-title link-dark"
        >
            {{ review.title }}
        </router-link>
        </td>
        <td>{{ review.score }}</td>
        <td>{{ review.content }}</td>
        <td>{{ review.registerDate }}</td>
        <td>{{ review.modifyDate }}</td>
        <td>{{ review.userId }}</td>
        <td>{{ review.contentId }}</td>
    </tr>
</template>

<style scoped>

</style>