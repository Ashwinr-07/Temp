.modal {
  @apply bg-white rounded-lg p-6 shadow-md;
}

.modal-heading-container {
  @apply flex justify-between items-center border-b pb-2;
}

.modal-heading-container-value {
  @apply text-xl font-bold;
}

.modal-heading-container-close-icon {
  @apply cursor-pointer text-gray-500 hover:text-gray-700;
}

.modal-body-container {
  @apply mt-4;
}

.modal-search-pagination {
  @apply flex flex-row justify-between items-center mb-4;
}

.modal-content-container {
  @apply h-[60vh] overflow-auto flex flex-col gap-4 mt-4;
}

.modal-action-container {
  @apply flex justify-end gap-2 mt-4;
}

.no-permissions {
  @apply w-full flex flex-col items-center justify-center;
}

.no-permissions-text {
  @apply mt-8 text-2xl font-bold;
}
