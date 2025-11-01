<template>
  <teleport to="body">
    <div class="popup-overlay" :style="props.style" v-if="visible">
      <div class="mask" @click.self.stop="maskHandle()" @wheel.stop="preventScroll"></div>
      <div class="modal">
        <slot></slot>
      </div>
    </div>
  </teleport>
</template>

<script setup>
import { ref } from "vue";

const props = defineProps({
  style: {
    type: Object,
    default: () => {},
  },
  mask: {
	type: Boolean,
	default: true
  }
});

const emit = defineEmits(['update:visible', 'closed']);

const visible = ref(false);

const preventScroll = (event) => {
	event.preventDefault()
}

const maskHandle = () => {
	if (props.mask) {
		closeModal()
	}
}

const closeModal = () => {
  visible.value = false;
  emit('update:visible', visible.value);
  emit('closed')
};

const openModal = () => {
  visible.value = true;
  emit('update:visible', visible.value);
};

defineExpose({ openModal, closeModal });
</script>

<style scoped>
.popup-overlay {
  position: fixed;
  width: 100vw;
}
.mask {
  position: fixed;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
  width: 100vw;
  height: 100vh;
  z-index: -1;
  touch-action: none;
}
.modal {
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  background-color: #ffffff;
  z-index: 1;
  width: 100%;
  min-height: 20vh;
}
</style>
