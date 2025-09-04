<script setup>
import { reactive } from 'vue';

const a=reactive({
    name:123
})
</script>

<template>
  <{{ a.name }}
</template>
