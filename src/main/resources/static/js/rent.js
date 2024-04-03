/**
 * Initializes the total price and calculates it on page load.
 */
window.onload = function() {
	var initialPrice = parseFloat(document.getElementById("total-price").textContent);
	document.getElementById("total-price").setAttribute("data-initial-price", initialPrice);
	calculateTotal();
};

/**
 * Calculates the total price based on the selected rental days.
 * @returns {string} The total price formatted with two decimal places.
 */
function calculateTotal() {
	var initialPrice = parseFloat(document.getElementById("total-price").getAttribute("data-initial-price"));
	var selectedDays = parseInt(document.getElementById("rental-days").value);
	var totalPrice = selectedDays * initialPrice;
	document.getElementById("total-price").textContent = totalPrice.toFixed(2);
	document.getElementById("rentalDays").value = selectedDays; // Update rentalDays value
	document.getElementById("totalPrice").value = totalPrice.toFixed(2); // Update totalPrice value
	return totalPrice.toFixed(2);
}

/**
 * Confirms the transaction with the user before submitting the form.
 */
function confirmTransaction() {
	if (confirm("Are you sure you want to rent this movie for " + document.getElementById("rental-days").value + " day(s) for a total price of $" + calculateTotal() + "?")) {
		// If user confirms, submit the form
		document.getElementById("rentForm").submit();
	} else {
		// If user cancels, do nothing or provide feedback
		alert("Transaction canceled.");
	}
}