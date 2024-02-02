document.addEventListener('DOMContentLoaded', function () {
    // Fetch and display groups on page load
    fetchGroups();

    // Event listener for adding an expense
    document.getElementById('expenseForm').addEventListener('submit', function (event) {
        event.preventDefault();
        addExpense();
    });
});

async function fetchGroups() {
    try {
        const response = await fetch('http://localhost:8080/api/groups');
        const groups = await response.json();

        const groupsList = document.getElementById('groupsList');
        groupsList.innerHTML = ''; // Clear previous content

        if (Array.isArray(groups)) {
            groups.forEach(group => {
                // ... (rest of your code for displaying groups)
            });
        } else {
            console.error('Invalid response format:', groups);
        }
    } catch (error) {
        console.error('Error fetching groups:', error);
    }
}


async function addExpense() {
    const amount = document.getElementById('amount').value;
    const category = document.getElementById('category').value;
    const date = document.getElementById('date').value;
    const description = document.getElementById('description').value;

    const groupId = 1; // Replace with the actual group ID

    const expense = {
        amount: parseFloat(amount),
        category: category,
        date: date,
        description: description
    };

    try {
        const response = await fetch(`http://localhost:8080/api/groups/${groupId}/expenses`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(expense),
        });

        if (response.status === 201) {
            // Refresh the groups after adding the expense
            fetchGroups();

            // Clear the form
            document.getElementById('amount').value = '';
            document.getElementById('category').value = '';
            document.getElementById('date').value = '';
            document.getElementById('description').value = '';
        } else {
            console.error('Error adding expense:', response.statusText);
        }
    } catch (error) {
        console.error('Error adding expense:', error);
    }
}
