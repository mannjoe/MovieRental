let currentIndex = 0;
const images = document.querySelectorAll('.banner-image');
let intervalId; // Variable to store the interval ID

// Function to show the next image
function nextImage() {
	currentIndex = (currentIndex + 1) % images.length;
	showImage();
}

// Function to show the previous image
function prevImage() {
	currentIndex = (currentIndex - 1 + images.length) % images.length;
	showImage();
}

// Function to show the image at currentIndex
function showImage() {
	images.forEach((image, index) => {
		if (index === currentIndex) {
			image.style.display = 'block';
		} else {
			image.style.display = 'none';
		}
	});
}

// Automatically move to the next image every 5 seconds
function startInterval() {
	intervalId = setInterval(nextImage, 5000);
}

// Reset the interval when the user clicks on left or right buttons
function resetInterval() {
	clearInterval(intervalId); // Clear the existing interval
	startInterval(); // Set a new interval
}

// Show the default image (the first one)
showImage();
startInterval(); // Start the interval initially

// Add event listeners to left and right buttons
const leftButton = document.querySelector('.left-round-button');
const rightButton = document.querySelector('.right-round-button');

leftButton.addEventListener('click', () => {
	prevImage();
	resetInterval(); // Reset the interval when left button is clicked
});

rightButton.addEventListener('click', () => {
	nextImage();
	resetInterval(); // Reset the interval when right button is clicked
});