/**
 * Represents the current index of the displayed image.
 * @type {number}
 */
let currentIndex = 0;

/**
 * Holds references to all banner images.
 * @type {NodeListOf<Element>}
 */
const images = document.querySelectorAll('.banner-image');

/**
 * ID of the interval for automatically transitioning between images.
 * @type {number}
 */
let intervalId;

/**
 * Shows the next image in the banner.
 */
function nextImage() {
	currentIndex = (currentIndex + 1) % images.length;
	showImage();
}

/**
 * Shows the previous image in the banner.
 */
function prevImage() {
	currentIndex = (currentIndex - 1 + images.length) % images.length;
	showImage();
}

/**
 * Displays the image at the current index.
 */
function showImage() {
	images.forEach((image, index) => {
		if (index === currentIndex) {
			image.style.display = 'block';
		} else {
			image.style.display = 'none';
		}
	});
}

/**
 * Starts the interval for automatically transitioning between images.
 */
function startInterval() {
	intervalId = setInterval(nextImage, 5000);
}

/**
 * Resets the interval when the user clicks on the left or right buttons.
 */
function resetInterval() {
	clearInterval(intervalId);
	startInterval();
}

// Show the default image (the first one)
showImage();
startInterval();

// Add event listeners to left and right buttons
const leftButton = document.querySelector('.left-round-button');
const rightButton = document.querySelector('.right-round-button');

leftButton.addEventListener('click', () => {
	prevImage();
	resetInterval();
});

rightButton.addEventListener('click', () => {
	nextImage();
	resetInterval();
});