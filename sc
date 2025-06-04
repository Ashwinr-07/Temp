<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video Content Overview</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
            background-color: #f9fafb;
            line-height: 1.6;
        }

        .header-image {
            width: 100%;
            height: 250px;
            object-fit: cover;
            display: block;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 2rem 1rem;
        }

        .video-section {
            background: white;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            margin-bottom: 2rem;
            padding: 2rem;
        }

        .video-section h1 {
            font-size: 2rem;
            color: #1f2937;
            margin-bottom: 1.5rem;
            display: flex;
            align-items: center;
        }

        .play-icon {
            width: 32px;
            height: 32px;
            margin-right: 12px;
            color: #2563eb;
        }

        video {
            width: 100%;
            max-height: 400px;
            border-radius: 8px;
            background: #000;
        }

        .content-grid {
            display: grid;
            grid-template-columns: 3fr 2fr;
            gap: 2rem;
        }

        @media (max-width: 768px) {
            .content-grid {
                grid-template-columns: 1fr;
            }
        }

        .summary-section, .chapters-section {
            background: white;
            border-radius: 8px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .section-header {
            padding: 1.5rem;
            border-bottom: 1px solid #e5e7eb;
        }

        .section-header h2 {
            font-size: 1.25rem;
            color: #1f2937;
            display: flex;
            align-items: center;
        }

        .section-icon {
            width: 20px;
            height: 20px;
            margin-right: 8px;
            color: #2563eb;
        }

        .section-content {
            padding: 1.5rem;
        }

        .summary-text {
            color: #374151;
            white-space: pre-line;
        }

        .chapter-item {
            padding: 1rem;
            border: 1px solid #e5e7eb;
            border-radius: 8px;
            margin-bottom: 0.75rem;
            cursor: pointer;
            transition: all 0.2s;
        }

        .chapter-item:hover {
            background-color: #f9fafb;
        }

        .chapter-item.active {
            background-color: #eff6ff;
            border-color: #3b82f6;
        }

        .chapter-number {
            display: inline-flex;
            align-items: center;
            justify-content: center;
            width: 32px;
            height: 32px;
            background-color: #3b82f6;
            color: white;
            border-radius: 50%;
            font-size: 0.875rem;
            font-weight: 500;
            margin-right: 12px;
            flex-shrink: 0;
        }

        .chapter-item.active .chapter-number {
            background-color: #2563eb;
        }

        .chapter-content {
            flex: 1;
        }

        .chapter-title {
            font-weight: 500;
            color: #1f2937;
            margin-bottom: 4px;
        }

        .chapter-description {
            font-size: 0.875rem;
            color: #6b7280;
            margin-bottom: 4px;
        }

        .chapter-timestamp {
            font-size: 0.75rem;
            color: #2563eb;
            font-weight: 500;
        }

        .chapter-item-inner {
            display: flex;
            align-items: flex-start;
        }

        .loading {
            text-align: center;
            padding: 2rem;
            color: #6b7280;
        }

        .error {
            text-align: center;
            padding: 2rem;
            color: #dc2626;
        }
    </style>
</head>
<body>
    <!-- Header Image -->
    <img src="https://images.unsplash.com/photo-1551288049-bebda4e38f71?ixlib=rb-4.0.3&auto=format&fit=crop&w=1920&h=600" 
         alt="Application Screenshot" 
         class="header-image">

    <div class="container">
        <!-- Video Section -->
        <div class="video-section">
            <h1>
                <svg class="play-icon" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14.828 14.828a4 4 0 01-5.656 0M9 10h1m4 0h1m-6 4h1m4 0h1M5 20h14a2 2 0 002-2V6a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
                </svg>
                Content Overview
            </h1>
            <video id="mainVideo" controls preload="metadata">
                <source src="https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4" type="video/mp4">
                Your browser does not support the video tag.
            </video>
        </div>

        <!-- Content Grid -->
        <div class="content-grid">
            <!-- Summary Section -->
            <div class="summary-section">
                <div class="section-header">
                    <h2>
                        <svg class="section-icon" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path>
                        </svg>
                        Summary
                    </h2>
                </div>
                <div class="section-content">
                    <div id="summaryContent" class="loading">Loading summary...</div>
                </div>
            </div>

            <!-- Chapters Section -->
            <div class="chapters-section">
                <div class="section-header">
                    <h2>
                        <svg class="section-icon" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 10h16M4 14h16M4 18h16"></path>
                        </svg>
                        Chapters
                    </h2>
                </div>
                <div class="section-content">
                    <div id="chaptersContent" class="loading">Loading chapters...</div>
                </div>
            </div>
        </div>
    </div>

    <script src="script.js"></script>
</body>
</html>
