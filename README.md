# Lokal Job App

Lokal Job App is an Android application designed to help users explore job listings, view detailed job information, and bookmark jobs for offline viewing. The app is built using modern Android development tools and libraries, ensuring a smooth and efficient user experience.

## 🛠️ Technologies Used

- **Kotlin**: For writing clean and concise code.
- **Jetpack Compose**: For building a responsive and intuitive UI.
- **Room**: For local database storage of bookmarked jobs.
- **Retrofit**: For making network requests to fetch job data.

## 🌟 Features

- **Splash Screen**: 
  - A welcoming splash screen that greets users on app launch.
  
- **Bottom Navigation UI**: 
  - Navigate between two primary sections: "Jobs" and "Bookmarks".
  
- **Jobs Screen**:
  - Fetches job data from the API in a paginated manner.
  - Displays job title, location, salary, and contact information in each card.
  - Supports appropriate states: loading, error, and empty.

- **Job Details Screen**:
  - Displays detailed information about a selected job.
  
- **Bookmarking**:
  - Allows users to bookmark jobs for easy access later.
  - All bookmarked jobs are stored locally in a Room database for offline viewing.

## 📋 Functional Requirements

1. **API Integration**:
   - Fetch job data from the provided API endpoint: [Get Lokal Jobs API](https://testapi.getlokalapp.com/common/jobs?page=1).

2. **Navigation**:
   - On app launch, users are presented with a bottom navigation UI featuring "Jobs" and "Bookmarks" sections.
   - Clicking on a job card in the "Jobs" section navigates to a detailed view screen.

3. **Pagination**:
   - Implement a paginated approach to fetch job data, ensuring efficient loading and display.

4. **Bookmarking Functionality**:
   - Users can bookmark jobs which will appear in the "Bookmarks" tab.
   - Bookmarked jobs are stored in a local database for offline access.

5. **State Management**:
   - Handle various states (loading, error, empty) appropriately across the app to ensure a seamless user experience.

6. **UI/UX Best Practices**:
   - Follow modern UI/UX design principles to create a visually appealing and user-friendly interface.
   - The app's design is optimized for light mode.

## 🚀 Getting Started

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Keshav-Biyani/Job_App.git

![IMG-20240826-WA0034](https://github.com/user-attachments/assets/dbaa0a12-29ab-4900-aa49-d3831b1ca4a1)
![IMG-20240826-WA0033](https://github.com/user-attachments/assets/d5d5edb4-35d3-45e7-9354-e4cf6854ff8c)
![IMG-20240826-WA0035](https://github.com/user-attachments/assets/f1f950a0-d387-4718-a99f-04d40985a3a9)

![IMG-20240826-WA0036](https://github.com/user-attachments/assets/a53d4b5f-e820-4006-8665-67f384e972ac)
![IMG-20240826-WA0037](https://github.com/user-attachments/assets/7937086f-4f2b-4150-8c7f-095225e9b118)
