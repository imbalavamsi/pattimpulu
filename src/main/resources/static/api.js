const API_BASE_URL = "/api/checklist";

async function fetchChecklist() {
    const response = await fetch(API_BASE_URL);
    return response.json();
}

async function createChecklistItem(item) {
    const response = await fetch(API_BASE_URL, {
        method: 'POST',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(item)
    });
    return response.json();
}

async function updateChecklistItemAPI(id, item) {
    const response = await fetch(`${API_BASE_URL}/${id}`, {
        method: 'PUT',
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(item)
    });

    if (!response.ok) {
        console.error("Error updating item:", await response.text());
    }
    return response.json();
}


async function deleteChecklistItem(id) {
    await fetch(`${API_BASE_URL}/${id}`, { method: 'DELETE' });
}

async function getChecklistItemById(id) {
    const response = await fetch(`${API_BASE_URL}/${id}`);
    return response.json();
}

function searchTasks() {
    const keyword = document.getElementById("searchBox").value.trim();

    if (keyword === "") {
        document.getElementById("searchResults").innerHTML = "";
        return;
    }

    fetch(`http://localhost:8080/api/checklist/search?keyword=${encodeURIComponent(keyword)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Search failed");
            }
            return response.json();
        })
        .then(data => {
            displaySearchResults(data);
        })
        .catch(error => {
            console.error("Error:", error);
            document.getElementById("searchResults").innerHTML = "<p style='color: red;'>No matching tasks found.</p>";
        });
}
