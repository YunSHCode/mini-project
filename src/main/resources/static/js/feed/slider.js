document.addEventListener("DOMContentLoaded", () => {
let currentIndex = 0;
const slides = document.querySelectorAll(".media_unit");

function showSlide(index) {
    slides.forEach((slide, i) => {
        slide.style.display = i === index ? "block" : "none";
    });
}

document.getElementById("prevBtn").addEventListener("click", () => {
    currentIndex = (currentIndex - 1 + slides.length) % slides.length;
    showSlide(currentIndex);
});

document.getElementById("nextBtn").addEventListener("click", () => {
    currentIndex = (currentIndex + 1) % slides.length;
    showSlide(currentIndex);
});

// 첫 슬라이드를 초기화합니다.
showSlide(currentIndex);
});