.modal-container {
  @apply bg-white rounded-lg p-6 shadow-md;
}

.modal-header {
  @apply flex justify-between items-center border-b pb-2;
}

.modal-title {
  @apply text-xl font-bold;
}

.modal-close {
  @apply cursor-pointer text-gray-500 hover:text-gray-700;
}

.modal-body {
  @apply mt-4;
}

.modal-search-pagination {
  @apply flex justify-between items-center mb-4;
}

.modal-content {
  @apply h-[60vh] overflow-auto flex flex-col gap-4 mt-4;
}

.modal-footer {
  @apply flex justify-end gap-2 mt-4;
}

.no-permissions {
  @apply w-full flex flex-col items-center justify-center;
}

.no-permissions-text {
  @apply mt-8 text-2xl font-bold;
}
