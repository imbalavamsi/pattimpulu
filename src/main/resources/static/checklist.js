document.addEventListener("DOMContentLoaded", loadChecklist);

// Validate Input and Enable Button
function validateInput() {
    const taskInput = document.getElementById("taskInput").value.trim();
    document.getElementById("addTaskButton").disabled = taskInput === "";
}
async function loadChecklist() {
    const tasks = await fetchChecklist();
    const checklist = document.getElementById("checklist");
    checklist.innerHTML = "";

    tasks.forEach(task => {
        const li = document.createElement("li");
        li.id = `task-${task.id}`;  // Assign unique ID for easy removal
        li.innerHTML = `
            <input type="checkbox" ${task.completed ? "checked" : ""}
                onchange="toggleChecklistItem(${task.id}, this.checked)">
            <span class="${task.completed ? "completed" : ""}">${task.title}</span>
            <button onclick="editChecklistItem(${task.id})">View/Edit</button>
            <button onclick="removeChecklistItem(${task.id})">Delete</button>
        `;
        checklist.appendChild(li);
    });
}


// Add Task
async function addChecklistItem() {
    const taskInput = document.getElementById("taskInput");
    const newItem = { title: taskInput.value.trim(), completed: false };

    await createChecklistItem(newItem);
    taskInput.value = "";
    document.getElementById("addTaskButton").disabled = true;
    loadChecklist();
}

// Toggle task completion
async function toggleChecklistItem(id, completed) {
    const item = await getChecklistItemById(id);

    if (!item || !item.title || item.title.trim() === "") {
        console.error("Invalid item, skipping update.");
        return;
    }

    const updatedItem = {
        title: item.title,  // Ensure title remains unchanged
        description: item.description || "",  // Ensure description remains unchanged
        completed: completed,
        version: item.version + 1  // Increment version number
    };

    await updateChecklistItemAPI(id, updatedItem);
    loadChecklist();
}

// Delete Task & Remove from UI
async function removeChecklistItem(id) {
    await deleteChecklistItem(id);
    document.getElementById(`task-${id}`)?.remove();  // Remove task from UI
    loadChecklist();
}


// Open Modal for Editing
async function editChecklistItem(id) {
    const item = await getChecklistItemById(id);

    document.getElementById("editTitle").value = item.title;
    document.getElementById("editDescription").value = item.description || "";
    document.getElementById("editCompleted").checked = item.completed;
    document.getElementById("taskModal").dataset.id = id;

    document.getElementById("taskModal").style.display = "block";
}

// Update Checklist Item
async function updateChecklistItem() {
    const id = document.getElementById("taskModal").dataset.id;
    const existingItem = await getChecklistItemById(id);

    const updatedItem = {
        title: document.getElementById("editTitle").value.trim() || existingItem.title,
        description: document.getElementById("editDescription").value.trim() || existingItem.description,
        completed: document.getElementById("editCompleted").checked,
        version: existingItem.version + 1  // Ensure versioning is updated
    };

    await updateChecklistItemAPI(id, updatedItem);
    closeModal();
    loadChecklist();
}


// Close Modal
function closeModal() {
    document.getElementById("taskModal").style.display = "none";
}

function displaySearchResults(tasks) {
    const resultDiv = document.getElementById("searchResults");
    resultDiv.innerHTML = "";

    if (tasks.length === 0) {
        resultDiv.innerHTML = "<p style='color: red;'>No matching tasks found.</p>";
        return;
    }

    tasks.forEach(task => {
        const taskElement = document.createElement("p");
        taskElement.innerHTML = `<strong>${task.title}</strong>: ${task.description}`;
        resultDiv.appendChild(taskElement);
    });
}
