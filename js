// Global variables
let currentChapter = 0;
let chaptersData = [];
const video = document.getElementById('mainVideo');

// Parse timestamp to seconds
function parseTimestamp(timestamp) {
    const parts = timestamp.split(':').map(Number);
    if (parts.length === 2) {
        return parts[0] * 60 + parts[1]; // MM:SS
    } else if (parts.length === 3) {
        return parts[0] * 3600 + parts[1] * 60 + parts[2]; // HH:MM:SS
    }
    return 0;
}

// Handle chapter click
function handleChapterClick(chapter, index) {
    currentChapter = index;
    
    // Update active chapter styling
    document.querySelectorAll('.chapter-item').forEach((item, i) => {
        if (i === index) {
            item.classList.add('active');
        } else {
            item.classList.remove('active');
        }
    });
    
    // Jump to timestamp in video
    if (chapter.timestamp && video) {
        const timeInSeconds = parseTimestamp(chapter.timestamp);
        
        if (video.readyState >= 1) {
            video.currentTime = timeInSeconds;
            video.play().catch(e => console.log('Autoplay prevented:', e));
        } else {
            video.addEventListener('loadedmetadata', () => {
                video.currentTime = timeInSeconds;
                video.play().catch(e => console.log('Autoplay prevented:', e));
            }, { once: true });
        }
    }
}

// Load summary content
async function loadSummary() {
    try {
        const response = await fetch('summary.txt');
        if (!response.ok) {
            throw new Error('Failed to load summary');
        }
        const summaryText = await response.text();
        document.getElementById('summaryContent').innerHTML = `<div class="summary-text">${summaryText}</div>`;
    } catch (error) {
        document.getElementById('summaryContent').innerHTML = `
            <div class="error">
                <p><strong>Summary Not Available</strong></p>
                <p>Unable to load summary.txt. Please check if the file exists.</p>
            </div>
        `;
    }
}

// Load chapters content
async function loadChapters() {
    try {
        const response = await fetch('chapters.json');
        if (!response.ok) {
            throw new Error('Failed to load chapters');
        }
        chaptersData = await response.json();
        
        const chaptersHtml = chaptersData.map((chapter, index) => `
            <div class="chapter-item ${index === 0 ? 'active' : ''}" onclick="handleChapterClick(chaptersData[${index}], ${index})">
                <div class="chapter-item-inner">
                    <span class="chapter-number">${index + 1}</span>
                    <div class="chapter-content">
                        <div class="chapter-title">${chapter.title}</div>
                        ${chapter.description ? `<div class="chapter-description">${chapter.description}</div>` : ''}
                        ${chapter.timestamp ? `<div class="chapter-timestamp">${chapter.timestamp}</div>` : ''}
                    </div>
                </div>
            </div>
        `).join('');
        
        document.getElementById('chaptersContent').innerHTML = chaptersHtml;
    } catch (error) {
        document.getElementById('chaptersContent').innerHTML = `
            <div class="error">
                <p><strong>Chapters Not Available</strong></p>
                <p>Unable to load chapters.json. Please check if the file exists.</p>
            </div>
        `;
    }
}

// Get video URL from URL parameters
function getVideoUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    const videoUrl = urlParams.get('video');
    if (videoUrl) {
        video.src = videoUrl;
    }
}

// Initialize the page
document.addEventListener('DOMContentLoaded', function() {
    getVideoUrl();
    loadSummary();
    loadChapters();
});
