// Modal handlers
function openCreateModal() {
    document.getElementById('createModal').style.display = 'flex';
}

function closeCreateModal() {
    document.getElementById('createModal').style.display = 'none';
}

function openEditModal(userId) {
    document.getElementById('editModal' + userId).style.display = 'flex';
}

function closeEditModal(userId) {
    document.getElementById('editModal' + userId).style.display = 'none';
}

let currentDeleteId = null;

function showDeleteModal(id) {
    currentDeleteId = id;
    document.getElementById('deleteModal').style.display = 'flex';
}

function closeDeleteModal() {
    document.getElementById('deleteModal').style.display = 'none';
    currentDeleteId = null;
}

function confirmDelete() {
    if (currentDeleteId) {
        console.log(`Deleting record ${currentDeleteId}`);
        window.location.href = '?action=delete&id=' + currentDeleteId;
    }
    closeDeleteModal();
}

// Close modal when clicking outside
document.getElementById('deleteModal').addEventListener('click', (e) => {
    if (e.target.id === 'deleteModal') {
        closeDeleteModal();
    }
});